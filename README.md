# AX Player - Advanced Android Video Player

<div align="center">

![Android](https://img.shields.io/badge/Android-16%20(SDK%2035)-green)
![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-blue)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-2025.01.00-brightgreen)
![Media3](https://img.shields.io/badge/Media3-1.5.0-orange)
![License](https://img.shields.io/badge/License-MIT-yellow)

A modern, feature-rich Android video player with glassmorphism UI design, iOS-style aesthetics, and powerful playback capabilities powered by ExoPlayer (Media3).

</div>

## ✨ Features

### 🎬 Core Playback
- **ExoPlayer Integration**: Industry-leading video playback using AndroidX Media3
- **Format Support**: MP4, MKV, AVI, MOV, FLV, and more
- **Hardware Acceleration**: GPU-powered video decoding
- **Resume Playback**: Continue watching from where you left off
- **Playback Speed Control**: 0.25x to 2.0x speed adjustment
- **Background Playback**: Continue audio playback in background

### 🎨 UI/UX Design
- **Glassmorphism Effects**: Modern frosted glass UI components
- **iOS-Style Design**: Premium, minimalist aesthetics
- **Material Design 3**: Latest Material You theming
- **Dark/Light Modes**: Full theme support
- **OLED Optimization**: True black mode for battery savings
- **Smooth Animations**: 60 FPS fluid transitions
- **Haptic Feedback**: Touch response vibrations

### 🎮 Gesture Controls
- **Double Tap Seek**: Quick 10/15/30s forward/backward
- **Swipe Gestures**: Volume (right) and brightness (left) control
- **Pinch to Zoom**: Video zoom functionality
- **Tap to Show/Hide**: Auto-hiding player controls (5s timeout)

### 📚 Video Library
- **Grid View**: Beautiful video thumbnail grid
- **Search**: Fast video search by name
- **Sort Options**: By name, date, duration, or size
- **Favorites**: Mark videos for quick access
- **Recent Videos**: Quick access to recently played
- **Metadata Display**: Duration, size, resolution info

### ⚙️ Settings & Customization
- **Playback Settings**: Resume, auto-play next, default speed
- **Display Settings**: Theme, subtitle size, screen timeout
- **Gesture Settings**: Configure all gesture controls
- **Notification Settings**: Customizable playback notifications
- **Storage Management**: Cache control and clearing

### 🔒 Privacy & Security
- **Scoped Storage**: Android 11+ compliant
- **Encrypted Preferences**: Secure settings storage
- **Permission Management**: Runtime permission requests
- **No Analytics Tracking**: Privacy-first approach (optional Firebase)

## 🏗️ Architecture

### Clean Architecture (Domain/Data/Presentation)

```
app/
├── domain/                  # Business Logic Layer
│   ├── entity/             # Domain models (Video, PlaybackState, UserPreferences)
│   ├── repository/         # Repository interfaces
│   └── usecase/            # Business use cases
│
├── data/                   # Data Layer
│   ├── db/                 # Room database (VideoEntity, VideoDao)
│   ├── datasource/         # Data sources (LocalVideoDataSource)
│   ├── repository/         # Repository implementations
│   └── preferences/        # DataStore preferences manager
│
├── presentation/           # UI Layer
│   ├── theme/              # Compose theming (Color, Typography, Theme)
│   ├── components/         # Reusable UI components (GlassComponents)
│   ├── navigation/         # Navigation setup
│   ├── player/             # Player screen (PlayerScreen, PlayerViewModel)
│   ├── library/            # Library screen (LibraryScreen, LibraryViewModel)
│   ├── settings/           # Settings screen (SettingsScreen, SettingsViewModel)
│   └── MainActivity.kt
│
└── di/                     # Dependency Injection (Hilt modules)
```

### Design Patterns
- **MVVM**: Model-View-ViewModel with StateFlow
- **Repository Pattern**: Data abstraction layer
- **Use Case Pattern**: Single responsibility business logic
- **Dependency Injection**: Hilt for automatic DI
- **Observer Pattern**: Flow/StateFlow for reactive updates

## 🛠️ Tech Stack

### Core
- **Language**: Kotlin 2.1.0
- **UI Framework**: Jetpack Compose 2025.01.00
- **Video Player**: AndroidX Media3 (ExoPlayer) 1.5.0
- **Architecture**: AndroidX Lifecycle, ViewModel, Navigation

### Data & Storage
- **Database**: Room 2.6.1
- **Preferences**: DataStore 1.1.1
- **Security**: AndroidX Security Crypto 1.1.0-alpha06
- **Image Loading**: Coil 2.7.0 (with video support)

### Dependency Injection
- **DI Framework**: Dagger Hilt 2.53.1
- **Compiler**: KSP 2.1.0-1.0.29

### Firebase (Optional)
- **Analytics**: Firebase Analytics 22.1.2
- **Crashlytics**: Firebase Crashlytics 19.2.1
- **Remote Config**: Firebase Config
- **Performance**: Firebase Performance Monitoring

### Development Tools
- **Build Tool**: Gradle 8.7.3
- **Desugaring**: Core Library Desugaring 2.1.3
- **Logging**: Timber 5.0.1
- **Permissions**: Accompanist Permissions 0.36.0

## 📋 Requirements

- **Min SDK**: 26 (Android 8.0 Oreo)
- **Target SDK**: 35 (Android 16)
- **Compile SDK**: 35
- **Java Version**: 17
- **Kotlin Version**: 2.1.0

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/axplayer.git
cd axplayer
```

### 2. Open in Android Studio
- Android Studio Ladybug | 2024.2.1 or newer
- Install Kotlin plugin if not already installed

### 3. Firebase Setup (Optional)
If you want to use Firebase features:
1. Create a Firebase project at https://console.firebase.google.com
2. Download `google-services.json`
3. Place it in `app/` directory
4. Enable Analytics, Crashlytics in Firebase console

### 4. Build & Run
```bash
# Debug build
./gradlew assembleDebug

# Release build (requires signing config)
./gradlew assembleRelease

# Install on connected device
./gradlew installDebug
```

### 5. Run on Device/Emulator
- Connect Android device or start emulator
- Click Run in Android Studio
- Select target device
- App will install and launch

## 📱 Screens

### 1. Library Screen
- Displays all videos in grid layout
- Search, filter, and sort functionality
- Favorite marking
- Permission request UI

### 2. Player Screen
- Full-screen video playback
- Glassmorphism control panel
- Gesture controls
- Progress bar with timestamps
- Play/pause, seek controls

### 3. Settings Screen
- Organized settings sections
- Glass card UI components
- Toggle switches for preferences
- Real-time updates

## 🎨 Glassmorphism Components

### GlassCard
```kotlin
GlassCard(
    onClick = { /* action */ }
) {
    // Content
}
```

### GlassButton
```kotlin
GlassButton(onClick = { /* action */ }) {
    Icon(Icons.Default.Play, contentDescription = null)
    Text("Play")
}
```

### GlassIconButton
```kotlin
GlassIconButton(
    onClick = { /* action */ },
    icon = Icons.Default.Settings,
    contentDescription = "Settings"
)
```

### GlassProgressBar
```kotlin
GlassProgressBar(
    progress = 0.5f,
    modifier = Modifier.fillMaxWidth()
)
```

## 🔧 Configuration

### BuildConfig Fields
- `VERSION_NAME`: App version string
- `VERSION_CODE`: App version number
- `BUILD_TIME`: Build timestamp
- `ENABLE_LOGGING`: Debug logging flag
- `ENABLE_CRASHLYTICS`: Crashlytics flag

### ProGuard Rules
Includes rules for:
- Retrofit, OkHttp, Gson
- ExoPlayer/Media3
- Room, Coil
- Firebase
- Kotlin Coroutines & Serialization

## 📦 Gradle Version Catalog

All dependencies managed in `gradle/libs.versions.toml`:
- Centralized version management
- Easy dependency updates
- Type-safe accessors
- Bundle definitions

## 🧪 Testing

### Unit Tests
```bash
./gradlew test
```

### Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

### Test Coverage
- ViewModels: Business logic testing
- UseCases: Domain logic validation
- Repository: Data layer mocking

## 📄 License

```
MIT License

Copyright (c) 2025 AX Player

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 🤝 Contributing

Contributions are welcome! Please read our contributing guidelines:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Code Style
- Follow Kotlin coding conventions
- Use meaningful variable names
- Add comments for complex logic
- Write unit tests for new features

## 🐛 Bug Reports

Found a bug? Please open an issue with:
- Device model and Android version
- Steps to reproduce
- Expected vs actual behavior
- Screenshots/logs if applicable

## 💡 Feature Requests

Have an idea? Open an issue with:
- Detailed description
- Use case/benefit
- Mockups (if UI-related)

## 📞 Contact

- **GitHub**: [@yourusername](https://github.com/yourusername)
- **Email**: contact@axplayer.com
- **Website**: https://axplayer.com

## 🙏 Acknowledgments

- **AndroidX Media3**: Excellent video playback library
- **Jetpack Compose**: Modern Android UI toolkit
- **Material Design 3**: Beautiful design system
- **Firebase**: Powerful backend services
- **Timber**: Great logging library

---

<div align="center">
Made with ❤️ by the AX Player Team
</div>
