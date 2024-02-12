package com.agenceImmo.service;

import com.agenceImmo.model.Bien;
import com.agenceImmo.repository.BienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BienService {

    @Autowired
    private BienRepository bienRepository;


    /*Sauvegarder les données*/
    public Bien saveAndUpdate(Bien bien)throws Exception{

        if (bien.getNom() == null){
            throw new Exception("Veuillez remplir un nom valide ! ");
        }
        if (bien.getAdresse() == null){
            throw new Exception("Veuillez remplir une adresse valide ! ");
        }
        if (String.valueOf(bien.getCp()).length() != 5) {
            throw new Exception("Le code postal n'a que 5 chiffres.");
        }
        if (bien.getVille() == null){
            throw new Exception("Veuillez remplir une ville valide ! ");
        }
        if (bien.getDescription() == null || bien.getDescription().length() < 20 ){
            throw new Exception("Veuillez remplir un descriptif valide ! ");
        }
        if (bien.getMiniDescription() == null){
            throw new Exception("Veuillez remplir une mini description valide ! ");
        }
        if(bien.getMiniDescription().length() < 15 || bien.getMiniDescription().length() > 100){
            throw new Exception("la mini description doit contenir au minimum 15 caracteres et au max 100 caracteres");
        }

        return this.bienRepository.save(bien);
    }


    /*Recuperer la liste des donnees*/
    public List<Bien> getAll(){
        return this.bienRepository.findAll();
    }

    /* Recuperer le bien par id */
    public Bien getById(Integer id){
        return this.bienRepository.findById(id).orElse(new Bien());
    }

    /*Effacer les données*/
    public void deleteById(int id){
        this.bienRepository.deleteById(id);
    }

    /*Recuperer les données actives*/
    public  List<Bien> getActiveBien(){
        return bienRepository.findByActiveTrue();
    }

}
