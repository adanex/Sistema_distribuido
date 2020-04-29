package es.uabcs.commod;

import java.util.List;

import es.uabcs.bd.Usuarios;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidor extends Remote {
	
	public int autenticar(String nombre) throws RemoteException;
	
	public int agregar(String nombre,int sesion) throws RemoteException;
	
	public void enviar (String nombre, int sesionDe,int sesionA) throws RemoteException;
	
	public List<Mensaje> recibir(int sesion) throws RemoteException;
	
	public void limpiarBuffer(int sesion) throws RemoteException;
	
	public int suma(int uno,int dos) throws RemoteException;
	
	public Boolean login(String nombre,String contrasena)  throws RemoteException;
	
	public Boolean registro(String nombre,String contrasena)  throws RemoteException;
	
	
	
}

/*
 * cualquier objeto que se envie por java RMI tiene que ser SEREALIZAVLE, REMOTO o PREDEFINIDO
 * (string, int, etc).
 * 
 * Remote: se envia una referencia al objeto en otro computador, los cambios que hagan
 * se reflejan en la instancia original.
 * 	hacerlo cuando:
 * 		- el objeto es demaciado grande para ser transferido
 * 		- afectar al estado del objeto original
 * 	*el servidor es candidato para esto.
 * 
 * Serializable: se envia una copia exacta del objeto, los cambios que se hagan en la otra
 * compu no se reflejan en el objeto original.
 * 	hacerlo quando:
 * 		- el objeto es relativamente peque�o
 * 		- se van a invocar varias veces los metodos del objeto
 * 		- no interesa modificar elestado interno del mismo
 * 	los mensajes son un candidato para esto.
 * 
 * Arquitectura:
 * *Servidor:
 * 	*autenticar cliente: recibimos nombre devolvemos sesion
 * 	
 * 	*agregar contacto: recibimos sesion y nombre de contacto y 
 * 		devolvemos la sesion del contacto agregado
 * 	
 * 	*enviar mensaje a contacto: recibimos el mensaje, la secion de y la sesion haceia donde
 * 		hay que enviar el mensaje
 * 
 * 	*Recibir Mensaje: recibimos una sesion y devolvemos todos
 * 		los mensajes pendientes para esa sesion
 * 
 * C:\Program Files\Java\jre1.8.0_221\bin
 * 
 * start rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false
 * */
