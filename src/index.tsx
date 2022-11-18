import { NativeModules, Platform } from 'react-native';
import type { ICatacombsVault } from './types';

const LINKING_ERROR =
  `The package 'catacombs-vault' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const CatacombsVault: ICatacombsVault =
  Platform.OS === 'ios'
    ? new Error('This is only available on Android')
    : NativeModules.CatacombsVault
    ? NativeModules.CatacombsVault
    : new Proxy(
        {},
        {
          get() {
            throw new Error(LINKING_ERROR);
          },
        }
      );

export { CatacombsVault };
