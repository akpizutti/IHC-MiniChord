//
// Created by akpiz on 6/20/2023.
//

#include "Oscillator.h"
#include <math.h>
#include <android/log.h>



void Oscillator::setSampleRate(int32_t sampleRate) {
    phaseIncrement_ = (TWO_PI * TUNING) / (double) sampleRate;
    nyquist = float(sampleRate / 2.0);
    initializePhases();


}

void Oscillator::setWaveOn(bool isWaveOn, int notes[]) {
    isWaveOn_.store(isWaveOn);
    for(int i=0; i< MAX_POLYPHONY; i++){
        notes_[i]= notes[i];
    }
}

void Oscillator::render(float *audioData, int32_t numFrames) {

    if (!isWaveOn_.load()){
        for(int i = 0; i < MAX_POLYPHONY; i++) {
            phase_[i] = 0.0;
            for(int j=0; j<7; j++){
                //code taken from https://stackoverflow.com/questions/7560114/random-number-c-in-some-range
                std::random_device rd; // obtain a random number from hardware
                std::mt19937 gen(rd()); // seed the generator
                std::uniform_int_distribution<> distr(TWO_PI*-1, TWO_PI); // define the range
                supersaw_phase_[i][j] = distr(gen); //phase randomization so that supersaw sounds good
            }
        }

    }

    for (int i = 0; i < numFrames; i++) {

        if (isWaveOn_.load()) {

            // Calculates the next sample value for the sine wave.
            //audioData[i] = (float) (sin(phase_) * AMPLITUDE);

            audioData[i] = 0.0;
            float currentSample = 0.0;


            for(int k=0; k<MAX_POLYPHONY; k++){
                if(notes_[k] >= NOTE_C_0){

                    //simple sine wave
                    //currentSample += (float) (sin(phase_[k] * 16)) * AMPLITUDE ;

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

                    //single sawtooth
                    //currentSample += phase_[k] * decibel2linear(-6.0);

                    //supersaw
                    /*for(int j=0;j<UNISON_VOICES; j++){
                        currentSample += supersaw_phase_[k][j] * decibel2linear(-18.0);

                        supersaw_phase_[k][j] += ( 2* phaseIncrement_ + ((j-(j/2))*0.0004)) * ((pow(double(2.0),double(double(notes_[k])/12))));
                        if (supersaw_phase_[k][j] > TWO_PI)
                            supersaw_phase_[k][j] -= TWO_PI;
                    }*/

                    currentSample = currentSample * decibel2linear(-6.0); //attenuate the signal to avoid clipping
                    audioData[i] += currentSample;

                    // Increments the phase, handling wrap around.
                    // f = f0 * (2^(n/12))
                    phase_[k] += (phaseIncrement_ ) * ((pow(double(2.0),double(double(notes_[k])/12))));
                    //phase_[k] += phase_[k] / 4;
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

void Oscillator::initializePhases() {
    for(int i=0;i<MAX_POLYPHONY;i++){
        phase_[i] = 0.0;

        for(int j=0; j<7; j++){
            supersaw_phase_[i][j] = 0.0;
        }
    }
}