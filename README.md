# MiniChord
This project was created to practice concepts of Human-Computer Interaction. Its goal is to aid in learning music theory by allowing you to easily play chords, listen to them in real time and see what notes make they are composed of.

## Technical Details
The project was built on Android Studio Iguana, tested on Android 13 and 14. The interface was built in Java, while the audio engine and notes were built in C++ using the Java Native Interface (JNI) framework.

## Interface
The first screen lets you choose a scale:
![Screenshot_20240817_230331_MiniChord](https://github.com/user-attachments/assets/2a5e0dde-adca-497d-b412-83f36b330d42)
The keyboard display shows which notes make up the selected scale.

Press next and you are taken to the Play section:

![Screenshot_20240817_230346_MiniChord](https://github.com/user-attachments/assets/ae8cfea8-a752-4c07-96ac-0be179b30d36)
The buttons along the bottom of the screen play the respective chords when they are pressed. The selectors above the buttons allow you to change the form of each chord, while respecting the scale you are in (mostly). The keyboard display shows which notes are being played when you press a chord button.

Both screens have help buttons that explain what each component does:
![Screenshot_20240817_230339_MiniChord](https://github.com/user-attachments/assets/bff90a6e-edc0-4587-ab83-d7d279def18b)

![Screenshot_20240817_230350_MiniChord](https://github.com/user-attachments/assets/4c2f4800-c30e-4d20-baed-61a73b4bfcca)

