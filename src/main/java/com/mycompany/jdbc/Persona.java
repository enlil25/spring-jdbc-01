package com.mycompany.jdbc;

public class Persona {

	private long idPersona;
	private String nombre;
	private String ape_paterno;
	private String ape_materno;
	private String email;
	
	public Persona() {}
	
	public Persona(long idPersona){
		this.idPersona = idPersona;
	}

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApe_paterno() {
		return ape_paterno;
	}

	public void setApe_paterno(String ape_paterno) {
		this.ape_paterno = ape_paterno;
	}

	public String getApe_materno() {
		return ape_materno;
	}

	public void setApe_materno(String ape_materno) {
		this.ape_materno = ape_materno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nombre=" + nombre + ", ape_paterno=" + ape_paterno
				+ ", ape_materno=" + ape_materno + ", email=" + email + "]";
	}
	
		
	
	
	
	
}
