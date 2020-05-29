import deps.Injection
import deps.Presentation
import deps.Gateway

plugins {
    id("com.android.application")
    id("project-module-plugin")
}

android {

    defaultConfig.applicationId = "com.delbel.poc.dsl"

    viewBinding { isEnabled = true }
}

dependencies {
    implementation(Injection.dagger)
    implementation(Injection.daggerSupport)

    kapt(Injection.daggerProcessor)
    kapt(Injection.daggerCompiler)

    implementation(Presentation.appCompat)
    implementation(Presentation.material)

    implementation(Gateway.room)
}