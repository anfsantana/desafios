package br.com.crawlers.entity;

public class ThreadEntity {
    private String subreddit;
    private Long votes;
    private String title;
    private String commentLink;
    private String threadLink;
    private String baseUrl;

    public ThreadEntity(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public ThreadEntity setSubreddit(String subreddit) {
        this.subreddit = subreddit;
        return this;
    }

    public Long getVotes() {
        return votes;
    }

    public ThreadEntity setVotes(Long votes) {
        this.votes = votes;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ThreadEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCommentLink() {
        return commentLink;
    }

    public ThreadEntity setCommentLink(String commentLink) {
        this.commentLink = commentLink;
        return this;
    }

    public String getThreadLink() {
        return threadLink;
    }

    public ThreadEntity setThreadLink(String threadLink) {
        this.threadLink = (!threadLink.startsWith("http") ? baseUrl + threadLink : threadLink);
        return this;
    }

}
