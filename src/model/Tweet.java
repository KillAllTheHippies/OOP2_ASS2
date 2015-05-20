package model;

import java.text.SimpleDateFormat;
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
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String tweetString = dateFormat.format(this.dateTimeSent) + ": " + this.tweetText + "\n" ;
        return tweetString;
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
