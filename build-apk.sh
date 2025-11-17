#!/bin/bash

echo "========================================="
echo "بناء ملف APK لتطبيق القرآن الكريم"
echo "Building Quran App APK"
echo "========================================="
echo ""

# Check if Android SDK is installed
if [ -z "$ANDROID_HOME" ]; then
    echo "⚠️  تحذير: متغير ANDROID_HOME غير معرف"
    echo "⚠️  Warning: ANDROID_HOME is not set"
    echo ""
    echo "الرجاء تثبيت Android SDK وتعريف المتغير:"
    echo "Please install Android SDK and set the variable:"
    echo "export ANDROID_HOME=\$HOME/Android/Sdk"
    echo ""
fi

# Step 1: Install dependencies
echo "📦 الخطوة 1: تثبيت المكتبات..."
echo "📦 Step 1: Installing dependencies..."
npm install
echo "✅ تم تثبيت المكتبات"
echo ""

# Step 2: Prebuild for native Android project
echo "🔨 الخطوة 2: إعداد مشروع Android..."
echo "🔨 Step 2: Preparing Android project..."
npx expo prebuild --platform android --clean
echo "✅ تم إعداد المشروع"
echo ""

# Step 3: Build Release APK
echo "🏗️  الخطوة 3: بناء ملف APK..."
echo "🏗️  Step 3: Building APK..."
cd android
./gradlew assembleRelease
cd ..
echo ""

# Step 4: Find and copy APK
APK_PATH="android/app/build/outputs/apk/release/app-release.apk"
if [ -f "$APK_PATH" ]; then
    cp "$APK_PATH" "./app-release.apk"
    echo "✅ نجح بناء APK!"
    echo "✅ APK built successfully!"
    echo ""
    echo "📱 موقع الملف:"
    echo "📱 APK location:"
    echo "   $(pwd)/app-release.apk"
    echo ""
    echo "📊 حجم الملف:"
    echo "📊 File size:"
    ls -lh app-release.apk | awk '{print "   " $5}'
    echo ""
    echo "🎉 يمكنك الآن تثبيت التطبيق على هاتفك!"
    echo "🎉 You can now install the app on your phone!"
else
    echo "❌ فشل بناء APK"
    echo "❌ APK build failed"
    echo "الرجاء التحقق من سجل الأخطاء أعلاه"
    echo "Please check the error log above"
fi

echo ""
echo "========================================="
