package br.com.crawlers.controller;

import br.com.crawlers.entity.ThreadEntity;
import br.com.crawlers.model.CrawlerModel;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Observable;
import java.util.stream.Collectors;

public class CrawlerController extends Observable {

    private String subredditAtual;
    private Long chatId;
    private boolean finished;

    private CrawlerModel getCrawlerModel(){
        return new CrawlerModel();
    }

    public void write(String baseUrl, Document doc, String params, Long chatId, String directory) throws IOException {
        this.setChatId(chatId);
        String url = directory + chatId + ".txt";
        StringBuilder stringBuilder = new StringBuilder();
        Files.deleteIfExists(Paths.get(url));
        CrawlerModel crw = getCrawlerModel();

        LinkedList<String> listParams = this.extractParams(params);
        listParams = listParams.stream().filter(e -> !StringUtil.isBlank(e)).collect(Collectors.toCollection(LinkedList::new));

        Long quantidadeThreads = StringUtil.isNumeric(listParams.getLast()) ? Long.valueOf(listParams.getLast()) : null;
        if(quantidadeThreads != null){
            listParams.removeLast();
        }

        for (String subreddit : listParams) {
            this.setSubredditAtual(subreddit);
            LinkedList<ThreadEntity> threadsList;
            if(doc == null) {
                threadsList = crw.getThreadsList(baseUrl + "/r/" + subreddit, baseUrl, quantidadeThreads, subreddit);
            }else{
                threadsList = crw.getThreadsList(doc, baseUrl, quantidadeThreads, subreddit);
            }
            stringBuilder.append("\n ======> LISTA DO SUBREDDIT: " + subreddit + " <==============");
            if(threadsList.size() > 0) {
                threadsList.forEach(e -> {
                    stringBuilder.append("\n   Subreddit: " + e.getSubreddit());
                    stringBuilder.append("\n   Número de Up Votes: " + e.getVotes());
                    stringBuilder.append("\n   Título da ThreadEntity: " + e.getTitle());
                    stringBuilder.append("\n   Link para os comentários da thread: " + e.getCommentLink());
                    stringBuilder.append("\n   Link da thread: " + e.getThreadLink());
                    stringBuilder.append("\n   ==========================================");
                });
            }else{
                stringBuilder.append("\n  Não encontrado! ");
                stringBuilder.append("\n   ==========================================");
            }
        }
        this.setFinished(true);
        Files.write(Paths.get(url), stringBuilder.toString().getBytes());
    }

    public LinkedList<String> extractParams(String params){
        String[] arrayParams = params.split(";");
        LinkedList<String> listParams = new LinkedList(Arrays.asList(arrayParams));
        listParams = listParams.stream().map(e -> e.trim()).collect(Collectors.toCollection(LinkedList::new));

        String lastParam = listParams.getLast();
        String[] arrayLastParams = lastParam.split("\\s+");
        LinkedList<String> listLastParams = new LinkedList(Arrays.asList(arrayLastParams));
        String quantidadeThreads = StringUtil.isNumeric(listLastParams.getLast()) ? listLastParams.getLast() : null;

        if(!StringUtil.isBlank(quantidadeThreads)) {
            listLastParams.removeLast();
        }

        listParams.set(listParams.size()-1,  listLastParams.stream().collect(Collectors.joining(" ")));

        listParams.addLast(quantidadeThreads);
        return listParams;
    }

    public String getSubredditAtual() {
        return subredditAtual;
    }

    public void setSubredditAtual(String subredditAtual) {
        this.subredditAtual = subredditAtual;
        setChanged();
        notifyObservers();
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
        setChanged();
        notifyObservers();
    }
}
