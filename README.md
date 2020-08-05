# Fruit Store [![Fruit Store](https://img.shields.io/badge/APK-brown?style=for-the-badge&logo=android)](https://github.com/stefanusj/FruitStore/tree/master/apk)

[![platform](https://img.shields.io/badge/platform-Android-yellow.svg?style=flat-square)](https://www.android.com)
[![API](https://img.shields.io/badge/API-21%2B-green?style=flat-square)](https://android-arsenal.com/api?level=21)

**Fruit Store** adalah contoh aplikasi yang mengimplementasikan *Modern Android App Architecture*. Aplikasi ini dibuat dengan menggunakan kotlin dan MVVM pattern serta Architecture Components. 


## Tentang Aplikasi
Aplikasi ini merupakan aplikasi sederhana dimana kita memiliki daftar buah beserta harga. apabila di klik maka akan menampilkan detail dari buah beserta jumlah buah yang ingin dibeli. Aplikasi ini dibuat dengan harapan bisa sebagai contoh dalam pembuatan aplikasi yang lainnya yang juga menggunakan best practice dari google

## Fitur aplikasi:
- Pencarian buah.
- Menyimpan buah favorit.
- Menampilkan daftar buah beserta harga (berdasarkan favorit maupun query pencarian)

## Screenshots

Beberapa screenshot aplikasi

<kbd><img src="https://stefanusj.com/storage/portfolios/fruit-store.jpg"  width="500" height="500"></kbd>


## Architecture
Arsitektur aplikasi ini [**MVVM (Model View View-Model)**](https://developer.android.com/jetpack/docs/guide#recommended-app-arch).
Aplikasi ini mendukung prinsip [**Single Activity**](https://www.youtube.com/watch?v=2k8x8V77CrU).

<kbd><img src="https://developer.android.com/topic/libraries/architecture/images/final-architecture.png"  width="700" height="550"></kbd>

## Library pendukung
- [Kotlin](https://kotlinlang.org/) - Bahasa pemrograman android resmi dari Google.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Kumpulan komponen arsitektur pendukung untuk membuat aplikasi.
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding) - Membuat class untuk setiap layout yang bisa menyimpan dan menampilkan tipe data terkait untuk UI.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Tipe data observable yang mendukung lifecycle android.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - Database android yang mendukung coroutines. 
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Tempat menyimpan dan mengelola data terkait UI. 
  - [Coroutines](https://developer.android.com/topic/libraries/architecture/coroutines) - Asynchronous dan lainnya
  - [Navigation](https://developer.android.com/guide/navigation) - Interaksi navigasi antar fragment yang terdapat dalam aplikasi. 
- [Material Components for Android](https://material.io/develop/android/) - Material Design UI untuk Android.
- [Koin](https://insert-koin.io) - Dependency Injection
- [Retrofit](https://square.github.io/retrofit/) - Pemanggilan API dari web service.
- [GSON](https://github.com/google/gson) - Mengubah objek menjadi JSON dan sebaliknya.
- [GSON Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) - Converter untuk mengubah JSON menjadi GSON dan sebaliknya dari Retrofit.
- [OkHttp3](https://square.github.io/okhttp/) -  Mencegat pemanggilan API dan mengembalikan respon palsu dari server.
- [Coil](https://coil-kt.github.io/coil/) - Loader gambar yang support coroutines.
- [Android Debug Database](https://github.com/amitshekhariitbhu/Android-Debug-Database) - Debugging data di dalam database.

## Credit
- [Unsplash](https://unsplash.com/) - Untuk gambar buah-buahan.
