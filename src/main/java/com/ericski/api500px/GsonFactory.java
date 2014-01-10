package com.ericski.api500px;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Date;

public class GsonFactory
{

    public static Gson getGson()
    {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Integer.class, new ErrorIgnoringIntegerDeserializer());
        builder.registerTypeAdapter(Date.class, new DateDeserializer());
        return builder.create();
    }
}

//
// parses Photo's Data format
//
class DateDeserializer implements JsonDeserializer<Date>
{

    @Override
    public Date deserialize(JsonElement json, Type typeofT, JsonDeserializationContext context) throws JsonParseException
    {
        Date rtn = new Date();
        String value = json.getAsString();
        if ("0000-01-01T00:00:00-04:56".equals(value))
        {
            // this particular value messes things up.
            // log or something
        }
        else
        {
            try
            {
                rtn = javax.xml.bind.DatatypeConverter.parseDateTime(value).getTime();
            }
            catch (Exception ignored)
            {
                // log or something
            }
        }
        return rtn;
    }
}

//
// Primarily used for Filter class since Category & Excludes can return "false" instead of an Integer
//
class ErrorIgnoringIntegerDeserializer implements JsonDeserializer<Integer>
{

    @Override
    public Integer deserialize(JsonElement json, Type typeofT, JsonDeserializationContext context) throws JsonParseException
    {
        String value = json.getAsString();
        try
        {
            Integer i = new Integer(value);
            return i;
        }
        catch (NumberFormatException iggy)
        {
        }
        return null;
    }
}
