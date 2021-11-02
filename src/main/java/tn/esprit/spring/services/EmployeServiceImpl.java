package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	public int ajouterEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		List<Employe> emp=(List<Employe>) employeRepository.findAll();
		if(employeRepository.findById(employeId).isPresent()){
		Employe employe = emp.get(employeId);
		employe.setEmail(email);
		employeRepository.save(employe);
		}
	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		List<Departement> dep=(List<Departement>) deptRepoistory.findAll();
		List<Employe> emp=(List<Employe>) employeRepository.findAll();
		if(deptRepoistory.findById(depId).isPresent()&&employeRepository.findById(employeId).isPresent())
		{
		Departement depManagedEntity = dep.get(depId);
		Employe employeManagedEntity = emp.get(employeId);
		
		if(depManagedEntity.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{

			depManagedEntity.getEmployes().add(employeManagedEntity);

		}
		}

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		List<Departement> deps=(List<Departement>) deptRepoistory.findAll();

		if(deptRepoistory.findById(depId).isPresent()){
		Departement dep = deps.get(depId);
		
		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;//a revoir
			}
		}
		}
	}

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		List<Employe> emp=(List<Employe>) employeRepository.findAll();
		List<Contrat> cont=(List<Contrat>) contratRepoistory.findAll();
		if(contratRepoistory.findById(contratId).isPresent()&&employeRepository.findById(employeId).isPresent())
		
			{Contrat contratManagedEntity = cont.get(contratId);
		Employe employeManagedEntity = emp.get(employeId);

		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);
			}
	}

	public String getEmployePrenomById(int employeId) {
		List<Employe>e= (List<Employe>) employeRepository.findAll();
		if(employeRepository.findById(employeId).isPresent()){
		Employe employeManagedEntity =e.get(employeId) ;
		return employeManagedEntity.getPrenom();}
		return null;
	}
	public void deleteEmployeById(int employeId)
	{
		List<Employe>e= (List<Employe>) employeRepository.findAll();
		if(employeRepository.findById(employeId).isPresent()){
			Employe employe =e.get(employeId) ;
	

		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		}

		employeRepository.delete(employe);
		}
	}

	public void deleteContratById(int contratId) {
		List<Contrat> cont=(List<Contrat>) contratRepoistory.findAll();
		if(contratRepoistory.findById(contratId).isPresent()){
		Contrat contratManagedEntity = cont.get(contratId);
		contratRepoistory.delete(contratManagedEntity);
		}
	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
         employeRepository.deleteAllContratJPQL();
	}
	
	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
				return (List<Employe>) employeRepository.findAll();
	}

}
