package com.example.consoleapp.modules.TextModules;

import com.example.consoleapp.modules.Module;
import com.example.consoleapp.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractTextModule implements Module { // объеденяем все остальные модули в один модуль
    private static final List<String> _supportedFormats = Arrays.asList("txt");

    @Override
    public boolean isSupportedFormat(File file){
        return (_supportedFormats.contains(FileUtils.getFileExtension(file)));
    }

    @Override
    public abstract String getFunctionDescription();

    @Override
    public abstract void function(File file);
}
