package edu.bsu.cs222;

public class OpeningCrawlFormatter {
    public String format(Film aFilm) {
        String openingCrawl = aFilm.getOpening_crawl();
        openingCrawl = openingCrawl.replace("\r", "");
        return openingCrawl;
    }
}
