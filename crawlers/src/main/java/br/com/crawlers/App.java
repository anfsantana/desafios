package br.com.crawlers;

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
        Crawler c = new Crawler();
        Scanner sc = new Scanner(System.in);
        System.out.print("Informe os nomes de subreddits separados por ponto-e-vírgula: ");
        String subreddits = sc.next();
        System.out.print("Informe a quantidade de threads que você deseja obter por cada subreddit: ");
        long quantidadeThreads = sc.nextLong();

        try {
            c.write("https://old.reddit.com", subreddits, quantidadeThreads);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
