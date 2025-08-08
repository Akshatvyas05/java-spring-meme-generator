// File: com/fun/meme_generator/model/MemeResponse.java

package com.fun.meme_generator.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemeResponse {
    private String memeUrl;
    private String status;

    public MemeResponse(String memeUrl, String status) {
        this.memeUrl = memeUrl;
        this.status = status;
    }

}
