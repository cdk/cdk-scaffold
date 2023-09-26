[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.openscience.cdk/cdk-scaffold/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.openscience.cdk/cdk-scaffold) 
[![build](https://github.com/cdk/cdk-scaffold/actions/workflows/maven.yml/badge.svg)](https://github.com/cdk/cdk-scaffold/actions/workflows/maven.yml) 
[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-blue.svg)](https://GitHub.com/cdk/cdk-scaffold/graphs/commit-activity)
[![GitHub issues](https://img.shields.io/github/issues/cdk/cdk-scaffold.svg)](https://GitHub.com/cdk/cdk-scaffold/issues/)
[![GitHub contributors](https://img.shields.io/github/contributors/cdk/cdk-scaffold.svg)](https://GitHub.com/cdk/cdk-scaffold/graphs/contributors/)
[![GitHub release](https://img.shields.io/github/release/cdk/cdk-scaffold.svg)](https://github.com/cdk/cdk-scaffold/releases/)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=cdk_cdk-scaffold&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=cdk_cdk-scaffold)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=cdk_cdk-scaffold&metric=bugs)](https://sonarcloud.io/summary/new_code?id=cdk_cdk-scaffold) 
[![DOI](https://zenodo.org/badge/638930745.svg)](https://zenodo.org/badge/latestdoi/638930745)
[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](http://cdk.github.io/cdk-scaffold/latest/docs/api/index.html?overview-summary.html)
[![License: LGPL v2.1](https://img.shields.io/badge/License-LGPL%20v2.1-blue.svg)](https://www.gnu.org/licenses/old-licenses/lgpl-2.1.en.html)

# CDK-Scaffold Module
## Scaffold Functionalities for the Chemistry Development Kit (CDK)
 
Copyright &copy; 2023 The CDK Development Team, especially
[Julian Zander](mailto:zanderjulian@gmx.de),
[Jonas Schaub](mailto:jonas.schaub@uni-jena.de),
[Achim Zielesny](mailto:achim.zielesny@w-hs.de),
[Christoph Steinbeck](mailto:christoph.steinbeck@uni-jena.de)

License: LGPL v2.1, see [LICENSE.txt](https://github.com/cdk/cdk-scaffold/blob/main/LICENSE.txt).

[CDK Home Page](https://cdk.github.io/) | [JavaDoc](http://cdk.github.io/cdk-scaffold/latest/docs/api/index.html?overview-summary.html) | [Wiki](https://github.com/cdk/cdk-scaffold/wiki) | [Issues](https://github.com/cdk/cdk-scaffold/issues) | [CDK Users Mailing List](https://sourceforge.net/projects/cdk/lists/cdk-user)

## Introduction

The Chemistry Development Kit (CDK) is an open-source Java library for cheminformatics and bioinformatics. For more details, visit the main CDK repository
at [https://github.com/cdk/cdk](https://github.com/cdk/cdk).

The cdk-scaffold module makes versatile molecular scaffold functionalities available for integration with CDK-based workflows and software. 

It was initially based on the "Scaffold Generator" project which is described in detail in [Schaub et al. "Scaffold Generator: a Java library implementing molecular scaffold functionalities in the Chemistry Development Kit (CDK)" (J Cheminform 14, 79, 2022)](https://doi.org/10.1186/s13321-022-00656-x).

Detailed code examples can be found in the [repository wiki](https://github.com/cdk/cdk-scaffold/wiki).

Some of the cdk-scaffold functionalities are also implemented in the MORTAR (MOlecule fRagmenTAtion fRamework) rich client Graphical User Interface (GUI) application ([GitHub repository](https://github.com/FelixBaensch/MORTAR) | [article](https://doi.org/10.1186/s13321-022-00674-9))
<p>

## Install

The library is built with Apache Maven and currently requires Java 1.8 or later.

You can download a pre-built library JAR from [releases](https://github.com/cdk/cdk-scaffold/releases). 

If you are using Maven, you can install the cdk-scaffold package using:

```xml
<dependency>
  <artifactId>cdk-scaffold</artifactId>
  <groupId>org.openscience.cdk</groupId>
  <version>2.8</version>
</dependency>
```

### Snapshot releases

For snapshot releases (currently `2.9-SNAPSHOT`) include the following fragment to define the
snapshot repository:
 
```xml
<repositories>
  <repository>
    <id>ossrh</id>
    <url>https://s01.oss.sonatype.org/content/repositories/snapshots</url>
  </repository>
</repositories>
```

Further details on building the project in integrated development environments (IDEs) are available on the wiki:
 * [Building the CDK](https://github.com/cdk/cdk/wiki/Building-CDK)
 * [Maven Reporting Plugins](https://github.com/cdk/cdk/wiki/Maven-Reporting-Plugins)

## Getting Help

The [Toolkit-Rosetta Wiki Page](https://github.com/cdk/cdk/wiki/Toolkit-Rosetta) provides some examples for common tasks. If you need help using the CDK and have questions please use the user mailing list, [``cdk-user@lists.sf.net``](mailto:cdk-user@lists.sf.net) (**you must [subscribe here]( https://sourceforge.net/projects/cdk/lists/cdk-user) first to post**).
