/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Race;

import java.util.Scanner;

/**
 *
 * @author fonyc
 */
public class Race {
    protected int [] stats;
    protected String name;
    private String items;
    private int TSA;
    private int TSE; 
    private int bonoVsTSE;
    
    protected Race(){
        System.out.println("Choose a name for your hero");
        Scanner sc = new Scanner(System.in);
        String cadena = sc.nextLine();
        this.name = cadena;
        this.TSA = 4; //Tener un 4 de TSA es salvarse a 4+, un modificador de armadura, SUMA este numero, por lo que F4=+1 --> TSA pasa de 4+ a 5+
        this.TSE = 7; //Tener un 7 de TSE es como no tener nada, porque el programa entiende por 7, sacar un 7+ en los dados (cosa imposible)
        this.bonoVsTSE = 0;
        this.items = "Una polla asi de grande mira 8=====D";
        
    }

    public String getItems() {
        return items;
    }

    public int getTSA() {
        return TSA;
    }

    public int getTSE() {
        return TSE;
    }

    public int getBonoVsTSE() {
        return bonoVsTSE;
    }
    

    public int[] getStats() {
        return stats;
    }

    public String getName() {
        return name;
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }
    
    public void subtractStat(int i, int j){ //este metodo particular coge i= un elemento de los 9 del array y resta a ese numero una j
        this.stats[i] -= j;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getM(){
        return this.stats[0];
    }
    
    public int getHA(){
        return this.stats[1];
    }
    public int getHP(){
        return this.stats[2];
    }
    
    public int getF(){
        return this.stats[3];
    }
    
    public int getR(){
        return this.stats[4];
    }
    
    public int getH(){
        return this.stats[5];
    }
    
    public int getI(){
        return this.stats[6];
    }
    
    public int getA(){
        return this.stats[7];
    }
    public int getL(){
        return this.stats[8];
    }
}
