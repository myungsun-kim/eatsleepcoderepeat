package jhlee;

import java.io.*;
import java.util.*;
import java.sql.*;

import java.lang.Math;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.*;


public class RPJoin {
	   public static class MapClass1 extends Mapper<Object, Text, Text, Text> {
		 	private int joinatt = 0;	// join attribute
			private Text emitkey = new Text ();
			private Text emitval = new Text ();

			public void setup(Context context) throws IOException {
				Configuration configuration = context.getConfiguration();
				joinatt = configuration.getInt("joinatt",0);
			}
			// Text : input line
			// --> format = <Relation id> \tab <record id> \tab <dimension 1> \tab <dimension 2>
			// if the join attribute is dimension1, joinatt = 2
			// relatation id => 0, record id => 1, dimension 1 => 2, dimension 2 => 3
			public void map (Object key, Text value, Context context) throws IOException, InterruptedException
			{
				// TO DO



			}
	   }
	   public static class ReduceClass1 extends Reducer<Text, Text, Text, Text> {
	 	    private ArrayList<String> r;	// array for storing records of R
			private ArrayList<String> s;	// array for storing records of S
			private Text emitkey = new Text ();
			private Text emitval = new Text ();

			public void setup(Context context) throws IOException {
			}

			public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
				r = new ArrayList<String>();
				s = new ArrayList<String>();
				
				// TO DO



			}
		}

	   	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
			Configuration conf = new Configuration ();
	    	String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
			if (otherArgs.length != 3) {
				System.out.println ("usage: <joinatt> <in> <out>");
				System.exit(1);
			}
			conf.setInt ("joinatt", Integer.parseInt(otherArgs[0]));
			Job job = new Job (conf, "repartition-join");
			job.setJarByClass(RPJoin.class);
			job.setNumReduceTasks (1);
			job.setMapperClass(MapClass1.class);
			job.setReducerClass(ReduceClass1.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			FileInputFormat.addInputPath(job, new Path(otherArgs[1]));
			FileOutputFormat.setOutputPath(job, new Path(otherArgs[2]));
			if (! job.waitForCompletion(true))
				System.exit (1);
			}
}

