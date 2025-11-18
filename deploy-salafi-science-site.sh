#!/bin/bash

# 🚀 SALAFI SCIENCE SITE DEPLOYMENT SCRIPT
# This script sets up the salafi-science-site repository for deployment

echo "🌟 Salafi Science Network Website Deployment Script"
echo "=================================================="

# Check if we're in the right directory
if [ ! -f "package.json" ]; then
    echo "❌ Error: package.json not found. Please run this script from the project root."
    exit 1
fi

echo "✅ Project directory verified"

# Update configuration files for salafi-science-site
echo "📝 Updating configuration files..."

# Update astro.config.mjs
sed -i "s|https://mimotoufik11-stack.github.io/Toufiko|https://mimotoufik11-stack.github.io/salafi-science-site|g" astro.config.mjs

# Update sitemap.xml
sed -i "s|https://mimotoufik11-stack.github.io/Toufiko|https://mimotoufik11-stack.github.io/salafi-science-site|g" public/sitemap.xml

# Update robots.txt
sed -i "s|https://mimotoufik11-stack.github.io/Toufiko/sitemap.xml|https://mimotoufik11-stack.github.io/salafi-science-site/sitemap.xml|g" public/robots.txt

echo "✅ Configuration files updated"

# Build the project
echo "🔨 Building the project..."
npm run build

if [ $? -ne 0 ]; then
    echo "❌ Error: Build failed"
    exit 1
fi

echo "✅ Build completed successfully"

# Git operations
echo "📤 Preparing for deployment..."

# Add changes
git add .

# Commit changes
git commit -m "Configure for salafi-science-site deployment

- Updated site URL in astro.config.mjs
- Updated sitemap.xml URLs  
- Updated robots.txt sitemap reference
- Ready for deployment to salafi-science-site repository

🚀 Salafi Science Network - Complete Islamic Educational Platform"

echo "✅ Changes committed"

# Instructions for repository setup
echo ""
echo "🎯 NEXT STEPS:"
echo "=============="
echo ""
echo "1. Create the new repository at:"
echo "   https://github.com/mimotoufik11-stack/salafi-science-site"
echo ""
echo "2. Update git remote:"
echo "   git remote set-url origin https://github.com/mimotoufik11-stack/salafi-science-site.git"
echo ""
echo "3. Push to new repository:"
echo "   git push -u origin main"
echo ""
echo "4. Enable GitHub Pages:"
echo "   - Go to repository Settings > Pages"
echo "   - Source: Deploy from a branch"
echo "   - Branch: main / (root)"
echo "   - Save"
echo ""
echo "5. Your website will be live at:"
echo "   https://mimotoufik11-stack.github.io/salafi-science-site/"
echo ""
echo "✅ Deployment script completed successfully!"
echo "🌟 Salafi Science Network Website Ready!"