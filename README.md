[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.openscience.cdk/cdk-scaffold/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.openscience.cdk/cdk-scaffold) [![build](https://github.com/cdk/cdk-scaffold/actions/workflows/maven.yml/badge.svg)](https://github.com/cdk/cdk-scaffold/actions/workflows/maven.yml) [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=cdk-scaffold&metric=bugs)](https://sonarcloud.io/summary/new_code?id=cdk-scaffold)

# CDK-Scaffold Module
## Scaffold Functionalities for the Chemistry Development Kit (CDK)
 
Copyright &copy; 2023 The CDK Development Team, especially
[Julian Zander](mailto:zanderjulian@gmx.de),
[Jonas Schaub](mailto:jonas.schaub@uni-jena.de),
[Achim Zielesny](mailto:achim.zielesny@w-hs.de),
[Christoph Steinbeck](mailto:christoph.steinbeck@uni-jena.de)

License: LGPL v2, see LICENSE.txt

[Home Page](https://cdk.github.io/) | [JavaDoc](http://cdk.github.io/cdk-scaffold/latest/docs/api/index.html?overview-summary.html) | [Wiki](https://github.com/cdk/cdk-scaffold/wiki) | [Issues](https://github.com/cdk/cdk-scaffold/issues) | [Mailing List](https://sourceforge.net/projects/cdk/lists/cdk-user)

## Introduction

The CDK is an open-source Java library for cheminformatics and bioinformatics. For more details, visit the main CDK repository
at [https://github.com/cdk/cdk](https://github.com/cdk/cdk).

The cdk-scaffold module makes versatile molecular scaffold functionalities available for integration with CDK-based workflows 
and software. All details of the functionality and implementation are described in <a href="https://doi.org/10.1186/s13321-022-00656-x"> 
Schaub et al. "Scaffold Generator: a Java library implementing molecular scaffold functionalities in the Chemistry Development Kit (CDK)" (J Cheminform 14, 79, 2022)</a><p>

## Install

CDK-scaffold depends on the cdk-bundle artifact. See the [CDK documentation](https://github.com/cdk/cdk) for installation instructions.

The library is built with Apache Maven and currently requires Java 1.8 or later.

You can also download a pre-built library JAR from [releases](https://github.com/cdk/cdk-scaffold/releases). 

If you are using Maven, you can install the cdk-bundle and cdk-scaffold packages using:

```xml
<dependency>
  <artifactId>cdk-bundle</artifactId>
  <groupId>org.openscience.cdk</groupId>
  <version>2.8</version>
</dependency>
<dependency>
<artifactId>cdk-scaffold</artifactId>
<groupId>org.openscience.cdk</groupId>
<version>2.8</version>
</dependency>
```

Further details on building the project in integrated development environments (IDEs) are available on the wiki:
 * [Building the CDK](https://github.com/cdk/cdk/wiki/Building-CDK)
 * [Maven Reporting Plugins](https://github.com/cdk/cdk/wiki/Maven-Reporting-Plugins)

## Getting Help

The [Toolkit-Rosetta Wiki Page](https://github.com/cdk/cdk/wiki/Toolkit-Rosetta) provides some examples for common tasks. If you need help using the CDK and have questions please use the user mailing list, [``cdk-user@lists.sf.net``](mailto:cdk-user@lists.sf.net) (**you must [subscribe here]( https://sourceforge.net/projects/cdk/lists/cdk-user) first to post**).
 
## Acknowledgments

![YourKit Logo](https://www.yourkit.com/images/yklogo.png)

The CDK developers use YourKit to profile and optimise code.

YourKit supports open source projects with its full-featured Java Profiler.
YourKit, LLC is the creator of <a href="https://www.yourkit.com/java/profiler/index.jsp">YourKit Java Profiler</a>
and <a href="https://www.yourkit.com/.net/profiler/index.jsp">YourKit .NET Profiler</a>,
innovative and intelligent tools for profiling Java and .NET applications.
