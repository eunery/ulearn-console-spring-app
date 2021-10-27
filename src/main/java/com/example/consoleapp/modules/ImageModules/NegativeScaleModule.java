package com.example.consoleapp.modules.ImageModules;

import com.example.consoleapp.utils.FileUtils;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class NegativeScaleModule extends AbstractImageModule{
    @Override
    public String getFunctionDescription() {
        return "Создаёт изображение в негативе";
    }

    @Override
    public void function(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            File newFile = new File("Negative_" + file.getName());
            Files.copy(Paths.get(file.getAbsolutePath()), Paths.get(newFile.getAbsolutePath()),
                    StandardCopyOption.REPLACE_EXISTING);
            paintImageToNegative(image);
            ImageIO.write(image, FileUtils.getFileExtension(newFile), newFile);

            System.out.println("Новое изображение: " + newFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Не удалось открыть файл " + file.getAbsolutePath());
        }
    }

    private void paintImageToNegative(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Color color = new Color(image.getRGB(x, y));
                image.setRGB(x, y, new Color(255- color.getRed(), 255- color.getGreen(), 255- color.getBlue()).getRGB());
            }
        }
    }
}
