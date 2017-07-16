package com.meishu.android.beatbox.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meishu on 16.07.2017.
 */

public class BeatBox {

    public static final String TAG = "BeatBox"; // для логирования

    public static final String SOUNDS_FOLDER = "sample_sounds"; // имя папки

    private AssetManager assetManager; // используется для обращения к assets
    private List<Sound> sounds = new ArrayList<>();

    public BeatBox(Context context) {
        assetManager = context.getAssets();
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = assetManager.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        }
        catch (IOException e) {
            Log.e(TAG, "IOException while loading sounds", e);
            return;
        }

        for (String fileName : soundNames) {
            String assetPath = SOUNDS_FOLDER + "/" + fileName;
            Sound sound = new Sound(assetPath);
            sounds.add(sound);
        }
    }

    public List<Sound> getSounds() {
        return sounds;
    }
 }
