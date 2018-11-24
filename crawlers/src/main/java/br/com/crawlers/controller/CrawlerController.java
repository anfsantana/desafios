package br.com.crawlers.controller;

import br.com.crawlers.entity.ThreadEntity;
import br.com.crawlers.model.CrawlerModel;

import java.io.IOException;
import java.util.LinkedList;

public class CrawlerController {

    private CrawlerModel getCrawlerModel(){
        return new CrawlerModel();
    }

    public void write(String baseUrl, String subreddits, long quantidadeThreads) throws IOException {
        CrawlerModel crw = getCrawlerModel();
        String[] arraySubreddits = subreddits.split(";");
        for (String subreddit : arraySubreddits) {
            System.out.print("\n ======> LISTA DO SUBREDDIT: " + subreddit + " <==============");
            LinkedList<ThreadEntity> threadsList = crw.getThreadsList(baseUrl + "/r/" + subreddit, baseUrl, quantidadeThreads, subreddit);
            threadsList.forEach(e -> {
                System.out.print("\n   Subreddit: " + e.getSubreddit());
                System.out.print("\n   Número de Up Votes: " + e.getVotes());
                System.out.print("\n   Título da ThreadEntity: " + e.getTitle());
                System.out.print("\n   Link para os comentários da thread: " + e.getCommentLink());
                System.out.print("\n   Link da thread: " + e.getThreadLink()) ;
                System.out.print("\n   ==========================================");
            });
        }

    }
}
