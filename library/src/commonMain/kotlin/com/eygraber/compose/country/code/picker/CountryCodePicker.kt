package com.eygraber.compose.country.code.picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldLabelScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.util.fastAny
import androidx.compose.ui.util.fastFilter
import com.vanniktech.locale.Country
import doist.x.normalize.Form
import doist.x.normalize.normalize

private val unicodeAccentsRegex = Regex("\\p{M}")
private val defaultSearchNormalizedNameCache = mutableMapOf<String, String>()
private val defaultSearch: (CharSequence, List<Country>) -> List<Country> = { searchTerm, countries ->
  val trimmedSearchTerm = searchTerm.trim()
  val isPlusPrefix = trimmedSearchTerm[0] == '+'
  val isCallingCode = isPlusPrefix || trimmedSearchTerm.toString().toIntOrNull() != null

  countries.fastFilter { country ->
    if(isCallingCode) {
      country.callingCodes.fastAny { callingCode ->
        when {
          isPlusPrefix -> callingCode.startsWith(trimmedSearchTerm)
          else -> callingCode.removePrefix("+").startsWith(trimmedSearchTerm)
        }
      }
    }
    else {
      val displayName = country.displayName()

      val isDisplayNameMatch = displayName.contains(searchTerm, ignoreCase = true)
      val isNormalizedNameMatch = isDisplayNameMatch || run {
        val normalizedName = defaultSearchNormalizedNameCache.getOrPut(displayName) {
          displayName.normalize(Form.NFD).replace(unicodeAccentsRegex, "")
        }
        normalizedName.contains(searchTerm, ignoreCase = true)
      }

      isDisplayNameMatch || isNormalizedNameMatch
    }
  }
}

@Composable
public fun CountryCodePicker(
  onClick: (Country) -> Unit,
  modifier: Modifier = Modifier,
  searchLabel: AnnotatedString,
  searchIcon: ImageVector,
  searchIconContentDescription: String?,
  countryItemLeadingContent: @Composable (Country) -> Unit = { country ->
    DefaultCountryItemLeadingContent(country)
  },
  countryItemHeadlineContent: @Composable (Country) -> Unit = { country ->
    DefaultCountryItemHeadlineContent(country)
  },
  countryItemColors: ListItemColors = ListItemDefaults.colors(),
  countries: List<Country> = Country.entries,
  search: (CharSequence, List<Country>) -> List<Country> = defaultSearch,
  searchState: TextFieldState = rememberTextFieldState(),
  initialCountry: Country? = null,
) {
  CountryCodePicker(
    modifier = modifier,
    onClick = onClick,
    searchField = { textFieldState ->
      DefaultSearchField(
        textFieldState = textFieldState,
        label = { Text(searchLabel) },
        icon = {
          Icon(
            imageVector = searchIcon,
            contentDescription = searchIconContentDescription,
          )
        },
      )
    },
    countryItemLeadingContent = countryItemLeadingContent,
    countryItemHeadlineContent = countryItemHeadlineContent,
    countryItemColors = countryItemColors,
    countries = countries,
    search = search,
    searchState = searchState,
    initialCountry = initialCountry,
  )
}

@Composable
public fun CountryCodePicker(
  onClick: (Country) -> Unit,
  modifier: Modifier = Modifier,
  searchLabel: String,
  searchIcon: ImageVector,
  searchIconContentDescription: String?,
  countryItemLeadingContent: @Composable (Country) -> Unit = { country ->
    DefaultCountryItemLeadingContent(country)
  },
  countryItemHeadlineContent: @Composable (Country) -> Unit = { country ->
    DefaultCountryItemHeadlineContent(country)
  },
  countryItemColors: ListItemColors = ListItemDefaults.colors(),
  countries: List<Country> = Country.entries,
  search: (CharSequence, List<Country>) -> List<Country> = defaultSearch,
  searchState: TextFieldState = rememberTextFieldState(),
  initialCountry: Country? = null,
) {
  CountryCodePicker(
    modifier = modifier,
    onClick = onClick,
    searchField = { textFieldState ->
      DefaultSearchField(
        textFieldState = textFieldState,
        label = { Text(searchLabel) },
        icon = {
          Icon(
            imageVector = searchIcon,
            contentDescription = searchIconContentDescription,
          )
        },
      )
    },
    countryItemLeadingContent = countryItemLeadingContent,
    countryItemHeadlineContent = countryItemHeadlineContent,
    countryItemColors = countryItemColors,
    countries = countries,
    search = search,
    searchState = searchState,
    initialCountry = initialCountry,
  )
}

