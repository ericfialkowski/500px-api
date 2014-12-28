package com.ericski.api500px;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Encapsulates many ways to get a consumer key for tests
 */
public class ConsumerKeyFinder
{

    private ConsumerKeyFinder()
    {
    }

    public static String getKey()
    {
        String key = System.getProperty("500PX_CONSUMER_KEY","");
        if (key.isEmpty())
        {
            try
            {
                key = findFile();
            }
            catch (IOException ex)
            {
                // log or something?
            }
        }
        return key;
    }

    private static String findFile() throws IOException
    {
        String rtn = "";
        File f = new File(".consumerkey");
        System.out.println(f.getCanonicalPath());
        if (f.exists())
        {
            byte[] bytes = Files.readAllBytes(f.toPath());
            rtn = new String(bytes);
        }

        return rtn.trim();
    }
}
