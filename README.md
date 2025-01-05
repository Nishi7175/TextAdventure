# Text Adventure Game

## Overview
The **Text Adventure Game** is a simple Java-based adventure game where players explore a fictional world, interact with characters, and make choices that affect the outcome of the game. The game includes a combination of text-based storytelling, decision-making mechanics, and a lightweight combat system.

## Features
- **Interactive Storytelling**: Players progress through an engaging story with multiple choices.
- **Combat System**: Fight monsters and guards using different weapons.
- **Dynamic UI**: Game interface built with Java Swing.
- **Game State Management**: Track the player's health, weapon, and story progression.

## How to Play
1. Start the game by launching the `Game.java` file.
2. Follow the story prompts and make choices using the provided buttons.
3. Manage your health and defeat enemies to progress.
4. Complete the story by finding the shiny gem and earning the Guard's trust.

## Key Classes
### 1. `Game`
- **Purpose**: Entry point for the game.
- **Responsibilities**:
  - Initializes the UI, story, and visibility manager.
  - Handles button clicks using the `ChoiceHandler` inner class.

### 2. `Story`
- **Purpose**: Core game logic and story progression.
- **Responsibilities**:
  - Manages different locations and scenarios (e.g., town gate, crossroads).
  - Handles combat mechanics and player interactions with monsters.
  - Provides "Game Over" and ending screens.

### 3. `Player`
- **Purpose**: Represents the player's character.
- **Attributes**:
  - `hp`: Tracks the player's health.
  - `currentWeapon`: Tracks the player's equipped weapon.

### 4. `SuperMonster` and Subclasses (`Monster_Goblin`, `Monster_Guard`)
- **Purpose**: Represents enemies in the game.
- **Attributes**:
  - `hp`: Health of the monster.
  - `attack`: Damage the monster can inflict.
- **Subclasses**:
  - `Monster_Goblin`: Standard enemy.
  - `Monster_Guard`: Guard at the town gate with unique logic.

### 5. `UI`
- **Purpose**: Manages the graphical user interface.
- **Components**:
  - Title screen, main text area, choice buttons, and player stats.
  - Updates dynamically based on the player's progress.

### 6. `VisibilityManager`
- **Purpose**: Controls visibility of UI elements (e.g., toggling between title screen and game screen).

### 7. Weapons (`Weapon_Knife`, `Weapon_Sword`)
- **Purpose**: Represents different weapons the player can use.
- **Attributes**:
  - `name`: Name of the weapon.
  - `damage`: Damage dealt to enemies.

## How to Run
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Open the project in your preferred IDE (e.g., IntelliJ, Eclipse).
3. Ensure that all `.java` files are placed in their respective `package01` and `package02` directories.
4. Run the `Game.java` file to start the game.

## Future Improvements
- Add more storylines and decision branches.
- Introduce new monsters and weapons.
- Implement a save and load game feature.

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request with your changes.

