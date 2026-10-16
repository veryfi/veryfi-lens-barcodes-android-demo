# Validation checklist

Run this before delivering host code. Fix conflicts by removing extra flags.

- [ ] Dependency is `com.veryfi.lens:veryfi-lens-barcodes-sdk:2.7.0.6`
- [ ] `documentTypes` is BARCODE and engine is VeryfiInApp
- [ ] `barcodeExtractionIsOn` left false
- [ ] Barcode JSON schema marked **Unconfirmed**
- [ ] `uploadImages` not described as the scanner or as Headless
- [ ] Any-Documents requests escalated
- [ ] Installed app size, not AAR size

- [ ] Every relative link in the generated host notes stays inside this skill folder
- [ ] Unconfirmed behavior the host depends on is labeled **Unconfirmed** in the delivery note
- [ ] Host `<application>` sets `android:allowBackup="false"` and `tools:replace="android:allowBackup"` so a new-project `true` does not fail the manifest merger
- [ ] No repository password, Demo `SettingsHolder` preset, or `VeryfiLensHelper` mutation is in the host code
