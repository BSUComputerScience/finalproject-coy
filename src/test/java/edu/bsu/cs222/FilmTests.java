package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FilmTests {
    Film newHope = new Film("A New Hope",
            4,
            "It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy....",
            "1977-05-25");

    @Test
    public void testGetTitle() {
        String title = newHope.getTitle();
        Assertions.assertEquals("A New Hope", title);
    }

    @Test
    public void testEpisode_id() {
        int id = newHope.getEpisode_id();
        Assertions.assertEquals(4, id);
    }

    @Test
    public void testGetOpening_crawl() {
        String crawl = newHope.getOpening_crawl();
        Assertions.assertEquals("It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy....", crawl);
    }

    @Test
    public void testGetRelease_date() {
        String date = newHope.getRelease_date();
        Assertions.assertEquals("1977-05-25", date);
    }
}
