# MakeGradients_AndroidKotlin

MakeGradients is a Kotlin-based Android app designed for frontend developers who need to merge two colors and create stunning gradients. With MakeGradients, you can pick two colors, save them with a custom name, copy their color codes, and manage your gradient collection efficiently.

## Features
- Pick two colors and preview the gradient.
- Save gradients with custom names.
- Copy color codes for quick use.
- Store gradients locally using Room Database.
- Update, delete, and mark gradients as favorites.
- Create multiple gradients and manage them on the main screen.

## Screenshots
![Gradients UI](https://github.com/user-attachments/assets/ec6c90f4-8959-44a3-a3fc-8a7b75ffd632)

## Tech Stack
- **Kotlin** – Primary programming language.
- **Jetpack Compose** – UI framework.
- **Room Database** – Local storage for gradients.
- **Skydoves ColorPickerView** – Dependency for color selection.

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/Shivu1126/MakeGradients_AndroidKotlin.git
   ```
2. Open the project in **Android Studio**.
3. Build and run the app on an emulator or a physical device.

## Dependencies
Ensure you have the following dependencies in your `build.gradle`:
```kotlin
dependencies {
    implementation(libs.skydoves.colorpicker.compose)
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
}
```
## App APK
[Download APK](https://github.com/Shivu1126/MakeGradients_AndroidKotlin/blob/main/app-debug.apk)

## Contribution
Feel free to contribute! Fork the repository, make changes, and submit a pull request.

