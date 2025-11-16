export type RootStackParamList = {
  Home: undefined;
  Surah: {
    surahNumber: number;
    surahName: string;
    initialVerseNumber?: number;
  };
};
