pluginManagement {
    repositories {
        maven("https://maven.aliyun.com/repository/public") {
            name = "AliyunPublic"
        }
        maven("https://maven.aliyun.com/repository/gradle-plugin") {
            name = "AliyunGradlePlugin"
        }
        gradlePluginPortal()
    }
}
