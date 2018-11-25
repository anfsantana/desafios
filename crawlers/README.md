# Descrição dos processos

## Resoluções do desafio
Para a resolução do desafio, foram utilizados os seguintes passos:
1. Para a extração das informações do site https://old.reddit.com:
    1. Leitura da documentação técnica do Jsoup;
    2. Analise da estrutura do site https://old.reddit.com, para saber como os elementos html e css estão organizados;
    3. Identificação de  onde estão e quais itens serão extraídos;
    4. Utilização de seletores para extração das informações e implementação da lógica. 
2. Para a disponibilização dos resultados via bot do telegram:
    1. Leitura da documentação técnica de bots do telegram;
    2. Implementar um pequeno MVP do boot para chegar ao funcionamento;
    3. Implementar as funcionalidades do bot.
    
## Como utilizar a solução
Para poder utilizar a solução é necessário **executar o projeto java** e em seguida **adicionar o bot no seu telegram**; para isso, utilize os seguintes passos:
1. Para executar o projeto, será necessário possuir **docker**;
2. Navegar até o diretório: desafios > crawlers;
3. Executar os seguintes comandos:
    1. `docker build -t crawlers:latest .`
        1. **Observação: Não esquecer o ponto após o latest!**
    2. `docker run crawlers`
4. Aguarde até a mensagem "IDCrawlerBot iniciado!" aparecer no terminal.
5. Para utilizar o bot basta buscar no telegram o seguinte username: `@idwallsubredditbot` e em seguida seguir as instruções fornecidas pelo próprio bot.


# Desafio 2: Crawlers

Parte do trabalho na IDwall inclui desenvolver *crawlers/scrapers* para coletar dados de websites.
Como nós nos divertimos trabalhando, às vezes trabalhamos para nos divertir!

O Reddit é quase como um fórum com milhares de categorias diferentes. Com a sua conta, você pode navegar por assuntos técnicos, ver fotos de gatinhos, discutir questões de filosofia, aprender alguns life hacks e ficar por dentro das notícias do mundo todo!

Subreddits são como fóruns dentro do Reddit e as postagens são chamadas *threads*.

Para quem gosta de gatos, há o subreddit ["/r/cats"](https://www.reddit.com/r/cats) com threads contendo fotos de gatos fofinhos.
Para *threads* sobre o Brasil, vale a pena visitar ["/r/brazil"](https://www.reddit.com/r/brazil) ou ainda ["/r/worldnews"](https://www.reddit.com/r/worldnews/).
Um dos maiores subreddits é o "/r/AskReddit".

Cada *thread* possui uma pontuação que, simplificando, aumenta com "up votes" (tipo um like) e é reduzida com "down votes".

Sua missão é encontrar e listar as *threads* que estão bombando no Reddit naquele momento!
Consideramos como bombando *threads* com 5000 pontos ou mais.

## Entrada
- Lista com nomes de subreddits separados por ponto-e-vírgula (`;`). Ex: "askreddit;worldnews;cats"

### Parte 1
Gerar e imprimir uma lista contendo número de upvotes, subreddit, título da thread, link para os comentários da thread, link da thread.
Essa parte pode ser um CLI simples, desde que a formatação da impressão fique legível.

### Parte 2
Construir um robô que nos envie essa lista via Telegram sempre que receber o comando `/NadaPraFazer [+ Lista de subrredits]` (ex.: `/NadaPraFazer programming;dogs;brazil`)

### Dicas
 - Use https://old.reddit.com/
 - Qualquer método para coletar os dados é válido. Caso não saiba por onde começar, procure por JSoup (Java), SeleniumHQ (Java), PhantomJS (Javascript) e Beautiful Soup (Python).
