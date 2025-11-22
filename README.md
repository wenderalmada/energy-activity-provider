# EnergyConsumptionManager – Inven!RA Activity Provider

## Conteúdo

* [Requisitos](#requisitos)
* [Estrutura do projeto](#estrutura-do-projeto)
* [Executar localmente (Maven)](#executar-localmente-maven)
* [Endpoints (descrição + exemplos)](#endpoints-descri%C3%A7%C3%A3o--exemplos)
* [Ficheiro de registo (activity manifest) para a Inven!RA](#ficheiro-de-registo-activity-manifest-para-a-invenra)

---

## Requisitos

* Java 17+
* Maven
* Docker (para build/produção)
* Conta GitHub (repositório com Dockerfile) para ligar ao Render

---

## Estrutura do projeto

```
energy-activity-provider/
 ├── src/main/java/com/provider
 │    ├── controller/
 │    │      ├── ConfigController.java        # GET /config
 │    │      ├── ParamsController.java        # GET /params
 │    │      ├── DeployController.java        # POST /deploy
 │    │      ├── AnalyticsListController.java # GET /analytics-list
 │    │      └── AnalyticsController.java     # POST /analytics
 │    └── EnergyActivityProviderApplication.java
 ├── src/main/resources/
 │    └── application.properties
 ├── Dockerfile
 └── pom.xml
```

---

## Executar localmente (Maven)

1. Clona o repositório:

```bash
git clone https://github.com/teu-usuario/energy-activity-provider.git
cd energy-activity-provider
```

2. Correr com Maven:

```bash
mvn spring-boot:run
```

`http://localhost:8080`.

---

## Endpoints (descrição + exemplos)


### 1) GET `/config`

**Descrição:** Retorna o ficheiro de registo / manifesto da atividade (URLs para os outros endpoints).

**Resposta (exemplo):**

```json
{
  "name": "EnergyConsumptionManager",
  "config_url": "https://energy-provider.onrender.com/config",
  "json_params_url": "https://energy-provider.onrender.com/params",
  "user_url": "https://energy-provider.onrender.com/deploy",
  "analytics_url": "https://energy-provider.onrender.com/analytics",
  "analytics_list_url": "https://energy-provider.onrender.com/analytics-list"
}
```

---

### 2) GET `/params`

**Descrição:** Lista os parâmetros configuráveis da atividade (json_params_url).

**Resposta (exemplo):**

```json
[
  {"name":"periodo_analise","type":"text/plain","description":"mensal|anual"},
  {"name":"integracao_solar","type":"boolean","description":"true|false"},
  {"name":"gerar_relatorio","type":"boolean","description":"true|false"}
]
```

---

### 3) POST `/deploy`

**Descrição:** Recebe pedido de deploy (instanciar atividade para um utilizador) e devolve `instanceId`.

**Pedido (exemplo):**

```json
{
  "userId": "professor01",
  "homeId": "smarthome_01",
  "deviceId": "thermo_01",
  "parameters": {"periodo_analise":"mensal","integracao_solar":true}
}
```

**Resposta (exemplo):**

```json
{
  "status":"success",
  "message":"Activity deployed successfully",
  "instanceId":"smarthome_01_thermo_01",
  "userId":"professor01",
  "parameters": {"periodo_analise":"mensal","integracao_solar":true}
}
```

---

### 4) GET `/analytics-list`

**Descrição:** Lista os analytics disponíveis (qualitativos e quantitativos).

**Resposta (exemplo):**

```json
{
  "qualAnalytics": [
    {"name":"Alertas de Consumo","type":"text/plain"}
  ],
  "quantAnalytics": [
    {"name":"Consumo Mensal (kWh)","type":"float"},
    {"name":"Custo Estimado (€)","type":"float"}
  ]
}
```

---

### 5) POST `/analytics`

**Descrição:** Recebe `instanceId` + lista de analytics e devolve os valores correspondentes.

**Pedido (exemplo):**

```json
{
  "instanceId":"smarthome_01_thermo_01",
  "analytics":["Consumo Mensal (kWh)", "Custo Estimado (€)"]
}
```

**Resposta (exemplo):**

```json
{
  "Consumo Mensal (kWh)": 22.4,
  "Custo Estimado (€)": 4.51
}
```

---

## Ficheiro de registo (activity manifest) para a Inven!RA

```json
{
  "name": "EnergyConsumptionManager",
  "config_url": "https://energy-provider.onrender.com/config",
  "json_params_url": "https://energy-provider.onrender.com/params",
  "user_url": "https://energy-provider.onrender.com/deploy",
  "analytics_url": "https://energy-provider.onrender.com/analytics",
  "analytics_list_url": "https://energy-provider.onrender.com/analytics-list"
}
```