# CURRENT WORKING ON: #
Game Object -> world generator -> scenes


WorldGenerator 
Tiles containing tilesheets and are not sprites
Before this can work... make class for alle game elements?
Player sensor..
Chest in world generator (all other objects than ground)







# Project_Fragment
[[LibGdx wiki](https://github.com/libgdx/libgdx/wiki)]
[[YouTube: Intro to LibGDX [10/10]](https://www.youtube.com/watch?v=IVtfZYbXuLg&list=PLZhNP5qJ2IA2RfQBxAC06xv2S07o-UBSd&index=3)]
[[Installation of Libgdx in Intellij](https://www.youtube.com/watch?v=q0wM63_KNIs)]
[[YouTube: Really great tut!](https://www.youtube.com/channel/UC1o7w8Y9BgI7ZgqyaPUNINQ/playlists)]

### Concept
#### Background history
this. . this this. . thisthis. . thisthis. . thisthis. . thisthis. . thisthis. . this
 
#### Game concept
The game will be an action side-scroller with loot. The name of the project is
 Project Fragment and the idea is that you loot fragment and might use them to
 craft loot.
 
There will be a "town" where you can sell items/fragment, craft items and access inventory.

#### Mechanics
##### The world
Genaration/Design

nico: USE TILES (32x32). world generation from a .png. The color matches an object. Like black for wall and blue for creatures and gold for chest.
[[Tiles libgdx wiki](https://github.com/libgdx/libgdx/wiki/Tile-maps)]

######Screen size
- 1280 resWide / 32pxTileSize = 40 tiles wide.
- 8000 resHigh / 32pxTileSize = 25 tiles High.

##### The Player
Movement and attacks

Chris: Attack hitbox, at roll = change hitbox to one that cannot get hit.
##### The Enemy
Movement and attacks
Bosses and mobs
##### Town
<b>Level access</b> Som way to enter levels
<b>Creating device</b> Som way of crafting using framents.
<b>Merchant</b> Where you can trade. Should have an animation (Stand breathing or glimmering?).

### Features
#### New Features
- Movement not based on FPS
- Player movement
    - Sharpen movement / weight
    - Sprite update / animation
    - Update hitbox?
    - Deal damage mechanic?
    - Health bar
- Menu
    - Options menu
        - Screen resolution
        - Sound options (toggle?)
- Config file with resolution and other settings
- Enemy creatures
- Chest
    - Loot
    - Spawns
- MAP GENERATION? Check concept.
- Town
    - Graphics: bricks with grass on top.

#### Improvements
- Abstract Contact Listener / make player sensor? Or chest?
- Platform needs to be scalable and hold its own height or width, or be rendered differently.
- Should the Color/Tiles be an enum? YES? Containing: color, sprite/id.

#### Bugs

#### Implemented
- Basic Game menu
- Basic player
    - Player movement
    - Hitbox and simple animation
- Basic chests
- Platforms