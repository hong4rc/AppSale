package kiat.anhong.appsale.AsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

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



public class ImageAsyncTask extends AsyncTask<String, Integer, Bitmap> {
    private static final String TAG = "ImageAsyncTask";
    private App app;
    private AppAdapter appAdapter;

    ImageAsyncTask(App app, String imgUrl, AppAdapter appAdapter) {
        Log.d(TAG, "exec: " + imgUrl);
        this.app = app;
        this.appAdapter = appAdapter;
        this.execute(imgUrl);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bmp = null;
        Log.d(TAG, strings[0]);
        try {
            URL url = new URL(strings[0]);
            InputStream inputStream = url.openConnection().getInputStream();
            bmp = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        app.getImageView().setImageBitmap(bitmap);
        appAdapter.notifyDataSetChanged();
    }
}