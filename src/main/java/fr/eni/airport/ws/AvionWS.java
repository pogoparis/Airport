package fr.eni.airport.ws;

import com.fasterxml.jackson.annotation.JsonView;
import fr.eni.airport.bll.AvionManager;
import fr.eni.airport.bo.Avion;
import fr.eni.airport.bo.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/avions")
public class AvionWS {

    @Autowired
    private AvionManager avionManager;

    @JsonView(Views.SansPassagers.class)
    @GetMapping("/state")
    public List<Avion> getAvionsStateWithoutPassagers() {
        return avionManager.getListeAvions();
    }


    @GetMapping("/liste")
    public List<Avion> listeDesAvions() {
        return avionManager.getListeAvions();
    }

    @PostMapping("/recevoir")
    public ResponseEntity<String> integrerAvion(@RequestBody Avion avion) {
        avionManager.creerAvion(avion);
        return new ResponseEntity<>("Avion intégré avec succès", HttpStatus.OK);
    }

    @PostMapping("/{idAvion}/deplacer")
    public ModelAndView envoyerAvionVersAutreServeur(@PathVariable Integer idAvion, RedirectAttributes redirectAttributes) {
        Avion avion = avionManager.getAvionById(idAvion);

        String urlAutreServeur = "http://localhost:8081/avions/recevoir";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(urlAutreServeur, avion, String.class);

        avionManager.supprimerAvion(idAvion);
        redirectAttributes.addFlashAttribute("successMessage", "Avion envoyé vers l'autre serveur avec succès");

        return new ModelAndView("redirect:/liste");
    }

}