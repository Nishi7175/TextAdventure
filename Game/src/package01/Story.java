package package01;

import package02.Monster_Goblin;
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
		
		switch(nextPosition) {
		case "townGate": townGate(); break;
		case "talkGuard": talkGuard(); break;
		case "attackGuard": attackGuard(); break;
		case "crossRoad": crossRoad(); break;
		case "north": north(); break;
		case "east": east(); break;
		case "west": east(); break;
		case "fight": fight(); break;
		case "playerAttack": playerAttack(); break;
		case "monsterAttack": monsterAttack(); break;
		case "win": win(); break;
		case "lose": lose(); break;
		case "ending": ending(); break;
		case "toTitle": toTitle(); break;
		
		
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
		
		if(shinyGem==0) {
			ui.mainTextArea.setText("Guard: Hello stranger, I have never seen your face. \nI'm sorry but I cannot let a stranger randomly enter our beloved town.");
			ui.choice1.setText(">");
			ui.choice2.setText("");
			ui.choice3.setText("");
			ui.choice4.setText("");
			
			game.nextPosition1 = "townGate";
			game.nextPosition2 = "";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
		}
		else if(shinyGem==1) {
			ending();
		}
		
	}
	public void attackGuard() {
		ui.mainTextArea.setText("Guard: What do you think you're doing! \n\nThe guard attacked you and gave you a nasty blow \n(You receive 5 damage)");
		ui.choice1.setText(">");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "townGate";
		player.hp = player.hp -5;
		ui.hpNumberLabel.setText("" + player.hp);
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
	public void north() {
		ui.mainTextArea.setText("There is a river.\nYou drink the water and take a nap by the riverside.\n\n(Your HP is recovered by 3)");
		player.hp = player.hp + 3;
		ui.hpNumberLabel.setText("" + player.hp);
		ui.choice1.setText("Go South");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "crossRoad";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
	}
	public void east() {
		ui.mainTextArea.setText("You walked into an forest masked by fog and found a glimmering sword!\n\n(You obtained a Sword)");
		
		player.currentWeapon = new Weapon_Sword();
		ui.weaponNameLabel.setText(player.currentWeapon.name);
		
		ui.choice1.setText(">");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "crossRoad";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
		
	}
	public void west() {
		
		monster.name = new Monster_Goblin();
		
		ui.mainTextArea.setText("You encounter a " + monster.name +"!");
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
		
		ui.mainTextArea.setText("You attacked the " + monster.name + " and inflicted" + playerDamage + " damage!");
		
		monster.hp = monster.hp - playerDamage;
		
		ui.choice1.setText(">");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		if(monster.hp>0) {
			game.nextPosition1 = "monsterAttack";
			game.nextPosition2 = "";
			game.nextPosition3 = "";
			game.nextPosition4 = "";	
		}
		else if(monster.hp<1) {
			game.nextPosition1 = "win";
			game.nextPosition2 = "";
			game.nextPosition3 = "";
			game.nextPosition4 = "";	
		}
	}
	public void monsterAttack() {
		
		int monsterDamage = new java.util.Random().nextInt(monster.attack);
		
		player.hp = player.hp - monsterDamage;
		ui.hpNumberLabel.setText("" + player.hp);
		
		game.nextPosition1 = ">";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
		
		if(player.hp>0) {
			game.nextPosition1 = "fight";
			game.nextPosition2 = "";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
		}
		else if (player.hp<1){
			game.nextPosition1 = "lose";
			game.nextPosition2 = "";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
		}
	}
	public void win() {
		
		ui.mainTextArea.setText("You've defeated the " + monster.name + "!\nThe monster dropped a shiny gem!\n\n(You obtained a Shiny Gem!)");
		
		shinyGem = 1;
		
		game.nextPosition1 = "Go East";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
		
		game.nextPosition1 = "crossRoad";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
	}
	public void lose() {
	
		ui.mainTextArea.setText("You are dead!\n\n<GAME OVER>");
		
		
		game.nextPosition1 = "To the title screen";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
		
		game.nextPosition1 = "toTitle";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
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
