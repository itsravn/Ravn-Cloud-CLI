package dev.ravn.cloud.cli.commands;

import dev.ravn.cloud.cli.docker.DockerClientStub;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "stop", description = "Stops a running instance")
public class StopCommand implements Runnable {

    @Parameters(index = "0", description = "Container ID to stop")
    private String containerId;

    @Override
    public void run() {
        DockerClientStub docker = new DockerClientStub();
        docker.stopContainer(containerId);
    }
}
