package com.example.minichord;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.minichord.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.minichord.Notes;

public class FirstFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentFirstBinding binding;
    private Spinner dropDown1, dropDown2;

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

    private String selectedScale;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        dropDown1 = (Spinner) binding.dropDown1;
        List<String> dropDown1Value = new ArrayList<String>(Arrays.asList("C","C#","D","D#","E","F","F#","G","G#","A","A#","B"));
        dropDown1.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dropDown1Value));

        dropDown2 = (Spinner) binding.dropDown2;
        List<String> dropDown2Value = new ArrayList<String>(Arrays.asList("Major", "Minor"));
        dropDown2.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dropDown2Value));

        dropDown1.setOnItemSelectedListener(this);
        dropDown2.setOnItemSelectedListener(this);

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

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

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

    private void updateScaleHighlight(String scale)
    {
        setAllKeysOFF();
        switch(scale){
            case "C":
                isPlaying_C.setVisibility(View.VISIBLE);
                isPlaying_D.setVisibility(View.VISIBLE);
                isPlaying_E.setVisibility(View.VISIBLE);
                isPlaying_F.setVisibility(View.VISIBLE);
                isPlaying_G.setVisibility(View.VISIBLE);
                isPlaying_A.setVisibility(View.VISIBLE);
                isPlaying_B.setVisibility(View.VISIBLE);
                break;
            case "C#":
                isPlaying_C_Sharp.setVisibility(View.VISIBLE);
                isPlaying_D_Sharp.setVisibility(View.VISIBLE);
                isPlaying_F.setVisibility(View.VISIBLE);
                isPlaying_F_Sharp.setVisibility(View.VISIBLE);
                isPlaying_G_Sharp.setVisibility(View.VISIBLE);
                isPlaying_A_Sharp.setVisibility(View.VISIBLE);
                isPlaying_C.setVisibility(View.VISIBLE);
                break;
            case "D":
                isPlaying_D.setVisibility(View.VISIBLE);
                isPlaying_E.setVisibility(View.VISIBLE);
                isPlaying_F_Sharp.setVisibility(View.VISIBLE);
                isPlaying_G.setVisibility(View.VISIBLE);
                isPlaying_A.setVisibility(View.VISIBLE);
                isPlaying_B.setVisibility(View.VISIBLE);
                isPlaying_C_Sharp.setVisibility(View.VISIBLE);
                break;
            case "D#":
                isPlaying_D_Sharp.setVisibility(View.VISIBLE);
                isPlaying_F.setVisibility(View.VISIBLE);
                isPlaying_G.setVisibility(View.VISIBLE);
                isPlaying_G_Sharp.setVisibility(View.VISIBLE);
                isPlaying_A_Sharp.setVisibility(View.VISIBLE);
                isPlaying_C.setVisibility(View.VISIBLE);
                isPlaying_D.setVisibility(View.VISIBLE);
                break;
            case "E":
                isPlaying_E.setVisibility(View.VISIBLE);
                isPlaying_F_Sharp.setVisibility(View.VISIBLE);
                isPlaying_G_Sharp.setVisibility(View.VISIBLE);
                isPlaying_A.setVisibility(View.VISIBLE);
                isPlaying_B.setVisibility(View.VISIBLE);
                isPlaying_C_Sharp.setVisibility(View.VISIBLE);
                isPlaying_D_Sharp.setVisibility(View.VISIBLE);
                break;
            case "F":
                isPlaying_F.setVisibility(View.VISIBLE);
                isPlaying_G.setVisibility(View.VISIBLE);
                isPlaying_A.setVisibility(View.VISIBLE);
                isPlaying_A_Sharp.setVisibility(View.VISIBLE);
                isPlaying_C.setVisibility(View.VISIBLE);
                isPlaying_D.setVisibility(View.VISIBLE);
                isPlaying_E.setVisibility(View.VISIBLE);
                break;
            case "F#":
                isPlaying_F_Sharp.setVisibility(View.VISIBLE);
                isPlaying_G_Sharp.setVisibility(View.VISIBLE);
                isPlaying_A_Sharp.setVisibility(View.VISIBLE);
                isPlaying_B.setVisibility(View.VISIBLE);
                isPlaying_C_Sharp.setVisibility(View.VISIBLE);
                isPlaying_D_Sharp.setVisibility(View.VISIBLE);
                isPlaying_F.setVisibility(View.VISIBLE);
                break;
            case "G":
                isPlaying_G.setVisibility(View.VISIBLE);
                isPlaying_A.setVisibility(View.VISIBLE);
                isPlaying_B.setVisibility(View.VISIBLE);
                isPlaying_C.setVisibility(View.VISIBLE);
                isPlaying_D.setVisibility(View.VISIBLE);
                isPlaying_E.setVisibility(View.VISIBLE);
                isPlaying_F_Sharp.setVisibility(View.VISIBLE);
                break;
            case "G#":
                isPlaying_G_Sharp.setVisibility(View.VISIBLE);
                isPlaying_A_Sharp.setVisibility(View.VISIBLE);
                isPlaying_C.setVisibility(View.VISIBLE);
                isPlaying_C_Sharp.setVisibility(View.VISIBLE);
                isPlaying_D_Sharp.setVisibility(View.VISIBLE);
                isPlaying_F.setVisibility(View.VISIBLE);
                isPlaying_G.setVisibility(View.VISIBLE);
                break;
            case "A":
                isPlaying_A.setVisibility(View.VISIBLE);
                isPlaying_B.setVisibility(View.VISIBLE);
                isPlaying_C_Sharp.setVisibility(View.VISIBLE);
                isPlaying_D.setVisibility(View.VISIBLE);
                isPlaying_E.setVisibility(View.VISIBLE);
                isPlaying_F_Sharp.setVisibility(View.VISIBLE);
                isPlaying_G_Sharp.setVisibility(View.VISIBLE);
                break;
            case "A#":
                isPlaying_A_Sharp.setVisibility(View.VISIBLE);
                isPlaying_C.setVisibility(View.VISIBLE);
                isPlaying_D.setVisibility(View.VISIBLE);
                isPlaying_D_Sharp.setVisibility(View.VISIBLE);
                isPlaying_F.setVisibility(View.VISIBLE);
                isPlaying_G.setVisibility(View.VISIBLE);
                isPlaying_A.setVisibility(View.VISIBLE);
                break;
            case "B":
                isPlaying_B.setVisibility(View.VISIBLE);
                isPlaying_C_Sharp.setVisibility(View.VISIBLE);
                isPlaying_D_Sharp.setVisibility(View.VISIBLE);
                isPlaying_E.setVisibility(View.VISIBLE);
                isPlaying_F_Sharp.setVisibility(View.VISIBLE);
                isPlaying_G_Sharp.setVisibility(View.VISIBLE);
                isPlaying_A_Sharp.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        selectedScale = dropDown1.getSelectedItem().toString();
        Notes.setScale(selectedScale);
        Log.d("SCALE_SET", Integer.toString(Notes.getScale()));
        updateScaleHighlight(selectedScale);
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}