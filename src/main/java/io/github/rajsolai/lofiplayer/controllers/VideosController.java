package io.github.rajsolai.lofiplayer.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;

@RestController
public class VideosController {
    private final MongoDatabase database;

    VideosController(){
            MongoClient mongoClient = MongoClients.create(
                    "mongodb+srv://lofiplayer_client:ksftVzo2k7YYlY1r@cluster0.udyz3.mongodb.net/lofiplayer?retryWrites=true&w=majority");
            database = mongoClient.getDatabase("lofiplayer");
    }

    @RequestMapping(path="/api",produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public String getVideos(){
        ArrayList<String> vids = new ArrayList<>();
        database.getCollection("videos")
                .find()
                .forEach(document -> vids.add(document.toJson()));
      return vids.toString();
    }

}
