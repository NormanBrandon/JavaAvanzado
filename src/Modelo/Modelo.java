/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class Modelo{
    public Statement stm;
    public Connection conn;   
    public ResultSet rs;
   
    
    public void setCon(Connection con){
        this.conn=con;
        }    
        
        
    public void getClients(){
        
         try{
         stm = conn.createStatement();
         rs=stm.executeQuery("SELECT * FROM producto");
         rs = stm.getResultSet();
          System.out.println("Consulta exitosa: ");
      
           Object[][] data = new String[10][10];
           int i=0;
         while(rs.next()){
              
                System.out.println(rs.getString( "p_nombre" ));
                System.out.println(rs.getString( "p_id" ));
                System.out.println(rs.getString( "p_precio" ));
                System.out.println(rs.getString( "p_cantidad" ));
                System.out.println("");
                
            
         }
         stm.close();
            rs.close();
            conn.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    
    }
    public void Actualizar(){
    
    }
    public void Borrar(){
    
    }
    
}