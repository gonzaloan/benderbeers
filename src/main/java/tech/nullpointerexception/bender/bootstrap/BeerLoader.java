package tech.nullpointerexception.bender.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.nullpointerexception.bender.model.Beer;
import tech.nullpointerexception.bender.repository.BeerRepository;

/**
 * Componente Bootstrap para cargar un par de datos de prueba
 * al iniciar la aplicación.
 */
@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeers();
    }


    private void loadBeers() {

        if (beerRepository.count() == 0) {

            beerRepository.save(new Beer(1, "Cuello Negro Stout", "Cuello Negro", "Chile", 1900.0, "CLP"));

            beerRepository.save(new Beer(2, "Mauco Imperial IPA", "Cervecería Mauco", "Chile", 1500.0, "CLP"));


        }

    }


}
