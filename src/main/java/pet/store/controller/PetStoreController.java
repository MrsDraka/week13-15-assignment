package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
//@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {

	@Autowired
	private PetStoreService petStoreService;
	
	@PostMapping("/pet_store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Creating pet store {}", petStoreData);
		
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PutMapping("/pet_store/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		
		petStoreData.setPetStoreId(petStoreId);
		log.info("Updating pet store {}", petStoreData);
				
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PostMapping("/pet_store/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee addEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee petStoreEmployee) {
		
				log.info("Creating pet store employee {}", petStoreEmployee);
				
				PetStoreEmployee employee = petStoreService.saveEmployee(petStoreId, petStoreEmployee);
		
		return employee;
	}
	
	@PostMapping("/pet_store/{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer addCustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer petStoreCustomer) {
		
				log.info("Creating pet store customer {}", petStoreCustomer);
				
				PetStoreCustomer customer = petStoreService.saveCustomer(petStoreId, petStoreCustomer);
		
		return customer;
	}
	
	@GetMapping("/pet_store")
	public List<PetStoreData> getAllPetStores() {
		
		log.info("Retrieving all pet stores");
	    List<PetStoreData> petStores = petStoreService.retrieveAllPetStores();
	    return petStores;
	}
	
	@GetMapping("/pet_store/{petStoreId}")
	public PetStoreData getPetStoreById(@PathVariable Long petStoreId) {
		
		log.info("Retrieving pet store with ID= {}", petStoreId);
		
	    PetStoreData petStore = petStoreService.retrievePetStoreById(petStoreId);
	    return petStore;
	}
	
	@DeleteMapping("/pet_store/{petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
		log.info("Deleting pet store with ID={}", petStoreId);
		
	    petStoreService.deletePetStoreById(petStoreId);
	    return Map.of("message", "Deletion of pet store with ID=" + petStoreId + " was successful.");
	}
	
}
