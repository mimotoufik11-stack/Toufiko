# GitHub Pages Deployment Guide - Salafi Science Site

## Final Website Information

### Repository Details
- **Repository Name**: `salafi-science-site`
- **Owner Username**: `mimotoufik11-stack`
- **GitHub Repository URL**: `https://github.com/mimotoufik11-stack/salafi-science-site`
- **Final Website URL**: `https://mimotoufik11-stack.github.io/salafi-science-site/`

### Project Information
- **Project Name**: Salafi Science Network
- **Tech Stack**: Astro 5.15.9, Tailwind CSS 3.4.14, Fuse.js 7.1.0
- **Build Output**: Static HTML (10 pages)
- **Content Items**: 20 items across 5 categories
- **Deployment Method**: GitHub Pages (automatic from main branch)

## Deployment Steps

### Step 1: Create the GitHub Repository

If the repository hasn't been created yet:

1. Go to https://github.com/new
2. Set **Repository name** to: `salafi-science-site`
3. Select **Public** (for GitHub Pages)
4. Do NOT initialize with README, .gitignore, or license (we have these)
5. Click **Create repository**

### Step 2: Update Remote and Push

```bash
# Update the git remote to point to the new repository
git remote set-url origin https://github.com/mimotoufik11-stack/salafi-science-site.git

# Push the current branch to the new repository
git push -u origin verify/salafi-science-site-url-deployment

# Push main branch
git push -u origin main
```

### Step 3: Enable GitHub Pages

1. Go to the repository on GitHub
2. Navigate to **Settings** → **Pages**
3. Under **Build and deployment**:
   - **Source**: Select "Deploy from a branch"
   - **Branch**: Select `main` and folder `/root`
4. Click **Save**

### Step 4: Configure Branch Protection (Optional)

1. Go to **Settings** → **Branches**
2. Click **Add rule** under **Branch protection rules**
3. Set **Branch name pattern** to `main`
4. Enable desired protections
5. Click **Create**

## Website URL and Features

### Live Website
- **Final URL**: `https://mimotoufik11-stack.github.io/salafi-science-site/`
- **Status**: Ready for deployment
- **HTTPS**: Automatic with GitHub Pages

### Features Implemented ✓
- ✅ Homepage with search and filters
- ✅ Navigation menu (Lessons, Audio, Books, Articles, Fatwas, Scholars, About, Contact)
- ✅ Dark/Light mode toggle (theme persists in localStorage)
- ✅ Client-side search using Fuse.js
- ✅ Category filtering
- ✅ Responsive design (mobile, tablet, desktop)
- ✅ SEO optimization (meta tags, sitemap, robots.txt)
- ✅ Accessibility features (WCAG 2.1 Level AA compliant)

### Content Categories (20 Items)

1. **Lessons** (5 items)
   - Islamic Tawheed Foundations
   - Quranic Exegesis Principles
   - Islamic Jurisprudence Fundamentals
   - Hadith Classification and Sciences
   - Islamic Purification Practices

2. **Articles** (6 items)
   - The Five Pillars of Islam
   - Understanding Quran's Message
   - Islamic Family Relations
   - Financial Ethics in Islam
   - Women's Rights in Islam
   - Knowledge Seeking in Islam

3. **Books** (4 items)
   - Sharh us-Sunnah (Explanation of the Sunnah)
   - Islamic Jurisprudence Foundations
   - Quran and Its Divine Message
   - Prophet Muhammad Biography

4. **Fatwas** (2 items)
   - Wealth Purification and Zakat
   - Modern Technology and Shariah

5. **Scholars** (3 items)
   - Sheikh Muhammad ibn Abdul-Wahhab ibn Baz
   - Sheikh Muhammad ibn Saleh al-Uthaymeen
   - Sheikh Muhammad Nasir-ud-Din al-Albani

## Build Information

### Build Command
```bash
npm run build
```

### Build Output
- **Location**: `/dist/` directory
- **Build Time**: ~2 seconds
- **Output Format**: Static HTML pages
- **Pages Generated**: 10 (home, lessons, audio, books, articles, fatwas, scholars, about, contact, privacy)
- **Assets**: CSS, JavaScript, fonts, images in `/_astro/` directory

### Production Preview
```bash
npm run preview
```

## Project Structure

```
salafi-science-site/
├── src/
│   ├── pages/              # Page components
│   │   ├── index.astro    # Home page
│   │   ├── lessons.astro
│   │   ├── articles.astro
│   │   ├── books.astro
│   │   ├── fatwas.astro
│   │   ├── scholars.astro
│   │   ├── audio.astro
│   │   ├── about.astro
│   │   ├── contact.astro
│   │   └── privacy.astro
│   └── layouts/           # Layout templates
├── components/            # Reusable UI components
├── content/              # Markdown content
│   ├── articles/         # 6 article files
│   ├── books/            # 4 book files
│   ├── fatwas/           # 2 fatwa files
│   ├── lessons/          # 5 lesson files
│   ├── scholars/         # 3 scholar files
│   └── index.json        # Content metadata
├── scripts/              # Utility scripts
│   ├── search-data.ts    # Search index
│   ├── theme-toggle.ts   # Theme switching
│   └── markdown.ts       # Markdown utilities
├── styles/              # Global styles
├── public/              # Static assets
│   ├── robots.txt       # SEO: Search engine directives
│   ├── sitemap.xml      # SEO: Site map with 30 URLs
│   └── favicon.svg
├── dist/                # Built output (after npm run build)
├── astro.config.mjs     # Astro configuration
├── tailwind.config.cjs  # Tailwind CSS configuration
├── package.json         # Dependencies
└── tsconfig.json        # TypeScript configuration
```

