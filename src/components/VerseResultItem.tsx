import React from 'react';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';

import { colors } from '../theme/colors';

interface VerseResultItemProps {
  surahName: string;
  surahEnglishName: string;
  surahNumber: number;
  verseNumber: number;
  text: string;
  onPress: () => void;
}

export const VerseResultItem: React.FC<VerseResultItemProps> = ({
  surahName,
  surahEnglishName,
  surahNumber,
  verseNumber,
  text,
  onPress
}) => {
  return (
    <TouchableOpacity style={styles.container} onPress={onPress} activeOpacity={0.9}>
      <View style={styles.header}>
        <Text style={styles.surahName}>{surahName}</Text>
        <Text style={styles.surahMeta}>{`سورة ${surahNumber} • ${surahEnglishName}`}</Text>
      </View>
      <Text style={styles.ayahText}>{text}</Text>
      <View style={styles.footer}>
        <Text style={styles.verseBadge}>{`آية ${verseNumber}`}</Text>
      </View>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  container: {
    backgroundColor: colors.surface,
    borderRadius: 18,
    padding: 20,
    borderWidth: 1,
    borderColor: colors.border,
    marginBottom: 12,
    shadowColor: '#0f172a1a',
    shadowOpacity: 0.06,
    shadowRadius: 6,
    shadowOffset: { width: 0, height: 3 }
  },
  header: {
    marginBottom: 12,
    alignItems: 'flex-end'
  },
  surahName: {
    fontSize: 18,
    fontWeight: '700',
    color: colors.text,
    textAlign: 'right'
  },
  surahMeta: {
    fontSize: 12,
    color: colors.textSecondary,
    marginTop: 2
  },
  ayahText: {
    fontSize: 18,
    lineHeight: 32,
    color: colors.text,
    textAlign: 'right'
  },
  footer: {
    marginTop: 16,
    alignItems: 'flex-start'
  },
  verseBadge: {
    fontSize: 12,
    color: colors.onPrimary,
    backgroundColor: colors.primary,
    paddingHorizontal: 12,
    paddingVertical: 6,
    borderRadius: 999
  }
});
