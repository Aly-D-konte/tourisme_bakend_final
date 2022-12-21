package com.bezkoder.springjwt.security.services;


import com.bezkoder.springjwt.models.Commentaire;

import java.util.List;

public interface CommentaireService {

    String addNewCommentaire(Commentaire commentaire);
    String SupprimerCommentaire(Long idCommentaire);
    String ModifierCommentaire(String username,Commentaire commentaire);
    List<Commentaire> toutLesCommentaires();
}
