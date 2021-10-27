package com.example.consoleapp.modules.ImageModules;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class ImageSizeModule extends AbstractImageModule{
    @Override
    public String getFunctionDescription() {
        return "Возвращает размер изображения";
    }

    @Override
    public void function(File file) {
        try{
            BufferedImage image = ImageIO.read(file);
            System.out.printf("Высота: %s, Длина: %s", image.getHeight(), image.getWidth());
        }
        catch (IOException e){
            System.out.println("Не удалось прочитать файл " + file.getAbsolutePath());
        }
    }
}
