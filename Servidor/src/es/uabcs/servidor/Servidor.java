package es.uabcs.servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.lang.*;

import es.uabcs.bd.Registro;
import es.uabcs.bd.Select;
import es.uabcs.bd.Usuarios;
import es.uabcs.commod.*;

public class Servidor implements IServidor{
	
	protected Servidor() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private Map<Integer, String> sesion_nombre= new HashMap<Integer, String>();
	private Map<String, Integer> nombre_sesion= new HashMap<String, Integer>();
	private Map<Integer, List<Integer>> contactos= new HashMap<Integer, List<Integer>>();
	private Map<Integer, List<Mensaje>> buffer= new HashMap<Integer, List<Mensaje>>();
	
	
	
	@Override
	public int autenticar(String nombre) {
		int sesionUsuario=getSesion();
		
		sesion_nombre.put(sesionUsuario, nombre);
		nombre_sesion.put(nombre,sesionUsuario);
		
		return sesionUsuario;
	}
	
	@Override
	public int agregar(String nombre, int sesion) {
		if(!sesion_nombre.containsKey(sesion)) {
			throw new RuntimeException("Sesion invalida");
		}
		
		if(!nombre_sesion.containsKey(nombre)) {
			throw new RuntimeException(nombre+" no esta conectado");
		}
		
		List<Integer> misContactos= contactos.get(sesion);
		if(misContactos==null) {
			misContactos= new LinkedList<Integer>();
			contactos.put(sesion, misContactos);
		}
		
		misContactos.add(nombre_sesion.get(nombre));
		 return nombre_sesion.get(nombre);
	}
	
	@Override
	public void enviar (String mensaje, int sesionDe, int sesionA) {
		if(!sesion_nombre.containsKey(sesionDe)) {
			throw new RuntimeException("Sesion invalida");
		}
		
		if(!sesion_nombre.containsKey(sesionA)) {
			throw new RuntimeException("Contacto no conectado");
		}
		
		if(!contactos.get(sesionDe).contains(sesionA)) {
			throw new RuntimeException(sesion_nombre.get(sesionA)+ "No es parte de sus contactos");
		}
		
		List<Mensaje> mensajes= buffer.get(sesionA);
		
		if(mensajes == null) {
			mensajes = new LinkedList<Mensaje>();
			buffer.put(sesionA,mensajes);
		}
		
		mensajes.add(new Mensaje(mensaje,sesion_nombre.get(sesionDe)));
		
	}
	
	@Override
	public List<Mensaje> recibir(int sesion){
		
		if(sesion_nombre.containsKey(sesion)) {
			throw new RuntimeException("Sesion invalida");
		}
		return buffer.get(sesion);
	}
	
	@Override
	public void limpiarBuffer(int sesion) throws RemoteException {
		// TODO Auto-generated method stub
		if(sesion_nombre.containsKey(sesion)) {
			throw new RuntimeException("Sesion invalida");
		}
		
		buffer.get(sesion).clear();
		
	}
	
	@Override
	  public int suma(int uno, int dos) throws RemoteException {
	    // TODO Auto-generated method stub
	    return uno + dos;
	  }
	
	@Override
	public Boolean login(String nombre, String contrasena) throws RemoteException {
		// TODO Auto-generated method stub
		
		Select s= new Select();
		Usuarios u=s.selectU(nombre, contrasena);
		
		if(u==null) {
			System.out.println("no");
			return false;
		}else {
			System.out.println("si");
			return true;
		}
		
		
	}
	
	@Override
	public Boolean registro(String nombre, String contrasena) throws RemoteException {
		// TODO Auto-generated method stub
		
		Registro s= new Registro();
		Boolean u=s.registrarU(nombre, contrasena);
		
		return u;
		
	}
	
	private static int sesion = new Random().nextInt();
	
	public static int getSesion() {
		return ++sesion;
	}

	

	

	
			
}
