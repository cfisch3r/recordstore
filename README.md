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

## Code Coverage
Code Coverage is determined by [Jacoco-Plugin](https://docs.gradle.org/current/userguide/jacoco_plugin.html) 
and can be executed by:

```
gradle check jacocoTestReport
```

Verification is not configured, since this is obsolete when coding according TDD principles.
Results can be found in *build/jacocoHtml*.
 