# catacombs-vault

A Secure way to persist any secrets in your application.

This package is an implementation of the `EncryptedSharedPreferences` class on Android [Link to the class documentation](https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences)

## Installation

```sh
npm install catacombs-vault
```

## Usage

```js
import CatacombsVault from 'catacombs-vault';

// ...

await CatacombsVault.initialize(NAME_OF_VAULT);

await CatacombsVault.set('key', 'value');
await CatacombsVault.get('key'); // 'value'
```
