package com.example.minichord;

import android.util.Log;

public class Notes {

    static {
        System.loadLibrary("minichord");
    }

    public static final int MAX_POLYPHONY = 6;

    //este enum NECESSITA estar EXATAMENTE igual ao declarado na Notes.h
    private static enum chordVoicings{
        MAJOR, MINOR, DIM, MAJ7, MIN7, MAJ9, SUS2, SUS4
    }
    private static enum normalizedNoteIDs{ //get a note ID by using normalizedNoteIDs.valueOf("NOTE_NAME").ordinal()
        C, C_SHARP, D, D_SHARP, E, F, F_SHARP, G, G_SHARP, A, A_SHARP, B
    }

    private static int currentScale = 0;
    private static int chordModes[] = {chordVoicings.valueOf("MAJOR").ordinal(),
            chordVoicings.valueOf("MINOR").ordinal(),
            chordVoicings.valueOf("MINOR").ordinal(),
            chordVoicings.valueOf("MAJOR").ordinal(),
            chordVoicings.valueOf("MAJOR").ordinal(),
            chordVoicings.valueOf("MINOR").ordinal(),
            chordVoicings.valueOf("DIM").ordinal(),
    };
    public static int playingNotes[] = {-100,-100,-100,-100,-100,-100};
    private static native void c_setScale(int scale);
    private static native void c_setMode(int degree, int mode);

    public static native int c_getPlayingNote(int pos);
    public static void getNotes(){
        for(int i=0;i<Notes.MAX_POLYPHONY;i++)
        {
            playingNotes[i] = c_getPlayingNote(i);
        }
    }
    public static int[] getNotesArray(){
        return playingNotes;
    }

    public static int getScale(){
        return currentScale;
    }
    public static void setScale(int scale){
        currentScale = scale;
        c_setScale(currentScale);
    }
    public static void setScale(String scale){
        switch(scale){
            case "C":
                currentScale = 0;
                break;
            case "C#":
                currentScale = 1;
                break;
            case "D":
                currentScale = 2;
                break;
            case "D#":
                currentScale = 3;
                break;
            case "E":
                currentScale = 4;
                break;
            case "F":
                currentScale = 5;
                break;
            case "F#":
                currentScale = 6;
                break;
            case "G":
                currentScale = 7;
                break;
            case "G#":
                currentScale = 8;
                break;
            case "A":
                currentScale = 9;
                break;
            case "A#":
                currentScale = 10;
                break;
            case "B":
                currentScale = 11;
                break;
        }
        c_setScale(currentScale);
    }
    public static void setChordMode(int degree, String modeName){
        int mode = 0;
        switch(modeName)
        {
            case "Major":
                mode = chordVoicings.valueOf("MAJOR").ordinal();
                break;
            case "Minor":
                mode = chordVoicings.valueOf("MINOR").ordinal();
                break;
            case "Dim":
                mode = chordVoicings.valueOf("DIM").ordinal();
                break;
            case "Maj7":
                mode = chordVoicings.valueOf("MAJ7").ordinal();
                break;
            case "Min7":
                mode = chordVoicings.valueOf("MIN7").ordinal();
                break;
            case "Maj9":
                mode = chordVoicings.valueOf("MAJ9").ordinal();
                break;
            case "Sus2":
                mode = chordVoicings.valueOf("SUS2").ordinal();
                break;
            case "Sus4":
                mode = chordVoicings.valueOf("SUS4").ordinal();
                break;
        }
        chordModes[degree-1] = mode;
        Log.d("DEBUG_CHORD", "Chamando c_setMode com grau " + degree + " e modo " + mode);
        c_setMode(degree, mode);
    }

    //tá errado, não use essa função
    public static int getNormalizedNoteID(int note){
        int return_id = -100;
        switch(note % 12){
            case 0: return_id = normalizedNoteIDs.valueOf("C").ordinal();
                    break;
            case 1: return_id = normalizedNoteIDs.valueOf("C_SHARP").ordinal();
                break;
            case 2: return_id = normalizedNoteIDs.valueOf("D").ordinal();
                break;
            case 3: return_id = normalizedNoteIDs.valueOf("D_SHARP").ordinal();
                break;
            case 4: return_id = normalizedNoteIDs.valueOf("E").ordinal();
                break;
            case 5: return_id = normalizedNoteIDs.valueOf("F").ordinal();
                break;
            case 6: return_id = normalizedNoteIDs.valueOf("F_SHARP").ordinal();
                break;
            case 7: return_id = normalizedNoteIDs.valueOf("G").ordinal();
                break;
            case 8: return_id = normalizedNoteIDs.valueOf("G_SHARP").ordinal();
                break;
            case 9: return_id = normalizedNoteIDs.valueOf("A").ordinal();
                break;
            case 10: return_id = normalizedNoteIDs.valueOf("A_SHARP").ordinal();
                break;
            case 11: return_id = normalizedNoteIDs.valueOf("B").ordinal();
                break;
        }
        return return_id;
    }


    public static int getScaleFromString(String selectedScale){
        int scaleValue = 0;
        switch(selectedScale){
            case "C":
                scaleValue = 0;
                break;
            case "D":
                scaleValue = 1;
                break;
            case "E":
                scaleValue = 2;
                break;
            case "F":
                scaleValue = 3;
                break;
            case "G":
                scaleValue = 4;
                break;
            case "A":
                scaleValue = 5;
                break;
            case "B":
                scaleValue = 6;
                break;
        }
        return scaleValue;
    }
}
