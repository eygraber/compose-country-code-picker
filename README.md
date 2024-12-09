# Compose Country Code Picker

A Compose Multiplatform library for selecting country codes in your application.

ComposeCountryCodePicker is designed to simplify the process of country selection in applications using 
Compose. This library provides an composable function that allows users to pick a country from a list,
typically for phone number input forms, internationalization settings, or any scenario requiring country selection.

## Features

  - **Multiplatform Support**: Works with Android, iOS, Desktop, ~~and Web~~ through Compose Multiplatform
    - Waiting on dependency support for `wasmJs`
  - **Customizable UI**: Adjust the appearance of the picker to match your app's theme or design requirements
  - **Search Functionality**: Allows users to easily search countries by name or calling code
  - **Flag Display**: Displays country flags using emoji, resulting in a higher quality rendering at no storage cost

## Usage

```kotlin
CountryCodePicker(
  onClick = { country -> ... },
)
```

You can optionally specify:

  - `Modifier`
  - A Composable to replace the default search
  - A Composable to replace the default search label
  - A Composable to replace the default search icon
  - A Composable to replace the default item leading content
  - A Composable to replace the default item headline content
  - The `ListItemColors` for each item
  - The list of `Country` to display
  - The search algorithm implementation
  - The initial `Country` to display

### Setup

```
repositories {
  mavenCentral()
}

dependencies {
  implementation("com.eygraber:compose-country-code-picker:0.5.0")
}
```

Snapshots can be found [here](https://s01.oss.sonatype.org/#nexus-search;gav~com.eygraber~compose-country-code-picker~~~).
