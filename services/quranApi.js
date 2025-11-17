const BASE_URL = 'https://api.alquran.cloud/v1';

export const fetchSurahVerses = async (surahNumber) => {
  try {
    const response = await fetch(`${BASE_URL}/surah/${surahNumber}`);
    const data = await response.json();
    
    if (data.code === 200 && data.status === 'OK') {
      return data.data.ayahs;
    }
    throw new Error('Failed to fetch surah verses');
  } catch (error) {
    console.error('Error fetching surah verses:', error);
    throw error;
  }
};

export const searchQuran = async (keyword) => {
  try {
    const response = await fetch(`${BASE_URL}/search/${encodeURIComponent(keyword)}/all/ar`);
    const data = await response.json();
    
    if (data.code === 200 && data.status === 'OK') {
      return data.data.matches;
    }
    return [];
  } catch (error) {
    console.error('Error searching Quran:', error);
    return [];
  }
};
