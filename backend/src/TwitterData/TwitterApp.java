package TwitterData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;




public class TwitterApp {
	
	

	
	public static void main(String[] args) throws TwitterException{
		JSONObject obj = new JSONObject();
		
		ConfigurationBuilder cf = new ConfigurationBuilder();
		cf.setDebugEnabled(true)
				.setOAuthConsumerKey("1F50rEDR4rsevy0yDMyzVNiH1")
				.setOAuthConsumerSecret("D49y2cgy0cqegJGY094EnApMk0KAe2Be6k834E0uZ4Nx5WZS9q")
				.setOAuthAccessToken("4532155512-DUnATy53M3vI5XhdaypbXmFfBbRlnaVzyW7f4WE")
				.setOAuthAccessTokenSecret("iHjaEASicgMnDMkDDgJ32jwbkYlrrsQ1RrHsw5JI5xkNN");
		TwitterFactory tf = new TwitterFactory(cf.build());
		Twitter twitter = tf.getInstance();
		String [] tag = {"michaeljordan", "MLB", "NFL", "NBA",
						 "donaldtrumph", "hillary", "kimjongun", "duterte",
						 "ps4", "iphone7", "amazon", "fitbit",
						 "indexexchange", "starbucks", "apple", "Volkswagen"};
		BufferedWriter bw = null;
		String text;
		Set<String> alreadyPresent = new HashSet<String>();

		try {
			for (int i = 0; i<tag.length; i++) {
				bw = new BufferedWriter(new FileWriter("/Users/Seohee/Documents/HackwithIX/AdStocker/backend/google-api-python-client-1.5.5/"+tag[i]+".txt"));
				Query query = new Query("#"+tag[i]);
				query.count(2000);
				QueryResult result = twitter.search(query);
				for (Status st: result.getTweets()) {
					text = st.getText();
					 if(!alreadyPresent.contains(text)) {
						 bw.write(text);
						 bw.newLine();
					 }
					 else continue;
					 alreadyPresent.add(text);
				}
				
				bw.close();
				
			}
		} catch (TwitterException te) {
			System.out.println("Failed to search tweets: "+te.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
