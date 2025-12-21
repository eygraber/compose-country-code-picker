plugins {
  id("com.eygraber.conventions-kotlin-multiplatform")
  id("com.eygraber.conventions-android-kmp-library")
  id("com.eygraber.conventions-compose-jetbrains")
  id("com.eygraber.conventions-detekt2")
  id("com.eygraber.conventions-publish-maven-central")
}

kotlin {
  defaultKmpTargets(
    project = project,
    androidNamespace = "com.eygraber.compose.country.code.picker",
  )

  sourceSets {
    commonMain {
      dependencies {
        // needed because material3 didn't get upgraded to stable with the rest of 1.8.0
        api(libs.compose.material3)
        api(compose.runtime)

        api(libs.locale)
        implementation(libs.normalizer)
      }
    }

    commonTest {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }
  }
}
