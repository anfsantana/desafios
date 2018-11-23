package br.com.crawlers;

public class Thread {
    private String subreddit;
    private Long votes;
    private String title;
    private String commentLink;
    private String threadLink;
    private String baseUrl;

    public Thread(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public Thread setSubreddit(String subreddit) {
        this.subreddit = subreddit;
        return this;
    }

    public Long getVotes() {
        return votes;
    }

    public Thread setVotes(Long votes) {
        this.votes = votes;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Thread setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCommentLink() {
        return commentLink;
    }

    public Thread setCommentLink(String commentLink) {
        this.commentLink = commentLink;
        return this;
    }

    public String getThreadLink() {
        return threadLink;
    }

    public Thread setThreadLink(String threadLink) {
        this.threadLink = (!threadLink.startsWith("http") ? baseUrl + threadLink : threadLink);
        return this;
    }

}
