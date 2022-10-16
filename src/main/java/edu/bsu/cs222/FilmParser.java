package edu.bsu.cs222;
// As a Star Wars fan,
// so that I can learn more about the films,
// I want to see information such as the date released and the opening crawl text.

import java.io.InputStream;

public class FilmParser {

    Film parseFilm(InputStream dataStream)
    {
        return new Film("oops", 1, "oops", "oops");
    }

}
