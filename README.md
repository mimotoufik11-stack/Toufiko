# Salafi Science Network - Static Islamic Knowledge Portal

الشبكة العلمية السلفية

A comprehensive static website built with [Astro](https://astro.build/) for sharing authentic Islamic knowledge, curated lessons, scholarly resources, and Quranic guidance in English and Arabic.

[![Deploy to GitHub Pages](https://github.com/mimotoufik11-stack/salafi-science-site/actions/workflows/deploy.yml/badge.svg)](https://github.com/mimotoufik11-stack/salafi-science-site/actions/workflows/deploy.yml)

## Features

✨ **Static Site Generation** - Fast, secure, and SEO-friendly  
🔍 **Full-Text Search** - Powered by Fuse.js for instant search results  
🎨 **Dark/Light Theme** - Automatic theme switching with localStorage persistence  
📱 **Responsive Design** - Optimized for mobile, tablet, and desktop  
🌍 **Multi-language Support** - English and Arabic content  
🏷️ **Advanced Filtering** - Filter by category, author, date, and tags  
🔗 **SEO Optimized** - Meta tags, sitemap, robots.txt, Open Graph support  
📊 **RSS Feed** - Subscribe to content updates  
♿ **Accessible** - WCAG 2.1 Level AA compliant  

## Tech Stack

- **Framework**: [Astro 5.15.9](https://astro.build/) - Zero-JavaScript by default
- **Styling**: [Tailwind CSS 3.4.14](https://tailwindcss.com/) - Utility-first CSS
- **Search**: [Fuse.js 7.1.0](https://fusejs.io/) - Fuzzy search engine
- **Markdown**: [Remark 15.0.1](https://remark.js.org/) - Markdown processor
- **RSS**: [Feed 5.1.0](https://github.com/jpmonette/feed) - Feed generation

## Project Structure

```
salafi-science-site/
├── .github/
│   └── workflows/
│       └── deploy.yml          # GitHub Actions workflow
├── components/                 # Reusable Astro components
│   ├── Header.astro
│   ├── Footer.astro
│   ├── SearchBar.astro
│   ├── ThemeToggle.astro
│   └── ContentCard.astro
├── content/                    # Markdown content files
│   ├── index.json              # Content metadata index
│   ├── articles/               # Islamic articles (6 items)
│   ├── books/                  # Islamic books (4 items)
│   ├── fatwas/                 # Religious fatwas (2 items)
│   ├── lessons/                # Islamic lessons (5 items)
│   └── scholars/               # Islamic scholars (3 items)
├── public/                     # Static assets
│   ├── favicon.svg
│   ├── robots.txt              # Search engine directives
│   └── sitemap.xml             # SEO sitemap
├── scripts/                    # Utility scripts
│   ├── search-data.ts          # Search indexing
│   ├── theme-toggle.ts         # Theme switching
│   ├── markdown.ts             # Markdown utilities
│   └── rss.ts                  # RSS generation
├── src/
│   ├── layouts/                # Reusable page layouts
│   │   ├── RootLayout.astro
│   │   └── ContentLayout.astro
│   └── pages/                  # Site pages
│       ├── index.astro         # Homepage
│       ├── articles.astro      # Articles listing
│       ├── books.astro         # Books listing
│       ├── fatwas.astro        # Fatwas listing
│       ├── lessons.astro       # Lessons listing
│       ├── scholars.astro      # Scholars listing
│       ├── search.astro        # Search results
│       ├── about.astro         # About page
│       ├── contact.astro       # Contact page
│       └── privacy.astro       # Privacy policy
├── styles/                     # Global styles
│   └── global.css
├── astro.config.mjs            # Astro configuration
├── tailwind.config.cjs         # Tailwind configuration
├── tsconfig.json               # TypeScript configuration
├── package.json                # Dependencies
├── .gitignore                  # Git ignore rules
├── LICENSE                     # MIT License
├── CONTRIBUTING.md             # Contribution guidelines
└── CODE_OF_CONDUCT.md          # Code of conduct
```

## Content Overview

The site contains 20+ curated Islamic resources:

### Lessons (5)
- Islamic Tawheed (Monotheism)
- Quran Exegesis (Tafsir)
- Islamic Jurisprudence Principles
- Hadith Classification and Criticism
- Islamic Purification (Tahara)

### Articles (6)
- Five Pillars of Islam
- The Message of the Quran
- Family Relations in Islam
- Financial Ethics and Riba
- Women in Islamic Society
- Knowledge Seeking in Islam

### Books (4)
- Sharh us-Sunnah
- Foundations of Islamic Jurisprudence
- The Message of the Quran
- Biography of Prophet Muhammad

### Fatwas (2)
- Wealth Purification and Zakat
- Modern Technology in Islamic Practice

### Scholars (3)
- Sheikh Abdullah bin Abdulrahman al-Baz
- Sheikh Muhammad Salih al-Uthaymeen
- Sheikh Muhammad Nasiruddin al-Albani

## Getting Started

### Prerequisites

- Node.js 18+ 
- npm or yarn

### Installation

```bash
# Clone the repository
git clone https://github.com/mimotoufik11-stack/salafi-science-site.git
cd salafi-science-site

# Install dependencies
npm install
```

### Development

```bash
# Start development server at http://localhost:3000
npm run dev

# Or use the alias
npm start
```

### Build

```bash
# Generate static site to dist/ directory
npm run build

# Preview production build locally
npm run preview
```

## Usage

### Adding New Content

1. Create a new markdown file in the appropriate category directory (`content/[category]/[slug].md`)
2. Add frontmatter with metadata:

```markdown
---
id: "unique-id"
title: "Content Title"
category: "category-name"
slug: "content-slug"
description: "Brief description"
author: "Author Name"
date: 2024-01-15
tags: ["tag1", "tag2"]
source: "https://source-url.com"
---

# Content Title

Your content here...
```

3. Update `/content/index.json` with the new item metadata
4. Rebuild the site to regenerate search indexes

### Customizing Theme

The site uses CSS variables for theming. Modify colors in:

1. `/tailwind.config.cjs` - Tailwind theme configuration
2. `/styles/global.css` - CSS variables

Dark mode colors are automatically generated based on light mode configuration.

### Enabling RSS Feed

Configure RSS settings in `/scripts/rss.ts` and regenerate the site:

```bash
npm run build
```

## Deployment

### GitHub Pages

This project is configured for automatic deployment to GitHub Pages:

1. **Automatic Deployment**: Any push to the `main` branch triggers the GitHub Actions workflow
2. **Build**: The site is automatically built using `npm run build`
3. **Deploy**: Built files are deployed to GitHub Pages

#### Manual Setup

If GitHub Pages is not auto-configured:

1. Go to **Settings** → **Pages**
2. Set source to **Deploy from a branch**
3. Select branch: **gh-pages**
4. Save

### Other Hosting Platforms

The site can be deployed to any static hosting service:

- **Vercel**: Connect repository, no configuration needed
- **Netlify**: Connect repository, set build command to `npm run build`
- **AWS S3 + CloudFront**: Upload `dist/` directory
- **Docker**: Build static files and serve with Nginx

## SEO and Meta Information

The site includes comprehensive SEO optimization:

- **Meta Tags**: Title, description, keywords on all pages
- **Open Graph**: Social media preview optimization
- **Twitter Cards**: Enhanced Twitter sharing
- **Canonical URLs**: Prevent duplicate content issues
- **Sitemap**: `/public/sitemap.xml` for search engines
- **Robots.txt**: `/public/robots.txt` for crawler directives
- **Structured Data**: JSON-LD schema markup

## Performance

- **Build Time**: ~2.5 seconds
- **Pages**: 10+ static HTML pages
- **Bundle Size**: Minimal (no runtime JavaScript by default)
- **Search Index**: ~20 items, lightweight client-side search

## Browser Support

- Chrome/Edge 90+
- Firefox 88+
- Safari 14+
- Mobile browsers (iOS Safari, Chrome Mobile)

## Contributing

We welcome contributions! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## Community Standards

Please review our [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) for community guidelines.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Built with [Astro](https://astro.build/)
- Styled with [Tailwind CSS](https://tailwindcss.com/)
- Search powered by [Fuse.js](https://fusejs.io/)

## Support

For issues, questions, or suggestions:
- Open an issue on GitHub
- Check existing documentation
- Review [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines

## Links

- **Live Site**: https://mimotoufik11-stack.github.io/salafi-science-site/
- **Repository**: https://github.com/mimotoufik11-stack/salafi-science-site
- **Issues**: https://github.com/mimotoufik11-stack/salafi-science-site/issues

---

**Last Updated**: 2024  
**Maintained by**: Salafi Science Network  
الشبكة العلمية السلفية
