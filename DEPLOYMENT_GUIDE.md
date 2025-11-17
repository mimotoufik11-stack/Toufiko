# Deployment Guide

This guide explains how to deploy the Salafi Science Network website to GitHub Pages.

## 📋 Prerequisites

- GitHub account
- Repository created on GitHub: `mimotoufik11-stack/Toufiko`
- Git configured locally
- Node.js 18.x or higher installed

## 🚀 Automatic Deployment (Recommended)

The site is configured for automatic deployment using GitHub Actions. Every push to the `main` branch triggers a build and deployment.

### Initial Setup

1. **Enable GitHub Pages in Repository Settings**
   - Go to your repository on GitHub
   - Navigate to **Settings** → **Pages**
   - Under "Build and deployment":
     - Source: **GitHub Actions**
     - This enables the custom workflow

2. **Push to Main Branch**
   ```bash
   git add .
   git commit -m "Setup GitHub Pages deployment"
   git push origin main
   ```

3. **Workflow Automatically Runs**
   - Navigate to **Actions** tab in your repository
   - Watch the "Deploy to GitHub Pages" workflow execute
   - Deployment typically takes 2-3 minutes

4. **Access Your Site**
   - Once complete, visit: `https://mimotoufik11-stack.github.io/Toufiko/`
   - The URL is shown in the workflow output under the "deploy" job

### Workflow Details

The workflow (`.github/workflows/deploy.yml`) performs the following steps:

**Build Job:**
1. Checks out the repository
2. Sets up Node.js 18
3. Installs dependencies with `npm ci`
4. Builds the site with `npm run build`
5. Uploads the `dist/` directory as an artifact

**Deploy Job:**
1. Takes the artifact from the build job
2. Deploys to GitHub Pages
3. Outputs the deployment URL

### Monitoring Deployments

- **Actions Tab**: View all deployment runs, logs, and status
- **Environments**: Check the `github-pages` environment for deployment history
- **Status Badge**: Add to README:
  ```markdown
  ![Deployment](https://github.com/mimotoufik11-stack/Toufiko/actions/workflows/deploy.yml/badge.svg)
  ```

## 🔧 Manual Deployment

If you prefer to deploy manually or need to troubleshoot:

### Option 1: Using npm Script (Requires gh-pages package)

1. **Install gh-pages**
   ```bash
   npm install --save-dev gh-pages
   ```

2. **Deploy**
   ```bash
   npm run deploy
   ```

This builds the site and pushes the `dist/` directory to the `gh-pages` branch.

### Option 2: Manual Git Commands

1. **Build the site**
   ```bash
   npm run build
   ```

2. **Navigate to dist directory**
   ```bash
   cd dist
   ```

3. **Initialize git and push to gh-pages branch**
   ```bash
   git init
   git add -A
   git commit -m "Deploy to GitHub Pages"
   git push -f git@github.com:mimotoufik11-stack/Toufiko.git main:gh-pages
   ```

4. **Return to project root**
   ```bash
   cd ..
   ```

## ⚙️ Configuration

### Astro Configuration

The `astro.config.mjs` file contains GitHub Pages-specific settings:

```javascript
export default defineConfig({
  site: 'https://mimotoufik11-stack.github.io',
  base: '/Toufiko',  // Repository name
  // ... other config
});
```

**Important:**
- `site`: Your GitHub Pages domain
- `base`: Must match your repository name exactly
- Change these if deploying to a custom domain

### Custom Domain (Optional)

To use a custom domain:

1. **Add CNAME file** to `public/` directory:
   ```
   yourdomain.com
   ```

2. **Update astro.config.mjs**:
   ```javascript
   export default defineConfig({
     site: 'https://yourdomain.com',
     base: '/',  // No base path for custom domain
     // ...
   });
   ```

3. **Configure DNS** with your domain provider:
   - Add A records or CNAME record pointing to GitHub Pages
   - See: https://docs.github.com/en/pages/configuring-a-custom-domain-for-your-github-pages-site

