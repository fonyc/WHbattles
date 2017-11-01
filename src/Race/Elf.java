/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Race;

public class Elf extends Race {

    public Elf() {
        super();
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
        //prueba
    }
    
    public Elf(String name){
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
    
    
     public static void main(String[] args) {
        
        
    }
}
