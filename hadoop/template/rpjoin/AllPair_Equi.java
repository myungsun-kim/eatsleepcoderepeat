package yjpark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class AllPair_Equi {
	public static class MapClass1 extends Mapper<Object, Text, Text, Text> {

		private int joinatt = 0; // join attribute
		private int numberOfPartition = 2; // number of partition

		private Text emitkey = new Text();
		private Text emitval = new Text();

		public void setup(Context context) throws IOException {
			Configuration configuration = context.getConfiguration();
			numberOfPartition = configuration.getInt("partition", 2);
		}

		// Text : input line
		// --> format = <Relation id> \tab <record id> \tab <dimension 1> \tab
		// <dimension 2>
		// if the join attribute is dimension1, joinatt = 2
		// relatation id => 0, record id => 1, dimension 1 => 2, dimension 2 =>
		// 3

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			
			String[] tuple = value.toString().split( "\t" );
			
			Random rn = new Random();
			int partitionId = rn.nextInt( numberOfPartition );
			
			for( int i = 0; i < numberOfPartition; i++ ) {
				
				// TODO
				String text;
				if( tuple[ 0 ].equals( "r" ) || tuple[ 0 ].equals( "R" ) ) {
					text = i + " " + partitionId;
				}
				else {
					text = partitionId + " " + i;
				}
				
				emitkey.set( text );
				context.write(emitkey, value);
			}
		}
	}

	public static class ReduceClass1 extends Reducer<Text, Text, Text, Text> {

		private ArrayList<String> r; // array for storing records of R
		private ArrayList<String> s; // array for storing records of S
		private Text emitkey = new Text();
		private Text emitval = new Text();
		
		private int joinatt = 0; // join attribute

		public void setup(Context context) throws IOException {
			Configuration configuration = context.getConfiguration();
			joinatt = configuration.getInt("joinatt", 0);
		}

		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			r = new ArrayList<String>();
			s = new ArrayList<String>();
			
			String[] tuple;
			for (Text value : values) {
				tuple = value.toString().split("\t");
				if (tuple[0].equals("r")) {
					r.add(value.toString());
				} else {
					s.add(value.toString());
				}
			}
			
			for (String rtuple : r) {
				String[] r_values = rtuple.split( "\t" );
				
				for (String stuple : s) {
					String[] s_values = stuple.split( "\t" );
					
					if( r_values[joinatt+1].equals( s_values[ joinatt+1])) {
						emitval.set(rtuple + "	" + stuple);
						context.write(emitkey, emitval);
					}
				}
			}

			r.clear();
			s.clear();
		}
	}

	public static void main(String[] args) throws IOException,
			InterruptedException, ClassNotFoundException {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();
		if (otherArgs.length != 4) {
			System.out.println("usage: <joinatt> <partition> <in> <out>");
			System.exit(1);
		}
		conf.setInt("joinatt", Integer.parseInt(otherArgs[0]));
		conf.setInt("partition", Integer.parseInt(otherArgs[1]));
		Job job = new Job(conf, "repartition-join");
		job.setJarByClass(AllPair_Equi.class);
		job.setNumReduceTasks(1);
		job.setMapperClass(MapClass1.class);
		job.setReducerClass(ReduceClass1.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job, new Path(otherArgs[2]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[3]));
		if (!job.waitForCompletion(true))
			System.exit(1);
	}
}
