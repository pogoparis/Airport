package fr.eni.airport.aspect;

import fr.eni.airport.bo.Personne;
import fr.eni.airport.dal.PersonneDAO;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class PassagerInterdit {

    @Autowired
    PersonneDAO personneRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PassagerInterdit.class);

    @Before("execution(* fr.eni.airport.bll.AvionManagerImpl.embarquerPassager(..)) && args(personneId, avionId)")
    public void logIfDupontPassagerEmbarque(Integer personneId, Integer avionId) {
        Personne passager = personneRepository.findById(personneId).orElse(null);
        if (passager != null && passager.getNom().equalsIgnoreCase("Dupont")) {
            LOGGER.warn("Alerte : Passager Dupont a embarqué !");
        }
    }
}
