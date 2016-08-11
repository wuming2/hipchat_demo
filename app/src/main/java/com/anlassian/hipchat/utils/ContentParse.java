package com.anlassian.hipchat.utils;

import android.util.Patterns;

import com.anlassian.hipchat.models.MessageContent;
import com.anlassian.hipchat.models.URLContent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuming on 16/8/11.
 * <p/>
 * Parse message using pattern
 */
public class ContentParse {

    // TODO a non-word character? chinese ? numbers?
    private static final Pattern PATTERN_MENTION =
            Pattern.compile("\\@[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]+"
                    , Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    //"\\@[a-z]+"

    private static final Pattern PATTERN_EMOTION =
            Pattern.compile("\\([a-z]{1,15}\\)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    private static final Pattern PATTERN_URL = Patterns.WEB_URL;
    int linkCount = 0;
    boolean linkChecked = false;

    public boolean parse(String input, final OnResultListener listener
            , boolean testMode, final double code) {

        final MessageContent content = new MessageContent();

        Matcher matcher = PATTERN_MENTION.matcher(input);
        while (matcher.find()) {
            String mention = matcher.group();
            content.addMentions(mention.substring(1));
        }

        matcher = PATTERN_EMOTION.matcher(input);
        while (matcher.find()) {
            String emoticon = matcher.group();
            if (EmoticonsUtil.isEmoticon(testMode, emoticon)) {
                content.addEmoticons(emoticon.substring(1, emoticon.length() - 1));
            }
        }

        matcher = PATTERN_URL.matcher(input);
        linkChecked = false;
        while (matcher.find()) {
            linkCount++;
            final String url = matcher.group();

            // get title
            LinkParse linkParse = new LinkParse();
            linkParse.requestTitle(url, new LinkParse.OnTitleGetListener() {
                @Override
                public void onGet(boolean ret, String title) {
                    linkCount--;
                    if (!ret) {
                        title = "[ERROR,Unable to open the link]";
                    }
                    content.addLink(new URLContent(url, title));//TitleExtractor.getPageTitle(url)
                    if (linkCount <= 0 && linkChecked) {
                        listener.onResult(content, code);
                    }
                }
            });
        }
        linkChecked = true;
        if (linkCount <= 0 && linkChecked) {
            listener.onResult(content, code);
        }
        //listener.onResult(content);
        return true;
    }

    public interface OnResultListener {
        void onResult(MessageContent result, double code);
    }
}
