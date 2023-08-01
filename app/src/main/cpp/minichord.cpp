// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("minichord");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("minichord")
//      }
//    }
#include <jni.h>
#include <cstdlib>
#include <vector>
#include <android/input.h>
#include "AudioEngine.h"
#include "Notes.h"

static AudioEngine *audioEngine = new AudioEngine();


extern "C"
JNIEXPORT void JNICALL
Java_com_example_minichord_SecondFragment_touchEvent(JNIEnv *env, jobject thiz, jint action,
                                                     jint degree) {
    switch (action) {
        case AMOTION_EVENT_ACTION_DOWN:
            audioEngine->setToneOn(true,degree);
            break;
        case AMOTION_EVENT_ACTION_UP:
            audioEngine->setToneOn(false, 0);
            break;
        default:
            break;
    }
}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_minichord_SecondFragment_startEngine(JNIEnv *env, jobject thiz) {
    audioEngine->start();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_minichord_SecondFragment_stopEngine(JNIEnv *env, jobject thiz) {
    audioEngine->stop();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_minichord_SecondFragment_stopSound(JNIEnv *env, jobject thiz) {
    audioEngine->setToneOn(false, 0);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_minichord_Notes_c_1setScale(JNIEnv *env, jclass thiz, jint scale) {
    Notes::setScale(scale);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_minichord_Notes_c_1setMode(JNIEnv *env, jclass clazz, jint degree, jint mode) {
    Notes::setMode(degree,mode);
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_minichord_Notes_c_1getPlayingNote(JNIEnv *env, jclass clazz, jint pos) {
    return Notes::playingNotes[pos];
}