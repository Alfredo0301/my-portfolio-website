# Guia de Deploy - Portfolio Website

Este documento descreve como fazer o deploy da aplicação Portfolio em diferentes plataformas.

## 📋 Pré-requisitos

- Java 17+ (versão 25 recomendada)
- Maven 3.6+
- Git
- Variáveis de ambiente configuradas (produção)

## 🚀 Deploy Local

### Desenvolvimento (H2 In-Memory Database)

```bash
cd PortfolioWebsite
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

H2 Console: `http://localhost:8080/h2-console`

### Produção Local (MySQL)

1. **Criar banco de dados:**
```sql
CREATE DATABASE db_portfolio;
CREATE USER 'portfolio_user'@'localhost' IDENTIFIED BY 'portfolio_password';
GRANT ALL PRIVILEGES ON db_portfolio.* TO 'portfolio_user'@'localhost';
FLUSH PRIVILEGES;
```

2. **Compilar e rodar:**
```bash
./mvnw clean package -DskipTests
java -Dspring.profiles.active=prod \
  -Dspring.datasource.url=jdbc:mysql://localhost:3306/db_portfolio \
  -Dspring.datasource.username=portfolio_user \
  -Dspring.datasource.password=portfolio_password \
  -jar target/PortfolioWebsite-0.0.1-SNAPSHOT.jar
```

Ou usar variáveis de ambiente:
```bash
export DB_URL=jdbc:mysql://localhost:3306/db_portfolio
export DB_USERNAME=portfolio_user
export DB_PASSWORD=portfolio_password

./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

## 🐳 Deploy com Docker

### Usando Docker Compose (Recomendado)

```bash
docker-compose up --build
```

Isso vai:
- Iniciar MySQL container
- Iniciar aplicação Spring Boot container
- Conectar automaticamente

Acesse: `http://localhost:8080`

### Usando Docker manualmente

1. **Build da imagem:**
```bash
./mvnw clean package -DskipTests
docker build -t portfolio-website .
```

2. **Run do container:**
```bash
docker run -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_URL=jdbc:mysql://seu-host:3306/db_portfolio \
  -e DB_USERNAME=seu_usuario \
  -e DB_PASSWORD=sua_senha \
  -p 8080:8080 \
  portfolio-website
```

## 🚁 Deploy em Heroku

### Pré-requisitos
- Conta Heroku
- Heroku CLI instalado
- Git configurado

### Passos

1. **Login no Heroku:**
```bash
heroku login
```

2. **Criar aplicação:**
```bash
heroku create seu-app-portfolio
```

3. **Adicionar MySQL (ClearDB):**
```bash
heroku addons:create cleardb:ignite
```

4. **Configurar variáveis de ambiente:**
```bash
heroku config:set SPRING_PROFILES_ACTIVE=prod
```

5. **Deploy:**
```bash
git push heroku main
```

6. **Ver logs:**
```bash
heroku logs --tail
```

## ☁️ Deploy em AWS

### EC2 + RDS

1. **Launch EC2 Instance:**
   - AMI: Ubuntu 22.04
   - Instance Type: t2.micro (free tier)
   - Security Group: Abrir porta 8080

2. **Conectar via SSH:**
```bash
ssh -i sua-chave.pem ubuntu@seu-ec2-ip
```

3. **Instalar Java:**
```bash
sudo apt update
sudo apt install openjdk-25-jdk -y
```

4. **Clonar e fazer deploy:**
```bash
git clone seu-repo
cd PortfolioWebsite
./mvnw clean package -DskipTests
java -Dspring.profiles.active=prod \
  -Dspring.datasource.url=jdbc:mysql://seu-rds-endpoint:3306/db_portfolio \
  -Dspring.datasource.username=admin \
  -Dspring.datasource.password=sua_senha \
  -jar target/PortfolioWebsite-0.0.1-SNAPSHOT.jar
```

### Usar PM2 para manter rodando:
```bash
npm install -g pm2
pm2 start "java -jar target/PortfolioWebsite-0.0.1-SNAPSHOT.jar" --name portfolio
pm2 save
sudo pm2 startup
```

## 🌐 Deploy em Railway

### Passos

1. **Conectar repositório:**
   - Acesse railway.app
   - Conecte seu GitHub
   - Selecione o repositório

2. **Configurar variáveis:**
   - Vá para Settings
   - Adicione `SPRING_PROFILES_ACTIVE=prod`
   - Railway fornecerá `DATABASE_URL`

3. **Deploy automático:**
   - Push para main branch
   - Railway fará deploy automaticamente

## 📝 Configuração de Produção

### Variáveis de Ambiente Essenciais:

```bash
SPRING_PROFILES_ACTIVE=prod
DB_URL=jdbc:mysql://seu-host:3306/db_portfolio
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha_forte
```

### application-prod.properties

A aplicação usa:
```properties
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
logging.level.root=INFO
```

Isso significa:
- ✅ Não altera banco de dados automaticamente
- ✅ Sem logs SQL
- ✅ Performance otimizada

## 🔒 Segurança em Produção

1. **Usar HTTPS:**
```yaml
server.ssl.enabled=true
server.ssl.key-store=classpath:keystore.jks
server.ssl.key-store-password=sua_senha
```

2. **Variáveis sensíveis:**
   - Nunca versione `application.properties`
   - Use variáveis de ambiente
   - Usar serviço como AWS Secrets Manager

3. **CORS Configuration:**
```java
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("seu-dominio.com")
                    .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
```

## 🛠️ Troubleshooting

### Erro: "Cannot connect to database"
- Verificar credenciais DB_URL, DB_USERNAME, DB_PASSWORD
- Testar conexão: `mysql -h host -u user -p`

### Erro: "Port 8080 already in use"
- Mudar porta: `-Dserver.port=8081`
- Ou matar processo: `lsof -i :8080 | kill -9`

### Erro: "Java version not compatible"
- Usar Java 17 LTS se Java 25 tiver problemas
- Mudar em pom.xml: `<java.version>17</java.version>`

## 📊 Monitoring

### Logs
```bash
tail -f logs/application.log
```

### Health Check
```bash
curl http://localhost:8080/actuator/health
```

### Metrics (Adicionar dependência)
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## ✅ Checklist Pré-Deploy

- [ ] Banco de dados criado e configurado
- [ ] Variáveis de ambiente definidas
- [ ] `mvnw clean package -DskipTests` executa com sucesso
- [ ] Testes passam: `./mvnw test`
- [ ] `application.properties` não foi versionado
- [ ] README.md está atualizado
- [ ] HTTPS configurado (produção)
- [ ] Backups automáticos do banco de dados
- [ ] Logs centralizados (opcional)
- [ ] CDN para assets estáticos (opcional)

---

**Suporte**: Para dúvidas, consulte o README.md ou crie uma issue no GitHub.

