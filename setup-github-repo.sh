#!/bin/bash

# GitHub Repository Setup Script for Salafi Science Network
# This script helps create and configure the GitHub repository

set -e

# Configuration
REPO_NAME="salafi-science-site"
REPO_DESCRIPTION="A modern static website for sharing authentic Islamic knowledge and scholarly resources"
GITHUB_USERNAME=""

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Helper functions
print_header() {
    echo -e "${BLUE}========================================${NC}"
    echo -e "${BLUE}$1${NC}"
    echo -e "${BLUE}========================================${NC}"
}

print_success() {
    echo -e "${GREEN}✅ $1${NC}"
}

print_warning() {
    echo -e "${YELLOW}⚠️  $1${NC}"
}

print_error() {
    echo -e "${RED}❌ $1${NC}"
}

# Check if GitHub CLI is installed
check_github_cli() {
    if ! command -v gh &> /dev/null; then
        print_warning "GitHub CLI (gh) is not installed."
        echo "Please install it from: https://cli.github.com/"
        echo "Or create the repository manually on GitHub.com"
        return 1
    fi
    return 0
}

# Create GitHub repository
create_repository() {
    print_header "Creating GitHub Repository"
    
    if check_github_cli; then
        # Get GitHub username
        GITHUB_USERNAME=$(gh api user --jq '.login')
        echo "GitHub username: $GITHUB_USERNAME"
        
        # Check if repository already exists
        if gh repo view "$GITHUB_USERNAME/$REPO_NAME" &> /dev/null; then
            print_warning "Repository $GITHUB_USERNAME/$REPO_NAME already exists"
            read -p "Do you want to continue with existing repository? (y/n): " -n 1 -r
            echo
            if [[ ! $REPLY =~ ^[Yy]$ ]]; then
                exit 1
            fi
        else
            # Create repository
            echo "Creating repository: $REPO_NAME"
            gh repo create "$REPO_NAME" \
                --description "$REPO_DESCRIPTION" \
                --public \
                --gitignore=Node \
                --confirm
            
            print_success "Repository created successfully"
        fi
        
        # Add remote if not already added
        if ! git remote get-url origin &> /dev/null; then
            git remote add origin "https://github.com/$GITHUB_USERNAME/$REPO_NAME.git"
            print_success "Remote 'origin' added"
        fi
        
        # Push to GitHub
        echo "Pushing code to GitHub..."
        git push -u origin $(git branch --show-current)
        print_success "Code pushed to GitHub"
        
        # Enable GitHub Pages
        echo "Configuring GitHub Pages..."
        gh api --method POST \
            -H "Accept: application/vnd.github.v3+json" \
            "/repos/$GITHUB_USERNAME/$REPO_NAME/pages" \
            --field build_type="legacy" \
            --field source="{\"branch\":\"main\",\"path\":\"/\"}" || \
        print_warning "Could not configure GitHub Pages automatically. Please configure manually in repository settings."
        
        return 0
    else
        print_error "GitHub CLI not available. Please create repository manually:"
        echo "1. Go to https://github.com/new"
        echo "2. Repository name: $REPO_NAME"
        echo "3. Description: $REPO_DESCRIPTION"
        echo "4. Make it public"
        echo "5. Add .gitignore (Node.js template)"
        echo "6. Create repository"
        echo "7. Add remote: git remote add origin https://github.com/YOUR_USERNAME/$REPO_NAME.git"
        echo "8. Push: git push -u origin main"
        return 1
    fi
}

# Verify setup
verify_setup() {
    print_header "Verifying Setup"
    
    # Check if required files exist
    required_files=(
        "README.md"
        "LICENSE"
        "CONTRIBUTING.md"
        "CODE_OF_CONDUCT.md"
        "DEPLOYMENT_GUIDE.md"
        ".github/workflows/deploy.yml"
        "package.json"
        ".gitignore"
    )
    
    for file in "${required_files[@]}"; do
        if [ -f "$file" ]; then
            print_success "$file exists"
        else
            print_error "$file is missing"
        fi
    done
    
    # Check if project builds
    echo "Testing build process..."
    if npm run build &> /dev/null; then
        print_success "Build successful"
    else
        print_error "Build failed"
        echo "Run 'npm run build' to see the errors"
    fi
}

# Main execution
main() {
    print_header "GitHub Repository Setup for Salafi Science Network"
    
    echo "This script will help you:"
    echo "1. Create a GitHub repository named '$REPO_NAME'"
    echo "2. Configure it for automatic deployment"
    echo "3. Push your code to GitHub"
    echo "4. Set up GitHub Pages"
    echo ""
    
    read -p "Do you want to continue? (y/n): " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        exit 0
    fi
    
    # Verify we're in the right directory
    if [ ! -f "package.json" ]; then
        print_error "package.json not found. Please run this script from the project root."
        exit 1
    fi
    
    # Create repository
    create_repository
    
    # Verify setup
    verify_setup
    
    print_header "Setup Complete!"
    echo "🎉 Your repository is ready!"
    echo ""
    echo "Next steps:"
    echo "1. Visit your repository: https://github.com/$GITHUB_USERNAME/$REPO_NAME"
    echo "2. Check GitHub Actions for deployment status"
    echo "3. Once deployed, visit: https://$GITHUB_USERNAME.github.io/$REPO_NAME/"
    echo "4. Read DEPLOYMENT_GUIDE.md for detailed instructions"
    echo ""
    echo "For manual setup, refer to DEPLOYMENT_GUIDE.md"
}

# Run main function
main "$@"