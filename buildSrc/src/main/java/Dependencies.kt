object Dependencies {

    // Gradle
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val gradleKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.gradleKotlin}"

    // Deploy
    const val bintray = "com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4"
    const val maven = "com.github.dcendents:android-maven-gradle-plugin:2.1"

    // Kotlin
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.gradleKotlin}"

    // AppCompat
    const val appCompat = "com.android.support:appcompat-v7:${Versions.appcompat}"

    // ConstraintLayout
    const val constraintLayout =
        "com.android.support.constraint:constraint-layout:${Versions.constraintLayout}"

    // Picasso
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"

    // OkHttp
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpUrlConnection = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okHttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
}