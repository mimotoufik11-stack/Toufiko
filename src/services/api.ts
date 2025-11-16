import type { Surah } from '../types/quran';

const API_BASE_URL = 'https://api.alquran.cloud/v1';

interface QuranResponse {
  data: {
    surahs: Surah[];
  };
}

export const fetchFullQuran = async (): Promise<Surah[]> => {
  const response = await fetch(`${API_BASE_URL}/quran/quran-uthmani`);

  if (!response.ok) {
    const message = `فشل تحميل البيانات (رمز ${response.status})`;
    throw new Error(message);
  }

  const payload = (await response.json()) as QuranResponse;

  if (!payload?.data?.surahs || !Array.isArray(payload.data.surahs)) {
    throw new Error('الاستجابة غير متوقعة من الخادم.');
  }

  return payload.data.surahs;
};
