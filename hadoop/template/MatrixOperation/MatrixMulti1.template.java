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

public class MatrixMulti1 {
	// Map
	public static class MMMapper extends Mapper<Object, Text, Text, Text>{
		private Text keypair = new Text();	// emit할 key로 사용할 변수
		private Text valpair= new Text();	// emit할 value로 사용할 변수
		private int n;	// matrix A의 행
		private int m;	// matrix B의 
		protected void setup(Context context) throws IOException, InterruptedException {
			Configuration config = context.getConfiguration();
			n = config.getInt("n",10);	 // matrix A의 행 받아 오기
			m = config.getInt("m",10);	 // matrix B의 열 받아 오기
		}
		public void map(Object key, Text value, Context context
				) throws IOException, InterruptedException {

			/*
			 * fill here
			 */
			// --------------------------------------
			// --------------------------------------

		}
	}
	// Reduce
	public static class MMReducer extends Reducer<Text, Text, Text, IntWritable> {
		private IntWritable val = new IntWritable();	// emit할 value로 사용할 변수
		private int l;	// matrix A의 행

		protected void setup(Context context) throws IOException, InterruptedException {
			Configuration config = context.getConfiguration();
			l = config.getInt("l",10);	 // matrix A의 열 받아 오기(matrix B의 행)
		}
		public void reduce(Text key, Iterable<Text> values, Context context) 
			throws IOException, InterruptedException {

			/*
			 * fill here
			 */
			// --------------------------------------
			// --------------------------------------

		}
	}
	// Main
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length != 5) {
			System.err.println("Usage: wordcount <in> <out> <n> <m> <l>");
			System.exit(2);
		}
		Job job = new Job(conf, "matrix multiplication 1");
		job.setJarByClass(MatrixMulti1.class);
		job.setMapperClass(MMMapper.class);
		job.setReducerClass(MMReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setNumReduceTasks(2);

		Configuration config = job.getConfiguration();
		config.setInt("n",Integer.parseInt(otherArgs[2]));
		config.setInt("m",Integer.parseInt(otherArgs[3]));
		config.setInt("l",Integer.parseInt(otherArgs[4]));
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		FileSystem.get(config).delete(new Path(otherArgs[1]),true);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
