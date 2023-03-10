package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.models.Habitants;
import com.bezkoder.springjwt.security.services.HabitantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RestController
@RequestMapping(path = "/api/habitant",name ="Habitants")
public class HabitansControler {

    @Autowired
    private HabitantsService habitantsService;

    @Autowired
    public HabitansControler(HabitantsService habitantsService)
    {
        this.habitantsService=habitantsService;
    }


    @PostMapping(path = "/creer")
    public Habitants ajouterHabitant(@RequestBody Habitants habitants)
    {
        return this.habitantsService.ajouterHabitant(habitants);
    }
    @GetMapping(path ="/liste", name = "list")

    public List<Habitants> list()
    {
        return this.habitantsService.maListe();
    }


    @PutMapping(path ="/modifier/{id_habitans}", name = "modifier")
    public Habitants modifierHabitant(@RequestBody Habitants habitants, @PathVariable Long id_habitants)
    {
        return habitantsService.modifierHabitant(habitants, id_habitants);
    }


    @DeleteMapping(path ="/supprimer/{id_habitants}", name = "supprimer")
    public void supprimerHabitant(@PathVariable Long id_habitants)
    {
        this.habitantsService.supprimerHabitant(id_habitants);
    }




}
