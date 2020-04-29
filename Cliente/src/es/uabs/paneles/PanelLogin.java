package es.uabs.paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.*;

import es.uabcs.TextPrompt;
import es.uabcs.bd.Usuarios;
import es.uabcs.commod.IServidor;


public class PanelLogin extends JPanel {
	JLabel login=new JLabel("Login");
	JLabel resultado=new JLabel();
	JTextField usuario,contrasena;
	JButton entrar, registrarse;
	Boolean C;
	Usuarios ui;
	
	PLogin pl=new PLogin("localhost");
	

	public PanelLogin(int w,int h) throws RemoteException, NotBoundException {
		// TODO Auto-generated constructor stub
		setVisible(true);
		
		this.setLayout(null);
		login.setBounds(w/2-60, 20, 100, 50);
		login.setForeground(new Color(255,255,255));
		login.setFont(new Font("Arial", 0, 40));
		this.add(login);	
		
		C=false;
		
		resultado.setBounds(w/2-60, 250, 300, 50);
		resultado.setForeground(new Color(255,255,255));
		resultado.setFont(new Font("Arial", 0, 20));
		resultado.setVisible(true);
		this.add(resultado);
		
		usuario=new JTextField();
		TextPrompt Uplaceholder = new TextPrompt("Usuario", usuario);
		usuario.setBounds(w/2-110, 100, 200, 30);
		add(usuario);
		
		contrasena=new JTextField();
		TextPrompt Cplaceholder = new TextPrompt("Contraseņa", contrasena);
		contrasena.setBounds(w/2-110, 140, 200, 30);
		add(contrasena);
		
		entrar=new JButton("entrar");
		entrar.setBounds(w/2-120, 200, 100, 30);
		add(entrar);
		
		entrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String usu=usuario.getText();
				String pass=contrasena.getText();
				
				try {
					
					Boolean b=pl.pruebasbd(usu, pass);
					System.out.println(b);
					if(b) {
						resultado.setText("Usuario encontrado");
						ui=new Usuarios(0, usu, pass);
						
							C=true;
							
					}else {
						resultado.setText("Usuario no encontrado");
						
					}
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
			}
		});
		
		
		registrarse=new JButton("registrarse");
		registrarse.setBounds(w/2, 200, 100, 30);
		add(registrarse);
		
		this.setOpaque(false);
	}
	
	


	public JButton getEntrar() {
		return entrar;
	}


	public void setEntrar(JButton entrar) {
		this.entrar = entrar;
	}


	public JButton getRegistrarse() {
		return registrarse;
	}


	public void setRegistrarse(JButton registrarse) {
		this.registrarse = registrarse;
	}




	public Boolean getC() {
		return C;
	}

	public void setC(Boolean c) {
		C = c;
	}




	public Usuarios getUi() {
		return ui;
	}




	public void setUi(Usuarios ui) {
		this.ui = ui;
	}
	
	
	
	
	

}
