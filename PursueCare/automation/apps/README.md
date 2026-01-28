# Mobile App Files Directory

This directory contains mobile application files (.apk for Android, .ipa for iOS) used for automation testing.

## Directory Structure

```
apps/
├── android/          # Place Android .apk files here
│   └── PursueCare.apk
└── ios/             # Place iOS .ipa files here
    └── PursueCare.ipa
```

## Usage in config.properties

### Option 1: Relative Path (Recommended)
```
mobile.android.appPath = apps/android/PursueCare.apk
mobile.ios.appPath = apps/ios/PursueCare.ipa
```

### Option 2: Absolute Path
```
# Windows
mobile.android.appPath = C:/Users/YourName/Downloads/PursueCare.apk

# Mac/Linux
mobile.ios.appPath = /Users/YourName/Downloads/PursueCare.ipa
```

### Option 3: No App Path (App Already Installed)
If the app is already installed on the device/emulator, leave `appPath` empty and use:
- **Android**: `appPackage` and `appActivity`
- **iOS**: `bundleId`

## Notes

- **Android**: Use `.apk` files
- **iOS**: Use `.ipa` files (or `.app` for simulator)
- App files can be large, consider adding to `.gitignore` if not needed in version control
- Always use forward slashes (`/`) in paths, even on Windows

## Getting App Files

### Android (.apk)
1. Download from build server/CI
2. Extract from device: `adb pull /data/app/com.pursuecare.app/base.apk`
3. Build from source and copy APK

### iOS (.ipa)
1. Download from TestFlight/App Store Connect
2. Export from Xcode Archive
3. Extract from device (requires jailbreak)

