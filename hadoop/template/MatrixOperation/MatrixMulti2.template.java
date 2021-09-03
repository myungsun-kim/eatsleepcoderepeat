package jhlee;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class MatrixMulti2 {
	public static class M1Mapper extends Mapper<Object, Text, Text, IntWritable>{
		// phase1 Map
		private Text pair = new Text();
		private IntWritable val = new IntWritable();
		private int n;
		private int m;
		protected void setup(Context context) throws IOException, InterruptedException {
			Configuration config = context.getConfiguration();
			n = config.getInt("n",10);	
			m = config.getInt("m",10);	
		}
		public void map(Object key, Text value, Context context
				) throws IOException, InterruptedException {



		}
	}
	// phase1 Reduce
	public static class M1Reducer extends Reducer<Text,IntWritable,Text,IntWritable> {
		private IntWritable result = new IntWritable();	
		private Text pair = new Text();		
		public void reduce(Text key, Iterable<IntWritable> values, Context context) 
			throws IOException, InterruptedException {



			}
	}
	// phase2 Map
	public static class M2Mapper extends Mapper<Object, Text, Text, IntWritable>{    
		private Text pair = new Text();	
		private IntWritable val = new IntWritable();
		public void map(Object key, Text value, Context context) 
			throws IOException, InterruptedException {



			}
	}
	// phase2 Reduce
	public static class M2Reducer extends Reducer<Text,IntWritable,Text,IntWritable> {
		private IntWritable result = new IntWritable();
		public void reduce(Text key, Iterable<IntWritable> values, Context context) 
			throws IOException, InterruptedException {



			}
	}  
	// main
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length != 4) {
			System.err.println("Usage: wordcount <in> <out> <n> <m>");
			System.exit(2);
		}
		// phase 1
		Job job1 = new Job(conf, "2-phase matrix multiplication 1");
		job1.setJarByClass(MatrixMulti2.class);
		job1.setMapperClass(M1Mapper.class);
		job1.setReducerClass(M1Reducer.class);
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(IntWritable.class);
		job1.setNumReduceTasks(2);

		Configuration config = job1.getConfiguration();
		config.setInt("n",Integer.parseInt(otherArgs[2]));
		config.setInt("m",Integer.parseInt(otherArgs[3]));

		FileInputFormat.addInputPath(job1, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job1, new Path("tmp"));	
		FileSystem.get(config).delete(new Path("tmp"),true);
		// phase 2
		Job job2 = new Job(conf, "2-phase matrix multiplication 2");
		job2.setJarByClass(MatrixMulti2.class);
		job2.setMapperClass(M2Mapper.class);
		job2.setReducerClass(M2Reducer.class);
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(IntWritable.class);
		job2.setNumReduceTasks(2);

		FileInputFormat.addInputPath(job2, new Path("tmp"));
		FileOutputFormat.setOutputPath(job2, new Path(otherArgs[1]));
		FileSystem.get(config).delete(new Path(otherArgs[1]),true);

		job1.waitForCompletion(true);
		job2.waitForCompletion(true);
	}

}

