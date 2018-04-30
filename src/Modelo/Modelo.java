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
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

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
 public void vehiculo(){
  try {

	File fXmlFile = new File("Vehiculos.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	
	doc.getDocumentElement().normalize();
        System.out.println("\n----------------------------");
	System.out.println("Leyento elementos de :" + doc.getDocumentElement().getNodeName());
			
	NodeList nList = doc.getElementsByTagName("vehiculo");
			
	System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		System.out.println("\n---------------");
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
                        stm = conn.createStatement();
                           stm.executeUpdate("INSERT INTO `Vehiculo`(`Id_Placas`, `Marca`, `Modelo`, `Id_Factura`) VALUES ("+eElement.getElementsByTagName("placas").item(0).getTextContent()+",'"+eElement.getElementsByTagName("marca").item(0).getTextContent()+"','"+eElement.getElementsByTagName("modelo").item(0).getTextContent()+"',"+eElement.getElementsByTagName("id_factura").item(0).getTextContent()+");");
                      
                        
			
			System.out.println("placas: " + eElement.getElementsByTagName("placas").item(0).getTextContent());
			System.out.println("marca : " + eElement.getElementsByTagName("marca").item(0).getTextContent());
                        System.out.println("modelo : " + eElement.getElementsByTagName("modelo").item(0).getTextContent());
                        System.out.println("id_factura: " + eElement.getElementsByTagName("id_factura").item(0).getTextContent());
		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
  
  }  
      
      public void factura(){
    
try {

	File fXmlFile = new File("Facturas.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	
	doc.getDocumentElement().normalize();
        System.out.println("\n----------------------------");
	System.out.println("Leyento elementos de :" + doc.getDocumentElement().getNodeName());
			
	NodeList nList = doc.getElementsByTagName("factura");
			
	System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		System.out.println("\n---------------");
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
                         stm.executeUpdate("INSERT INTO `Factura'(`Id_Factura`, `Monto`) VALUES ("+eElement.getAttribute("id")+","+eElement.getElementsByTagName("costo_total").item(0).getTextContent()+");");
                         
                      System.out.println("factura id : " + eElement.getAttribute("id"));
			System.out.println("costo_total: " + eElement.getElementsByTagName("costo_total").item(0).getTextContent());
		
		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }    
 
 }
        public void cliente(){
    try {

	File fXmlFile = new File("Clientes.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	
	doc.getDocumentElement().normalize();
        System.out.println("\n----------------------------");
	System.out.println("Leyento elementos de :" + doc.getDocumentElement().getNodeName());
			
	
        NodeList nList = doc.getElementsByTagName("cliente");		
	System.out.println("\n----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		System.out.println("\n---------------");
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
                         stm.executeUpdate("INSERT INTO `Cliente`(`Id_Cliente`, `Nombre`, `Direccion`) VALUES ("+eElement.getAttribute("id")+",'"+eElement.getElementsByTagName("nombre").item(0).getTextContent()+"','"+eElement.getElementsByTagName("direccion").item(0).getTextContent()+"');");
                       
			System.out.println("cliente id : " + eElement.getAttribute("id"));
			System.out.println("nombre: " + eElement.getElementsByTagName("nombre").item(0).getTextContent());
			System.out.println("direccion : " + eElement.getElementsByTagName("direccion").item(0).getTextContent());
		
		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
     
}
    public void Tables(){
        //Update cliente Set Dirección = 'nueva_dirección'  Where Nombre='Mike';
           
         try{
         stm = conn.createStatement();
         stm.execute("CREATE TABLE Cliente( Id_Cliente INT NOT NULL, Direccion VARCHAR(25)  NOT NULL,Nombre VARCHAR(25) NOT NULL, PRIMARY KEY (Id_Cliente))");
         stm.execute("CREATE TABLE Factura (Id_Factura INT NOT NULL,Monto DECIMAL NOT NULL,PRIMARY KEY (Id_Factura))");
         stm.execute("CREATE TABLE Vehiculo (Id_Placas VARCHAR(45) NOT NULL,Marca VARCHAR(45) NOT NULL,Modelo VARCHAR(45) NOT NULL,Id_Factura INT NOT NULL,PRIMARY KEY (Id_Placas),INDEX (Id_Factura),FOREIGN KEY (Id_Factura) REFERENCES Factura(Id_Factura))");
         stm.execute("CREATE TABLE Poliza(Id_Poliza INT NOT NULL,Costo DECIMAL(15,2) NOT NULL,Prima_Asegurada DECIMAL(15,2) NOT NULL,Fecha_Apertura DATE NOT NULL,Fecha_Vencimiento DATE NOT NULL,Id_Cliente INT NOT NULL,Id_Placas VARCHAR(45) NOT NULL,PRIMARY KEY (Id_Poliza),INDEX (Id_Cliente),INDEX (Id_Placas),FOREIGN KEY (Id_Cliente) REFERENCES Cliente(Id_Cliente),FOREIGN KEY (Id_Placas) REFERENCES Vehiculo (Id_Placas))");
         System.out.println("Creando Tablas ");
         
      }catch(SQLException e){
         System.err.println( "No se ha creado"+e.getMessage() );
      }
        
        
        }
  


}