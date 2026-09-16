plugins {
    id("com.android.library") version "9.2.1" apply false
    id("org.jetbrains.kotlin.android") version "2.1.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0" apply false
}

tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}
