import React, {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useMemo,
  useState
} from 'react';

import { fetchFullQuran } from '../services/api';
import type { SearchIndexItem, Surah } from '../types/quran';
import { normalizeArabic } from '../utils/arabic';

interface QuranContextValue {
  surahs: Surah[];
  searchIndex: SearchIndexItem[];
  loading: boolean;
  error: string | null;
  refresh: () => Promise<void>;
}

const QuranContext = createContext<QuranContextValue | undefined>(undefined);

interface QuranProviderProps {
  children: React.ReactNode;
}

export const QuranProvider: React.FC<QuranProviderProps> = ({ children }) => {
  const [surahs, setSurahs] = useState<Surah[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const loadQuran = useCallback(async (): Promise<void> => {
    try {
      setLoading(true);
      setError(null);
      const data = await fetchFullQuran();
      setSurahs(data);
    } catch (err) {
      const message = err instanceof Error ? err.message : 'حدث خطأ غير متوقع.';
      setError(message);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    void loadQuran();
  }, [loadQuran]);

  const searchIndex = useMemo<SearchIndexItem[]>(() => {
    if (!surahs.length) {
      return [];
    }

    return surahs.flatMap((surah) =>
      surah.ayahs.map((ayah) => ({
        surahNumber: surah.number,
        surahName: surah.name,
        surahEnglishName: surah.englishName,
        verseNumber: ayah.numberInSurah,
        globalAyahNumber: ayah.number,
        text: ayah.text,
        normalizedText: normalizeArabic(ayah.text)
      }))
    );
  }, [surahs]);

  const value = useMemo<QuranContextValue>(
    () => ({
      surahs,
      searchIndex,
      loading,
      error,
      refresh: loadQuran
    }),
    [surahs, searchIndex, loading, error, loadQuran]
  );

  return <QuranContext.Provider value={value}>{children}</QuranContext.Provider>;
};

export const useQuran = (): QuranContextValue => {
  const context = useContext(QuranContext);

  if (context === undefined) {
    throw new Error('useQuran must be used within a QuranProvider');
  }

  return context;
};
