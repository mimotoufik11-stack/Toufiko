import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

import { colors } from '../theme/colors';

interface AyahItemProps {
  numberInSurah: number;
  text: string;
  highlighted?: boolean;
}

export const AyahItem: React.FC<AyahItemProps> = ({ numberInSurah, text, highlighted = false }) => {
  return (
    <View style={[styles.container, highlighted && styles.highlightedContainer]}>
      <View style={styles.badge}>
        <Text style={styles.badgeText}>{numberInSurah}</Text>
      </View>
      <Text style={[styles.text, highlighted && styles.highlightedText]}>{text}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    borderRadius: 16,
    paddingVertical: 20,
    paddingHorizontal: 18,
    backgroundColor: colors.surface,
    borderWidth: 1,
    borderColor: colors.border,
    marginBottom: 12
  },
  highlightedContainer: {
    borderColor: colors.accent,
    backgroundColor: '#ecfdf5'
  },
  badge: {
    alignSelf: 'flex-start',
    backgroundColor: colors.surfaceMuted,
    borderRadius: 12,
    paddingHorizontal: 12,
    paddingVertical: 4,
    marginBottom: 12
  },
  badgeText: {
    fontSize: 13,
    fontWeight: '600',
    color: colors.primary
  },
  text: {
    fontSize: 20,
    lineHeight: 36,
    color: colors.text,
    textAlign: 'right'
  },
  highlightedText: {
    color: colors.primary
  }
});
