package com.ericski.api500px;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Px500Api;
import org.scribe.exceptions.OAuthConnectionException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class Api500pxPhotoUrlBuilder
{
    private static final Logger log = LogManager.getLogger(Api500pxPhotoUrlBuilder.class);
    
    private static final String PHOTO_RESOURCE_URL = "https://api.500px.com/v1/photos/%d?consumer_key=%s";

    private String consumerKey;
    private String privateKey;
    private int imageId;
    private Token accessToken;

    private Integer imageSize;
    private Integer commentsPage;
    private boolean includeComments;

    public Api500pxPhotoUrlBuilder()
    {

    }

    public Api500pxPhotoUrlBuilder(int imageId, String consumerKey)
    {
        this.imageId = imageId;
        this.consumerKey = consumerKey;
    }

    public Api500pxPhotoUrlBuilder withConsumerKey(String consumerKey)
    {
        this.consumerKey = consumerKey;
        return this;
    }

    public Api500pxPhotoUrlBuilder forImageId(int imageId)
    {
        this.imageId = imageId;
        return this;
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
        if (page > 0)
        {
            this.includeComments = true;
            this.commentsPage = page;
        }
        return this;
    }

    private String urlString()
    {
        StringBuilder bldr = new StringBuilder(128);
        String baseUrl = String.format(PHOTO_RESOURCE_URL, imageId, consumerKey);
        bldr.append(baseUrl);
        if (imageSize != null)
        {
            bldr.append("&image_size=");
            bldr.append(imageSize);
        }
        if (includeComments)
        {
            bldr.append("&comments=1");

            if (commentsPage != null)
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
                service = new ServiceBuilder().provider(Px500Api.class).apiKey(consumerKey).apiSecret(privateKey).build();
            }
            OAuthRequest request = new OAuthRequest(Verb.GET, urlString());
            if (service != null)
            {
                service.signRequest(accessToken, request);
            }

            Response response = null;
            int tries = 0;
            while (response == null && tries < 3)
            {
                try
                {
                    tries++;
                    response = request.send();
                }
                catch (OAuthConnectionException oce)
                {
                    if (tries < 3)
                    {
                        // some sort of error happened
                        log.info("Exception getting response on try " + tries,oce);
                        Thread.sleep(tries * 500);
                    }
                }
            }
            if (response != null)
            {
                String body = response.getBody();
                log.trace(body);
                Gson gson = GsonFactory.getGson();

                //
                // TODO better way to check for html return
                //
                //if (body.contains("<html") || body.contains("<!DOCTYPE"))
                if (body.contains("<!DOCTYPE"))
                {
                    log.warn("Response body: " +System.lineSeparator() + body);                   
                }
                else
                {
                    pr = gson.fromJson(body, PhotoResponse.class);
                }
            }
        }
        catch (Exception badE)
        {
            // TODO: log this, turn it into another exception type
            log.warn("Exception getting response",badE);
        }

        return pr;
    }
}
