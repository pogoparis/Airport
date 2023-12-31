package fr.eni.airport.controller;

import fr.eni.airport.bll.AvionManager;
import fr.eni.airport.bo.Avion;
import fr.eni.airport.bo.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AvionController {

    @Autowired
    private AvionManager avionManager;
    private Boolean donneesTest = false;

    @GetMapping("/liste")
    public String afficherListeAvions(Model model) {
        if (!donneesTest) {
            avionManager.creerDonneesDeTest();
            donneesTest = true;
        }

        List<Personne> toutesLesPersonnes = avionManager.getListePersonnesSansAvion();
        List<Avion> avions = avionManager.getListeAvions();

        model.addAttribute("toutesLesPersonnes", toutesLesPersonnes);
        model.addAttribute("avions", avions);
        return "liste";
    }

    @PostMapping("/embarquer/{personneId}")
    public String embarquerPassager(@PathVariable("personneId") Integer personneId,
                                    @RequestParam("avionId") Integer avionId) {
        avionManager.embarquerPassager(personneId, avionId);
        return "redirect:/liste";
    }

    @PostMapping("/debarquer/{avionId}")
    public String debarquerPassagers(@PathVariable("avionId") Integer avionId) {
        avionManager.debarquerPassagers(avionId);
        return "redirect:/liste";
    }

}
