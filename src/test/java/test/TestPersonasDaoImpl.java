package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.jdbc.Persona;
import com.mycompany.jdbc.PersonaDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:datasource-test.xml","classpath:ApplicationContext.xml"})
public class TestPersonasDaoImpl {

	private static Log logger = LogFactory.getLog("TestPersonasDaoImpl");
	
	@Autowired
	private PersonaDao personaDao;
	
	//@Ignore
	@Test
	public void deneriaMostrarPersonas() {
		logger.info("Inicio del test deberiaMostrarPersonas");
		List<Persona> personas = personaDao.findAllPersonas();
		int contadorPersonas = 0;
		for(Persona persona: personas){
			logger.info("Persona:"+persona.toString());
			contadorPersonas++;
		}
		
		assertEquals(contadorPersonas,personaDao.contadorPersonas() );
		logger.info("Fin del test deberiamostrarPersonas");
	}
	
	@Test
	public void testContarPersonasPorNombre(){
		logger.info("contar personas por nombre");
		String nombre="JOSE";
		Persona personaEjemplo = new Persona();
		personaEjemplo.setNombre(nombre);
		
		int encontradas = personaDao.contadorPersonasPorNombre(personaEjemplo);
		
		logger.info("Numero de personas encontradas por nombre:"+nombre+":"+encontradas);
		assertEquals(1, encontradas);
		logger.info("Fin del test contar personas por nombre");
		
	}
	
	@Test
	public void deberiaEncontrarPersonaPorId(){
			logger.info("inicio del test deberiaencontrarpersonaporId");
			long idpersona=1;
			Persona persona = personaDao.findPersonaById(idpersona);
			assertEquals("admin",persona.getNombre());
	}
	
	@Test
	public void deberiaInsertarPersona(){
		logger.info("Inicio del test deberiaInsertarPersona");
		Persona persona = new Persona(); 
		persona.setNombre("Carlos");
		persona.setApe_paterno("flores");
		persona.setApe_materno("rojas");
		persona.setEmail("jose12345112@gmail.com");
		personaDao.insertarPersona(persona);
		
		persona = personaDao.getPersonaByEmail(persona);
		logger.info("Persona insertada:"+persona);
		assertEquals(4,personaDao.contadorPersonas());
		
		logger.info("fin del test deberiaInsertarPersona");
	}

}
