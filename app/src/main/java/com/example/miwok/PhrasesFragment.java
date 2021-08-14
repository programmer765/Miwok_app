package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PhrasesFragment extends Fragment {
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
    public PhrasesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.words_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word ("Where are you going?","minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word ("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word ("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word ("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word ("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word ("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word ("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word ("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word ("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word ("Come here.", "әnni'nem", R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);

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
