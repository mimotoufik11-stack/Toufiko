import matter from 'gray-matter';
import { remark } from 'remark';
import remarkHtml from 'remark-html';

export type Collection = 'articles' | 'books' | 'lessons' | 'audio' | 'fatwas' | 'scholars';

export interface ContentItem {
  id: string;
  slug: string;
  title: string;
  description: string;
  collection: Collection;
  url: string;
  category?: string;
  author: string;
  scholar?: string | null;
  contentType: string;
  date: string;
  tags: string[];
  languages: string[];
  body: string;
  excerpt: string;
}

const allowedCollections: Collection[] = ['articles', 'books', 'lessons', 'audio', 'fatwas', 'scholars'];
const rawModules = import.meta.glob('../../content/**/*.md', { eager: true, as: 'raw' });

const normaliseArray = (input: unknown): string[] => {
  if (Array.isArray(input)) {
    return input.map((value) => String(value)).filter(Boolean);
  }
  if (typeof input === 'string') {
    return input
      .split(',')
      .map((value) => value.trim())
      .filter(Boolean);
  }
  return [];
};

const toIsoString = (value: unknown): string => {
  if (!value) {
    return new Date().toISOString();
  }

  const date = new Date(String(value));
  if (Number.isNaN(date.getTime())) {
    return new Date().toISOString();
  }

  return date.toISOString();
};

const createExcerpt = (body: string, length = 200): string => {
  const plain = body.replace(/[#*_`>\-]+/g, ' ').replace(/\s+/g, ' ').trim();
  if (plain.length <= length) {
    return plain;
  }
  return `${plain.slice(0, length).trim()}…`;
};

const contentItems: ContentItem[] = Object.entries(rawModules)
  .map(([path, raw]) => {
    const relative = path.split('/content/')[1];
    if (!relative) {
      return null;
    }

    const [collection, fileName] = relative.split('/');
    if (!collection || !fileName) {
      return null;
    }

    if (!allowedCollections.includes(collection as Collection)) {
      return null;
    }

    const slug = fileName.replace(/\.md$/, '');
    const { data, content } = matter(raw as string);

    const tags = normaliseArray(data.tags);
    const languages = normaliseArray(data.languages);

    return {
      id: `${collection}-${slug}`,
      slug,
      title: String(data.title ?? slug),
      description: String(data.description ?? ''),
      collection: collection as Collection,
      url: `/${collection}/${slug}`,
      category: data.category ? String(data.category) : undefined,
      author: String(data.author ?? 'Salafi Science Network'),
      scholar: data.scholar ? String(data.scholar) : null,
      contentType: String(data.contentType ?? collection),
      date: toIsoString(data.date),
      tags,
      languages: languages.length ? languages : ['English'],
      body: content.trim(),
      excerpt: createExcerpt(content)
    } satisfies ContentItem;
  })
  .filter((item): item is ContentItem => Boolean(item))
  .sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());

export const getContentItems = (): ContentItem[] => contentItems;

export const getContentItem = (collection: Collection, slug: string): ContentItem | undefined =>
  contentItems.find((item) => item.collection === collection && item.slug === slug);

export const renderContentToHtml = async (markdown: string): Promise<string> => {
  const processed = await remark().use(remarkHtml).process(markdown);
  return String(processed);
};

export const getAvailableFilters = () => {
  const items = getContentItems();
  return {
    categories: Array.from(new Set(items.map((item) => item.collection))).sort(),
    contentTypes: Array.from(new Set(items.map((item) => item.contentType))).sort(),
    authors: Array.from(new Set(items.map((item) => item.author))).sort(),
    scholars: Array.from(new Set(items.flatMap((item) => (item.scholar ? [item.scholar] : [])))).sort(),
    languages: Array.from(new Set(items.flatMap((item) => item.languages))).sort(),
    tags: Array.from(new Set(items.flatMap((item) => item.tags))).sort()
  };
};

export default contentItems;
