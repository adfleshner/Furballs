# Furballs
Furballs is a Simple application that uses the DogAPI(https://dog.ceo/dog-api/) and CatAPI(http://thecatapi.com/) to pull down a list of cat and dog images.

Furballs is Written in Kotlin.

Currently (not as) bare bones (as before).

- Uses retrofit2 and Glide for Networking and ImageLoading.
- Uses XmlToJson to convert the xml response in json so that the XMLtoGsonConverterFactory can do its magic.
- Uses retrofit2Gson Convertor for Json Serialization and Deserialization.
- Uses Chris Barnes PhotoView for the ImageActivity Image (for easy gestures).

### Libraries in project
```gradle
        implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
        //AndroidX Libs
        implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.0.0'
        implementation 'androidx.appcompat:appcompat:1.1.0'
        implementation 'androidx.recyclerview:recyclerview:1.1.0'
        implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
        implementation 'com.google.android.material:material:1.1.0'
        testImplementation 'junit:junit:4.13'
        androidTestImplementation 'androidx.test.ext:junit:1.1.1'
        androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
        //Retrofit
        implementation 'com.squareup.retrofit2:retrofit:2.7.1'
        implementation 'com.squareup.retrofit2:converter-gson:2.7.1'
        implementation 'com.squareup.retrofit2:adapter-rxjava2:2.7.1'
        //For xml to json conversion
        implementation 'com.github.smart-fun:XmlToJson:1.4.5'
        //OkHttp
        implementation 'com.squareup.okhttp3:okhttp:4.4.0'
        implementation 'com.squareup.okio:okio:2.4.3'
        implementation 'com.squareup.okhttp3:logging-interceptor:4.4.0'
        //ImageLoading
        implementation 'com.github.bumptech.glide:glide:4.11.0'
        kapt 'com.github.bumptech.glide:compiler:4.11.0'
        //Rxandroid
        implementation "io.reactivex.rxjava2:rxandroid:2.0.1"
        //Photo View
        implementation 'com.github.chrisbanes:PhotoView:2.1.3'
        //Dagger 2
        implementation 'com.google.dagger:dagger:2.26'
        kapt 'com.google.dagger:dagger-compiler:2.26'
```

### CHECKLIST
- [x] Start Project
- [x] Make Simple Web call to DogAPI
- [x] Display Images in a list
- [x] Show full image when user clicks on image in list
- [ ] Show image details
- [x] Share and save image
- [ ] favorite image list??? maybe.
- [x] DAGGER 2
- [x] Handle Config Changes
- [x] MVP
- [x] Different breeds(for dogs only)
- [ ] Different categories(for cats only)
- [ ] fancy animations
- [x] write some tests. I have 2 so far :) 
- [x] Add Cats Api (because why not)
- [ ] Lots More


<img src="https://github.com/adfleshner/Furballs/blob/master/art/dogImagesFragment.png?raw=true" width="216" height="394" />
<img src="https://github.com/adfleshner/Furballs/blob/master/art/catImagesFragment.png?raw=true" width="216" height="394" />
