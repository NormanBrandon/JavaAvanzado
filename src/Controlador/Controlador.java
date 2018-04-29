/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.Modelo;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Vista.Interfaz;
import java.awt.event.ActionListener;

/**
 *
 * @author vanessa-vaca1
 */
public class Controlador implements ActionListener{
     public Interfaz vista ;
     public  Modelo modelo = new Modelo();
    
    public Controlador(Interfaz vista){
       
        ///////////////////////////////////Conectamos con la base de datos en el contructor
        Connection conn;
        PreparedStatement ps;
       
        Conexion.cargar();
        conn = Conexion.conectar("jdbc:mysql://localhost/prueba","root","Conan@1234");
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
      
        //añade e inicia el jtable con un DefaultTableModel vacio
     //   this.vista.__tabla.addMouseListener(this);
     vista.texto.setText("hola2");
       // this.vista.__tabla.setModel( new DefaultTableModel() );
    }
    
    
    
    @Override
     public void actionPerformed(ActionEvent e) {
         
             vista.texto.setText(e.getActionCommand());
    switch (e.getActionCommand())
        {
            case "allclient":
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                 vista.texto.setText("hola3");
                JOptionPane.showMessageDialog(vista,"Error: .");
                break;
            case "allfact":
                //añade un nuevo registro
               
             
                    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                break;
            case "clientvehicle":
               JOptionPane.showMessageDialog(vista,"incorrectos.");
                break;       
        }

}
    
}
