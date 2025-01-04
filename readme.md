<div align="center">
  <img src="https://img.shields.io/badge/language-java-gold?style=flat" />
  <img src="https://img.shields.io/badge/v2.0.1-gold?style=flat" />
  <img src="https://img.shields.io/github/stars/rmilansky/translate-java?style=flat" />
</div>

# Introduction

I've been searching for a good translation library for Java that works without any authentication and tokens, but I
didn't found that - so I've decided to create one.

You can use this in any kind of your projects for free without even thinking about tokens and limits. 
# Usage Guide

## Maven / Gradle library adding

1. Add a repository:

Maven:

```xml

<repositories>
    <repository>
        <id>milansky-repo</id>
        <url>https://maven.milansky.ovh/releases</url>
    </repository>
</repositories>
```

Gradle:

```groovy
repositories {
    maven {
        url = "https://maven.milansky.ovh/releases"
    }
}
```

2. Add dependencies

Maven:

```xml

<properties>
    <protocol.version>2.0.1</protocol.version>
</properties>

<dependencies>
<dependency>
    <groupId>by.milansky.translate</groupId>
    <artifactId>api</artifactId>
    <version>${protocol.version}</version>
</dependency>
<dependency>
    <groupId>by.milansky.translate</groupId>
    <artifactId>google</artifactId>
    <version>${protocol.version}</version>
</dependency>
</dependencies>
```

Gradle:

```groovy
dependencies {
    // It's better to use gradle's dependencyResolutionManagement
    def protocolVersion = '2.0.1'

    compileOnly "by.milansky.translate:api:${protocolVersion}"
    compileOnly "by.milansky.translate:google:${protocolVersion}"
}
```

## Java API usage
1. Create a handler to process packets:

```java
// Just create a needed implementation of API and use it!
val requestProcessor = GoogleTranslateRequestProcessor.create();

val request = ImmutableTranslateRequest.builder()
        .text("Hello, it's test! How are you?")
        .to(GoogleTranslateLanguage.RUSSIAN)
        .from(GoogleTranslateLanguage.AUTO) // It means that Google Translator will try to detect language itself 
        .build();

requestProcessor.processRequest(request).thenAccept(System.out::println); // Works as a CompletableFuture
```
