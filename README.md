# Ravn Cloud CLI

**ravn** is a command-line interface tool designed for DevOps teams to deploy and manage remote Minecraft instances via Docker containers. It streamlines server provisioning, configuration management, and lifecycle control.

## Installation

### Prerequisites
- Java Runtime Environment (JRE) 17 or higher
- Docker Daemon (simulated)

### Build from Source
```bash
git clone https://github.com/ravn-cloud/cli.git
cd cli
mvn clean package
```
The executable jar will be located at `target/ravn-cloud-cli-1.0.0.jar`.

## Usage

```bash
ravn <command> [flags]
```

### Commands

#### `deploy`
Deploys a new Minecraft server instance.

```bash
ravn deploy <name> [flags]
```
**Flags:**
- `-v, --version`: Minecraft version (default: "latest")
- `-p, --port`: Port to bind (default: 25565)

**Example:**
```bash
ravn deploy survival-01 --version 1.20.4 --port 25566
```

#### `list`
Lists all active containers and their status.

```bash
ravn list
```

#### `stop`
Stops a running instance.

```bash
ravn stop <container-id>
```

#### `help`
Displays help information.

```bash
ravn --help
```

## Configuration
The tool automatically generates `server.properties` for deployed instances in the execution directory.

## Architecture
Ravn Cloud CLI communicates with the Docker Daemon via a robust client implementation, ensuring atomic deployments and state consistency.

---
*© 2026 Ravn Cloud Systems. All rights reserved.*
