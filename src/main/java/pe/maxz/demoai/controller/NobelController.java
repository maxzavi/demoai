package pe.maxz.demoai.controller;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.maxz.demoai.model.Author;

@RestController
@RequestMapping("/api/nobel")
public class NobelController {
    @Autowired
    private ChatClient chatClient;
    
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

    @GetMapping("/text/{year}")
    public String getByName(@PathVariable String year){
        String promt= """
            Quien gano el premio nobel de literatura el año {year} y que año nacio
            """;
        PromptTemplate promptTemplate = new PromptTemplate(promt);
        promptTemplate.add("year", year);
        String result =  chatClient.call(promptTemplate.render());
        return result;
    }
}
