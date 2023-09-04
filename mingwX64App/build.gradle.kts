import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    //jvm()

    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        compilations.getByName("main") {
            cinterops {
                //val libcurl by creating
            }
        }
        binaries {
            executable {
                //entryPoint = "main"
            }
            sharedLib {
                //baseName = "native" // on Linux and macOS
                baseName = "libnative" // on Windows
            }
        }
    }

    sourceSets {
        /*val mingwX64Main by getting  {
            dependencies {
                implementation(project(":shared"))
            }
        }*/
    }
}
