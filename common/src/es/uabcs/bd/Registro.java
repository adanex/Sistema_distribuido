package es.uabcs.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import es.uabcs.commod.Conexion;

public class Registro {
	Conexion conexion = new Conexion();
    Connection cn = null;
    Statement stm = null;
    ResultSet rs = null;
    Boolean U;
	public Registro() {
		
	}
	
	public Boolean registrarU(String nom, String passwor) {
		
		try {
            cn = conexion.conectar();
            stm = cn.createStatement();
            System.out.println(nom+"========"+passwor);
            String query="INSERT INTO usuarios(nombre,password) VALUES ('"+nom+"', '"+passwor+"')";
            
            if(stm.executeUpdate(query)==1) {
            	U=true;
            }else {
            	U=false;
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
