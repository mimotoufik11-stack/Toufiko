# Salafi Science Network

A modern static website powered by [Astro](https://astro.build/) for sharing authentic Islamic knowledge, curated lessons, and scholarly resources for the Salafi Science Network. This platform provides a clean, accessible, and responsive interface for accessing Islamic educational content.

## 🌟 Features

- **Static Site Generation**: Lightning-fast build times with Astro 5.15.9
- **Responsive Design**: Mobile-first approach with Tailwind CSS 3.4.14
- **Dark/Light Theme**: User preference persistence with localStorage
- **Full-Text Search**: Client-side search powered by Fuse.js 7.1.0
- **Content Management**: Markdown-based content system with Remark processing
- **SEO Optimized**: Semantic HTML, meta tags, and accessibility features
- **10 Static Pages**: Home, Lessons, Audio, Books, Articles, Fatwas, Scholars, About, Contact, Privacy
- **GitHub Pages Ready**: Automated deployment with GitHub Actions

## 🛠 Tech Stack

- **Framework**: [Astro](https://astro.build/) 5.15.9 - Static site generator
- **Styling**: [Tailwind CSS](https://tailwindcss.com/) 3.4.14 - Utility-first CSS framework
- **Search**: [Fuse.js](https://fusejs.io/) 7.1.0 - Fuzzy search library
- **Content**: [Remark](https://remark.js.org/) 15.0.1 - Markdown processor
- **RSS**: [Feed](https://github.com/jpmonette/feed) 5.1.0 - RSS feed generation
- **TypeScript**: 5.9.3 - Type safety and development experience

## 📁 Project Structure

```
├── .github/
│   └── workflows/
│       └── deploy.yml          # GitHub Actions deployment workflow
├── astro.config.mjs            # Astro configuration with custom aliases
├── components/                 # Reusable UI components
│   ├── Card.astro
│   ├── Footer.astro
│   ├── Header.astro
│   ├── Layout.astro
│   ├── Search.astro
│   └── ThemeToggle.astro
├── content/                    # Markdown content
│   ├── articles/
│   ├── books/
│   ├── fatwas/
│   ├── lessons/
│   └── scholars/
├── public/                     # Static assets
├── scripts/                    # Utility scripts
│   ├── markdown.ts
│   ├── rss.ts
│   ├── search-data.ts
│   └── theme-toggle.ts
├── src/
│   ├── layouts/               # Layout templates
│   └── pages/                 # Route components
├── styles/                    # Global styling
├── LICENSE                    # MIT License
├── CONTRIBUTING.md           # Contribution guidelines
├── CODE_OF_CONDUCT.md        # Community guidelines
└── README.md                 # This file
```

## 🚀 Getting Started

### Prerequisites

- Node.js 18.0.0 or higher
- npm or yarn package manager

### Installation

1. Clone the repository:
```bash
git clone https://github.com/your-username/salafi-science-site.git
cd salafi-science-site
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```

4. Open your browser and navigate to `http://localhost:4321`

## 📜 Available Scripts

```bash
# Start development server
npm run dev

# Build for production
npm run build

# Preview production build locally
npm run preview

# Type checking
npm run type-check

# Linting
npm run lint

# Formatting
npm run format
```

## 🌐 Deployment

This project is configured for automatic deployment to GitHub Pages. The deployment workflow is triggered on:

- Push to `main` branch
- Pull requests to `main` branch

### Manual Deployment

To deploy manually:

1. Build the project:
```bash
npm run build
```

2. Deploy the `dist/` folder to your hosting provider

## 📝 Content Management

Content is managed through Markdown files in the `content/` directory. Each content type has its own subdirectory:

- `articles/` - Islamic articles and essays
- `books/` - Book recommendations and reviews
- `fatwas/` - Islamic legal rulings
- `lessons/` - Educational lessons and courses
- `scholars/` - Scholar biographies and contributions

### Adding New Content

1. Create a new Markdown file in the appropriate category folder
2. Add frontmatter with required fields:
```yaml
---
title: "Content Title"
description: "Brief description"
author: "Author Name"
date: "2024-01-01"
category: "articles"
tags: ["islam", "knowledge"]
---
```

3. Write your content in Markdown format
4. The build process will automatically include it in the site

## 🤝 Contributing

We welcome contributions! Please read our [Contributing Guidelines](CONTRIBUTING.md) for details on:

- Code of conduct
- How to submit pull requests
- Coding standards
- Content submission process

## 📋 Code of Conduct

Please read our [Code of Conduct](CODE_OF_CONDUCT.md) to understand our community standards and expectations.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- The [Astro](https://astro.build/) team for the amazing framework
- [Tailwind CSS](https://tailwindcss.com/) for the utility-first CSS framework
- The open-source community for the tools and libraries that make this project possible

## 📞 Contact

For questions, suggestions, or support:

- Create an issue in the GitHub repository
- Contact the project maintainers

## 🔗 Live Demo

The site is deployed at: `https://your-username.github.io/salafi-science-site/`

---

**Built with ❤️ for the Salafi Science Network**