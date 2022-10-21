package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;

public class SWAPIconnectTests {

    @Test   // Only used in initial development, should not connect to SWAPI when testing regularly
    public void testURLOpen_testTitle() {
        // BUILD
        JSONArray jsonResult = null;
        String targetPage = "films/1";
        SWAPIconnect SWAPIconnection = new SWAPIconnect(targetPage);
        String filename = "films-1.json";
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
        try {
            jsonResult = JsonPath.read(testDataStream, "$..title");
        } catch (IOException e) {
            Assertions.fail("Could not read test file.");
        }
        // OPERATE
        InputStream APIresponseStream = SWAPIconnection.connect();
        // CHECK
        try {
            JSONArray APIjsonResult = JsonPath.read(APIresponseStream, "$..title");
            Assertions.assertEquals(jsonResult.toString(), APIjsonResult.toString());
        } catch (IOException e) {
            Assertions.fail("No API response InputStream.");
        }
    }

    @Test   // Only used in initial development, should not connect to SWAPI when testing regularly
    public void testURLOpen_testEpisodeID() {
        // BUILD
        JSONArray jsonResult = null;
        String targetPage = "films/1";
        SWAPIconnect SWAPIconnection = new SWAPIconnect(targetPage);
        String filename = "films-1.json";
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
        try {
            jsonResult = JsonPath.read(testDataStream, "$..episode_id");
        } catch (IOException e) {
            Assertions.fail("Could not read test file.");
        }
        // OPERATE
        InputStream APIresponseStream = SWAPIconnection.connect();
        // CHECK
        try {
            JSONArray APIjsonResult = JsonPath.read(APIresponseStream, "$..episode_id");
            Assertions.assertEquals(jsonResult.toString(), APIjsonResult.toString());
        } catch (IOException e) {
            Assertions.fail("No API response InputStream.");
        }
    }
}
