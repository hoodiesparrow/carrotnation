package reducers;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.StringTokenizer;

public class SetSimJoin {
    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

            // value.toString() : get a line + split criterion = ,
            StringTokenizer token = new StringTokenizer(value.toString(), "|");
            token.nextToken(); // date
            token.nextToken(); // id
            token.nextToken(); // link
            token.nextToken(); // link
            token.nextToken(); // location
            token.nextToken(); // market

            IntWritable price = new IntWritable(Integer.parseInt(token.nextToken())); // price
            Text pid = new Text(token.nextToken());
            context.write(pid, price);
        }
    }
}
