package dev.ravn.cloud.cli.docker;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DockerClientStub {

    private static final Map<String, Container> containers = new ConcurrentHashMap<>();
    private static final File STATE_FILE = new File(System.getProperty("user.home"), ".ravn-cloud-state.ser");

    public static class Container implements Serializable {
        public String id;
        public String name;
        public String image;
        public int port;
        public String status;
        public String created;

        public Container(String id, String name, String image, int port, String status) {
            this.id = id;
            this.name = name;
            this.image = image;
            this.port = port;
            this.status = status;
            this.created = new Date().toString();
        }

        @Override
        public String toString() {
            return String.format("%s  %s  %s  0.0.0.0:%d->25565/tcp", id, image, status, port);
        }
    }

    public DockerClientStub() {
        loadState();
    }

    public void checkDaemon() {
        System.out.println("Checking Docker Daemon connection...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("✔ Docker Daemon is running (Engine v24.0.5)");
    }

    public String createContainer(String name, String image, int port) {
        System.out.printf("Pulling image '%s'...\n", image);
        simulateProgress("Downloading layers");

        String shortId = UUID.randomUUID().toString().substring(0, 12);
        Container container = new Container(shortId, name, image, port, "Up 1 second");
        containers.put(shortId, container);
        saveState();

        System.out.printf("✔ Container created: %s (%s)\n", name, shortId);
        return shortId;
    }

    public void startContainer(String containerId) {
        Container c = containers.get(containerId);
        if (c == null) {
            System.err.println("Error: No such container: " + containerId);
            return;
        }
        System.out.printf("Starting container %s...\n", containerId);
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
        }
        c.status = "Up 1 second";
        saveState();
        System.out.printf("✔ Container %s started on port %d\n", c.name, c.port);
    }

    public void stopContainer(String containerId) {
        Container c = containers.get(containerId);
        if (c == null) {
            System.err.println("Error: No such container: " + containerId);
            return;
        }
        System.out.printf("Stopping container %s...\n", containerId);
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
        }
        c.status = "Exited (0)";
        saveState();
        System.out.printf("✔ Container %s stopped\n", c.name);
    }

    public Collection<Container> listContainers() {
        return containers.values();
    }

    private void simulateProgress(String task) {
        System.out.print(task + ": [");
        for (int i = 0; i < 20; i++) {
            System.out.print("=");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("] Done");
    }

    @SuppressWarnings("unchecked")
    private void loadState() {
        if (!STATE_FILE.exists())
            return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STATE_FILE))) {
            Map<String, Container> loaded = (Map<String, Container>) ois.readObject();
            containers.clear();
            containers.putAll(loaded);
        } catch (Exception e) {
            // Ignore corrupted state
        }
    }

    private void saveState() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STATE_FILE))) {
            oos.writeObject(containers);
        } catch (IOException e) {
            System.err.println("Warning: Failed to save state: " + e.getMessage());
        }
    }
}
