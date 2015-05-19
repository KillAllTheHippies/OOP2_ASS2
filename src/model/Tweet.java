package model;

import java.util.Date;

/**
 * Created by Jamie on 26/04/15.
 */
public class Tweet {
    private String tweetText;
    private Date dateTimeSent;

    public Tweet(String tweetText, Date dateTimeSent) {
        this.tweetText = tweetText;
        this.dateTimeSent = dateTimeSent;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public Date getDateTimeSent() {
        return dateTimeSent;
    }

    public void setDateTimeSent(Date dateTimeSent) {
        this.dateTimeSent = dateTimeSent;
    }
}
