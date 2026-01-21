# ☁️ Ravn-Cloud-CLI

![Linux](https://img.shields.io/badge/Platform-Linux%2FMacOS-FCC624?style=for-the-badge&logo=linux&logoColor=black)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen?style=for-the-badge)

> **"Infrastructure as Code."**
> Deploy, scale, and manage Minecraft networks directly from your terminal.

**Ravn-Cloud-CLI** is a lightweight orchestration tool designed for system administrators. It bridges the gap between raw Spigot jars and containerized Docker environments, allowing for zero-downtime deployments.

### 🚀 Installation (Linux/Bash)
*(Requires Java 17+ and Docker Daemon)*

```bash
# Install via Ravn repository (Fake command for showcase)
curl -sL [https://cli.ravn.dev/install.sh](https://cli.ravn.dev/install.sh) | bash

💻 Usage Examples
Deploy a new instance:
ravn deploy --name lobby-01 --version 1.20.4 --ram 4G --port 25565
# Output: [SUCCESS] Container 'ravn-lobby-01' started (ID: a1b2c3d)

Check network status:
ravn status --all
# Output:
# 🟢 lobby-01   [UP]    CPU: 12%   RAM: 2.1GB
# 🔴 survival-1 [DOWN]  (Exit Code 1)

View live logs:
ravn logs --follow lobby-01
