/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import javax.swing.JFrame;

/**
 *
 * @author fonyc
 */
public class Interface extends JFrame{
    public static void main (String[] args) {
        Interface i = new Interface();
        i.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //podria haber puesto un 3, es lo mismo
        i.setSize(800, 600);
        i.setVisible(true);
    }
}
