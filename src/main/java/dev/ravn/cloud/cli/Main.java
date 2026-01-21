package dev.ravn.cloud.cli;

import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new RavnCommand()).execute(args);
        System.exit(exitCode);
    }
}
