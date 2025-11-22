# Al-Ilmia Android Application

A production-ready Android application for Al-Ilmia (alilmia.com) with luxury UI/UX and web scraping capabilities.

## Features

- **Luxury Black/Gold Theme**: Dammam-inspired design with RTL support
- **Web Scraping**: Jsoup-based content extraction from Al-Ilmia website
- **Material Design 3**: Modern UI components and transitions
- **Audio Support**: ExoPlayer integration for audio content
- **Image Loading**: Glide-powered image caching and loading
- **Fragment Navigation**: Bottom navigation with three main sections
- **Responsive Design**: Optimized for various screen sizes
- **Arabic RTL Support**: Full right-to-left layout support

## Technical Specifications

- **Package**: `com.alilmia.app`
- **Min SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Language**: Kotlin
- **Architecture**: MVVM with fragments
- **Build Tools**: Gradle 8.0.2
- **Java Version**: 11

## Dependencies

### Core Libraries
- `androidx.appcompat:appcompat:1.6.1`
- `com.google.android.material:material:1.9.0`
- `androidx.constraintlayout:constraintlayout:2.1.4`
- `androidx.navigation:navigation-fragment-ktx:2.6.0`

### Web Scraping
- `org.jsoup:jsoup:1.15.3`

### Image Loading
- `com.github.bumptech.glide:glide:4.15.1`

### Audio Playback
- `com.google.android.exoplayer:exoplayer:2.18.7`

### UI Components
- `androidx.recyclerview:recyclerview:1.3.0`
- `androidx.cardview:cardview:1.0.0`
- `androidx.swiperefreshlayout:swiperefreshlayout:1.1.0`

## Project Structure

```
app/
├── src/main/
│   ├── java/com/alilmia/app/
│   │   ├── MainActivity.kt
│   │   ├── AlIlmiaApplication.kt
│   │   ├── model/
│   │   │   └── ContentItem.kt
│   │   ├── data/
│   │   │   ├── WebScraper.kt
│   │   │   └── ScraperCallback.kt
│   │   ├── ui/
│   │   │   ├── HomeFragment.kt
│   │   │   ├── LibraryFragment.kt
│   │   │   ├── AudioFragment.kt
│   │   │   └── DetailActivity.kt
│   │   └── adapter/
│   │       ├── ContentAdapter.kt
│   │       └── AudioAdapter.kt
│   ├── res/
│   │   ├── layout/
│   │   ├── values/
│   │   ├── drawable/
│   │   ├── menu/
│   │   └── navigation/
│   └── AndroidManifest.xml
├── build.gradle
└── proguard-rules.pro
```

## Build Instructions

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or later
- JDK 11 or later
- Android SDK API 34

### Build Steps

1. **Clone the Repository**
   ```bash
   git clone [repository-url]
   cd alilmia-android
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the project directory

3. **Sync Project**
   - Android Studio will automatically sync the project
   - Wait for Gradle sync to complete

4. **Build the Project**
   ```bash
   # From command line
   ./gradlew build
   
   # Or use Android Studio Build menu
   # Build -> Build Bundle(s) / APK(s) -> Build APK(s)
   ```

5. **Run the Application**
   - Connect an Android device or start an emulator
   - Click the Run button in Android Studio
   - Or use command line:
   ```bash
   ./gradlew installDebug
   ```

### Build Variants

- **debug**: Development build with debugging enabled
- **release**: Production build with optimizations

## Configuration

### Web Scraping Configuration
The app scrapes content from `https://alilmia.com` using Jsoup. Modify `WebScraper.kt` to:
- Change target URL
- Adjust CSS selectors
- Modify content extraction logic

### Theme Customization
Modify colors in `res/values/colors.xml`:
- `gold_luxury`: Primary gold color (#D4AF37)
- `background_black`: Main background (#000000)
- `surface_dark`: Card background (#151515)

### RTL Configuration
RTL support is enabled by default. To disable:
- Remove `android:supportsRtl="true"` from AndroidManifest.xml
- Update layout directions in themes.xml

## Key Features Implementation

### 1. Web Scraping
- Jsoup-based HTML parsing
- Background threading with ExecutorService
- Robust error handling and fallback selectors
- Content type detection

### 2. UI Components
- Material Design 3 cards with elevation
- Custom gold/black theme
- RTL layout support
- Responsive grid layouts

### 3. Navigation
- Bottom navigation with three tabs
- Fragment-based navigation
- Proper back stack management

### 4. Image Loading
- Glide integration with caching
- Placeholder and error images
- Memory-efficient loading

### 5. Audio Support
- ExoPlayer integration (placeholder)
- Audio-specific UI layouts
- Duration and metadata display

## Production Deployment

### Release Build
```bash
./gradlew assembleRelease
```

### Signing
1. Generate signing key:
   ```bash
   keytool -genkey -v -keystore alilmia-release.keystore -alias alilmia -keyalg RSA -keysize 2048 -validity 10000
   ```

2. Configure signing in `app/build.gradle`

### ProGuard
Release builds include ProGuard optimization with custom rules for:
- Jsoup classes
- Glide components
- ExoPlayer classes

## Testing

### Unit Tests
```bash
./gradlew test
```

### Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

## Troubleshooting

### Common Issues

1. **Build Errors**: Check Android SDK and JDK versions
2. **Network Issues**: Verify internet permissions and SSL settings
3. **Image Loading**: Check Glide configuration and memory settings
4. **RTL Issues**: Verify layoutDirection settings in themes

### Debugging
- Enable debug logging in WebScraper
- Use Android Studio Layout Inspector for UI issues
- Monitor network traffic with Charles Proxy or similar

## Contributing

1. Fork the repository
2. Create a feature branch
3. Implement changes with proper testing
4. Submit a pull request with detailed description

## License

This project is proprietary and confidential to Al-Ilmia organization.

## Support

For technical support and questions, contact the development team.

---

**Note**: This application is designed specifically for Al-Ilmia website content and may require updates if the target website structure changes.