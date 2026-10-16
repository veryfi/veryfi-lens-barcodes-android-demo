# Veryfi Lens Android — Barcodes — settings

Source of truth: Veryfi Lens Android SDK **2.7.0.6**, commit `7f33726c`. Names below were read from that tree. Items not fully confirmed are marked **Unconfirmed**. Do not copy repository passwords, keystores, or Demo-only types into the host app.


Always construct a fresh `VeryfiLensSettings()`. Android has no `VeryfiLens*Settings` subclasses. Pass that instance to `configure`. The SDK mutates it.

```kotlin
import com.veryfi.lens.helpers.DocumentType
import com.veryfi.lens.helpers.VeryfiLensSettings

fun barcodeSettings() = VeryfiLensSettings().apply {
    ignoreRemoteSettings = true            // default false; dashboard lens_settings overwrite this object during configure
    backupDocsToGallery = false            // default true; copies every scan into the device gallery
    moreMenuIsOn = false                   // default true; stored user prefs then replace host capture flags
    moreSettingsMenuIsOn = false           // default true
    autoCaptureOtherIsOn = false           // default true; OTHER auto-captures even when autoCaptureIsOn is false
    showNoDocumentDetectedWarning = false  // default true
    dictateIsOn = false                    // default true; obsolete voice capture, not a partner feature
    locationServicesIsOn = false           // default false; true raises a runtime location request
    glareDetectionIsOn = false             // default true; the glare gate is a weak algorithmic check

    dataExtractionEngine = VeryfiLensSettings.ExtractionEngine.VeryfiInApp
    documentTypes = arrayListOf(DocumentType.BARCODE)
    showDocumentTypes = false
    autoCaptureIsOn = true               // default false
    galleryIsOn = false                  // default true
    stitchIsOn = false                   // default true
    autoLightDetectionIsOn = false       // default true
    continuousAutoFocus = true           // default false
}

```

## Defaults versus this recipe

Android initializers below are the property defaults in `VeryfiLensSettings`.

| Property | Android default | This recipe | Why |
|----------|-----------------|-------------|-----|
| `ignoreRemoteSettings` | false | true | Dashboard `lens_settings` would overwrite this object during configure |
| `backupDocsToGallery` | true | false | Default copies every scan into the device gallery |
| `moreMenuIsOn` | true | false | Stored user prefs would replace host capture flags on later configure calls |
| `moreSettingsMenuIsOn` | true | false | Settings sheet inside More |
| `autoCaptureOtherIsOn` | true | false | `OTHER` auto-captures even when `autoCaptureIsOn` is false |
| `showNoDocumentDetectedWarning` | true | false | Skip the no-document alert |
| `dictateIsOn` | true | false | Obsolete voice control; default is on |
| `glareDetectionIsOn` | true | false | Android default glare gate is a weak check |
| `locationServicesIsOn` | false | false | Leave off unless the host needs lat/lng on the package |

| `dataExtractionEngine` | `VeryfiCloudAPI` | `VeryfiInApp` | On-device `BarcodesDetector` |
| `autoCaptureIsOn` | false | true | Capture when a code is read |
| `galleryIsOn` | true | false | Camera product |
| `stitchIsOn` | true | false | Single frame |
| `autoLightDetectionIsOn` | true | false | Manual light for close codes |
| `continuousAutoFocus` | false | true | Small codes held close to the lens |
| `barcodeExtractionIsOn` | false | false | Leave false. That flag is Cloud receipt barcodes |


Success `data` is the string from `BarcodesDetector.getBarcodes()` parsed into a `JSONObject` when it is valid JSON. The key set is not named in production comments. Log one real payload before coding against keys (**Unconfirmed** shape).

InApp still posts doc-ready metadata (no image upload branch). `uploadImages` is not the barcode reader.


## Suggestions

- Keep gallery off unless the host has tested still-image barcode reads. That path is **Unconfirmed** as a contract.
- If install size matters and the app already needs other Full types, compare `veryfi-lens-remote-models-sdk` or Lite. Both allow `BARCODE`.
- Measure the installed app, not the AAR.

