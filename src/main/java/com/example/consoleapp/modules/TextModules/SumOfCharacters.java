package com.example.consoleapp.modules.TextModules;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
public class SumOfCharacters extends AbstractTextModule {
    @Override
    public String getFunctionDescription() {
        return "Возвращает количество символов в тексте.";
    }

    @Override
    public void function(File file) {
        List<String> lines;
        try{
            lines = Files.readAllLines(file.toPath());
        }
        catch (IOException e){
            System.out.println("Не удалось прочитать файл " + file.getAbsolutePath());
            return;
        }
        int sum = lines.stream().flatMapToInt(String::chars).sum();
        System.out.println("Количество символов в файле: " + sum);
    }
}
