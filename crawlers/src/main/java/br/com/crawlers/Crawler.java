package br.com.crawlers;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.List;

public class Crawler {

    public static List<Node> extractNodes(Element elemento) {
        return elemento.childNodes();
    }

    public static Elements extractElements(Document doc, String s) {
        return doc.select(s);
    }

}
