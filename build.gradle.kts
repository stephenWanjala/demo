import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31"
}




group = "com.github.wanjalaStephen"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                val ktorVersion = "1.6.3"
//                val ktorVersion = "2.3.0"
                implementation(compose.desktop.currentOs)
                implementation("org.jetbrains.compose.material3:material3-desktop:1.2.1")

                //ktor
                implementation("io.ktor:ktor-client-apache:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-json:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation ("io.ktor:ktor-client-cio:$ktorVersion")

                //material icons
                implementation("org.jetbrains.compose.material:material-icons-extended:1.0.0-rc11")

                implementation("io.jsonwebtoken:jjwt-api:0.11.2")
                implementation("io.jsonwebtoken:jjwt-impl:0.11.2")
                implementation("io.jsonwebtoken:jjwt-jackson:0.11.2")





            }


        }
        val jvmTest by getting


    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "demo"
            packageVersion = "1.0.0"
        }
    }
}
