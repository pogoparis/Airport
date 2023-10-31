package fr.eni.airport.ws;

import fr.eni.airport.bll.AvionManager;
import fr.eni.airport.bo.Avion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/avions")
public class AvionWS {

    @Autowired
    private AvionManager avionManager;

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
    public ResponseEntity<String> envoyerAvionVersAutreServeur(@PathVariable Integer idAvion) {
        Avion avion = avionManager.getAvionById(idAvion);

        String urlAutreServeur = "http://localhost:8081/avions/recevoir";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(urlAutreServeur, avion, String.class);

        avionManager.supprimerAvion(idAvion);
        return ResponseEntity.ok("Avion envoyé vers l'autre serveur avec succès");
    }

}