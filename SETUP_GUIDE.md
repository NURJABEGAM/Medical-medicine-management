# AX Player - Complete Setup Guide

## 🚀 Quick Start (5 Minutes)

### Prerequisites
- **Android Studio**: Ladybug | 2024.2.1 or newer
- **Java JDK**: 17 or newer
- **Android SDK**: API 26 (Android 8.0) minimum
- **Git**: For version control

### Step 1: Clone & Open Project
```bash
git clone <your-repo-url>
cd axplayer
```

Open the project in Android Studio:
1. Launch Android Studio
2. Click "Open" or "Open an existing project"
3. Navigate to the `axplayer` folder
4. Click "OK"

### Step 2: Gradle Sync
Android Studio will automatically:
- Download Gradle wrapper (if needed)
- Sync project dependencies (~5-10 minutes first time)
- Index project files

**Wait for "Gradle sync finished" message before proceeding.**

### Step 3: (Optional) Configure Firebase
If you want Firebase features (Analytics, Crashlytics):

1. Go to https://console.firebase.google.com
2. Create new project or use existing
3. Add Android app with package name: `com.axplayer`
4. Download `google-services.json`
5. Place it in `app/` directory
6. Rebuild project

**Note**: App works fine without Firebase - it's completely optional.

### Step 4: Build Project
```bash
./gradlew build
```

Or in Android Studio:
- **Build** → **Make Project** (Ctrl+F9 / Cmd+F9)

### Step 5: Run on Device/Emulator
1. Connect Android device via USB (enable USB debugging)
   - OR -
2. Start Android Emulator (Android 8.0+)

3. Click **Run** button (green play icon) or Shift+F10

4. Select target device

5. App will install and launch automatically

---

## 📱 Testing on Real Device

### Enable USB Debugging
1. Go to **Settings** → **About Phone**
2. Tap **Build Number** 7 times
3. Go back to **Settings** → **Developer Options**
4. Enable **USB Debugging**

### Install App
```bash
./gradlew installDebug
```

### Grant Permissions
On first launch:
1. App will request **Storage Permission**
2. Click **"Grant Permission"**
3. Select **"Allow"** in system dialog
4. Videos will appear in library

---

## 🛠️ Development Setup

### Android Studio Configuration

#### 1. Kotlin Plugin
Already included in Android Studio Ladybug+

#### 2. Code Style
- **File** → **Settings** → **Editor** → **Code Style** → **Kotlin**
- Import scheme: **Kotlin style guide**

#### 3. Build Variants
- **Build** → **Select Build Variant**
- Choose `debug` for development
- Choose `release` for production builds

### Gradle Tasks

#### Clean Build
```bash
./gradlew clean build
```

#### Debug Build
```bash
./gradlew assembleDebug
```

#### Release Build
```bash
./gradlew assembleRelease
# Requires signing configuration
```

#### Install Debug
```bash
./gradlew installDebug
```

#### Run Tests
```bash
./gradlew test
./gradlew connectedAndroidTest
```

#### Lint Check
```bash
./gradlew lint
```

---

## 🎨 Customization

### Change App Name
**File**: `app/src/main/res/values/strings.xml`
```xml
<string name="app_name">Your App Name</string>
```

### Change Package Name
1. Refactor package in Android Studio
2. Update `applicationId` in `app/build.gradle.kts`
3. Update Firebase config if used

### Change Colors
**File**: `app/src/main/java/com/axplayer/presentation/theme/Color.kt`
```kotlin
val CyanPrimary = Color(0xFF00BCD4) // Change primary color
val PurplePrimary = Color(0xFF9C27B0) // Change secondary color
```

### Change App Icon
1. Right-click `res` folder
2. **New** → **Image Asset**
3. Select **Launcher Icons**
4. Choose image and configure
5. Click **Next** → **Finish**

---

## 🔥 Firebase Setup (Detailed)

### Create Firebase Project

1. Visit https://console.firebase.google.com
2. Click **"Add project"**
3. Enter project name: `AX Player`
4. Enable/disable Google Analytics (optional)
5. Click **"Create project"**

### Add Android App

1. Click **"Add app"** → **Android icon**
2. Enter package name: `com.axplayer`
3. Enter app nickname: `AX Player` (optional)
4. Enter SHA-1 (optional, for future features)
5. Click **"Register app"**

### Download Config File

1. Download `google-services.json`
2. Move to `app/` directory in project
3. Verify file location: `app/google-services.json`

### Enable Services

#### Analytics
- Automatically enabled with `google-services.json`

#### Crashlytics
1. Go to **Release & Monitor** → **Crashlytics**
2. Click **"Enable Crashlytics"**
3. Follow setup wizard

#### Remote Config
1. Go to **Engage** → **Remote Config**
2. Add parameters as needed

#### Performance Monitoring
1. Go to **Release & Monitor** → **Performance**
2. Click **"Enable Performance Monitoring"**

### Test Firebase Integration

```bash
./gradlew assembleDebug
adb install app/build/outputs/apk/debug/app-debug.apk
# Open app and check Firebase console for events
```

---

## 📦 Building for Release

### 1. Create Keystore

```bash
keytool -genkey -v -keystore axplayer-release.keystore \
  -alias axplayer -keyalg RSA -keysize 2048 -validity 10000
```

**Important**: Save keystore file and passwords securely!

### 2. Configure Signing

