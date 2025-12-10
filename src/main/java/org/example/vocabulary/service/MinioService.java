package org.example.vocabulary.service;

import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient client;

    @Value("${minio.bucket}")
    private String bucket;

    public String upload(MultipartFile file, String folderName){
        try{

            String fileName = folderName + "/" + UUID.randomUUID() + "_" + System.currentTimeMillis()+"_" + file.getOriginalFilename() ;

            boolean exists = client.bucketExists(
                    BucketExistsArgs.builder().bucket(bucket).build()
            );

            if (!exists) {
                client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }

            client.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            return bucket + "/" + fileName;
        }  catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(String fileName) {

        try {
            String replace = fileName.replace(bucket + "/", "");

            client.removeObject(
                    RemoveObjectArgs.builder().bucket(bucket).object(replace).build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}