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

https://github.com/hyuwah/Kalkulator/assets/5181388/1422fc54-1160-49b1-91b7-a8758ac17c15




Dark Theme

https://github.com/hyuwah/Kalkulator/assets/5181388/3630c1ea-2cf9-41b6-9116-b77e758e8799



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