4. **Set custom domain in GitHub**:
   - Repository **Settings** → **Pages**
   - Enter your custom domain
   - Enable "Enforce HTTPS"

## 🔍 Troubleshooting

### Workflow Fails

**Check the workflow logs:**
1. Go to **Actions** tab
2. Click on the failed workflow run
3. Expand job steps to see error messages

**Common issues:**
- **Build errors**: Check for TypeScript errors, missing imports
- **Permission errors**: Ensure workflow has proper permissions (already configured)
- **Node version**: Workflow uses Node.js 18; ensure local dev matches

### Assets Not Loading

If CSS/JS files return 404:

1. **Verify base path** in `astro.config.mjs` matches repo name
2. **Check built files**:
   ```bash
   npm run build
   grep -r "/_astro" dist/
   ```
   Should show paths like `/Toufiko/_astro/...`

3. **Clear browser cache** and hard refresh (Ctrl+Shift+R)

### Pages Return 404

1. **Ensure GitHub Pages source is set** to "GitHub Actions"
2. **Check deployment status** in Actions tab
3. **Verify URL structure**:
   - Correct: `https://mimotoufik11-stack.github.io/Toufiko/`
   - Wrong: `https://mimotoufik11-stack.github.io/` (missing repo name)

### Build Succeeds but Site Doesn't Update

1. **Hard refresh browser** (Ctrl+Shift+R or Cmd+Shift+R)
2. **Check deployment timestamp** in Actions tab
3. **Wait a few minutes** for CDN propagation
4. **Check gh-pages branch** exists and has latest commit

## 📊 Build Information

- **Build time**: ~2-3 seconds locally, ~30-60 seconds in CI
- **Output size**: ~50KB HTML, ~20KB CSS, ~5KB JS (gzipped)
- **Pages generated**: 10 static pages
- **Build output**: `dist/` directory
- **Node version**: 18.x (LTS)

## 🔐 Security

### Workflow Permissions

The workflow has minimal required permissions:
- `contents: read` - Read repository files
- `pages: write` - Deploy to GitHub Pages
- `id-token: write` - Required for GitHub Pages deployment

### Secrets

No secrets are required for basic deployment. GitHub automatically provides:
- `GITHUB_TOKEN` - For repository access
- GitHub Pages deployment token

## 📝 Deployment Checklist

Before deploying to production:

- [ ] Run `npm run build` locally without errors
- [ ] Test with `npm run preview` on port 4321
- [ ] Verify all pages load correctly
- [ ] Test responsive design on mobile/tablet/desktop
- [ ] Check dark/light mode toggle works
- [ ] Test search functionality
- [ ] Verify all navigation links work
- [ ] Check for console errors in browser dev tools
- [ ] Ensure content is accurate and properly formatted
- [ ] Review SEO meta tags on all pages
- [ ] Test accessibility with keyboard navigation
- [ ] Commit all changes to git
- [ ] Push to `main` branch

## 🔄 Deployment Workflow

```
Local Changes
     ↓
git commit
     ↓
git push origin main
     ↓
GitHub Actions Triggered
     ↓
Build Job (npm ci, npm run build)
     ↓
Upload Artifact (dist/)
     ↓
Deploy Job
     ↓
GitHub Pages Updated
     ↓
Site Live at https://mimotoufik11-stack.github.io/Toufiko/
```

## 📞 Support

If you encounter issues:

1. **Check Actions logs** for error messages
2. **Review this guide** for troubleshooting steps
3. **Check Astro documentation**: https://docs.astro.build/en/guides/deploy/github/
4. **GitHub Pages documentation**: https://docs.github.com/en/pages

## 🎉 Success!

Once deployed, your site will be accessible at:
**https://mimotoufik11-stack.github.io/Toufiko/**

The site automatically updates whenever you push changes to the `main` branch.
