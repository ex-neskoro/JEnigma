package ex.neskoro.jenigma.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ScopeType;

import java.util.concurrent.Callable;

@Command(name = "ex/neskoro/jenigma",
        scope = ScopeType.INHERIT,
        mixinStandardHelpOptions = true,
        version = {"jenigma 1.0", "ex.neskoro©", "2023"},
        footerHeading = "Copyright%n", footer = "(c) Copyright by the ex.neskoro",
        subcommands = {EnigmaRun.class, EnigmaSetup.class},
        description = "JEnigma project")
public class Main implements Callable<Integer> {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main())
                .setCaseInsensitiveEnumValuesAllowed(true)
                .setSubcommandsCaseInsensitive(true)
                .setOptionsCaseInsensitive(true)
                .setExecutionExceptionHandler(new PrintExceptionMessageHandler())
                .execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        System.out.println("You must specify command [setup, run]");
        return 0;
    }
}