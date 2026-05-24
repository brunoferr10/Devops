# 1 - Imagem base
FROM maven:3.9.9-eclipse-temurin-17-alpine

# 2 - Atualizar Alpine
RUN apk update

# 3 - Criar usuario
RUN adduser -h /home/usrapp -s /bin/sh -D userapp

# 4 - Diretorio padrao
WORKDIR /app

# 5 - Copiar projeto
COPY . .

# 6 - Build Maven
RUN mvn package -DskipTests

# 7 - Dar permissao
RUN chown -R userapp:userapp /app

# 8 - Trocar usuario
USER userapp

# 9 - Expor porta
EXPOSE 8080

# 10 - Executar aplicacao
CMD ["java", "-jar", "target/vetlink-0.0.1-SNAPSHOT.jar"]