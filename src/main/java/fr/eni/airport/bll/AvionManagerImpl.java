package fr.eni.airport.bll;

import fr.eni.airport.bo.Avion;
import fr.eni.airport.bo.Personne;
import fr.eni.airport.dal.AvionDAO;
import fr.eni.airport.dal.PersonneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvionManagerImpl implements AvionManager{

    @Autowired
    AvionDAO avionRepository;
    @Autowired
    PersonneDAO personneRepository;

    @Override
    public void embarquerPassager(Integer personneId, Integer avionId) {
        Personne passager = personneRepository.findById(personneId).orElse(null);
        Avion avion = avionRepository.findById(avionId).orElse(null);

        if (passager != null && avion != null) {
            avion.getPassagers().add(passager);
            passager.setAvion(avion);
            avionRepository.save(avion);
            personneRepository.save(passager);
        }
    }

    public void debarquerPassagers(Integer idAvion) {
        Avion avion = avionRepository.findById(idAvion).orElse(null);

        if (avion != null) {
            for (Personne passager : avion.getPassagers()) {
                passager.setAvion(null);
            }
            avion.getPassagers().clear();
            avionRepository.save(avion);
        }
    }

    @Override
    public List<Avion> getListeAvions() {
        return (List<Avion>) avionRepository.findAll();
    }
    @Override
    public List<Personne> getListePersonnes() {
        return (List<Personne>) personneRepository.findAll();
    }


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




    }


}
