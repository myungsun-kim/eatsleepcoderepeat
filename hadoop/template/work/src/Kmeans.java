package shim;

import java.io.IOException;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Kmeans {

	/*
	 * MapperClass
	 *
	 * parameters:
	 *   Object, Text : input key-value pair type (always same - get a line of input file)
	 *   IntWritable, Text : output key-value pair type
	 *       IntWriteable --> the closest cluster center number
	 *       Text : vector (separated by tab)
	 */
	public static class MapperClass
			extends Mapper<Object,Text,IntWritable,Text> {

		private final int d = 2;
		private IntWritable emitKey = new IntWritable();

		// IMPORTANT: the number of clusters
		private int mK;

		// IMPORTANT: the vectors for cluster centers
		private double[][] centers =  null;
		private double[] point = null;
			
		// get the parameters set by the main function (see the main function. we call "conf.set")
		//
		protected void setup(Context context) throws IOException, InterruptedException {
			Configuration config = context.getConfiguration();

			// get the parameter of name "k"
			// 2 is the default value (getInt returns the default value if the param of "k" is not set)
			mK= config.getInt ("k", 2);

			// we have k number of cluster centers
			centers = new double[mK][d];

			// get the vectors of the k cluster centers 
			for (int i=0; i<mK; i++)
			{
				String center = config.get ("strCenters." + i);
				//System.out.println ("center " + i + ": " + center);
				String[] arr = center.split("\t");
				for (int j=0; j<d; j++)
					centers[i][j] = Float.parseFloat(arr[j]);
			}
			point = new double[d];

		}

		// map function (Object, Text : input key-value pair
		//               Context : fixed parameter)
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
				
			double mindist = 10000000, dist;
			int which_center = 0;
			stringToDoubleArray (value.toString(), d, point);

			/**
			 * fill here
			 */

			emitKey.set (which_center);

			// emit a key-value pair 
			//   emitKey : cluster id that the input point belongs to
			//   value : the input point
			context.write (emitKey, value);
		}
	}
	

	/*
	 * ReducerClass
	 *
	 * parameters:
	 *   IntWritable, Text : input key-value pair type which is the same with the output key-value pair
	 *                       type of MapperClass
	 *   IntWritable, Text : output key-value pair type
	 *      IntWritable --> the cluster number
	 *      Text --> the updated vector
	 */
	public static class ReducerClass
			extends Reducer<IntWritable,Text,IntWritable,Text> {

		private Text result = new Text();
		private int d = 2;

		public void reduce(IntWritable key, Iterable<Text> values, Context context) 
				throws IOException, InterruptedException {
			double[] sum = new double [d];
			double[] point = new double [d];

			int count = 0;

			/**
			 * fill here
			 */

			result.set(doubleArrayToString (sum, d));

			// emit a key-value pair 
			context.write(key,result);
		}
	}


	/*
	 * return the square of the Euclidean distance between two points
	 * ( we do not compute the square root )
	 */
	public static double computeDistance (double[] arr1, double[] arr2, int d) {
		if (arr1.length < d || arr2.length < d)
			return -1;

		double sum = 0;
		for (int i=0; i<d; i++) {
			sum += (arr1[i] - arr2[i]) * (arr1[i] - arr2[i]);
		}
		return sum;
	}


	/*
	 * convert a double type array to a string
	 */
	public static String doubleArrayToString (double[] arr, int d) {
		String str = "";
		if (d == 0) return str;

		str += arr[0];
		for (int i=1; i<d; i++) {
			str += "\t" + arr[i];
		}
		return str;
	}

	/*
	 * convert a string to a double type array
	 */
	public static boolean stringToDoubleArray (String str, int d, double[] arr) {
		if (arr.length < d)
			return false;

		String[] strarr = str.split ("\t");
		for (int i=0; i<strarr.length && i<d; i++) {
			arr[i] = Double.parseDouble (strarr[i]);
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
		if ( otherArgs.length != 2 ) {
			System.err.println("Usage: Kmeans <in> <out>");
			System.exit(2);
		}

		// maximum iteration
		int maxIter = 3;

		// dimension
		int d = 2;

		// initial centers
		// broadcasted to the map functions by using "conf.set"
		conf.setInt("k", 2);
		conf.set("strCenters.0", "1.5\t1.5");	
		conf.set("strCenters.1", "7.5\t7.5");

		double[] center=new double [d];

		for (int itr=0; itr<maxIter; itr++) {
			Job job = new Job(conf,"k-means clustering");
			job.setJarByClass(Kmeans.class);

			// let hadoop know the map and reduce classes
			job.setMapperClass(MapperClass.class);
			job.setReducerClass(ReducerClass.class);

			// let hadoop know the key-value pair type
			job.setOutputKeyClass(IntWritable.class);
			job.setOutputValueClass(Text.class);

			// set number of reduce functions (let's use 1 in the example, do not change!!)
			job.setNumReduceTasks(1);

			Path outdir = new Path (otherArgs[1]);
			if (FileSystem.get(conf).exists(outdir)) {
				FileSystem.get(conf).delete (outdir);
			}	

			// read the output of reduce function to obtain the updated cluster centers
			// the updated cluster centers are broadcasted again
			FileInputFormat.addInputPath(job,new Path(otherArgs[0]));
			FileOutputFormat.setOutputPath(job,new Path(otherArgs[1]));
			job.waitForCompletion(true);

			FileSystem fs = outdir.getFileSystem (conf);
			FSDataInputStream fp = fs.open (new Path (outdir + "/part-r-00000"));
			String line = null;
			while ( (line = fp.readLine ()) != null ) {
				String[] arr = line.split("\t");
				int centerId = Integer.parseInt (arr[0]);
				for (int i=1; i<=d; i++)
					center[i-1] = Double.parseDouble (arr[i]);

				conf.set ("strCenters." + centerId, doubleArrayToString (center, d));
			}
		}
	}
}