## Deployment Verification Checklist

After deployment, verify the following:

### ✅ Homepage
- [ ] Website loads at https://mimotoufik11-stack.github.io/salafi-science-site/
- [ ] Homepage displays correctly
- [ ] Header and navigation visible
- [ ] Logo/branding visible

### ✅ Navigation
- [ ] All menu links work (Lessons, Audio, Books, Articles, Fatwas, Scholars, About, Contact)
- [ ] Links navigate to correct pages
- [ ] Back button works
- [ ] All page URLs follow pattern: `/salafi-science-site/[page]/`

### ✅ Search & Filter
- [ ] Search box is visible on homepage
- [ ] Search functionality works
- [ ] Typing returns relevant results
- [ ] Search results are clickable
- [ ] Category filters work (if implemented)

### ✅ Theme Toggle
- [ ] Dark/Light mode toggle button visible in header
- [ ] Toggle switches between dark and light theme
- [ ] Theme preference persists on page reload
- [ ] All pages display correctly in both themes

### ✅ Responsive Design
- **Desktop** (1920px):
  - [ ] Two-column layout displays correctly
  - [ ] Navigation is horizontal
  - [ ] Content is well-spaced

- **Tablet** (768px):
  - [ ] Layout adapts properly
  - [ ] Navigation remains accessible
  - [ ] Content readable

- **Mobile** (375px):
  - [ ] Single-column layout
  - [ ] Mobile navigation menu works
  - [ ] Touch-friendly buttons (44px minimum)
  - [ ] Text is readable without zooming

### ✅ SEO & Metadata
- [ ] Open Graph meta tags present
- [ ] Twitter Card meta tags present
- [ ] Canonical URLs correct
- [ ] Robots.txt accessible at `/salafi-science-site/robots.txt`
- [ ] Sitemap.xml accessible at `/salafi-science-site/sitemap.xml`

### ✅ Content
- [ ] All 20 content items display
- [ ] 5 Lessons visible
- [ ] 6 Articles visible
- [ ] 4 Books visible
- [ ] 2 Fatwas visible
- [ ] 3 Scholars visible

### ✅ Performance
- [ ] Homepage loads in < 3 seconds
- [ ] Images load properly
- [ ] No console errors
- [ ] No broken links

## Troubleshooting

### Site not deploying
1. Verify the repository is public
2. Check GitHub Pages settings (Settings → Pages)
3. Ensure `main` branch is selected
4. Wait 1-2 minutes for deployment to complete
5. Check Actions tab for deployment status

### CSS/Styling not loading
1. Verify base path in `astro.config.mjs`: `base: '/salafi-science-site/'`
2. Rebuild: `npm run build`
3. Clear browser cache (Ctrl+Shift+Del)
4. Verify file links start with `/salafi-science-site/`

### Search not working
1. Verify `search-data.ts` is present in `/scripts/`
2. Check browser console for errors
3. Verify Fuse.js library loaded
4. Rebuild and redeploy

### Dark mode not working
1. Verify `theme-toggle.ts` is present
2. Check `localStorage` is enabled in browser
3. Verify CSS variables are applied
4. Check browser console for errors

## Additional Resources

- [Astro Documentation](https://docs.astro.build/)
- [GitHub Pages Documentation](https://docs.github.com/en/pages)
- [Tailwind CSS Documentation](https://tailwindcss.com/docs)
- [Fuse.js Documentation](https://fusejs.io/)

## Configuration Files

### astro.config.mjs
```javascript
export default defineConfig({
  srcDir: 'src',
  integrations: [tailwind()],
  base: '/salafi-science-site/',
  site: 'https://mimotoufik11-stack.github.io/'
});
```

### Key Environment
- Node.js version: 18+
- npm version: 8+
- Package manager: npm

## Support & Maintenance

### Regular Tasks
- Update content in `/content/` directories
- Rebuild with `npm run build` after changes
- Push changes to GitHub (auto-deploys)
- Monitor Google Search Console for indexing

### Content Updates
- Add new items to `/content/[category]/` directories
- Update `/content/index.json` with metadata
- Update `/scripts/search-data.ts` with search entries
- Rebuild and push to GitHub

## Deployment Timeline

**Expected timeline from start to live:**
- Repository creation: 1 minute
- First deployment: 2-3 minutes
- DNS propagation: Already complete (GitHub Pages)
- Total: ~5 minutes to live website

## Final Notes

- Site is fully static (no backend server needed)
- All content is markdown-based for easy updates
- Search is client-side (no server processing)
- Dark mode persists with localStorage
- Fully responsive and mobile-friendly
- SEO-optimized with sitemap and robots.txt
- Ready for production use

**Website Status**: ✅ READY FOR DEPLOYMENT

---

For any questions or issues, refer to the Astro documentation or GitHub Pages documentation.
