package kiat.anhong.appsale.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import kiat.anhong.appsale.R;
import kiat.anhong.appsale.app.App;
import kiat.anhong.appsale.app.AppAdapter;
import kiat.anhong.appsale.app.AppDetail;



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

public class HomeAsyncTask extends AsyncTask<String, Integer, Elements> {
    private static final String TAG = "HomeAsyncTask";
    private static final String HOME_URL = "https://www.reddit.com/r/googleplaydeals/";
    private static final String HOME_SELECT = ".link[onclick=\"click_thing(this)\"]";
    private static final String HOME_ATTR_LINK = "data-url";
    private static final String HOME_ATTR_TITLE = "a.title";
    private static AppAdapter appAdapter;
    private static ArrayList<App> listApp;

    private Object context;

    public HomeAsyncTask(Context context, ListView listViewApp) {
        this.context = context;
        listApp = new ArrayList<>();
        appAdapter = new AppAdapter(context, R.layout.item_app, listApp);
        listViewApp.setAdapter(appAdapter);

        this.execute(HOME_URL);
    }

    @Override
    protected Elements doInBackground(String... strings) {
        try {
            return Jsoup.connect(strings[0]).get().select(HOME_SELECT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Elements elements) {
        super.onPostExecute(elements);

        Log.d(TAG, "onPostExecute: ");
        super.onPostExecute(elements);
        if (elements == null) return;
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            AppDetail appDetail = new AppDetail();
            appDetail.link = element.attr(HOME_ATTR_LINK);
            appDetail.title = element.select(HOME_ATTR_TITLE).text();
            Log.d(TAG, "title " + appDetail.title);

            ImageView imageView = new ImageView((Context) context);
            imageView.setImageResource(R.drawable.ic_launcher);
            App app = new App(appDetail.link, appDetail.title, imageView);
            listApp.add(app);
            appAdapter.notifyDataSetChanged();

            if (isPlayStore(app.getLink())){
                new PlayStoreAsyncTask(app, appAdapter);
            } else if (isList(app.getLink())){
                new PlayStoreAsyncTask(app, appAdapter);
            }

        }

    }

    private boolean isPlayStore(String link) {
        return link.contains("play.google.com/");
    }
    private boolean isList(String link) {
        return link.contains("/r/googleplaydeals");
    }
}
