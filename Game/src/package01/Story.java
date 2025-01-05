package package01;

// Updated the Story logic for guard interaction
import package02.Monster_Goblin;
import package02.Monster_Guard;
import package02.SuperMonster;
import package02.Weapon_Knife;
import package02.Weapon_Sword;

public class Story {

    Game game;
    UI ui;
    VisibilityManager vm;
    Player player = new Player();
    SuperMonster monster;

    int shinyGem;

    public Story(Game g, UI userInterface, VisibilityManager vManager) {

        game = g;
        ui = userInterface;
        vm = vManager;

    }

    public void defaultSetup() {

        player.hp = 10;
        ui.hpNumberLabel.setText("" + player.hp);

        player.currentWeapon = new Weapon_Knife();
        ui.weaponNameLabel.setText(player.currentWeapon.name);

        shinyGem = 0;

    }

    public void selectPosition(String nextPosition) {

        switch (nextPosition) {
            case "townGate":
                townGate();
                break;
            case "talkGuard":
                talkGuard();
                break;
            case "attackGuard":
                attackGuard();
                break;
            case "crossRoad":
                crossRoad();
                break;
            case "north":
                north();
                break;
            case "east":
                east();
                break;
            case "west":
                west();
                break;
            case "fight":
                fight();
                break;
            case "playerAttack":
                playerAttack();
                break;
            case "monsterAttack":
                monsterAttack();
                break;
            case "win":
                win();
                break;
            case "lose":
                lose();
                break;
            case "ending":
                ending();
                break;
            case "toTitle":
                toTitle();
                break;

        }
    }

    public void townGate() {
        ui.mainTextArea.setText("You are a lost wanderer who cannot remember anything of the past. \nAll you see in front of you is a massive gate with a guard standing in front of you \n\nWhat do you do?");
        ui.choice1.setText("Talk to the guard");
        ui.choice2.setText("Attack the guard");
        ui.choice3.setText("Leave");
        ui.choice4.setText("");

        game.nextPosition1 = "talkGuard";
        game.nextPosition2 = "attackGuard";
        game.nextPosition3 = "crossRoad";
        game.nextPosition4 = "";
    }

    public void talkGuard() {

        if (shinyGem == 0) {
            ui.mainTextArea.setText("Guard: Hello stranger, I have never seen your face. \nI'm sorry but I cannot let a stranger randomly enter our beloved town.");
            ui.choice1.setText(">");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "townGate";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        } else if (shinyGem == 1) {
            ending();
        }

    }

    public void attackGuard() {
        monster = new Monster_Guard(); // Assign Guard logic here
        ui.mainTextArea.setText("Guard: What do you think you're doing! \n\nThe guard attacked you and gave you a nasty blow \n(You receive 5 damage)");
        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        player.hp -= 5;
        ui.hpNumberLabel.setText("" + player.hp);

        if (player.hp <= 0) {
            lose(); // Show game over screen
        } else {
            game.nextPosition1 = "townGate";
        }

        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void crossRoad() {
        ui.mainTextArea.setText("You are at a crossroad. \nIf you go South, you will go back to the town.");
        ui.choice1.setText("Go North");
        ui.choice2.setText("Go East");
        ui.choice3.setText("Go South");
        ui.choice4.setText("Go West");

        game.nextPosition1 = "north";
        game.nextPosition2 = "east";
        game.nextPosition3 = "townGate";
        game.nextPosition4 = "west";

    }

    public void west() {
        monster = new Monster_Goblin();

        ui.mainTextArea.setText("You encounter a " + monster.name + "!");
        ui.choice1.setText("Fight");
        ui.choice2.setText("Run");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "fight";
        game.nextPosition2 = "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

    }

    public void fight() {
        ui.mainTextArea.setText(monster.name + ": " + monster.hp + "\n\nWhat do you do?");
        ui.choice1.setText("Attack");
        ui.choice2.setText("Run");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "playerAttack";
        game.nextPosition2 = "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

    }

    public void playerAttack() {
        int playerDamage = new java.util.Random().nextInt(player.currentWeapon.damage);

        ui.mainTextArea.setText("You attacked the " + monster.name + " and inflicted " + playerDamage + " damage!");

        monster.hp -= playerDamage;

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if (monster.hp > 0) {
            game.nextPosition1 = "monsterAttack";
        } else {
            game.nextPosition1 = "win";
        }

        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void monsterAttack() {

        int monsterDamage = new java.util.Random().nextInt(monster.attack);

        player.hp -= monsterDamage;
        ui.hpNumberLabel.setText("" + player.hp);

        ui.mainTextArea.setText(monster.name + " attacked you and inflicted " + monsterDamage + " damage!");

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if (player.hp > 0) {
            game.nextPosition1 = "fight";
        } else {
            lose();
        }

        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void win() {

        ui.mainTextArea.setText("You've defeated the " + monster.name + "!\nThe monster dropped a shiny gem!\n\n(You obtained a Shiny Gem!)");

        shinyGem = 1;

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void lose() {

        ui.mainTextArea.setText("You are dead!\n\n<GAME OVER>");

        game.nextPosition1 = "toTitle";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        vm.showTitleScreen();
    }

    public void ending() {

        ui.mainTextArea.setText("Guard: Wait you killed that goblin!\n No way that's the missing gem from the King's crown!\n Thank you so much, you're a true hero!\nWelcome to our town!\n\n<THE END>");

        ui.choice1.setVisible(false);
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);
    }

    public void toTitle() {

        defaultSetup();
        vm.showTitleScreen();
    }
}
