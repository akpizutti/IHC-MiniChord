//
// Created by akpiz on 6/20/2023.
//

#ifndef WAVEMAKER_AUDIOENGINE_H
#define WAVEMAKER_AUDIOENGINE_H


#include <aaudio/AAudio.h>
#include "Oscillator.h"

#define OSCILLATORS 6

class AudioEngine {
public:
    bool start();
    void stop();
    void restart();
    void setToneOn(bool isToneOn, int degree);


private:
    Oscillator oscillator;
    AAudioStream *stream_;
};

#endif //WAVEMAKER_AUDIOENGINE_H
