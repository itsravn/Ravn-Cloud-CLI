package dev.ravn.cloud.cli.commands;

import dev.ravn.cloud.cli.config.ConfigGenerator;
import dev.ravn.cloud.cli.docker.DockerClientStub;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "deploy", description = "Deploys a new Minecraft server instance")
public class DeployCommand implements Runnable {

    @Parameters(index = "0", description = "Name of the server instance")
    private String name;

    @Option(names = { "-v", "--version" }, description = "Minecraft version (default: latest)", defaultValue = "latest")
    private String version;

    @Option(names = { "-p", "--port" }, description = "Port to bind (default: 25565)", defaultValue = "25565")
    private int port;

    @Override
    public void run() {
        DockerClientStub docker = new DockerClientStub();

        System.out.println("Ravn Cloud Deployer v1.0");
        System.out.println("------------------------");

        docker.checkDaemon();

        // Generate Config
        ConfigGenerator.generate(name, port);

        // Deploy Container
        String image = "itzg/minecraft-server:" + version;
        docker.createContainer(name, image, port);

        System.out.println("\n✔ Deployment Complete. Server is starting up.");
        System.out.println("  Manage it using: ravn stop " + name);
    }
}
