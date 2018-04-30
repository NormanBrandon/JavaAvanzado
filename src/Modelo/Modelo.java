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
         rs=stm.executeQuery("SELECT c.Nombre,p.Id_Placas, v.Modelo, p.Costo FROM Cliente as c, Poliza as p, Vehiculo as v WHERE c.Id_Cliente = p.Id_Cliente AND p.Id_Placas = v.Id_Placas;");
         rs = stm.getResultSet();
       
          System.out.println("Consulta exitosa: ");
          
          int i=0;
         while(rs.next()){
                data[i][0] = rs.getString( "Nombre" );
                data[i][1] = rs.getString( "Id_Placas" );
                data[i][2] = rs.getString( "Modelo" );
                data[i][3] = rs.getString( "Costo" );
                
                
                
                
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
    public String[][] busc1(String value){
      
          String[][] data = new String[this.registros][5];
       
           
         try{
         stm = conn.createStatement();
         rs=stm.executeQuery("SELECT Nombre,Direccion,Id_Placas FROM Cliente INNER JOIN Poliza on Poliza.Id_Cliente= Cliente.Id_Cliente WHERE Nombre='"+value+"'");
         rs = stm.getResultSet();
       
          System.out.println("Consulta exitosa: ");
          
          int i=0;
         while(rs.next()){
                data[i][0] = rs.getString( "Nombre" );
                data[i][1] = rs.getString( "Direccion" );
                data[i][2] = rs.getString( "Id_Placas" );
                    
                
            i++;
         }
         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
       
    return data;
    }
        public String[][] busc2(String value){
      
          String[][] data = new String[this.registros][5];
       
           
         try{
         stm = conn.createStatement();
         rs=stm.executeQuery("SELECT Nombre,Id_Placas,Costo,Prima_Asegurada FROM Cliente INNER JOIN Poliza on Poliza.Id_Cliente= Cliente.Id_Cliente WHERE Nombre='"+value+"'");
         rs = stm.getResultSet();
       
          System.out.println("Consulta exitosa: ");
          
          int i=0;
         while(rs.next()){
                data[i][0] = rs.getString( "Nombre" );
                data[i][1] = rs.getString( "Id_Placas" );
                data[i][2] = rs.getString( "Costo" );
                data[i][3] = rs.getString( "Prima_Asegurada");
                
                
                    
                
            i++;
         }
         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
       
    return data;
    }
        public void upDir(String name,String adress){
        //Update cliente Set Dirección = 'nueva_dirección'  Where Nombre='Mike';
           
         try{
         stm = conn.createStatement();
         stm.executeUpdate("UPDATE Cliente SET Direccion = '"+adress+"'  WHERE Nombre='"+name+"'");
         //rs = stm.getResultSet();
       
          System.out.println("Actualizacion exitosa: ");
         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
        
        
        }

}