package es.uabcs.servidor;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import es.uabcs.commod.IServidor;
import es.uabcs.commod.Utils;

public class MainServidor {
	public static void main(String[] args) throws Exception {
		Utils.setCodeBase(IServidor.class);
		
		Servidor servidor= new Servidor();
		IServidor remote= (IServidor)UnicastRemoteObject.exportObject(servidor,1100);
		
		Registry registry=LocateRegistry.getRegistry();
		registry.rebind("Mensajeria", remote);
		
		System.out.println(" server listo, presione enter para terminar");
		System.in.read();
		
		registry.unbind("Mensajeria");
		UnicastRemoteObject.unexportObject(servidor, true);
		
	}
}
