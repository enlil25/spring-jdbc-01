package com.mycompany.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaDaoImpl implements PersonaDao{

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		//no es comun que se utilizen 2 plantillas , sin embargo
		// si es posible
		//la diferencia es el manejo de parametros por indice o por nombre
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	//query con parametros por nombre
	private static final String SQL_INSERT_PERSONA = "insert into persona(nombre,ape_paterno,ape_materno,email) values(:nombre,:ape_paterno,:ape_materno,:email)";
	
	//query con parametros por indice
	//private static final String SQL_INSERT_PERSONA = "insert into persona(username,password,fullname,email,update_by_email) values (?,?,?,?,?)";
	
	//con parametros por nombre
	private static final String SQL_UPDATE_PERSONA = "update persona set nombre=:nombre , ape_paterno=:ape_paterno , ape_materno = :ape_materno , email = :email where id_persona = :id_persona";
	
	private static final String SQL_DELETE_PERSONA = "delete from persona where id_persona=:id_persona";
	
	private static final String SQL_SELECT_PERSONA = "select id_persona,nombre,ape_paterno,ape_materno,email from persona";
	
	//Parametros por indice
	private static final String SQL_SELECT_PERSONA_BY_ID = SQL_SELECT_PERSONA + " where id_persona=?";
	
	@Override
	public void insertarPersona(Persona persona) {
		// TODO Auto-generated method stub
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(persona);
		namedParameterJdbcTemplate.update(SQL_INSERT_PERSONA, parameterSource);
	}

	@Override
	public void updatePersona(Persona persona) {
		// TODO Auto-generated method stub
		
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(persona);
		
		namedParameterJdbcTemplate.update(SQL_UPDATE_PERSONA, namedParameters);
	}

	@Override
	public void deletePersona(Persona persona) {
		// TODO Auto-generated method stub
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(persona);
		namedParameterJdbcTemplate.update(SQL_DELETE_PERSONA, namedParameters);
	}

	@Override
	public Persona findPersonaById(Long id) {
		// TODO Auto-generated method stub
		Persona persona = null;
		
		try{
			persona = jdbcTemplate.queryForObject(SQL_SELECT_PERSONA_BY_ID, new PersonaRowMapper(), id);
		}catch(EmptyResultDataAccessException e){
			persona = null;
		}
		
		
		return persona;
	}

	@Override
	public List<Persona> findAllPersonas() {
		
		//ParameterizedBeanPropertyRowMapper ya no esta en la api al parecer
		RowMapper<Persona> personaRowMapper = BeanPropertyRowMapper.newInstance(Persona.class);
		
		return this.jdbcTemplate.query(SQL_SELECT_PERSONA, personaRowMapper);
	}

	@Override
	public int contadorPersonasPorNombre(Persona persona) {
		// TODO Auto-generated method stub
		
		String sql = "select count(*) from persona where nombre = :nombre";
	                     	                    
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(persona);
		
		
		return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
	}

	@Override
	public int contadorPersonas() {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("select count(*) from persona", Integer.class); 
	}

	@Override
	public Persona getPersonaByEmail(Persona persona) {
		
		String sql = "select * from persona where email=:email";
		
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(persona);
		
		return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new PersonaRowMapper());
	}

}
