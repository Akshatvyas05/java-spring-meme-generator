package com.fun.meme_generator.service.impl;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface MemeService {
    public BufferedImage createMeme(String topText, String bottomText, BufferedImage image) throws IOException;
}
