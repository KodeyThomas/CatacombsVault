package com.kodeythomas.catacombsvault;

import androidx.annotation.NonNull;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = CatacombsVaultModule.NAME)
public class CatacombsVaultModule extends ReactContextBaseJavaModule {
  public static final String NAME = "CatacombsVault";
  private SharedPreferences keyStore;
  private ReactApplicationContext reactContext;

  public CatacombsVaultModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void initialize(String vaultName, Promise promise) {
    try {
        MasterKey key = new MasterKey.Builder(reactContext)
          .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
          .build();

        this.keyStore = EncryptedSharedPreferences.create(
          this.reactContext,
          vaultName,
          key,
          EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
          EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );

        promise.resolve(true);
    }

    catch (Exception ex) {
      promise.reject(new Error("Failed to create KeyStore"));
    }
  }

  @ReactMethod
  public void set(String key, String value, Promise promise) {
      if (this.keyStore == null) {
          promise.reject(new Exception("The KeyStore has not been initialized. Please call initialize() first."));
          return;
      }

      SharedPreferences.Editor editor = this.keyStore.edit();
      editor.putString(key, value);
      boolean saved = editor.commit();

      if (saved) {
          promise.resolve(value);
      }

      else {
          promise.reject(new Exception("Failed to save value to KeyStore"));
      }
  }

  @ReactMethod
  public void get(String key, Promise promise) {
      if (this.keyStore == null) {
        promise.reject(new Exception("The KeyStore has not been initialized. Please call initialize() first."));
        return;
      }

      String value = this.keyStore.getString(key, null);

      promise.resolve(value);
  }

  @ReactMethod
  public void delete(String key, Promise promise) {
    if (this.keyStore == null) {
        promise.reject("The KeyStore has not been initialized. Please call initialize() first.");
        return;
    }

    SharedPreferences.Editor editor = this.keyStore.edit();
    editor.remove(key);
    boolean saved = editor.commit();

    if (saved) {
        promise.resolve(true);
    }

    else {
        promise.reject(new Exception("Unable to delete item from KeyStore"));
    }
  }

  @ReactMethod
  public void deleteAll(Promise promise) {
      if (this.keyStore == null) {
        promise.reject(new Exception("The KeyStore has not been initialized. Please call initialize() first."));
        return;
      }

      SharedPreferences.Editor editor = this.keyStore.edit();
      editor.clear();
      boolean saved = editor.commit();

      if (saved) {
          promise.resolve(true);
      }

      else {
          promise.reject(new Exception("Unable to delete items from KeyStore"));
      }
  }
}
