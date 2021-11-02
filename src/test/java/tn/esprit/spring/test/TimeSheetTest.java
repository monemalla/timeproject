package tn.esprit.spring.test;
import static org.junit.Assert.*;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.EntrepriseServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TimeSheetTest.class)
public class TimeSheetTest {

	EntrepriseServiceImpl entreSer;
	private static final Logger l =
			LogManager.getLogger(TimeSheetTest.class);
	
	
	@Test(expected=NullPointerException.class)
	public void testeajouterEntreprise() {
		
		Entreprise entr=new Entreprise(1,"hkjb","p");

		assertTrue(entr.getRaisonSocial().equals("p"));
		assertNotNull(entr.getName());
		entreSer.ajouterEntreprise(entr);
	l.info("entreprise est ajouter");
	
	}
	@Test(expected=NullPointerException.class)
	public void testedeleteEntrepriseById()  {
		Entreprise entr=new Entreprise(1,"","p");
		assertNotNull(entr.getId());

		entreSer.deleteEntrepriseById(entr.getId());
		l.info("entreprise est supprimer");
	}
	@Test(expected=NullPointerException.class)
	public void testegetEntrepriseById() {
		Entreprise entr=new Entreprise(1,"","p");
		assertNotNull(entr.getId());
		entreSer.getEntrepriseById(entr.getId());
		l.info("entreprise est trouve");
	}
	@Test(expected=NullPointerException.class)
	public void testeaffecterDepartementAEntreprise()  {
		Entreprise entr=new Entreprise(1,"","p");
		Departement dep=new Departement(1,"dihc");
		assertNotNull(entr.getId());
		assertNotNull(dep.getId());
		entreSer.affecterDepartementAEntreprise(dep.getId(), entr.getId());
	}
	
	 	@org.aspectj.lang.annotation.Around("execution(* tn.esprit.spring.services.*.*(..))")
	public Object time(org.aspectj.lang.ProceedingJoinPoint pjp) {
		
	long start = System.currentTimeMillis();
	long elapsedTime = System.currentTimeMillis() - start;
	if (elapsedTime>3000){
	l.info( elapsedTime );
	return pjp.getSignature();
	}
	return null;
	}
	 
}
