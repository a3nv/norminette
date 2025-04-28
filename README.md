# Norminette for CLion Plugin (Forked & fixed)

**This is my personal fork of the original [skrtks](https://github.com/skrtks/norminette) Plugin**, created because the upstream version wasn’t working quite right 
for my use case. I’ve applied a number of fixes, feature enhancements and upgrade both java and kotlin version to make it more stable and flexible. 
Feel free to try it out, raise issues, or submit PRs!

## How to build locally

- clonse repo
- run `./gradlew clean buildPlugin`
- check /path/to/your/repo/folder/build/distributions/Norminette-[version].zip
- open intellij and upload zip file as a local plugin


[//]: # (![Build]&#40;https://github.com/skrtks/norminette/workflows/Build/badge.svg&#41;)

[//]: # ([![Version]&#40;https://img.shields.io/jetbrains/plugin/v/17190-norminette.svg&#41;]&#40;https://plugins.jetbrains.com/plugin/17190-norminette&#41;)

[//]: # ([![Downloads]&#40;https://img.shields.io/jetbrains/plugin/d/17190-norminette.svg&#41;]&#40;https://plugins.jetbrains.com/plugin/17190-norminette&#41;)

<!-- Plugin description -->

### Norminette v3.x is required
#### ✅ Verified Norminette version: v3.3.51
Please make sure that you have [Norminette](https://github.com/42School/norminette) installed!

### Issue reporting 🔬
If you find an issue please report it trough [GitHub](https://github.com/skrtks/norminette/issues/new/choose). Thanks!

### Setup 🛠
Check if Norminette is already installed on your system (run `norminette -v`). 
If not, you can download it [here](https://github.com/42School/norminette). 

When Norminette is properly installed on your system, Norminette for CLion should automatically detect the path to the executable.
If you have done a custom installation of Norminette, or the executable is not in PATH, please go to <kbd>Settings/Preferences</kbd> > <kbd>Norminette</kbd> 
and provide the path to your norminette executable.

It is possible to change the highlight style or enable/disable highlighting trough:
<kbd>Settings/Preferences</kbd> > <kbd>Editor</kbd> > <kbd>Inspections</kbd> > <kbd>C/C++</kbd> > <kbd>Norminette</kbd>

<!-- Plugin description end -->

### Plugin Installation

- Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "norminette"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/skrtks/norminette/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>
