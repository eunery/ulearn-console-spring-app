package com.example.consoleapp;

import com.example.consoleapp.modules.Module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MapFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class FilesService {
    @Autowired
    List<Module> _modules;

    public void executeFunction(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

//        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.example.consoleapp.modules");
        Module[] modules = getAvailableModules(file);
        if (modules.length == 0) {
            System.out.println("Нет модулей для этого файла.");
            return;
        }
        printAvailableModules(file, modules);
        int number = askUserForNumberOfFunction(modules.length);
        modules[number - 1].function(file);
    }

    private int askUserForNumberOfFunction(int max) {
        while (true) {
            System.out.printf("Введите номер функции (%d-%d): ", 1, max);
            String input = new Scanner(System.in).nextLine();
            int number;
            try {
                number = Integer.parseInt(input.strip());
                if (number < 1 || number > max) {
                    System.out.println("Некорректный номер");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный номер");
            }
        }
    }

    private void printAvailableModules(File file, Module[] modules) {
        System.out.println("-----------------------------------------------------");
        System.out.println("Доступные функции для файла " + file.getAbsolutePath() + ":");
        for (int i = 0; i < modules.length; i++) {
            System.out.println((i + 1) + ". " + modules[i].getFunctionDescription());
        }
    }

    private Module[] getAvailableModules(File file) {
        return _modules
                .stream()
                .filter(module -> module.isSupportedFormat(file))
                .toArray(Module[]::new);
    }
}
