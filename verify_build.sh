#!/bin/bash

# Al-Ilmia Android Build Verification Script
# This script verifies that all essential files are present for a complete Android build

echo "🔍 Al-Ilmia Android Build Verification"
echo "======================================"

missing_files=()

# Check gradle files
echo ""
echo "📋 Gradle Configuration Files:"
gradle_files=(
    "build.gradle"
    "settings.gradle"
    "gradle.properties"
    "gradle/wrapper/gradle-wrapper.properties"
    "gradlew"
)

for file in "${gradle_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ $file (MISSING)"
        missing_files+=("$file")
    fi
done

# Check app module files
echo ""
echo "📱 App Module Files:"
app_files=(
    "app/build.gradle"
    "app/proguard-rules.pro"
    "app/src/main/AndroidManifest.xml"
)

for file in "${app_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ $file (MISSING)"
        missing_files+=("$file")
    fi
done

# Check source files
echo ""
echo "💻 Source Files:"
source_files=(
    "app/src/main/java/com/alilmia/app/MainActivity.kt"
    "app/src/main/java/com/alilmia/app/AlIlmiaApplication.kt"
    "app/src/main/java/com/alilmia/app/model/ContentItem.kt"
    "app/src/main/java/com/alilmia/app/data/WebScraper.kt"
    "app/src/main/java/com/alilmia/app/data/ScraperCallback.kt"
    "app/src/main/java/com/alilmia/app/ui/HomeFragment.kt"
    "app/src/main/java/com/alilmia/app/ui/LibraryFragment.kt"
    "app/src/main/java/com/alilmia/app/ui/AudioFragment.kt"
    "app/src/main/java/com/alilmia/app/ui/DetailActivity.kt"
    "app/src/main/java/com/alilmia/app/adapter/ContentAdapter.kt"
    "app/src/main/java/com/alilmia/app/adapter/AudioAdapter.kt"
)

for file in "${source_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ $file (MISSING)"
        missing_files+=("$file")
    fi
done

# Check resource files
echo ""
echo "🎨 Resource Files:"
resource_files=(
    "app/src/main/res/values/strings.xml"
    "app/src/main/res/values/colors.xml"
    "app/src/main/res/values/themes.xml"
    "app/src/main/res/values/dimens.xml"
    "app/src/main/res/drawable/bg_main_gradient.xml"
    "app/src/main/res/drawable/bg_gold_border.xml"
    "app/src/main/res/color/nav_item_selector.xml"
    "app/src/main/res/drawable/ic_home.xml"
    "app/src/main/res/drawable/ic_library.xml"
    "app/src/main/res/drawable/ic_audio.xml"
    "app/src/main/res/drawable/ic_book.xml"
    "app/src/main/res/drawable/ic_search.xml"
    "app/src/main/res/drawable/placeholder_book.xml"
    "app/src/main/res/drawable/placeholder_audio.xml"
    "app/src/main/res/drawable/error_image.xml"
)

for file in "${resource_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ $file (MISSING)"
        missing_files+=("$file")
    fi
done

# Check layout files
echo ""
echo "📐 Layout Files:"
layout_files=(
    "app/src/main/res/layout/activity_main.xml"
    "app/src/main/res/layout/activity_detail.xml"
    "app/src/main/res/layout/fragment_home.xml"
    "app/src/main/res/layout/fragment_library.xml"
    "app/src/main/res/layout/fragment_audio.xml"
    "app/src/main/res/layout/item_book_card.xml"
    "app/src/main/res/layout/item_audio_card.xml"
)

for file in "${layout_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ $file (MISSING)"
        missing_files+=("$file")
    fi
done

# Check menu and navigation files
echo ""
echo "🧭 Menu & Navigation Files:"
menu_files=(
    "app/src/main/res/menu/bottom_nav_menu.xml"
    "app/src/main/res/menu/detail_menu.xml"
    "app/src/main/res/navigation/mobile_navigation.xml"
    "app/src/main/res/xml/backup_rules.xml"
    "app/src/main/res/xml/data_extraction_rules.xml"
)

for file in "${menu_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ $file (MISSING)"
        missing_files+=("$file")
    fi
done

# Summary
echo ""
echo "======================================"
if [ ${#missing_files[@]} -eq 0 ]; then
    echo "🎉 SUCCESS: All essential files are present!"
    echo "📱 Ready for compilation and deployment"
    echo ""
    echo "📋 Next Steps:"
    echo "   1. Open project in Android Studio"
    echo "   2. Wait for Gradle sync to complete"
    echo "   3. Build the project (Build → Make Project)"
    echo "   4. Run on device/emulator"
    echo ""
    echo "🔧 Build Commands:"
    echo "   ./gradlew build                    # Build project"
    echo "   ./gradlew assembleDebug           # Build debug APK"
    echo "   ./gradlew assembleRelease         # Build release APK"
    echo "   ./gradlew installDebug            # Install debug APK"
else
    echo "❌ FAILURE: Missing ${#missing_files[@]} files:"
    for file in "${missing_files[@]}"; do
        echo "   - $file"
    done
fi

echo ""
echo "📊 Project Structure Analysis"
echo "============================"

total_files=$(find . -type f -name "*.kt" -o -name "*.java" -o -name "*.xml" -o -name "*.gradle" | wc -l)
kotlin_files=$(find . -name "*.kt" | wc -l)
xml_files=$(find . -name "*.xml" | wc -l)
gradle_files=$(find . -name "*.gradle" | wc -l)

echo "📁 Total source files: $total_files"
echo "🔷 Kotlin files: $kotlin_files"
echo "📄 XML files: $xml_files"
echo "🔨 Gradle files: $gradle_files"

# Check directory structure
echo ""
echo "📂 Directory Structure:"
echo "   app/src/main/java/com/alilmia/app/ - Main source code"
echo "   app/src/main/res/ - Android resources"
echo "   app/src/main/res/layout/ - Layout files"
echo "   app/src/main/res/values/ - Value resources"
echo "   app/src/main/res/drawable/ - Drawables and icons"
echo "   app/src/main/res/menu/ - Menu resources"
echo "   app/src/main/res/navigation/ - Navigation graphs"

echo ""
echo "✨ Build verification complete!"