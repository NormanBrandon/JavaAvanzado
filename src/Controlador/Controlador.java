
package Controlador;

import Modelo.Conexion;
import Modelo.Modelo;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Vista.Interfaz;
import java.awt.event.ActionListener;


public class Controlador implements ActionListener{
     public Interfaz vista ;
     public  Modelo modelo = new Modelo();
     public DefaultTableModel tabla = new DefaultTableModel();
     
     public Connection conn;
     public Conexion servidor;
     
     public Controlador(Interfaz vista){
       
        ///////////////////////////////////Conectamos con la base de datos en el contructor
        
        PreparedStatement ps;
        servidor = new Conexion("jdbc:mysql://localhost/seguro","root","Conan@1234");
       
        servidor.cargar();
        conn = servidor.conectar();
        modelo.setCon(conn);
        ///////////////////////////////////
        
         this.vista = vista;
         this.vista.search1.addActionListener(this);
        this.vista.search1.setActionCommand("allclient");
        //
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.search2.addActionListener(this); 
        this.vista.search2.setActionCommand("allfact");
         this.vista.search3.addActionListener(this);
           this.vista.search3.setActionCommand("clientvehicle");
           this.vista.search4.addActionListener(this);
           this.vista.search4.setActionCommand("dates");
       this.vista.search5.addActionListener(this);
        this.vista.search5.setActionCommand("biggest");
        this.vista.search6.addActionListener(this);
        this.vista.search6.setActionCommand("search6");
        this.vista.search7.addActionListener(this);
        this.vista.search7.setActionCommand("search7");
        this.vista.Up1.addActionListener(this);
        this.vista.Up1.setActionCommand("Up1");
        this.vista.Up2.addActionListener(this);
        this.vista.Up2.setActionCommand("Up2");
        this.vista.Up3.addActionListener(this);
        this.vista.Up3.setActionCommand("Up3");
       this.vista.__tabla.setModel(tabla);
       
       modelo.setCon(servidor.conectar());
       modelo.getReg();
       modelo.iniciar();
       
    }
    
    
    
    @Override
     public void actionPerformed(ActionEvent e) {
         
             String[][] datos = new String[10][5];
             
    switch (e.getActionCommand())
        {
            case "allclient":
                 datos = new String[modelo.getReg()][5];
               modelo.setCon(servidor.conectar());
               datos = modelo.getClients();
             
              String[] columnNames = {"Numero De Cliente","Nombre","Direccion"};
              tabla.setDataVector(datos,columnNames);
                //    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                break;
            case "clientvehicle":
               datos = new String[modelo.getReg()][5];
               modelo.setCon(servidor.conectar());
               datos = modelo.getCV();
                String[] columnNames3 = {"Nombre","Placas","Modelo","Monto Factura"};
              tabla.setDataVector(datos,columnNames3);  
                break;
            case "allfact":
                 datos = new String[modelo.getReg()][5];
               modelo.setCon(servidor.conectar());
               datos = modelo.getFact();
                String[] columnNames2 = {"Numero de Factura","Monto"};
              tabla.setDataVector(datos,columnNames2);  
                break;
            case "dates":
                 datos = new String[modelo.getReg()][5];
               modelo.setCon(servidor.conectar());
               datos = modelo.getDates();
                String[] columnNames4 = {"Numero de Poliza","Fecha de apertura","Fecha de Vencimiento"};
              tabla.setDataVector(datos,columnNames4);  
                break;
            case "search6":
               datos = new String[modelo.getReg()][5];
               modelo.setCon(servidor.conectar());
               datos = modelo.busc1(vista.texto.getText());
                String[] columnNames5 = {"Nombre","Direccion","Placas"};
              tabla.setDataVector(datos,columnNames5);  
                break;
             case "search7":
               datos = new String[modelo.getReg()][5];
               modelo.setCon(servidor.conectar());
               
               datos = modelo.busc2(vista.texto.getText());
                String[] columnNames6 = {"Nombre","Placas","Costo Poliza","Prima Asegurada"};
              tabla.setDataVector(datos,columnNames6);  
                break;
             case "Up1":
              modelo.upDir(vista.UpNombre.getText(),vista.UpDir.getText());
                 break;
            case "Up2":
              modelo.upFact(vista.UpCosto.getText(),vista.UpFact.getText());
                 break;
            case "Up3":
              modelo.upVehicle(vista.UpMarca.getText(),vista.UpModel.getText(),vista.UpPlaca.getText());
                 break;
            case "biggest":
                datos = new String[modelo.getReg()][5];
               modelo.setCon(servidor.conectar());
               datos = modelo.getMayor();
                String[] columnNames7 = {"Nombre","Placas","Modelo","Monto Factura"};
              tabla.setDataVector(datos,columnNames7); 
                break;
        }

}
    
}
