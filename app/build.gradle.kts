import deps.Injection
import deps.Presentation

plugins {
    id("com.android.application")
    id("project-module-plugin")
}

android {

    defaultConfig.applicationId = "com.delbel.poc.dsl.queries"

    viewBinding { isEnabled = true }
}

dependencies {
    implementation(Injection.dagger)
    implementation(Injection.daggerSupport)
    kapt(Injection.daggerProcessor)
    kapt(Injection.daggerCompiler)

    implementation(Presentation.appCompat)
    implementation(Presentation.material)
}