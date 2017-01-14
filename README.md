## GoEuro Java-Junit-Selenium Exercise

### Environment Setup

1. Global setup
    * Install [Maven](https://maven.apache.org/install.html)
    * Download [Selenium standalone](http://www.seleniumhq.org/download/) with [Cromedriver](https://sites.google.com/a/chromium.org/chromedriver/downloads) and/or [Geckodriver](https://github.com/mozilla/geckodriver/releases) or use external Selenium grid.

2. Project Dependencies
    * Check dependencies
    ```
    $ mvn test-compile
    ```
    * Check for updates
    ```
    $ mvn versions:display-dependency-updates
    ```
    
### Running Tests

Make sure selenium standalone is launched before test, with chromedriver/geckodriver available:
```
$ java -jar selenium-server-standalone-3.0.1.jar
```

#####Testing:
```
$ mvn test
```

#####Test results:

    * Use surefire xml reports for CI integration.
    * Screenshots are available in screensgots directory.
