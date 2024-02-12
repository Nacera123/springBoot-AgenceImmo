package com.agenceImmo.controller;

import com.agenceImmo.model.FileUploadInfo;

import com.agenceImmo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@Controller
public class PhotoController {

    /** recuperer le path qu'on a crée dans application.properties**/
    @Value("${file.upload.path}")
    private String pathUploadFile;


    @Autowired
    private PhotoService photoService;



    @PostMapping("file/upload")
    public FileUploadInfo upload(@RequestParam MultipartFile file){
        File directory = new File(this.pathUploadFile);


        if (!directory.exists()){
            directory.mkdirs();
        }

        try {
            String fileName = file.getOriginalFilename();
            int positionPoint = fileName.lastIndexOf(".");
            String extensions = fileName.substring(positionPoint);
            List<String> extAutoriser = List.of(".png",".jpg",".webp",".svg",".jpeg");
            if (!extAutoriser.contains(extensions.toLowerCase())){ // toLowerCase() => miniscule
                return new FileUploadInfo(true, "Extensions non autoriser", null);
            }

            String uniqueId = UUID.randomUUID().toString();
            fileName = uniqueId + extensions;
            file.transferTo(new File(directory.getAbsolutePath(), fileName));

            return new FileUploadInfo(false, null, fileName);
        }catch (Exception ex){
            //ex.printStackTrace();
            return new FileUploadInfo(true, ex.getMessage(), null);
        }

    }

    /**
     * Cette méthode et appeler l'orsqu'on veux afficher une image
     * @param fileName nom de l'image
     * @return ResponseEntity qui va contenir l'image sous forme de byte
     */
    @GetMapping("/file/image/{fileName}")
    public ResponseEntity<?> displayImage(@PathVariable String fileName){
        // pathUploadFile => Chemin que l'image et sauvegarder
        File file = new File(pathUploadFile, fileName);
        if (!file.exists()){
            return null;
        }
        try{
            byte[] imageData = Files.readAllBytes(file.toPath());
            return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(imageData);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }

    }

}
