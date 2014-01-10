package com.ericski.api500px;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

public class Api500pxStreamBuilder
{
	private static final Logger log = Logger.getLogger(Api500pxStreamBuilder.class);
	
	private static final String PHOTOS_URL_BASE = "https://api.500px.com/v1/photos?consumer_key=%s";

	private String consumerKey;
	private String privateKey;

	private String baseUrl;
	private String feature;
	private Category onlyCategory;
	private Category excludeCategory;
	private Integer imageSize;
	private Integer page;
	private Integer rpp;
	private Token accessToken;
	private Sort sortOrder;
	private String username;
	private Integer user_id;
	
	// only for testing!!!
	public Api500pxStreamBuilder()
	{
		this("HZG59PbzthimGPLN6bb0OQnxeaaq6ieRcR9rW40n");
	}

	public Api500pxStreamBuilder(String consumerKey)
	{
		this(consumerKey, null);
	}

	public Api500pxStreamBuilder(String consumerKey, String privateKey)
	{
		this.consumerKey = consumerKey;
		this.privateKey = privateKey;
		baseUrl = String.format(PHOTOS_URL_BASE, consumerKey);
	}

	public Api500pxStreamBuilder popularPhotos()
	{
		feature = "popular";
		return this;
	}

	public Api500pxStreamBuilder upcomingPhotos()
	{
		feature = "upcoming";
		return this;
	}

	public Api500pxStreamBuilder editorsPhotos()
	{
		feature = "editors";
		return this;
	}

	public Api500pxStreamBuilder freshTodayPhotos()
	{
		feature = "fresh_today";
		return this;
	}

	public Api500pxStreamBuilder freshYesterdayPhotos()
	{
		feature = "fresh_yesterday";
		return this;
	}

	public Api500pxStreamBuilder freshThisWeekPhotos()
	{
		feature = "fresh_week";
		return this;
	}

	public Api500pxStreamBuilder userPhotos(String username)
	{
		feature = "user";
		this.username = username;
		return this;
	}

	public Api500pxStreamBuilder userPhotos(Integer user_id)
	{
		feature = "user";
		this.user_id = user_id;
		return this;
	}	

	public Api500pxStreamBuilder userFriendsPhotos(String username)
	{
		feature = "user_friends";
		this.username = username;
		return this;
	}

	public Api500pxStreamBuilder userFriendsPhotos(Integer user_id)
	{
		feature = "user_friends";
		this.user_id = user_id;
		return this;
	}	

	public Api500pxStreamBuilder userFavoritesPhotos(String username)
	{
		feature = "user_favorites";
		this.username = username;
		return this;
	}

	public Api500pxStreamBuilder userFavoritesPhotos(Integer user_id)
	{
		feature = "user_favorites";
		this.user_id = user_id;
		return this;
	}	
	
	public Api500pxStreamBuilder only(Category cat)
	{
		onlyCategory = cat;
		return this;
	}

	public Api500pxStreamBuilder exclude(Category cat)
	{
		excludeCategory = cat;
		return this;
	}	
	
	public Api500pxStreamBuilder smallThumbnails()
	{
		imageSize = ImageSize.SmallThumbnail.getSizeKey();
		return this;
	}

	public Api500pxStreamBuilder mediumThumbnails()
	{
		imageSize = ImageSize.MediumThumbnail.getSizeKey();
		return this;
	}

	public Api500pxStreamBuilder largeThumbnails()
	{
		imageSize = ImageSize.LargeThumbnail.getSizeKey();
		return this;
	}

	public Api500pxStreamBuilder fullImage()
	{
		imageSize = ImageSize.Image.getSizeKey();
		return this;
	}

	public Api500pxStreamBuilder page(int page)
	{
		if (page > 0)
		{
			this.page = page;
		}
		return this;
	}

	public Api500pxStreamBuilder resultsPerPage(int rpp)
	{
		if (rpp > 0 && rpp < 101)
		{
			this.rpp = rpp;
		}
		return this;
	}

	public Api500pxStreamBuilder accessToken(Token accessToken)
	{
		this.accessToken = accessToken;
		return this;
	}

	public Api500pxStreamBuilder sort(Sort sortOrder)
	{
		this.sortOrder = sortOrder;
		return this;
	}	
	
	protected String urlString()
	{
		StringBuilder bldr = new StringBuilder(128);
		bldr.append(baseUrl);
		if (feature != null)
		{
			bldr.append("&feature=");
			bldr.append(feature);
		}
		if ( username != null)
		{
			bldr.append("&username=");
			bldr.append(username);			
		}
		if ( user_id != null)
		{
			bldr.append("&user_id=");
			bldr.append(user_id);			
		}		
		if (onlyCategory != null)
		{
			String catName = null;
			try
			{
				catName = URLEncoder.encode(onlyCategory.getCategoryName(), "utf-8");
				bldr.append("&only=");
				bldr.append(catName);
			}
			catch (UnsupportedEncodingException e)
			{
				// probably should log this
			}
		}
		if ( excludeCategory != null)
		{
			try
			{
				String catName = URLEncoder.encode(excludeCategory.getCategoryName(), "utf-8");
				bldr.append("&exclude=");
				bldr.append(catName);
			}
			catch (UnsupportedEncodingException e)
			{
				log.warn("Error encoding Url",e);
			}			
		}
		if ( sortOrder != null)
		{
			try
			{
				String sortName = URLEncoder.encode(sortOrder.name(), "utf-8");
				bldr.append("&sort=");
				bldr.append(sortName);
			}
			catch (UnsupportedEncodingException e)
			{
				log.warn("Error encoding Url",e);
			}			
		}		
		if (imageSize != null)
		{
			bldr.append("&image_size=");
			bldr.append(imageSize);
		}
		if (page != null)
		{
			bldr.append("&page=");
			bldr.append(page);
		}
		if (rpp != null)
		{
			bldr.append("&rpp=");
			bldr.append(rpp);
		}
		log.debug(bldr.toString());
//		System.out.println(bldr.toString());
		return bldr.toString();
	}

	public FeatureResponse getResponse()
	{
		FeatureResponse pr = null;

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
			log.debug(body);
			Gson gson = GsonFactory.getGson();
			
			pr = gson.fromJson(body, FeatureResponse.class);
			pr.setBuilder(this);
		}
		catch (Exception badE)
		{
			// TODO: log this, turn it into another exception type
			log.warn("Exception getting response",badE);
		}
		return pr;
	}
}
