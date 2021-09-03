import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class MinhashGenerator {
	public static void main( String args[] ) throws Exception {
		if( args.length != 4 ) {
			System.out.println( "[Usage] number_of_tuple domain max_tuple_size seed" );
			System.exit( 0 );
		}
		int nPoint = Integer.parseInt( args[ 0 ] );
		int domain = Integer.parseInt( args[ 1 ] );
		int maxSize = Integer.parseInt( args[ 2 ] );
		int seed = Integer.parseInt( args[ 3 ] );
		BufferedWriter bw = new BufferedWriter( new FileWriter( "minhash_" + nPoint + "_" + domain + "_" + maxSize + "_" + seed + ".txt" ) );

		if( maxSize > domain - 1 ) {
			maxSize = domain - 1;
		}
		
		Random rn = new Random( seed );

		for( int i = 0; i < nPoint; i++ ) {
			int size = rn.nextInt( maxSize ) + 1;
			
			int[] itemArray = new int[ size ];

			for( int d = 0; d < size; d++ ) {
				itemArray[ d ] = d;
			}
			for( int d = size; d < domain; d++ ) {
				int j = rn.nextInt( d );
				if( j <= size - 1 ) {
					itemArray[ j ] = d;
				}
			}
			bw.write( Integer.toString( i ) );
			for( int j = 0; j < size; j++ ) {
				bw.write( "\t" );
				bw.write( Integer.toString( itemArray[ j ] ) );
			}
			
			bw.write( "\n" );
		}

		bw.close();
	}
}
