package reducers;

import org.apache.hadoop.util.ProgramDriver;

public class Driver {
    public static void main(String[] args) {
        int exitCode = -1;
        ProgramDriver pgd = new ProgramDriver();
        try {
            //pgd.addClass("getmax", GetMax.class, "Usage : Get max value of product");
            //pgd.addClass("getmin", GetMin.class, "Usage : Get min value of product");
            //pgd.addClass("setjoin",SetSimJoin.class, "A map/reduce program that perform the set similarity self-join using the inverted index for a single input file.");
            //pgd.addClass("wordcount", Wordcount.class, "A map/reduce program that performs word counting.");
            //pgd.addClass("wordcount1char", WordCount1char.class, "A map/reduce programthat counts the 1st character of words in the input files.");
            //pgd.addClass("wordcountsort", Wordcountsort.class, "A map/reduce program.  lees 'a'");
            //pgd.addClass("invertedindex", InvertedIndex.class, "A map/reduce program. output with filepath.");
            pgd.addClass("sellpricecount",Sellpricecount.class,"count product price");
            pgd.addClass("setjoin",SetSimJoin.class,"A map/reduce program that preform the se similarity self-join using the inverted index for a single input file.");

            pgd.driver(args);
            exitCode = 0;
        }
        catch(Throwable e) {
            e.printStackTrace();
        }

        System.exit(exitCode);
    }
}
