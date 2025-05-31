#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
ROOT_DIR="${SCRIPT_DIR}/spring-cloud-root"
COMPOSE_FILE="${SCRIPT_DIR}/docker-compose.yml"

# Función para construir un microservicio
build_service() {
  local service_dir=$1
  echo "🔨 Construyendo $service_dir..."
  cd "${ROOT_DIR}/${service_dir}"
  mvn clean package -DskipTests
  cd "${SCRIPT_DIR}"
  echo "✅ $service_dir construido correctamente"
  echo    # Esto imprime un salto de línea
}

# Construir todos los microservicios
echo "🏗️  Iniciando construcción de todos los microservicios..."
build_service "registry.server"
build_service "config.server"
build_service "companies"
build_service "companies.fallback"
build_service "report.ms"
build_service "report.listener"
build_service "auth.server"
build_service "gateway"

# Construir y levantar los contenedores
echo "🐳 Iniciando contenedores con Docker Compose..."
sudo docker compose -f "${COMPOSE_FILE}" up --build

echo "🚀 Todos los servicios están en marcha!"