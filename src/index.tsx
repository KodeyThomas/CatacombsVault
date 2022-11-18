import { NativeModules } from 'react-native';

const { CatacombsVault } = NativeModules.CatacombsVault;

interface ICatacombsVault {
  /**
   * Initializes the vault. This must be called before any other methods.
   *
   * @param {string} vaultName The name of the vault to use.
   */
  initialize(vaultName: string): Promise<boolean>;
  /**
   * Stores a value in the vault.
   * @param key The key to store the value under.
   * @param value The value to store.
   */
  set<T>(key: string, value: T): Promise<T>;
  /**
   * Retrieves a value from the vault.
   * @param key The key to retrieve the value for.
   */
  get<T>(key: string): Promise<T>;
  /**
   * Deletes a value from the vault.
   * @param key The key to remove from the vault.
   */
  delete(key: string): Promise<boolean>;
  /**
   * Deletes all values from the vault.
   */
  deleteAll(): Promise<boolean>;
}

export default CatacombsVault as ICatacombsVault;
