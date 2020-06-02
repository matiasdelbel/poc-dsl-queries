package plugin

import org.gradle.api.Project

class NavigationPlugin : ModulePlugin {

    companion object {
        private const val PLUGIN_SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
    }

    override fun apply(target: Project) {
        target.plugins.apply(PLUGIN_SAFE_ARGS)
    }
}