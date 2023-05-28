import com.android.build.gradle.BaseExtension

plugins {
    id("java-platform")
}

subprojects {
    apply(plugin = "com.android.library")

    plugins.withType(BasePlugin::class.java).configureEach {
        configure<BaseExtension> {
            defaultConfig {
                consumerProguardFiles("consumer-rules.pro")
            }
        }
    }
}

dependencies {
    constraints {
        api(project(":features:test"))
    }
}
