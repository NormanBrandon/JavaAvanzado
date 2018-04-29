/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class Conexion {
    public String url,username,password;
    public Conexion(String url,String username,String password){
     this.url=url;
     this.username=username;
     this.password=password;
    }

    public static void cargar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");       //Para agregar el Driver
            System.out.println("Driver cargado...");
        } catch (Exception e) {
            System.out.println("Nel"+e.getMessage());
        }
       
    }
    
    public Connection conectar() {
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(this.url,this.username,this.password);
            System.out.println("Conexión a base realizada");
        } catch (SQLException e) {
            System.out.println("Nel"+e.getMessage());
        }
        
        return conn;
    }
}