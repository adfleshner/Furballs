# Furballs
Furballs is a Simple application that uses the DogAPI(https://dog.ceo/dog-api/) to pull down a list of dog images.

Furballs is Written in Kotlin.

Currently very bare bones.

- Uses retrofit2 and Picasso for Networking and ImageLoading.
- Uses retrofit2Gson Convertor for Json Serialization and Deserialization.
- Uses Chris Barnes PhotoView for the ImageActivity Image (for easy gestures).

### Libraries in project
```gradle
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
    implementation 'com.android.support:appcompat-v7:26.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation 'com.github.chrisbanes:PhotoView:2.1.3'
    implementation 'com.android.support:recyclerview-v7:26.0.0'
    implementation 'com.squareup.picasso:picasso:2.5.2'
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
    implementation "com.squareup.retrofit2:adapter-rxjava2:2.3.0"
    implementation "io.reactivex.rxjava2:rxandroid:2.0.1"
    implementation 'com.google.dagger:dagger:2.11'
    kapt 'com.google.dagger:dagger-compiler:2.11'
    provided 'org.glassfish:javax.annotation:10.0-b28'
```

### CHECKLIST
- [x] Start Project
- [x] Make Simple Web call to DogAPI
- [x] Display Images in a list
- [x] Show full image when user clicks on image in list
- [x] DAGGER 2
- [x] Handle Config Changes
- [ ] MVP
- [ ] Persistence
- [ ] Different Breeds
- [ ] fancy animations
- [ ] Lots More

<img src="https://github.com/adfleshner/Furballs/blob/master/art/ImagesFragment.png?raw=true" width="216" height="394" />
