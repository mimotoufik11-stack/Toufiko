import { readFile } from 'node:fs/promises';
import { join } from 'node:path';
import { remark } from 'remark';

type Frontmatter = Record<string, string>;

export interface MarkdownDocument {
  frontmatter: Frontmatter;
  markdown: string;
  html: string;
}

const FRONTMATTER_PATTERN = /^---\n([\s\S]*?)\n---/;

const parseFrontmatter = (raw: string): { frontmatter: Frontmatter; body: string } => {
  const match = raw.match(FRONTMATTER_PATTERN);
  if (!match) {
    return { frontmatter: {}, body: raw };
  }

  const frontmatter: Frontmatter = {};
  match[1]
    .split('\n')
    .map((line) => line.trim())
    .filter(Boolean)
    .forEach((line) => {
      const [key, ...rest] = line.split(':');
      if (key) {
        frontmatter[key.trim()] = rest.join(':').trim();
      }
    });

  const body = raw.slice(match[0].length).trim();
  return { frontmatter, body };
};

export const readMarkdown = async (relativePath: string): Promise<MarkdownDocument> => {
  const filePath = join(process.cwd(), 'content', relativePath);
  const raw = await readFile(filePath, 'utf-8');
  const { frontmatter, body } = parseFrontmatter(raw);
  const processed = await remark().process(body);

  return {
    frontmatter,
    markdown: body,
    html: String(processed)
  };
};

export default readMarkdown;
