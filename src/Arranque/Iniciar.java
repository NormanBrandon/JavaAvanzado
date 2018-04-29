/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arranque;
import Vista.Interfaz;
import Controlador.Controlador;
/**
 *
 * @author vanessa-vaca1
 */
public class Iniciar {
    public static void main(String args[]){
       Interfaz vista=new Interfaz();
        Controlador con = new Controlador(vista) ;
        vista.setVisible(true);
        
    
    }
}
