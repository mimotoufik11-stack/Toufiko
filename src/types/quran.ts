export interface RawAyah {
  number: number;
  text: string;
  numberInSurah: number;
  juz: number;
  manzil: number;
  page: number;
  ruku: number;
  hizbQuarter: number;
  sajda: boolean | {
    recommended: boolean;
    obligatory: boolean;
  };
}

export type RevelationType = 'Meccan' | 'Medinan';

export interface Surah {
  number: number;
  name: string;
  englishName: string;
  englishNameTranslation: string;
  revelationType: RevelationType;
  ayahs: RawAyah[];
}

export interface SearchIndexItem {
  surahNumber: number;
  surahName: string;
  surahEnglishName: string;
  verseNumber: number;
  globalAyahNumber: number;
  text: string;
  normalizedText: string;
}
