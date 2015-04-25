###Description:
The application allows playing the tic tac toe game. It supports the following additional options:
- new game - starts the game from the beginning
- undo - returns game into previous state
- redo - reverses undo
- save - saves the current game state. This option enables to continue the game later.
- load - loads saved game state if exists

###Game modes:
- single player (vs AI which is using minimax algorithm)
- multiplayer - vs another player.

###User Interface
The game supports:
- swing user interface
- console user interface

###How to run?
You can use the jar files in build folder. To run the game with:
- swing user interface type in  `java -jar ttt_swing.jar` for single player
- console user interface type in `java -jar ttt_console.jar` for single player.

If you want to play multiplay mode add an argument - for example `java -jar ttt_swing.jar -m`  
