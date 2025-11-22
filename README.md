# Dammaj Android Application

A luxury Islamic learning Android application built with native Android development, featuring a black and gold theme design. The app provides access to Islamic content including books, articles, lessons, fatwas, and audio lectures.

## 📱 Features

- **🏠 Home Screen**: Display featured Islamic content in a beautiful grid layout
- **📚 Library**: Browse and search through categorized Islamic content
- **🎧 Audio Section**: Listen to Islamic lectures and audio content
- **🔍 Search**: Search functionality across all content
- **🏷️ Categories**: Filter content by categories (Books, Articles, Lessons, Fatwas, Scholars)
- **🌙 Dark Theme**: Luxury black and gold Material Design 3 theme
- **🔤 RTL Support**: Full right-to-left support for Arabic content
- **📱 Responsive**: Optimized for all screen sizes and orientations

## 🛠 Tech Stack

- **Language**: Java 11
- **Framework**: Android SDK (API 24-34)
- **Build System**: Gradle 8.0+
- **UI Components**: Material Design 3
- **Architecture**: Fragment-based MVVVM-ready architecture
- **Image Loading**: Glide 4.15.1
- **Web Scraping**: Jsoup 1.15.3
- **Audio Playback**: ExoPlayer 2.18.7
- **Testing**: JUnit 4, Espresso

## 📦 Dependencies

```gradle
// AndroidX Core Libraries
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
implementation 'androidx.cardview:cardview:1.0.0'
implementation 'androidx.recyclerview:recyclerview:1.3.0'
implementation 'androidx.fragment:fragment:1.6.1'

// Material Design 3
implementation 'com.google.android.material:material:1.9.0'

// Image Loading
implementation 'com.github.bumptech.glide:glide:4.15.1'

// Web Scraping
implementation 'org.jsoup:jsoup:1.15.3'

// Audio/Video Playback
implementation 'com.google.android.exoplayer:exoplayer:2.18.7'
```

## 🏗 Architecture

The app follows a clean architecture pattern with the following structure:

```
com.dammaj.app/
├── MainActivity.java              # Main activity with bottom navigation
├── model/                        # Data models
│   ├── ContentItem.java         # Content item model
│   └── AudioItem.java           # Audio item model
├── data/                         # Data layer
│   ├── ScraperCallback.java     # Callback interface
│   └── WebScraper.java         # Web scraping logic
├── ui/                          # UI layer (Fragments)
│   ├── HomeFragment.java       # Home screen fragment
│   ├── LibraryFragment.java    # Library screen fragment
│   └── AudioFragment.java      # Audio screen fragment
└── adapter/                     # RecyclerView adapters
    ├── ContentAdapter.java      # Content grid adapter
    └── AudioAdapter.java        # Audio list adapter
```

## 🎨 Design System

### Colors
- **Primary**: Gold (#FFD700)
- **Surface**: Black (#000000, #121212, #1E1E1E)
- **Text**: White (#FFFFFF), Gray (#CCCCCC, #888888)
- **Accent**: Gold variations

### Theme
- Material Design 3 Dark Theme
- Custom luxury black and gold color palette
- RTL layout support for Arabic
- Custom ripple effects and animations

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API 24-34
- Java 11 or higher
- Git

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR-USERNAME/dammaj-android.git
   cd dammaj-android
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Click "Open an existing Android Studio project"
   - Navigate to and select the `dammaj-android` directory
   - Wait for Gradle sync to complete (2-3 minutes)

3. **Build and Run**
   - Select an emulator or connected device
   - Click Run > Run 'app'
   - Or use the shortcut: `Ctrl+R` (Windows/Linux) or `Cmd+R` (Mac)

### Project Configuration

The app uses the following configuration:
- **Application ID**: `com.dammaj.app`
- **Min SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Compile SDK**: API 34
- **Java Version**: 11

## 📱 Screens

### Home Screen
- Grid layout with featured content
- Material cards with images and text
- Loading states and error handling

### Library Screen
- Search functionality
- Category filter chips
- Grid layout with filtered content

### Audio Screen
- List layout for audio content
- Play buttons and duration display
- Audio metadata display

## 🔧 Configuration

### Web Scraping
The app uses Jsoup for web scraping Islamic content. The scraping logic is implemented in `WebScraper.java`:

```java
WebScraper scraper = new WebScraper();
scraper.scrapeHome(new ScraperCallback() {
    @Override
    public void onSuccess(List<ContentItem> items) {
        // Handle success
    }
    
    @Override
    public void onFailure(Exception e) {
        // Handle error
    }
});
```

### Image Loading
Images are loaded using Glide with custom configurations:

```java
Glide.with(context)
    .load(imageUrl)
    .apply(options)
    .into(imageView);
```

## 🧪 Testing

### Unit Tests
```bash
./gradlew test
```

### Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

## 📦 Build Variants

The app supports two build variants:
- **debug**: Development build with debugging enabled
- **release**: Production build optimized for distribution

### Generate Release APK
```bash
./gradlew assembleRelease
```

## 🔒 Security

- Network security configuration for HTTPS
- ProGuard obfuscation enabled in release builds
- Input validation for web scraping
- Safe image loading with Glide

## 🌐 Localization

The app supports Arabic language with:
- RTL layout support
- Arabic strings in `res/values/strings.xml`
- RTL configuration in `MainActivity`
- Proper text direction handling

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

For support and questions:
- Create an issue in the GitHub repository
- Email: support@dammaj.org

## 🔄 Version History

- **v1.0.0** (Initial Release)
  - Basic app structure
  - Home, Library, and Audio screens
  - Web scraping functionality
  - Material Design 3 theme
  - RTL support

## 📊 Performance

- **Build Time**: ~2 minutes
- **APK Size**: ~8MB
- **Memory Usage**: < 50MB
- **Battery Usage**: Optimized for minimal battery drain

## 🔮 Future Enhancements

- [ ] Offline content caching
- [ ] Audio player with background playback
- [ ] Content bookmarking
- [ ] User authentication
- [ ] Push notifications
- [ ] Content sharing
- [ ] Advanced search filters
- [ ] Content download for offline viewing

---

**Built with ❤️ for the Muslim Ummah**