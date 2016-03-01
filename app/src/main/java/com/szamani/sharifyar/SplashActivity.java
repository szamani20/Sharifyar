package com.szamani.sharifyar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Szamani on 8/16/2015.
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "D1sUryf6egQQjxEqyPJFzeYggKt3FHmLiyAAYZIK", "mdaIGIJX8KvtE1VpajPU6g7xOkN22mnz0UUqNveo");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("NotificationObject");

        query.getInBackground("4ARUuFHBJy", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    String notification = parseObject.getString("Notification");
                    if (notification.length() >= 10)
                        Toast.makeText(SplashActivity.this, notification, Toast.LENGTH_LONG)
                                .show();
                }
            }
        });

        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("BlockedUsers");
        query1.getInBackground("CYLcvA0XVN", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null)
                    Utils.block = parseObject.getString("Block");
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, Utils.SPLASH_TIMEOUT);
    }
}
