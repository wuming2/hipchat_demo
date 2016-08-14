package com.anlassian.hipchat.models;

/**
 * Created by wuming on 16/8/11.
 *
 */
public class URLContent {

    private String url;
    private String title;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageTitle() {
        return title;
    }

    public void setPageTitle(String pageTitle) {
        this.title = pageTitle;
    }

    public URLContent(String _url, String _pageTitle) {
        url = _url;
        title = _pageTitle;
    }
}
