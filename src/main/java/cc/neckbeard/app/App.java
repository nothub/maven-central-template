package cc.neckbeard.app;

import com.sun.tools.javac.Main;
import org.apache.commons.cli.*;
import org.jetbrains.annotations.NotNull;
import org.tinylog.Logger;

public class App {

    public static void main(@NotNull String[] args) {

        Options options = new Options();

        Option optionFoo = new Option("f", "foo", true, "foo (required)");
        optionFoo.setRequired(true);
        options.addOption(optionFoo);

        Option optionBar = new Option("b", "bar", false, "bar");
        options.addOption(optionBar);

        CommandLine cmd;
        try {
            cmd = ((CommandLineParser) new DefaultParser()).parse(options, args);
        } catch (ParseException e) {
            Logger.error(e.getMessage());
            new HelpFormatter().printHelp(
                new java.io.File(
                    Main
                        .class
                        .getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .getPath()
                ).getName(),
                options);
            System.exit(1);
            return;
        }

        Logger.info("Hello World!");
        Logger.debug("foo: " + cmd.getOptionValue("foo"));
        Logger.debug("bar: " + Boolean.valueOf(cmd.hasOption("bar")).toString());

    }

}
