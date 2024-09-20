# Predium - Sistema de Gerenciamento de Ordens de Serviço

**Predium** é um sistema de gerenciamento de ordens de serviço para manutenção predial, desenvolvido em Java Swing, com persistência de dados feita em arquivos CSV. O sistema oferece funcionalidades para registrar ordens de serviço, atribuir técnicos, editar e excluir ordens, e visualizar relatórios.

## Funcionalidades

- **Cadastro de Usuários**: Permite a criação de novos usuários para o sistema com armazenamento de informações em arquivo CSV.
- **Login de Usuários**: Sistema de autenticação simples via arquivos CSV.
- **Cadastro de Ordens de Serviço**: Permite o registro de ordens de serviço contendo descrição, local e prioridade.
- **Atribuição de Técnicos**: Possibilita a atribuição de técnicos para ordens de serviço.
- **CRUD de Ordens de Serviço**: O sistema permite criar, editar e excluir ordens de serviço existentes.
- **Relatórios**: Geração de relatórios das ordens de serviço cadastradas.
- **Persistência via CSV**: Todos os dados de usuários, ordens de serviço e técnicos são armazenados em arquivos CSV para simplicidade e fácil manutenção.

## Estrutura de Pastas

```
predium/
│
├── src/
│   └── main/
│       └── java/
│           └── com/mycompany/predium/
│               ├── controller/
│               ├── model/
│               ├── view/
│               └── resources/
│                   └── usuarios.csv
│                   └── ordens.csv
│                   └── tecnicos.csv
│
└── README.md

```

- `controller/`: Classes que fazem o gerenciamento das funcionalidades do sistema, como login, cadastro e ordens de serviço.
- `model/`: Classes que representam os objetos principais, como Usuários, Técnicos e Ordens de Serviço.
- `view/`: Telas do sistema feitas em Java Swing.
- `resources/`: Arquivos CSV que armazenam os dados do sistema (usuários, ordens de serviço, técnicos).

## Como Executar

1. **Clonar o repositório**:
   ```bash
   git clone https://github.com/SEU_USUARIO/NOME_REPOSITORIO.git
   cd NOME_REPOSITORIO
   ```

2. **Abrir o projeto no NetBeans**:
   - Abra o NetBeans IDE.
   - Selecione `File > Open Project` e navegue até o diretório do projeto clonado.
   - Selecione a pasta do projeto e clique em **Open Project**.

3. **Executar o projeto**:
   - Na aba de projetos do NetBeans, clique com o botão direito no nome do projeto.
   - Selecione **Run** para iniciar a aplicação.

## Tecnologias Utilizadas

- **Java SE 8+**
- **Java Swing** para a interface gráfica
- **Arquivos CSV** para persistência de dados
- **NetBeans IDE** para desenvolvimento

## Melhorias Futuras

- Implementar autenticação com níveis de acesso (administrador, técnico, etc.).
- Migrar a persistência de dados de arquivos CSV para um banco de dados relacional, como PostgreSQL ou MySQL.
- Criar uma interface web para maior acessibilidade e mobilidade.
- Implementar uma API REST para integração com outras ferramentas.

## Contribuição

Sinta-se à vontade para contribuir com este projeto. Para contribuir:

1. Faça um fork do projeto.
2. Crie uma branch com sua feature ou correção: `git checkout -b minha-feature`.
3. Faça o commit das suas alterações: `git commit -m 'Adicionando nova feature'`.
4. Envie para a branch principal: `git push origin minha-feature`.
5. Abra um Pull Request.

## Licença

Este projeto é de código aberto e está licenciado sob a [MIT License](LICENSE).

---

Desenvolvido por Marques Vinícius Melo Martins.
