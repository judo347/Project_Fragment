# TODO: #
##### Player movement REWORK!

- USE when player is not moving: body.setLinearVelocity(new Vector2(0,0));
- USE when player is moving: getBody().setLinearVelocity(new Vector2(-1f,0));
- Maybe fix friction?

ContactListener not working on items: FIX!

should all fixture.setUserData(this); be like that? Make them like that.

Maybe create maskBits and such for all?

should make object for inventory handling?
Chest should extend StaticAnimatedProb (and have animations).
Implement menus for portal (level select) and vendor (shops).

Rework this document + add gifs and promotional content?

Tiles containing tilesheets and are not sprites

- Platform needs to be scalable and hold its own height or width, or be rendered differently.
- Should the Color/Tiles be an enum? YES? Containing: color, sprite/id.

#### Improvements:

Dummy tiles should not be at bottom.

Movement not based on FPS

PLAYER MOVEMENT SHOULD BE SHARP! FIX!

#### Bugs:

Items from chests: flies further the faster the player moves.



# Project_Fragment
[[LibGdx wiki](https://github.com/libgdx/libgdx/wiki)][[YouTube: Intro to LibGDX [10/10]](https://www.youtube.com/watch?v=IVtfZYbXuLg&list=PLZhNP5qJ2IA2RfQBxAC06xv2S07o-UBSd&index=3)]
[[Installation of Libgdx in Intellij](https://www.youtube.com/watch?v=q0wM63_KNIs)][[YouTube: Really great tut!](https://www.youtube.com/channel/UC1o7w8Y9BgI7ZgqyaPUNINQ/playlists)]

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

#### Screen size

- 1280 resWide / 32pxTileSize = 40 tiles wide.
- 8000 resHigh / 32pxTileSize = 25 tiles High.

##### The Player
Movement and attacks

Chris: Attack hitbox, at roll = change hitbox to one that cannot get hit.
##### The Enemy
Movement and attacks
Bosses and mobs
##### Town
<b>Level access</b> Some way to enter levels
<b>Creating device</b> Some way of crafting using fragments.
<b>Merchant</b> Where you can trade. Should have an animation (Stand breathing or glimmering?).