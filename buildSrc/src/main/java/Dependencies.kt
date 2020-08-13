object Dependencies {

    // Gradle
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val gradleKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    // Deploy
    const val bintray = "com.jfrog.bintray.gradle:gradle-bintray-plugin:${Versions.bintray}"
    const val maven = "com.github.dcendents:android-maven-gradle-plugin:${Versions.maven}"

    // Kotlin
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    // AppCompat
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    // ConstraintLayout
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // Picasso
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"

    // OkHttp
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpUrlConnection = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okHttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
}