package tn.esprit.spring.tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Test;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.services.EntrepriseServiceImpl;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepTest {
	@Autowired
	public EntrepriseServiceImpl entrepriseServiceImpl;
	
	@Autowired
	public IEmployeService Emps;
	
	private static final Logger l= LogManager.getLogger(DepTest.class);
	@SuppressWarnings("squid:S2699")
	@Test
	public void testAddDep(){
		Departement dep= new Departement("Informatique");
		entrepriseServiceImpl.ajouterDepartement(dep);
		Emps.getSalaireMoyenByDepartementId(dep.getId());
	}
	
	@After("execution(* tn.esprit.spring.service.*.*(..))")
	public void logMethodExit(JoinPoint joinPoint){
		String name= joinPoint.getSignature().getName();
		String msg="Out of method : " +name;
		l.info(msg);
	}
	
	@Around("execution(* tn.esprit.spring.service.*.*(..))")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
	long start = System.currentTimeMillis();
	Object obj = pjp.proceed();
	long elapsedTime = System.currentTimeMillis() - start;
	if (elapsedTime > 3000) {
		l.fatal("This process takes more than 3sec to execute");
	}
	
	String msg="Method execution time: " + elapsedTime + " milliseconds.";
	l.info(msg);
		return obj;
	}
	
}
