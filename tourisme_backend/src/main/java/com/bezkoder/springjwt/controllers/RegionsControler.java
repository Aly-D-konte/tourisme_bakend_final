package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.img.ConfigImage;
import com.bezkoder.springjwt.models.Regions;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.PaysRepository;
import com.bezkoder.springjwt.repository.RegionsRepository;
import com.bezkoder.springjwt.security.services.RegionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping(path = "/api/region", name = "Regions")
public class RegionsControler {

    @Autowired
    RegionsService regionsService;
    //Controller
            /*
            @Autowired
            public RegionsControler(RegionsService regionsService)
            {
                this.regionsService=regionsService;
            }
             */
//Classe d'ajout des regions

    @PostMapping(path = "/creer", name = "create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse add(@RequestBody Regions regions) {
        return this.regionsService.ajouterRegions(regions);
    }

   // ICI ON TENTE DE CREER UNE REGION AVEC IMAGES
   private String nomregions;
    @Column(unique = true)
    private String coderegion;
    private String activiterregion;
    private String superficieregion;
    private String languemregion;
    private String images;
    private String description;
    private int nombrecommentaire;
    @Autowired
    private RegionsRepository regionsRepository;

    @Autowired
    private PaysRepository paysRepository;

    @PostMapping("/ajouterRegion")
   // @PreAuthorize("hasRole('ADMIN')")

    public MessageResponse ajouterRegion(@Param("nomregions") String nomregions, @Param("coderegion") String coderegion, @Param("activiterregion") String activiterregion, @Param("superficieregion") String superficieregion, @Param("languemregion") String languemregion, @Param("description") String description, @Param("file") MultipartFile file) throws IOException {

        System.out.println("aaaaaaaaaaaaa"+ activiterregion + " " + "description: " + description +  " " + "     superfice" + superficieregion);

        Regions regions = new Regions();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());

        System.out.println(nomregions);
        regions.setNomregions(nomregions);

        System.out.println(coderegion);
       regions.setCoderegion(coderegion);

        System.out.println(description);
        regions.setDescription(description);

        System.out.println(activiterregion);
       regions.setActiviterregion(activiterregion);

        System.out.println(nomfile);
        regions.setImages(nomfile);

        System.out.println(nomregions);
        regions.setNomregions(nomregions);

        System.out.println(languemregion);
        regions.setLanguemregion(languemregion);

        System.out.println(superficieregion);
        regions.setSuperficieregion(superficieregion);

        System.out.println(nombrecommentaire);
        regions.setNombrecommentaire(0);

        regions.setPays(paysRepository.findById(1L).get());
        System.out.println(regions.getId_regions());
        System.out.println(regions.getNomregions());

        if(regionsRepository.findByNomregions(nomregions) == null){


            String uploaDir = "C:\\Users\\adkonte\\Desktop\\InterfaceMaliTourist\\Front-APIREGION\\src\\assets\\images";
            //String uploaDir = new ClassPathResource("files/").getFile().getAbsolutePath();
            ConfigImage.saveimg(uploaDir, nomfile, file);
            //  entiteServiceImplement.ajouter(entite);
            return regionsService.ajouterRegions(regions);
        }else {
            MessageResponse message = new MessageResponse("Regions existe déja");
            return message;
        }

    }


















//Classe afficher toute les regions
    @GetMapping(path = "/liste", name = "list")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")

    @ResponseStatus(HttpStatus.OK) //Permet de monter l'etat de notre requete
    public List<Regions> list() {
        return this.regionsService.afficherRegions();
    }
//Classe afficher une regions


    @GetMapping(path = "/detail/{id_regions}", name = "lire")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")

    @ResponseStatus(HttpStatus.OK)
    public Optional<Regions> lire(@PathVariable Long id_regions) {
        return this.regionsService.afficherUneRegion(id_regions);
    }
//Classe permettant de modifier les regions

    @PutMapping(path = "/modifier/{id_regions}", name = "modifier")
    @PreAuthorize("hasRole('ADMIN') ")

    @ResponseStatus(HttpStatus.OK)
    public MessageResponse modifier(@RequestBody Regions regions, @PathVariable Long id_regions) {
        return this.regionsService.modifierRegions(regions, id_regions);
    }

    //Classe permettant de supprimer une region
    @DeleteMapping(path = "/supprimer/{id_regions}", name = "supprimer")
    @PreAuthorize("hasRole('ADMIN') ")

    //  @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id_regions) {
        this.regionsService.supprimer(id_regions);
    }

    // La liste des regions sans pays

    @GetMapping(path = "/regionssansPays")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")

    public Iterable<Object[]> mesRegions() {
        return this.regionsService.mesRegions();
    }


    // La liste des regions avec pays

    @GetMapping(path = "/regionsavecPays")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")

    public Iterable<Object[]> mesRegionsAvecPays() {
        return this.regionsService.mesRegionsAvecPays();
    }
/*
            // Generale
            @ApiOperation(value = "Crée une Region en generale")
            @PostMapping(path ="/ajouter", name = "create")
            public Regions Generale(Regions regions) {
                return this.regionsService.Generale(regions);
            }

 */


    //nombre de regions

}

