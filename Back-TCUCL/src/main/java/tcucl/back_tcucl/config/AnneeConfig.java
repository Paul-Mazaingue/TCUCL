package tcucl.back_tcucl.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class AnneeConfig {

    public static int getAnneeCourante() {

        Instant instant = Instant.now();
        // Convertir Instant en LocalDate dans le fuseau horaire par défaut
        LocalDate currentDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        // Récupérer l'année courante
        int currentYear = currentDate.getYear();

        // Vérifier si la date est avant ou après le 31 août
        if (currentDate.isBefore(LocalDate.of(currentYear, 8, 31))) {
            return currentYear; // Avant le 31 août, retourner l'année courante
        } else {
            return currentYear + 1; // À partir du 31 août, retourner l'année suivante
        }
    }

}
