# Demo Spring AI using OpenAI
## OpenAi Platform:

Create API Key in https://platform.openai.com/api-keys

 
## Spring AI
Link Spring AI: https://spring.io/projects/spring-ai

Add api-key in application config:

```yml
spring:
  application:
    name: demoai
  ai:
    openai:
      api-key: asas**********asas
```

## Test

```cmd
curl --location 'http://localhost:8080/api/demo/'

curl --location 'http://localhost:8080/api/demo/detail?name=Corona'

```

