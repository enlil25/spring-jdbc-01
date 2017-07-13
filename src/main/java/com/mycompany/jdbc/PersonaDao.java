package com.mycompany.jdbc;

import java.util.List;

public interface PersonaDao {
	void insertarPersona(Persona persona);

	void updatePersona(Persona persona);

	void deletePersona(Persona persona);

	Persona findPersonaById(Long id);

	List<Persona> findAllPersonas();

	int contadorPersonasPorNombre(Persona persona);
	
	int contadorPersonas();
	
	Persona getPersonaByEmail(Persona persona);
}
