# دليل النشر وبناء APK
# Deployment and APK Build Guide

## ✅ نظرة عامة | Overview

تطبيق القرآن الكريم جاهز 100% للاستخدام ويحتوي على:
- 114 سورة كاملة مع البيانات
- عرض الآيات من API حقيقي
- بحث متقدم في القرآن
- واجهة عربية احترافية

The Quran app is 100% ready and includes:
- All 114 Surahs with complete data
- Verses from real API
- Advanced search in Quran
- Professional Arabic interface

---

## 📱 طرق بناء APK | APK Build Methods

### الطريقة 1: EAS Build (الأسهل - موصى بها) | Method 1: EAS Build (Easiest - Recommended)

هذه الطريقة تبني APK في السحابة ولا تحتاج Android Studio محلياً.

This method builds APK in the cloud without needing local Android Studio.

#### الخطوات | Steps:

```bash
# 1. تثبيت EAS CLI | Install EAS CLI
npm install -g eas-cli

# 2. تسجيل الدخول | Login
eas login
# إذا لم يكن لديك حساب، أنشئ واحداً على expo.dev
# If you don't have an account, create one at expo.dev

# 3. بناء APK | Build APK
eas build -p android --profile preview

# انتظر حتى يكتمل البناء (~10-20 دقيقة)
# Wait for build to complete (~10-20 minutes)

# سيظهر رابط لتحميل APK
# A download link for the APK will appear
```

#### النتيجة | Result:
- ستحصل على رابط مباشر لتحميل ملف APK
- حجم الملف: ~30-50 MB
- جاهز للتثبيت على أي هاتف Android

- You'll get a direct link to download the APK file
- File size: ~30-50 MB
- Ready to install on any Android phone

---

### الطريقة 2: Build محلي | Method 2: Local Build

#### المتطلبات | Requirements:

1. **Android Studio**
   - تحميل من: https://developer.android.com/studio
   - Download from: https://developer.android.com/studio

2. **Java Development Kit (JDK) 17+**
   ```bash
   java -version
   ```

3. **تعريف متغيرات البيئة | Set Environment Variables**
   ```bash
   # Linux/Mac
   export ANDROID_HOME=$HOME/Android/Sdk
   export PATH=$PATH:$ANDROID_HOME/emulator
   export PATH=$PATH:$ANDROID_HOME/platform-tools
   export PATH=$PATH:$ANDROID_HOME/tools
   export PATH=$PATH:$ANDROID_HOME/tools/bin
   
   # Windows (PowerShell)
   $env:ANDROID_HOME = "C:\Users\YourUsername\AppData\Local\Android\Sdk"
   $env:PATH += ";$env:ANDROID_HOME\platform-tools"
   ```

#### الخطوات | Steps:

```bash
# استخدم السكريبت الجاهز | Use the ready script
./build-apk.sh

# أو يدوياً | Or manually:

# 1. تثبيت المكتبات | Install dependencies
npm install

# 2. إعداد Android | Prepare Android
npx expo prebuild --platform android --clean

# 3. بناء APK | Build APK
cd android
./gradlew assembleRelease
cd ..

# الملف سيكون في | File will be at:
# android/app/build/outputs/apk/release/app-release.apk
```

---

### الطريقة 3: Expo Export | Method 3: Expo Export

للتطوير والتجربة السريعة:

For quick development and testing:

```bash
# 1. تصدير | Export
npx expo export

# 2. تشغيل على جهاز حقيقي | Run on real device
# ثبت تطبيق Expo Go من Play Store
# Install Expo Go from Play Store
# امسح QR code الظاهر
# Scan the QR code that appears
```

---

## 🚀 التثبيت على الهاتف | Installing on Phone

### الطريقة 1: نقل ملف APK مباشرة | Method 1: Direct APK Transfer

1. انقل ملف `app-release.apk` إلى هاتفك عبر USB أو Email
   Transfer `app-release.apk` to your phone via USB or Email

2. اضغط على الملف وثبت
   Tap the file and install

3. قد تحتاج لتفعيل "تثبيت من مصادر غير معروفة"
   You may need to enable "Install from unknown sources"

### الطريقة 2: عبر ADB | Method 2: Via ADB

