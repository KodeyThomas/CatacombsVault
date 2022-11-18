
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNCatacombsVaultSpec.h"

@interface CatacombsVault : NSObject <NativeCatacombsVaultSpec>
#else
#import <React/RCTBridgeModule.h>

@interface CatacombsVault : NSObject <RCTBridgeModule>
#endif

@end
