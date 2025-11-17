# Contributing to Salafi Science Network

First off, thank you for considering contributing to the Salafi Science Network! It's people like you that make this project a great resource for the community.

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [How Can I Contribute?](#how-can-i-contribute)
- [Development Guidelines](#development-guidelines)
- [Pull Request Process](#pull-request-process)
- [Style Guide](#style-guide)
- [Content Guidelines](#content-guidelines)

## 📜 Code of Conduct

This project and everyone participating in it is governed by our [Code of Conduct](CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code. Please report unacceptable behavior to the project maintainers.

## 🚀 Getting Started

### Prerequisites

- Node.js 18.x or higher
- npm or yarn
- Git
- A GitHub account

### Setting Up Your Development Environment

1. **Fork the repository** on GitHub

2. **Clone your fork locally**
   ```bash
   git clone https://github.com/YOUR-USERNAME/Toufiko.git
   cd Toufiko
   ```

3. **Add upstream remote**
   ```bash
   git remote add upstream https://github.com/mimotoufik11-stack/Toufiko.git
   ```

4. **Install dependencies**
   ```bash
   npm install
   ```

5. **Create a branch for your changes**
   ```bash
   git checkout -b feature/your-feature-name
   ```

6. **Start the development server**
   ```bash
   npm run dev
   ```

## 🤝 How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check existing issues to avoid duplicates. When you create a bug report, include as many details as possible:

- **Use a clear and descriptive title**
- **Describe the exact steps to reproduce the problem**
- **Provide specific examples** (code snippets, screenshots)
- **Describe the behavior you observed and what you expected**
- **Include your environment details** (OS, Node version, browser)

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion:

- **Use a clear and descriptive title**
- **Provide a detailed description** of the suggested enhancement
- **Explain why this enhancement would be useful** to most users
- **List any alternatives** you've considered

### Contributing Code

We welcome code contributions! Here are areas where you can help:

#### Content Contributions
- Adding new articles, lessons, or scholarly content
- Improving existing content quality
- Translating content to other languages
- Adding audio transcriptions

#### Feature Contributions
- Implementing new features
- Improving search functionality
- Enhancing UI/UX
- Adding accessibility features
- Performance optimizations

#### Bug Fixes
- Fixing reported bugs
- Addressing security vulnerabilities
- Fixing broken links or typos

## 💻 Development Guidelines

### Project Structure

```
├── components/     # Reusable Astro components
├── content/        # Markdown content files
├── src/
│   ├── layouts/    # Page layouts
│   └── pages/      # Route components
├── scripts/        # Utility scripts
└── styles/         # Global styles
```

### Code Standards

- **TypeScript**: Use TypeScript for type safety
- **Components**: Create reusable, single-purpose components
- **Styling**: Use Tailwind CSS utilities; avoid custom CSS when possible
- **Accessibility**: Ensure all UI is keyboard navigable and screen-reader friendly
- **Performance**: Keep bundle sizes small; use lazy loading where appropriate

### Testing Your Changes

Before submitting a pull request:

1. **Build the project**
   ```bash
   npm run build
   ```

2. **Preview the production build**
   ```bash
   npm run preview
   ```

3. **Test all affected pages** in multiple browsers
4. **Verify responsive design** on mobile, tablet, and desktop
5. **Test dark/light mode** functionality
6. **Check accessibility** with keyboard navigation

## 🔄 Pull Request Process

1. **Update your fork** with the latest upstream changes
   ```bash
   git fetch upstream
   git rebase upstream/main
   ```

2. **Ensure your code follows the style guide** (see below)

3. **Write clear, concise commit messages**
   ```
   type(scope): subject
   
   body (optional)
   
   footer (optional)
   ```
   
   Types: `feat`, `fix`, `docs`, `style`, `refactor`, `perf`, `test`, `chore`
   
   Examples:
   - `feat(search): add advanced filtering options`
   - `fix(nav): resolve mobile menu toggle issue`
   - `docs(readme): update installation instructions`

4. **Push to your fork**
   ```bash
   git push origin feature/your-feature-name
   ```

5. **Create a Pull Request** on GitHub with:
   - Clear title describing the change
   - Detailed description of what changed and why
   - Screenshots (for UI changes)
   - Reference to related issues (if applicable)

6. **Respond to review feedback** promptly

7. **Ensure CI checks pass** before requesting final review

### Pull Request Checklist

- [ ] Code builds without errors
- [ ] All pages load correctly
- [ ] No console errors or warnings
- [ ] Responsive design works on all screen sizes
- [ ] Dark/light mode functions properly
- [ ] Accessibility standards maintained
- [ ] No broken links
- [ ] Commit messages follow convention
- [ ] Documentation updated (if needed)

## 🎨 Style Guide

### TypeScript/JavaScript

- Use TypeScript for type safety
- Use ES6+ features
- Use `const` and `let`, not `var`
- Use arrow functions for callbacks
- Use template literals for string concatenation
- Add JSDoc comments for complex functions

```typescript
// Good
const getContentItems = async (category: string): Promise<ContentItem[]> => {
  // Implementation
};

// Bad
var getContentItems = function(category) {
  // Implementation
};
```

### Astro Components

- Keep components focused and single-purpose
- Use TypeScript for component props
- Extract reusable logic to utilities
- Use semantic HTML elements

```astro
---
interface Props {
  title: string;
  description?: string;
}

const { title, description } = Astro.props;
---

<article class="content-card">
  <h2>{title}</h2>
  {description && <p>{description}</p>}
</article>
```

### CSS/Tailwind

- Use Tailwind utilities first
- Use CSS variables for theme values
- Keep custom CSS minimal
- Follow mobile-first responsive design

```astro
<!-- Good: Tailwind utilities -->
<div class="flex items-center gap-4 p-4 bg-white dark:bg-gray-800">
  <!-- Content -->
</div>

<!-- Avoid: Inline styles -->
<div style="display: flex; padding: 1rem;">
  <!-- Content -->
</div>
```

### File Naming

- Use kebab-case for files: `content-card.astro`
- Use PascalCase for component names: `ContentCard`
- Use lowercase for directories: `components`, `layouts`

## 📝 Content Guidelines

### Adding Content

Content is stored in the `content/` directory as Markdown files.

#### Frontmatter Structure

```markdown
---
title: "Content Title"
description: "Brief description (150-200 characters)"
category: "articles" # or books, fatwas, lessons, scholars
author: "Author Name"
date: "2024-01-15"
tags: ["tag1", "tag2"]
---

Your content here in Markdown format.
```

#### Content Quality Standards

- **Accuracy**: Ensure all Islamic content is authentic and properly sourced
- **Citations**: Include references and sources for scholarly content
- **Clarity**: Write clear, understandable content
- **Formatting**: Use proper Markdown formatting
- **Length**: Aim for comprehensive but concise content

#### Markdown Best Practices

- Use semantic heading levels (`#`, `##`, `###`)
- Include alt text for images
- Use lists for better readability
- Add line breaks between sections
- Use code blocks for technical content

### Content Categories

- **Articles**: Essays, discussions, and explanatory content
- **Books**: Book summaries, excerpts, and reviews
- **Fatwas**: Islamic rulings and scholarly opinions
- **Lessons**: Educational content and lectures
- **Scholars**: Biographies and profiles

## 🙋 Questions?

If you have questions about contributing:

- Check the [README.md](README.md) for project overview
- Review existing [GitHub Issues](https://github.com/mimotoufik11-stack/Toufiko/issues)
- Open a new issue with the `question` label
- Contact the maintainers

## 📌 Additional Resources

- [Astro Documentation](https://docs.astro.build/)
- [Tailwind CSS Documentation](https://tailwindcss.com/docs)
- [Markdown Guide](https://www.markdownguide.org/)
- [GitHub Flow Guide](https://guides.github.com/introduction/flow/)

---

Thank you for contributing to Salafi Science Network! 🙏
