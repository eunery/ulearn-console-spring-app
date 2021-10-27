package com.example.consoleapp.modules.ImageModules;

import com.example.consoleapp.utils.FileUtils;
import com.example.consoleapp.modules.Module;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractImageModule implements Module {
    static final List<String> _supportedFormats = Arrays.asList("jpg","jpeg","png","bmp", "webp");

    @Override
    public boolean isSupportedFormat(File file) {
        return _supportedFormats.contains(FileUtils.getFileExtension(file));
    }

    @Override
    public abstract String getFunctionDescription();

    @Override
    public abstract void function(File file);
}
