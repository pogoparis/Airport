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
    private RestTemplate restTemplate;
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
    public ResponseEntity<String> deplacerAvionVersAutreAeroport(@PathVariable Integer idAvion) {
        Avion avion = avionManager.getAvionById(idAvion);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Avion> request = new HttpEntity<>(avion, headers);
        ResponseEntity<Avion> response = restTemplate.postForEntity("http://localhost:8081/avions/recevoir", request, Avion.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok("Avion déplacé vers l'autre aéroport");
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Erreur lors du déplacement de l'avion");
        }
    }

}