pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "code-challenge"
include(
    ":app",
    ":base",
    ":model",
    ":data:api",
    ":data:database",
    ":data:repository",
    ":feature:home",
    ":feature:detail",
    ":feature:onBoarding"
)
