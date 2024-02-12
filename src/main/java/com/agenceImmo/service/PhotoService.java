package com.agenceImmo.service;


import com.agenceImmo.model.Photo;
import com.agenceImmo.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {


    @Autowired
    private PhotoRepository photoRepository;


    public List<Photo> getAll(){
        return  photoRepository.findAll();
    }

    public Photo getById( Integer id){
        return photoRepository.findById(id).orElse(null);

    }

    public Photo save( Photo ph){
        return  photoRepository.save(ph);

    }

    public  void  delete(Integer id ){
        Photo ph = photoRepository.findById(id).orElse(null);
        photoRepository.delete(ph);
    }

}
