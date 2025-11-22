# Dammaj - Islamic Learning Android App

## Overview

Dammaj is a comprehensive Islamic learning application for Android with a luxury black and gold UI design. The app provides access to Islamic lessons, articles, books, fatwas, and scholar information with a beautiful Material Design 3 interface.

## Features

- **🏠 Home Screen**: Featured Islamic content with web scraping integration
- **📚 Library**: Categorized content with filtering capabilities
- **🎵 Audio Content**: Listen to Islamic lectures and Quranic recitations
- **🎨 Luxury UI**: Premium black and gold Material Design 3 theme
- **📱 RTL Support**: Full right-to-left language support for Arabic
- **⚡ Fast Loading**: Optimized image loading with Glide
- **🌐 Web Integration**: Fetches content from Islamic websites

## Technical Stack

- **Android API**: 24 (Android 7.0) - 34 (Android 14)
- **Language**: Java
- **Build Tool**: Gradle 8.0
- **UI Framework**: Material Design 3
- **Dependencies**:
  - AndroidX (AppCompat, RecyclerView, Fragment, Lifecycle)
  - Material Design Components
  - Jsoup (Web scraping)
  - Glide (Image loading)
  - Media3/ExoPlayer (Audio playback)
  - OkHttp (Networking)
  - Gson (JSON parsing)

## Project Structure

```
dammaj-android/
├── app/
│   ├── build.gradle                 # App-level Gradle configuration
│   ├── proguard-rules.pro           # ProGuard rules for obfuscation
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/com/dammaj/app/
│       │   │   ├── MainActivity.java
│       │   │   ├── model/
│       │   │   │   ├── ContentItem.java
│       │   │   │   └── AudioItem.java
│       │   │   ├── data/
│       │   │   │   ├── ScraperCallback.java
│       │   │   │   └── WebScraper.java
│       │   │   ├── ui/
│       │   │   │   ├── HomeFragment.java
│       │   │   │   ├── LibraryFragment.java
│       │   │   │   └── AudioFragment.java
│       │   │   └── adapter/
│       │   │       ├── ContentAdapter.java
│       │   │       └── AudioAdapter.java
│       │   └── res/
│       │       ├── layout/           (6 layout files)
│       │       ├── drawable/         (12 drawable files)
│       │       ├── color/            (selector files)
│       │       ├── values/           (colors, themes, strings)
│       │       ├── menu/             (navigation menu)
│       │       └── mipmap/           (launcher icons)
│       └── test/
│           └── java/com/dammaj/app/
│               └── ExampleUnitTest.java
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── .github/
│   └── workflows/
│       └── android-build.yml        # GitHub Actions workflow
├── build.gradle                     # Root Gradle configuration
├── settings.gradle                  # Gradle settings
├── README.md                        # This file
├── LICENSE                          # MIT License
└── .gitignore                       # Git ignore rules

```

## Color Scheme

- **Primary Gold**: #D4AF37
- **Soft Gold**: #F4DF99
- **Background Black**: #000000
- **Card Black**: #1A1A1A
- **Text White**: #FFFFFF
- **Text Grey**: #B0B0B0
- **Search Background**: #252525

## Installation & Setup

### Prerequisites

- Android Studio Flamingo or newer
- JDK 11 or higher
- Android SDK 34 (API level 34)
- Minimum 2GB RAM for build process

### Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/YOUR-USERNAME/dammaj-android.git
   cd dammaj-android
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Click "File" → "Open"
   - Select the `dammaj-android` folder
   - Wait for Gradle sync to complete (2-3 minutes)

3. **Build the Project**
   - Click "Build" → "Make Project"
   - Wait for compilation to complete

4. **Run the App**
   - Connect an Android device or start an emulator
   - Click "Run" → "Run 'app'"
   - Select your device/emulator
   - App will install and launch automatically

### Alternative: Command Line Build

```bash
# Make gradlew executable (Linux/Mac)
chmod +x gradlew

# Build the project
./gradlew build

# Build APK for debugging
./gradlew assembleDebug

# Build APK for release
./gradlew assembleRelease

# Run on connected device
./gradlew installDebug
```

## Dependencies

### Core AndroidX
- `androidx.appcompat:appcompat:1.6.1` - AppCompat support library
- `androidx.constraintlayout:constraintlayout:2.1.4` - Constraint layouts
- `androidx.recyclerview:recyclerview:1.3.1` - RecyclerView widget
- `androidx.fragment:fragment:1.6.1` - Fragment support
- `androidx.lifecycle:lifecycle-runtime:2.6.1` - Lifecycle awareness

### Material Design
- `com.google.android.material:material:1.10.0` - Material Design 3 components

### Third-Party Libraries
- `org.jsoup:jsoup:1.16.1` - HTML/XML parsing
- `com.github.bumptech.glide:glide:4.16.0` - Image loading and caching
- `androidx.media3:media3-exoplayer:1.1.1` - Media playback
- `com.squareup.okhttp3:okhttp:4.11.0` - HTTP client
- `com.google.code.gson:gson:2.10.1` - JSON serialization

