package com.anlassian.hipchat.models;

import java.util.ArrayList;

/**
 * Created by wuming on 16/8/11.
 * <p/>
 * emoticons
 */
public class EmoticonsList {

    private ArrayList<Emoticon> list;

    public ArrayList<Emoticon> getList() {
        return list;
    }

    public void setList(ArrayList<Emoticon> list) {
        this.list = list;
    }

    public class Emoticon {

        private String text;
        private String img;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
