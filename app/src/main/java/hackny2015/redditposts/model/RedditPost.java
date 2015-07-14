package hackny2015.redditposts.model;

// This object represents a reddit post.
public class RedditPost {

    private String title; // Title of the reddit post
    private String imgUrl; // Url of the image for the reddit post
    private String url; // Url of the reddit post

    // Constructor that takes in a title, image url, and post url
    public RedditPost(String title, String imgUrl, String url) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.url = url;
    }

    // Returns the title
    public String getTitle() {
        return title;
    }

    // Sets the title
    public void setTitle(String title) {
        this.title = title;
    }

    // Returns the image url
    public String getImgUrl() {
        return imgUrl;
    }

    // Sets the image url
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    // Returns the post url
    public String getUrl() {
        return url;
    }

    // Sets the post url
    public void setUrl(String url) {
        this.url = url;
    }
}