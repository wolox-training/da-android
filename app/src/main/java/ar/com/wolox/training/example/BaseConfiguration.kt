package ar.com.wolox.training.example

import ar.com.wolox.training.BuildConfig

internal open class BaseConfiguration {

    companion object {
        const val EXAMPLE_CONFIGURATION_KEY = BuildConfig.BASE_URL
        const val SHARED_PREFERENCES_NAME = "private-shared-prefs"
    }
}