## Architecture

### Model-View-Controller (MVC) Pattern

**Models** (`model/`):
- `ContentItem` - Represents Islamic content (lessons, articles, books, etc.)
- `AudioItem` - Represents audio content (lectures, Quran recitations)

**Views** (`ui/`, `adapter/`):
- `HomeFragment` - Displays featured content
- `LibraryFragment` - Categorized content with filters
- `AudioFragment` - Audio content player
- `ContentAdapter` - RecyclerView adapter for content
- `AudioAdapter` - RecyclerView adapter for audio

**Controllers** (`data/`):
- `WebScraper` - Fetches and parses content from websites
- `ScraperCallback` - Callback interface for async operations
- `MainActivity` - Main activity and navigation controller

## Features in Detail

### Home Screen
- Displays featured Islamic content
- Grid layout with image thumbnails
- Automatic web scraping from Islamic websites
- Pull-to-refresh functionality
- Error handling with retry option

### Library
- Categorized content (Lessons, Articles, Books, Fatwas, Scholars)
- Filter by category using chips
- Search functionality
- SharedPreferences caching
- Responsive grid layout

### Audio Player
- List of audio lectures and Quranic recitations
- Play button integration
- Duration display
- Author information
- ExoPlayer integration for playback

### UI/UX
- Material Design 3 dark theme
- Smooth transitions and animations
- RTL support for Arabic text
- Responsive layouts for all screen sizes
- Touch ripple effects in gold

## Permissions

The app requires the following permissions:
- `INTERNET` - For web scraping and content fetching
- `ACCESS_NETWORK_STATE` - To check network connectivity

## ProGuard/R8 Configuration

The app includes ProGuard rules to:
- Protect AndroidX classes
- Preserve Material Design components
- Maintain Jsoup parsing capabilities
- Keep Glide image loading features
- Protect app-specific model classes

## Building for Production

### Debug APK
```bash
./gradlew assembleDebug
# Output: app/build/outputs/apk/debug/app-debug.apk
```

### Release APK (Unsigned)
```bash
./gradlew assembleRelease
# Output: app/build/outputs/apk/release/app-release-unsigned.apk
```

### Release APK (Signed)
```bash
./gradlew assembleRelease -Pandroid.injected.signing.store.file=keystore.jks \
  -Pandroid.injected.signing.store.password=PASSWORD \
  -Pandroid.injected.signing.key.alias=ALIAS \
  -Pandroid.injected.signing.key.password=KEY_PASSWORD
```

## Continuous Integration

GitHub Actions workflow automatically:
- Builds the project on push/pull requests
- Compiles the APK
- Uploads build artifacts
- Runs unit tests

See `.github/workflows/android-build.yml` for configuration.

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Make your changes
4. Commit your changes (`git commit -m 'Add AmazingFeature'`)
5. Push to the branch (`git push origin feature/AmazingFeature`)
6. Open a Pull Request

## Code Style

- Follow Android coding standards
- Use meaningful variable and method names
- Add Javadoc comments for public methods
- Maximum line length: 120 characters
- Use 4 spaces for indentation

## Troubleshooting

### Gradle Sync Issues
- Clear Android Studio cache: `File` → `Invalidate Caches` → `Invalidate and Restart`
- Delete `.gradle` folder and resync
- Check JDK version (must be 11 or higher)

### Build Failures
- Check Java version: `java -version`
- Update Android SDK: SDK Manager
- Clear build cache: `./gradlew clean`

### Network Issues
- Check internet connectivity
- Verify firewall settings
- Check website availability for web scraping

### App Crashes
- Check Android version (minimum API 24)
- Review Logcat logs: `./gradlew logcat`
- Check runtime permissions

## Testing

Run unit tests:
```bash
./gradlew test
```

Run instrumented tests on device/emulator:
```bash
./gradlew connectedAndroidTest
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Authors

- **Dammaj Development Team**

## Support

For issues, questions, or suggestions:
- Open an issue on GitHub
- Email: support@dammaj.com
- Website: [Dammaj Islamic Learning](https://dammaj-app.com)

## Changelog

### Version 1.0.0 (Initial Release)
- Home screen with featured content
- Library with category filtering
- Audio player for lectures
- Material Design 3 UI with gold accents
- Web scraping functionality
- Full RTL support for Arabic
- GitHub Actions CI/CD

## Roadmap

- [ ] User authentication and profiles
- [ ] Bookmarking and favorites
- [ ] Download for offline access
- [ ] Search with advanced filters
- [ ] Dark/light theme toggle
- [ ] Multiple language support
- [ ] Social sharing features
- [ ] Push notifications for new content
- [ ] Podcast integration

## Acknowledgments

- Material Design 3 guidelines
- Jsoup HTML parsing library
- Glide image loading library
- AndroidX libraries and documentation

---

**Made with ❤️ for Islamic Learning**
