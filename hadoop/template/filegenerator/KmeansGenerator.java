import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class KmeansGenerator {
	public static void main( String args[] ) throws Exception {
		if( args.length != 4 ) {
			System.out.println( "[Usage] number_of_cluster number_of_points dimension seed" );
			System.exit( 0 );
		}
		int nCluster = Integer.parseInt( args[ 0 ] );
		int nPoint = Integer.parseInt( args[ 1 ] );
		int dimension = Integer.parseInt( args[ 2 ] );
		int seed = Integer.parseInt( args[ 3 ] );
		BufferedWriter bw = new BufferedWriter( new FileWriter( "kmeans_" + nCluster + "_" + nPoint + "_" + dimension + "_" + seed + ".txt" ) );

		Random rn = new Random( seed );

		double[][] center = new double[ nCluster ][ dimension ];
		for( int i = 0; i < nCluster; i++ ) {
			for( int j = 0; j < dimension; j++ ) {
				center[ i ][ j ] = rn.nextInt( 10 );
			}
		}

		for( int i = 0; i < nPoint; i++ ) {
			for( int d = 0; d < dimension; d++ ) {
				bw.write( Double.toString( center[ ( i % nCluster ) ][ d ] + rn.nextGaussian() ) );
				bw.write( "\t" );
			}
			bw.write( "\n" );
		}

		bw.close();
	}
}
