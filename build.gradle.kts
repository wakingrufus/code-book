import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    kotlin("jvm") version "1.3.10"
    jacoco
    application
}

application {
    mainClassName = "com.github.wakingrufus.codebook.MainKt"
}


repositories {
    mavenCentral()
    jcenter()
}

version = "0.2.0"
group = "com.github.wakingrufus"


dependencies {
    compile(kotlin("stdlib"))
    compile("io.github.microutils:kotlin-logging:1.6.10")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.marcinmoskala:DiscreteMathToolkit:1.0.3")
    listOf("slf4j-api", "slf4j-log4j12").forEach {
        implementation(group = "org.slf4j", name = it, version = "1.7.25")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

idea {
    project {
        languageLevel = org.gradle.plugins.ide.idea.model.IdeaLanguageLevel("1.8")
    }
}

task<Wrapper>("wrapper") {
    gradleVersion = "4.10.2"
}

tasks.findByPath("jacocoTestReport")?.dependsOn("test")
tasks.findByPath("build")?.dependsOn("jacocoTestReport")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
