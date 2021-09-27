package ssafy;

import java.io.*;
import java.util.*;
import java.lang.Math;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.*;

public class SetSimJoin {

	/*
	 * Map class part
	 */

	public static class InvertedListMapper extends Mapper<Object, Text, Text, Text> {

		private Text rid = new Text();
		private Text item = new Text ();

		// Text : input line
		// --> format = <p \t item item item ...>
		public void map (Object key, Text value, Context context) throws IOException, InterruptedException{
			StringTokenizer itr = new StringTokenizer(value.toString());
			String tmprid=itr.nextToken() ;
                        rid.set(tmprid + "," + itr.countTokens());
                        while(itr.hasMoreTokens()){
                        	item.set(itr.nextToken());          
                        	context.write(item,rid);  
                        	//System.out.println("item "+item.toString());
                        	//System.out.println("rid "+rid.toString());
                        }
		}
	}

	/*
	 * Reduce class part
	 */

	public static class InvertedListReducer extends Reducer<Text, Text, Text, IntWritable> {

		private static IntWritable one = new IntWritable(1);

		// input: < item, [p:p.size, ..., q:q.size] >
		public void reduce(Text key, Iterable<Text> values, Context context) 
			throws IOException, InterruptedException
		{
			// read the value list
			Vector<String> str = new Vector<String>();
			for ( Text val : values ) {
				str.add( val.toString() );
			}
			Text ridpair =null;
			for(int i=0;i<str.size();i++){
				for(int j=0;j<str.size();j++){
					//set (pair,overlab) to gloal hash table
					ridpair = new Text(str.get(i)+"\t"+str.get(j));
					context.write(ridpair,one);
					//System.out.println("ridpair "+ridpair.toString());
				}			
			}
		}
	}

	public static class SimMapper extends Mapper<Object, Text, Text, IntWritable> {

		private Text pair = new Text();
		private IntWritable count = new IntWritable();
		
		public void map( Object key, Text value, Context context ) throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(value.toString());
                        String str="";
                        String tmp="";
                        int idx=0;
                        //last value(1) except
                        while(true){
                        	if(idx!=0)
                        		str+="\t";
				tmp = itr.nextToken();
                        	if(!itr.hasMoreTokens()){
                        		count.set(Integer.parseInt(tmp));
                        		break;       
                        	}
                        	str+=tmp;  
                        	idx++;          	
                        }
                        pair.set(str);
                        //count.set(1);
                        //System.out.println("SimMapper pair "+pair.toString());
                        //System.out.println("SimMapper count "+count.get());
                        
                        context.write(pair, count);                        
		}
	}

	public static class SimReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

		private static double sigma;

		protected void setup( Context context ) throws IOException, InterruptedException {

			Configuration config = context.getConfiguration();
			sigma = config.getFloat( "threshold", -1 );
		}
		
		public void reduce( Text key, Iterable<IntWritable> values, Context context ) throws IOException, InterruptedException {
			String[] strarr= key.toString().split("\t");
			String[] aarr=strarr[0].split(",");
			String a=aarr[0];
			int alength=Integer.parseInt(aarr[1]);
			//System.out.println("SimReducer a "+a);
			//System.out.println("SimReducer alength "+alength);
			
			String[] barr=strarr[1].split(",");
			String b=barr[0];
			int blength=Integer.parseInt(barr[1]);
			//System.out.println("SimReducer b "+b);
			//System.out.println("SimReducer blength "+blength);
			
		
			int count=0;
			Vector<String> str = new Vector<String>();
			for ( IntWritable val : values ) {
				count+= val.get();
			}
                       
                       double similarlity = count / (alength + blength);

			if(similarlity < sigma)
				return;
			int percenttmp = (int)(similarlity * 1000) % 10;
			int retval = (int)(similarlity * 100);
			if(percenttmp>=5)
				retval+=1;				
		
			Text sim = new Text(a+"\t"+b);
			//System.out.println("SimReducer sim "+sim);
			
			context.write(sim,new IntWritable(retval));
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		Configuration conf = new Configuration ();
		FileSystem fs = FileSystem.get( conf );
    	String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length != 3) {
			System.out.println ("usage: <threshold> <in> <out>");
			System.exit(2);
		}
		conf.setFloat ("threshold", (float)Double.parseDouble(otherArgs[0]));

                FileSystem hdfs = FileSystem.get(conf);
                Path output = new Path(otherArgs[2]);
                if (hdfs.exists(output))
                        hdfs.delete(output, true);

		// run phase1 job
		Job job1 = new Job (conf, "buildInvertedList");
		job1.setJarByClass(SetSimJoin.class);
		job1.setMapperClass(InvertedListMapper.class);
		job1.setReducerClass(InvertedListReducer.class);
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);
		job1.setNumReduceTasks(40);
		FileInputFormat.addInputPath(job1, new Path(otherArgs[1]));
		Path pathtmp = new Path( "setSimJoinTmp" );
		FileOutputFormat.setOutputPath(job1, pathtmp );
		if ( fs.exists( pathtmp ) ) fs.delete( pathtmp );
		if ( !job1.waitForCompletion( true ) ) System.exit(1);

		// Run phase2 job
 		Job job2 = new Job (conf, "SimJoinList");
		job2.setJarByClass(SetSimJoin.class);
		job2.setMapperClass(SimMapper.class);
		job2.setReducerClass(SimReducer.class);
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(IntWritable.class);
		job2.setNumReduceTasks(40);
		FileInputFormat.addInputPath(job2, pathtmp);
		FileOutputFormat.setOutputPath(job2, output );
		if ( fs.exists( output ) ) fs.delete( output );
		if ( !job2.waitForCompletion( true ) ) System.exit(1);



	}
}

