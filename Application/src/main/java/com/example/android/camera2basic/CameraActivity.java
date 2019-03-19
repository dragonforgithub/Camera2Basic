/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.camera2basic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


public class CameraActivity extends AppCompatActivity {

    private final int PICTURE_MODE = 0;
    private final int VIDEO_MODE = 1;
    private int mCurMode = 0;

    private ImageButton mModeButton = null;
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_camera);

        mModeButton = findViewById(R.id.mode_switch);
        mModeButton.setOnClickListener(new ButtonPart());

        //默认启动拍照模式
        switch_to_pictureMode();
    }

    /**
     * 切换到录像模式
     * */
    private void switch_to_pictureMode(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, Camera2PictureFragment.newInstance())
                .commit();
        mCurMode = PICTURE_MODE;
        Drawable pic_drawable = ContextCompat.getDrawable(mContext, R.drawable.btn_switch_to_video);
        mModeButton.setBackground(pic_drawable);
    }

    /**
     * 切换到拍照模式
     * */
    private void switch_to_videoMode(){
        getFragmentManager().beginTransaction()
                .replace(R.id.container, Camera2VideoFragment.newInstance())
                .commit();
        mCurMode = VIDEO_MODE;
        Drawable video_drawable = ContextCompat.getDrawable(mContext, R.drawable.btn_switch_to_picture);
        mModeButton.setBackground(video_drawable);
    }

    /**
     * 按键事件处理
     * */
    class ButtonPart implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.mode_switch:
                    if (mCurMode == PICTURE_MODE) {
                        switch_to_videoMode();
                    } else if (mCurMode == VIDEO_MODE) {
                        switch_to_pictureMode();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
