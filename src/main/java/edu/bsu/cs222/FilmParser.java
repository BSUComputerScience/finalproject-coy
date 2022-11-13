package edu.bsu.cs222;
// As a Star Wars fan,
// so that I can learn more about the films,
// I want to see information such as the date released and the opening crawl text.

import java.io.IOException;
import java.io.InputStream;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class FilmParser {

    Film parseFilm(InputStream dataStream) throws IOException {
        // read ONCE from stream, store in JSONArray, then read what we need out of it
        try {
            String jsonResultAll = new String(dataStream.readAllBytes());
            JSONArray title = JsonPath.read(jsonResultAll, "$..title");
            JSONArray episode_id = JsonPath.read(jsonResultAll, "$..episode_id");
            JSONArray opening_crawl = JsonPath.read(jsonResultAll, "$..opening_crawl");
            JSONArray release_date = JsonPath.read(jsonResultAll, "$..release_date");
            return new Film(title.get(0).toString(), Integer.parseInt(episode_id.get(0).toString()), opening_crawl.get(0).toString(), release_date.get(0).toString());
        } catch (NullPointerException e) {
            return new Film("No Response From Server", -1, "", "");
        }
    }

}
