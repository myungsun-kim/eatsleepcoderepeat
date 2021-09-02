package jhlee;

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

public class Clique {

	/*
	 * MapperClass
	 */
	public static class MapperClass extends Mapper<Object,Text,Text,IntWritable> {

		private final int d = 2;
		private final double interval = 10;
		private Text emitKey = new Text();
		private IntWritable one = new IntWritable(1);

		private int [][] candidates = null;
		private String [] candidateKeys = null;
		private int ncandidate = 0;
		private int step = 0;
		
		private double[] point = null;

		// get the parameters set by the main function (see the main function. we call "conf.set")
		protected void setup(Context context) throws IOException, InterruptedException {
			Configuration config = context.getConfiguration();

			step = config.getInt("step", 0);

			ncandidate = config.getInt("ncandidate", 0);

			candidates = new int [ncandidate][d];
			candidateKeys = new String [ncandidate];

			for (int i=0; i<ncandidate; i++) {
				candidateKeys[i] = config.get("candidate" + i);
				String[] arr = candidateKeys[i].split("\t");

				//System.out.println ("read : " + candidateKeys[i]);

				for (int j=0; j<d; j++) {
					candidates[i][j] = Integer.parseInt(arr[j]);
				}
			}

			point = new double [d];
		}

		// map function (Object, Text : input key-value pair
		//               Context : fixed parameter)
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

			// get the input line 
			stringToDoubleArray (value.toString(), d, point);

			/**
			 * fill here
			 */
			for (int c=0; c<ncandidate; c++) {
				boolean is_cand = true;

				// ------------------
				// ------------------

				if (is_cand) {
					emitKey.set (candidateKeys[c]);
					context.write (emitKey, one);
				}
			}
		}

		public int computeDimKey (double val) {
			return (int) (val / interval);
		}
	}
	

	/*
	 * ReducerClass
	 */
	public static class ReducerClass
			extends Reducer<Text,IntWritable,Text,IntWritable> {

		private IntWritable result = new IntWritable();
		private int d = 2;
		private int threshold;

		protected void setup(Context context) throws IOException, InterruptedException {
			Configuration config = context.getConfiguration();
			threshold = config.getInt ("threshold", 10);
		}

		public void reduce(Text key, Iterable<IntWritable> values, Context context) 
				throws IOException, InterruptedException {
			int sum = 0;

			/**
			 * fill here
			 */
			// -------------------------------
			// -------------------------------

			// emit a key-value pair 
			if (sum < threshold) {
				return;
			}

			result.set(sum);
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

	public static void generateCandidate (Configuration conf, Path outdir, int step) throws Exception {
		int cid1 = 0;
		int cid2 = 0;
		int[] freq1 = new int [100];
		int[] freq2 = new int [100];
		int count = 0;

		if (step == 1) {
			//System.out.println ("generate candidates for step = " + step);
			for (int i=0; i<10; i++)
				conf.set("candidate" + (count++), i + "\t" + "-1");

			for (int i=0; i<10; i++)
				conf.set("candidate" + (count++), "-1" + "\t" + i);
		}
		else if (step == 2) {
			// read dense units
			FileSystem fs = outdir.getFileSystem (conf);
			FSDataInputStream fp = fs.open (new Path (outdir + "/part-r-00000"));
			String line = null;

			while ( (line = fp.readLine ()) != null ) {
				String[] arr = line.split("\t");
				int id1 = Integer.parseInt (arr[0]);
				int id2 = Integer.parseInt (arr[1]);

				if (id1 >= 0) {
					freq1[cid1++] = id1;
				}

				if (id2 >= 0) {
					freq2[cid2++] = id2;
				}
			}

			for (int i=0; i<cid1; i++) {
				for (int j=0; j<cid2; j++) {
					conf.set("candidate" + (count++), "" + freq1[i] + "\t" + freq2[j]);
				}
			}
		}

		conf.setInt ("ncandidate", count);
	}

	// dimension (fixed)
	private static final int d = 2;

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
		if ( otherArgs.length != 3 ) {
			System.err.println("Usage: clique <threshold> <in> <out>");
			System.exit(1);
		}
		int threshold = Integer.parseInt (otherArgs[0]);

		// initial centers
		// broadcasted to the map functions by using "conf.set"
		conf.setInt("threshold", threshold);

		int maxIter = 10;
		int step = 0;
		for (int itr=0; itr<d; itr++) {
			conf.setInt("step", ++step);

			// generate candidates & broadcast them
			Path outdir = new Path (otherArgs[2]);
			generateCandidate (conf, outdir, step);

			Job job = new Job(conf,"clique clustering");
			job.setJarByClass(Clique.class);
			
			// let hadoop know the map and reduce classes
			job.setMapperClass(MapperClass.class);
			job.setReducerClass(ReducerClass.class);

			// let hadoop know the key-value pair type
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);

			// set number of reduce functions (let's use 1 in the example, do not change!!)
			job.setNumReduceTasks(1);

			// delete output path
			if (FileSystem.get(conf).exists(outdir)) {
				FileSystem.get(conf).delete (outdir);
			}

			FileInputFormat.addInputPath(job, new Path(otherArgs[1]));
			FileOutputFormat.setOutputPath(job, new Path(otherArgs[2]));
			job.waitForCompletion(true);
		}
	}
}
