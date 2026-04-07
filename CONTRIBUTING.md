# Guia de Contribuição

Obrigado por querer contribuir para o Portfolio Website! Este documento descreve o processo de desenvolvimento.

## 🔧 Setup do Ambiente

### 1. Clonar repositório
```bash
git clone https://github.com/seu-usuario/PortfolioWebsite.git
cd PortfolioWebsite
```

### 2. Instalar dependências
```bash
./mvnw clean install
```

### 3. Executar em desenvolvimento
```bash
./mvnw spring-boot:run
```

A aplicação estará em `http://localhost:8080`

### 4. Acessar H2 Console (Desenvolvimento)
```
http://localhost:8080/h2-console
URL: jdbc:h2:mem:portfolio_db
Usuario: sa
Senha: (deixar em branco)
```

## 📁 Estrutura do Projeto

```
src/main/
├── java/com/portfolio/PortfolioWebsite/
│   ├── controller/          # REST/Web Controllers
│   ├── Model/               # JPA Entities
│   ├── Repository/          # Data Access Layer
│   └── PortfolioWebsiteApplication.java
└── resources/
    ├── templates/           # Thymeleaf templates
    ├── static/
    │   ├── css/
    │   └── image/
    └── application.properties
```

## 🐛 Reportando Bugs

Ao reportar um bug, inclua:
- Descrição clara do problema
- Passos para reproduzir
- Comportamento esperado vs. atual
- Environment (OS, Java version, etc)
- Logs de erro

## ✨ Sugerindo Features

1. Abra uma issue descrevendo a feature
2. Explique o caso de uso
3. Discuta a implementação se necessário

## 💻 Pull Request Process

### 1. Criar branch
```bash
git checkout -b feature/sua-feature
```

### 2. Fazer mudanças
- Siga as convenções de código
- Adicione testes para novas funcionalidades
- Atualize documentação

### 3. Testar
```bash
./mvnw clean test
./mvnw clean package -DskipTests
```

### 4. Commit
```bash
git commit -m "feat: descrição clara da mudança"
```

Use padrão Conventional Commits:
- `feat:` nova feature
- `fix:` correção de bug
- `docs:` mudanças na documentação
- `style:` formatação/style
- `refactor:` refatoração
- `test:` testes

### 5. Push
```bash
git push origin feature/sua-feature
```

### 6. Abrir Pull Request
- Descreva as mudanças
- Linke issues relacionadas
- Espere review

## 📋 Padrões de Código

### Java
- Use nomes descritivos
- Maximo 100 caracteres por linha
- Use Lombok para getters/setters
- Escreva testes para nova lógica

### HTML/CSS
- Use nomes de classes semânticos
- Mobile-first approach
- Validar HTML

### Exemplo de Controller
```java
@Controller
@RequestMapping("/api/features")
public class FeatureController {

    @Autowired
    private FeatureRepository featureRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("features", featureRepository.findAll());
        return "features";
    }
}
```

### Exemplo de Teste
```java
@SpringBootTest
@AutoConfigureMockMvc
class FeatureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListReturnsSuccess() throws Exception {
        mockMvc.perform(get("/api/features"))
                .andExpect(status().isOk());
    }
}
```

## 📝 Commits

Bons commit messages:
```
feat: adicionar autenticação de usuário

- Implementar login com JWT
- Adicionar validação de email
- Testes unitários para autenticação
```

Evitar:
```
fix stuff
update code
changes
```

## 🧪 Testes

Todos os PRs devem ter testes:
```bash
./mvnw test
```

Cobertura mínima: 70%

## 📚 Documentação

- Atualize README.md se adicionar features
- Documente novas endpoints em comments
- Use JavaDoc para classes públicas

## ✅ Checklist para PR

- [ ] Testes adicionados/atualizados
- [ ] Documentação atualizada
- [ ] Sem warnings do compilador
- [ ] Código formatado
- [ ] Commits com boas mensagens
- [ ] Funcionalities testadas localmente
- [ ] Nenhum `System.out.println()`
- [ ] Sem hardcoded credentials

## 🚀 Processo de Review

1. Revisor comenta sobre mudanças
2. Autor responde/faz ajustes
3. Revisor aprova
4. Merge para `main`
5. Branch deletado

## 📦 Release

Versioning segue Semantic Versioning (MAJOR.MINOR.PATCH):
- MAJOR: breaking changes
- MINOR: novas features (backward compatible)
- PATCH: bug fixes

## ❓ Dúvidas?

- Abra uma discussion no GitHub
- Envie email para o maintainer
- Crie uma issue com tag `question`

---

**Obrigado por contribuir! 🎉**

