package ssafy;

import java.io.IOException;
import java.util.StringTokenizer;

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
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class WordcountInvertedIndex {
	/* 
	Object, Text : input key-value pair type (always same (to get a line of input file))
	Text, IntWritable : output key-value pair type
	*/

	public static class TokenizerMapper
			extends Mapper<Object,Text,Text,Text> {

		
		// variable declairations
		private Text word = new Text();
		private Text invertedIndex = new Text();

		private String filename;
		@Override
		protected void setup(Context context) throws IOException, InterruptedException{
			filename = ((FileSplit)context.getInputSplit()).getPath().getName();
		}
		// map function (Context -> fixed parameter)
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {

			// value.toString() : get a line
			StringTokenizer itr = new StringTokenizer(value.toString());
			long offset = ((LongWritable) key).get();
			while ( itr.hasMoreTokens() ) {
				String token = itr.nextToken();
				word.set(token);
				StringBuilder sb = new StringBuilder(filename);
				sb.append(": ").append(offset);
				invertedIndex.set(sb.toString());
				// emit a key-value pair
				context.write(word,invertedIndex);
				
				offset += token.length();
			}
		}
	}

	/*
	Text, IntWritable : input key type and the value type of input value list
	Text, IntWritable : output key-value pair type
	*/
	public static class IndexSumReducer
			extends Reducer<Text,Text,Text,Text> {

		public void reduce(Text key, Iterable<Text> values, Context context) 
				throws IOException, InterruptedException {

			StringBuilder sb = new StringBuilder();
			for ( Text val : values ) {
				sb.append(val.toString()).append(", ");
			}
			if(sb.length() > 2){
				sb.setLength(sb.length()-2);
			}
			Text concatenated = new Text();
			concatenated.set(sb.toString());
			context.write(key,concatenated);
		}
	}


	/* Main function */
	public static void main(String[] args) throws Exception {
		System.out.println ("test 1");
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
		if ( otherArgs.length != 2 ) {
			System.err.println("Usage: wordcount <in> <out>");
			System.exit(2);
		}
		Job job = new Job(conf,"word count");
		job.setJarByClass(Wordcount.class);

		// let hadoop know my map and reduce classes
		job.setMapperClass(TokenizerMapper.class);
		job.setReducerClass(IndexSumReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		// set number of reduces
		job.setNumReduceTasks(2);

		// set input and output directories
		FileInputFormat.addInputPath(job,new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job,new Path(otherArgs[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1 );
	}
}

