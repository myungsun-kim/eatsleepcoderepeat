package jhlee;

import java.io.IOException;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Minhash {

	public static class MinMapper extends Mapper<Object, Text, Text, Text> {

		private Text	lsh	= new Text();

		private int		domain;
		private int		hash;
		private long	seed;
		private int[]	a;
		private int[]	b;
		private int[]	minval;

		private int getSig(int id, int dim) {
			int sig = (a[dim] * id + b[dim]) % domain;
			if(sig < 0) {
				sig += domain;
			}
			return sig;
		}

		public void setup(Context context) throws IOException {
			Configuration configuration = context.getConfiguration();
			domain = configuration.getInt("domain", 100);
			hash = configuration.getInt("hash", 100);
			seed = configuration.getLong("seed", 10);

			a = new int[hash];
			b = new int[hash];
			minval = new int[hash];
			Random random = new Random(seed);
			for(int i = 0 ; i < hash ; i++) {
				a[i] = 1 + random.nextInt(domain - 1);
				b[i] = random.nextInt(domain);
			}
		}

		public void map(Object unused, Text value, Context context) throws IOException, InterruptedException {
			//
		}
	}

	public static class MinReducer extends Reducer<Text, Text, Text, Text> {

		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			for(Text val : values) {
				context.write(key, val);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if(otherArgs.length < 4) {
			System.err.println("Usage: minhash <in> <out> <domain> <hash> <seed>");
			System.exit(2);
		}
		Job job = new Job(conf, "minhash");
		job.setJarByClass(Minhash.class);
		job.setMapperClass(MinMapper.class);
		job.setReducerClass(MinReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		job.getConfiguration().setInt("domain", Integer.parseInt(otherArgs[2]));
		job.getConfiguration().setInt("hash", Integer.parseInt(otherArgs[3]));
		long seed = 0;
		if(otherArgs.length == 5) {
			seed = Long.parseLong(otherArgs[4]);
		}
		else {
			Random random = new Random();
			seed = random.nextLong();
		}
		job.getConfiguration().setLong("seed", seed);

		FileSystem.get(job.getConfiguration()).delete(new Path(otherArgs[1]), true);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
