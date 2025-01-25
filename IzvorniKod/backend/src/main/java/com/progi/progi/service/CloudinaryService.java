package com.progi.progi.service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
//import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;
//    public CloudinaryService() {
//        Dotenv dotenv = Dotenv.load();
//        this.cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
//    }
    public CloudinaryService(
        @Value("${CLOUDINARY_URL}") String cloudUrl
//        @Value("${CLOUDINARY_API_KEY}") String apiKey,
//        @Value("${CLOUDINARY_API_SECRET}") String apiSecret
) {
        this.cloudinary = new Cloudinary(cloudUrl);
}

    public String uploadFile(MultipartFile file) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("url").toString(); // Get the URL of the uploaded image
    }
}
