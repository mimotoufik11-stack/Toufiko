const STORAGE_KEY = 'ssn-theme';

type Theme = 'light' | 'dark';

const prefersDarkScheme = () => window.matchMedia('(prefers-color-scheme: dark)').matches;

export const resolveTheme = (): Theme => {
  const stored = window.localStorage.getItem(STORAGE_KEY) as Theme | null;
  if (stored === 'light' || stored === 'dark') {
    return stored;
  }
  return prefersDarkScheme() ? 'dark' : 'light';
};

export const applyTheme = (theme: Theme) => {
  const root = document.documentElement;
  root.dataset.theme = theme;
  root.classList.toggle('dark', theme === 'dark');
};

export const setTheme = (theme: Theme) => {
  window.localStorage.setItem(STORAGE_KEY, theme);
  applyTheme(theme);
};

export const toggleTheme = () => {
  const current = resolveTheme();
  const next: Theme = current === 'dark' ? 'light' : 'dark';
  setTheme(next);
  return next;
};

export const initThemeToggle = () => {
  const buttons = Array.from(document.querySelectorAll<HTMLButtonElement>('[data-theme-toggle]'));
  if (!buttons.length) return;

  const syncState = (theme: Theme) => {
    buttons.forEach((button) => {
      button.dataset.state = theme;
      button.setAttribute('aria-pressed', theme === 'dark' ? 'true' : 'false');
    });
  };

  const activeTheme = resolveTheme();
  applyTheme(activeTheme);
  syncState(activeTheme);

  buttons.forEach((button) => {
    if (!button.dataset.themeToggleAttached) {
      button.dataset.themeToggleAttached = 'true';
      button.addEventListener('click', () => {
        const next = toggleTheme();
        syncState(next);
      });
    }
  });

  window
    .matchMedia('(prefers-color-scheme: dark)')
    .addEventListener('change', (event) => {
      const stored = window.localStorage.getItem(STORAGE_KEY);
      if (stored) return;
      const theme: Theme = event.matches ? 'dark' : 'light';
      applyTheme(theme);
      syncState(theme);
    });
};

export default initThemeToggle;
