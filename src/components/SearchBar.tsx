import React from 'react';
import {
  ActivityIndicator,
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  View
} from 'react-native';

import { colors } from '../theme/colors';

interface SearchBarProps {
  value: string;
  placeholder?: string;
  loading?: boolean;
  onChangeText: (text: string) => void;
  onSubmit: () => void;
  onClear: () => void;
}

export const SearchBar: React.FC<SearchBarProps> = ({
  value,
  placeholder = 'ابحث عن آية...',
  loading = false,
  onChangeText,
  onSubmit,
  onClear
}) => {
  const canSubmit = value.trim().length > 1;

  return (
    <View style={styles.container}>
      <TextInput
        style={styles.input}
        placeholder={placeholder}
        placeholderTextColor={colors.textSecondary}
        value={value}
        onChangeText={onChangeText}
        onSubmitEditing={onSubmit}
        returnKeyType="search"
        textAlign="right"
        textAlignVertical="center"
        autoCapitalize="none"
        autoCorrect={false}
        selectionColor={colors.accent}
      />

      {value.length > 0 && !loading ? (
        <TouchableOpacity
          accessibilityLabel="مسح الحقل"
          onPress={onClear}
          style={styles.clearButton}
        >
          <Text style={styles.clearText}>✕</Text>
        </TouchableOpacity>
      ) : null}

      <TouchableOpacity
        accessibilityLabel="بحث"
        disabled={!canSubmit || loading}
        onPress={onSubmit}
        style={[styles.actionButton, (!canSubmit || loading) && styles.actionButtonDisabled]}
      >
        {loading ? (
          <ActivityIndicator color={colors.onPrimary} size="small" />
        ) : (
          <Text style={styles.actionButtonText}>بحث</Text>
        )}
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: colors.surface,
    borderRadius: 16,
    borderWidth: 1,
    borderColor: colors.border,
    overflow: 'hidden'
  },
  input: {
    flex: 1,
    paddingVertical: 12,
    paddingHorizontal: 16,
    fontSize: 16,
    color: colors.text,
    fontWeight: '500'
  },
  clearButton: {
    paddingHorizontal: 12,
    justifyContent: 'center',
    alignItems: 'center'
  },
  clearText: {
    fontSize: 16,
    color: colors.textSecondary
  },
  actionButton: {
    height: '100%',
    paddingHorizontal: 20,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: colors.primary
  },
  actionButtonDisabled: {
    backgroundColor: '#475569'
  },
  actionButtonText: {
    color: colors.onPrimary,
    fontSize: 16,
    fontWeight: '600'
  }
});
