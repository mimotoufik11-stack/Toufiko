import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { I18nManager, Platform } from 'react-native';
import HomeScreen from './screens/HomeScreen';
import SurahScreen from './screens/SurahScreen';
import SearchScreen from './screens/SearchScreen';

I18nManager.allowRTL(true);
I18nManager.forceRTL(true);

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator
        screenOptions={{
          headerStyle: {
            backgroundColor: '#2E7D32',
          },
          headerTintColor: '#FFD700',
          headerTitleStyle: {
            fontWeight: 'bold',
            fontSize: 20,
          },
          headerTitleAlign: 'center',
        }}
      >
        <Stack.Screen 
          name="Home" 
          component={HomeScreen} 
          options={{ title: 'القرآن الكريم' }}
        />
        <Stack.Screen 
          name="Surah" 
          component={SurahScreen} 
          options={({ route }) => ({ title: route.params.surahName })}
        />
        <Stack.Screen 
          name="Search" 
          component={SearchScreen} 
          options={{ title: 'البحث في القرآن' }}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
