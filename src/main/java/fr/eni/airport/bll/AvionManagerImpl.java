package fr.eni.airport.bll;

import fr.eni.airport.bo.Avion;
import fr.eni.airport.bo.Personne;
import fr.eni.airport.dal.AvionDAO;
import fr.eni.airport.dal.PersonneDAO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvionManagerImpl implements AvionManager {

    @Autowired
    AvionDAO avionRepository;
    @Autowired
    PersonneDAO personneRepository;

    @Override
    public void creerAvion(Avion avion) {
        avionRepository.save(avion);

        //Liste des passagers
        List<Personne> passagers = avion.getLstPassagers();

        if (passagers != null) {
            for (Personne passager : passagers) {
                passager.setAvion(avion);
                personneRepository.save(passager);
            }
        }
    }

    @Override
    @Transactional
    public List<Personne> getListePersonnesSansAvion() {

        List<Personne> toutesLesPersonnes = getListePersonnes();

        // Les personnes sans avion
        List<Personne> personnesSansAvion = new ArrayList<>();
        for (Personne personne : toutesLesPersonnes) {
            if (personne.getAvion() == null) {
                personnesSansAvion.add(personne);
            }
        }
        return personnesSansAvion;
    }

    @Override
    public List<Avion> getAvionsState() {
        List<Avion> avions = avionRepository.findAll();
        for (Avion avion : avions) {
            avion.setLstPassagers(null);
        }
        return avions;
    }


    @Transactional
    @Override
    public void embarquerPassager(Integer personneId, Integer avionId) {
        Personne passager = personneRepository.findById(personneId).orElse(null);
        Avion avion = avionRepository.findById(avionId).orElse(null);

        if (passager != null && avion != null) {
            avion.getLstPassagers().add(passager);
            passager.setAvion(avion);
            avionRepository.save(avion);
            personneRepository.save(passager);
        }
    }

    @Transactional
    @Override
    public void debarquerPassagers(Integer idAvion) {
        Avion avion = avionRepository.findById(idAvion).orElse(null);

        if (avion != null) {
            for (Personne passager : avion.getLstPassagers()) {
                passager.setAvion(null);
            }
            avion.getLstPassagers().clear();
            avionRepository.save(avion);
        }
    }

    @Override
    @Transactional
    public List<Avion> getListeAvions() {
        return avionRepository.findAll();
    }

    @Override
    @Transactional
    public List<Personne> getListePersonnes() {
        return (List<Personne>) personneRepository.findAll();
    }

    @Override
    public Avion getAvionById(Integer idAvion) {
        return avionRepository.findById(idAvion).orElse(null);
    }

    @Override
    public void supprimerAvion(Integer avionId) {
        Avion avion = avionRepository.findById(avionId).orElse(null);
        if (avion != null) {
            List<Personne> passagers = avion.getLstPassagers();
            for (Personne passager : passagers) {
                passager.setAvion(null);
                personneRepository.save(passager);
            }
            avionRepository.deleteById(avionId);
        } else {
            throw new EntityNotFoundException("L'avion avec l'ID " + avionId + " n'existe pas.");
        }

    }


/*    @Transactional
    @Override
    public void creerDonneesDeTest() {
        Avion avion1 = new Avion("ABC123", "Boeing", 747);
        Avion avion2 = new Avion("XYZ789", "Airbus", 380);
        Avion avion3 = new Avion("FDS27K", "Jet", 150);
        Avion avion4 = new Avion("MLO555", "Beechcraft", 3500);
        avionRepository.save(avion1);
        avionRepository.save(avion2);
        avionRepository.save(avion3);
        avionRepository.save(avion4);

        Personne personne1 = new Personne("Doe", "John", 30);
        Personne personne2 = new Personne("Smith", "Alice", 25);
        Personne personne3 = new Personne("Garcia", "Maria", 28);
        Personne personne4 = new Personne("Chen", "Michael", 32);
        Personne personne5 = new Personne("Kumar", "Priya", 27);

        personneRepository.save(personne1);
        personneRepository.save(personne2);
        personneRepository.save(personne3);
        personneRepository.save(personne4);
        personneRepository.save(personne5);
    }*/
}