@Composable
public fun CountryCodePicker(
  onClick: (Country) -> Unit,
  modifier: Modifier = Modifier,
  searchLabel: @Composable TextFieldLabelScope.() -> Unit,
  searchIcon: @Composable () -> Unit,
  countryItemLeadingContent: @Composable (Country) -> Unit = { country ->
    DefaultCountryItemLeadingContent(country)
  },
  countryItemHeadlineContent: @Composable (Country) -> Unit = { country ->
    DefaultCountryItemHeadlineContent(country)
  },
  countryItemColors: ListItemColors = ListItemDefaults.colors(),
  countries: List<Country> = Country.entries,
  search: (CharSequence, List<Country>) -> List<Country> = defaultSearch,
  searchState: TextFieldState = rememberTextFieldState(),
  initialCountry: Country? = null,
) {
  CountryCodePicker(
    modifier = modifier,
    onClick = onClick,
    searchField = { textFieldState ->
      DefaultSearchField(
        textFieldState = textFieldState,
        label = searchLabel,
        icon = searchIcon,
      )
    },
    countryItemLeadingContent = countryItemLeadingContent,
    countryItemHeadlineContent = countryItemHeadlineContent,
    countryItemColors = countryItemColors,
    countries = countries,
    search = search,
    searchState = searchState,
    initialCountry = initialCountry,
  )
}

@Composable
public fun CountryCodePicker(
  onClick: (Country) -> Unit,
  modifier: Modifier = Modifier,
  searchField: (@Composable ColumnScope.(TextFieldState) -> Unit)? = { textFieldState ->
    DefaultSearchField(textFieldState)
  },
  countryItemLeadingContent: @Composable (Country) -> Unit = { country: Country ->
    DefaultCountryItemLeadingContent(country)
  },
  countryItemHeadlineContent: @Composable (Country) -> Unit = { country ->
    DefaultCountryItemHeadlineContent(country)
  },
  countryItemColors: ListItemColors = ListItemDefaults.colors(),
  countries: List<Country> = Country.entries,
  search: (CharSequence, List<Country>) -> List<Country> = defaultSearch,
  searchState: TextFieldState = rememberTextFieldState(),
  initialCountry: Country? = null,
) {
  Column(modifier = modifier) {
    if(searchField != null) searchField(searchState)

    CountryList(
      onClick = onClick,
      search = search,
      countryItemLeadingContent = countryItemLeadingContent,
      countryItemHeadlineContent = countryItemHeadlineContent,
      countryItemColors = countryItemColors,
      countries = countries,
      searchTerms = searchState.text,
      initialCountry = initialCountry ?: Country.USA,
    )
  }
}

@Composable
private fun CountryList(
  onClick: (Country) -> Unit,
  search: (CharSequence, List<Country>) -> List<Country>,
  countryItemLeadingContent: @Composable (Country) -> Unit,
  countryItemHeadlineContent: @Composable (Country) -> Unit,
  countryItemColors: ListItemColors,
  countries: List<Country>,
  searchTerms: CharSequence,
  initialCountry: Country,
) {
  val initialIndex = remember {
    countries.indexOf(initialCountry).coerceAtLeast(0)
  }

  var previousSearchTerms by remember { mutableStateOf(searchTerms) }
  var searchableItems by remember { mutableStateOf(countries) }

  val isContinuationOfPreviousSearch = searchTerms.startsWith(previousSearchTerms)

  val items = remember(searchTerms) {
    when {
      searchTerms.isBlank() -> countries

      !isContinuationOfPreviousSearch -> search(searchTerms, countries)

      else -> search(searchTerms, searchableItems)
    }.also { results ->
      searchableItems = results
      previousSearchTerms = searchTerms
    }
  }

  LazyColumn(
    state = rememberLazyListState(
      initialFirstVisibleItemIndex = initialIndex,
    ),
  ) {
    items(
      items = items,
      key = { it.code },
    ) { country ->
      CountryItem(
        onClick = onClick,
        leadingContent = countryItemLeadingContent,
        headlineContent = countryItemHeadlineContent,
        colors = countryItemColors,
        country = country,
      )
    }
  }
}

@Composable
private fun CountryItem(
  onClick: (Country) -> Unit,
  leadingContent: @Composable (Country) -> Unit,
  headlineContent: @Composable (Country) -> Unit,
  colors: ListItemColors,
  country: Country,
) {
  ListItem(
    leadingContent = {
      leadingContent(country)
    },
    headlineContent = {
      headlineContent(country)
    },
    modifier = Modifier.clickable {
      onClick(country)
    },
    colors = colors,
  )
}

@Composable
private fun DefaultSearchField(
  textFieldState: TextFieldState,
  label: @Composable TextFieldLabelScope.() -> Unit = { Text("Search") },
  icon: @Composable () -> Unit = {
    Icon(
      imageVector = Icons.Default.Search,
      contentDescription = null,
    )
  },
) {
  TextField(
    state = textFieldState,
    modifier = Modifier.fillMaxWidth(),
    label = label,
    leadingIcon = icon,
    keyboardOptions = KeyboardOptions.Default.copy(
      keyboardType = KeyboardType.Text,
    ),
  )
}

@Composable
private fun DefaultCountryItemLeadingContent(country: Country) {
  Text(country.emoji)
}

@Composable
private fun DefaultCountryItemHeadlineContent(country: Country) {
  Text("${country.displayName()} (${country.callingCodes.first()})")
}
