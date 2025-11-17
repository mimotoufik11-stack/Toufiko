# Salafi Science Network

A static website powered by [Astro](https://astro.build/) for sharing authentic Islamic knowledge, curated lessons, and
scholarly resources for the Salafi Science Network.

## Tech stack

- [Astro](https://astro.build/) for the site framework and static generation
- [Tailwind CSS](https://tailwindcss.com/) for utility-first styling and rapid component design
- [Fuse.js](https://fusejs.io/) to provide fast client-side search across curated resources
- [Remark](https://remark.js.org/) utilities for processing Markdown-based content stored under the `content/` directory
- [feed](https://github.com/jpmonette/feed) to support future RSS feed generation

## Project structure

```
├── astro.config.mjs        # Astro configuration with custom aliases
├── components/             # Reusable UI components (header, footer, cards, search, etc.)
├── content/                # Markdown content (articles, books, fatwas, lessons, scholars)
├── public/                 # Static assets served as-is (favicons, images)
├── scripts/                # Utility scripts for theming, search data, markdown helpers, RSS
├── src/
│   ├── layouts/            # Shared layout templates for different content types
│   └── pages/              # Route components (Home, Lessons, Audio, Books, Articles, Fatwas, Scholars, About, Contact, Privacy)
├── styles/                 # Global styling and Tailwind entrypoint
├── tailwind.config.cjs     # Tailwind configuration with theme colors mapped to CSS variables
└── tsconfig.json           # TypeScript configuration with path aliases
```

## Development

```bash
npm install
npm run dev
```

## Build

```bash
npm run build
```

Preview the production build locally with:

```bash
npm run preview
```

---

The project ships with a dark/light theme system powered by CSS variables. Users can toggle the theme using the control
in the header; preferences persist across visits using `localStorage`.
