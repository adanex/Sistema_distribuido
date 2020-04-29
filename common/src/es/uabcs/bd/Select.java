package es.uabcs.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import es.uabcs.commod.Conexion;

public class Select {
	Conexion conexion = new Conexion();
    Connection cn = null;
    Statement stm = null;
    ResultSet rs = null;
    Usuarios U;
	public Select() {
		
	}
	
	public Usuarios selectU(String nom, String passwor) {
		int id;
		String nombre;
		String password;
		
		try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            rs = stm.executeQuery("SELECT id,nombre,password FROM usuarios WHERE nombre= '"+nom+"' AND password='"+passwor+"'");
            
            while (rs.next()) {
                id = rs.getInt(1);
                nombre = rs.getString(2);
                password = rs.getString(3); 
                U=new Usuarios(id, nombre, password);
                
            }
                
                
                
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            try {
                if (rs!= null) {
                    rs.close();
                }
                
                if (stm != null) {
                    stm.close();
                }
                
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

		return U;
	}
}
