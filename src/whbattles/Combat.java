package whbattles;

import Race.*;

public class Combat {

    private Race fighter1;
    private Race fighter2;

    public Combat(Race x, Race y) { //en el constructor pretendo ver las iniciativas, de tal modo que fighter1 sera el
        if (x.getStats()[6] > y.getStats()[6]) { //que tengas mas iniciativa, y será el que ataque primero. fighter 2 ataca segundo
            this.fighter1 = x;
            this.fighter2 = y;

        } else if (x.getStats()[6] < y.getStats()[6]) { //x tiene menor iniciativa por lo que es el fighter 2 
            this.fighter1 = y;
            this.fighter2 = x;

        } else { //el resto de casos es el empate, por lo que tiro 1d6, 4+gana x, 1-3 gana y
            int dice = roll();
            System.out.println("En el dado sale un: " + dice);
            if (dice > 3) {
                this.fighter1 = x;
                this.fighter2 = y;
            } else {
                this.fighter1 = y;
                this.fighter2 = x;
            }
        }
        System.out.println(this.fighter1.getName() + " tiene la iniciativa!!");
    }

    private int hitDifficulty() { //metodo que determina el numero de dificultad para impactar 
        int difficulty;
        if (this.fighter1.getStats()[1] > this.fighter2.getStats()[1]) { //si el atacante tiene mas HA
            difficulty = 3;
        } else if (2 * this.fighter1.getStats()[1] + 1 == this.fighter2.getStats()[1]) { //si el defensor dobla +1 la HA del agresor
            difficulty = 5;
        } else {
            difficulty = 4; //resto de casos, que son que el atacante tenga menos HA pero nunca menos del doble +1
        }
        return difficulty;
    }

    private int roll() { // tirada de 1d6 aleatoria
        int dice = (int) (Math.random() * 6) + 1;
        System.out.println("En el dado sale un: " + dice);
        return dice;
    }

    private boolean hit() {
        int i = hitDifficulty(); //guardo en i la dificultad de impactar
        int dice = roll(); //guardo en dice la tirada del dado       
        return dice >= i; //devuelvo true si la tirada es mayor o igual que la dificultad (impacto!!)
    }

    private int woundDifficulty() {
        if (this.fighter1.getStats()[3] - this.fighter2.getStats()[4] == 0) { // si F-R = 0 entonces la dificultad es a 4+
            return 4;
        } else if (this.fighter1.getStats()[3] - this.fighter2.getStats()[4] == 1) { // si F-R = 1 entonces dificultad es 3+
            return 3;
        } else if (this.fighter1.getStats()[3] - this.fighter2.getStats()[4] == 2) {
            return 2;
        } else if (this.fighter1.getStats()[3] - this.fighter2.getStats()[4] == -1) {
            return 5;
        } else if (this.fighter1.getStats()[3] - this.fighter2.getStats()[4] == -2) {
            return 6;
        } else if (this.fighter1.getStats()[3] - this.fighter2.getStats()[4] == -3) {
            return 6;
        } else if (this.fighter1.getStats()[3] - this.fighter2.getStats()[4] <= -4) { //casos que superan diferencia por -3 es imposible, devuelvo un 0
            return 0;
        } else { //resto de casos, que son los que superan la diferencia por 2+
            return 2;
        }
    }

    private boolean wound() {
        int i = woundDifficulty();
        if (i == 0) { //una dificultad de 0 al herir quiere decir que el enemigo tiene demasiada resistencia y no puedo herirle
            System.out.println("Es imposible herir al objetivo");
            return false;

        } else {
            int dice = roll();
            return dice >= i;
        }

    }

    private boolean checkDeath() { //comprobar heridas del fighter 1.si son <= 0 -->  TRUE = muerto
        if (this.fighter2.getStats()[5] <= 0) {
            System.out.println(this.fighter2.getName() + " ha muerto");
            System.out.println("EL COMBATE HA TERMINADO!!! El ganador es " + this.fighter1.getName());
        }
        return this.fighter2.getStats()[5] <= 0;
    }

    private boolean checkDeathAtacante() { //comprobar heridas fighter 2, si es true, esta muerto
        if (this.fighter1.getStats()[5] <= 0) {
            System.out.println(this.fighter1.getName() + " ha muerto");
        }
        return this.fighter1.getStats()[5] <= 0;
    }

    private void changeFighters() {
        Race auxFighter = this.fighter1;
        this.fighter1 = this.fighter2;
        this.fighter2 = auxFighter;
        auxFighter = this.fighter1;
        System.out.println("Ahora es el turno de atacar de " + this.fighter1.getName());
    }

    private int TSAModifier() {
        switch (this.fighter1.getStats()[3]) {
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 4;
            case 8:
                return 5;
            case 9:
                return 6;
            case 10:
                return 7;
            default:
                return 0;
        }
    }

    private boolean TSA() { //NOTA IMPORTANTE: de momento y de manera provisional contare con que todos tienen una TSA 4+
        int i = TSAModifier();
        int dice = roll();
        return dice >= this.fighter2.getTSA() + i;
    }

    private boolean TSE() {
        int dice = roll();
        return dice >= this.fighter2.getTSE() + this.fighter1.getBonoVsTSE(); // tanto TSE como TSA estan en race, y el bono contra TSE tambien por lo que no es raro encontrarlo aqui restando
    }

    private void subtractWound() { //se mete en las heridas del array de fighter2 y le quita 1
        this.fighter2.setStat(5, 1);
        System.out.println("Parece que " + this.fighter2.getName() + " tiene ahora HERIDAS= " + this.fighter2.getStats()[5] + " herida(s)");
    }

    public void fight() {
        boolean dead = false;
        while (!dead) {
            int a = this.fighter1.getStats()[7]; //guardo en la variable a los ataques del f1
            while (a > 0) {
                boolean h = hit();
                if (h & (a > 0)) {
                    System.out.println(this.fighter1.getName() + " ha impactado a " + this.fighter2.getName());
                    boolean w = wound();
                    if (w & (a > 0)) {
                        System.out.println(this.fighter1.getName() + " ha herido a " + this.fighter2.getName());
                        boolean ta = TSA();
                        if (!ta & (a > 0)) {
                            System.out.println(this.fighter2.getName() + " No ha conseguido salvar la herida!!");
                            boolean te = TSE();
                            if (!te & (a > 0)) {
                                System.out.println(this.fighter2.getName() + " Tuvo mala suerte!!");
                                subtractWound();
                            } else {
                                System.out.println(this.fighter2.getName() + " Ha salvado la herida misticamente!!");
                                a--;
                            }
                        } else {
                            System.out.println(this.fighter2.getName() + " Ha salvado la herida con su armadura!!");
                            a--;
                        }
                    } else {
                        System.out.println(this.fighter1.getName() + " no ha conseguido herir a " + this.fighter2.getName());
                        a--;
                    }
                } else {
                    System.out.println(this.fighter1.getName() + " ha fallado el ataque ");
                    a--;
                }
                a--;
            }
            dead = checkDeath();//compruebo muerte de fighter 2 
            if (!dead) { //si fighter 2 no ha muerto, le toca atacar
                changeFighters(); //cambio a los fighters de sitio para que ahora fighter 2 pueda atacar 
            }
        }
    }

    public static void main(String[] args) {
        Elf e1 = new Elf();

        Lizardmen liz = new Lizardmen();
        Combat c1 = new Combat(e1, liz);

        c1.fight();
    }

}
