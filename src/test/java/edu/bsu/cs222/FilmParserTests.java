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
    Film empireStrikesBack = new Film("The Empire Strikes Back",
            5,
            "It is a dark time for the\r\nRebellion. Although the Death\r\nStar has been destroyed,\r\nImperial troops have driven the\r\nRebel forces from their hidden\r\nbase and pursued them across\r\nthe galaxy.\r\n\r\nEvading the dreaded Imperial\r\nStarfleet, a group of freedom\r\nfighters led by Luke Skywalker\r\nhas established a new secret\r\nbase on the remote ice world\r\nof Hoth.\r\n\r\nThe evil lord Darth Vader,\r\nobsessed with finding young\r\nSkywalker, has dispatched\r\nthousands of remote probes into\r\nthe far reaches of space....",
            "1980-05-17");

    @Test
    public void test_parseFilm() {
        testingFilm(1);
        testingFilm(2);
    }

    public void testingFilm(int i) {
        FilmParser filmParser = new FilmParser();
        Film results = null;
        // BUILD
        Film expectedResults;
        if (i == 1) {
            expectedResults = newHope;
        } else {
            expectedResults = empireStrikesBack;
        }
        // OPERATE
        String filename = String.format("films-%d.json", i);
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
        if (testDataStream != null) {
            try {
                results = filmParser.parseFilm(testDataStream);
            } catch (IOException e) {
                Assertions.fail("Could not access test data file.");
            }
        }
        // CHECK
        if (results != null) {
            Assertions.assertEquals(expectedResults.getTitle(), results.getTitle());
            Assertions.assertEquals(expectedResults.getEpisode_id(), results.getEpisode_id());
            Assertions.assertEquals(expectedResults.getOpening_crawl(), results.getOpening_crawl());
            Assertions.assertEquals(expectedResults.getRelease_date(), results.getRelease_date());
        } else {
            Assertions.fail("Results parsed from test was null.");
        }
    }
}
