# GitHub Packages

The compiled mod is published as a Maven JAR associated with this repository.

| Field | Value |
| :--- | :--- |
| Group | `io.github.mmaarij123` |
| Artifact | `spear-dashing` |
| Version | `1.1.0` |
| Registry | `https://maven.pkg.github.com/mmaarij123/Spear-Dashing` |
| Package listing | [Repository packages](https://github.com/mmaarij123/Spear-Dashing/packages) |

## Download for Minecraft

For normal gameplay, download [`Spear-Dashing-1.1.0-optimized.jar`](../downloads/Spear-Dashing-1.1.0-optimized.jar) from the repository and follow the [installation instructions](../README.md#getting-started). The Maven copy contains the same JAR bytes. Its checksum is recorded in [`downloads/SHA256SUMS`](../downloads/SHA256SUMS).

## Publish

The repository owner can open **Actions → Publish JAR to GitHub Packages → Run workflow**, selecting `main`.

The workflow checks the checksum, metadata and included notices, then publishes the existing JAR using the repository's temporary `GITHUB_TOKEN` with `packages: write`. No personal access token is stored in this repository. The POM under `packaging/` describes publication; it does not replace the source build script.

Published release versions should be treated as immutable. Before publishing a future version, update the JAR, its metadata, `packaging/pom.xml`, workflow JAR path, documentation and checksum together.

## Download with Maven

GitHub requires authentication for Maven packages even when the associated repository is public. Configure a GitHub server and repository in your local Maven settings using an appropriately scoped credential, then use:

```xml
<dependency>
  <groupId>io.github.mmaarij123</groupId>
  <artifactId>spear-dashing</artifactId>
  <version>1.1.0</version>
</dependency>
```

The package is a Fabric client mod. Maven installation alone does not install its Minecraft, Fabric API or Fzzy Config runtime dependencies, or grant reuse rights in proprietary material.

## Rights

The [proprietary license](../LICENSE) reserves rights in covered original contributions. Earlier CC0 permissions remain intact, as explained in [the licensing scope](../THIRD_PARTY_NOTICES.md). Both notices accompany the JAR.

Registry documentation: [GitHub's Apache Maven registry guide](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry).
