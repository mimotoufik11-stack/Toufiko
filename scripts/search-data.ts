import type { Collection, ContentItem } from '@data/content';
import contentItems from '@data/content';

export interface SearchEntry {
  id: string;
  title: string;
  description: string;
  url: string;
  author: string;
  scholar: string | null;
  contentType: string;
  collection: Collection;
  date: string;
  tags: string[];
  languages: string[];
  body: string;
  excerpt: string;
}

const mapToSearchEntry = (item: ContentItem): SearchEntry => ({
  id: item.id,
  title: item.title,
  description: item.description,
  url: item.url,
  author: item.author,
  scholar: item.scholar ?? null,
  contentType: item.contentType,
  collection: item.collection,
  date: item.date,
  tags: item.tags,
  languages: item.languages,
  body: item.body,
  excerpt: item.excerpt
});

const searchEntries: SearchEntry[] = contentItems.map(mapToSearchEntry);

export default searchEntries;
