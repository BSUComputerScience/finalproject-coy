package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class FilmParserTests {
    Film newHope = new Film("A New Hope",
            4,
            "It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy....",
            "1977-05-25");

    @Test
    public void test_parseFilm_film1() {
        FilmParser filmParser = new FilmParser();
        Film results = null;
        // BUILD
        Film expectedResults = newHope;
        // OPERATE
        String filename = "films-1.json";
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
        if (testDataStream != null) {
            try {
                results = filmParser.parseFilm(testDataStream);
            } catch (IOException e) {
                Assertions.fail("IOException was caught");
            }
        }
        // CHECK
        if (results != null) {
            Assertions.assertEquals(expectedResults.getTitle(), results.getTitle());
            Assertions.assertEquals(expectedResults.getEpisode_id(), results.getEpisode_id());
            Assertions.assertEquals(expectedResults.getOpening_crawl(), results.getOpening_crawl());
            Assertions.assertEquals(expectedResults.getRelease_date(), results.getRelease_date());
        } else {
            Assertions.fail("No InputStream, perhaps file not found");
        }
    }

}