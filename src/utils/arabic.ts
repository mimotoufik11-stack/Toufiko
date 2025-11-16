const diacriticsRegex = /[\u064B-\u065F\u0670\u06D6-\u06ED]/g;
const tatweelRegex = /\u0640/g;

export const normalizeArabic = (value: string): string => {
  return value
    .replace(diacriticsRegex, '')
    .replace(tatweelRegex, '')
    .replace(/\s+/g, ' ')
    .trim();
};

export const revelationTypeToArabic = (type: string): string => {
  if (type === 'Meccan') {
    return 'مكية';
  }
  if (type === 'Medinan') {
    return 'مدنية';
  }
  return type;
};
