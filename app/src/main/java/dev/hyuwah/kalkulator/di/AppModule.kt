package dev.hyuwah.kalkulator.di

import dev.hyuwah.kalkulator.domain.usecase.EvaluateExpressionUseCase
import dev.hyuwah.kalkulator.domain.usecase.EvaluatePostfixUseCase
import dev.hyuwah.kalkulator.domain.usecase.InfixToPostfixUseCase
import dev.hyuwah.kalkulator.ui.page.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::InfixToPostfixUseCase)
    singleOf(::EvaluatePostfixUseCase)
    singleOf(::EvaluateExpressionUseCase)
    viewModelOf(::MainViewModel)
}