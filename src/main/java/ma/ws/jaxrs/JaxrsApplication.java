package ma.ws.jaxrs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ma.ws.jaxrs.entities.Compte;
import ma.ws.jaxrs.entities.TypeCompte;
import ma.ws.jaxrs.repositories.CompteRepository;

import java.util.Date; // ✅ use this import

@SpringBootApplication
public class JaxrsApplication {
	public static void main(String[] args) {
		SpringApplication.run(JaxrsApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompteRepository compteRepository) {
		return args -> {
			compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.COURANT));
			compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE));

			compteRepository.findAll().forEach(c -> System.out.println(c.toString()));
		};
	}
}
