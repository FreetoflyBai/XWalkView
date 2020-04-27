package com.android.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkSettings;
import org.xwalk.core.XWalkView;

public class MainActivity extends XWalkActivity {

    private TextView walkurl;
    private XWalkView walkView;

    @Override
    protected void onXWalkReady() {

        walkurl=findViewById(R.id.walkurl);
        walkView=findViewById(R.id.walkview);

        SharedPreferences sharedPreferences=getSharedPreferences("TEST", Context.MODE_PRIVATE);
        String boll=sharedPreferences.getString("OFFER","");
        if(TextUtils.isEmpty(boll)){
            Toast.makeText(this,"URL ERROR",Toast.LENGTH_SHORT).show();
            return;
        }

        XWalkSettings walkSettings=walkView.getSettings();
        walkSettings.setDomStorageEnabled(true);
        walkSettings.setJavaScriptEnabled(true);
        walkSettings.setUserAgentString(walkSettings.getUserAgentString().replace("Crosswalk/23.53.589.4","").replace("Chrome/53.0.2785.143","Chrome/80.0.3987.119"));

        walkView.setResourceClient(new XWalkResourceClient(walkView){
            @Override
            public boolean shouldOverrideUrlLoading(XWalkView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onLoadFinished(XWalkView view, String url) {
                super.onLoadFinished(view, url);
                walkurl.setText(url);
            }
        });

        walkurl.setText(boll);
        walkView.load(boll,null);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
