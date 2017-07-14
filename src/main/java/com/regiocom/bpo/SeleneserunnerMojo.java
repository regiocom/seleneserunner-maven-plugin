package com.regiocom.bpo;

import jp.vmi.selenium.selenese.Main;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.util.ArrayList;

/**
 * Run a list of test suites created by Selenium IDE.
 *
 * @goal seleneserunner
 * @phase integration-test
 */
public class SeleneserunnerMojo extends AbstractMojo {
    /**
     * Location of the file.
     *
     * @parameter
     * @required
     */
    private File configurationFile;

    /**
     * Test suites to run
     *
     * @parameter
     * @required
     */
    private File[] testSuites;

    /**
     * override base URL set in selenese
     *
     * @parameter
     * @optional
     */
    private String baseUrl;

    public void execute() throws MojoExecutionException {
        getLog().info("Using config file " + configurationFile.getAbsolutePath());

        ArrayList<String> args = new ArrayList<>();

        // config file to use
        args.add("--config");
        args.add(configurationFile.getAbsolutePath());

        // do not call System.exit() as it breaks the maven build
        args.add("--no-exit");

        if (baseUrl != null && !baseUrl.isEmpty()) {
            args.add("--baseurl");
            args.add(baseUrl);
        }

        for (File testSuiteFile : this.testSuites) {
            getLog().info("Adding test suite " + testSuiteFile.getName());

            // add the test suite as last parameter
            args.add(testSuiteFile.getAbsoluteFile().toString());
        }

        // Create and run
        Main main = new Main();
        main.run(args.toArray(new String[0]));

        // If exit code <> 0: throw exception
        if (main.getExitCode() != 0) {
            throw new MojoExecutionException("Error in test");
        }
    }
}
