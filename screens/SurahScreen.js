import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  FlatList,
  StyleSheet,
  ActivityIndicator,
  StatusBar,
} from 'react-native';
import { fetchSurahVerses } from '../services/quranApi';

const SurahScreen = ({ route }) => {
  const { surahNumber, surahName, numberOfAyahs, revelationType } = route.params;
  const [verses, setVerses] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadSurahVerses();
  }, []);

  const loadSurahVerses = async () => {
    try {
      setLoading(true);
      const data = await fetchSurahVerses(surahNumber);
      setVerses(data);
    } catch (error) {
      console.error('Error loading verses:', error);
    } finally {
      setLoading(false);
    }
  };

  const renderVerse = ({ item }) => (
    <View style={styles.verseCard}>
      <View style={styles.verseNumberBadge}>
        <Text style={styles.verseNumberText}>{item.numberInSurah}</Text>
      </View>
      <Text style={styles.verseText}>{item.text}</Text>
    </View>
  );

  const renderHeader = () => (
    <View style={styles.headerCard}>
      <Text style={styles.surahTitle}>{surahName}</Text>
      <View style={styles.infoRow}>
        <View style={styles.infoBadge}>
          <Text style={styles.infoText}>
            {revelationType === 'Meccan' ? 'مكية' : 'مدنية'}
          </Text>
        </View>
        <View style={styles.infoBadge}>
          <Text style={styles.infoText}>{numberOfAyahs} آية</Text>
        </View>
      </View>
      {surahNumber !== 1 && surahNumber !== 9 && (
        <View style={styles.bismillahContainer}>
          <Text style={styles.bismillahText}>
            بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ
          </Text>
        </View>
      )}
    </View>
  );

  if (loading) {
    return (
      <View style={styles.loadingContainer}>
        <ActivityIndicator size="large" color="#2E7D32" />
        <Text style={styles.loadingText}>جاري تحميل الآيات...</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <StatusBar barStyle="light-content" backgroundColor="#2E7D32" />
      <FlatList
        data={verses}
        renderItem={renderVerse}
        keyExtractor={(item) => item.number.toString()}
        ListHeaderComponent={renderHeader}
        contentContainerStyle={styles.listContainer}
        showsVerticalScrollIndicator={false}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F5F5F5',
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5F5F5',
  },
  loadingText: {
    marginTop: 16,
    fontSize: 16,
    color: '#2E7D32',
    fontWeight: '600',
  },
  listContainer: {
    padding: 16,
  },
  headerCard: {
    backgroundColor: '#FFFFFF',
    borderRadius: 16,
    padding: 20,
    marginBottom: 16,
    elevation: 3,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
    alignItems: 'center',
  },
  surahTitle: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#1B5E20',
    marginBottom: 12,
    textAlign: 'center',
  },
  infoRow: {
    flexDirection: 'row',
    justifyContent: 'center',
    gap: 12,
    marginBottom: 16,
  },
  infoBadge: {
    backgroundColor: '#E8F5E9',
    paddingHorizontal: 16,
    paddingVertical: 8,
    borderRadius: 20,
  },
  infoText: {
    color: '#2E7D32',
    fontSize: 14,
    fontWeight: '600',
  },
  bismillahContainer: {
    marginTop: 8,
    paddingTop: 16,
    borderTopWidth: 1,
    borderTopColor: '#E0E0E0',
    width: '100%',
  },
  bismillahText: {
    fontSize: 22,
    color: '#2E7D32',
    fontWeight: 'bold',
    textAlign: 'center',
  },
  verseCard: {
    backgroundColor: '#FFFFFF',
    borderRadius: 12,
    padding: 16,
    marginBottom: 12,
    elevation: 2,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.1,
    shadowRadius: 2,
  },
  verseNumberBadge: {
    width: 32,
    height: 32,
    borderRadius: 16,
    backgroundColor: '#2E7D32',
    justifyContent: 'center',
    alignItems: 'center',
    alignSelf: 'flex-end',
    marginBottom: 12,
  },
  verseNumberText: {
    color: '#FFD700',
    fontSize: 14,
    fontWeight: 'bold',
  },
  verseText: {
    fontSize: 22,
    lineHeight: 40,
    color: '#212121',
    textAlign: 'right',
    fontWeight: '500',
  },
});

export default SurahScreen;
