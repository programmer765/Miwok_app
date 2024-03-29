package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class ColorsFragment extends Fragment {


    private MediaPlayer music;
    private AudioManager mAudioManager;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                music.pause();
                music.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                music.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    public ColorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.words_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word ("red","wetetti", R.drawable.color_red, R.raw.color_red));
        words.add(new Word ("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word ("brown", "takaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word ("gray", "topoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word ("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word ("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        words.add(new Word ("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word ("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word pos = words.get(position);
                releaseMediaPlayer();
                music = MediaPlayer.create(getActivity(), pos.getAudioResourceId());
                music.start();
                music.setOnCompletionListener(mCompletionListener);
            }
        });
        return rootView;
    }
    private void releaseMediaPlayer() {
        if (music != null) {
            music.release();
            music = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

}