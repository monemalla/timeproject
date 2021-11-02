package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		List<Departement> deps=(List<Departement>) deptRepoistory.findAll();
		List<Entreprise> ent=(List<Entreprise>) entrepriseRepoistory.findAll();
if(entrepriseRepoistory.findById(entrepriseId).isPresent()&&deptRepoistory.findById(depId).isPresent())
{
				Entreprise entrepriseManagedEntity = ent.get(entrepriseId);
				Departement depManagedEntity = deps.get(depId);
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
}
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		List<Entreprise> ent=(List<Entreprise>) entrepriseRepoistory.findAll();
		List<String> depNames = new ArrayList<>();

		if(entrepriseRepoistory.findById(entrepriseId).isPresent()){
		Entreprise entrepriseManagedEntity = ent.get(entrepriseId);
		
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
		}
		return depNames;

	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		List<Entreprise> ent=(List<Entreprise>) entrepriseRepoistory.findAll();
		if(entrepriseRepoistory.findById(entrepriseId).isPresent()){
		entrepriseRepoistory.delete(ent.get(entrepriseId));	
		}
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		List<Departement> deps=(List<Departement>) deptRepoistory.findAll();
if(deptRepoistory.findById(depId).isPresent())
		deptRepoistory.delete(deps.get(depId));	
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		List<Entreprise> ent=(List<Entreprise>) entrepriseRepoistory.findAll();
if( entrepriseRepoistory.findById(entrepriseId).isPresent())
		return ent.get(entrepriseId);	
	return null;
	}

}
