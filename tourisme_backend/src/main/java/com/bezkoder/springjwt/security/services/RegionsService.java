        package com.bezkoder.springjwt.security.services;

        import com.bezkoder.springjwt.models.Regions;
        import com.bezkoder.springjwt.payload.response.MessageResponse;
        import com.bezkoder.springjwt.repository.PaysRepository;
        import com.bezkoder.springjwt.repository.RegionsRepository;
        import com.bezkoder.springjwt.repository.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Optional;
        @Service
        public class RegionsService {
            @Autowired
            RegionsRepository regionsRepository;

        @Autowired
        PaysRepository paysRepository;

            @Autowired
            private final UserRepository userRepository;

            //Controllers
        public RegionsService(RegionsRepository regionsRepository,
                              UserRepository userRepository)
        {
            this.regionsRepository = regionsRepository;
            this.userRepository = userRepository;
        }
//Ajouter des Regions
        public MessageResponse ajouterRegions(Regions regions)
        {
           if(regionsRepository.findByNomregions(regions.getNomregions() ) == null){
               regionsRepository.save(regions);
               MessageResponse message = new MessageResponse("Région ajoutée avec succès");
               return message;
            }
            else {
               MessageResponse message = new MessageResponse("Regions existe déja");
                return message;
            }






        /*
            try {

                 regionsRepository.save(regions);
                 ReponseMessage message = new ReponseMessage("Région ajoutée avec succès",true);
                 return message;
            }
            catch (Exception e)
            {
                ReponseMessage message = new ReponseMessage("Regions existe déja",false);
                return message;
            }

         */


        }
//Afficher la liste des Regions
        public List<Regions> afficherRegions()
            {
                return regionsRepository.findAll();
            }

// Afficher une seule region
            public Optional<Regions> afficherUneRegion(Long id_regions)
            {
                Optional<Regions> regions = this.regionsRepository.findById(id_regions);

                if (!regions.isPresent()) {
                    MessageResponse message = new MessageResponse("Cette région n'est pas trouvée !");
                    return null;
                }
                else {
                   // Regions RG =  this.regionsRepository.findById(id_regions).get();
                   // ReponseMessage message = new ReponseMessage(" Nom: "+RG.getNomregions()+" Pays: "+RG.getPays().getNompays()+" Activité "+RG.getActiviterregion()+" Code région: "+RG.getCoderegion()+" Langue: "+RG.getLanguemregion()+" Superficie: "+RG.getSuperficieregion(), true);
                    return regionsRepository.findById(id_regions);
                }

            }
 //Modifier un pays
            public MessageResponse modifierRegions(Regions regions, Long id_regions)
            {
                Optional<Regions> regionExistePays = this.regionsRepository.findById(id_regions);
                if (!regionExistePays.isPresent())
                {
                    MessageResponse message = new MessageResponse("Cette région n'est pas trouvée !");
                    return message;
                }
                else {
                    Regions regions1 = regionsRepository.findById(id_regions).get();
                    regions1.setActiviterregion(regions.getActiviterregion());
                    regions1.setLanguemregion(regions.getLanguemregion());
                    regions1.setSuperficieregion(regions.getSuperficieregion());
                    regions1.setCoderegion(regions.getCoderegion());
                    regions1.setNomregions(regions.getNomregions());
                    regions1.setPays(regions.getPays());
                     this.regionsRepository.save(regions1);
                    MessageResponse message = new MessageResponse("Région modifiée avec succès !");
                    return message;
                }

            }

//Supprimer une region
            public MessageResponse supprimer(Long id_region)
            {
                Optional<Regions> regions = this.regionsRepository.findById(id_region);
                if (!regions.isPresent())
                {
                    MessageResponse message = new MessageResponse("Région non trouvée !");
                    return message;
                }
                else {
                    this.regionsRepository.delete(regions.get());
                    MessageResponse message = new MessageResponse("Région supprimé avec succès !");
                    return message;
                }

            }


            public Iterable<Object[]> mesRegions() {
                return this.regionsRepository.mesRegions();
            }

            public Iterable<Object[]> mesRegionsAvecPays()
            {
                return this.regionsRepository.mesRegionsAvecPays();
            }
/*
            public Regions Generale(Regions regions)
            {

                          return regionsRepository.save(regions);
            }

 */

        }
