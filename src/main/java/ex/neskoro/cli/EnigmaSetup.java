package ex.neskoro.cli;

import ex.neskoro.Enigma;
import picocli.CommandLine;
import picocli.CommandLine.*;

import java.util.concurrent.Callable;

@Command(name = "setup", version = "setup 1.0")
public class EnigmaSetup implements Callable<Integer> {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new EnigmaSetup()).execute(args);
        System.exit(exitCode);
    }

    @Parameters(index = "0", description = "Type for state generator: random for random Enigma with init rotor count and Language; standard for setup Enigma from standard components")
    private SetupType type;

    @Override
    public Integer call() throws Exception {


        return 0;
    }

}
