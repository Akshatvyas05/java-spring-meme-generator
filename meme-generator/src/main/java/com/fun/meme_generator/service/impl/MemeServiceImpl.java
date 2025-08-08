package com.fun.meme_generator.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class MemeServiceImpl implements MemeService{

    @Override
    public BufferedImage createMeme(String topText, String bottomText, BufferedImage image) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();

        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int fontSize = Math.max(width / 15, 20);
        Font font = new Font("Impact", Font.BOLD, fontSize);
        g.setFont(font);

        drawCenteredText(g, topText, width / 2, fontSize + 10, font);
        drawCenteredText(g, bottomText, width / 2, height - 10, font);

        g.dispose();
        return image;
    }

    private void drawCenteredText(Graphics2D g, String text, int centerX, int y, Font font) {
        FontMetrics fm = g.getFontMetrics(font);
        int textWidth = fm.stringWidth(text);

        // Outline
        g.setColor(Color.BLACK);
        for (int dx = -2; dx <= 2; dx++) {
            for (int dy = -2; dy <= 2; dy++) {
                if (dx != 0 || dy != 0) {
                    g.drawString(text, centerX - textWidth / 2 + dx, y + dy);
                }
            }
        }

        // Main text
        g.setColor(Color.WHITE);
        g.drawString(text, centerX - textWidth / 2, y);
    }
}

