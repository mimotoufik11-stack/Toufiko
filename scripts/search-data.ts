export interface SearchEntry {
  title: string;
  description: string;
  url: string;
  tags: string[];
}

const searchEntries: SearchEntry[] = [
  {
    title: 'Foundations of Tawheed',
    description: 'Introductory lesson covering the pillars of Islamic monotheism and its practical implications.',
    url: '/lessons/foundations-of-tawheed',
    tags: ['lessons', 'aqeedah', 'beginners']
  },
  {
    title: 'Explanation of Kitab at-Tawheed',
    description: 'Audio series featuring detailed commentary from recognised scholars.',
    url: '/audio/kitab-at-tawheed-series',
    tags: ['audio', 'aqeedah']
  },
  {
    title: 'Books of the Salaf: Recommended Reading List',
    description: 'A curated list of essential texts for students traversing the path of knowledge.',
    url: '/books/recommended-reading',
    tags: ['books', 'reading']
  },
  {
    title: 'Contemporary Issues in Fiqh',
    description: 'Articles addressing modern-day questions with evidences from the Quran and Sunnah.',
    url: '/articles/contemporary-fiqh-issues',
    tags: ['articles', 'fiqh']
  },
  {
    title: 'Fatwas from the Permanent Committee',
    description: 'A selection of translated verdicts dealing with common daily matters.',
    url: '/fatwas/permanent-committee',
    tags: ['fatwas', 'translations']
  },
  {
    title: 'Biographies of the Scholars',
    description: 'Learn about the lives and contributions of renowned scholars of the Salafi methodology.',
    url: '/scholars/biographies',
    tags: ['scholars', 'history']
  }
];

export default searchEntries;
