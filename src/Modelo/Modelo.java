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
   public void iniciar(){
    try{
         stm = conn.createStatement();
         rs=stm.executeQuery("SELECT Monto,Id_Factura FROM Factura");
         rs = stm.getResultSet();
       
          System.out.println("Consulta exitosa: ");
          String[] datos = new String[this.registros];
          String[] datos2 = new String[this.registros];          
          String[] datos3 = new String[this.registros];          
          double[] prima = new double[this.registros];
          double[] poliza = new double[this.registros];
          
          int i=0;
         while(rs.next()){
             datos[i]=rs.getString("Monto");
             datos2[i]=rs.getString("Id_Factura");
             
             poliza[i]=(Integer.parseInt(datos[i]))*0.0055583333;
             prima[i]=(Integer.parseInt(datos[i]))*0.85;
             i++;
         }
         System.out.println("pl");
         for (int j = 0; j < i; j++) {
             rs=stm.executeQuery("SELECT Id_Placas FROM Vehiculo WHERE Id_Factura='"+datos2[j]+"'");
             rs = stm.getResultSet();
             rs.next();
             datos3[j]=rs.getString("Id_Placas");
             
             
        }
         
         
            for (int j = 0; j < i; j++) {
         stm.executeUpdate("UPDATE Poliza SET Costo = '"+poliza[j]+"'  WHERE Id_Placas='"+datos3[j]+"'");
         stm.executeUpdate("UPDATE Poliza SET Prima_Asegurada = '"+prima[j]+"' WHERE Id_Placas='"+datos3[j]+"'");
        }
         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    
    
   }
    
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
                data[i][1] = "$"+rs.getString( "Monto" );
                
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
         rs=stm.executeQuery("SELECT c.Nombre,p.Id_Placas, v.Modelo, q.Monto FROM Cliente as c, Poliza as p, Vehiculo as v,Factura as q WHERE c.Id_Cliente = p.Id_Cliente AND p.Id_Placas = v.Id_Placas AND q.Id_Factura=v.Id_Factura;");
         rs = stm.getResultSet();
       
          System.out.println("Consulta exitosa: ");
          
          int i=0;
         while(rs.next()){
                data[i][0] = rs.getString( "Nombre" );
                data[i][1] = rs.getString( "Id_Placas" );
                data[i][2] = rs.getString( "Modelo" );
                data[i][3] ="$"+ rs.getString( "Monto" );
                
                
                
                
            i++;
         }
         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
       
    return data;
    
}
      public String[][] getMayor(){
      String[][] data = new String[1][5];
                
         try{
         stm = conn.createStatement();
         rs=stm.executeQuery("SELECT c.Nombre,p.Id_Placas, v.Modelo, q.Monto FROM Cliente as c, Poliza as p, Vehiculo as v,Factura as q WHERE c.Id_Cliente = p.Id_Cliente AND p.Id_Placas = v.Id_Placas AND q.Id_Factura=v.Id_Factura;");
         rs = stm.getResultSet();
       
          System.out.println("Consulta exitosa: ");
          
          int i=0;
          int mayor=0;
         while(rs.next()){
             if(Integer.parseInt(rs.getString( "Monto" ))>mayor){
            mayor=Integer.parseInt(rs.getString( "Monto" ));
             data[0][0] = rs.getString( "Nombre" );
                data[0][1] = rs.getString( "Id_Placas" );
                data[0][2] = rs.getString( "Modelo" );
                data[0][3] ="$"+ rs.getString( "Monto" );   
             }
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
                data[i][2] = "$"+rs.getString( "Costo" );
                data[i][3] ="$"+rs.getString( "Prima_Asegurada");
                
                
                    
                
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
   public void upFact(String monto,String id){
        //Update cliente Set Dirección = 'nueva_dirección'  Where Nombre='Mike';
           double prima,poliza;
           
         try{
         stm = conn.createStatement();
          rs=stm.executeQuery("SELECT Id_Factura FROM Vehiculo WHERE Id_Placas='"+id+"'");
          
         rs = stm.getResultSet();
         rs.next();
         String idf=rs.getString("Id_Factura");
             System.out.println(rs.getString("Id_Factura"));
         stm.executeUpdate("UPDATE Factura SET Monto = '"+monto+"'  WHERE Id_Factura='"+idf+"'");
        
         poliza = ((Integer.parseInt(monto))*0.005558333);
         prima=(Integer.parseInt(monto))*0.85;
         stm.executeUpdate("UPDATE Poliza SET Costo = '"+poliza+"'  WHERE Id_Placas='"+id+"'");
         stm.executeUpdate("UPDATE Poliza SET Prima_Asegurada = '"+prima+"' WHERE Id_Placas='"+id+"'");
        
             System.out.println(prima);
             System.out.println(poliza);
          System.out.println("Actualizacion exitosa: ");
         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
        
        
        }
      public void upVehicle(String marca,String model,String placa){
        //Update cliente Set Dirección = 'nueva_dirección'  Where Nombre='Mike';
           
         try{
         stm = conn.createStatement();
         if(marca!=""){
         stm.executeUpdate("UPDATE Vehiculo SET Marca = '"+marca+"'  WHERE Id_Placas='"+placa+"'");}
         if(model!=""){
         stm.executeUpdate("UPDATE Vehiculo SET Modelo = '"+model+"'  WHERE Id_Placas='"+placa+"'");}

       
          System.out.println("Actualizacion exitosa: ");
         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
        
        
        }
}