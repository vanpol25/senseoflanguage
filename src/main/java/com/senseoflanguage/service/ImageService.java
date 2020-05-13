package com.senseoflanguage.service;

import com.senseoflanguage.exception.HtmlToImageException;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ImageService {

    Integer HEIGHT = 800;
    Integer WIDTH = 800;
    String HTML_TO_IMAGE = "" +
            "<!doctype html>\n" +
            "<html lang=\"en\" style=\"padding: 0; margin: 0\">\n" +
            "<head><title>SportBuddies</title></head>\n" +
            "<body style=\"background-color: #333333; height: 800px; width: 800px; padding: 0 80px 0 80px;\">\n" +
            "<div style=\"height: 300px\">\n" +
            "\n" +
            "</div>\n" +
            "<div style=\"height: 200px; width: 800px; text-align:center; align-content: center\">\n" +
            "    <div style=\"align-content: center\">\n" +
            "        <span style=\"font-size: 150px; font-family: Verdana,monospace; color: #C0CCB6\">%s</span>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div style=\"height: 300px\">\n" +
            "\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>\n";

    InputFile htmlToImage(String word) throws IOException;

}
