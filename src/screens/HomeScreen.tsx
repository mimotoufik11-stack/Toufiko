import React, { useEffect, useMemo, useState } from 'react';
import {
  ActivityIndicator,
  FlatList,
  RefreshControl,
  StyleSheet,
  Text,
  View
} from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';

import { SearchBar } from '../components/SearchBar';
import { SurahCard } from '../components/SurahCard';
import { VerseResultItem } from '../components/VerseResultItem';
import { useQuran } from '../context/QuranContext';
import { colors } from '../theme/colors';
import type { SearchIndexItem } from '../types/quran';
import type { NativeStackScreenProps } from '@react-navigation/native-stack';
import type { RootStackParamList } from '../types/navigation';
import { normalizeArabic } from '../utils/arabic';

export type HomeScreenProps = NativeStackScreenProps<RootStackParamList, 'Home'>;

const SEARCH_DEBOUNCE_MS = 250;

const HomeScreen: React.FC<HomeScreenProps> = ({ navigation }) => {
  const { surahs, loading, error, refresh, searchIndex } = useQuran();
  const [query, setQuery] = useState<string>('');
  const [searching, setSearching] = useState<boolean>(false);
  const [results, setResults] = useState<SearchIndexItem[]>([]);
  const [attempted, setAttempted] = useState<boolean>(false);

  useEffect(() => {
    if (query.trim().length < 2) {
      setResults([]);
      setSearching(false);
      return;
    }

    setSearching(true);
    const timeout = setTimeout(() => {
      const normalizedQuery = normalizeArabic(query.trim());
      const filtered = searchIndex.filter((item) =>
        item.normalizedText.includes(normalizedQuery)
      );

      setResults(filtered.slice(0, 60));
      setSearching(false);
      setAttempted(true);
    }, SEARCH_DEBOUNCE_MS);

    return () => clearTimeout(timeout);
  }, [query, searchIndex]);

  const subtitle = useMemo(() => {
    if (!surahs.length) {
      return 'جار التحميل...';
    }

    const totalAyahs = surahs.reduce((acc, surah) => acc + surah.ayahs.length, 0);
    return `${surahs.length} سورة • ${totalAyahs} آية`;
  }, [surahs]);

  const showResults = query.trim().length >= 2;

  const renderSurahList = () => (
    <FlatList
      data={surahs}
      keyExtractor={(item) => item.number.toString()}
      renderItem={({ item }) => (
        <SurahCard
          surah={item}
          onPress={() =>
            navigation.navigate('Surah', {
              surahNumber: item.number,
              surahName: item.name
            })
          }
        />
      )}
      contentContainerStyle={styles.listContent}
      refreshControl={
        <RefreshControl refreshing={loading} onRefresh={() => { void refresh(); }} tintColor={colors.primary} />
      }
      showsVerticalScrollIndicator={false}
    />
  );

  const renderSearchResults = () => (
    <FlatList
      data={results}
      keyExtractor={(item) => `${item.globalAyahNumber}`}
      renderItem={({ item }) => (
        <VerseResultItem
          surahName={item.surahName}
          surahEnglishName={item.surahEnglishName}
          surahNumber={item.surahNumber}
          verseNumber={item.verseNumber}
          text={item.text}
          onPress={() =>
            navigation.navigate('Surah', {
              surahNumber: item.surahNumber,
              surahName: item.surahName,
              initialVerseNumber: item.verseNumber
            })
          }
        />
      )}
      ListHeaderComponent={() => (
        <View style={styles.resultsHeader}>
          <Text style={styles.resultsTitle}>{`نتائج البحث (${results.length})`}</Text>
          <Text style={styles.resultsSubtitle}>أعلى ٦٠ نتيجة متطابقة</Text>
        </View>
      )}
      ListEmptyComponent={() => (
        <View style={styles.emptyState}>
          <Text style={styles.emptyStateText}>
            {searching
              ? 'جاري البحث في المصحف كاملًا...'
              : attempted
                ? 'لم يتم العثور على نتائج. حاول صياغة العبارة بصورة أخرى.'
                : 'اكتب عبارتك وابدأ البحث في جميع الآيات'}
          </Text>
        </View>
      )}
      contentContainerStyle={styles.listContent}
      refreshControl={
        <RefreshControl refreshing={loading} onRefresh={() => { void refresh(); }} tintColor={colors.primary} />
      }
      showsVerticalScrollIndicator={false}
    />
  );

  return (
    <SafeAreaView style={styles.safeArea}>
      <View style={styles.container}>
        <View style={styles.header}>
          <Text style={styles.title}>القرآن الكريم</Text>
          <Text style={styles.subtitle}>{subtitle}</Text>
        </View>

        <SearchBar
          value={query}
          onChangeText={(text) => {
            setQuery(text);
            setAttempted(false);
          }}
          onSubmit={() => setAttempted(true)}
          onClear={() => {
            setQuery('');
            setAttempted(false);
          }}
          loading={searching}
        />

        {error ? (
          <View style={styles.feedbackBox}>
            <Text style={styles.feedbackTitle}>تعذر تحميل البيانات</Text>
            <Text style={styles.feedbackMessage}>{error}</Text>
            <Text style={styles.feedbackLink} onPress={() => refresh()}>
              إعادة المحاولة
            </Text>
          </View>
        ) : loading && !surahs.length ? (
          <View style={styles.loader}>
            <ActivityIndicator size="large" color={colors.primary} />
            <Text style={styles.loaderText}>جار تحميل المصحف الشريف...</Text>
          </View>
        ) : showResults ? (
          renderSearchResults()
        ) : (
          renderSurahList()
        )}
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  safeArea: {
    flex: 1,
    backgroundColor: colors.background
  },
  container: {
    flex: 1,
    paddingHorizontal: 20,
    paddingTop: 24
  },
  header: {
    marginBottom: 24,
    alignItems: 'flex-end'
  },
  title: {
    fontSize: 28,
    fontWeight: '800',
    color: colors.text,
    marginBottom: 8
  },
  subtitle: {
    fontSize: 14,
    color: colors.textSecondary
  },
  listContent: {
    paddingTop: 24,
    paddingBottom: 48
  },
  loader: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center'
  },
  loaderText: {
    marginTop: 16,
    fontSize: 16,
    color: colors.textSecondary
  },
  resultsHeader: {
    alignItems: 'flex-end',
    marginBottom: 16
  },
  resultsTitle: {
    fontSize: 18,
    fontWeight: '700',
    color: colors.text
  },
  resultsSubtitle: {
    fontSize: 13,
    marginTop: 4,
    color: colors.textSecondary
  },
  emptyState: {
    marginTop: 40,
    alignItems: 'center'
  },
  emptyStateText: {
    fontSize: 16,
    color: colors.textSecondary,
    textAlign: 'center',
    lineHeight: 26
  },
  feedbackBox: {
    marginTop: 36,
    padding: 24,
    borderRadius: 18,
    backgroundColor: '#fee2e2',
    borderWidth: 1,
    borderColor: '#fecaca',
    alignItems: 'center'
  },
  feedbackTitle: {
    fontSize: 18,
    fontWeight: '700',
    color: colors.danger,
    marginBottom: 8
  },
  feedbackMessage: {
    fontSize: 14,
    color: colors.textSecondary,
    textAlign: 'center',
    lineHeight: 22
  },
  feedbackLink: {
    marginTop: 16,
    fontSize: 15,
    color: colors.primary,
    fontWeight: '700'
  }
});

export default HomeScreen;
