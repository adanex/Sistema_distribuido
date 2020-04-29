package es.uabs.paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import es.uabcs.TextPrompt;
import es.uabcs.commod.ImagenJChoser;

public class PanelMenu extends JPanel{
	
	JLabel titulo=new JLabel("MENU");
	JLabel foto=new JLabel("foto");
	JLabel nombre=new JLabel("");
	
	ImagenJChoser img;
	JFileChooser j;
	
	int w,h;
	
	

	
	public PanelMenu(int w,int h) throws RemoteException, NotBoundException{
		// TODO Auto-generated constructor stub
		this.w=w;
		this.h=h;
		setVisible(true);
		
		this.setLayout(null);
		titulo.setBounds(w/2-60, 20, 100, 50);
		titulo.setForeground(new Color(255,255,255));
		titulo.setFont(new Font("Arial", 0, 30));
		this.add(titulo);	
		
		nombre.setBounds(w-110, 120, 100, 40);
		nombre.setFont(new Font("Arial", 0, 20));
		
		add(nombre);
		
		foto.setBounds(w-110, 20, 100, 100);
		foto.setForeground(new Color(255,255,255));
		
		
		add(foto);
		
		foto.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				JOptionPane mostrar=new JOptionPane();
				//mostrar.showConfirmDialog(foto,this, "hola",1);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getButton()==3 && j!=null) {
					displayGUI();
				}else {
					open_file();
				}
				
				
			}
		});;
       
        
		
		
		this.setOpaque(false);
	}
	
	private void displayGUI() {
        JOptionPane.showConfirmDialog(null,
                        getPanel(),
                        "Foto Perfil: ",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
    }

    private JPanel getPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("");
        Image image =new ImageIcon(j.getSelectedFile().getAbsolutePath()).getImage();
        ImageIcon img2=new ImageIcon(image.getScaledInstance(w, h, Image.SCALE_SMOOTH));
        

        label.setIcon(img2);
        panel.add(label);

        return panel;
    }
	
	public void open_file() {
        j= new JFileChooser();
        //j.setCurrentDirectory(new File("ruta de archivo a recordar"));
        FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        j.setFileFilter(fil);
        j.setCurrentDirectory(new File("Fotos"));
        ImagenJChoser preview = new ImagenJChoser();
        j.setAccessory(preview);
        j.addPropertyChangeListener(preview);

        int el = j.showOpenDialog(this);
        if (el == JFileChooser.APPROVE_OPTION) {
            //txtRuta.setText(j.getSelectedFile().getAbsolutePath());
          //  Image img= new ImageIcon(j.getSelectedFile().getAbsolutePath());
            //ImageIcon img2=new ImageIcon(img.getScaledInstance(90, 80, Image.SCALE_SMOOTH)); 
        	Image img= new ImageIcon(j.getSelectedFile().getAbsolutePath()).getImage();
        	ImageIcon img2=new ImageIcon(img.getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_SMOOTH));
            foto.setIcon(img2);
        	//lblFoto.setIcon(img2);
        }
    }

	public JLabel getNombre() {
		return nombre;
	}

	public void setNombre(JLabel nombre) {
		this.nombre = nombre;
	}
	
	
	
}
