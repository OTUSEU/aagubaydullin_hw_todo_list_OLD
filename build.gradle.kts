//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
// старайтесь использовать последние стабильные версии библиотек, согласованные друг с другом
plugins {
    kotlin("jvm") version "1.8.21"
    jacoco
    id("io.qameta.allure") version "2.11.2"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

//allure {
//    autoconfigure = true
//    aspectjweaver = true
//
//}



repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.21")
    //implementation("org.junit.platform:junit-platform-commons:1.9.3") // не используется

    // все эти библиотеки подключены плагином
//    testImplementation("io.qameta.allure:allure-junit5:2.22.0")
//    testImplementation("io.qameta.allure:allure-commandline:2.22.0")
//    testImplementation("io.qameta.allure:allure-assertj:2.22.0")
//    testImplementation("io.qameta.allure:allure-java-commons:2.22.0")

    //testImplementation("org.aspectj:aspectjweaver:1.9.19")


    //testImplementation("org.junit.platform:junit-platform-launcher:1.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")  // этот один заменяет все три - специально сделано
//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
//    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
    implementation("org.slf4j:slf4j-simple:2.0.7")
}

tasks.test {
    useJUnitPlatform()
}

//tasks.withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = "1.8"
//}
// без этого нет отчетов
tasks.getByName<Test>("test") {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport", "allureReport")
}
application {
    mainClass.set("MainKt")
}
