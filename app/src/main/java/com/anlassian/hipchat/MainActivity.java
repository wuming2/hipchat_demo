package com.anlassian.hipchat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.anlassian.hipchat.models.MessageContent;
import com.anlassian.hipchat.utils.ContentParse;
import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {

    EditText et_input;
    TextView tv_result;
    Button bt_parse;
    Button bt_test_messages;
    CheckBox cb_testMode;
    Gson gson;

    // cancel display for the newest message
    double parsingIndex;

    // 2. Emoticons - For this exercise, you only need to consider 'custom' emoticons which are alphanumeric strings,
    // no longer than 15 characters, contained in parenthesis.
    // You can assume that anything matching this format is an emoticon.
    // (https://www.hipchat.com/emoticons)
    boolean testMode = false;

    //test code
    private String[] testMessages = {"@chris you around?"
            , "Good morning! (megusta) (coffee)"
            ,"link test http://www.google.com"
            , "Olympics are starting soon; http://www.nbcolympics.com"
            , "@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016"
            , "@xiaoyang @晓阳 中文还是要测试一下的"
            , "@xiaoyang multi link test with link can't access http://www.nbcolympics.com https://twitter.com/jdorfman/status/430511497475670016"};
    private int testMessageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initTestViews();
    }

    private void initView() {

        et_input = (EditText) findViewById(R.id.input);
        tv_result = (TextView) findViewById(R.id.results);
        bt_parse = (Button) findViewById(R.id.button_check);
        bt_test_messages = (Button) findViewById(R.id.button_test_messages);
        cb_testMode = (CheckBox) findViewById(R.id.testModeCheckBox);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.button_check:
                        String input = et_input.getText().toString();
                        parseMessage(input);
                        break;
                    case R.id.button_test_messages:
                        String message = testMessages[testMessageIndex];
                        et_input.setText(message);
                        testMessageIndex++;
                        if (testMessageIndex >= testMessages.length) {
                            testMessageIndex = 0;
                        }
                        parseMessage(message);
                        break;
                }
            }
        };

        bt_parse.setOnClickListener(listener);
        bt_test_messages.setOnClickListener(listener);

        cb_testMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                testMode = !isChecked;
            }
        });
    }

    private void displayParsing() {
        tv_result.setText(getString(R.string.results_parsing));
    }

    private void parseMessage(String message) {

        parsingIndex = Math.random();
        displayParsing();

        ContentParse parse = new ContentParse();
        parse.parse(message, new ContentParse.OnResultListener() {
            @Override
            public void onResult(final MessageContent result, final double code) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (gson == null) {
                            gson = new Gson();
                        }
                        if (code == parsingIndex) {
                            tv_result.setText(gson.toJson(result));
                        }
                    }
                });
            }
        }, testMode, parsingIndex);
    }

    private void initTestViews() {

    }
}
