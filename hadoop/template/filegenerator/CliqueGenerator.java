import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class CliqueGenerator {
	// private static final int min = 0;
	private static final int	bins		= 10;
	private static final int	interval	= 10;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if(args.length != 3) {
			System.out.println("[Usage] dimension number_of_data random_seed");
			System.exit(0);
		}
		int dim = Integer.parseInt(args[0]);
		int number_of_data = Integer.parseInt(args[1]);
		long seed = Long.parselong(args[2]);
		Random rand = new Random(seed);
		String filename = "clique_input";
		String afilename = "clique_answer";

		int n_clusters = 3 + rand.nextInt(8);
		int ppc = 1 + number_of_data / 2 / n_clusters;
		int n_random_points = number_of_data - n_clusters * ppc;

		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		// Generate cluster points
		HashSet<List<Integer>> cidxs = new HashSet<List<Integer>>();
		while(cidxs.size() < n_clusters) {
			List<Integer> cidx = new ArrayList<Integer>();
			for(int d = 0 ; d < dim ; ++d)
				cidx.add(rand.nextInt(bins));
			if(cidxs.contains(cidx)) continue;
			cidxs.add(cidx);
			for(int n = 0 ; n < ppc ; ++n) {
				for(int d = 0 ; d < dim ; ++d) {
					double rv = (((double) cidx.get(d)) + rand.nextDouble()) * interval;
					bw.write(rv + "\t");
				}
				bw.write("\n");
			}
		}
		for(int n = 0 ; n < n_random_points ; ++n) {
			for(int d = 0 ; d < dim ; ++d) {
				double rv = rand.nextDouble() * interval * bins;
				bw.write(rv + "\t");
			}
			bw.write("\n");
		}
		bw.close();

		bw = new BufferedWriter(new FileWriter(afilename));
		for(List<Integer> cidx : cidxs) {
			for(int id : cidx)
				bw.write(id + "\t");
			bw.write("\n");
		}
		bw.close();
	}

}
