package com.meishu.android.beatbox.beatbox;

/**
 * Created by Meishu on 16.07.2017.
 */

public class Sound {
    private String assetPath;
    private String name;
    private Integer soundID;

    public Sound(String assetPath) {
        this.assetPath = assetPath;
        String[] components = assetPath.split("/");
        String fileName = components[components.length - 1];
        name = fileName.replace(".wav", "");
    }

    public String getAssetPath() {
        return assetPath;
    }

    public String getName() {
        return name;
    }

    public Integer getSoundID() {
        return soundID;
    }

    public void setSoundID(Integer integer) {
        soundID = integer;
    }
}
