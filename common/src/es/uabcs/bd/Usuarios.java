package es.uabcs.bd;

import java.rmi.*;
import java.io.*;

public class Usuarios implements Serializable{
	static int id;
	static String nombre;
	static String password;
	
	public Usuarios(int id,String nombre, String password){
		this.id=id;
		this.nombre=nombre;
		this.password=password;
	}
	
	public void imprimir() {
		System.out.println("id= "+id);
		System.out.println("nombre= "+nombre);
		System.out.println("password= "+password);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