```bash
# تأكد أن الهاتف متصل | Make sure phone is connected
adb devices

# تثبيت | Install
adb install app-release.apk

# أو | Or
adb install android/app/build/outputs/apk/release/app-release.apk
```

---

## 🔍 اختبار التطبيق | Testing the App

### على المحاكي | On Emulator

```bash
npm run android
```

### على جهاز حقيقي | On Real Device

```bash
# مع Expo Go
npm start
# امسح QR code

# أو مع APK
# Or with APK
adb install app-release.apk
```

---

## 📋 قائمة التحقق | Checklist

قبل النشر، تأكد من:
Before publishing, verify:

- [x] التطبيق يعمل بدون أخطاء | App works without errors
- [x] جميع السور تظهر (114 سورة) | All surahs display (114 surahs)
- [x] الآيات تحمل من API | Verses load from API
- [x] البحث يعمل | Search works
- [x] الواجهة بالعربية | Interface in Arabic
- [x] الألوان الإسلامية | Islamic colors
- [x] حجم APK معقول | APK size is reasonable

---

## 🛠️ حل المشاكل | Troubleshooting

### مشكلة: فشل بناء Android | Issue: Android build fails

```bash
# نظف وأعد البناء | Clean and rebuild
cd android
./gradlew clean
./gradlew assembleRelease
cd ..
```

### مشكلة: ANDROID_HOME غير معرف | Issue: ANDROID_HOME not set

```bash
# عرّف مجلد Android SDK | Set Android SDK folder
export ANDROID_HOME=$HOME/Android/Sdk
# أو على Windows | Or on Windows:
# $env:ANDROID_HOME = "C:\Users\YourUsername\AppData\Local\Android\Sdk"
```

### مشكلة: إصدار Java خاطئ | Issue: Wrong Java version

```bash
# تأكد من JDK 17+ | Ensure JDK 17+
java -version

# إذا كان أقل، ثبت أحدث إصدار من:
# If lower, install latest from:
# https://www.oracle.com/java/technologies/downloads/
```

### مشكلة: APK لا يعمل على الهاتف | Issue: APK doesn't work on phone

1. تأكد أن الملف `app-release.apk` غير تالف
   Make sure `app-release.apk` is not corrupted

2. تحقق من صلاحيات التثبيت
   Check installation permissions

3. جرب إعادة البناء
   Try rebuilding

---

## 📊 مواصفات APK | APK Specifications

- **الاسم | Name**: القرآن الكريم
- **Package**: com.quranapp.mobile
- **الإصدار | Version**: 1.0.0 (versionCode: 1)
- **الحجم المتوقع | Expected size**: 30-50 MB
- **الحد الأدنى لـ Android | Min Android**: 5.0 (API 21)
- **الصلاحيات | Permissions**: INTERNET فقط | only

---

## 🎉 النشر النهائي | Final Deployment

### للاستخدام الشخصي | For Personal Use
- ابن APK باستخدام أي طريقة أعلاه
- شارك الملف مع الأصدقاء
- Build APK using any method above
- Share the file with friends

### للنشر في Google Play Store | For Google Play Store Publishing
1. أنشئ حساب Google Play Developer
   Create Google Play Developer account
2. وقع APK بمفتاح توقيع
   Sign APK with keystore
3. ارفع على Play Console
   Upload to Play Console
4. املأ بيانات المتجر
   Fill store listing

---

## 📞 الدعم | Support

إذا واجهت أي مشكلة:
If you face any issues:

1. راجع ملف BUILD_INSTRUCTIONS.md
   Check BUILD_INSTRUCTIONS.md
2. راجع README.md للمميزات
   Check README.md for features
3. تحقق من سجل الأخطاء
   Check error logs

---

## ✅ الخلاصة | Summary

التطبيق جاهز 100% مع:
App is 100% ready with:

✅ كود كامل وجاهز للعمل
✅ Complete and working code

✅ بيانات القرآن الكريم (114 سورة)
✅ Quran data (114 surahs)

✅ API للآيات والبحث
✅ API for verses and search

✅ واجهة احترافية
✅ Professional interface

✅ جاهز للبناء كـ APK
✅ Ready to build as APK

✅ وثائق كاملة
✅ Complete documentation

**يمكنك الآن بناء APK باتباع أي طريقة أعلاه!**
**You can now build APK following any method above!**
