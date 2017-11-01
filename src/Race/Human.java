/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Race;

/**
 *
 * @author fonyc
 */
public class Human extends Race{

    public Human() {
        super();
        stats = new int[9];
        this.stats[0] = 10; // M
        this.stats[1] = 5; // HA
        this.stats[2] = 5; // HP
        this.stats[3] = 4; // F
        this.stats[4] = 4; // R
        this.stats[5] = 2; // H
        this.stats[6] = 5; // I
        this.stats[7] = 3; // A 
        this.stats[8] = 8; // L
    }
    
     public Human(String name){
        stats = new int[9];
        this.stats[0] = 12;
        this.stats[1] = 6;
        this.stats[2] = 6;
        this.stats[3] = 4;
        this.stats[4] = 3;
        this.stats[5] = 2;
        this.stats[6] = 7;
        this.stats[7] = 3;
        this.stats[8] = 9;
        this.name = name;
    }
}
