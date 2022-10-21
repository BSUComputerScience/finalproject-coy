package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;

public class SWAPIconnectTests {

    @Test   // Only used in initial development, should not connect to SWAPI when testing regularly
    public void testURLOpen() {
        // test each of the items that we are interested in.
        // TODO:  Could be improved, because each test is hitting the Star Wars API
        testURLOpen_testItem("$..title");
        testURLOpen_testItem("$..episode_id");
        testURLOpen_testItem("$..opening_crawl");
        testURLOpen_testItem("$..release_date");
    }

    private static void testURLOpen_testItem(String desiredJsonPath) {
        // BUILD
        JSONArray jsonResult = null;
        String targetPage = "films/1";
        SWAPIconnect SWAPIconnection = new SWAPIconnect(targetPage);
        jsonResult = readDataFromTestJSONFile(jsonResult, desiredJsonPath);
        // OPERATE
        InputStream APIresponseStream = SWAPIconnection.connect();
        // CHECK
        try {
            JSONArray APIjsonResult = JsonPath.read(APIresponseStream, desiredJsonPath);
            Assertions.assertEquals(jsonResult.toString(), APIjsonResult.toString());
        } catch (IOException e) {
            Assertions.fail("No API response InputStream.");
        }
    }

    private static JSONArray readDataFromTestJSONFile(JSONArray jsonResult, String jsonPath) {
        String filename = "films-1.json";
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
        try {
            jsonResult = JsonPath.read(testDataStream, jsonPath);
        } catch (IOException e) {
            Assertions.fail("Could not read test file.");
        }
        return jsonResult;
    }
}
