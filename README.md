LocaSpace: Plataforma de Gestão de Reservas

LocaSpace é um projeto de sistema de reservas desenvolvido em Java com foco na aplicação e demonstração dos princípios fundamentais da Programação Orientada a Objetos (POO).

O sistema permite o cadastro e gerenciamento de diferentes tipos de usuários (Administrador, Cliente, Locatário), estabelecimentos e unidades, além de simular o ciclo de vida de uma reserva, desde a criação até a confirmação/cancelamento.
✨ Destaques do Projeto

    Modelagem de Domínio: Estrutura clara e coesa de classes representando entidades do mundo real (Usuário, Cliente, Reserva, Estabelecimento).

    Implementação de POO: Aplicação prática de Herança, Encapsulamento, Polimorfismo e Classes Abstratas.

    Persistência Simples: Utilização de arquivos de texto para simular o armazenamento de dados.

    Interface Gráfica (Swing): Demonstração de interação com o usuário para cadastro de entidades.

    Testes Unitários (JUnit 5): Validação das regras de negócio e funcionalidades centrais do sistema.

🛠️ Tecnologias Utilizadas
Categoria	Tecnologia	Observações
Linguagem	Java	Core do sistema.
POO	Classes, Herança, Encapsulamento, Polimorfismo.	Foco principal do projeto.
Persistência	Arquivos de Texto (.txt)	Simulação de banco de dados (GerenciadorDados).
Testes	JUnit 5	Testes de unidade para validação de regras.
Interface	Swing (AWT/JFrame)	Interface gráfica simples para cadastro.
💡 Conceitos de POO Aplicados

O projeto LocaSpace é uma excelente demonstração dos pilares da Programação Orientada a Objetos:
1. Herança e Classes Abstratas

    A classe Usuario é abstrata e serve como base.

    As classes Administrador, Cliente e Locatario herdam de Usuario, reutilizando atributos como id, login e senha, mas adicionando funcionalidades e atributos específicos de cada perfil.

2. Encapsulamento

    Todos os atributos em todas as classes de modelo (Cliente, Reserva, Unidade, etc.) são declarados como private.

    O acesso e modificação desses atributos são controlados através de métodos públicos getters e setters, garantindo a integridade dos dados.

3. Polimorfismo

    Embora sutil no código enviado, a estrutura permite que, em um futuro desenvolvimento, métodos como um hipotético fazerLogin() poderiam ser sobrescritos (Override) nas classes filhas (Administrador, Cliente, Locatario) para implementar lógicas de autenticação diferentes, demonstrando Polimorfismo por Sobrescrita.

4. Enumerações (Enums)

    Uso de StatusReserva (PENDENTE, CONFIRMADA, CANCELADA, NEGADA) e TipoEstabelecimento para tipagem forte, controlando os valores possíveis de forma segura e organizada.

📂 Estrutura e Classes Principais

O projeto está organizado em pacotes distintos (model, service, ui, test):

locaspace/
├── model/                  # Classes de Modelo e Entidades (POO)
│   ├── Usuario.java (Abstract)
│   ├── Administrador.java
│   ├── Cliente.java
│   ├── Locatario.java
│   ├── Estabelecimento.java
│   ├── Unidade.java
│   ├── Reserva.java
│   ├── StatusReserva.java (Enum)
│   └── TipoEstabelecimento.java (Enum)
├── service/                # Lógica de Serviço e Persistência
│   └── GerenciadorDados.java # Salva entidades em arquivos .txt
├── ui/                     # Interface com o Usuário
│   └── SistemaPrincipal.java # Interface Swing (Cadastro)
└── test/                   # Testes de Unidade
    └── STesteUnidadeSistema.java # Testes JUnit

⚙️ Funcionalidades e Regras de Negócio

O sistema implementa as seguintes funcionalidades:
Perfil	Ação	Regra de Negócio
Cliente	Criar Reserva	A reserva é criada com status PENDENTE.
Locatario	Confirmar Reserva	Altera o status para CONFIRMADA e a unidade reservada passa a isDisponivel: false.
Locatario	Cancelar/Negar Reserva	Altera o status para CANCELADA ou NEGADA.
Reserva	Calcular Custo	O custo é baseado na diferença de datas de início e fim da reserva (diárias) e no preço da diária da Unidade.
Administrador	Gerenciar Sistema	Funções de auditoria (auditarLog) e controle de usuários (bloquearUsuario).
🚀 Como Executar o Projeto

    Pré-requisitos: Certifique-se de ter o Java Development Kit (JDK) 8+ instalado.

    Clone o Repositório:
    Bash

    git clone [LINK_DO_SEU_REPOSITORIO]
    cd locaspace

    Compilar e Executar a Interface (UI):

        Abra o projeto em sua IDE preferida (IntelliJ, Eclipse, VS Code).

        Compile os arquivos Java.

        Execute a classe principal: locaspace.ui.SistemaPrincipal.

        A interface gráfica aparecerá, permitindo o cadastro de Cliente, Estabelecimento e Unidade, salvando os dados em arquivos .txt na raiz do projeto.

    Executar os Testes Unitários:

        A classe STesteUnidadeSistema.java utiliza JUnit 5.

        Execute os testes na sua IDE para verificar se o cálculo de custo e a gestão de status da reserva estão funcionando corretamente.

🤝 Contribuições

Sinta-se à vontade para enviar pull requests para melhorias, como a integração com um banco de dados real (JDBC/Hibernate) ou a refatoração da UI.

Desenvolvedor: Antonio Lincon Leite Militão, Samuel Araujo Chaves Dias e Marcela Hemilly Fernandes Cavalcante
