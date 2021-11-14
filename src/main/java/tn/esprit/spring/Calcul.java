package tn.esprit.spring;

import java.util.logging.Logger;
//import org.apache.log4j.Logger;

public class Calcul {
	private static final Logger logger = Logger.getLogger("Calcul.class");
	public int calculerSom (int a, int b) {
	int res = 0;
	try {
	logger.info("In calculerSom(" + a + ", " + b + ")");
	res = a+ b;
	logger.info("Out calculerSom() : " + res);
	} catch (Exception e) {logger.info("Erreur : " + e);}
	return res;
	} 
	
	public int calculerDiff (int a, int b)
	{
	int res = 0;
	try {
	logger.info("In calculerDiff(" + a + ", " + b + ")");
	res = a - b;
	logger.info("Out calculerDiff() : " + res);
	}
	catch (Exception e) { logger.info("Erreur : " + e);
	}
	return res;
	} 
}
