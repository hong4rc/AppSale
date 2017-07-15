package kiat.anhong.appsale.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import kiat.anhong.appsale.R;

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

public class AppAdapter extends ArrayAdapter<App> {
    public AppAdapter(Context context, int resource, ArrayList<App> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_app, parent, false);

            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.imageView = convertView.findViewById(R.id.imgView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        App app = getItem(position);
        if (app != null) {

            viewHolder.title.setText(app.getTitle());
            if (app.getImageView().getDrawable() != null){
                Log.d( "getView: ", "getDrawable() = " + app.getImageView().getDrawable().toString());
                viewHolder.imageView.setImageDrawable(app.getImageView().getDrawable());
            }
            convertView.setOnClickListener(null);
            if (app.getLink().contains("play.google.com")) {
                Log.d( "getView: ", "link : " + app.getLink());
                convertView.setOnClickListener(getItemClick(app.getLink()));
            } else if (app.getLink().contains("r/googleplaydeals")) {
                convertView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showhideDetail();
                    }
                });
            }
        }
        return convertView;
    }

    private void showhideDetail() {

    }

    private OnClickListener getItemClick(final String url) {
        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                getContext().startActivity(intent);
            }
        };
    }
}
