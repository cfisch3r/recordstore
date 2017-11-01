#Record Store Demo


##Analysis

###Dependencies
Analysis is done by [jdepend](https://github.com/clarkware/jdepend) and can be run with

```
gradle check
```

Results can be found in *build/reports/jdepend* folder.
For a detailed introduction to package metrics see this 
[article](http://www.onjava.com/pub/a/onjava/2004/01/21/jdepend.html).

###Code Coverage
Code Coverage is determined by [Jacoco-Plugin](https://docs.gradle.org/current/userguide/jacoco_plugin.html) 
and can be executed by:

```
gradle jacocoTestReport
```

Verification is not configured, since this is obsolete when coding according TDD principles.
Results can be found in *build/jacocoHtml*.

###FindBugs
Check is performed by [Gradle FindBugs Plugin](https://docs.gradle.org/current/userguide/findbugs_plugin.html) and can 
be executed by

```
gradle findbugsMain
gradle findbugsTest
```

Results can be found in *build/reports/findbugs*.

###CheckStyle
The [CheckStyle Gradle Plugin](https://docs.gradle.org/current/userguide/checkstyle_plugin.html) is used for format 
analysis and can be executed with

```
gradle check
```

Configuration can be found in *config/checkstyle*. Unfortunately the reference to the suppression configuration is
relative to execution path, so the check cannot be executed in module directories.
Furthermore the Checkstyle version has to be fixed to 6.7.1. Newer versions are not supported. Tis means, that some
configuration options cannot be used. This  prevents the use of the Google checkstyle file.

Results can be found in *build/results/checkstyle*.

###PMD
For further code inspection th [PMD Gradle Plugin](https://docs.gradle.org/current/userguide/pmd_plugin.html) was added
and can be executed with.

```
gradle check
```

The results can be found in *build/reports/pmd*.
 