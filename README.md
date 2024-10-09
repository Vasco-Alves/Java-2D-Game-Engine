
# 2D Game Engine and Game Project

This repository contains a custom 2D game engine and a game built using that engine. The engine is designed to be lightweight, flexible, and easy to extend, supporting core features such as object rendering, collision detection, player movement, and more.

This is a project for me to train Java and game development. There is a lack of comments and some parts of the code are not in use, this project is not finished yet, I'm still very far away from finishing it and I alway get new ideas to implement and test.

If you see some very obvious mistakes or unoptimized code I'm sorry :)

## Game Overview

This game is a 2D adventure game that revolves around exploring a dynamic world, interacting with various objects, and battling enemies. The player can move around, collect items, manage an inventory, and interact with other entities like chests or NPCs.

### Features So Far:
- **Player movement**: Fully functional player controls using keyboard input.
- **Collisions**: Sophisticated collision detection system with depth perception for better interaction with the environment.
- **Entities**: Various game entities (mobs, players, chests) that can interact with each other and the player.
- **Inventory System**: Objects like players and chests can have inventories, allowing for item collection, storage, and use.
- **Basic AI**: Simple enemy AI that follows the player and interacts with the environment.
- **Custom Hitbox**: Flexible hitbox implementation that allows precise control over object collisions.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/yourrepository.git
   ```
2. Compile the project using your preferred IDE (e.g., IntelliJ IDEA, Eclipse) or using a command-line tool like `javac`.
3. Run the project from the main class. It is located in **com.engine.Manager.java**.

## How It Works

### Game Engine

The engine is the backbone of the game, and it provides the following components:
- **Rendering System**: Objects that implement the `Renderable` interface are responsible for drawing themselves on the screen.
- **Update System**: Entities implement the `Updatable` interface to handle logic and update on each game tick.
- **Collision System**: Detects and processes collisions between game entities, with support for depth-based collision checks for a realistic 2D environment.
- **Hitboxes**: Every game object has a hitbox that defines its physical boundaries in the world. Hitboxes can be customized for specific entities to achieve various effects, such as better depth perception.
  
### Game Design

- **GameObject**: The base class for all interactive and renderable objects in the game.
- **Player**: The main character controlled by the player, capable of moving, collecting items, and fighting enemies.
- **Mob**: Entities that move around the world and interact with the player. They can collide, follow, or even attack depending on their AI.
- **Inventory**: Each entity, including the player and chests, can have its own inventory for storing and managing items.
- **Collision Handling**: A flexible collision system that supports various types of interactions, including advanced depth-based collision detection between entities.

### Upcoming Features

- **Improved Inventory**: Separate inventory for player items and currency (coins, gems, etc.).
- **Additional AI**: More complex enemy behaviors and interactions.
- **More enemy types**: So far there is only a test enemy.
- **World Expansion**: More tilesets and object types to expand the game world.
- **Save/Load Functionality**: Save the game state and load it later.

## Gameplay Screenshot

![Game GIF](assets/gameplay/movement.gif)

*Movement and collisions with entities.*



![Game GIF](assets/gameplay/combat.gif)

*Test enemy following player and collision between them.*

## License

This project is licensed under the MIT License.
