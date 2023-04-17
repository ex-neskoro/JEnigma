package ex.neskoro.jenigma.cli;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "setup",
        subcommands = {EnigmaSetupRandom.class, EnigmaSetupStandard.class},
        sortOptions = false,
        description = "Command to setup up Enigma with provided parameters. Output Enigma settings in stdout")
public class EnigmaSetup implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("You must specify setup mode [random, standard]");
        return 0;
    }

}
