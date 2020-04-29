package es.uabs.paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import es.uabcs.TextPrompt;

public class PanelRegistro extends JPanel{
	JLabel login=new JLabel("Registro");
	JLabel resultado=new JLabel();
	JTextField usuario,contrasena;
	JButton entrar, cancelar;
	
	PLogin pl=new PLogin("localhost");
	
	public PanelRegistro(int w,int h) throws RemoteException, NotBoundException {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		login.setBounds(w/2-90, 20, 150, 50);
		login.setForeground(new Color(255,255,255));
		login.setFont(new Font("Arial", 0, 40));
		this.add(login);
		setVisible(true);
		

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
		
		entrar=new JButton("Registrar");
		entrar.setBounds(w/2-120, 200, 100, 30);
		add(entrar);
		
		entrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String usu=usuario.getText();
				String pass=contrasena.getText();
				
				try {
					
					Boolean b=pl.registrar(usu, pass);
					System.out.println(b);
					if(b) {
						resultado.setText("Registro exitoso");
						try {
							Thread.sleep(3*1000);
							cancelar.setText("Regresar");
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						resultado.setText("Registro invalido");
					}
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
			}
		});
		
		
		cancelar=new JButton("Cancelar");
		cancelar.setBounds(w/2, 200, 100, 30);
		add(cancelar);
		
		this.setOpaque(false);
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public void setCancelar(JButton cancelar) {
		this.cancelar = cancelar;
		
	}
	
	
}
