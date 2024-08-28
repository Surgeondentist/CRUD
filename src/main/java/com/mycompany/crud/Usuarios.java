
package com.mycompany.crud;


import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class Usuarios {
    
    int idpersona;
    String cc;
    String NombrePersona;

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getNombrePersona() {
        return NombrePersona;
    }

    public void setNombrePersona(String NombrePersona) {
        this.NombrePersona = NombrePersona;
    }
    
    
    public void InsertarUsuario(JTextField paramNombres, JTextField paramCC){
    
        setNombrePersona(paramNombres.getText());
        setCc(paramCC.getText());
        
        Conexion objetoConexion = new Conexion();
       
        
        String consulta = "insert into persona (cc,NombrePersona) values (?,?);";
        
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1,getCc());
            cs.setString(2,getNombrePersona());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "se inserto correctamente el usuario");
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamente, Error: " + e.toString());
        }
                
        
    
    }
    
    
    public void MostrarAlumnos(JTable paramtbusuarios){
        
        Conexion objetoConexion = new Conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<>(modelo);
        paramtbusuarios.setRowSorter(OrdenarTabla);
        
        String sql = "";
        
        modelo.addColumn("Id");
        modelo.addColumn("CC");
        modelo.addColumn("Nombre");
        
        paramtbusuarios.setModel(modelo);
        
        sql = "select * from persona";
        
        String[] datos = new String[3];
        Statement st;
        
        try {
            
            st = objetoConexion.estableceConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                
                 datos[0]=rs.getString(1);
                 datos[1]=rs.getString(2);
                 datos[2]=rs.getString(3);
                 
                 modelo.addRow(datos);
            }
            
            paramtbusuarios.setModel(modelo);
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"No se pudo mostrar los registros: " + e.toString());
        }
        
    }
    
    
    public void SeleccionarUsuario(JTable paramTablaUsuarios, JTextField paramId, JTextField paramCC, JTextField paramNombres){
        
        try {
            
            int fila = paramTablaUsuarios.getSelectedRow();
            if (fila >= 0){
             paramId.setText(paramTablaUsuarios.getValueAt(fila, 0).toString());
             paramCC.setText(paramTablaUsuarios.getValueAt(fila, 1).toString());
             paramNombres.setText(paramTablaUsuarios.getValueAt(fila, 2).toString());
            }
            
            else{
            
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "error de selcciones: " + e.toString());
           
        }
    
    
    }
    
    
    
    public void ModificarUsuarios(JTextField paramIdpersona, JTextField paramCC, JTextField paramNombres){
    
    setIdpersona(Integer.parseInt(paramIdpersona.getText()));
        setCc(paramCC.getText());
        setNombrePersona(paramNombres.getText());
        
        
       Conexion objetoConexion = new Conexion();
       
       String consulta = "UPDATE persona SET persona.NombrePersona = ?, persona.cc = ? where persona.idpersona=?;";
       
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            
            cs.setString(1, getNombrePersona());
            cs.setString(2, getCc());
            cs.setInt(3, getIdpersona());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "No se modifico error: " + e.toString());
            
        }
             
        
    }

    public void EliminarUsuarios(JTextField paramId){
    
        setIdpersona(Integer.parseInt(paramId.getText()));
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "DELETE FROM persona WHERE persona.idpersona=?;";
    
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, getIdpersona());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.toString());
            
        }
    }
    
    
}
