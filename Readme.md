# About

Run selenese tests during maven build.

This plugin can run [Selenium](http://www.seleniumhq.org/) test suites during the maven build process.
To create a Selenium test, use the [Selenium IDE](https://addons.mozilla.org/en-US/firefox/addon/selenium-ide/).

# Usage

Add the plugin to your project's `pom.xml`:

    <plugin>
        <groupId>com.regiocom.bpo</groupId>
        <artifactId>seleneserunner-maven-plugin</artifactId>
        <version>1.0.4</version>
        <configuration>
          <configurationFile>src/test/selenium/phantomjs.cfg</configurationFile>
          <testSuites>
            <testSuite>${project.basedir}/src/test/selenium/CASE-1/TestSuite-1.html</testSuite>
          </testSuites>
          <baseUrl>https://servername/</baseUrl>
        </configuration>
        <executions>
          <execution>
            <phase>integration-test</phase>
            <goals>
              <goal>seleneserunner</goal>
            </goals>
          </execution>
        </executions>
      </plugin>

# Credits

The actual test execution is forwarded to the library from the command line application  
[Selenese Runner Java](https://github.com/vmi/selenese-runner-java).
