
package com.mycompany.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Conexion {
    
    
    Connection conectar = null;
    
    
    String usuario = "root";
    String contrasena = "MyNewPass";
    String bd = "usuarios";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasena);
            JOptionPane.showMessageDialog(null, "La conexion se ha realizado con exito");                         
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al conectar la base de datos, error: " + e.toString());
      
        }
         return conectar;
   }
    
}
