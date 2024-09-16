package com.ianpert;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import jakarta.websocket.server.PathParam;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
public class PhotosController {

    @Autowired
    private PhotoRepository photoRepository;

    private OkHttpClient client = new OkHttpClient();
    private final int MAX_ITERATIONS = 50;

    @GetMapping("/photos/{type}")
    @ResponseBody 
    public String getAndStorePhotos(@PathVariable("type") String type, @RequestParam(name="n", defaultValue="1") Integer iterations) throws IOException {
        PhotoTypeEnum photoType = PhotoTypeEnum.lookup(type);
        if(photoType == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }

        //set upper bound for iterations
        if(iterations > MAX_ITERATIONS) {
            iterations = MAX_ITERATIONS;
        }

        Request request = null;
        for(int i = 0; i < iterations; i++ ) {
            request = new Request.Builder().url(photoType.photoEndpoint()).build();
            System.out.println(i + " | Requesting: " + request.url());
            Response response = client.newCall(request).execute();
            if(response.code() >= 400) {
                return HttpStatus.INTERNAL_SERVER_ERROR.toString();
            }
            Photo photo = new Photo(response.body().byteStream().readAllBytes());
            photoRepository.save(photo);
        }
        return HttpStatus.OK.toString();
    }
}