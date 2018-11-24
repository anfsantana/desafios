package br.com.crawlers.service;

import br.com.crawlers.controller.CrawlerController;
import br.com.crawlers.model.CrawlerModel;
import org.jsoup.helper.StringUtil;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IDCrawlerBotService extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        SendMessage message;
        SendVideo sendVideo;
        InputStream stream;
        SendDocument sendDocument;
        for (Update update : updates) {
            boolean ok = false;
            SendChatAction sendChatAction = new SendChatAction()
                    .setChatId(update.getMessage().getChatId())
                    .setAction(ActionType.TYPING);
            SendChatAction sendChatActionVideo = new SendChatAction()
                    .setChatId(update.getMessage().getChatId())
                    .setAction(ActionType.UPLOADVIDEO);
            SendChatAction sendChatActionDocument = new SendChatAction()
                    .setChatId(update.getMessage().getChatId())
                    .setAction(ActionType.UPLOADDOCUMENT);
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
                                .setText("Você também pode me dizer a quantidade de threads que você deseja obter, basta digitar o comando ```nadaprafazer``` ou ```NadaPraFazer``` seguido de " +
                                        "uma lista de itens para busca, por exemplo ```askreddit;worldnews;cats``` mais a quantidade de threads que você deseja obter. ");
                        execute(sendChatAction);
                        Thread.sleep(3000);
                        execute(message);
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .enableMarkdown(true)
                                .setText("Caso não seja informado a quantidade, poderá ser uma busca mais demorada, porque irei buscar em todos os subreddits ^^ .");
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
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .enableMarkdown(true)
                                .setText("Irei mostrar um exemplo de como funciona  \uD83D\uDE42.");
                        execute(sendChatAction);
                        Thread.sleep(3500);
                        execute(message);
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .enableMarkdown(true)
                                .setText("Aguarde até que o vídeo carregue  \uD83D\uDE42. Seja um pouco paciente ^^ ");
                        execute(sendChatAction);
                        Thread.sleep(3500);
                        execute(message);

                        sendVideo = new SendVideo();
                        ClassLoader classLoader = getClass().getClassLoader();
                        stream = classLoader.getResourceAsStream("animationgretchen.mp4");
                        sendVideo.setVideo("Funny", stream)
                                .setChatId(update.getMessage().getChatId());
                        execute(sendChatAction);
                        execute(sendVideo);

                        sendVideo = new SendVideo();
                        stream = classLoader.getResourceAsStream("demonstracao.mp4");
                        sendVideo.setVideo("Demonstração", stream)
                                .setChatId(update.getMessage().getChatId());
                        execute(sendChatActionVideo);
                        execute(sendVideo);
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .enableMarkdown(true)
                                .setText("Agora que você já está sabendo, vamos começar !");
                        execute(sendChatAction);
                        Thread.sleep(7000);
                        execute(message);

                        sendVideo = new SendVideo();
                        stream = classLoader.getResourceAsStream("animation2.mp4");
                        sendVideo.setVideo("Divando", stream)
                                .setChatId(update.getMessage().getChatId());
                        execute(sendChatAction);
                        execute(sendVideo);
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
                    CrawlerController c = new CrawlerController();
                    String lista = update.getMessage().getText().toLowerCase().replaceFirst("/nadaprafazer", "").trim();
                    if (!StringUtil.isBlank(lista)) {
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .enableMarkdown(true)
                                .setText("Blz! Irei iniciar a busca. Assim que finalizar, enviarei a lista do que eu encontrei (: ");
                        execute(sendChatAction);
                        Thread.sleep(7000);
                        execute(message);

                        String directory = "src/main/files/";
                        String parametros = lista;
                        c.write("https://old.reddit.com", null, parametros, update.getMessage().getChatId(), directory);

                        sendDocument = new SendDocument();
                        stream = Files.newInputStream(Paths.get( directory+ update.getMessage().getChatId() + ".txt"));
                        sendDocument.setDocument("Resultado_busca.txt", stream)
                                .setChatId(update.getMessage().getChatId());
                        execute(sendChatActionDocument);
                        execute(sendDocument);
                    } else {
                        message = new SendMessage()
                                .setChatId(update.getMessage().getChatId())
                                .setText("Desculpe-me, poderia verificar os itens solicitados na lista e tentar novamente \uD83D\uDE14 ?");
                        execute(sendChatAction);
                        execute(message);
                    }
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
            } catch (IOException e) {
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
            } catch (InterruptedException e) {
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
