package com.anlassian.hipchat.utils;

import com.anlassian.hipchat.models.MessageContent;
import com.google.gson.Gson;

import junit.framework.TestCase;
import android.util.Patterns;

import org.mockito.ArgumentCaptor;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by wuming on 16/8/11.
 */
public class ContentParseTest extends TestCase {

    Gson gson;
    double parsingIndex;
    int testMessageIndex = 0;

    String[] testMessages = {"@chris you around?"
            , "Good morning! (megusta) (coffee)"
            //, "Olympics are starting soon; http://www.nbcolympics.com"
            //, "@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016"
            , "@xiaoyang @晓阳 中文还是要测试一下的"};
            //, "@xiaoyang multi link test with link can't access http://www.nbcolympics.com https://twitter.com/jdorfman/status/430511497475670016"};

    String[] testResult = {"{\"mentions\":[\"chris\"]}"
            , "{\"emoticons\":[\"megusta\",\"coffee\"]}"
            //, "Olympics are starting soon; http://www.nbcolympics.com"
            //, "@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016"
            , "{\"mentions\":[\"xiaoyang\",\"晓阳\"]}"};
            //, "@xiaoyang multi link test with link can't access http://www.nbcolympics.com https://twitter.com/jdorfman/status/430511497475670016"};


    @Before
    public void setUp() throws Exception {
        super.setUp();
        System.out.println("setUp");

    }

    @Test
    public void testParse() throws Exception {


        ContentParse parse = new ContentParse();
        gson = new Gson();


        boolean testMode = false;

        parsingIndex = Math.random();


        for (testMessageIndex = 0; testMessageIndex < testMessages.length; testMessageIndex++) {

            String message = testMessages[testMessageIndex];
            System.out.println("test " + message);
            parse.parse(message, new ContentParse.OnResultListener() {
                @Override
                public void onResult(final MessageContent result, final double code) {

                    if (code == parsingIndex) {
                        assertEquals(gson.toJson(result), testResult[testMessageIndex]);
                    }
                }
            }, testMode, parsingIndex);
        }


    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}