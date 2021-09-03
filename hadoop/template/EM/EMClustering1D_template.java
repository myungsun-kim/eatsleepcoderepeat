package jhlee;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Random;
import java.lang.*; 
import java.lang.Math.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;//Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class EMClustering1D 
{

	public static int number_of_clusters;

	public final static double PI = 3.14159265;


	public static double cal_Gaussian(double f_value, double f_mean, double f_var)
	{
		return (1.0)/Math.pow(2*PI*f_var,0.5) * Math.exp(-1* Math.pow(f_value - f_mean,2.0) / (2*f_var));
		
	}

	
	public static class TokenizerMapper extends Mapper<Object,Text,Text, DoubleWritable> 
	{

		private Configuration configuration;

		public double[] f_means;
		public double[] f_vars;
		public double[] f_weights;
		protected void setup(Context context) throws IOException, InterruptedException
		{
			configuration = context.getConfiguration();
			
			number_of_clusters=Integer.parseInt(configuration.get("number_of_clusters"));

			f_means=new double[number_of_clusters];
			f_vars=new double[number_of_clusters];
			f_weights=new double[number_of_clusters];
	
			for (int i=0; i<number_of_clusters; i++)
			{
				f_means[i]=Double.parseDouble(configuration.get("f_means"+i));
				f_vars[i]=Double.parseDouble(configuration.get("f_vars"+i));
				f_weights[i]=Double.parseDouble(configuration.get("f_weights"+i));
			}

		}

		private Text key_text = new Text();
		private DoubleWritable	 val_double = new DoubleWritable();
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException 
		{
			

			double f_value=Double.parseDouble(value.toString());

			double[] p_cj_xi_temp = new double[number_of_clusters];
			double sum_p_cj_xi = 0.0;
			
			for (int j=0; j<number_of_clusters; j++)
			{
				sum_p_cj_xi+=f_weights[j]*cal_Gaussian(f_value, f_means[j], f_vars[j]);
				
			}
			
			double f_a_j;
			double f_b_j;
			double f_c_j;
			
			for (int j=0; j<number_of_clusters; j++)
			{

			//---------------implement here---------------//

				context.write(key_text, val_double);
				
			//---------------implement here---------------//

				context.write(key_text, val_double);
				
			//---------------implement here---------------//

				context.write(key_text, val_double);

			}

		}
	}

	public static class Reducer_2nd extends Reducer<Text,DoubleWritable,Text, DoubleWritable> 
	{

		private DoubleWritable result = new DoubleWritable();
		public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException 
		{
			
			double f_temp=0.0;
			

			for ( DoubleWritable val : values ) 
			{
				f_temp+=val.get();


				
			}
			result.set(f_temp);
			context.write(key,result);

		}
	}




	public static void main(String[] args) throws Exception 
	{


		Configuration confTemp = new Configuration();
		String[] otherArgsTemp = new GenericOptionsParser(confTemp,args).getRemainingArgs();
	
		if ( otherArgsTemp.length != 4 ) 
		{
			System.err.println("Usage: EMclustering <number_of_clusters> <number_of_iterations> <in> <out>");
			System.exit(2);
		}
		
		FileSystem.get(confTemp).delete(new Path(otherArgsTemp[3]));
		number_of_clusters=Integer.parseInt(otherArgsTemp[0]);
		int number_of_iterations=Integer.parseInt(otherArgsTemp[1]);
		int m_number_of_inputs=0;
		
		double[] f_means = new double[number_of_clusters];
		double[] f_vars= new double[number_of_clusters];
		double[] f_weights = new double[number_of_clusters];
		double[] f_a_j=new double[number_of_clusters];
		double[] f_b_j=new double[number_of_clusters];
		double[] f_c_j=new double[number_of_clusters];
		
		
		boolean b_first=true;



		Random oRandom= new Random();
		

		for (int mi=0; mi<number_of_iterations; mi++) 
		{

		
			Configuration conf = new Configuration();
			String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
			Job jobforMean = new Job(conf,"EMclustering1DforMean");
				
			FileSystem fs=FileSystem.get(conf);

			if (b_first)
			{
//				FSDataInputStream in = fs.open(new Path(otherArgs[2]));
				FileStatus[] fs_list = fs.listStatus(new Path(otherArgs[2]));
				for (FileStatus file_status : fs_list)
				{
					if (file_status.getPath().toString().indexOf("logs")==-1)
					{
						FSDataInputStream in = fs.open(file_status.getPath());
					

						b_first=false;
						double f_normalizing=0.0;
						double f_min=Double.MAX_VALUE;
						double f_max=-1*Double.MAX_VALUE;

						String str_read=new String();
						while ((str_read=in.readLine())!=null)
						{
							double f_temp = Double.parseDouble(str_read);
							if (f_min>f_temp)
							{
								f_min=f_temp;
							}
							if (f_max<f_temp)
							{
								f_max=f_temp;
							}
							m_number_of_inputs++;
						}
						for (int i=0; i<number_of_clusters; i++)
						{
							f_means[i]=f_min+((double)i*(f_max-f_min))/(double)(number_of_clusters-1);
							f_vars[i]=oRandom.nextFloat();
							f_vars[i]=1.0;
							if (f_vars[i]==0.0)
							{
								f_vars[i]+=0.0000001;
							}
							f_weights[i]=oRandom.nextFloat();
							f_normalizing+=f_weights[i];
						}

						for (int i=0; i<number_of_clusters; i++)
						{
							f_weights[i]=f_weights[i]/f_normalizing;
						}
					}
				}
			}
			else
			{
				FileStatus[] fs_list = fs.listStatus(new Path(otherArgs[3]+"/iteration"+Integer.toString(mi-1)));
				for (FileStatus file_status : fs_list)
				{
					if (file_status.getPath().toString().indexOf("logs")==-1)
					{
						FSDataInputStream in = fs.open(file_status.getPath());
						String str_read=new String();


						int m_cluster_id=in.readInt();
						f_means[m_cluster_id]=in.readDouble();
						f_vars[m_cluster_id]=in.readDouble();
						f_weights[m_cluster_id]=in.readDouble();
					}
				}

				
			}

			jobforMean.getConfiguration().set("number_of_clusters", ""+number_of_clusters);
			for (int i=0; i<number_of_clusters; i++)
			{
				jobforMean.getConfiguration().set("f_means"+i, ""+f_means[i]);
				jobforMean.getConfiguration().set("f_vars"+i, ""+f_vars[i]);
				jobforMean.getConfiguration().set("f_weights"+i, ""+f_weights[i]);
			}


			jobforMean.setJarByClass(EMClustering1D.class);
			jobforMean.setMapperClass(TokenizerMapper.class);
			jobforMean.setReducerClass(Reducer_2nd.class);
			jobforMean.setOutputKeyClass(Text.class);
			jobforMean.setOutputValueClass(DoubleWritable.class);

			jobforMean.setNumReduceTasks(1);
			FileInputFormat.addInputPath(jobforMean,new Path(otherArgs[2]));
			FileOutputFormat.setOutputPath(jobforMean,new Path(otherArgs[3]+"/iteration"+Integer.toString(mi)));
			jobforMean.waitForCompletion(true);


			Job job = new Job(conf,"EMclustering1D");
			
	
			FileStatus[] fs_list = fs.listStatus(new Path(otherArgs[3]+"/iteration"+Integer.toString(mi)));
			for (FileStatus file_status : fs_list)
			{
					
				if (file_status.getPath().toString().indexOf("logs")==-1)
				{
					FSDataInputStream in = fs.open(file_status.getPath());
					String str_read=new String();
					
					while ((str_read=in.readLine())!=null)
					{
						String[] str_cluster;
						str_cluster=str_read.split(" |\t");
						
						int m_cluster_id = Integer.parseInt(str_cluster[0].substring(1, str_cluster[0].length()));
					
						if (str_cluster[0].indexOf('A')==0)
						{
							f_a_j[m_cluster_id]=Double.parseDouble(str_cluster[1]);
							
						}
						else if (str_cluster[0].indexOf('B')==0)
						{
							f_b_j[m_cluster_id]=Double.parseDouble(str_cluster[1]);
							
						}
						else if (str_cluster[0].indexOf('C')==0)
						{
							f_c_j[m_cluster_id]=Double.parseDouble(str_cluster[1]);
							
						}
					}
				}
			}

			for (int i=0; i<number_of_clusters; i++)
			{

			//---------------implement here---------------//

			}
			
			fs.delete(new Path(otherArgs[3]+"/iteration"+Integer.toString(mi)));
			FSDataOutputStream out = fs.create(new Path(otherArgs[3]+"/iteration"+Integer.toString(mi)+"/part-r-00000"));
			for (int i=0; i<number_of_clusters; i++)
			{
				out.writeInt(i);
				out.writeDouble(f_means[i]);
				out.writeDouble(f_vars[i]);
				out.writeDouble(f_weights[i]);
			}
			out.close();

		}
	}
}

