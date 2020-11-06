package battles;

import java.util.Scanner;

public class Battles {

    public static void main(String[] args) {

        // Spelaren kommer in i rummet
        // Spelaren slår tärning lika många gånger som hjältens Initiativ och totala värdet sparas
        // Monstren i rummet slår tärning lika många gånger som deras Initiativ och totala värdet per monster sparas
        // Alla Initiativ-värden jämförs och den som får högst börjar, sen går det i fallande ordning
        // När det är spelarens tur får den först välja om den vill strida eller fly
        // Vill spelaren strida så kastas lika många tärningar som hjältens Attack
        // Monstret slår lika många tärningar som dess Smidighet
        // Spelarens Attack-värde jämförs mot monstrets Smidighets-värde
        // Är Attack-värdet STÖRRE än Smidighet-värdet så dras ett poäng från monstrets Tålighet
        // Är Attack-värdet MINDRE än Smidighet-värdet så missar attacken.
        // Vill spelaren fly så gångras dennes Smidighet med 10 för att räkna ut hur stor procents chans denne har att fly
        // Lyckas spelaren fly går denne tillbaka till föregående rum och får välja var denne ska gå näst
        // Spelet måste komma ihåg monstren som var i rummet
        // Lyckas spelaren inte fly går turen över till monstret som attackerar
        // När det är monstrets tur attackerar det, då jämförs monstrets Attack-värde mot spelaren Smidighet-värde
        // Striden tar slut när spelaren vinner över alla monster, själv dör eller lyckas fly
        battle();
    }

    public static void battle() {
        Scanner sc = new Scanner(System.in);

        System.out.println("There is a monster in the room! \n1. Fight! \n2. Escape");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.println("You chose to fight!");

                int heroInitiative = 5; // Riddarens Initiativ. Här ska man egentligen hämta just den valda Hjältens Initiativ
                int heroInitiativeSum = rollDice(heroInitiative);
                System.out.println("Your initiative is: " + heroInitiativeSum);

                int monsterInitiative = 7; // Spindelns Initiativ. Här ska man egentligen hämta just det slumpade Monstrets Initiativ
                int monsterInitiativeSum = rollDice(monsterInitiative);
                System.out.println("The monster's initiative is: " + monsterInitiativeSum);

                if (heroInitiativeSum > monsterInitiativeSum) {
                    int attack = 6; // Riddarens Attack. Här ska man egentligen hämta just den valda Hjältens Attack
                    int attackSum = rollDice(attack);
                    System.out.println("You get to starta attacking!");
                    System.out.println("The result of your attack is: " + attackSum);

                    int agility = 3; // Spindelns Smidighet. Här ska man egentligen hämta just det slumpade monstrets Smidighet
                    int agilitySum = rollDice(agility);
                    System.out.println("The result of the monster's agility is: " + agilitySum);
                    
                    if (attackSum > agilitySum) {
                        System.out.println("Your attack hit the monster which loses 1 HP!");
                    } else {
                        System.out.println("The monster dodges your attack and you miss...");
                    }
                    
                } else {
                    System.out.println("The monster starts to attack you");
                    
                    int attack = 2; // Spindelns Attack. Här ska man egentligen hämta just det slumpade Monstrets Attack
                    int attackSum = rollDice(attack);
                    System.out.println("The result of the monster's attcak is: " + attackSum);
                    
                    int agility = 4; // Riddarens Smidighet. Här ska man egentligen hämta just den valda Hjältens Smidighet
                    int agilitySum = rollDice(agility);
                    System.out.println("The result of your agility is: " + agilitySum);
                    
                }
                break;

            case 2:
                System.out.println("You chose to try to escape!");
                break;

            default:
                System.out.println("Error! \nPlease choose 1 or 2!");
                break;
        }

    }

    public static int rollDice(int numberOfDice) {
        int sum = 0;

        for (int i = 0; i < numberOfDice; i++) {

            int resultOfDie = (int) (Math.random() * 6) + 1; // Slår en tärning med maxvärde 6
            sum += resultOfDie; // Lägger ihop alla träningsresultat
        }
        return sum;

    }

}
