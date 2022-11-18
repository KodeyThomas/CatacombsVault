import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import CatacombsVault from 'catacombs-vault';

export default function App() {
  const [value, setValue] = React.useState<string | undefined>(undefined);

  React.useEffect(() => {
    CatacombsVault.initialize('testVaultName').then(() => {
      CatacombsVault.set('testKey', 'testValue').then(() => {
        console.log('Set key: ', 'testKey');
        CatacombsVault.get<string>('testKey').then((result) => {
          console.log('Got value: ' + result);
          setValue(result);
        });
      });
    });
  }, []);

  return (
    <View style={styles.container}>
      <Text>{value}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
