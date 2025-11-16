import React, { useState, useEffect } from 'react';
import './App.css';
import { quranData } from './quranData';

function App() {
  const [selectedSurah, setSelectedSurah] = useState(null);
  const [searchQuery, setSearchQuery] = useState('');
  const [searchResults, setSearchResults] = useState([]);
  const [isSearching, setIsSearching] = useState(false);

  useEffect(() => {
    if (searchQuery.trim() === '') {
      setSearchResults([]);
      setIsSearching(false);
    } else {
      performSearch(searchQuery);
    }
  }, [searchQuery]);

  const performSearch = (query) => {
    setIsSearching(true);
    const results = [];
    
    quranData.forEach(surah => {
      surah.verses.forEach(verse => {
        if (verse.text.includes(query)) {
          results.push({
            surahId: surah.id,
            surahName: surah.name,
            verseId: verse.id,
            verseText: verse.text,
            fullSurah: surah
          });
        }
      });
    });
    
    setSearchResults(results);
  };

  const handleSurahSelect = (surah) => {
    setSelectedSurah(surah);
    setSearchQuery('');
    setSearchResults([]);
    setIsSearching(false);
  };

  const handleSearchResultClick = (result) => {
    setSelectedSurah(result.fullSurah);
    setSearchQuery('');
    setSearchResults([]);
    setIsSearching(false);
  };

  const highlightText = (text, query) => {
    if (!query) return text;
    const parts = text.split(new RegExp(`(${query})`, 'gi'));
    return parts.map((part, index) => 
      part.toLowerCase() === query.toLowerCase() ? 
        <span key={index} className="highlight">{part}</span> : part
    );
  };

  return (
    <div className="app">
      {/* Sidebar */}
      <div className="sidebar">
        <div className="sidebar-header">
          <h1>🕌 القرآن الكريم</h1>
          <div className="search-container">
            <input
              type="text"
              className="search-input"
              placeholder="ابحث عن آية..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
            />
            <span className="search-icon">🔍</span>
          </div>
        </div>
        
        {isSearching ? (
          <div className="search-results">
            {searchResults.length > 0 ? (
              searchResults.map((result, index) => (
                <div
                  key={index}
                  className="search-result-item"
                  onClick={() => handleSearchResultClick(result)}
                >
                  <div className="search-result-surah">
                    {result.surahName} - الآية {result.verseId}
                  </div>
                  <div className="search-result-verse">
                    {highlightText(result.verseText, searchQuery)}
                  </div>
                </div>
              ))
            ) : (
              <div className="no-results">
                لا توجد نتائج للبحث عن "{searchQuery}"
              </div>
            )}
          </div>
        ) : (
          <div className="surah-list">
            {quranData.map(surah => (
              <div
                key={surah.id}
                className={`surah-item ${selectedSurah?.id === surah.id ? 'active' : ''}`}
                onClick={() => handleSurahSelect(surah)}
              >
                <div className="surah-name">{surah.name}</div>
                <div className="surah-english">{surah.englishName}</div>
              </div>
            ))}
          </div>
        )}
      </div>

      {/* Main Content */}
      <div className="main-content">
        {selectedSurah ? (
          <>
            <div className="content-header">
              <h2 className="surah-title">{selectedSurah.name}</h2>
            </div>
            
            <div className="verses-container">
              {selectedSurah.verses.map(verse => (
                <div key={verse.id} className="verse">
                  <div className="verse-number">{verse.id}</div>
                  <div className="verse-text">{verse.text}</div>
                </div>
              ))}
            </div>
          </>
        ) : (
          <div className="welcome-screen">
            <div className="welcome-icon">📖</div>
            <h2 className="welcome-title">مرحباً بك في تطبيق القرآن الكريم</h2>
            <p className="welcome-subtitle">
              اختر سورة من القائمة الجانبية لبدء القراءة، أو استخدم شريط البحث للعثور على آيات محددة
            </p>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;