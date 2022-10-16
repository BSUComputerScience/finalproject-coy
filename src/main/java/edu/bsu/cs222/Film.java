package edu.bsu.cs222;
// store information about a Star Wars film
// information returned from API is more than we are intersted in saving
// sample API call: https://swapi.dev/api/films/1/

public class Film {
    String title;
    int episode_id;
    String opening_crawl;
    String release_date;

    public Film(String title, int episode_id, String opening_crawl, String release_date) {
        this.title = title;
        this.episode_id = episode_id;
        this.opening_crawl = opening_crawl;
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public int getEpisode_id() {
        return episode_id;
    }

    public String getOpening_crawl() {
        return opening_crawl;
    }

    public String getRelease_date() {
        return release_date;
    }
}
