
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class WeblogAnalyzer {

	public static class WeblogMapper extends Mapper<Object, Text, Text, IntWritable>{
		
		public static String APACHE_ACCESS_LOGS_PATTERN = "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\d+) (.+?) \"([^\"]+|(.+?))\"";

		public static Pattern pattern = Pattern.compile(APACHE_ACCESS_LOGS_PATTERN);

			private static final IntWritable one = new IntWritable(1);
			private Text url = new Text();

			public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
				Matcher matcher = pattern.matcher(value.toString());
				if (matcher.matches()) {
				// 1 -IP; 2- -; 3- -; 4- Time; 5- Req Type; 6 -URL; 9- Resp Code; 10- Site; 11- Browser Type;
				
				url.set(matcher.group(1));
				System.out.println(url.toString());
				context.write(this.url, one);
			}

		}
	}

	public static class WeblogReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable>values, Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			this.result.set(sum);
			context.write(key, this.result);
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		if (args.length != 2) {
			System.err.println("Usage: WeblogAnalyzer <in> <out>");
			System.exit(2);
		}
		Job job = Job.getInstance(conf, "Weblog Analyzer");
		job.setJarByClass(WeblogAnalyzer.class);
		job.setMapperClass(WeblogMapper.class);
		job.setCombinerClass(WeblogReducer.class);
		job.setReducerClass(WeblogReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
