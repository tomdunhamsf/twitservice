package com.selectquotereview.twitservice;
import java.io.*;
import java.util.*;

import twitter4j.api.*;
import twitter4j.auth.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.*;

public class Unfollower {
	/***
	 * I wanted to Unfollow fewer people than available with TweetAdder so I use this
	 * to read in a list of twitter ids (generated by my own criteria based on ratio and lists)
	 * Like TweetAdder I wait for user input to comply with
	 * the automation rules.
	 * To be a good citizen, I also recommend doing no more than around 15 at a time. 
	 * 
	 */
	
	private static String fileLoc="c:\\ids.txt";
	public static void main(String[] args){
		BufferedReader idFile=null;
		try{
		idFile=new BufferedReader(new FileReader(fileLoc));
		}catch(Exception e){
			e.printStackTrace();
		}
		String consumerKey = "JpDhnrX8qKR60erSwKAN6Q";
		String consumerSecret = "tesyZIHfatgtEy8eo2YouwJXfAgD8unOaP1yojZcTwc";
		String accessToken = "416100289-9aIlh6ZpXE4v0oX6lhX6Pl38A8Kvuus1HoJb9PtC";
		String accessTokenSecret = "NSb92QJRojy7qT9PYGfFrh15RPYUrMHuf6ok7OKDaB7Xe";
		try{
			twitter4j.conf.ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true);
			cb.setOAuthConsumerKey(consumerKey);
			cb.setOAuthConsumerSecret(consumerSecret);
			cb.setOAuthAccessToken(accessToken);
			cb.setOAuthAccessTokenSecret(accessTokenSecret);;
			cb.setDebugEnabled(true);
			
		TwitterFactory twitterFactory = new TwitterFactory(cb.build());
		Twitter twitter = twitterFactory.getInstance();
AccessToken rt=twitter.getOAuthAccessToken();
		
		

		//			      twitterRequestToken.getAuthenticationURL());
		long id=twitter.getId();
		
		long line=Long.parseLong(idFile.readLine());
		Random gen=new Random();
        while(line!=-1){
        	System.out.println(line);
        	Relationship rel=twitter.showFriendship(id, line);
        	if(rel.isTargetFollowedBySource()){
        	User resp=twitter.destroyFriendship(line);
        	
        	System.out.println(resp.getName());
        	}
        	/* To reduce load on Twitter, changed to comply to Twitter unfollow automation rules
        	 * a la TweetAdder
        	synchronized(Unfollower.class){
        	Unfollower.class.wait((long)(14+gen.nextInt(32))*1000l);
        	}*/
        	try {
                System.in.read();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        	line=Long.parseLong(idFile.readLine());
        }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}