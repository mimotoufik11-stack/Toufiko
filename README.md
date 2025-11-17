# Salafi Science Network

[![Deployment Status](https://img.shields.io/badge/deployment-active-brightgreen)](https://mimotoufik11-stack.github.io/Toufiko/)
[![Built with Astro](https://astro.badg.es/v2/built-with-astro/tiny.svg)](https://astro.build)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A modern, static website for sharing authentic Islamic knowledge, curated lessons, and scholarly resources. Built with performance, accessibility, and user experience in mind.

## 🌐 Live Site

Visit: [https://mimotoufik11-stack.github.io/Toufiko/](https://mimotoufik11-stack.github.io/Toufiko/)

## ✨ Features

### Core Functionality
- **📚 Content Management**: Markdown-based content system for articles, books, fatwas, lessons, and scholar profiles
- **🔍 Advanced Search**: Fast, client-side full-text search powered by Fuse.js across all content categories
- **🎨 Dark/Light Mode**: Smooth theme toggle with localStorage persistence
- **📱 Responsive Design**: Mobile-first design that works beautifully on all devices
- **⚡ Static Site Generation**: Lightning-fast page loads with pre-rendered static HTML
- **🎯 SEO Optimized**: Semantic HTML, meta tags, and optimized content structure
- **♿ Accessible**: WCAG-compliant with semantic HTML and keyboard navigation

### Content Categories
- Lessons & Lectures
- Audio Recordings
- Books & Publications
- Articles & Essays
- Fatwas & Rulings
- Scholar Profiles

### Technical Features
- **No JavaScript Required**: Core content accessible without JS (progressive enhancement)
- **CSS Variables**: Flexible theming system with CSS custom properties
- **RSS Feed Support**: Built-in feed generation capability
- **Fast Navigation**: Client-side routing for instant page transitions
- **Optimized Assets**: Automatic image optimization and lazy loading

## 🛠️ Tech Stack

- **[Astro 5.15.9](https://astro.build/)** - Modern static site generator
- **[Tailwind CSS 3.4.14](https://tailwindcss.com/)** - Utility-first CSS framework
- **[Fuse.js 7.1.0](https://fusejs.io/)** - Fuzzy search library
- **[Remark](https://remark.js.org/)** - Markdown processing
- **[TypeScript](https://www.typescriptlang.org/)** - Type-safe development
- **[Feed](https://github.com/jpmonette/feed)** - RSS/Atom feed generation

## 📁 Project Structure

```
salafi-science-network/
├── .github/
│   └── workflows/
│       └── deploy.yml          # GitHub Actions CI/CD workflow
├── components/                 # Reusable UI components
│   ├── Header.astro           # Site header with navigation
│   ├── Footer.astro           # Site footer
│   ├── ContentCard.astro      # Content preview cards
│   ├── SearchBar.astro        # Search interface
│   └── ThemeToggle.astro      # Dark/light mode toggle
├── content/                    # Markdown content
│   ├── articles/              # Article files
│   ├── books/                 # Book entries
│   ├── fatwas/                # Fatwa rulings
│   ├── lessons/               # Lesson content
│   └── scholars/              # Scholar profiles
├── public/                     # Static assets
│   ├── favicon.ico
│   └── images/
├── scripts/                    # Utility scripts
│   ├── theme-toggle.ts        # Theme switching logic
│   ├── search-data.ts         # Search index generation
│   ├── markdown.ts            # Markdown utilities
│   └── rss.ts                 # RSS feed configuration
├── src/
│   ├── layouts/               # Page layouts
│   │   ├── BaseLayout.astro   # Base HTML structure
│   │   └── ContentLayout.astro # Content page template
│   └── pages/                 # Route components
│       ├── index.astro        # Home page
│       ├── lessons.astro      # Lessons listing
│       ├── audio.astro        # Audio resources
│       ├── books.astro        # Books listing
│       ├── articles.astro     # Articles listing
│       ├── fatwas.astro       # Fatwas listing
│       ├── scholars.astro     # Scholars directory
│       ├── about.astro        # About page
│       ├── contact.astro      # Contact page
│       └── privacy.astro      # Privacy policy
├── styles/
│   └── global.css             # Global styles & CSS variables
├── astro.config.mjs           # Astro configuration
├── tailwind.config.cjs        # Tailwind configuration
├── tsconfig.json              # TypeScript configuration
└── package.json               # Dependencies & scripts
```

## 🚀 Getting Started

### Prerequisites

- Node.js 18.x or higher
- npm or yarn package manager

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/mimotoufik11-stack/Toufiko.git
   cd Toufiko
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Start development server**
   ```bash
   npm run dev
   ```
   
   Open [http://localhost:4321](http://localhost:4321) to view the site in your browser.

## 📦 Available Scripts

| Command | Description |
|---------|-------------|
| `npm run dev` | Start development server with hot reload |
| `npm run start` | Alias for `npm run dev` |
| `npm run build` | Build production site to `dist/` directory |
| `npm run preview` | Preview production build locally |
| `npm run deploy` | Build and deploy to GitHub Pages (manual) |

## 🏗️ Building for Production

```bash
npm run build
```

This generates optimized static files in the `dist/` directory, ready for deployment.

### Preview Production Build

```bash
npm run preview
```

Serves the production build locally at [http://localhost:4321](http://localhost:4321).

## 🚢 Deployment

### Automatic Deployment (GitHub Actions)

The site automatically deploys to GitHub Pages on every push to the `main` branch using GitHub Actions.

**Workflow**: `.github/workflows/deploy.yml`

The workflow:
1. Checks out the repository
2. Installs Node.js and dependencies
3. Builds the static site
4. Deploys to `gh-pages` branch
5. Serves at `https://mimotoufik11-stack.github.io/Toufiko/`

### Manual Deployment

To deploy manually:

```bash
npm run deploy
```

This requires the `gh-pages` npm package and proper repository permissions.

### Deployment Configuration

GitHub Pages settings in `astro.config.mjs`:

```javascript
export default defineConfig({
  site: 'https://mimotoufik11-stack.github.io',
  base: '/Toufiko',
  // ... other config
});
```

## 📝 Content Management

### Adding Content

Content is managed through Markdown files in the `content/` directory.

**Example Article** (`content/articles/example.md`):

```markdown
---
title: "Article Title"
description: "Brief description of the article"
category: "articles"
author: "Author Name"
date: "2024-01-15"
---

Your article content here in Markdown format.
```

### Supported Categories

- `articles/` - Articles and essays
- `books/` - Book entries and publications
- `fatwas/` - Islamic rulings and guidance
- `lessons/` - Educational lessons and lectures
- `scholars/` - Scholar profiles and biographies

## 🎨 Theming & Customization

### CSS Variables

Theme colors are defined in `styles/global.css` using CSS variables:

```css
:root {
  --color-primary: #10b981;
  --color-background: #ffffff;
  --color-text: #1f2937;
  /* ... more variables */
}

[data-theme="dark"] {
  --color-background: #1f2937;
  --color-text: #f3f4f6;
  /* ... dark theme overrides */
}
```

### Tailwind Configuration

Customize Tailwind settings in `tailwind.config.cjs`:

```javascript
module.exports = {
  theme: {
    extend: {
      colors: {
        primary: 'var(--color-primary)',
        // ... more custom colors
      }
    }
  }
};
```

## 🧪 Testing

The project includes comprehensive testing coverage:

- ✅ All pages return HTTP 200
- ✅ Markdown rendering verified
- ✅ Responsive design (mobile, tablet, desktop)
- ✅ Dark/light mode functionality
- ✅ Search functionality
- ✅ Navigation and routing
- ✅ SEO meta tags
- ✅ Accessibility compliance

See `FINAL_TESTING_SUMMARY.md` for detailed test results.

## 🤝 Contributing

We welcome contributions! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## 📜 Code of Conduct

This project follows our [Code of Conduct](CODE_OF_CONDUCT.md). Please read it before contributing.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Built with [Astro](https://astro.build/)
- Styled with [Tailwind CSS](https://tailwindcss.com/)
- Search powered by [Fuse.js](https://fusejs.io/)
- Markdown processing by [Remark](https://remark.js.org/)

## 📞 Contact

For questions or support, please visit the [Contact page](https://mimotoufik11-stack.github.io/Toufiko/contact) on our website.

---

**Made with ❤️ for the Salafi Science Network community**
