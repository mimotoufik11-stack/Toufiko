import React, { useEffect, useMemo, useRef, useState } from 'react';
import {
  ActivityIndicator,
  FlatList,
  StyleSheet,
  Text,
  View
} from 'react-native';
import type { NativeStackScreenProps } from '@react-navigation/native-stack';
import { SafeAreaView } from 'react-native-safe-area-context';

import { AyahItem } from '../components/AyahItem';
import { useQuran } from '../context/QuranContext';
import { colors } from '../theme/colors';
import type { RootStackParamList } from '../types/navigation';
import { revelationTypeToArabic } from '../utils/arabic';

const APPROX_ITEM_HEIGHT = 120;

export type SurahScreenProps = NativeStackScreenProps<RootStackParamList, 'Surah'>;

const SurahScreen: React.FC<SurahScreenProps> = ({ route }) => {
  const { surahNumber, initialVerseNumber = null } = route.params;
  const { surahs, loading, error, refresh } = useQuran();

  const surah = useMemo(() => surahs.find((item) => item.number === surahNumber), [surahs, surahNumber]);
  const listRef = useRef<FlatList<any> | null>(null);
  const [highlightedVerse, setHighlightedVerse] = useState<number | null>(initialVerseNumber);

  useEffect(() => {
    setHighlightedVerse(initialVerseNumber ?? null);
  }, [initialVerseNumber]);

  useEffect(() => {
    if (!surah || !highlightedVerse) {
      return;
    }

    const index = surah.ayahs.findIndex((ayah) => ayah.numberInSurah === highlightedVerse);
    if (index < 0) {
      return;
    }

    const timer = setTimeout(() => {
      listRef.current?.scrollToIndex({ index, animated: true });
    }, 300);

    return () => clearTimeout(timer);
  }, [surah, highlightedVerse]);

  if (loading && !surah) {
    return (
      <SafeAreaView style={styles.safeArea}>
        <View style={styles.centered}>
          <ActivityIndicator size="large" color={colors.primary} />
          <Text style={styles.centeredText}>جار تحميل السورة...</Text>
        </View>
      </SafeAreaView>
    );
  }

  if (error && !surah) {
    return (
      <SafeAreaView style={styles.safeArea}>
        <View style={styles.feedbackBox}>
          <Text style={styles.feedbackTitle}>تعذر تحميل السورة</Text>
          <Text style={styles.feedbackMessage}>{error}</Text>
          <Text style={styles.feedbackLink} onPress={() => { void refresh(); }}>
            إعادة المحاولة
          </Text>
        </View>
      </SafeAreaView>
    );
  }

  if (!surah) {
    return (
      <SafeAreaView style={styles.safeArea}>
        <View style={styles.centered}>
          <Text style={styles.centeredText}>لم يتم العثور على السورة المطلوبة.</Text>
        </View>
      </SafeAreaView>
    );
  }

  return (
    <SafeAreaView style={styles.safeArea}>
      <FlatList
        ref={listRef}
        data={surah.ayahs}
        keyExtractor={(item) => item.number.toString()}
        renderItem={({ item }) => (
          <AyahItem
            numberInSurah={item.numberInSurah}
            text={item.text}
            highlighted={highlightedVerse === item.numberInSurah}
          />
        )}
        contentContainerStyle={styles.listContent}
        ListHeaderComponent={
          <View style={styles.heroCard}>
            <Text style={styles.heroSurahName}>{surah.name}</Text>
            <Text style={styles.heroEnglish}>{surah.englishNameTranslation}</Text>
            <View style={styles.heroMetaRow}>
              <View style={styles.heroMetaChip}>
                <Text style={styles.heroMetaText}>{revelationTypeToArabic(surah.revelationType)}</Text>
              </View>
              <View style={styles.heroMetaChip}>
                <Text style={styles.heroMetaText}>{`${surah.ayahs.length} آيات`}</Text>
              </View>
            </View>
          </View>
        }
        ListFooterComponent={<View style={styles.footerSpacer} />}
        getItemLayout={(_, index) => ({ length: APPROX_ITEM_HEIGHT, offset: APPROX_ITEM_HEIGHT * index, index })}
        onScrollToIndexFailed={(info) => {
          setTimeout(() => {
            const offset = info.averageItemLength * info.index;
            listRef.current?.scrollToOffset({ offset, animated: true });
          }, 350);
        }}
        showsVerticalScrollIndicator={false}
      />
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  safeArea: {
    flex: 1,
    backgroundColor: colors.background
  },
  listContent: {
    paddingHorizontal: 20,
    paddingTop: 24,
    paddingBottom: 48
  },
  heroCard: {
    backgroundColor: colors.primary,
    borderRadius: 24,
    paddingHorizontal: 24,
    paddingVertical: 28,
    marginBottom: 24
  },
  heroSurahName: {
    fontSize: 30,
    fontWeight: '800',
    color: colors.onPrimary,
    textAlign: 'right'
  },
  heroEnglish: {
    marginTop: 8,
    fontSize: 16,
    color: '#e2e8f0',
    textAlign: 'right'
  },
  heroMetaRow: {
    marginTop: 20,
    flexDirection: 'row-reverse',
    gap: 12
  },
  heroMetaChip: {
    backgroundColor: '#1e293b',
    borderRadius: 999,
    paddingHorizontal: 14,
    paddingVertical: 8
  },
  heroMetaText: {
    color: colors.onPrimary,
    fontSize: 13,
    fontWeight: '600'
  },
  footerSpacer: {
    height: 32
  },
  centered: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    paddingHorizontal: 24
  },
  centeredText: {
    marginTop: 16,
    fontSize: 16,
    color: colors.textSecondary,
    textAlign: 'center'
  },
  feedbackBox: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    paddingHorizontal: 32
  },
  feedbackTitle: {
    fontSize: 20,
    fontWeight: '700',
    color: colors.danger,
    marginBottom: 12
  },
  feedbackMessage: {
    fontSize: 15,
    color: colors.textSecondary,
    textAlign: 'center',
    lineHeight: 24
  },
  feedbackLink: {
    marginTop: 18,
    fontSize: 16,
    color: colors.primary,
    fontWeight: '700'
  }
});

export default SurahScreen;
