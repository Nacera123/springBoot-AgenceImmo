package com.agenceImmo.service;


import com.agenceImmo.model.Role;
import com.agenceImmo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role add(String nom){

        List<Role> roles = roleRepository.findByNomLikeIgnoreCase(nom);
        if (roles.size() >= 1){
            return roles.get(0);
        }
        Role newRole = new Role();
        newRole.setNom(nom);
        return roleRepository.save(newRole);
    }

    public Role getRoleFromName(String nom){
        //TODO: a modifier après :> ou cas ou la liste est vide .get(0) renvoyer une exception
        return this.roleRepository.findByNomLikeIgnoreCase(nom).get(0);
    }


    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    /**
     * Cette partie je l'ai ajouté
     * @param role
     * @return
     */
    public Role save(Role  role){
        return roleRepository.save(role);
    }

    public Optional<Role> getById(Integer id){
        return roleRepository.findById(id);
    }
}
