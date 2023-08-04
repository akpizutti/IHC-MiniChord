//
// Created by akpiz on 6/20/2023.
//

#ifndef WAVEMAKER_OSCILLATOR_H
#define WAVEMAKER_OSCILLATOR_H


#include <atomic>
#include <stdint.h>
#include <android/log.h>
#include "Notes.h"
#include <random>

#define TWO_PI (3.14159 * 2)
#define AMPLITUDE 0.2
#define TUNING 440.0
#define NOTE 3.0
#define ORGAN_HARMONICS 4
#define UNISON_VOICES 7
#define DETUNE 0.1

class Oscillator {
public:
    void setWaveOn(bool isWaveOn, int notes[]);
    void setSampleRate(int32_t sampleRate);
    void render(float *audioData, int32_t numFrames);

private:
    std::atomic<bool> isWaveOn_{false};
    double phase_[MAX_POLYPHONY] ;
    double phaseIncrement_ = 0.0;
    int notes_[MAX_POLYPHONY] = {NOTE_NULL,NOTE_NULL,NOTE_NULL,NOTE_NULL,NOTE_NULL,NOTE_NULL};
    float nyquist;
    float decibel2linear(float decibels);

    double supersaw_phase_[MAX_POLYPHONY][UNISON_VOICES];
    void initializePhases();
};


#endif //WAVEMAKER_OSCILLATOR_H
