plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {

    plugins {
        val projectModulePlugin = "project-module-plugin"

        register(projectModulePlugin) {
            id = projectModulePlugin
            implementationClass = "ProjectModulePlugin"
        }
    }
}

repositories {
    google()
    jcenter()
}

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:3.6.3")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.2.2")

    implementation(kotlin("gradle-plugin", "1.3.61"))
}