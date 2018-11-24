package br.com.crawlers.model;

import br.com.crawlers.entity.ThreadEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class CrawlerModelTest {
    File input = null;
    Document doc = null;
    static final String ID_SITE_TABLE = "#siteTable";

    @Before
    public void beforeTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        String url = URLDecoder.decode(classLoader.getResource("Cats.html").getFile(), "UTF-8");
        input = new File(url);
        doc = Jsoup.parse(input, "UTF-8", "");
        //doc = Jsoup.connect("https://old.reddit.com/r/cats/").get();
    }

    @Test
    public void extractElements() {
        //Obter elementos com base no documento e na consulta
        Elements elementos = CrawlerModel.extractElements(doc, ID_SITE_TABLE);
        assert elementos.size() > 0;
    }

    @Test
    public void extractNodes() {
        //Obter nodes com base em uma área específica do documento, ou seja, com base no elemento
        Elements elementos = CrawlerModel.extractElements(doc, ID_SITE_TABLE);
        List<Node> nodes = CrawlerModel.extractNodes(elementos.get(0));
        assert (nodes.size() > 0);
    }

    @Test
    public void getThreadsList() throws IOException {
        CrawlerModel c = new CrawlerModel();
        long quant = 5000;
        long quantidadeThreads = 2;
        LinkedList<ThreadEntity> threadsList = c.getThreadsList(doc, "https://old.reddit.com", quantidadeThreads, "cats");
        assertTrue(String.format("%1$s == %2$s", threadsList.size(), quantidadeThreads), threadsList.size() == quantidadeThreads);
        threadsList.forEach(e -> assertTrue(String.format("%1$s >= %2$s", e.getVotes(), quant),e.getVotes() >= quant));

    }

    @Test
    public void getThreadsListWithoutThreadsLimit() throws IOException {
        CrawlerModel c = new CrawlerModel();
        long quant = 5000;
        long quantidadeMaxThreadsHtml = 3;
        Long quantidadeThreads = null;
        LinkedList<ThreadEntity> threadsList = c.getThreadsList(doc, "https://old.reddit.com", quantidadeThreads, "cats");
        assertTrue(String.format("%1$s == %2$s", threadsList.size(), quantidadeMaxThreadsHtml), threadsList.size() == quantidadeMaxThreadsHtml);
        threadsList.forEach(e -> assertTrue(String.format("%1$s >= %2$s", e.getVotes(), quant),e.getVotes() >= quant));

    }

}