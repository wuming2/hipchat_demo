package com.anlassian.hipchat.utils;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wuming on 16/8/11.
 */
public class LinkParse {

    //    private OkHttpClient client = new OkHttpClient();
    private Pattern titlePattern = Pattern.compile("<title>([^<]+)</title>", Pattern.CASE_INSENSITIVE);


    public void requestTitle(String url, final OnTitleGetListener listener) {


        OkHttpClient newClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();

        Request request = new Request.Builder()
                .url(url)
                .build();


        newClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, IOException e) {
                        // TODO Error
                        listener.onGet(false, "");
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        String res = response.body().string();
                        // Do something with the response

                        Matcher matcher = titlePattern.matcher(res);
                        if (matcher.find()) {
                            String title = matcher.group(1);
                            Log.d("match", title);
                            listener.onGet(true, title);

                        } else {
                            listener.onGet(false, "");
                        }
                    }
                });

    }

    public interface OnTitleGetListener {
        void onGet(boolean ret, String title);
    }
}