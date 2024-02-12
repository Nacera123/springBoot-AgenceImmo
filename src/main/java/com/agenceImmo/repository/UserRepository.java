package com.agenceImmo.repository;


import com.agenceImmo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    User findUsersByEmailIgnoreCase(String email);

}
