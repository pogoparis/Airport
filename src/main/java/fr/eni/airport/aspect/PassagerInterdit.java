package fr.eni.airport.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class PassagerInterdit {

    private static final Logger LOGGER = LoggerFactory.getLogger(PassagerInterdit.class);

    @Before("execution(* fr.eni.airport.bll.AvionManagerImpl.embarquerPassager(..)) && args(nom)")
    public void logIfDupontPassagerEmbarque(String nom) {
        if (nom.equalsIgnoreCase("Dupont")) {
            LOGGER.warn("Alerte : Passager Dupont a embarqué !");
        }
    }
}
