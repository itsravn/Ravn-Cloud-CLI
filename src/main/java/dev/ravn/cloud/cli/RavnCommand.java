package dev.ravn.cloud.cli;

import dev.ravn.cloud.cli.commands.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "ravn", mixinStandardHelpOptions = true, version = "1.0.0", description = "Ravn Cloud CLI - Manage remote Minecraft instances", subcommands = {
        DeployCommand.class,
        ListCommand.class,
        StopCommand.class,
        CommandLine.HelpCommand.class
})
public class RavnCommand implements Runnable {

    @Override
    public void run() {
        // Default action if no subcommand is provided
        CommandLine.usage(this, System.out);
    }
}
