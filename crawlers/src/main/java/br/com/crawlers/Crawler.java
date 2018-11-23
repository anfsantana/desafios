package br.com.crawlers;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Crawler {
    private static final String ID_SITE_TABLE = "#siteTable";
    private static final long VOTES_CONDITION = 5000;

    public static List<Node> extractNodes(Element elemento) {
        return elemento.childNodes();
    }

    public static Elements extractElements(Document doc, String s) {
        return doc.select(s);
    }

    public LinkedList<Thread> getThreadsList(String url, String baseUrl, long quantidadeThreads, String subreddit) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return this.getThreadsList(doc, baseUrl, quantidadeThreads, subreddit);
    }

    public LinkedList<Thread> getThreadsList(Document doc, String baseUrl, long quantidadeThreads, String subreddit) throws IOException {
        LinkedList<Thread> threadsList = new LinkedList<>();
        if (quantidadeThreads > 0) {
            Elements elementos = Crawler.extractElements(doc, ID_SITE_TABLE);
            for (Element elemento : elementos) {
                for (Node node : Crawler.extractNodes(elemento)) {
                    String stringVote = Jsoup.parse(node.toString()).select("div.midcol.unvoted > div.score.unvoted").attr("title");
                    String title = Jsoup.parse(node.toString()).select("div.entry.unvoted > div.top-matter > p.title > a").text();
                    String commentLink = Jsoup.parse(node.toString()).select("div.entry.unvoted > div.top-matter > ul > li.first > a").attr("href");
                    String threadLink = Jsoup.parse(node.toString()).select("div.entry.unvoted > div.top-matter > p.title > a").attr("href");
                    if (!StringUtil.isBlank(stringVote) && StringUtil.isNumeric(stringVote)) {
                        Long votes = Long.parseLong(stringVote);
                        if (votes >= VOTES_CONDITION && quantidadeThreads > 0) {
                            Thread t = new Thread(baseUrl);
                            t.setSubreddit(subreddit)
                                    .setVotes(votes)
                                    .setTitle(title)
                                    .setCommentLink(commentLink)
                                    .setThreadLink(threadLink);
                            threadsList.add(t);
                            quantidadeThreads = quantidadeThreads - 1;
                        }
                    } else {
                        String subUrl = Jsoup.parse(node.toString()).select("div.nav-buttons > span > span.next-button > a").attr("href");
                        if (!StringUtil.isBlank(subUrl)) {
                            threadsList.addAll(getThreadsList(subUrl, baseUrl, quantidadeThreads, subreddit));
                        }
                    }
                }
            }
        }

        return threadsList;
    }
}
