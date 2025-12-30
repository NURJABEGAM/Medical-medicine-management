# AX Player - Project Structure

## Complete File Listing

### Root Configuration
```
├── build.gradle.kts                    # Root build configuration
├── settings.gradle.kts                 # Project settings & modules
├── gradle.properties                   # Gradle properties
├── gradlew                            # Gradle wrapper script (Unix)
├── .gitignore                         # Git ignore rules
└── README.md                          # Project documentation
```

### Gradle Configuration
```
gradle/
├── wrapper/
│   └── gradle-wrapper.properties      # Gradle wrapper configuration
└── libs.versions.toml                 # Version catalog (all dependencies)
```

### App Module
```
app/
├── build.gradle.kts                   # App module build configuration
├── proguard-rules.pro                 # ProGuard/R8 rules
├── google-services.json               # Firebase configuration (placeholder)
│
├── src/main/
│   ├── AndroidManifest.xml           # App manifest
│   │
│   ├── java/com/axplayer/
│   │   ├── AXPlayerApplication.kt    # Application class
│   │   │
│   │   ├── di/                       # Dependency Injection
│   │   │   ├── AppModule.kt          # App-level DI module
│   │   │   ├── DatabaseModule.kt     # Database DI module
│   │   │   └── RepositoryModule.kt   # Repository bindings
│   │   │
│   │   ├── domain/                   # Domain Layer (Business Logic)
│   │   │   ├── entity/
│   │   │   │   ├── Video.kt          # Video entity
│   │   │   │   ├── PlaybackState.kt  # Playback state entity
│   │   │   │   └── UserPreferences.kt# User preferences entity
│   │   │   ├── repository/
│   │   │   │   └── VideoRepository.kt# Repository interface
│   │   │   └── usecase/
│   │   │       ├── GetVideosUseCase.kt
│   │   │       ├── PlayVideoUseCase.kt
│   │   │       └── SavePlaybackStateUseCase.kt
│   │   │
│   │   ├── data/                     # Data Layer
│   │   │   ├── db/
│   │   │   │   ├── AppDatabase.kt    # Room database
│   │   │   │   ├── VideoDao.kt       # Video DAO
│   │   │   │   └── VideoEntity.kt    # Video entity
│   │   │   ├── repository/
│   │   │   │   └── VideoRepositoryImpl.kt
│   │   │   ├── preferences/
│   │   │   │   └── PreferencesManager.kt
│   │   │   └── datasource/
│   │   │       └── LocalVideoDataSource.kt
│   │   │
│   │   └── presentation/             # Presentation Layer (UI)
│   │       ├── MainActivity.kt       # Main activity
│   │       │
│   │       ├── theme/                # Compose theme
│   │       │   ├── Color.kt          # Color definitions
│   │       │   ├── Typography.kt     # Typography scale
│   │       │   └── Theme.kt          # Theme composition
│   │       │
│   │       ├── components/           # Reusable UI components
│   │       │   └── GlassComponents.kt# Glassmorphism components
│   │       │
│   │       ├── navigation/           # Navigation
│   │       │   ├── Navigation.kt     # NavHost setup
│   │       │   └── Screen.kt         # Screen routes
│   │       │
│   │       ├── player/               # Player Screen
│   │       │   ├── PlayerScreen.kt   # Player UI
│   │       │   ├── PlayerViewModel.kt# Player business logic
│   │       │   └── PlayerState.kt    # Player state
│   │       │
│   │       ├── library/              # Library Screen
│   │       │   ├── LibraryScreen.kt  # Library UI
│   │       │   ├── LibraryViewModel.kt
│   │       │   └── LibraryState.kt
│   │       │
│   │       └── settings/             # Settings Screen
│   │           ├── SettingsScreen.kt # Settings UI
│   │           ├── SettingsViewModel.kt
│   │           └── SettingsState.kt
│   │
│   └── res/                          # Android Resources
│       ├── values/
│       │   ├── strings.xml           # String resources
│       │   ├── colors.xml            # Color resources
│       │   ├── dimens.xml            # Dimension resources
│       │   └── styles.xml            # Style definitions
│       │
│       ├── values-night/
│       │   └── colors.xml            # Dark mode colors
│       │
│       ├── drawable/
│       │   ├── ic_play.xml           # Play icon
│       │   └── ic_pause.xml          # Pause icon
│       │
│       ├── mipmap-anydpi-v26/
│       │   ├── ic_launcher.xml       # Launcher icon
│       │   └── ic_launcher_round.xml # Round launcher icon
│       │
│       └── xml/
│           ├── data_extraction_rules.xml  # Android 12+ backup rules
│           ├── backup_rules.xml           # Backup configuration
│           └── file_paths.xml             # FileProvider paths
```

