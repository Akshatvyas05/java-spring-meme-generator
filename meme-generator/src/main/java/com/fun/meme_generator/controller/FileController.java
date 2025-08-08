package com.fun.meme_generator.controller;

import com.fun.meme_generator.service.impl.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fun.meme_generator.model.MemeResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


@RestController
public class FileController {

    @Autowired
    private MemeService memeService;

    @PostMapping(value = "/generate-meme", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> generateMeme(@RequestParam("image")MultipartFile image,
                                          @RequestParam("topText") String topText, @RequestParam("bottomText") String bottomText) throws IOException {
        InputStream stream = image.getInputStream();
        BufferedImage bufferedImage = ImageIO.read(stream);
        BufferedImage img = memeService.createMeme(topText, bottomText, bufferedImage);
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        ImageIO.write(img, "png", stream1);
        byte[] arr = stream1.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentDisposition(ContentDisposition.attachment().filename("meme.png").build());
        return new ResponseEntity<>(arr, headers, HttpStatus.OK);
    }
}
