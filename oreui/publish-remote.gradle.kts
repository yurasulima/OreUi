plugins {
    `maven-publish`
    signing
}

val ossrhUsername = project.properties["ossrhUsername"]?.toString() ?: System.getenv("OSSRH_USERNAME")
val ossrhPassword = project.properties["ossrhPassword"]?.toString() ?: System.getenv("OSSRH_PASSWORD")

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "io.github.yurasulima"
            artifactId = "oreui"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("OreUI")
                description.set("A Minecraft-style UI components library for Jetpack Compose")
                url.set("https://github.com/yurasulima/OreUi")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("yurasulima")
                        name.set("Yura Sulima")
                        email.set("yura@example.com") // Replace with actual email if desired
                    }
                }
                scm {
                    connection.set("scm:git:github.com/yurasulima/OreUi.git")
                    developerConnection.set("scm:git:ssh://github.com/yurasulima/OreUi.git")
                    url.set("https://github.com/yurasulima/OreUi")
                }
            }
        }
    }
    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = ossrhUsername
                password = ossrhPassword
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["maven"])
}
