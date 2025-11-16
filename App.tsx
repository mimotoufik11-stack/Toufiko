import 'react-native-gesture-handler';
import 'react-native-reanimated';
import React from 'react';
import { NavigationContainer, DefaultTheme } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import { StatusBar } from 'expo-status-bar';

import HomeScreen from './src/screens/HomeScreen';
import SurahScreen from './src/screens/SurahScreen';
import { QuranProvider } from './src/context/QuranContext';
import { colors } from './src/theme/colors';
import type { RootStackParamList } from './src/types/navigation';

const Stack = createNativeStackNavigator<RootStackParamList>();

const navigationTheme = {
  ...DefaultTheme,
  colors: {
    ...DefaultTheme.colors,
    background: colors.background,
    card: colors.background,
    primary: colors.primary,
    text: colors.text
  }
};

export default function App(): JSX.Element {
  return (
    <QuranProvider>
      <SafeAreaProvider>
        <NavigationContainer theme={navigationTheme}>
          <StatusBar style="light" backgroundColor={colors.primary} />
          <Stack.Navigator
            initialRouteName="Home"
            screenOptions={{
              headerTitleAlign: 'center',
              headerTintColor: colors.onPrimary,
              headerStyle: {
                backgroundColor: colors.primary
              },
              headerTitleStyle: {
                fontWeight: '600',
                fontSize: 18
              }
            }}
          >
            <Stack.Screen
              name="Home"
              component={HomeScreen}
              options={{
                headerShown: false
              }}
            />
            <Stack.Screen
              name="Surah"
              component={SurahScreen}
              options={({ route }) => ({
                title: route.params?.surahName ?? 'السورة'
              })}
            />
          </Stack.Navigator>
        </NavigationContainer>
      </SafeAreaProvider>
    </QuranProvider>
  );
}
