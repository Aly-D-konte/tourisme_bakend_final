package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {
   /* @Query(value =" SELECT * FROM `pays` WHERE pays.nom_pays=:nomPays",nativeQuery = true)
    List<String> VerifierPay(String nomPays);

    */

     Pays findByNompays(String nompays);


}
