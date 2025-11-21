# --------- ESTÁGIO 1: BUILD ---------
# Usamos uma imagem que já vem com Maven e JDK 21 instalados
FROM maven:3.9-eclipse-temurin-21 AS build

# Define a pasta de trabalho dentro do container de build
WORKDIR /app

# Copia os arquivos do projeto para dentro do container
COPY pom.xml .
COPY src ./src

# Roda o comando do Maven para gerar o .jar (dentro do Docker!)
# O flag -DskipTests agiliza o processo pulando testes unitários
RUN mvn clean package -DskipTests

# --------- ESTÁGIO 2: RUN ---------
# Usamos uma imagem leve apenas com o JRE (Java Runtime) para rodar
FROM eclipse-temurin:21-jre

WORKDIR /app

# A MÁGICA: Copia o .jar DO estágio de build (build) PARA este estágio atual
COPY --from=build /app/target/taskmanager-0.0.1-SNAPSHOT.jar app.jar

# Define o comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]