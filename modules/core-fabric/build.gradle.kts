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
val fapi_version: String by project
val codextra_version: String by project
val common_events_version: String by project

val xplatProject = project(":core-xplat")

group = maven_group
version = project_version

repositories {
    mavenLocal()
    maven("https://maven.kneelawk.com/releases/")
}

base {
    archivesName.set("$archives_base_name-core-fabric")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(java_version))
    withSourcesJar()
}

sourceSets.named("main") {
    java.srcDir(xplatProject.file("src/main/java"))
    resources.srcDir(xplatProject.file("src/main/resources"))
}



dependencies {
    minecraft("com.mojang:minecraft:$minecraft_version")
    implementation("net.fabricmc:fabric-loader:$fabric_loader_version")
    implementation("net.fabricmc.fabric-api:fabric-api:$fapi_version")

    implementation("io.github.daleydeng.codextra:codextra-fabric:$codextra_version")
    implementation("io.github.daleydeng.common-events:common-events-fabric:$common_events_version")
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(java_version.toInt())
}

tasks.processResources {
    inputs.property("version", project.version)
    inputs.property("mod_id", "graphlib")


    filesMatching("fabric.mod.json") {
        expand("version" to project.version, "mod_id" to "graphlib")
    }

}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "$archives_base_name-core-fabric"
            from(components["java"])
        }
    }
}
