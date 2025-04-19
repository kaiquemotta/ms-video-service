# Etapa 1: Build
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copia o projeto e compila
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Runtime (imagem leve)
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copia o JAR da etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
