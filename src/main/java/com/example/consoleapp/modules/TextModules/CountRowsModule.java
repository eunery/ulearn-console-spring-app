package com.example.consoleapp.modules.TextModules;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class CountRowsModule extends AbstractTextModule {

    @Override
    public String getFunctionDescription() {
        return "Возвращает количество строк в текстовом файле";
    }

    @Override
    public void function(File file) {
        try {
            int linesQuantity =  Files.readAllLines(file.toPath()).size();
            System.out.printf("В файле %s строк", linesQuantity);
        }
        catch (IOException e){
            System.out.println("Не удалось прочитать файл " + file.getAbsolutePath());
        }
    }
}
