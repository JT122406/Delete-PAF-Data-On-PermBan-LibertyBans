plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven {
        url = uri("https://simonsator.de/repo/")
    }

    maven {
        url = uri("https://mvn-repo.arim.space/affero-gpl3/")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api("space.arim.libertybans:bans-api:1.0.4")
    compileOnly("com.velocitypowered:velocity-api:3.1.2-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.1.2-SNAPSHOT")
    compileOnly("de.simonsator:VelocityPoweredPartyAndFriends:1.0.89")
}

group = "xyz.lightning-mc"
version = "1.0-SNAPSHOT"
description = "Delete-PAF-Data-On-PermBan-LibertyBans"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}
