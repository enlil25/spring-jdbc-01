package com.mycompany.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PersonaRowMapper implements RowMapper<Persona> {

	@Override
	public Persona mapRow(ResultSet rs, int numRow) throws SQLException {
		// TODO Auto-generated method stub
		Persona persona = new Persona();
		persona.setIdPersona(rs.getLong("id_persona"));
		persona.setNombre(rs.getString("nombre"));
		persona.setApe_paterno(rs.getString("ape_paterno"));
		persona.setApe_materno(rs.getString("ape_materno"));
		persona.setEmail(rs.getString("email"));
		return persona;
	}

}
