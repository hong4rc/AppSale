package kiat.anhong.appsale.app;

import android.widget.ImageView;

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

public class App {
    private String link;
    private String title;
    private ImageView imageView;

    public App(String link, String title, ImageView imageView) {
        if (link.matches("^/r/")){
            link = "https://www.reddit.com" + link;
        }
        this.link = link;
        this.title = title;
        this.imageView = imageView;
    }

    public String getLink() {
        return link;
    }

    public ImageView getImageView() {
        return imageView;
    }

    String getTitle() {
        return title;
    }
}
