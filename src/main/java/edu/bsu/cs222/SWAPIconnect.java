package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class SWAPIconnect {

    String swapiURLstring;

    public SWAPIconnect(String targetPage) {
        // encode requested string and build URL for this connection
        String encodedTargetPage = URLEncoder.encode(targetPage, Charset.defaultCharset());
        swapiURLstring = String.format("https://swapi.dev/api/%s/?format=json", encodedTargetPage);
        swapiURLstring = swapiURLstring.replaceAll(" ", "_");
    }

    public InputStream connect() throws RuntimeException {
        // connect to the url, return the response
        try {
            URL url = new URL(swapiURLstring);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Revision Reporter/0.1 (jennifer.coy@bsu.edu)");
            return urlConnection.getInputStream();
        } catch (IOException malformedURLException) {
            throw new RuntimeException(malformedURLException);
        }
    }
}
