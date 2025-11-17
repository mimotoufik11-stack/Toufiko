# Contributing to Salafi Science Network

Thank you for your interest in contributing to the Salafi Science Network! This document provides guidelines and information for contributors.

## 🤝 How to Contribute

We welcome contributions in many forms:

### 📝 Content Contributions

- **Articles**: Islamic articles and essays on authentic Islamic topics
- **Book Reviews**: Reviews and recommendations of beneficial Islamic books
- **Fatwas**: Authentic Islamic legal rulings from qualified scholars
- **Lessons**: Educational content and course materials
- **Scholar Biographies**: Information about reputable Islamic scholars

### 💻 Technical Contributions

- **Bug Fixes**: Report and fix issues with the website
- **Features**: Suggest and implement new features
- **Documentation**: Improve documentation and guides
- **Design**: UI/UX improvements and accessibility enhancements

## 🚀 Getting Started

### Prerequisites

- Node.js 18.0.0 or higher
- Git installed and configured
- GitHub account

### Setup

1. **Fork the Repository**
   ```bash
   # Fork the repository on GitHub, then clone your fork
   git clone https://github.com/your-username/salafi-science-site.git
   cd salafi-science-site
   ```

2. **Install Dependencies**
   ```bash
   npm install
   ```

3. **Start Development Server**
   ```bash
   npm run dev
   ```

4. **Create a Feature Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

## 📋 Content Guidelines

### Quality Standards

All content must adhere to the following principles:

- **Authenticity**: Based on the Qur'an and authentic Sunnah
- **Scholarly**: Referenced to reputable Islamic scholars
- **Clarity**: Clear, accessible language for diverse audiences
- **Accuracy**: Factually correct and well-researched
- **Respect**: Respectful tone and appropriate Islamic etiquette

### Content Format

1. **File Naming**
   - Use kebab-case: `importance-of-prayer.md`
   - Descriptive and meaningful names
   - English filenames for consistency

2. **Frontmatter Requirements**
   ```yaml
   ---
   title: "Content Title in English"
   description: "Brief description (150 characters max)"
   author: "Author Name"
   date: "2024-01-01"
   category: "articles|books|fatwas|lessons|scholars"
   tags: ["tag1", "tag2", "tag3"]
   ---
   ```

3. **Writing Guidelines**
   - Use clear, simple language
   - Include references where appropriate
   - Structure content with proper headings
   - Keep paragraphs concise (3-4 sentences max)
   - Use bullet points for lists

### Content Review Process

1. **Submission**: Create a pull request with your content
2. **Review**: Content will be reviewed for authenticity and quality
3. **Feedback**: You may receive requests for changes
4. **Approval**: Once approved, content will be merged

## 💻 Technical Guidelines

### Code Standards

- **TypeScript**: Use TypeScript for all new code
- **Formatting**: Follow Prettier configuration
- **Linting**: Adhere to ESLint rules
- **Components**: Use Astro components for UI elements
- **Styles**: Use Tailwind CSS classes

### Development Workflow

1. **Branch Naming**
   - `feature/feature-name` for new features
   - `fix/bug-description` for bug fixes
   - `docs/update-description` for documentation

2. **Commit Messages**
   - Use conventional commits:
     - `feat: add new feature`
     - `fix: resolve issue description`
     - `docs: update documentation`
     - `style: code formatting changes`
     - `refactor: code refactoring`

3. **Testing**
   - Test your changes locally
   - Ensure the site builds successfully: `npm run build`
   - Check responsive design on different screen sizes

### Pull Request Guidelines

1. **PR Description**
   - Clear title describing the change
   - Detailed description of what was changed and why
   - Screenshots for UI changes (if applicable)
   - Links to related issues

2. **PR Checklist**
   - [ ] Code follows project style guidelines
   - [ ] Self-review completed
   - [ ] Documentation updated (if needed)
   - [ ] Build passes successfully
   - [ ] No console errors or warnings

## 🐛 Bug Reports

When reporting bugs, please include:

- **Description**: Clear description of the issue
- **Steps to Reproduce**: Detailed steps to reproduce the issue
- **Expected Behavior**: What you expected to happen
- **Actual Behavior**: What actually happened
- **Environment**: Browser, OS, and device information
- **Screenshots**: If applicable

## 💡 Feature Requests

For feature requests:

- **Use Case**: Describe the problem you're trying to solve
- **Proposed Solution**: How you think the feature should work
- **Alternatives**: Any alternative solutions you've considered
- **Additional Context**: Any other relevant information

## 📧 Communication

- **Issues**: Use GitHub Issues for bug reports and feature requests
- **Discussions**: Use GitHub Discussions for general questions
- **Pull Requests**: Use PR comments for code-related discussions

## 🙏 Recognition

Contributors will be recognized in:

- Project README contributors section
- Annual contributor acknowledgments
- Special recognition for significant contributions

## 📄 License

By contributing to this project, you agree that your contributions will be licensed under the MIT License.

## 📞 Questions?

If you have questions about contributing:

- Check existing issues and discussions
- Create a new issue with the "question" label
- Contact the project maintainers

---

Thank you for contributing to the Salafi Science Network! Your efforts help spread authentic Islamic knowledge to benefit the community.