object Dependencies {

    // Gradle
    const val gradleAndroid = "com.android.tools.build:gradle:${Versions.gradleAndroid}"
    const val gradleKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.gradleKotlin}"

    // Deploy
    const val maven = "com.github.dcendents:android-maven-gradle-plugin:${Versions.mavenPublish}"

    // Kotlin
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.gradleKotlin}"

    // AppCompat
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    // ConstraintLayout
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // Picasso
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val picassoTestApp = "com.squareup.picasso:picasso:${Versions.picassoTestApp}" // intended to maintain compatibility

    // OkHttp
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpUrlConnection = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okHttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
}
