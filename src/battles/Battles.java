package battles;

import java.util.Scanner;

public class Battles {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        //Hero
        int heroHP = 9; // hero.getHealth
        int heroInit = 5; // hero.getInitiative
        int heroAgility = 4; // hero.getAgility
        int choice;
        int hAttack;
        int flee = 0;

        //Monster
        int monsterHP = 1; // monster.getHealth
        int monsterInit = 7; // monster.getInitiative
        int mAttack;

        System.out.println("There is a monster in the room!");

        int whoToStart = checkInitiative(heroInit, monsterInit);

        if (whoToStart == 1) {
            while (flee != 1 && heroHP >= 1 && monsterHP >= 1) {
                System.out.println("\nYOUR HP: " + heroHP + "\nMONSTER'S HP: " + monsterHP);
                choice = fightOrFlight();
                if (choice == 1) {
                    hAttack = heroAttack();
                    if (hAttack == 1) {
                        monsterHP--; // monster.setHealth??
                    }
                    mAttack = monsterAttack();
                    if (mAttack == 1) {
                        heroHP--; // hero.setHealth??
                    }
                } else if (choice == 2) {
                    flee = flee(heroAgility);
                    if (flee != 1) {
                        mAttack = monsterAttack();
                        if (mAttack == 1) {
                            heroHP--;
                        }
                    }
                }
            }
        } else {
            while (flee != 1 && heroHP >= 1 && monsterHP >= 1) {
                mAttack = monsterAttack();
                if (mAttack == 1) {
                    heroHP--;
                }
                System.out.println("\nYOUR HP: " + heroHP + "\nMONSTER'S HP: " + monsterHP);
                choice = fightOrFlight(); 
                if (choice == 1) {
                    hAttack = heroAttack();
                    if (hAttack == 1) {
                        monsterHP--;
                    }
                } else if (choice == 2) {
                    flee = flee(heroAgility);
                }
            }
        }
        
        if (heroHP <= 0) {
            System.out.println("\nYou died!");
        } else if (monsterHP <= 0) {
            System.out.println("\nYou defeated the beast!");
        }

    }

    public static int checkInitiative(int hero, int monster) {

        int heroInitSum = rollDice(hero);
        int monsterInitSum = rollDice(monster);

        System.out.println("\nYour initiative is: " + heroInitSum);
        System.out.println("The monster's initiative is: " + monsterInitSum);

        if (heroInitSum > monsterInitSum) {
            System.out.println("You start");
            return 1;
        } else {
            System.out.println("The monster starts attacking you");
            return 0;
        }

    }

    public static int fightOrFlight() {
        System.out.println("\nChoose: \n1. Fight \n2. Flee");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            return 1;
        } else if (choice == 2) {
            return 2;
        } else {
            return 0;
        }
    }

    public static int flee(int flee) {
        System.out.println("\nYou try to escape...");

        int chance = flee * 10;

        int result = (int) (Math.random() * 100) + 1; // Drar random tal mellan 1 - 100

        if (result <= chance) {
            System.out.println("and you manage to get away!");
            return 1;
        } else {
            System.out.println("but to no avail, you have to stay and fight one more round!");
            return 0;
        }
    }

    public static int heroAttack() {

        int attackSum = attack(6); // 6 Är riddarens Attack. Här ska vald hjältes attack användas
        System.out.println("\nYou attack with a level " + attackSum + " attack");

        int agilitySum = dodge(3); // Spindelns Smidighet. Här ska man egentligen hämta just det slumpade monstrets Smidighet
        System.out.println("and the monster try to get away with a level " + agilitySum + " dodge!");

        if (attackSum > agilitySum) {
            System.out.println("So your attack hit the monster which loses 1 HP!");
            return 1;
        } else {
            System.out.println("So you miss the monster...");
            return 0;
        }
    }

    public static int monsterAttack() {

        int attackSum = attack(2); // Spindelns Attack. Här ska man egentligen hämta just det slumpade Monstrets Attack
        System.out.println("\nThe monster attacks you with a level " + attackSum + " attack");

        int agilitySum = dodge(4); // Riddarens Smidighet. Här ska man egentligen hämta just den valda Hjältens Smidighet
        System.out.println("and you try to get away with a level " + agilitySum + " dodge!");

        if (attackSum > agilitySum) {
            System.out.println("So the monster's attack hit you an you lose 1 HP...");
            return 1;
        } else {
            System.out.println("So the monster swings and misses you!");
            return 0;
        }
    }

    public static int attack(int attack) {
        int attackSum = rollDice(attack);
        return attackSum;
    }

    public static int dodge(int inititative) {
        int initiativeSum = rollDice(inititative);
        return initiativeSum;
    }

    public static int rollDice(int numberOfDice) {
        int sum = 0;

        for (int i = 0; i < numberOfDice; i++) {

            int resultOfDie = (int) (Math.random() * 6) + 1; // Slår en tärning med maxvärde 6
            sum += resultOfDie; // Lägger ihop alla tärningsresultat
        }
        return sum;
    }

}
