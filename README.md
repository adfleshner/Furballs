# Furballs
Furballs is a Simple application that uses the DogAPI(https://dog.ceo/dog-api/) to pull down a list of dog images.

Furballs is Written in Kotlin.
Currently very bare bones.

Uses retrofit2 and Picasso for Networking and ImageLoading.
Uses retrofit2Gson Convertor for Json Serialization and Deserialization.
Uses Chris Barnes PhotoView for the ImageActivity Image (for easy gestures)

```gradle
    implementation 'com.github.chrisbanes:PhotoView:2.1.3'
    implementation 'com.android.support:recyclerview-v7:26.0.0'
    implementation 'com.squareup.picasso:picasso:2.5.2'
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
    implementation "com.squareup.retrofit2:adapter-rxjava2:2.3.0"
    implementation "io.reactivex.rxjava2:rxandroid:2.0.1"
```
