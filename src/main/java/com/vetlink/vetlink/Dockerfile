# 1 - Imagem base
FROM maven:3.9.9-eclipse-temurin-17-alpine

# 2 - Atualizar Alpine
RUN apk update

# 3 - Criar usuario
RUN adduser -h /home/usrapp -s /bin/sh -D userapp

# 4 - Usar usuario
USER userapp

# 5 - Diretorio padrao
WORKDIR /app

# 6 - Copiar projeto
COPY . .

# 7 - Build Maven
RUN mvn clean package -DskipTests

# 8 - Expor porta
EXPOSE 8080

# 9 - Executar aplicacao
CMD ["java", "-jar", "target/vetlink-0.0.1-SNAPSHOT.jar"]