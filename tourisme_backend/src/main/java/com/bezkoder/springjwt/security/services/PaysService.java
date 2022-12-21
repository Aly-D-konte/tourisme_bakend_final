package com.bezkoder.springjwt.security.services;


import com.bezkoder.springjwt.models.Pays;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;



// ----------------------------Controllers-----------------------------------

    /*
    public PaysService(PaysRepository paysRepository)
    {
        this.paysRepository = paysRepository;
    }

     */
    // ----------------------------Ajouter un pays-----------------------------------OK

    public MessageResponse ajouterPays(Pays pays) {
        if (paysRepository.findByNompays(pays.getNompays()) == null) {
            this.paysRepository.save(pays);
            MessageResponse contenu = new MessageResponse("Pays ajouté avec succes");
            return contenu;
        } else {
            MessageResponse contenu = new MessageResponse("Pays existe déja");
            return contenu;
        }

    }

// ----------------------------Aficher une liste de pays-----------------------------------OK
    public List<Pays> afficheTout()
    {
        return this.paysRepository.findAll();
    }

// ----------------------------Aficher un pays-----------------------------------

    public MessageResponse afficherUn(Long id_pays)
    {
        Optional<Pays> pays = this.paysRepository.findById(id_pays);
        if (!pays.isPresent()) {
            MessageResponse message = new MessageResponse("Ce pays n'est pas trouvé !");
            return message;
          }
        else {
             this.paysRepository.findById(id_pays);
             Optional<Pays> a = this.paysRepository.findById(id_pays);

            MessageResponse message = new MessageResponse( a.get().getNompays());
            return message;
        }


    }
    // ----------------------------Modifier un pays-----------------------------------OK
    public MessageResponse modifierPays(Pays pays, Long id_pays)
    {
        //ICI ON VERIFIE SI LE PAYS EXISTE
        Optional<Pays> paysExistePays = this.paysRepository.findById(id_pays);
        if (!paysExistePays.isPresent()) {
            MessageResponse message = new MessageResponse("Pays non trouvé !");
            return message;
        }
        else {
            Pays paysT = paysRepository.findById(id_pays).get();
            paysT.setNompays(pays.getNompays());
            paysRepository.saveAndFlush(paysT);
            MessageResponse message = new MessageResponse("Pays modifié avec success");
            return message;
        }

    }

    // ----------------------------Supprimer un pays-----------------------------------

    public MessageResponse supprimerPays(Long id_pays)
    {
        Optional<Pays> pays = this.paysRepository.findById(id_pays);
        if (!pays.isPresent())
        {
            MessageResponse message = new MessageResponse("Ce pays n'est pas trouvé !");
            return message;
        }
        else{
            this.paysRepository.delete(pays.get());
            MessageResponse message = new MessageResponse("Suppression reussie avec succès !");
            return message;
        }


    }

}
