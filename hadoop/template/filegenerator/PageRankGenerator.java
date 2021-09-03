import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class PageRankGenerator {

	public static void main( String args[] ) throws Exception {
		if( args.length != 3 ) {
			System.out.println( "[Usage] number_of_node maximum_degree random_seed" );
			System.exit( 0 );
		}

		int nNode = Integer.parseInt( args[ 0 ] );
		int maxDegree = Integer.parseInt( args[ 1 ] );
		int seed = Integer.parseInt( args[ 2 ] );

		if( maxDegree > nNode - 1 ) {
			maxDegree = nNode - 1;
		}

		Random rn = new Random( seed );

		BufferedWriter bw = new BufferedWriter( new FileWriter( "pagerank_" + nNode + "_" + maxDegree + "_" + seed + ".txt" ) );
		for( int i = 0; i < nNode; i++ ) {
			bw.write( Integer.toString( i ) );
			bw.write( "\t" );

			int degree = rn.nextInt( maxDegree - 1 ) + 1; // has at least one edge

			int[] nodeArray = new int[ degree ];

			for( int d = 0; d < degree; d++ ) {
				nodeArray[ d ] = d;
			}
			for( int d = degree; d < nNode - 1; d++ ) {
				int j = rn.nextInt( d );
				if( j <= degree - 1 ) {
					nodeArray[ j ] = d;
				}
			}

			for( int j = 0; j < degree; j++ ) {
				int node = nodeArray[ j ];
				if( node >= i ) {
					node++;
				}
				bw.write( Integer.toString( node ) );
				bw.write( "\t" );
			}
			bw.write( "\n" );
		}
		bw.close();
	}
}
