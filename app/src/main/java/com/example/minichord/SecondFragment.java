package com.example.minichord;

import static java.lang.Math.abs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.minichord.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    static {
        System.loadLibrary("minichord");
    }

    private native void touchEvent(int action, int degree);
    private native void startEngine();
    private native void stopEngine();
    private native void stopSound();



    private FragmentSecondBinding binding;
    private Spinner spinner_bt1;
    private Spinner spinner_bt2;
    private Spinner spinner_bt3;
    private Spinner spinner_bt4;
    private Spinner spinner_bt5;
    private Spinner spinner_bt6;
    private Spinner spinner_bt7;

    private ImageView isPlaying_C;
    private ImageView isPlaying_C_Sharp;
    private ImageView isPlaying_D;
    private ImageView isPlaying_D_Sharp;
    private ImageView isPlaying_E;
    private ImageView isPlaying_F;
    private ImageView isPlaying_F_Sharp;
    private ImageView isPlaying_G;
    private ImageView isPlaying_G_Sharp;
    private ImageView isPlaying_A;
    private ImageView isPlaying_A_Sharp;
    private ImageView isPlaying_B;

    private Button helpOpenButton;
    private Button helpCloseButton;

    private TextView keyboardTextHelp;
    private TextView selectorTextHelp;
    private TextView buttonsTextHelp;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);

        spinner_bt1 = (Spinner) binding.spinnerBt1;
        List<String> spinner_bt1Value = new ArrayList<String>(Arrays.asList("Major", "Maj7", "Maj9"));
        spinner_bt1.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinner_bt1Value));
        spinner_bt1.setOnItemSelectedListener(this);

        spinner_bt2 = (Spinner) binding.spinnerBt2;
        List<String> spinner_bt2Value = new ArrayList<String>(Arrays.asList("Minor", "Min7"));
        spinner_bt2.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinner_bt2Value));
        spinner_bt2.setOnItemSelectedListener(this);

        spinner_bt3 = (Spinner) binding.spinnerBt3;
        List<String> spinner_bt3Value = new ArrayList<String>(Arrays.asList("Minor", "Min7"));
        spinner_bt3.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinner_bt3Value));
        spinner_bt3.setOnItemSelectedListener(this);

        spinner_bt4 = (Spinner) binding.spinnerBt4;
        List<String> spinner_bt4Value = new ArrayList<String>(Arrays.asList("Major", "Maj7", "Maj9"));
        spinner_bt4.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinner_bt4Value));
        spinner_bt4.setOnItemSelectedListener(this);

        spinner_bt5 = (Spinner) binding.spinnerBt5;
        List<String> spinner_bt5Value = new ArrayList<String>(Arrays.asList("Major", "Maj7", "Maj9"));
        spinner_bt5.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinner_bt5Value));
        spinner_bt5.setOnItemSelectedListener(this);

        spinner_bt6 = (Spinner) binding.spinnerBt6;
        List<String> spinner_bt6Value = new ArrayList<String>(Arrays.asList("Minor", "Min7"));
        spinner_bt6.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinner_bt6Value));
        spinner_bt6.setOnItemSelectedListener(this);

        spinner_bt7 = (Spinner) binding.spinnerBt7;
        List<String> spinner_bt7Value = new ArrayList<String>(Arrays.asList("Dim"));
        spinner_bt7.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinner_bt7Value));
        spinner_bt7.setOnItemSelectedListener(this);

        isPlaying_C = binding.isPlayingC;
        isPlaying_C_Sharp = binding.isPlayingCSharp;
        isPlaying_D = binding.isPlayingD;
        isPlaying_D_Sharp = binding.isPlayingDSharp;
        isPlaying_E = binding.isPlayingE;
        isPlaying_F = binding.isPlayingF;
        isPlaying_F_Sharp = binding.isPlayingFSharp;
        isPlaying_G = binding.isPlayingG;
        isPlaying_G_Sharp = binding.isPlayingGSharp;
        isPlaying_A = binding.isPlayingA;
        isPlaying_A_Sharp = binding.isPlayingASharp;
        isPlaying_B = binding.isPlayingB;

        setAllKeysOFF();

        helpOpenButton = binding.helpOpenButton;
        helpCloseButton = binding.helpCloseButton;

        keyboardTextHelp = binding.keyboardTextHelp;
        selectorTextHelp = binding.selectorTextHelp;
        buttonsTextHelp = binding.buttonsTextHelp;

        closeHelpOverlay();

        Notes.getNotes();

        return binding.getRoot();

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });*/
        binding.buttonSecond.setOnTouchListener((v, event) -> {
            //Log.d("botao", "onclick" + event.getAction());
            touchEvent(event.getAction(),1);
            Notes.getNotes();
            Log.d("NOTES", "Playing notes: " + Arrays.toString(Notes.getNotesArray()));
            updatePlayingNotesDisplay();


            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // PRESSED
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    setAllKeysOFF();
                case MotionEvent.ACTION_CANCEL:
                    // RELEASED
                    break;
            }
            return false;
        });
        binding.buttonSecond2.setOnTouchListener((v, event) -> {
            //Log.d("botao", "onclick" + event.getAction());
            touchEvent(event.getAction(),2);
            Notes.getNotes();
            Log.d("NOTES", "Playing notes: " + Arrays.toString(Notes.getNotesArray()));
            updatePlayingNotesDisplay();

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // PRESSED
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    setAllKeysOFF();
                case MotionEvent.ACTION_CANCEL:
                    // RELEASED
                    break;
            }
            return false;
        });
        binding.buttonSecond3.setOnTouchListener((v, event) -> {
            //Log.d("botao", "onclick" + event.getAction());
            touchEvent(event.getAction(),3);
            Notes.getNotes();
            Log.d("NOTES", "Playing notes: " + Arrays.toString(Notes.getNotesArray()));
            updatePlayingNotesDisplay();

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // PRESSED
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    setAllKeysOFF();
                case MotionEvent.ACTION_CANCEL:
                    // RELEASED
                    break;
            }
            return false;
        });
        binding.buttonSecond4.setOnTouchListener((v, event) -> {
            //Log.d("botao", "onclick" + event.getAction());
            touchEvent(event.getAction(),4);
            Notes.getNotes();
            Log.d("NOTES", "Playing notes: " + Arrays.toString(Notes.getNotesArray()));
            updatePlayingNotesDisplay();

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // PRESSED
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    setAllKeysOFF();
                case MotionEvent.ACTION_CANCEL:
                    // RELEASED
                    break;
            }
            return false;
        });
        binding.buttonSecond5.setOnTouchListener((v, event) -> {
            //Log.d("botao", "onclick" + event.getAction());
            touchEvent(event.getAction(),5);
            Notes.getNotes();
            Log.d("NOTES", "Playing notes: " + Arrays.toString(Notes.getNotesArray()));
            updatePlayingNotesDisplay();

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // PRESSED
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    setAllKeysOFF();
                case MotionEvent.ACTION_CANCEL:
                    // RELEASED
                    break;
            }
            return false;
        });
        binding.buttonSecond6.setOnTouchListener((v, event) -> {
            //Log.d("botao", "onclick" + event.getAction());
            touchEvent(event.getAction(),6);
            Notes.getNotes();
            Log.d("NOTES", "Playing notes: " + Arrays.toString(Notes.getNotesArray()));
            updatePlayingNotesDisplay();

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // PRESSED
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    setAllKeysOFF();
                case MotionEvent.ACTION_CANCEL:
                    // RELEASED
                    break;
            }
            return false;
        });
        binding.buttonSecond7.setOnTouchListener((v, event) -> {
            //Log.d("botao", "onclick" + event.getAction());
            touchEvent(event.getAction(),7);
            Notes.getNotes();
            Log.d("NOTES", "Playing notes: " + Arrays.toString(Notes.getNotesArray()));
            updatePlayingNotesDisplay();

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // PRESSED
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    setAllKeysOFF();
                case MotionEvent.ACTION_CANCEL:
                    // RELEASED
                    break;
            }
            return false;
        });

        binding.helpOpenButton.setOnTouchListener((v, event) -> {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    openHelpOverlay();
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                case MotionEvent.ACTION_CANCEL:
                    // RELEASED
                    break;
            }
            return false;
        });

        binding.helpCloseButton.setOnTouchListener((v, event) -> {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    closeHelpOverlay();
                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                case MotionEvent.ACTION_CANCEL:
                    // RELEASED
                    break;
            }
            return false;
        });

        startEngine();
    }

    private void openHelpOverlay(){
        helpCloseButton.setVisibility(View.VISIBLE);

        keyboardTextHelp.setVisibility(View.VISIBLE);
        selectorTextHelp.setVisibility(View.VISIBLE);
        buttonsTextHelp.setVisibility(View.VISIBLE);
    }
    private void closeHelpOverlay(){
        helpCloseButton.setVisibility(View.GONE);

        keyboardTextHelp.setVisibility(View.INVISIBLE);
        selectorTextHelp.setVisibility(View.INVISIBLE);
        buttonsTextHelp.setVisibility(View.INVISIBLE);
    }

    private void setAllKeysOFF(){
        isPlaying_C.setVisibility(View.INVISIBLE);
        isPlaying_C_Sharp.setVisibility(View.INVISIBLE);
        isPlaying_D.setVisibility(View.INVISIBLE);
        isPlaying_D_Sharp.setVisibility(View.INVISIBLE);
        isPlaying_E.setVisibility(View.INVISIBLE);
        isPlaying_F.setVisibility(View.INVISIBLE);
        isPlaying_F_Sharp.setVisibility(View.INVISIBLE);
        isPlaying_G.setVisibility(View.INVISIBLE);
        isPlaying_G_Sharp.setVisibility(View.INVISIBLE);
        isPlaying_A.setVisibility(View.INVISIBLE);
        isPlaying_A_Sharp.setVisibility(View.INVISIBLE);
        isPlaying_B.setVisibility(View.INVISIBLE);
    }

    private void updatePlayingNotesDisplay()
    {
        setAllKeysOFF();
        for(int i=0; i<Notes.MAX_POLYPHONY;i++){
            if(Notes.playingNotes[i] != -100){
                switch(Notes.playingNotes[i]){
                    case -48:
                    case -36:
                    case -24:
                    case -12:
                    case 0:
                    case 12:
                    case 24:
                        isPlaying_A.setVisibility(View.VISIBLE);
                        break;
                    case -47:
                    case -35:
                    case -23:
                    case -11:
                    case 1:
                    case 13:
                    case 25:
                        isPlaying_A_Sharp.setVisibility(View.VISIBLE);
                        break;
                    case -46:
                    case -34:
                    case -22:
                    case -10:
                    case 2:
                    case 14:
                    case 26:
                        isPlaying_B.setVisibility(View.VISIBLE);
                        break;
                    case -57:
                    case -45:
                    case -33:
                    case -21:
                    case -9:
                    case 3:
                    case 15:
                        isPlaying_C.setVisibility(View.VISIBLE);
                        break;
                    case -56:
                    case -44:
                    case -32:
                    case -20:
                    case -8:
                    case 4:
                    case 16:
                        isPlaying_C_Sharp.setVisibility(View.VISIBLE);
                        break;
                    case -55:
                    case -43:
                    case -31:
                    case -19:
                    case -7:
                    case 5:
                    case 17:
                        isPlaying_D.setVisibility(View.VISIBLE);
                        break;
                    case -54:
                    case -42:
                    case -30:
                    case -18:
                    case -6:
                    case 6:
                    case 18:
                        isPlaying_D_Sharp.setVisibility(View.VISIBLE);
                        break;
                    case -53:
                    case -41:
                    case -29:
                    case -17:
                    case -5:
                    case 7:
                    case 19:
                        isPlaying_E.setVisibility(View.VISIBLE);
                        break;
                    case -52:
                    case -40:
                    case -28:
                    case -16:
                    case -4:
                    case 8:
                    case 20:
                        isPlaying_F.setVisibility(View.VISIBLE);
                        break;
                    case -51:
                    case -39:
                    case -27:
                    case -15:
                    case -3:
                    case 9:
                    case 21:
                        isPlaying_F_Sharp.setVisibility(View.VISIBLE);
                        break;
                    case -50:
                    case -38:
                    case -26:
                    case -14:
                    case -2:
                    case 10:
                    case 22:
                        isPlaying_G.setVisibility(View.VISIBLE);
                        break;
                    case -49:
                    case -37:
                    case -25:
                    case -13:
                    case -1:
                    case 11:
                    case 23:
                        isPlaying_G_Sharp.setVisibility(View.VISIBLE);
                        break;

                }
            }
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        /*selectedScale = dropDown1.getSelectedItem().toString();
        Notes.setScale(selectedScale);
        Log.d("SCALE_SET", Integer.toString(Notes.getScale()));*/

        Notes.setChordMode(1, spinner_bt1.getSelectedItem().toString());
        Notes.setChordMode(2, spinner_bt2.getSelectedItem().toString());
        Notes.setChordMode(3, spinner_bt3.getSelectedItem().toString());
        Notes.setChordMode(4, spinner_bt4.getSelectedItem().toString());
        Notes.setChordMode(5, spinner_bt5.getSelectedItem().toString());
        Notes.setChordMode(6, spinner_bt6.getSelectedItem().toString());
        Notes.setChordMode(7, spinner_bt7.getSelectedItem().toString());
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public void onDestroyView() {
        stopEngine();
        super.onDestroyView();
        binding = null;
    }

}