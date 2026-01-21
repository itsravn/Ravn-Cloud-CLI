package dev.ravn.cloud.cli.commands;

import dev.ravn.cloud.cli.docker.DockerClientStub;
import picocli.CommandLine.Command;

@Command(name = "list", description = "Lists all running instances")
public class ListCommand implements Runnable {

    @Override
    public void run() {
        DockerClientStub docker = new DockerClientStub();
        var containers = docker.listContainers();

        System.out.printf("%-15s %-30s %-20s %-25s\n", "CONTAINER ID", "IMAGE", "STATUS", "PORTS");

        if (containers.isEmpty()) {
            System.out.println("No running instances found.");
        } else {
            for (var c : containers) {
                System.out.printf("%-15s %-30s %-20s 0.0.0.0:%d->25565/tcp\n",
                        c.id, c.image, c.status, c.port);
            }
        }
    }
}
