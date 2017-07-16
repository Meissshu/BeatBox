package com.meishu.android.beatbox.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.meishu.android.beatbox.R;
import com.meishu.android.beatbox.beatbox.BeatBox;
import com.meishu.android.beatbox.beatbox.Sound;

import java.util.List;

/**
 * Created by Meishu on 16.07.2017.
 */

public class BeatBoxFragment extends Fragment {

    private RecyclerView recyclerView;
    private BeatBox beatBox;

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beatBox = new BeatBox(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beat_box, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.fragment_beat_box_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        recyclerView.setAdapter(new SoundAdapter(beatBox.getSounds()));
        return v;
    }

    private class SoundHolder extends RecyclerView.ViewHolder{

        private Button button;
        private Sound sound;

        public SoundHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.list_item_sound, container, false));
            button = (Button) itemView.findViewById(R.id.list_item_sound_button);
        }

        public void bindSounds(Sound sound) {
            this.sound = sound;
            button.setText(sound.getName());
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        private List<Sound> sounds;

        public SoundAdapter(List<Sound> sounds) {
            this.sounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SoundHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = sounds.get(position);
            holder.bindSounds(sound);
        }

        @Override
        public int getItemCount() {
            return sounds.size();
        }
    }
}
