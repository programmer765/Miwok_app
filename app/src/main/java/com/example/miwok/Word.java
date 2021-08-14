package com.example.miwok;

import android.media.Image;
import android.media.MediaPlayer;

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE;
    private static int NO_IMAGE;
    private int mAudioResourceId;
    public Word (String defaultTranslation, String miwokTranslation, int AudioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = AudioResourceId;
    }
    public Word (String defaultTranslation, String miwokTranslation, int ImageResourceId, int AudioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = ImageResourceId;
        mAudioResourceId = AudioResourceId;
    }
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }
    public int getAudioResourceId() {
        return mAudioResourceId;
    }
    public int getImageResourceId() {
        return mImageResourceId;
    }
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE;
    }
}
