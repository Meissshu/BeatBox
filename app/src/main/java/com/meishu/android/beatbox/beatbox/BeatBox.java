package com.meishu.android.beatbox.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meishu on 16.07.2017.
 */

public class BeatBox {

    private static final String TAG = "BeatBox"; // для логирования

    private static final String SOUNDS_FOLDER = "sample_sounds"; // имя папки

    private static final int MAX_SOUNDS = 5;

    private AssetManager assetManager; // используется для обращения к assets
    private List<Sound> sounds = new ArrayList<>();
    private SoundPool soundPool;

    public BeatBox(Context context) {
        assetManager = context.getAssets();
        soundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0); // deprecated, но нужен для обеспечения совместимости
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
