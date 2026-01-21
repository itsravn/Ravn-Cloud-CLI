package dev.ravn.cloud.cli.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ConfigGenerator {

    public static void generate(String serverName, int port) {
        File file = new File(serverName + "-config", "server.properties");
        File dir = file.getParentFile();
        
        System.out.printf("Generating configuration for %s...\n", serverName);

        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                System.out.println("Created directory: " + dir.getAbsolutePath());
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("#Minecraft server properties");
            writer.println("#" + new java.util.Date());
            writer.println("motd=Ravn Cloud Instance - " + serverName);
            writer.println("server-port=" + port);
            writer.println("query.port=" + port);
            writer.println("enable-rcon=false");
            writer.println("difficulty=hard");
            writer.println("gamemode=survival");
            writer.println("max-players=20");
            writer.println("view-distance=10");
            writer.println("white-list=false");
            writer.println("online-mode=true");
            writer.println("spawn-protection=0");
            writer.println("simulation-distance=8");
            
            System.out.println("✔ server.properties generated successfully at " + file.getPath());
            
        } catch (IOException e) {
            System.err.println("Failed to generate config: " + e.getMessage());
        }
    }
}
