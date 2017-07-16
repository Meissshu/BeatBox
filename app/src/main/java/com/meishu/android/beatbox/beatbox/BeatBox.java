package com.meishu.android.beatbox.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
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
            try {
                String assetPath = SOUNDS_FOLDER + "/" + fileName;
                Sound sound = new Sound(assetPath);
                loadSoundToSoundPool(sound);
                sounds.add(sound);
            }
            catch (IOException e) {
                Log.e(TAG, "Cannot load sound " + fileName, e);
            }
        }
    }

    private void loadSoundToSoundPool(Sound sound) throws IOException {
        AssetFileDescriptor assetFileDescriptor = assetManager.openFd(sound.getAssetPath());
        int soundID = soundPool.load(assetFileDescriptor, 1);
        sound.setSoundID(soundID);
    }

    public void playSound(Sound sound) {
        Integer soundID = sound.getSoundID();
        if (soundID == null) {
            return;
        }
        soundPool.play(soundID, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release() {
        soundPool.release();
    }

    public List<Sound> getSounds() {
        return sounds;
    }
 }
