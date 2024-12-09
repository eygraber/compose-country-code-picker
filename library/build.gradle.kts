plugins {
  id("com.eygraber.conventions-kotlin-multiplatform")
  id("com.eygraber.conventions-android-library")
  id("com.eygraber.conventions-compose-jetbrains")
  id("com.eygraber.conventions-detekt")
  id("com.eygraber.conventions-publish-maven-central")
}

android {
  namespace = "com.eygraber.compose.country.code.picker"
}

kotlin {
  defaultKmpTargets(
    project = project,
  )

  sourceSets {
    commonMain {
      dependencies {
        api(compose.material3)
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
