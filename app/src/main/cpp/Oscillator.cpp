//
// Created by akpiz on 6/20/2023.
//

#include "Oscillator.h"
#include <math.h>

#define TWO_PI (3.14159 * 2)
#define AMPLITUDE 0.2
#define TUNING 440.0
#define NOTE 3.0
#define ORGAN_HARMONICS 4

void Oscillator::setSampleRate(int32_t sampleRate) {
    phaseIncrement_ = (TWO_PI * TUNING) / (double) sampleRate;
    nyquist = float(sampleRate / 2.0);
}

void Oscillator::setWaveOn(bool isWaveOn, int notes[]) {
    isWaveOn_.store(isWaveOn);
    for(int i=0; i< MAX_POLYPHONY; i++){
        notes_[i]= notes[i];
    }
}

void Oscillator::render(float *audioData, int32_t numFrames) {

    if (!isWaveOn_.load()){
        for(int i = 0; i < MAX_POLYPHONY; i++)
            phase_[i] = 0.0;
    }

    for (int i = 0; i < numFrames; i++) {

        if (isWaveOn_.load()) {

            // Calculates the next sample value for the sine wave.
            //audioData[i] = (float) (sin(phase_) * AMPLITUDE);

            audioData[i] = 0.0;
            float currentSample = 0.0;
            // Increments the phase, handling wrap around.
            // f = f0 * (2^(n/12))

            for(int k=0; k<MAX_POLYPHONY; k++){
                if(notes_[k] >= NOTE_C_0){

                    //simple sine wave
                    /*currentSample += (float) (sin(phase_[k] * 2)) * AMPLITUDE ;*/

                    //tonewheel organ sound
                    for(int j=0; j < ORGAN_HARMONICS; j++) {
                        currentSample += (float) (sin((phase_[k] * pow(2, j)) ) * AMPLITUDE *
                                                  decibel2linear(j*-3.0));
                    }

                    //attempting to recreate a sound I stumbled upon in FL Studio's 3xOsc that I thought kinda resembles a Rhodes.
                    /*
                    //two sine waves added together:
                    currentSample += (float) (sin((phase_[k])) *2.0 ) * AMPLITUDE;
                    currentSample += (float) (sin((phase_[k]))   ) * decibel2linear(-4.0) * AMPLITUDE;
                    //then multiplied by another sine, also known as amplitude modulation (AM):
                    currentSample = currentSample *( (float) sin((phase_[k]))  * decibel2linear(-4.0) );
                    */

                    //another sound I stumbled upon while writing this code, idk how, but it sounds like a sawtooth
                    /*currentSample += (float) sin((phase_[k]) /2 ) * AMPLITUDE;
                    currentSample += (float) sin((phase_[k]) /4 ) * AMPLITUDE * decibel2linear(-4);*/

                    audioData[i] += currentSample * decibel2linear(-3.0); //attenuate the signal so it doesn't clip

                    phase_[k] += phaseIncrement_ * ((pow(double(2.0),double(double(notes_[k])/12))));
                    if (phase_[k] > TWO_PI) phase_[k] -= TWO_PI;
                }
            }



        } else {
            // Outputs silence by setting sample value to zero.
            audioData[i] = 0;
        }
    }
}

float Oscillator::decibel2linear(float decibels){
    return pow(float(10.0),float(decibels/10.0));
}