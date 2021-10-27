package com.example.consoleapp.modules.DirectoryModules;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

@Component
public class ListFilesModule extends AbstractDirectoryModule{
    @Override
    public String getFunctionDescription() {
        return "Возвращает список файлов в папке.";
    }

    @Override
    public void function(File file) {
        Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .map(x -> (x.isDirectory()? "dir:" : "file:") + "\t" + x.getName())
                .forEach(System.out::println);
    }
}
