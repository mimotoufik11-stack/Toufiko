import React, { useState } from 'react';
import {
  View,
  Text,
  FlatList,
  TouchableOpacity,
  StyleSheet,
  TextInput,
  StatusBar,
} from 'react-native';
import surahs from '../data/surahs.json';

const HomeScreen = ({ navigation }) => {
  const [searchQuery, setSearchQuery] = useState('');
  const [filteredSurahs, setFilteredSurahs] = useState(surahs);

  const handleSearch = (text) => {
    setSearchQuery(text);
    if (text.trim() === '') {
      setFilteredSurahs(surahs);
    } else {
      const filtered = surahs.filter(
        (surah) =>
          surah.name.includes(text) ||
          surah.englishName.toLowerCase().includes(text.toLowerCase()) ||
          surah.number.toString().includes(text)
      );
      setFilteredSurahs(filtered);
    }
  };

  const renderSurahItem = ({ item }) => (
    <TouchableOpacity
      style={styles.surahCard}
      onPress={() =>
        navigation.navigate('Surah', {
          surahNumber: item.number,
          surahName: item.name,
          numberOfAyahs: item.numberOfAyahs,
          revelationType: item.revelationType,
        })
      }
    >
      <View style={styles.surahNumber}>
        <Text style={styles.surahNumberText}>{item.number}</Text>
      </View>
      <View style={styles.surahInfo}>
        <Text style={styles.surahName}>{item.name}</Text>
        <Text style={styles.surahDetails}>
          {item.revelationType === 'Meccan' ? 'مكية' : 'مدنية'} • {item.numberOfAyahs} آية
        </Text>
      </View>
      <View style={styles.arrowContainer}>
        <Text style={styles.arrow}>‹</Text>
      </View>
    </TouchableOpacity>
  );

  return (
    <View style={styles.container}>
      <StatusBar barStyle="light-content" backgroundColor="#2E7D32" />
      
      <View style={styles.header}>
        <View style={styles.bismillah}>
          <Text style={styles.bismillahText}>بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ</Text>
        </View>
        
        <View style={styles.searchContainer}>
          <TextInput
            style={styles.searchInput}
            placeholder="ابحث عن سورة..."
            placeholderTextColor="#999"
            value={searchQuery}
            onChangeText={handleSearch}
          />
        </View>
        
        <TouchableOpacity
          style={styles.advancedSearchButton}
          onPress={() => navigation.navigate('Search')}
        >
          <Text style={styles.advancedSearchText}>البحث المتقدم في القرآن</Text>
        </TouchableOpacity>
      </View>

      <FlatList
        data={filteredSurahs}
        renderItem={renderSurahItem}
        keyExtractor={(item) => item.number.toString()}
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
  header: {
    backgroundColor: '#FFFFFF',
    paddingHorizontal: 16,
    paddingTop: 16,
    paddingBottom: 12,
    elevation: 4,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
  },
  bismillah: {
    paddingVertical: 20,
    alignItems: 'center',
    backgroundColor: '#2E7D32',
    borderRadius: 12,
    marginBottom: 16,
  },
  bismillahText: {
    fontSize: 24,
    color: '#FFD700',
    fontWeight: 'bold',
    textAlign: 'center',
  },
  searchContainer: {
    marginBottom: 12,
  },
  searchInput: {
    backgroundColor: '#F5F5F5',
    borderRadius: 12,
    padding: 14,
    fontSize: 16,
    textAlign: 'right',
    borderWidth: 1,
    borderColor: '#E0E0E0',
  },
  advancedSearchButton: {
    backgroundColor: '#1B5E20',
    paddingVertical: 12,
    borderRadius: 8,
    alignItems: 'center',
  },
  advancedSearchText: {
    color: '#FFD700',
    fontSize: 16,
    fontWeight: 'bold',
  },
  listContainer: {
    padding: 16,
  },
  surahCard: {
    flexDirection: 'row',
    backgroundColor: '#FFFFFF',
    borderRadius: 12,
    padding: 16,
    marginBottom: 12,
    elevation: 2,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.1,
    shadowRadius: 2,
    alignItems: 'center',
  },
  surahNumber: {
    width: 48,
    height: 48,
    borderRadius: 24,
    backgroundColor: '#2E7D32',
    justifyContent: 'center',
    alignItems: 'center',
    marginLeft: 12,
  },
  surahNumberText: {
    color: '#FFD700',
    fontSize: 18,
    fontWeight: 'bold',
  },
  surahInfo: {
    flex: 1,
    alignItems: 'flex-end',
  },
  surahName: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#1B5E20',
    marginBottom: 4,
  },
  surahDetails: {
    fontSize: 14,
    color: '#757575',
  },
  arrowContainer: {
    marginRight: 8,
  },
  arrow: {
    fontSize: 24,
    color: '#2E7D32',
    fontWeight: 'bold',
  },
});

export default HomeScreen;
