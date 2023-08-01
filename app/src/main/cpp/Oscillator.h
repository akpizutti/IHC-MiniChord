//
// Created by akpiz on 6/20/2023.
//

#ifndef WAVEMAKER_OSCILLATOR_H
#define WAVEMAKER_OSCILLATOR_H


#include <atomic>
#include <stdint.h>
#include "Notes.h"



class Oscillator {
public:
    void setWaveOn(bool isWaveOn, int notes[]);
    void setSampleRate(int32_t sampleRate);
    void render(float *audioData, int32_t numFrames);

private:
    std::atomic<bool> isWaveOn_{false};
    double phase_[MAX_POLYPHONY] = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    double phaseIncrement_ = 0.0;
    int notes_[MAX_POLYPHONY] = {NOTE_NULL,NOTE_NULL,NOTE_NULL,NOTE_NULL,NOTE_NULL,NOTE_NULL};
    float nyquist;
    float decibel2linear(float decibels);
};


#endif //WAVEMAKER_OSCILLATOR_H
