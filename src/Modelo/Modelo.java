/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class Modelo{
    public Statement stm;
    public Connection conn;   
    public ResultSet rs;
    int registros;
   
    
    public void setCon(Connection con){
        this.conn=con;
        }    
    public int getReg(){
              try{
         stm = conn.createStatement();
         rs=stm.executeQuery("SELECT count(*) as total FROM Poliza");
         rs.next();
         registros = rs.getInt("total");
         rs.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    return this.registros;
    }
        
    public String[][] getClients(){
           
           String[][] data = new String[this.registros][5];
         try{
         stm = conn.createStatement();
         rs=stm.executeQuery("SELECT * FROM Cliente");
         rs = stm.getResultSet();
       
          System.out.println("Consulta exitosa: ");
          
          int i=0;
         while(rs.next()){
                data[i][0] = rs.getString( "Id_Cliente" );
                data[i][1] = rs.getString( "Nombre" );
                data[i][2] = rs.getString( "Direccion" );
               
            i++;
         }
         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
       
    return data;
    }
    public String[][] getFact(){
        
    
           String[][] data = new String[this.registros][5];
         try{
         stm = conn.createStatement();
         rs=stm.executeQuery("SELECT * FROM Factura");
         rs = stm.getResultSet();
       
          System.out.println("Consulta exitosa: ");
          
          int i=0;
         while(rs.next()){
                data[i][0] = rs.getString( "Id_Factura" );
                data[i][1] = rs.getString( "Monto" );
                
            i++;
         }
         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
       
    return data;
    }
     public String[][] getCV(){
      String[][] data = new String[this.registros][5];
                
         try{
         stm = conn.createStatement();
         rs=stm.executeQuery("SELECT * FROM Vehiculo");
         rs = stm.getResultSet();
       
          System.out.println("Consulta exitosa: ");
          
          int i=0;
         while(rs.next()){
                data[i][0] = rs.getString( "Id_Placas" );
                data[i][1] = rs.getString( "Modelo" );
                data[i][2] = rs.getString( "Marca" );
                
                
                
            i++;
         }
         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
       
    return data;
    
}
public String[][] getDates(){
      String[][] data = new String[this.registros][5];
       
           
         try{
         stm = conn.createStatement();
         rs=stm.executeQuery("SELECT * FROM Poliza");
         rs = stm.getResultSet();
       
          System.out.println("Consulta exitosa: ");
          
          int i=0;
         while(rs.next()){
                data[i][0] = rs.getString( "Id_Poliza" );
                data[i][1] = rs.getString( "Fecha_Apertura" );
                data[i][2] = rs.getString( "Fecha_Vencimiento" );
               
                
                
                
            i++;
         }
         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
       
    return data;
    
}


}