
# Video Service - Documentação Técnica

## 📦 Visão Geral

- **Projeto:** video-service
- **Group ID:** com.ms
- **Versão:** 1.0-SNAPSHOT
- **Descrição:** Serviço responsável pelo gerenciamento de upload de vídeos com integração a AWS (S3 e SQS) e persistência em MongoDB.

---

## 🛠️ Tecnologias Utilizadas

- Java 17 (presumido)
- Spring Boot
  - spring-boot-starter-web
  - spring-boot-starter-data-rest
  - spring-boot-starter-data-mongodb
  - spring-boot-starter-validation
  - spring-boot-starter-security
- AWS SDK for Java v2
  - S3
  - SQS
- MongoDB
- Jackson (JSON)
- JUnit e Spring Test

---

## 📁 Estrutura de Pacotes

```
com.ms.video
├── VideoServiceApplication.java
├── adapter
│   ├── input.rest
│   │   ├── PreSignedVideoController.java
│   │   ├── VideoController.java
│   │   └── dto/*.java
│   ├── output.mongo
│   │   ├── SpringDataVideoRepository.java
│   │   ├── VideoRepositoryImpl.java
│   │   └── document/VideoDocument.java
│   ├── output.s3
│   │   ├── S3VideoStorage.java
│   │   └── S3PreSignedUrlGenerator.java
│   └── output.sqs
│       └── SqsPublisherImpl.java
├── application
│   ├── CreateVideoUseCase.java
│   ├── ConfirmUploadUseCase.java
│   ├── GeneratePreSignedUrlUseCase.java
│   └── ListVideosByClientUseCase.java
```

---

## 🌐 Endpoints REST

| Método | Endpoint                    | Descrição                          |
|--------|-----------------------------|------------------------------------|
| POST   | /videos                     | Criação de um novo vídeo           |
| GET    | /videos/{id}               | Consulta detalhes de um vídeo      |
| POST   | /videos/pre-signed-url      | Geração de URL pré-assinada (S3)   |
| POST   | /videos/confirm             | Confirmação de upload              |
| GET    | /health                     | Verificação de saúde do serviço    |

---

## 🔁 Integrações Externas

- **S3 (AWS):** Armazenamento dos vídeos via upload direto com URL pré-assinada.
- **SQS (AWS):** Envio de mensagens para notificação ou processamento assíncrono.
- **MongoDB:** Persistência de informações dos vídeos.

---

## 🔄 Fluxo Resumido

1. **Cliente requisita uma URL pré-assinada** via `/videos/pre-signed-url`.
2. **Realiza o upload do vídeo diretamente no S3**.
3. **Confirma o upload** via `/videos/confirm`.
4. **Mensagem é enviada à SQS** para o próximo passo de processamento.
5. **Dados do vídeo são persistidos no MongoDB**.

---

## 🧪 Testes

- Estrutura de testes baseada em JUnit e Spring Boot Test.

