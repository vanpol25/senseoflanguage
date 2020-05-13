package com.senseoflanguage.service.impl;

import com.senseoflanguage.service.ImageService;
import gui.ava.html.image.generator.HtmlImageGenerator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Primary
public class HtmlImageGeneratorImpl implements ImageService {

    @Override
    public InputFile htmlToImage(String word) throws IOException {
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();

        imageGenerator.loadHtml(String.format(ImageService.HTML_TO_IMAGE, word));
        imageGenerator.saveAsImage("E:\\HtmlImageGeneratorImpl.png");

        BufferedImage bufferedImage = imageGenerator.getBufferedImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);

        InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
        return new InputFile().setMedia(inputStream, "1");
    }

}
