//
// Created by akpiz on 6/21/2023.
//

#ifndef WAVEMAKER_NOTES_H
#define WAVEMAKER_NOTES_H

#include<cstdlib>
#include<array>
#include <android/log.h>

#define MAX_POLYPHONY 6

#define NOTE_NULL -100
#define OCTAVE 12
#define STEP 2

#define NOTE_C_0 -57
#define NOTE_C_SHARP_0 -56
#define NOTE_D_0 -55
#define NOTE_D_SHARP_0 -54
#define NOTE_E_0 -53
#define NOTE_F_0 -52
#define NOTE_F_SHARP_0 -51
#define NOTE_G_0 -50
#define NOTE_G_SHARP_0 -49
#define NOTE_A_0 -48
#define NOTE_A_SHARP_0 -47
#define NOTE_B_0 -46
#define NOTE_C_1 -45
#define NOTE_C_SHARP_1 -44
#define NOTE_D_1 -43
#define NOTE_D_SHARP_1 -42
#define NOTE_E_1 -41
#define NOTE_F_1 -40
#define NOTE_F_SHARP_1 -39
#define NOTE_G_1 -38
#define NOTE_G_SHARP_1 -37
#define NOTE_A_1 -36
#define NOTE_A_SHARP_1 -35
#define NOTE_B_1 -34
#define NOTE_C_2 -33
#define NOTE_C_SHARP_2 -32
#define NOTE_D_2 -31
#define NOTE_D_SHARP_2 -30
#define NOTE_E_2 -29
#define NOTE_F_2 -28
#define NOTE_F_SHARP_2 -27
#define NOTE_G_2 -26
#define NOTE_G_SHARP_2 -25
#define NOTE_A_2 -24
#define NOTE_A_SHARP_2 -23
#define NOTE_B_2 -22
#define NOTE_C_3 -21
#define NOTE_C_SHARP_3 -20
#define NOTE_D_3 -19
#define NOTE_D_SHARP_3 -18
#define NOTE_E_3 -17
#define NOTE_F_3 -16
#define NOTE_F_SHARP_3 -15
#define NOTE_G_3 -14
#define NOTE_G_SHARP_3 -13
#define NOTE_A_3 -12
#define NOTE_A_SHARP_3 -11
#define NOTE_B_3 -10
#define NOTE_C_4 -9
#define NOTE_C_SHARP_4 -8
#define NOTE_D_4 -7
#define NOTE_D_SHARP_4 -6
#define NOTE_E_4 -5
#define NOTE_F_4 -4
#define NOTE_F_SHARP_4 -3
#define NOTE_G_4 -2
#define NOTE_G_SHARP_4 -1
#define NOTE_A_4 0
#define NOTE_A_SHARP_4 1
#define NOTE_B_4 2
#define NOTE_C_5 3
#define NOTE_C_SHARP_5 4
#define NOTE_D_5 5
#define NOTE_D_SHARP_5 6
#define NOTE_E_5 7
#define NOTE_F_5 8
#define NOTE_F_SHARP_5 9
#define NOTE_G_5 10
#define NOTE_G_SHARP_5 11
#define NOTE_A_5 12
#define NOTE_A_SHARP_5 13
#define NOTE_B_5 14
#define NOTE_C_6 15
#define NOTE_C_SHARP_6 16
#define NOTE_D_6 17
#define NOTE_D_SHARP_6 18
#define NOTE_E_6 19
#define NOTE_F_6 20
#define NOTE_F_SHARP_6 21
#define NOTE_G_6 22
#define NOTE_G_SHARP_6 23
#define NOTE_A_6 24
#define NOTE_A_SHARP_6 25
#define NOTE_B_6 26

class Notes {
        private:
            static int scaleTranspose;
            static int chordModes[7];



        public:
            static std::array<int,MAX_POLYPHONY> playingNotes;

            //este enum NECESSITA estar EXATAMENTE igual ao declarado na Notes.java
            enum chordVoicings{
                MAJOR, MINOR, DIM, MAJ7, MIN7, MAJ9
            };
            static void buildChords(int degree, int *notes);
            static void buildChord(int *notes, int root, int voicing);
            static void setScale(int scale);
            static void setModes(int *modes);
            static void setMode(int degree, int mode);
            static int getMode(int degree);
            static int getRootFromDegree(int degree);
            static void nullChord(int* notes);
            static void buildMajorChord(int* notes, int root);
            static void buildMinorChord(int* notes, int root);
            static void buildDimChord(int* notes, int root);
            static void buildMajor7thChord(int* notes, int root);
            static void buildMajor9thChord(int* notes, int root);
            static void buildMinor7thChord(int* notes, int root);

};

#endif //WAVEMAKER_NOTES_H
