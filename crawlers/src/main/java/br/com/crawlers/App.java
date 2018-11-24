package br.com.crawlers;

import br.com.crawlers.controller.CrawlerController;
import br.com.crawlers.model.CrawlerModel;
import br.com.crawlers.service.IDCrawlerBotService;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new IDCrawlerBotService());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

        System.out.println("IDCrawlerBot iniciado!");

    }
}
