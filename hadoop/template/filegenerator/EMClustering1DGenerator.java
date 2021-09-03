import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class EMClustering1DGenerator {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if(args.length != 3) {
			System.out.println("[Usage] number_of_clusters number_of_data random_seed");
			System.exit(0);
		}
		int number_of_clusters = Integer.parseInt(args[0]);
		int number_of_data = Integer.parseInt(args[1]);
		long seed = Long.parseLong(args[2]);
		
		String filename = "EM_input";
		String afilename = "EM_answer";
		Random rand = new Random(seed);

		double[] prob = new double[number_of_clusters];
		double[] hist = new double[number_of_clusters];
		double[] mean = new double[number_of_clusters];
		double[] var = new double[number_of_clusters];
		double[] std = new double[number_of_clusters];

		double psum = 0;
		for(int i = 0 ; i < number_of_clusters ; ++i) {
			prob[i] = rand.nextDouble() + 0.3;
			psum += prob[i];
			mean[i] = rand.nextDouble() * 20 - 10;
			var[i] = 1 + rand.nextDouble() * 2;
			std[i] = Math.sqrt(var[i]);
		}
		for(int i = 0 ; i < number_of_clusters ; ++i) {
			prob[i] /= psum;
			if(i == 0)
				hist[i] = prob[i];
			else
				hist[i] = hist[i - 1] + prob[i];
		}
		hist[number_of_clusters - 1] = 1;

		BufferedWriter bw = new BufferedWriter(new FileWriter(afilename));
		for(int i = 0 ; i < number_of_clusters ; ++i)
			bw.write(mean[i] + " " + var[i] + " " + prob[i] + "\n");
		bw.close();

		bw = new BufferedWriter(new FileWriter(filename));
		for(int i = 0 ; i < number_of_data ; ++i) {
			double rv = rand.nextDouble();
			int cid = 0;
			while(hist[cid] < rv)
				++cid;
			rv = rand.nextGaussian() * std[cid] + mean[cid];
			bw.write(rv + "\n");
		}
		bw.close();
	}
}
