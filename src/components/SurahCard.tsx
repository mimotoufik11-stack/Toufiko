import React from 'react';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';

import { colors } from '../theme/colors';
import type { Surah } from '../types/quran';
import { revelationTypeToArabic } from '../utils/arabic';

interface SurahCardProps {
  surah: Surah;
  onPress: () => void;
}

export const SurahCard: React.FC<SurahCardProps> = ({ surah, onPress }) => {
  return (
    <TouchableOpacity style={styles.card} onPress={onPress} activeOpacity={0.9}>
      <View style={styles.leading}>
        <Text style={styles.index}>{surah.number}</Text>
      </View>

      <View style={styles.content}>
        <Text style={styles.name} numberOfLines={1}>
          {surah.name}
        </Text>
        <Text style={styles.translation} numberOfLines={1}>
          {surah.englishNameTranslation}
        </Text>
      </View>

      <View style={styles.meta}>
        <View style={styles.chip}>
          <Text style={styles.chipText}>{revelationTypeToArabic(surah.revelationType)}</Text>
        </View>
        <Text style={styles.ayahCount}>{`${surah.ayahs.length} آية`}</Text>
      </View>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  card: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: colors.surface,
    borderRadius: 20,
    paddingVertical: 16,
    paddingHorizontal: 20,
    marginBottom: 12,
    borderWidth: 1,
    borderColor: colors.border,
    shadowColor: '#0f172a33',
    shadowOpacity: 0.08,
    shadowRadius: 8,
    shadowOffset: { width: 0, height: 4 },
    elevation: 2
  },
  leading: {
    width: 48,
    height: 48,
    borderRadius: 16,
    backgroundColor: colors.surfaceMuted,
    justifyContent: 'center',
    alignItems: 'center',
    marginLeft: 16,
    borderWidth: 1,
    borderColor: colors.border
  },
  index: {
    fontSize: 18,
    fontWeight: '700',
    color: colors.primary
  },
  content: {
    flex: 1,
    marginHorizontal: 12
  },
  name: {
    fontSize: 20,
    fontWeight: '700',
    color: colors.text,
    textAlign: 'right'
  },
  translation: {
    marginTop: 4,
    fontSize: 14,
    color: colors.textSecondary,
    textAlign: 'right'
  },
  meta: {
    alignItems: 'flex-end',
    justifyContent: 'center',
    gap: 6
  },
  chip: {
    paddingVertical: 4,
    paddingHorizontal: 12,
    borderRadius: 999,
    backgroundColor: colors.chipBackground
  },
  chipText: {
    fontSize: 12,
    color: colors.chipText,
    fontWeight: '600'
  },
  ayahCount: {
    fontSize: 13,
    color: colors.textSecondary
  }
});
