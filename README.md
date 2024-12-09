# Todo

1. Update `.github/workflows/publish_release.yml` to use the correct maven name in `prepare_next_dev`
2. Add repository secrets
  - ARTIFACT_SIGNING_PRIVATE_KEY
  - GIT_SIGNING_PRIVATE_KEY
  - GIT_SIGNING_PRIVATE_KEY_PASSWORD
  - PUSH_PAT
  - SONATYPE_NEXUS_PASSWORD
  - SONATYPE_NEXUS_USERNAME
3. Remove unused entries from `gradle/libs.versions.toml`
4. Add labels to GitHub repo from `changelog_config.json`
5. Update POM properties in `gradle.properties`
6. Update POM properties in `template-module/gradle.properties`
7. Update `android.namespace` in `template-module/build.gradle.kts`
8. Remove `.template` in `template-module/srm/commonMain/kotlin/com/eygraber`
9. Update `rootProject.name` in `settings.gradle.kts`
10. Update `template-module` name in `settings.gradle.kts`
11. Remove `if` condition from `.github/workflows/publish_snapshot_release.yml`
