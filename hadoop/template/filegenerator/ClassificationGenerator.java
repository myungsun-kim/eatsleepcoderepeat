import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;



public class ClassificationGenerator {

	public ClassificationGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main( String[] argv ) {
		argv = "0 bayes_input.data 1000".split("\\s+");
		if ( argv.length != 3 ) {
			usage();
			System.exit(1);
		}
		
		long seed = Long.parseLong( argv[0] );
		int n = Math.max( 100, Integer.parseInt( argv[2] ) );
		n = Math.min( n, 10000);
		Random rand = new Random(seed);
		
		String[] word = "always, amazing, beats, beautiful, bill, buying, casino, click, create, credit, different, dvd, element, for_free, free, income, letter, lhome, lowest, luxury, mass, merge, mlm, no_cost, nothing, offic, profits, remove, same, send, spam, stock, stupid, use, winner, winning".split(", ");
		String[] label = { "normal", "spam" };
		double[] wordDist = {0.02800982800982801, 0.057002457002457, 0.09533169533169533, 0.13513513513513514, 0.16117936117936119, 0.17936117936117937, 0.1955773955773956, 0.22162162162162163, 0.24963144963144962, 0.27174447174447175, 0.3036855036855037, 0.3253071253071253, 0.3601965601965602, 0.3769041769041769, 0.3936117936117936, 0.41277641277641275, 0.4452088452088452, 0.4805896805896806, 0.5017199017199018, 0.5267813267813268, 0.5616707616707617, 0.6014742014742015, 0.6162162162162163, 0.6407862407862408, 0.6791154791154791, 0.7076167076167076, 0.7375921375921376, 0.7597051597051597, 0.8014742014742015, 0.8417690417690418, 0.8619164619164619, 0.888943488943489, 0.9213759213759214, 0.9592137592137592, 0.9769041769041769, 1.0 };
		double[] labelDist = { 0.9824561403508771, 0.06779661016949153, 0.9487179487179487, 0.9506172839506173, 0.03773584905660377, 0.05405405405405406, 0.06060606060606061, 0.03773584905660377, 0.9649122807017544, 0.1111111111111111, 0.9384615384615385, 0.11363636363636363, 0.9859154929577465, 0.14705882352941177, 0.14705882352941177, 0.07692307692307693, 0.9696969696969697, 0.9166666666666666, 0.11627906976744186, 0.0784313725490196, 0.9295774647887324, 0.9629629629629629, 0.13333333333333333, 0.08, 0.9487179487179487, 0.9137931034482759, 0.06557377049180328, 0.08888888888888889, 0.9294117647058824, 0.9878048780487805, 0.07317073170731707, 0.07272727272727272, 0.8787878787878788, 0.974025974025974, 0.1111111111111111, 0.0851063829787234 };

		try {
			BufferedWriter bw = new BufferedWriter( new FileWriter( argv[1] ) );
			int wIdx;
			double p;
			for ( int i=0; i<n; i++ ) {
				p = rand.nextDouble();
				wIdx = Arrays.binarySearch( wordDist, p );
				wIdx = wIdx<0 ? -wIdx-1 : wIdx;
				p = rand.nextDouble();
				bw.write( word[wIdx]+":"+( p<labelDist[wIdx]?label[0]:label[1] )+"\n" );
			}
			
			bw.flush(); bw.close();
		} catch ( IOException e ) { e.printStackTrace(); }
		System.out.println( "Data "+argv[1]+" is successfully generated." );
	}
	
	private static void usage() {
		System.err.println( "usage: seed output_name number_of_data(100~10000)" );
	}
}

