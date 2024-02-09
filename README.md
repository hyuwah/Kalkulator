# Kalkulator

<img src="https://res.cloudinary.com/hyuwah-github-io/image/upload/v1707476772/Kalkulator/kalkulator_icon.png" width="100"/>

A simple calculator app built with Jetpack Compose.
It uses [mXparser](https://mathparser.org/) as it's expression evaluator by default, but has one [built-in with limited capability](https://www.educative.io/answers/what-is-reverse-polish-notation) for learning purposes which includes [infix to postfix converter](https://csis.pace.edu/~wolf/CS122/infix-postfix.htm).

## Features

- [x] Basic arithmetic operations
- [x] Copy Result (on long click)
- [x] Dynamic theme on Android 12+ & Light/Dark Mode
- [ ] History View (save and access past calculations)
- [ ] Calculator Configuration (Decimal, Thousand Separator, Precision)
- [ ] Theme switcher
- [ ] Flexible Layout (adaptive for landscape, tablet, foldable devices)

## Preview

Light Theme

![Light Theme](https://res.cloudinary.com/hyuwah-github-io/video/upload/v1707476748/Kalkulator/kalkulator-light-theme.mp4)

Dark Theme

![Dark Theme](https://res.cloudinary.com/hyuwah-github-io/video/upload/v1707476747/Kalkulator/kalkulator-dark-theme.mp4)

## Tech Stack
- JDK `1.8`
- Kotlin `1.9.22`
- Min SDK `23`
- Compiles & Target SDK `34`
- Jetpack Compose (BOM `2024.02.00`)
- *Gradle Version Catalog* for dependencies management
- [Koin](https://github.com/InsertKoin/koin) for dependency injection
- Jetpack Lifecycle
- [mXparser](https://mathparser.org/) for mathematical expression parsing and evaluation
- [Material3](https://developer.android.com/jetpack/compose/material) for UI components