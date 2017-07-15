package kiat.anhong.appsale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ListView;

import kiat.anhong.appsale.AsyncTask.HomeAsyncTask;

/*
 * Copyright (C) 2017 The KiaT Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

public class MainActivity extends Activity {
    private ListView listViewApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkInternetConnection();

        listViewApp = findViewById(R.id.listApp);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new HomeAsyncTask(getApplicationContext(), listViewApp);
            }
        });
    }

    private void checkInternetConnection() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        if (conMgr.getActiveNetworkInfo() == null
                || !conMgr.getActiveNetworkInfo().isAvailable()
                || !conMgr.getActiveNetworkInfo().isConnected()) {

            //:TODO show dialog to user
            Intent connectionIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            this.startActivity(connectionIntent);
        }
    }
}
