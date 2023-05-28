package com.joseph.exhibition.core.ui.presentation.test

sealed class TestIntent {
    object DoTest1 : TestIntent()
    object DoTest2 : TestIntent()
}
