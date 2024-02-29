# nanodata

Para Iniciar o Projeto vai ser preciso o banco de dados com os seguintes dados.
```
database=nanodata
username=teste
password=teste
```

Abaixo ja esta configurado o Docker do Postgresql e o Frontend.

# Docker

## Iniciar docker compose 
```bash
docker compose -f "docker-compose.yml" up -d --build
```
## Stop docker compose
  ```bash
    docker compose -f "docker-compose.yml" down 
  ```

# JAVA SPRINGBOOT
## Para iniciar o projeto backend rode o comando a seguir
Lembre-se de verificar o diretorio
```bash
  cd server/
  mvn spring-boot:run
  
  ou
  
  cd server/ && mvn spring-boot:run
```