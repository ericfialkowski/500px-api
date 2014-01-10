package com.fialkowski.api500px.tests;

import com.ericski.api500px.Api500pxStreamBuilder;
import com.ericski.api500px.FeatureResponse;
import com.ericski.api500px.Oauth500pxApi;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

public class Oauth500pxExample
{
	//private static final String PROTECTED_RESOURCE_URL = "https://api.500px.com/v1/photos?feature=popular&page=1&consumer_key=%s";
	//private static final String PROTECTED_RESOURCE_URL = "https://api.500px.com/v1/users";
	//private static final String PROTECTED_RESOURCE_URL = "https://api.500px.com/v1/photos/1195521?consumer_key=%s&comments=1";
	private static final String privateKey = "io9rOPebZYAEgW9kxjpxwhlddY9A3tgXijytHjIj";
	private static final String consumerKey = "HZG59PbzthimGPLN6bb0OQnxeaaq6ieRcR9rW40n";

	/*
	http://api.500px.com/v1/photos?feature=popular&page=1&consumer_key=HZG59PbzthimGPLN6bb0OQnxeaaq6ieRcR9rW40n

	 */
	
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		OAuthService service = new ServiceBuilder().provider(Oauth500pxApi.class)
				.apiKey(consumerKey)
				.apiSecret(privateKey)
				.build();
		
		
		//String urlString = new Api500pxStreamBuilder(consumerKey).freshTodayPhotos().mediumThumbnails().resultsPerPage(1).ulrString();
/*		
  
 		// the first section is for giving the app permission 
		Scanner in = new Scanner(System.in);
		
		System.out.println("=== 500px OAuth Workflow ===");
		System.out.println();

		// Obtain the Request Token
		System.out.println("Fetching the Request Token...");
		Token requestToken = service.getRequestToken();
		System.out.println("Got the Request Token!");
		System.out.println();

		System.out.println("Now go and authorize Scribe here:");
		System.out.println(service.getAuthorizationUrl(requestToken));
		System.out.println("And paste the verifier here");
		System.out.print(">>");
		//String verifierString = "cJJ0kqXE4liiDZYGY9FB";
		
		
		Verifier verifier = new Verifier(in.nextLine());
		//Verifier verifier = new Verifier(verifierString);
		System.out.println();

		
		// Trade the Request Token and Verfier for the Access Token
		System.out.println("Trading the Request Token for an Access Token...");
		Token accessToken = service.getAccessToken(requestToken, verifier);
		System.out.println("Got the Access Token!");
		System.out.println("(if your curious it looks like this: " + accessToken + " )");
		System.out.println();
*/
		// After the app has permission, you only need the accessToken 
		// (for certain resources, others don't need anything more than consumerKey) 
		Token accessToken = new Token("Qp8H14ju5Vg5KR4hUBpyoohMXAFA7JSMpVkDJxbS" , "R6lEfi22zHTepLdjOQoX5Xf83eFJUnJdEESqTjps");
		
		// Now let's go and ask for a protected resource!
		//System.out.println("Now we're going to access a protected resource...");
		//System.out.println(urlString);
		//OAuthRequest request = new OAuthRequest(Verb.GET, urlString);
		
		//service.signRequest(accessToken, request);
		
		//Response response = request.send();
		//System.out.println("Got it! Lets see what we found...");
		//System.out.println();
		//String body = response.getBody();
		//System.out.println(body);

		//Gson gson = new GsonBuilder().setPrettyPrinting().create();
		//body = body.substring(9, body.length() -15);
		//FeatureResponse pr= gson.fromJson(body, FeatureResponse.class);
		FeatureResponse pr= new Api500pxStreamBuilder(consumerKey,privateKey).freshYesterdayPhotos().largeThumbnails().resultsPerPage(1).accessToken(accessToken).getResponse();
		if ( pr != null)
		{
			//System.out.printf("Username %s%n",user.getUsername());
			//System.out.println(gson.toJson(pr));
			System.out.println(pr.getTotal_items());
		}
		else
		{
			System.out.println("Go back to the drawing board");
		}
		
		System.out.println();
		System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
	}
}
