# Contributing to Salafi Science Network

Thank you for your interest in contributing to the Salafi Science Network! We appreciate your time and effort to help improve this project.

## Code of Conduct

Please review our [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) before contributing. By participating in this project, you agree to follow our community standards.

## How Can You Contribute?

### 1. Reporting Bugs

If you find a bug, please report it by opening an issue:

- **Use a clear, descriptive title**
- **Describe the exact steps to reproduce the problem**
- **Provide specific examples**
- **Describe the behavior you observed**
- **Explain what behavior you expected**
- **Include screenshots or logs if applicable**
- **Include your environment** (OS, browser, Node version)

### 2. Suggesting Enhancements

Enhancement suggestions are welcome:

- **Use a clear, descriptive title**
- **Provide a step-by-step description**
- **Provide specific examples**
- **Describe the current behavior and what you'd like instead**
- **Explain why this enhancement would benefit users**

### 3. Submitting Content

We welcome contributions of Islamic knowledge content:

- **Content must be:**
  - Accurate and authentic Islamic knowledge
  - Properly sourced with references
  - 200+ words for substantive content
  - Appropriate for all ages
  - Original or properly attributed

- **Content categories:**
  - Articles
  - Books
  - Fatwas
  - Lessons
  - Scholars

- **Submission format:**
  1. Create markdown file: `content/[category]/[slug].md`
  2. Follow frontmatter template (see below)
  3. Include proper sourcing and attribution
  4. Submit pull request with description

#### Content Frontmatter Template

```markdown
---
id: "unique-identifier"
title: "Content Title"
category: "articles|books|fatwas|lessons|scholars"
slug: "content-slug"
description: "Brief description (50-150 characters)"
author: "Content Author or Source"
date: 2024-01-15
tags: ["tag1", "tag2", "tag3"]
source: "https://source-url.com"
---

# Content Title

Your content here...
```

### 4. Code Contributions

#### Setting Up Development Environment

```bash
# Fork the repository on GitHub
# Clone your fork
git clone https://github.com/YOUR-USERNAME/salafi-science-site.git
cd salafi-science-site

# Add upstream remote
git remote add upstream https://github.com/mimotoufik11-stack/salafi-science-site.git

# Install dependencies
npm install

# Create your feature branch
git checkout -b feature/your-feature-name
```

#### Development Workflow

```bash
# Start development server
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview
```

#### Code Style Guidelines

- **Follow existing code patterns** in the project
- **Use TypeScript** for new components
- **Use meaningful variable and function names**
- **Add comments only for complex logic**
- **Keep components focused and reusable**
- **Follow Astro component conventions**

#### Component Structure

```astro
---
import { someFunction } from '../scripts/utility';
import Layout from '../layouts/RootLayout.astro';

interface Props {
  title: string;
  content: string;
}

const { title, content } = Astro.props;
---

<Layout>
  <div class="component">
    <h1>{title}</h1>
    <p>{content}</p>
  </div>
</Layout>

<style>
  .component {
    padding: 1rem;
  }
</style>
```

#### Styling

- Use **Tailwind CSS** utility classes
- For complex styles, add to `/styles/global.css`
- Follow **CSS variable naming** conventions
- Ensure **dark mode support** for all styles
- Use **responsive design** patterns (mobile-first)

### 5. Submitting Pull Requests

1. **Fork the repository** and create your feature branch
2. **Make your changes** with clear, descriptive commits
3. **Test your changes** locally
4. **Push to your fork**
5. **Submit a pull request** with a clear description

#### Pull Request Template

```markdown
## Description
Brief description of changes

## Related Issues
Fixes #(issue number)

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Content addition
- [ ] Documentation update
- [ ] Style improvement

## How Has This Been Tested?
Describe testing approach

## Checklist
- [ ] Changes follow code style guidelines
- [ ] Comments added for complex logic
- [ ] Documentation updated
- [ ] Build completes without errors
- [ ] No new warnings introduced
```

## Development Guidelines

### Git Workflow

```bash
# Keep your branch up to date
git fetch upstream
git rebase upstream/main

# Make atomic commits
git commit -m "type: brief description"

# Commit types: feat, fix, docs, style, refactor, test, chore
```

### Testing

- Test locally before submitting: `npm run build`
- Verify responsive design at multiple breakpoints
- Test dark/light mode toggle
- Verify all links work
- Check search functionality with new content

### Documentation

- Update README if adding new features
- Add inline comments for complex logic
- Update this CONTRIBUTING.md if needed
- Maintain consistent markdown formatting

## Community

- **Discussion**: Use GitHub Discussions for questions
- **Issues**: Use GitHub Issues for bugs and features
- **Emails**: For sensitive matters, contact maintainers privately

## License

By contributing, you agree that your contributions will be licensed under the MIT License.

## Questions?

Feel free to open an issue or discussion if you have questions about contributing.

Thank you for contributing to Salafi Science Network! 🙏

---

**Last Updated**: 2024  
**Maintained by**: Salafi Science Network  
الشبكة العلمية السلفية