**File**: `app/build.gradle.kts`

```kotlin
android {
    signingConfigs {
        create("release") {
            storeFile = file("../axplayer-release.keystore")
            storePassword = "YOUR_STORE_PASSWORD"
            keyAlias = "axplayer"
            keyPassword = "YOUR_KEY_PASSWORD"
        }
    }
    
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            // ... other config
        }
    }
}
```

**Better approach**: Use environment variables or `keystore.properties`

### 3. Build Release APK

```bash
./gradlew assembleRelease
```

Output: `app/build/outputs/apk/release/app-release.apk`

### 4. Build Release AAB (for Play Store)

```bash
./gradlew bundleRelease
```

Output: `app/build/outputs/bundle/release/app-release.aab`

---

## 🧪 Testing

### Unit Tests

**Location**: `app/src/test/java/`

```bash
./gradlew test
```

### Instrumented Tests

**Location**: `app/src/androidTest/java/`

```bash
./gradlew connectedAndroidTest
```

### Add Test Dependencies

Already included in `build.gradle.kts`:
- JUnit
- MockK
- Turbine (Flow testing)
- Espresso (UI testing)

---

## 🐛 Troubleshooting

### Gradle Sync Failed

**Solution**:
1. Check internet connection
2. Run: `./gradlew clean`
3. **File** → **Invalidate Caches** → **Restart**
4. Delete `.gradle` folder in project root
5. Sync again

### Build Failed - Dependencies

**Solution**:
```bash
./gradlew clean build --refresh-dependencies
```

### KSP Errors

**Solution**:
1. **Build** → **Clean Project**
2. **Build** → **Rebuild Project**
3. Restart Android Studio

### Firebase Not Working

**Solution**:
1. Verify `google-services.json` in `app/` directory
2. Check package name matches Firebase console
3. Sync Gradle
4. Clean and rebuild

### Permission Errors on Device

**Solution**:
1. Go to device **Settings** → **Apps** → **AX Player**
2. **Permissions** → Enable **Storage**/**Media**
3. Restart app

### ExoPlayer Crashes

**Solution**:
1. Check video format compatibility
2. Verify hardware acceleration settings
3. Check logs: `adb logcat | grep ExoPlayer`

### App Not Installing

**Solution**:
```bash
adb uninstall com.axplayer
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

---

## 📊 Performance Optimization

### Enable ProGuard (Release)
Already configured in `app/build.gradle.kts`

### Reduce APK Size
1. Enable R8 minification (already enabled)
2. Remove unused resources
3. Use WebP for images
4. Enable App Bundle for Play Store

### Optimize Images
Use Coil's caching:
```kotlin
// Already configured in project
implementation("io.coil-kt:coil-compose:2.7.0")
```

### Database Optimization
- Use proper indices in Room
- Limit query results
- Use Flow for reactive queries

---

## 📚 Resources

### Official Documentation
- [Android Developers](https://developer.android.com)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Media3 (ExoPlayer)](https://developer.android.com/guide/topics/media/media3)
- [Hilt Documentation](https://dagger.dev/hilt/)
- [Firebase Android](https://firebase.google.com/docs/android/setup)

### Sample Code
- [Compose Samples](https://github.com/android/compose-samples)
- [Media3 Samples](https://github.com/androidx/media)

### Community
- [Android Developers Discord](https://discord.gg/android-dev)
- [Kotlin Slack](https://kotlinlang.org/community/)
- [Stack Overflow](https://stackoverflow.com/questions/tagged/android)

---

## 🎯 Development Workflow

### Daily Development
1. Pull latest code: `git pull`
2. Sync Gradle
3. Make changes
4. Test on device/emulator
5. Run tests: `./gradlew test`
6. Commit changes: `git commit -m "Description"`
7. Push code: `git push`

### Before Committing
```bash
# Format code
./gradlew ktlintFormat

# Run tests
./gradlew test

# Check lint
./gradlew lint

# Build
./gradlew build
```

### Feature Development
1. Create feature branch: `git checkout -b feature/my-feature`
2. Develop feature
3. Test thoroughly
4. Commit changes
5. Push branch
6. Create pull request
7. Code review
8. Merge to main

---

## ✅ Checklist Before Release

- [ ] Update version in `app/build.gradle.kts`
- [ ] Update `CHANGELOG.md`
- [ ] Run all tests
- [ ] Test on multiple devices/Android versions
- [ ] Test all permissions
- [ ] Verify ProGuard rules
- [ ] Check for memory leaks
- [ ] Optimize images and resources
- [ ] Generate signed APK/AAB
- [ ] Test release build thoroughly
- [ ] Prepare Play Store listing
- [ ] Create release notes
- [ ] Tag release in Git

---

## 📞 Support

### Issues
Report bugs: GitHub Issues (add your repo URL)

### Questions
- Stack Overflow with `android` tag
- GitHub Discussions

### Contributing
See `CONTRIBUTING.md` (create if needed)

---

## 🎉 Success!

You now have a fully functional Android video player app with:
- ✅ Modern Jetpack Compose UI
- ✅ Glassmorphism design
- ✅ ExoPlayer integration
- ✅ Clean Architecture
- ✅ Room database
- ✅ Encrypted preferences
- ✅ Firebase integration (optional)
- ✅ Material Design 3
- ✅ Dark/Light themes
- ✅ OLED optimization

**Happy Coding! 🚀**
