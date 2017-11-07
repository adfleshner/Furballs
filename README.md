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
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
    implementation 'com.android.support:appcompat-v7:26.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    implementation 'com.github.chrisbanes:PhotoView:2.1.3'
    implementation 'com.android.support:recyclerview-v7:26.0.0'
    implementation 'com.github.bumptech.glide:glide:4.3.1'
    kapt 'com.github.bumptech.glide:compiler:4.3.1' 
    implementation 'com.github.smart-fun:XmlToJson:1.4.1'
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
- [ ] Show image details
- [ ] Share and save image
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


<img src="https://github.com/adfleshner/Furballs/blob/master/art/ImagesFragment.png?raw=true" width="216" height="394" />
