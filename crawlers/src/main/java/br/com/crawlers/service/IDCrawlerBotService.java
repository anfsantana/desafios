package br.com.crawlers.service;

import org.jsoup.helper.StringUtil;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class IDCrawlerBotService extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        SendMessage message;
        for (Update update : updates) {
            boolean ok = false;
            SendChatAction sendChatAction = new SendChatAction()
                    .setChatId(update.getMessage().getChatId())
                    .setAction(ActionType.TYPING);
            try {
                if (update.hasMessage() && update.getMessage().isCommand()) {

                    if (update.getMessage().getText().toLowerCase().startsWith("/start")) {
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .enableMarkdown(true)
                                .setText("Olá !");
                        execute(sendChatAction);
                        execute(message);
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .enableMarkdown(true)
                                .setText("Fico feliz em te ver por aqui \uD83D\uDE03 !");
                        execute(sendChatAction);
                        Thread.sleep(2000);
                        execute(message);
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .enableMarkdown(true)
                                .setText("Para começar, digite o comando ```nadaprafazer``` ou ```NadaPraFazer``` seguido de uma lista de itens para busca, por exemplo ```askreddit;worldnews;cats```");
                        execute(sendChatAction);
                        Thread.sleep(3000);
                        execute(message);
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .enableMarkdown(true)
                                .setText("Após isso, aguarde até que a busca por esses itens (askreddit;worldnews;cats) que tenham igual ou mais de 5000 votos, sejam encontrados. ");
                        execute(sendChatAction);
                        Thread.sleep(3500);
                        execute(message);
                    } else if (update.getMessage().getText().toLowerCase().startsWith("/nadaprafazer")) {
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .setText("Recebi a sua solicitação, estou buscando aqui ... aguarde só um pouquinho \uD83D\uDE42 ");
                        ok = true;
                        execute(sendChatAction);
                        execute(message);
                    } else {
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .setText("Desculpe-me, não reconheço esse comando \uD83D\uDE14 ... ")
                                .enableHtml(true);
                        execute(sendChatAction);
                        execute(message);
                    }
                } else {
                    message = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setText("Para interagir, utilize o comando /nadaprafazer com a lista de subreddits \uD83D\uDE09 ");
                    execute(sendChatAction);
                    execute(message);
                }
                if (ok) {
                }
            } catch (TelegramApiException e) {
                message = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Desculpe-me, ocorreu um erro \uD83D\uDE25 ... Tente novamente ... \uD83D\uDE14 ");
                try {
                    execute(sendChatAction);
                    execute(message);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            } catch (Exception e) {
                message = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .enableMarkdown(true)
                        .setText("Desculpe-me, ocorreu um erro \uD83D\uDE25. Não consegui acessar a busca devido ao seguinte erro: *" + e.getLocalizedMessage() + "* ... Tente novamente ... \uD83D\uDE14 ");
                try {
                    execute(sendChatAction);
                    execute(message);
                } catch (TelegramApiException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "idwallsubredditbot";
    }

    @Override
    public String getBotToken() {
        return "664988127:AAESPjaGdvbsjtGWMB904Hel7Xk-j76uoU0";
    }
}
