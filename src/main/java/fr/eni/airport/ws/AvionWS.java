package fr.eni.airport.ws;

import fr.eni.airport.bll.AvionManager;
import fr.eni.airport.bo.Avion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/new")
    public ResponseEntity<String> creerAvion(@RequestBody Avion avion) {
        avionManager.creerAvion(avion);
        return new ResponseEntity<>("Avion intégré avec succès", HttpStatus.OK);
    }

}