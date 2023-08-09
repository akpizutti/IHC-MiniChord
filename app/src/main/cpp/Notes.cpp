//
// Created by akpiz on 6/22/2023.
//
#include "Notes.h"

int Notes::scaleTranspose;
int Notes::chordModes[7];
std::array<int,MAX_POLYPHONY> Notes::playingNotes;

void Notes::buildChords(int degree, int *notes) {
    //int notes[MAX_POLYPHONY] = {NOTE_NULL, NOTE_NULL, NOTE_NULL, NOTE_NULL, NOTE_NULL, NOTE_NULL, };
    switch(degree){
        case 1: //C maj
            notes[0] = NOTE_C_4 + scaleTranspose;
            notes[1] = NOTE_C_4+4 + scaleTranspose;
            notes[2] = NOTE_C_4+7 + scaleTranspose;
            break;
        case 2: //D min
            notes[0] = NOTE_D_4;
            notes[1] = NOTE_D_4+3 + scaleTranspose;
            notes[2] = NOTE_D_4+7 + scaleTranspose;
            break;
        case 3: //E min
            notes[0] = NOTE_E_4;
            notes[1] = NOTE_E_4+3 + scaleTranspose;
            notes[2] = NOTE_E_4+7 + scaleTranspose;
            break;
        case 4: //F maj
            notes[0] = NOTE_F_4;
            notes[1] = NOTE_F_4+4 + scaleTranspose;
            notes[2] = NOTE_F_4+7 + scaleTranspose;
            break;
        case 5: //G maj
            notes[0] = NOTE_G_4;
            notes[1] = NOTE_G_4+4 + scaleTranspose;
            notes[2] = NOTE_G_4+7 + scaleTranspose;
            break;
        case 6: //A min
            notes[0] = NOTE_A_4;
            notes[1] = NOTE_A_4+3 + scaleTranspose;
            notes[2] = NOTE_A_4+7 + scaleTranspose;
            break;
        case 7: //B dim
            notes[0] = NOTE_B_4;
            notes[1] = NOTE_B_4+3 + scaleTranspose;
            notes[2] = NOTE_B_4+6 + scaleTranspose;
            break;
        default:
            break;
    }
    return;
}

int Notes::getRootFromDegree(int degree) {
    int transpose =0;

    //TODO: generalizar para todo tipo de escala
    //válido apenas para escalas maiores
    switch(degree){
        case 1:
            transpose =0;
            break;
        case 2:
            transpose =2;
            break;
        case 3:
            transpose =4;
            break;
        case 4:
            transpose =5;
            break;
        case 5:
            transpose =7;
            break;
        case 6:
            transpose =9;
            break;
        case 7:
            transpose =11;
            break;
    }

    return NOTE_C_3 + scaleTranspose + transpose;
}

void Notes::nullChord(int* notes){
    for(int i=0; i< MAX_POLYPHONY; i++)
    {
        notes[i] = NOTE_NULL;
    }
}

void Notes::buildChord(int *notes, int root, int voicing) {
    nullChord(notes);
    switch(voicing)
    {
        case chordVoicings::MAJOR:
            buildMajorChord(notes, root);
            break;
        case chordVoicings::MINOR:
            buildMinorChord(notes, root);
            break;
        case chordVoicings::DIM:
            buildDimChord(notes, root);
            break;
        case chordVoicings::MAJ7:
            buildMajor7thChord(notes, root);
            break;
        case chordVoicings::MIN7:
            buildMinor7thChord(notes, root);
            break;
        case chordVoicings::MAJ9:
            buildMajor9thChord(notes, root);
            break;
        case chordVoicings::SUS2:
            buildSuspended2Chord(notes, root);
            break;
        case chordVoicings::SUS4:
            buildSuspended4Chord(notes, root);
            break;
    }
    for(int i=0; i<MAX_POLYPHONY; i++)
    {
        playingNotes[i] = notes[i];
    }

}

void Notes::buildMajorChord(int* notes, int root){
    notes[0] = root;
    notes[1] = root + 4;
    notes[2] = root + 7;
}


void Notes::buildMinorChord(int* notes, int root){
    notes[0] = root;
    notes[1] = root + 3;
    notes[2] = root + 7;
}

void Notes::setScale(int scale){
    scaleTranspose = scale;
}

void Notes::setModes(int *modes){
    for (int i=0; i<7; i++){
        chordModes[i] = modes[i];
    }
}

void Notes::setMode(int degree, int mode){
    chordModes[degree-1] = mode;
}

int Notes::getMode(int degree){
    return chordModes[degree-1];
}

void Notes::buildDimChord(int *notes, int root) {
    notes[0] = root;
    notes[1] = root + 3;
    notes[2] = root + 6;
}

void Notes::buildMajor7thChord(int *notes, int root) {
    notes[0] = root;
    notes[1] = root + 4;
    notes[2] = root + 7;
    notes[3] = root + 11;
}

void Notes::buildMinor7thChord(int *notes, int root) {
    notes[0] = root;
    notes[1] = root + 3;
    notes[2] = root + 7;
    notes[3] = root + 10;
}

void Notes::buildMajor9thChord(int *notes, int root) {
    notes[0] = root;
    notes[1] = root + 4;
    notes[2] = root + 7;
    notes[3] = root + 11;
    notes[4] = root + 14;
}

void Notes::buildSuspended2Chord(int* notes, int root){
    notes[0] = root;
    notes[1] = root + 2;
    notes[2] = root + 7;
}


void Notes::buildSuspended4Chord(int* notes, int root){
    notes[0] = root;
    notes[1] = root + 5;
    notes[2] = root + 7;
}
