package es.uabcs;

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
import java.util.concurrent.ScheduledExecutorService;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.SheetCollate;
import javax.swing.*;

import es.uabcs.commod.IServidor;
import es.uabs.paneles.PLogin;
import es.uabs.paneles.PanelLogin;
import es.uabs.paneles.PanelMenu;
import es.uabs.paneles.PanelRegistro;

import java.util.concurrent.*;

public class Login extends JFrame {
	
	PanelLogin pl;
	PanelRegistro pr;
	PanelMenu pm;
	JScrollPane sp;
	JPanel pp;
	
	ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
	
	
	
	public Login() throws RemoteException, NotBoundException {
		// TODO Auto-generated constructor stub
		super("Sistema Distribuido");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	     int height = pantalla.height;
	     int width = pantalla.width;
	     setSize(width/2, height/2);		
	     
	      setLocationRelativeTo(null);
	      setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pm=new PanelMenu(this.getWidth(),this.getHeight());
		
		pl= new PanelLogin(this.getWidth(),this.getHeight());
		
		pr= new PanelRegistro(this.getWidth(),this.getHeight());
		
		pl.getRegistrarse().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getContentPane().removeAll();
				add(pr);
				repaint();
				validate();
			}
		});
		
		pr.getCancelar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getContentPane().removeAll();
				add(pl);
				repaint();
				validate();
			}
		});
		
		
		
		add(pl);
		
		fondo();
		repaint();
		validate();
		
		
		iniciarMenu();
		
	}
	
	
	public void iniciarMenu() {
		Runnable tarea = new Runnable() {
			  public void run() {
				  
				  
			    if(pl.getC()==true) {
			    	timer.shutdown();
			    	getContentPane().removeAll();
			    	
			    	pm.getNombre().setText(pl.getUi().getNombre());
					add(pm);
					repaint();
					validate();
			    }
			  }
			};
			
		
		timer.scheduleAtFixedRate(tarea, 1, 1, TimeUnit.SECONDS);
		
	}
	
	public void fondo() {
		try {
			FondoSwing fondo = new FondoSwing(ImageIO.read(new File("src/img/fondo.jpg")));
			JPanel panel = (JPanel) this.getContentPane();
			panel.setBorder(fondo);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}



	
}
