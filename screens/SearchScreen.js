import React, { useState } from 'react';
import {
  View,
  Text,
  TextInput,
  FlatList,
  TouchableOpacity,
  StyleSheet,
  ActivityIndicator,
  StatusBar,
} from 'react-native';
import { searchQuran } from '../services/quranApi';

const SearchScreen = ({ navigation }) => {
  const [searchQuery, setSearchQuery] = useState('');
  const [searchResults, setSearchResults] = useState([]);
  const [loading, setLoading] = useState(false);
  const [searched, setSearched] = useState(false);

  const handleSearch = async () => {
    if (searchQuery.trim() === '') return;

    try {
      setLoading(true);
      setSearched(true);
      const results = await searchQuran(searchQuery);
      setSearchResults(results);
    } catch (error) {
      console.error('Error searching:', error);
      setSearchResults([]);
    } finally {
      setLoading(false);
    }
  };

  const renderSearchResult = ({ item }) => (
    <TouchableOpacity
      style={styles.resultCard}
      onPress={() =>
        navigation.navigate('Surah', {
          surahNumber: item.surah.number,
          surahName: item.surah.name,
          numberOfAyahs: item.surah.numberOfAyahs,
          revelationType: item.surah.revelationType,
        })
      }
    >
      <View style={styles.resultHeader}>
        <Text style={styles.surahName}>{item.surah.name}</Text>
        <Text style={styles.ayahNumber}>آية {item.numberInSurah}</Text>
      </View>
      <Text style={styles.ayahText}>{item.text}</Text>
    </TouchableOpacity>
  );

  return (
    <View style={styles.container}>
      <StatusBar barStyle="light-content" backgroundColor="#2E7D32" />
      
      <View style={styles.searchHeader}>
        <TextInput
          style={styles.searchInput}
          placeholder="ابحث في القرآن الكريم..."
          placeholderTextColor="#999"
          value={searchQuery}
          onChangeText={setSearchQuery}
          onSubmitEditing={handleSearch}
          returnKeyType="search"
        />
        <TouchableOpacity style={styles.searchButton} onPress={handleSearch}>
          <Text style={styles.searchButtonText}>بحث</Text>
        </TouchableOpacity>
      </View>

      {loading ? (
        <View style={styles.centerContainer}>
          <ActivityIndicator size="large" color="#2E7D32" />
          <Text style={styles.loadingText}>جاري البحث...</Text>
        </View>
      ) : searched && searchResults.length === 0 ? (
        <View style={styles.centerContainer}>
          <Text style={styles.noResultsText}>لا توجد نتائج</Text>
          <Text style={styles.noResultsSubtext}>جرب البحث بكلمات أخرى</Text>
        </View>
      ) : !searched ? (
        <View style={styles.centerContainer}>
          <Text style={styles.instructionText}>ابحث عن كلمة أو عبارة في القرآن الكريم</Text>
        </View>
      ) : (
        <View style={styles.resultsContainer}>
          <Text style={styles.resultsCount}>
            تم العثور على {searchResults.length} نتيجة
          </Text>
          <FlatList
            data={searchResults}
            renderItem={renderSearchResult}
            keyExtractor={(item, index) => index.toString()}
            contentContainerStyle={styles.listContainer}
            showsVerticalScrollIndicator={false}
          />
        </View>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F5F5F5',
  },
  searchHeader: {
    backgroundColor: '#FFFFFF',
    padding: 16,
    flexDirection: 'row',
    gap: 12,
    elevation: 4,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
  },
  searchInput: {
    flex: 1,
    backgroundColor: '#F5F5F5',
    borderRadius: 12,
    padding: 14,
    fontSize: 16,
    textAlign: 'right',
    borderWidth: 1,
    borderColor: '#E0E0E0',
  },
  searchButton: {
    backgroundColor: '#2E7D32',
    paddingHorizontal: 24,
    borderRadius: 12,
    justifyContent: 'center',
  },
  searchButtonText: {
    color: '#FFD700',
    fontSize: 16,
    fontWeight: 'bold',
  },
  centerContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 32,
  },
  loadingText: {
    marginTop: 16,
    fontSize: 16,
    color: '#2E7D32',
    fontWeight: '600',
  },
  instructionText: {
    fontSize: 18,
    color: '#757575',
    textAlign: 'center',
  },
  noResultsText: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#2E7D32',
    marginBottom: 8,
  },
  noResultsSubtext: {
    fontSize: 16,
    color: '#757575',
  },
  resultsContainer: {
    flex: 1,
  },
  resultsCount: {
    padding: 16,
    fontSize: 16,
    fontWeight: 'bold',
    color: '#2E7D32',
    textAlign: 'center',
    backgroundColor: '#E8F5E9',
  },
  listContainer: {
    padding: 16,
  },
  resultCard: {
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
  resultHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 12,
    paddingBottom: 12,
    borderBottomWidth: 1,
    borderBottomColor: '#E0E0E0',
  },
  surahName: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#2E7D32',
  },
  ayahNumber: {
    fontSize: 14,
    color: '#757575',
  },
  ayahText: {
    fontSize: 18,
    lineHeight: 32,
    color: '#212121',
    textAlign: 'right',
  },
});

export default SearchScreen;
