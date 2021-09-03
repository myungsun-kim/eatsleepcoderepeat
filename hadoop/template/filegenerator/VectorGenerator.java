import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class VectorGenerator {
	int nR 		= 10;		//Number of tuples;
	int nDim	= 3;		//Number of dimension;
	int seed	= 0;		//seed

	
	public static void main(String[] args) throws IOException{		
		//Parameter Settings;
		if(args.length!=3){
			System.out.printf("Usage <nR> <nDim> <seed>\n");
			System.out.println("nR: Number of tuples");
			System.out.println("nDim: Number of dimensions");
			System.out.println("seed: seed of the random number generator");
			
			System.exit(0);
		}
		int nR = Integer.parseInt(args[0]);
		int nDim = Integer.parseInt(args[1]);
		int seed = Integer.parseInt(args[2]);

		
		VectorGenerator dg = new VectorGenerator(nR, nDim, seed);
		dg.generate();
		
		System.out.println("Completed");
		
	}
	public VectorGenerator(int nR, int nDim, int seed){
		this.nR = nR;
		this.nDim  = nDim;
		this.seed = seed;
	}
	public void generate() throws IOException{
		Random r = new Random(seed);
		
		ArrayList<VectorR> vectors = new ArrayList<VectorR>();
		for(int id = 1; id<=nR; id++){
			VectorR v = new VectorR(id, r, nDim);
			vectors.add(v);
		}
		
		
		writeArrayList("inputVecSimJoin.data", vectors);
	}
	
	public static <T> void writeArrayList(String filename, ArrayList<T> list) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		
		for(int i = 0; i <list.size(); i++){
			bw.write(list.get(i).toString());
			bw.newLine();
		}
		
		bw.close();
		System.out.println("Filename: "+filename);
	}
	
	
	
}


class VectorR{
	int id;
	double[] vals;
	public VectorR(int id, Random r, int nDim){
		this.id = id;
		vals = new double[nDim];
		for(int c = 0; c<nDim; c++){
			vals[c] = r.nextDouble();
		}
	}
	
	public String toString(){
		String out = "";
		out += id+"\t";
		for(int c = 0; c<vals.length; c++){
			out+=vals[c];
			if(c!=vals.length-1){
				out+="\t";
			}
		}
		
		return out;
	}
}
