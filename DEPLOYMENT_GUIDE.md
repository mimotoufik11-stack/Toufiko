# GitHub Repository Setup & Deployment Guide

This guide provides step-by-step instructions for setting up the GitHub repository and configuring deployment for the Salafi Science Network site.

## 🚀 Repository Setup

### 1. Create GitHub Repository

1. **Repository Name**: `salafi-science-site`
2. **Description**: `A modern static website for sharing authentic Islamic knowledge and scholarly resources`
3. **Visibility**: Public (recommended for open-source Islamic content)
4. **Initialize with**:
   - ✅ Add a README file
   - ✅ Add .gitignore (Node.js template)
   - ❌ Add a license (we'll add MIT manually)

### 2. Local Repository Setup

```bash
# If starting fresh (without existing code)
git clone https://github.com/your-username/salafi-science-site.git
cd salafi-science-site

# If migrating existing code
git remote set-url origin https://github.com/your-username/salafi-science-site.git
git branch -M main
```

### 3. Push Initial Code

```bash
# Add all files
git add .

# Commit with descriptive message
git commit -m "feat: Initial commit with complete Astro static site

- Add comprehensive README with setup instructions
- Add MIT License, Contributing guidelines, and Code of Conduct
- Configure GitHub Actions workflow for automatic deployment
- Include all project files and documentation
- Ready for GitHub Pages deployment"

# Push to main branch
git push -u origin main
```

## 🔧 GitHub Pages Configuration

### 1. Enable GitHub Pages

1. Navigate to repository settings
2. Scroll down to "Pages" section
3. **Source**: Select "GitHub Actions"
4. **Custom domain** (optional): Enter your custom domain if available

### 2. Configure GitHub Actions

The deployment workflow is already configured in `.github/workflows/deploy.yml` with:

- **Triggers**: Push to main, PR to main, manual dispatch
- **Permissions**: Proper GitHub Pages permissions
- **Build Process**: Automated Node.js setup and Astro build
- **Deployment**: Automatic deployment to GitHub Pages

### 3. Verify Deployment

1. After first push, check "Actions" tab in GitHub
2. Wait for workflow to complete (typically 2-3 minutes)
3. Visit the deployed site at: `https://your-username.github.io/salafi-science-site/`

## 🛠 Development Workflow

### Branch Protection (Recommended)

1. Go to Settings → Branches
2. Add branch protection rule for `main`
3. Enable:
   - Require pull request reviews
   - Require status checks to pass
   - Require branches to be up to date

### Pre-commit Hooks (Optional)

Add pre-commit hooks for code quality:

```bash
# Install husky for git hooks
npm install --save-dev husky

# Initialize husky
npx husky install

# Add pre-commit hook
npx husky add .husky/pre-commit "npm run type-check && npm run lint"
```

## 📁 Repository Structure Verification

Ensure your repository has this structure:

```
salafi-science-site/
├── .github/
│   └── workflows/
│       └── deploy.yml          # GitHub Actions workflow
├── .gitignore                  # Node.js gitignore
├── LICENSE                     # MIT License
├── README.md                   # Comprehensive documentation
├── CONTRIBUTING.md             # Contribution guidelines
├── CODE_OF_CONDUCT.md          # Community guidelines
├── package.json                # Dependencies and scripts
├── astro.config.mjs            # Astro configuration
├── components/                 # Astro components
├── content/                    # Markdown content
├── public/                     # Static assets
├── scripts/                    # Utility scripts
├── src/                        # Source files
├── styles/                     # CSS and Tailwind
└── tsconfig.json               # TypeScript config
```

## 🔍 Testing the Deployment

### 1. Local Testing

```bash
# Install dependencies
npm install

# Start development server
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview
```

### 2. Automated Testing

The GitHub Actions workflow includes:

- **Build Test**: Verifies the site builds without errors
- **Artifact Upload**: Ensures build artifacts are properly created
- **Deployment Test**: Verifies successful deployment to GitHub Pages

### 3. Manual Testing Checklist

- [ ] Homepage loads correctly
- [ ] All navigation links work
- [ ] Dark/light theme toggle functions
- [ ] Search functionality works
- [ ] Responsive design on mobile/tablet/desktop
- [ ] All pages return HTTP 200
- [ ] No console errors
- [ ] SEO meta tags present

## 🔒 Security Considerations

### 1. Repository Security

- **Branch Protection**: Enable for main branch
- **Two-Factor Authentication**: Required for maintainers
- **Dependabot**: Enable for dependency security updates

### 2. Content Security

- **Review Process**: All content should be reviewed for authenticity
- **Contributor Vetting**: Verify contributors for Islamic content
- **Regular Audits**: Periodic review of all published content

## 📊 Monitoring and Maintenance

### 1. GitHub Actions Monitoring

- Check workflow runs regularly
- Monitor build times and failures
- Set up notifications for failed deployments

### 2. Site Performance

- Monitor page load times
- Check Core Web Vitals
- Optimize images and assets

### 3. SEO and Analytics

- Set up Google Analytics (if desired)
- Monitor search engine indexing
- Update sitemap regularly

## 🚨 Troubleshooting

### Common Issues

1. **Build Fails**
   - Check Node.js version compatibility
   - Verify all dependencies installed
   - Check for syntax errors

2. **Deployment Fails**
   - Verify GitHub Actions permissions
   - Check workflow syntax
   - Review build logs for errors

3. **Site Not Loading**
   - Wait a few minutes after deployment
   - Check GitHub Pages status
   - Verify custom domain DNS settings

### Debug Commands

```bash
# Clean build
npm run clean
npm install
npm run build

# Check configuration
npx astro check

# Preview locally
npm run preview
```

## 📞 Support

For deployment issues:

1. Check GitHub Actions logs
2. Review this guide for common solutions
3. Create an issue in the repository
4. Contact the project maintainers

---

**Note**: This setup assumes you have a GitHub personal access token with repository permissions. The token is only needed for initial repository creation via API.