package kiat.anhong.appsale.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;


import kiat.anhong.appsale.app.App;
import kiat.anhong.appsale.app.AppAdapter;

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

class PlayStoreAsyncTask extends AsyncTask<String, Integer, Element> {
    private static final String TAG = "PlayStoreAsyncTask";
    private App app;
    private AppAdapter appAdapter;
    PlayStoreAsyncTask(App app, AppAdapter appAdapter) {
        Log.d(TAG, "exec: " + app.getLink());
        this.app = app;
        this.appAdapter = appAdapter;
        this.execute(app.getLink());
    }

    @Override
    protected Element doInBackground(String... strings) {
        try {
            return Jsoup.connect(strings[0]).get().select(".cover-container .cover-image").get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Element element) {
        super.onPostExecute(element);
        String imgUrl;
        if (element != null){
            imgUrl = "https:" +element.attr("src");
        } else {
            imgUrl = "";
        }
        new ImageAsyncTask(app, imgUrl, appAdapter);

    }
}