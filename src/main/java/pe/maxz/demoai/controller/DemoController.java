package pe.maxz.demoai.controller;

import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    private ChatClient chatClient;
    
    @GetMapping("/")
    public String getAll(){
        String prompt="""
            Lista de todas las cervezas de todo el mundo que se venden en Peru, con su grado de alcohol y 
            su pais de procedencia
                """;
        return chatClient.call(prompt);
    }

    @GetMapping("/detail")
    public String getByName(@RequestParam (name = "name") String name){
        return chatClient.call("cuales son las presentaciones de cerveza " + name);
    }

}
