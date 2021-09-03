

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MatrixGenerator {

	public MatrixGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main( String[] argv ) {
//		argv = "0 data/matrix_input.data 5 3 4".split("\\s+");
		if ( argv.length != 5 ) {
			usage();
			System.exit(1);
		}

		long seed = Long.parseLong( argv[0] );
		int n = Integer.parseInt( argv[2] );
		int k = Integer.parseInt( argv[3] );
		int m = Integer.parseInt( argv[4] );
		Random rand = new Random(seed);
		
		try {
			BufferedWriter bw = new BufferedWriter( new FileWriter( argv[1] ) );
			for ( int i=0; i<n; i++ ) {
				for ( int j=0; j<k; j++ ) {
					bw.write("A\t"+i+"\t"+j+"\t"+(int)((rand.nextDouble()-0.5)*20)+"\n" );
				}
			}
			for ( int i=0; i<k; i++ ) {
				for ( int j=0; j<m; j++ ) {
					bw.write("B\t"+i+"\t"+j+"\t"+(int)((rand.nextDouble()-0.5)*20)+"\n" );
				}
			}
			bw.flush(); bw.close();
		}
		catch ( IOException e ) { e.printStackTrace(); }
		System.out.println( "Data "+argv[1]+" is successfully generated." );
	}
	
	private static void usage() {
		System.err.println( "usage: seed output_path n k m" );
		System.err.println( "matrix A is n by k, and matrix B is k by m" );
	}

}
