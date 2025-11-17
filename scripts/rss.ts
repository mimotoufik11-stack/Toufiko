import { Feed } from 'feed';

export interface FeedAuthor {
  name: string;
  email?: string;
  link?: string;
}

export interface FeedItem {
  id: string;
  title: string;
  description: string;
  link: string;
  date: Date;
  author?: FeedAuthor[];
  image?: string;
  categories?: string[];
}

export interface FeedOptions {
  title: string;
  description: string;
  id: string;
  link: string;
  language?: string;
  copyright?: string;
  generator?: string;
  authors?: FeedAuthor[];
}

export const createSiteFeed = (options: FeedOptions, items: FeedItem[]) => {
  const feed = new Feed({
    title: options.title,
    description: options.description,
    id: options.id,
    link: options.link,
    language: options.language ?? 'en',
    copyright: options.copyright ?? `© ${new Date().getFullYear()} Salafi Science Network`,
    generator: options.generator ?? 'Astro & Feed',
    author: options.authors?.[0],
    feedLinks: {
      rss2: `${options.link.replace(/\/$/, '')}/rss.xml`,
      json: `${options.link.replace(/\/$/, '')}/feed.json`
    }
  });

  options.authors?.forEach((author) => feed.addContributor(author));

  items.forEach((item) => {
    feed.addItem({
      title: item.title,
      id: item.id,
      link: item.link,
      description: item.description,
      date: item.date,
      author: item.author,
      image: item.image,
      category: item.categories?.map((name) => ({ name }))
    });
  });

  return feed;
};

export default createSiteFeed;
