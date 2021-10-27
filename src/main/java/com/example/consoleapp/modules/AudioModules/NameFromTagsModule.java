package com.example.consoleapp.modules.AudioModules;

import com.example.consoleapp.utils.FileUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.audio.AudioParser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.stereotype.Component;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class NameFromTagsModule extends AbstractAudioModule{
    @Override
    public String getFunctionDescription() {
        return null;
    }

    @Override
    public void function(File file) {
        try (InputStream input = new FileInputStream(file)) {
            Parser parser;
            if (FileUtils.getFileExtension(file).equals("mp3"))
                parser = new Mp3Parser();
            else
                parser = new AudioParser();

            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            ParseContext parseCtx = new ParseContext();
            parser.getSupportedTypes(parseCtx);

            parser.parse(input, handler, metadata, parseCtx);

            printSongTitle(metadata);

        } catch (IOException | SAXException | TikaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printSongTitle(Metadata metadata) {
        String title = metadata.get("dc:title");
        if (title != null)
            System.out.println("Название: " + title);
        else
            System.out.println("Название на найдено в метаданных.");
    }
}
