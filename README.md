# Peckman
This is a remake of the popular game, Pacman. "Peckman" is a play on words based off of the teacher I had when I was making this project. The project was made completely from scratch!
Other contributors: Angela Zhao and Michelle Kao
Each file has the name of the 

### Prerequisites

Any computer capable of running Java is suitable. A Java IDE is also needed, I personally used Eclipse to create the code.  

## Features

- A pacman sprite that is fully functional, with 5 different images
- A custom map, with walls that the pacman cannot run into
- 3 lives. When all lives are depleted, a "You lose!" message pop ups with your final score.
- Ghosts that chase you around
- Cherry that spawns slightly after game is created with bonus points
- File with JUnit tests

### Issues and Future Improvements (that I will try to implement)

- The ghosts all have the same programming, so they sometimes stack. I could either randomize each ghost's movements more or make each ghost with different movement patterns (like the original pacman)
- The ghosts sometimes go through parts of the wall, and their movement area is slightly skewed from where the walls are
- Several different map levels
- Ability to click restart when game is finished
- Large pac dots that will cause ghosts to run away and allows pacman to eat them
- Position of cherry is randomized

### Data Structures Used
- 2D array to hold information about the map.
- Linkedlist to hold the several ghosts.

### Built With

* [Eclipse](https://www.eclipse.org/ide/) - The Java IDE used

### Authors

* **Angela Zhao** - *Initial contributor*
* **Michelle Kao** - *Initial contributor*

### Acknowledgments

* Inspiration and idea from the original Pacman game by Namco. 
