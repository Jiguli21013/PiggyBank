The application allows users to scan product barcodes and save the data to a local database.

Tech stack:

- Android SDK
- [Android Jetpack](https://developer.android.com/jetpack)
- [Kotlin 2.0](https://kotlinlang.org/docs/whatsnew20.html)
- [KotlinX Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [KotlinX Serialization](https://github.com/Kotlin/kotlinx.serialization)
- [Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/)
- [Jetpack Compose](https://developer.android.com/develop/ui/compose)
- [Rebugger — Jetpack Compose Debugging Tool](https://github.com/LouisCAD/rebugger)
- [Dagger](https://dagger.dev) + [Hilt](https://dagger.dev/hilt/)
- [Jetpack Room](https://developer.android.com/jetpack/androidx/releases/room)
- [Material](https://github.com/material-components/material-components-android)
- [ML Kit Barcode Scanning](https://developers.google.com/ml-kit/vision/barcode-scanning)
- [ML Kit + CameraX Integration](https://developers.google.com/ml-kit/vision/camera-integration)

<pre><code>modules/
├── core/
│   ├── api
│   ├── factory
│   ├── impl
├── features/
│   ├── database
│   ├── history/
│   │   ├── api
│   │   └── impl
│   ├── product/
│   │   ├── details/
│   │   │   ├── api
│   │   │   └── impl
│   │   └── edit/
│   │   │   ├── api
│   │   │   └── impl
│   │   └── insert/
│   │       ├── api
│   │       └── impl
│   └── scanner/
│       ├── api
│       └── impl
</code></pre>

Planned features:

1. Add test coverage
2. Settings screen (theme selection, language switching)
3. Integrate baseInlineProfiler

## Screenshots

<p>
  <img src=".github/images/scan.webp" alt="Scan Screen" width="192"/>
  <img src=".github/images/add_product.webp" alt="Add Product" width="192"/>
  <img src=".github/images/product_details.webp" alt="Product Details" width="192"/>
  <img src=".github/images/history.webp" alt="History" width="192"/>
</p>