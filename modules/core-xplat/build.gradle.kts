plugins {
    id("net.fabricmc.fabric-loom")
    `maven-publish`
}

val java_version: String by project
val project_version: String by project
val maven_group: String by project
val archives_base_name: String by project
val minecraft_version: String by project
val fabric_loader_version: String by project
val codextra_version: String by project
val common_events_version: String by project

group = maven_group
version = project_version

repositories {
    mavenLocal()
    maven("https://maven.kneelawk.com/releases/")
}

base {
    archivesName.set("$archives_base_name-core-xplat")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(java_version))
    withSourcesJar()
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraft_version")
    compileOnly("net.fabricmc:fabric-loader:$fabric_loader_version")

    // The Fabric build is currently the only supported target, so use the Fabric variants directly.
    implementation("io.github.daleydeng.codextra:codextra-fabric:$codextra_version")
    implementation("io.github.daleydeng.common-events:common-events-fabric:$common_events_version")
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(java_version.toInt())
}
