package es.uabs.paneles;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import es.uabcs.commod.IServidor;

public class PLogin {
	
	private static IServidor servidor;
	private static Console console = System.console();
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	Registry registry;
	
	public PLogin(String ip) throws RemoteException, NotBoundException {
		// TODO Auto-generated constructor stub
		
		registry = LocateRegistry.getRegistry(ip);
		servidor = (IServidor)registry.lookup("Mensajeria");
		
	}
	
	
	public Boolean pruebasbd(String nom,String pass) throws RemoteException{
		
		
		Boolean u= servidor.login(nom, pass);
		
		return u;
	}
	
	public Boolean registrar(String nom,String pass) throws RemoteException{
		
		
		Boolean u= servidor.registro(nom, pass);
		
		return u;
	}
	
	

}
