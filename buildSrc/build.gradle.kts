plugins {
    id("java-gradle-plugin")
    `kotlin-dsl`
}

repositories {
    maven("https://maven.aliyun.com/repository/public") { name = "AliyunPublic" }
    maven("https://maven.aliyun.com/repository/central") { name = "AliyunCentral" }
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.fabricmc.net/") { name = "Fabric" }
    maven("https://maven.architectury.dev/") { name = "Architectury" }
    maven("https://maven.quiltmc.org/repository/release") { name = "Quilt" }
    maven("https://maven.minecraftforge.net/") { name = "Forge" }
    maven("https://maven.neoforged.net/releases/") { name = "NeoForged" }
    maven("https://kneelawk.com/maven") { name = "Kneelawk" }
}

dependencies {
    val architectury_loom_version: String by project
    implementation("dev.architectury.loom:dev.architectury.loom.gradle.plugin:$architectury_loom_version")
}

gradlePlugin {
    plugins {
        create("mojmapPlugin") {
            id = "com.kneelawk.mojmap"
            implementationClass = "com.kneelawk.mojmap.MojmapPlugin"
        }
    }
}
