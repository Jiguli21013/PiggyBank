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

Main modules:
•	core:* – base modules for working with data
•	common:* – shared code across features
•	features:* – all feature-specific modules
•	app – application assembly
•	di – dependency injection setup

Planned features:
1.	Add test coverage
2.	Settings screen (theme selection, language switching)
3.	Integrate baseInlineProfiler

## Screenshots
<img src=".github/images/scan.jpg" alt="Scan Screen" width="576"/>
<img src=".github/images/add_product.jpg" alt="Add Product" width="576"/>
<img src=".github/images/product_details.jpg" alt="Product Details" width="576"/>
<img src=".github/images/history.jpg" alt="History" width="576"/>