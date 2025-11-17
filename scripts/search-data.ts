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
    title: 'Principles of Quranic Exegesis',
    description: 'Understanding the foundational principles and methodology used by classical scholars in interpreting the Qur\'an.',
    url: '/lessons/principles-of-quranic-exegesis',
    tags: ['lessons', 'quran', 'tafsir', 'methodology']
  },
  {
    title: 'The Four Principles of Islamic Knowledge',
    description: 'A foundational lesson on the core principles that govern Islamic understanding and practice.',
    url: '/lessons/the-four-principles',
    tags: ['lessons', 'aqeedah', 'fundamentals']
  },
  {
    title: 'Understanding Hadith Classification',
    description: 'A comprehensive guide to how prophetic traditions are classified and evaluated for authenticity.',
    url: '/lessons/understanding-hadith-classification',
    tags: ['lessons', 'hadith', 'methodology']
  },
  {
    title: 'Purification in Islam - Physical and Spiritual',
    description: 'An in-depth exploration of the concept and practice of purification (Taharah) in Islamic tradition.',
    url: '/lessons/purification-in-islam',
    tags: ['lessons', 'fiqh', 'worship']
  },
  {
    title: 'Clarifying the Pillars of Islam',
    description: 'A concise explanation of the five pillars with evidences from the Quran and Sunnah.',
    url: '/articles/pillars-of-islam',
    tags: ['articles', 'fiqh', 'pillars']
  },
  {
    title: 'Understanding the Message of the Qur\'an',
    description: 'An analytical look at the central themes and universal messages of the Qur\'an for contemporary believers.',
    url: '/articles/understanding-the-message-of-the-quran',
    tags: ['articles', 'quran', 'message', 'guidance']
  },
  {
    title: 'Rights and Responsibilities in Family Relations',
    description: 'Exploring the Islamic framework for family relationships and the mutual obligations between spouses, parents, and children.',
    url: '/articles/rights-and-responsibilities-in-family',
    tags: ['articles', 'family', 'fiqh', 'social-ethics']
  },
  {
    title: 'Financial Ethics in Islam',
    description: 'An examination of Islamic principles governing wealth, trade, and financial transactions.',
    url: '/articles/financial-ethics-in-islam',
    tags: ['articles', 'economics', 'fiqh', 'ethics']
  },
  {
    title: 'Women in Islamic Tradition and Practice',
    description: 'Understanding the rights, roles, and contributions of women in Islamic history and contemporary society.',
    url: '/articles/women-in-islamic-tradition',
    tags: ['articles', 'women', 'social-ethics', 'rights']
  },
  {
    title: 'The Path of Knowledge Seeking in Islam',
    description: 'An exploration of Islamic principles regarding the pursuit of knowledge (talab al-ilm) and its significance.',
    url: '/articles/the-path-of-knowledge-seeking',
    tags: ['articles', 'education', 'knowledge', 'methodology']
  },
  {
    title: 'Sharh us-Sunnah',
    description: 'A classical commentary on prophetic practices and traditions.',
    url: '/books/sharh-us-sunnah',
    tags: ['books', 'hadith', 'jurisprudence']
  },
  {
    title: 'The Foundations of Islamic Jurisprudence',
    description: 'A comprehensive introduction to Usul al-Fiqh, the principles and methodologies underlying Islamic law.',
    url: '/books/the-foundations-of-islamic-jurisprudence',
    tags: ['books', 'fiqh', 'methodology']
  },
  {
    title: 'The Message of the Qur\'an',
    description: 'A thematic exploration of the Qur\'an\'s central messages and their implications for believers.',
    url: '/books/the-message-of-the-quran',
    tags: ['books', 'quran', 'message', 'theology']
  },
  {
    title: 'Biography of Prophet Muhammad (ﷺ)',
    description: 'A comprehensive account of the life, teachings, and contributions of the Prophet Muhammad.',
    url: '/books/biography-of-the-prophet-muhammad',
    tags: ['books', 'biography', 'prophet', 'history']
  },
  {
    title: 'Purifying Wealth',
    description: 'Religious ruling on wealth purification through zakah and related obligations.',
    url: '/fatwas/purifying-wealth',
    tags: ['fatwas', 'zakah', 'financial']
  },
  {
    title: 'Islamic Ruling on the Use of Modern Technology',
    description: 'A fatwa addressing the permissibility and appropriate use of contemporary technologies.',
    url: '/fatwas/ruling-on-modern-technology',
    tags: ['fatwas', 'technology', 'contemporary']
  },
  {
    title: 'Shaykh Abdul Aziz bin Baz',
    description: 'Biography of the Grand Mufti of Saudi Arabia, renowned for his dedication to teaching and issuing fatawa.',
    url: '/scholars/ibn-baz',
    tags: ['scholars', 'biography', 'fiqh']
  },
  {
    title: 'Sheikh Muhammad ibn Saleh al-Uthaymeen',
    description: 'A renowned Islamic scholar known for his clear explanations of Islamic sciences and accessibility to students.',
    url: '/scholars/muhammad-ibn-saleh-al-uthaymeen',
    tags: ['scholars', 'fiqh', 'tawheed', 'biography']
  },
  {
    title: 'Sheikh Muhammad Nasir al-Din al-Albani',
    description: 'A legendary hadith scholar known for his meticulous verification of prophetic traditions.',
    url: '/scholars/albani-muhammad-nasir',
    tags: ['scholars', 'hadith', 'biography']
  }
];

export default searchEntries;
