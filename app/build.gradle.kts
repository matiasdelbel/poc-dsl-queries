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
    implementation(Injection.daggerViewModel)

    kapt(Injection.daggerProcessor)
    kapt(Injection.daggerCompiler)

    implementation(Presentation.appCompat)
    implementation(Presentation.material)
    implementation(Presentation.constraintLayout)
    implementation(Presentation.recyclerView)
    implementation(Presentation.fragment)
    implementation(Presentation.liveData)

    implementation(Gateway.room)
    kapt(Gateway.roomCompiler)
}