package com.ericski.api500px;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

public class Oauth500pxApi extends DefaultApi10a
{

    @Override
    public String getRequestTokenEndpoint()
    {
        return "https://api.500px.com/v1/oauth/request_token";
    }

    @Override
    public String getAccessTokenEndpoint()
    {
        return "https://api.500px.com/v1/oauth/access_token";
    }

    @Override
    public String getAuthorizationUrl(Token requestToken)
    {
        return "https://api.500px.com/v1/oauth/authorize?oauth_token=" + requestToken.getToken();
    }

}
