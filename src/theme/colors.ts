export const colors = {
  primary: '#0f172a',
  onPrimary: '#f8fafc',
  background: '#f5f5f5',
  surface: '#ffffff',
  surfaceMuted: '#f1f5f9',
  accent: '#10b981',
  text: '#0f172a',
  textSecondary: '#475569',
  border: '#e2e8f0',
  chipBackground: '#dbeafe',
  chipText: '#1e3a8a',
  danger: '#dc2626'
} as const;

export type ColorName = keyof typeof colors;
