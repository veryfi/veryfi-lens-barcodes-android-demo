---
name: veryfi-lens-android-barcodes-integrate
description: "Integrates the Veryfi Lens Android Barcodes SDK (com.veryfi.lens:veryfi-lens-barcodes-sdk:2.7.0.6): Maven setup, on-device VeryfiInApp barcode reading, VeryfiLens.configure, VeryfiLensSettings, delegate callbacks, and showCamera. Use when adding Lens barcode scanning on Android. This is not barcodeExtractionIsOn on a receipt."
---

# Veryfi Lens Android — Barcodes

Walk a host Android app from Gradle to a working first scan. Change the fewest flags. Do not invent SDK APIs. Facts were read from the Android SDK at version `2.7.0.6` (commit `7f33726c`). Anything not confirmed there is marked **Unconfirmed**.

**Coordinate:** `com.veryfi.lens:veryfi-lens-barcodes-sdk:2.7.0.6`  
**Gradle flavor:** `lensBarcodes`  
**Allowed document types:** `DocumentType.BARCODE` only.

Public entry point: `com.veryfi.lens.VeryfiLens` with `com.veryfi.lens.VeryfiLensDelegate`. Settings type: `com.veryfi.lens.helpers.VeryfiLensSettings`.

This folder is complete by itself. Duplicate guidance across products is intentional.

## Pick a workflow

- **Quick** — the host already asked for this product. Ask nothing else. Apply the settings in [settings.md](settings.md), wire [implementation.md](implementation.md), and state the assumptions below.
- **Guided** — something material is missing. Ask only the unknown questions (at most four, one message), then continue.

### Quick workflow

```
veryfi-lens-android-barcodes-integrate quick:
- [ ] Confirm this artifact (install.md)
- [ ] Add the Maven repository and this one dependency
- [ ] Permissions (install.md)
- [ ] Credentials + settings from settings.md
- [ ] setDelegate, then configure { ok -> }, then showCamera() only when ok is true
- [ ] Handle the callbacks in callbacks.md
- [ ] Run the conflicts in troubleshooting.md
```

Assumptions when the host was specific about the product and silent on the rest: Lens camera, `BARCODE`, `VeryfiInApp`, auto-capture on, gallery off, continuous autofocus on.

### Guided workflow

1. **Product** — scan barcode symbols (`BARCODE` / InApp), or read a barcode printed on a receipt (`barcodeExtractionIsOn` on a receipts artifact)?
2. **Entry** — Lens camera only, or also gallery?
3. **Focus** — small codes held close (`continuousAutoFocus`)?

Defaults: camera, InApp, gallery off, continuous autofocus on.


## Rules

1. `documentTypes = arrayListOf(DocumentType.BARCODE)` and `dataExtractionEngine = VeryfiInApp`.
2. `VeryfiInApp` for any other type returns a plain success with no extraction. Barcode is one of the types that returns on-device JSON (`BarcodesDetector.getBarcodes()`).
3. `barcodeExtractionIsOn` is a Cloud flag for barcodes on receipts and bills. It is not this artifact.
4. `uploadImages` is the UI file pipeline. It is not documented as the barcode detector. The camera path is `showCamera()`.


## Do not

- Copy a repository password, keystore, or credential from the SDK repo or the Demo app. Maven access comes from Veryfi. Keep it in `~/.gradle/gradle.properties` or CI secrets.
- Depend on a second Lens UI flavor, or on `com.veryfi.lens.core:veryfi-lens-core-sdk` (email and obsolete dictation; adds `WRITE_CONTACTS` and `RECORD_AUDIO`).
- Treat Demo types as the client contract: `SettingsHolder`, Demo analyzers, `ImageProxyUtils`, `VeryfiLensHelper`.
- Set `dictateIsOn = true` or call `VeryfiLensCore.showDictation()`.
- Set `anyDocumentType` (deprecated local regex OCR). A new template id is `anyDocumentTemplate`, and only after Veryfi support confirms it.
- Set `useLegacyModels` (default true; no partner contract).
- Quote AAR size as the installed app size. Compare the final app with the SDK linked and removed. [SDK binary sizes](https://docs.veryfi.com/lens/mobile/settings/sdk_binary_sizes/).
- Treat `allowSubmittingScreenshots` (rejects imported screenshots) and `enableScreenshots` (device screenshots of Lens screens, default true) as one switch.
- Set `barcodeExtractionIsOn` here and expect receipt barcodes. That flag belongs on a receipt Cloud call.
- Promise a fixed barcode JSON schema. Log one payload.
- Use `VeryfiCloudAPI` for this type unless the host explicitly wants a different engine (**Unconfirmed** cloud barcode shape).


## What to hand back

1. `implementation "com.veryfi.lens:veryfi-lens-barcodes-sdk:2.7.0.6"`.
2. `barcodeSettings()` with `VeryfiInApp`.
3. Delegate that logs one `data` payload before parsing, because the JSON key set is **Unconfirmed**.
4. A sentence separating this from `barcodeExtractionIsOn`.


## Files in this folder

- [install.md](install.md) — Maven coordinate, requirements, permissions, app size
- [implementation.md](implementation.md) — configure, delegate, and launch or camera lifecycle
- [settings.md](settings.md) — `VeryfiLensSettings` for this product, with Android defaults
- [callbacks.md](callbacks.md) — delegate payloads
- [troubleshooting.md](troubleshooting.md) — symptom, cause, fix
- [clarifications.md](clarifications.md) — Unconfirmed items, Any-Documents, fraud, field detection
- [checklist.md](checklist.md) — validation checklist before handing code to the host

