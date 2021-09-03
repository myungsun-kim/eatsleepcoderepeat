package ssafy;

import org.apache.hadoop.util.ProgramDriver;

public class Driver {
	public static void main(String[] args) {
		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		try {
			pgd.addClass("wordcount",Wordcount.class,"");
			pgd.addClass("wordcount1char",Wordcount1char.class,"Counts first character of words.");
			pgd.addClass("wordcountsort",Wordcountsort.class,"sort by alphabetical order.");

			pgd.addClass("matrixadd", MatrixAdd.class, "add matrix");
			pgd.addClass("kmeans",Kmeans.class,"");
      			pgd.driver(args);
			exitCode = 0;
		}
		catch(Throwable e) {
			e.printStackTrace();
		}

		System.exit(exitCode);
	}
}
