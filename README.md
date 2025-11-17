# Salafi Science Network

A modern static site built with [Astro](https://astro.build/) to share authentic Islamic knowledge, curated lessons, scholarly resources, and fatwas for the Salafi Science Network community.

## Project Overview

This site uses a content-driven structure powered by Markdown files and Astro's static site generation. It features responsive layouts, accessible navigation, theme control, and a fast search tool to help visitors discover material efficiently.

## Features

- 🔎 **Full-text search** powered by Fuse.js for client-side discovery across articles, books, lessons, scholars, and fatwas.
- 🗂️ **Category filters & organized content** with structure based on Markdown content collections.
- 🌗 **Dark/light mode toggle** with persistent user preference using localStorage and CSS variables.
- 📰 **RSS-ready** architecture (with `feed` integration available for extending to RSS feeds).
- 📱 **Responsive design** with Tailwind CSS for consistent experience across devices.

## Tech Stack

- [Astro 5](https://docs.astro.build/) for static site generation and routing
- [Tailwind CSS](https://tailwindcss.com/) for utility-first styling
- [Fuse.js](https://fusejs.io/) for flexible and fast search across site content
- [Remark](https://remark.js.org/) for Markdown content processing
- [Node.js 18+](https://nodejs.org/) for local development tooling

## Installation

Clone the repository and install dependencies:

```bash
npm install
```

## Development

Start the development server with hot reloading:

```bash
npm run dev
```

Visit `http://localhost:4321` to preview the site during development.

## Build

Generate optimized production assets to the `dist/` directory:

```bash
npm run build
```

To preview the production build locally, run:

```bash
npm run preview
```

## Deployment

### Automated Deployment (GitHub Actions)

The project is configured for automated deployment to GitHub Pages:

1. **Push to main**: Pushing to the `main` branch triggers the GitHub Actions workflow (`.github/workflows/deploy.yml`).
2. **Automatic build**: The workflow installs dependencies, builds the site, and publishes to the `gh-pages` branch.
3. **Authentication**: Uses the default `GITHUB_TOKEN` (no manual setup needed).

**Setup GitHub Pages:**
- Go to your repository Settings → Pages
- Select "Deploy from a branch"
- Choose `gh-pages` branch as the source
- Save

### Manual Deployment

For manual deployment, run:

```bash
npm run deploy
```

This builds the site and pushes the `dist/` directory to the `gh-pages` branch.

### Testing Locally

Always test before deploying:

```bash
npm run build
npm run preview
```

Visit `http://localhost:4321/Toufiko/` to verify the build works with the base path.

## Project Structure

```
├── astro.config.mjs        # Astro configuration, aliases, and GitHub Pages setup
├── components/             # UI components like navigation, cards, search, etc.
├── content/                # Markdown content (articles, books, fatwas, lessons, scholars)
├── public/                 # Static assets served as-is (favicons, images)
├── scripts/                # Utility scripts for theming, search data, markdown helpers, RSS
├── src/
│   ├── layouts/            # Shared layout templates for different content types
│   └── pages/              # Routes (Home, Lessons, Audio, Books, Articles, Fatwas, Scholars, About, Contact, Privacy)
├── styles/                 # Global styles and Tailwind entry point
├── tailwind.config.cjs     # Tailwind custom configuration
└── tsconfig.json           # TypeScript configuration with path aliases
```

## Contributing

We welcome contributions! Please read the [CONTRIBUTING.md](./CONTRIBUTING.md) for how to get involved and follow the [Code of Conduct](./CODE_OF_CONDUCT.md) to maintain a respectful community.

---

Built with ☕, 📚, and ✨ for the Salafi Science Network.
