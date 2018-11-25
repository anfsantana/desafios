package br.com.crawlers.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CrawlerControllerTest {

    CrawlerController crawlerController = null;
    File input = null;
    Document doc = null;

    @Before
    public void setUp() throws Exception {
        crawlerController = new CrawlerController();
        ClassLoader classLoader = getClass().getClassLoader();
        String url = URLDecoder.decode(classLoader.getResource("Cats.html").getFile(), "UTF-8");
        input = new File(url);
        doc = Jsoup.parse(input, "UTF-8", "");
    }

    @Test
    public void extractParams() {
        String params = "askreddit;worldnews;cats 2";
        LinkedList<String> array = crawlerController.extractParams(params);

        assertEquals("askreddit", array.get(0));
        assertEquals("worldnews", array.get(1));
        assertEquals("cats", array.get(2));
        assertEquals("2", array.get(3));

    }

    @Test
    public void extractParamsWithSpace_subredditsLimit() {
        String params = "    askreddit     ;worldnews      ;    cats         2";
        LinkedList<String> array = crawlerController.extractParams(params);

        assertEquals("askreddit", array.get(0));
        assertEquals("worldnews", array.get(1));
        assertEquals("cats", array.get(2));
        assertEquals("2", array.get(3));

    }


    @Test
    public void extractParamsWithSpaceBetweenWords_subredditsLimit() {
        String params = "    askreddit     ;worldnews      ;    cats cute 3        2";
        LinkedList<String> array = crawlerController.extractParams(params);

        assertEquals("askreddit", array.get(0));
        assertEquals("worldnews", array.get(1));
        assertEquals("cats cute 3", array.get(2));
        assertEquals("2", array.get(3));

    }


    @Test
    public void extractParamsWithSpace() {
        String params = "    askreddit     ;worldnews      ;    cats         ";
        LinkedList<String> array = crawlerController.extractParams(params);

        assertEquals("askreddit", array.get(0));
        assertEquals("worldnews", array.get(1));
        assertEquals("cats", array.get(2));

    }


    @Test
    public void extractParamsWithSpaceBetweenWords() {
        String params = "    askreddit     ;worldnews      ;    cats cute  ";
        LinkedList<String> array = crawlerController.extractParams(params);

        assertEquals("askreddit", array.get(0));
        assertEquals("worldnews", array.get(1));
        assertEquals("cats cute", array.get(2));

    }


    @Test
    public void write() throws IOException {
        String params = "askreddit;worldnews;cats 2";
        Long chatId = 123456L;
        byte[] file = crawlerController.write("https://old.reddit.com", doc, params, chatId);
        assertTrue(file.length > 0);

    }
}