<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Norminette Changelog
## [0.1.9] - 2025-04-29

## Added
- Build toolchain upgraded to Java 23 (while emitting Java 17 bytecode for IDE compatibility)
- Kotlin plugin bumped to 1.9.20 (or latest), and Gradle wrapper updated accordingly
- Gradle configuration modernized (Java and Kotlin toolchains, --release 17, jvmTarget=17)
- IntelliJ Platform baseline moved to 2020.3 (switched from com.intellij.modules.cidr.lang → com.intellij.cidr.base)
- Plugin.xml dependencies cleaned up: only platform, lang, cidr.base (+ optional clion)
- Inspection core refactored: replaced OCVisitor with PsiElementVisitor and extracted a standalone NorminetteSettingsService
- Settings panel rewritten to avoid class‐initializer/service lookup issues (lazy Swing init, headless‐safe)
- Build & packaging streamlined with the Gradle IntelliJ Plugin’s buildPlugin task

## [Unreleased]
### Added
- No version requirement

## [0.1.7] - 2023-04-22

### Added
- Compatibility for CLion 2023.*

## [0.1.4]

### Fixed
- Add compatibility for Norminette 3.3.51
- Add compatibility for CLion 2021.3

## [0.1.3]

### Fixed
- Compatibility issue with CLion 2020.2

## [0.1.2]

### Added
- Support for Norminette 3.3.4

## [0.1.1]

### Fixed
- Showed error on header include guard that wasn't shown by Norminette in the
terminal

## [0.1.0]

### Added
- Added option to disable/enable Norminette
- Made it possible to change the highlight style

### Changed
- Better placement of errors in source

## [0.0.8]

### Added
- Norminette indicator in error message

## [0.0.7]

### Added
- Instructions for bug reporting

## [0.0.6]

### Fixed
- Removed incompatibility with older versions of CLion

## [0.0.5]

### Updated
- New icon

## [0.0.4]

### Fixed
- Fixed issue with adding annotations in old versions of CLion

## [0.0.3]

### Fixed
- Fixed not auto-detecting Norminette from PATH
- Added support for older versions of CLion

## [0.0.2]

### Added
- Switched to using 42 Norminette as external annotator
- Showing proper error messages instead of only error code
- Now automatically detecting Norminette executable if in PATH

### Fixed
- Now properly deleting temporary file for Norminette runner after use

[Unreleased]: https://github.com/skrtks/norminette/compare/v0.1.7...HEAD
[0.1.7]: https://github.com/skrtks/norminette/compare/v0.1.4...v0.1.7
[0.1.4]: https://github.com/skrtks/norminette/compare/v0.1.3...v0.1.4
[0.1.3]: https://github.com/skrtks/norminette/compare/v0.1.2...v0.1.3
[0.1.2]: https://github.com/skrtks/norminette/compare/v0.1.1...v0.1.2
[0.1.1]: https://github.com/skrtks/norminette/compare/v0.1.0...v0.1.1
[0.1.0]: https://github.com/skrtks/norminette/compare/v0.0.8...v0.1.0
[0.0.8]: https://github.com/skrtks/norminette/compare/v0.0.7...v0.0.8
[0.0.7]: https://github.com/skrtks/norminette/compare/v0.0.6...v0.0.7
[0.0.6]: https://github.com/skrtks/norminette/compare/v0.0.5...v0.0.6
[0.0.5]: https://github.com/skrtks/norminette/compare/v0.0.4...v0.0.5
[0.0.4]: https://github.com/skrtks/norminette/compare/v0.0.3...v0.0.4
[0.0.3]: https://github.com/skrtks/norminette/compare/v0.0.2...v0.0.3
[0.0.2]: https://github.com/skrtks/norminette/commits/v0.0.2
