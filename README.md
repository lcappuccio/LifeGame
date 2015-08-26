# LifeGame

## Conway's Game of Life

A humble [Conway's Game Life](http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) java/swing implementation

## Installation

Download the code and execute or build with your favourite IDE

## Usage

### Main Controls

- Start: starts the game, will run indefinitely
- Speed menu: sets the speed between each iteration (a tribute to SimCity)
- Tick: stops the game and performs a single tick (or iteration)
- Stop: stops the game
- Reset: resets the game and generates a new random grid

### Preferences

- Cell Size: sets the cell size, smaller values generate more cells per grid, viceversa larger values will generate less cells per grid
- Theme: sets the colour theme for the grid
- Automata: selects a different automata for the game evolution, see [LifeWiki](http://www.conwaylife.com/wiki/Cellular_automaton)
- Life Probability: sets the probability of alive cell generations, combine different settings with the automata setting
- Apply: applies the preferences (*caution:* will stop the current game and reset the grid)

## License

LifeGame 0.1

Copyright (C) 2014 Leonardo Cappuccio

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see http://www.gnu.org/licenses/