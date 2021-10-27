package com.example.consoleapp.modules.DirectoryModules;

import com.example.consoleapp.modules.Module;

import java.io.File;

public abstract class AbstractDirectoryModule implements Module {
    @Override
    public boolean isSupportedFormat(File file) {
        return file.isDirectory();
    }

    @Override
    public abstract String getFunctionDescription();

    @Override
    public abstract void function(File file);
}
