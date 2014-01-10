package com.ericski.api500px;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;

public class Api500pxPhotoUrlBuilder {

	private static final String PHOTO_RESOURCE_URL = "https://api.500px.com/v1/photos/%d?consumer_key=%s";

	private String consumerKey;
	private String privateKey;
	private Token accessToken;


	private String baseUrl;
	private Integer imageSize;
	private Integer commentsPage;
	private boolean includeComments;


	// for testing only!!!!!
	public Api500pxPhotoUrlBuilder(int imageId)
	{
		this(imageId,"HZG59PbzthimGPLN6bb0OQnxeaaq6ieRcR9rW40n");
	}

	public Api500pxPhotoUrlBuilder(int imageId, String consumerKey)
	{
		this.consumerKey = consumerKey;
		baseUrl = String.format(PHOTO_RESOURCE_URL, imageId, consumerKey);
	}


	public Api500pxPhotoUrlBuilder smallThumbnail()
	{
		imageSize = ImageSize.SmallThumbnail.getSizeKey();
		return this;
	}

	public Api500pxPhotoUrlBuilder mediumThumbnail()
	{
		imageSize = ImageSize.MediumThumbnail.getSizeKey();
		return this;
	}

	public Api500pxPhotoUrlBuilder largeThumbnail()
	{
		imageSize = ImageSize.LargeThumbnail.getSizeKey();
		return this;
	}

	public Api500pxPhotoUrlBuilder fullImage()
	{
		imageSize = ImageSize.Image.getSizeKey();
		return this;
	}

	public Api500pxPhotoUrlBuilder commentsPage(int page)
	{
		if ( page > 0)
		{
			this.includeComments = true;
			this.commentsPage = page;
		}
		return this;
	}

	public String urlString()
	{
		StringBuilder bldr = new StringBuilder(128);
		bldr.append(baseUrl);
		if ( imageSize != null)
		{
			bldr.append("&image_size=");
			bldr.append(imageSize);
		}
		if ( includeComments )
		{
			bldr.append("&comments=1");

			if ( commentsPage != null)
			{
				bldr.append("&comments_page=");
				bldr.append(commentsPage);
			}
		}
		//System.out.println(bldr.toString());
		return bldr.toString();
	}

	public PhotoResponse getResponse()
	{
		PhotoResponse pr = null;
		try
		{
			OAuthService service = null;
			if (privateKey != null && accessToken != null)
			{
				service = new ServiceBuilder().provider(Oauth500pxApi.class).apiKey(consumerKey).apiSecret(privateKey).build();
			}
			OAuthRequest request = new OAuthRequest(Verb.GET, urlString());
			if (service != null)
			{
				service.signRequest(accessToken, request);
			}

			Response response = request.send();
			String body = response.getBody();
			//System.out.println(body);
			Gson gson = GsonFactory.getGson();

			if ( body.contains("<html") || body.contains("<!DOCTYPE"))
			{
				System.err.println(body);
			}
			else
			{
				pr = gson.fromJson(body, PhotoResponse.class);
			}
		}
		catch (Exception badE)
		{
			// TODO: log this, turn it into another exception type
			badE.printStackTrace();
		}

		return pr;
	}
}
