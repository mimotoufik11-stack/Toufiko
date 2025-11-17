import { defineConfig } from 'astro/config';
import tailwind from '@astrojs/tailwind';
import { fileURLToPath } from 'node:url';

const alias = {
  '@components': fileURLToPath(new URL('./components', import.meta.url)),
  '@styles': fileURLToPath(new URL('./styles', import.meta.url)),
  '@content': fileURLToPath(new URL('./content', import.meta.url)),
  '@layouts': fileURLToPath(new URL('./src/layouts', import.meta.url)),
  '@scripts': fileURLToPath(new URL('./scripts', import.meta.url))
};

export default defineConfig({
  srcDir: 'src',
  integrations: [tailwind()],
  site: 'https://mimotoufik11-stack.github.io/Toufiko/',
  base: '/Toufiko/',
  outDir: './dist',
  vite: {
    resolve: {
      alias
    }
  },
  markdown: {
    remarkPlugins: []
  }
});
