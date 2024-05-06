# Demo Spring AI using OpenAI
## OpenAi Platform:

Create API Key in https://platform.openai.com/api-keys

 
## Spring AI
Link Spring AI: https://spring.io/projects/spring-ai

Add api-key in application config, and model by default **gpt-3.5-turbo**:

```yml
spring:
  application:
    name: demoai
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
      chat:
        options:
          model: gpt-3.5-turbo      
```
## Groq

In case use Groq, it's free, model **mixtral**: **mixtral-8x7b-32768**:

```yml
    openai:
      api-key: ${OPENAI_API_KEY}
      chat:
        options:
          model: mixtral-8x7b-32768
        base-url: https://api.groq.com/openai
```

Set environment variable in SO:

Linux/Mac
```cmd
export OPENAI_API_KEY=******************
```

## Test

```cmd
curl --location 'http://localhost:8080/api/demo/'

curl --location 'http://localhost:8080/api/demo/detail?name=Corona'

curl --location 'http://localhost:8080/api/demo/stella artois'
```

## PromptTemplate

Use **PromptTemplate** to replace strings:

```java
        String promt= "cuales son las presentaciones de cerveza {name} en Peru?";
        PromptTemplate promptTemplate = new PromptTemplate(promt);
        promptTemplate.add("name", name);
        return chatClient.call(promptTemplate.render());
```

## BeanOutputParser

```java
    @GetMapping("/{year}")
    public Author getByNameDetail(@PathVariable String year){
        BeanOutputParser<Author> parser = new BeanOutputParser<>(Author.class);
        String promt= """
            Quien gano el premio nobel de literatura el año {year}
            {format}
            """;
        PromptTemplate promptTemplate = new PromptTemplate(promt);
        promptTemplate.add("year", year);
        promptTemplate.add("format", parser.getFormat());
        promptTemplate.setOutputParser(parser);
        String result =  chatClient.call(promptTemplate.render());
        return parser.parse(result);
    }
```