## Key Statistics

- **Total Kotlin Files**: 33
- **Lines of Code**: ~3,500+ (estimated)
- **Screens**: 3 (Player, Library, Settings)
- **ViewModels**: 3
- **Use Cases**: 3
- **Repositories**: 1
- **Database Entities**: 1
- **Dependency Injection Modules**: 3

## Architecture Layers

### 1. Domain Layer (Business Logic)
- **Purpose**: Pure business logic, framework-independent
- **Components**: Entities, Repository interfaces, Use Cases
- **Dependencies**: None (pure Kotlin)

### 2. Data Layer
- **Purpose**: Data management and persistence
- **Components**: Room database, DataStore preferences, Repository implementations
- **Dependencies**: Room, DataStore, Android framework

### 3. Presentation Layer (UI)
- **Purpose**: User interface and user interaction
- **Components**: Compose UI, ViewModels, State management
- **Dependencies**: Jetpack Compose, ViewModel, Navigation

## Technology Stack Summary

| Category | Technology | Version |
|----------|-----------|---------|
| Language | Kotlin | 2.1.0 |
| UI | Jetpack Compose | 2025.01.00 |
| Player | Media3 (ExoPlayer) | 1.5.0 |
| DI | Hilt | 2.53.1 |
| Database | Room | 2.6.1 |
| Preferences | DataStore | 1.1.1 |
| Image Loading | Coil | 2.7.0 |
| Logging | Timber | 5.0.1 |
| Firebase | BOM | 33.7.0 |
| Build Tool | Gradle | 8.7.3 |

## Build Configuration

### SDK Versions
- **Min SDK**: 26 (Android 8.0)
- **Target SDK**: 35 (Android 16)
- **Compile SDK**: 35

### Build Types
- **Debug**: Full logging, no minification
- **Release**: Minified, obfuscated, crashlytics enabled

### Features Enabled
- Jetpack Compose
- BuildConfig generation
- View Binding (optional)
- Data Binding (optional)
- Core library desugaring

## Quick Start Commands

### Build
```bash
./gradlew build
```

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

### Install on Device
```bash
./gradlew installDebug
```

### Run Tests
```bash
./gradlew test
```

### Clean Build
```bash
./gradlew clean build
```

## Development Workflow

1. **Setup**: Open project in Android Studio
2. **Sync**: Let Gradle sync and download dependencies
3. **Firebase**: Add `google-services.json` (optional)
4. **Build**: Build project to verify setup
5. **Run**: Deploy to device/emulator
6. **Develop**: Make changes and test
7. **Commit**: Commit to version control

## Next Steps

### Required Before First Run
1. ✅ Project structure created
2. ✅ All source files created
3. ✅ Build configuration complete
4. ⚠️ Add Firebase `google-services.json` (optional)
5. ⚠️ Create launcher icon assets (use IC Launcher in Android Studio)
6. ⚠️ Test permissions on real device

### Recommended Enhancements
- Add unit tests for ViewModels
- Add UI tests for screens
- Implement gesture handler for player
- Add subtitle support
- Implement playlist functionality
- Add video quality selector
- Implement chromecast support
- Add pip mode support

## Notes

- Firebase is optional - app works without it
- Launcher icons are placeholders - generate proper icons
- All dependencies are latest 2025 versions
- Clean Architecture ensures maintainability
- Glassmorphism UI provides modern aesthetics
- MVVM pattern with StateFlow for reactive UI
