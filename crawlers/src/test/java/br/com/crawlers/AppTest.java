package br.com.crawlers;

import static org.junit.Assert.assertTrue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    File input = null;
    Document doc = null;

    @Before
    public void beforeTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        //input = new File(classLoader.getResource("Cats.html").getFile());
        //doc = Jsoup.parse(input, "UTF-8", "");
        doc = Jsoup.connect("https://old.reddit.com/r/cats/").get();
    }

    @Test
    public void extractElements() {
        //Obter elementos com base no documento e na consulta
        Elements elementos = Crawler.extractElements(doc, "#siteTable");
        assert elementos.size() > 0;
    }

    @Test
    public void extractNodes() {
        //Obter nodes com base em uma área específica do documento, ou seja, com base no elemento
        Elements elementos = Crawler.extractElements(doc, "#siteTable");
        List<Node> nodes = Crawler.extractNodes(elementos.get(0));
        assert nodes.size() > 0;
    }

}
