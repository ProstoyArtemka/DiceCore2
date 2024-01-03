import java.util.Locale

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

if (!file(".git").exists()) {
    val errorText = """
        
        =====================[ ERROR ]=====================
         The Sapphire project directory is not a properly cloned Git repository.
         
         In order to build Sapphire from source you must clone
         the Sapphire repository using Git, not download a code
         zip from GitHub.
         
         Built Sapphire jars are available for download at
         https://github.com/SapphireMC/Sapphire/releases/
         
         See https://github.com/SapphireMC/Sapphire/blob/HEAD/CONTRIBUTING.md
         for further information on building and modifying Sapphire.
        ===================================================
    """.trimIndent()
    error(errorText)
}

rootProject.name = "dice"

for (name in listOf("dice-api", "dice-server")) {
    val projName = name.lowercase(Locale.ENGLISH)
    include(projName)
    findProject(":$projName")!!.projectDir = file(name)
}
