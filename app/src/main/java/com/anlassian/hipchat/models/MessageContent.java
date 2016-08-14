package com.anlassian.hipchat.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuming on 16/8/11.
 *
 */
public class MessageContent {

    private List<String> mentions = null;
    private List<String> emoticons = null;
    private List<URLContent> links = null;

    public MessageContent() {
    }

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        if (this.mentions == null) {
            this.mentions = new ArrayList<>();
        }
        this.mentions = mentions;
    }

    public void addMentions(String mention) {
        if (mentions == null) {
            mentions = new ArrayList<>();
        }
        mentions.add(mention);
    }

    public List<String> getEmoticons() {
        return emoticons;
    }

    public void setEmoticons(List<String> emoticons) {
        if (this.emoticons == null) {
            this.emoticons = new ArrayList<>();
        }
        this.emoticons = emoticons;
    }

    public void addEmoticons(String emotion) {
        if (this.emoticons == null) {
            this.emoticons = new ArrayList<>();
        }
        emoticons.add(emotion);
    }

    public List<URLContent> getLinks() {
        return links;
    }

    public void setLinks(List<URLContent> links) {
        if (this.links == null) {
            this.links = new ArrayList<>();
        }
        this.links = links;
    }

    public void addLink(URLContent link) {
        if (this.links == null) {
            this.links = new ArrayList<>();
        }
        this.links.add(link);
    }
}
