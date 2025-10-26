package ma.ws.jaxrs.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ma.ws.jaxrs.entities.Compte;
import ma.ws.jaxrs.repositories.CompteRepository;

import java.util.List;

@Component
@Path("/banque")
public class CompteRestJaxRSAPI {

	@Autowired
	private CompteRepository compteRepository;

	// ✅ GET all comptes
	@GET
	@Path("/comptes")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Compte> getComptes() {
		return compteRepository.findAll();
	}

	// ✅ GET by ID
	@GET
	@Path("/comptes/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Compte getCompte(@PathParam("id") Long id) {
		return compteRepository.findById(id).orElseThrow(() -> new WebApplicationException("Compte not found", 404));
	}

	// ✅ POST new compte
	@POST
	@Path("/comptes")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Compte saveCompte(Compte compte) {
		return compteRepository.save(compte);
	}

	// ✅ PUT update existing compte
	@PUT
	@Path("/comptes/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Compte updateCompte(@PathParam("id") Long id, Compte compte) {
		Compte existing = compteRepository.findById(id)
				.orElseThrow(() -> new WebApplicationException("Compte not found", 404));

		existing.setSolde(compte.getSolde());
		existing.setType(compte.getType());
		existing.setDateCreation(compte.getDateCreation());
		return compteRepository.save(existing);
	}

	// ✅ DELETE
	@DELETE
	@Path("/comptes/{id}")
	public void deleteCompte(@PathParam("id") Long id) {
		compteRepository.deleteById(id);
	}
}
