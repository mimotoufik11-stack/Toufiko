# تعليمات بناء ملف APK

## الطريقة 1: باستخدام Expo Application Services (EAS) - الأسهل

### الخطوات:

1. **تثبيت EAS CLI**
```bash
npm install -g eas-cli
```

2. **إنشاء حساب Expo (إذا لم يكن لديك)**
قم بزيارة: https://expo.dev/signup

3. **تسجيل الدخول**
```bash
eas login
```

4. **إعداد المشروع**
```bash
eas build:configure
```

5. **بناء APK**
```bash
eas build -p android --profile preview
```

سيتم رفع المشروع إلى خوادم Expo وبناء APK في السحابة. بعد الانتهاء، ستحصل على رابط لتحميل ملف APK.

## الطريقة 2: Build محلي باستخدام Expo

### المتطلبات:
- تثبيت Android Studio
- تثبيت Java Development Kit (JDK)
- إعداد متغيرات البيئة ANDROID_HOME

### الخطوات:

1. **تثبيت Prebuild**
```bash
npx expo prebuild
```

2. **بناء APK**
```bash
npx expo run:android --variant release
```

الملف سيكون في: `android/app/build/outputs/apk/release/app-release.apk`

## الطريقة 3: Build مباشر باستخدام Gradle

### بعد Prebuild:

```bash
cd android
./gradlew assembleRelease
```

الملف سيكون في: `android/app/build/outputs/apk/release/app-release.apk`

## ملاحظات مهمة:

1. **توقيع التطبيق**: للنشر في Google Play Store، تحتاج إلى توقيع التطبيق بمفتاح خاص
2. **حجم APK**: الحجم المتوقع حوالي 30-50 ميجابايت
3. **الإصدار**: تأكد من رفع `versionCode` في `app.json` عند كل بناء جديد
4. **الصلاحيات**: التطبيق يحتاج فقط صلاحية INTERNET

## اختبار APK:

بعد بناء APK، قم بنقله إلى هاتف Android واختباره:

```bash
adb install app-release.apk
```

أو انقل الملف مباشرة إلى الهاتف واضغط عليه للتثبيت.

## حل المشاكل الشائعة:

### مشكلة: "SDK location not found"
```bash
export ANDROID_HOME=$HOME/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/emulator
export PATH=$PATH:$ANDROID_HOME/tools
export PATH=$PATH:$ANDROID_HOME/tools/bin
export PATH=$PATH:$ANDROID_HOME/platform-tools
```

### مشكلة: "Java version incompatible"
تأكد من استخدام JDK 17 أو أعلى:
```bash
java -version
```

### مشكلة: فشل Build
نظف وأعد البناء:
```bash
cd android
./gradlew clean
./gradlew assembleRelease
```
