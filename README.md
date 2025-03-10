# 📦 PedidoAPI + App Android

Uma aplicação composta por um **backend em .NET 6** e um **app Android em Java**, permitindo gerenciar pedidos através de uma API REST.

## 🛠️ Tecnologias Utilizadas
### Backend
- **.NET 6**
- **ASP.NET Core Web API**
- **xUnit** (para testes unitários)
- **Moq** (para mocks nos testes)

### Frontend (Android)
- **Java**
- **Android Studio**
- **Retrofit** (para comunicação HTTP)
- **RecyclerView** (para exibir pedidos)

## 🚀 Como Configurar e Rodar a API
### 1️⃣ **Pré-requisitos**
Antes de começar, certifique-se de ter instalado:
- [SDK do .NET 6](https://dotnet.microsoft.com/en-us/download/dotnet/6.0)
- [Visual Studio](https://visualstudio.microsoft.com/) ou [VS Code](https://code.visualstudio.com/)

### 2️⃣ **Clonar o Repositório**
```sh
git clone https://github.com/sharkb8i/teste-tecnico-codgx
cd teste-tecnico-codgox/backend/PedidoAPI
```

### 3️⃣ **Restaurar Dependências**
```sh
dotnet restore
```

### 4️⃣ **Rodar a API**
```sh
dotnet run
```
A API estará disponível em: `http://localhost:5123`.

### 5️⃣ **Testar a API (Exemplos)**
- Criar um pedido (POST):
  ```http
  POST http://localhost:5123/api/pedidos
  Content-Type: application/json

  {
    "nomeCliente": "João Silva",
    "produto": "Cadeira",
    "quantidade": 2,
    "precoTotal": 300.00
  }
  ```

- Obter todos os pedidos (GET):
  ```http
  GET http://localhost:5123/api/pedidos
  ```

- Obter pedidos paginados (GET):
  ```http
  GET http://localhost:5123/api/pedidos?pagina=1&tamanhoPagina=5
  ```

## 📱 Como Configurar e Rodar o App Android
### 1️⃣ **Pré-requisitos**
Antes de rodar o app, instale:
- [Android Studio](https://developer.android.com/studio)
- [JDK 17+](https://www.oracle.com/java/technologies/javase-downloads.html)

### 2️⃣ **Abrir o projeto no Android Studio**
1. Abra o **Android Studio**.
2. Clique em **Open** e selecione a pasta do projeto Android (`PedidoApp`).
3. Aguarde a sincronização do Gradle.

### 3️⃣ **Executar o App**
1. Conecte um dispositivo físico ou use um **emulador Android**.
2. No Android Studio, clique no botão **Run** ▶️.
3. O app será iniciado e permitirá:
   - Criar um novo pedido
   - Visualizar pedidos cadastrados

### 4️⃣ **Configurar Retrofit para Comunicar com a API**
No arquivo `RetrofitClient.java`, altere a base URL para:
```java
private static final String BASE_URL = "http://10.0.2.2:5000/api/";
```
⚠️ **Observação**: `10.0.2.2` é usado para acessar o `localhost` no emulador Android. Se estiver testando em um celular real, use o IP da máquina onde a API está rodando.

## ✅ Como Rodar os Testes da API
Entre na pasta de testes:
```sh
cd ../backend/PedidoAPI.Tests
```
E execute:
```sh
dotnet test
```
Se quiser ver logs detalhados:
```sh
dotnet test --verbosity detailed
```

## 📂 Estrutura do Projeto
```
teste-tecnico-codgox/
├── backend/                 # Pasta do backend
│   ├── PedidoAPI/           # API principal
│   │   ├── Controllers/     # Endpoints da API
│   │   ├── Models/          # Modelos de dados
│   │   ├── Services/        # Lógica de negócio
│   │   ├── Repositories/    # Persistência em memória
│   │   ├── DTOs/            # Objetos de transferência de dados
│   │   ├── Program.cs       # Ponto de entrada
│   │   ├── PedidoAPI.csproj # Arquivo do projeto
│   │
│   ├── PedidoAPI.Tests/     # Testes unitários
│   │   ├── PedidoServiceTests.cs
│   │   ├── PedidoControllerTests.cs
│   │   ├── PedidoAPI.Tests.csproj
│
├── frontend/                # Pasta do frontend
│   ├── PedidoApp/           # Aplicativo Android
│   │   ├── app/src/main/java/com/example/pedidoapp/
│   │   │   ├── model/       # Modelo de dados (Pedido.java)
│   │   │   ├── network/     # Comunicação com API (RetrofitClient.java, PedidoService.java)
│   │   │   ├── ui/          # Cores e temas
│   │   │   ├── utils/       # Utilitários (MoneyMask.java)
│   │   │   ├── view/        # Telas do app (MainActivity.java, PedidoAdapter.java)
│   │   │   ├── viewmodel/   # Lógica de UI (PedidoViewModel.java)
│   │   ├── build.gradle     # Configuração do Gradle
│
└── README.md                # Documentação
```

📌 **Desenvolvido com 💙 para o teste técnico.**