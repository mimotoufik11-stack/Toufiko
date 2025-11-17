import { readFile } from 'node:fs/promises';
import { join } from 'node:path';
import matter from 'gray-matter';
import { remark } from 'remark';
import remarkHtml from 'remark-html';

export interface MarkdownDocument {
  frontmatter: Record<string, unknown>;
  markdown: string;
  html: string;
}

export const readMarkdown = async (relativePath: string): Promise<MarkdownDocument> => {
  const filePath = join(process.cwd(), 'content', relativePath);
  const raw = await readFile(filePath, 'utf-8');
  const { data, content } = matter(raw);
  const processed = await remark().use(remarkHtml).process(content);

  return {
    frontmatter: data,
    markdown: content,
    html: String(processed)
  };
};

export default readMarkdown;
