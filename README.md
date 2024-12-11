# Main Work (According to the Course Requirements)
## Github Link
You can find the project repository on GitHub at the following link:
- [CW2024 GitHub Repository](https://github.com/hcyzz1/CW2024)
- If the link doesn't work, use this one instead : https://github.com/hcyzz1/CW2024
## Compilation Instructions
### Prerequisites
To compile and run this project, make sure the following tools are installed/environment is setted.
1. JDK (Java Development Kit) 19+
2. Apache Maven
3. JavaFX SDK 
   - You need to configure it according to the IDE you are using, such as IntelliJ IDEA, to ensure it works effectively.
   - In addition to using IDEA, you can also use the command `mvn javafx:run` to directly test whether the project runs smoothly.
### Install Dependencies Using Maven
1. Go to your folder.

   `cd path/to/your/project`

2. To verify that Maven is correctly set up, run:

   `./mvnw --version`

3. To clean and install dependencies for the project, run:

   `./mvnw install clean`

4. To clean and package the project (creating a deployable artifact), run:

   `./mvnw package clean`
* **Note that if you choose to use the `mvn` command from your own environment instead of the Maven wrapper (`mvnw`), you should configure it according to your system setup, rather than blindly following the steps above.**

### Start the Application 

You have two ways to run the application.

1. Run **Main.main()** as Java Application in IDE.
1. User command `mvn javafx:run` or command `./mvnw javafx:run`.

### If you want to see JavaDoc

Generate JavaDoc for beginning.

- User Command `mvn javadoc:javadoc`
- Check `index.html` in path `\`

Check the file here. I also use github function to make a branch `gh-pages` and put my JavaDoc there. You can check Be the  [link](https://hcyzz1.github.io/CW2024/) directly.

- https://hcyzz1.github.io/CW2024/

## Implemented and Working Properly

### rename packages & classes

Including put them in correct position, You can check the folder tree directly.(It's faster).

Also, I will write some introduction about the thinking when I reput & refactory them.

#### Check the folder tree

```
.
├── README.md
├── dir.txt
├── files.txt
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   └── hcyzz1company
│   │   │   │       └── skybattle
│   │   │   │           ├── Main.java
│   │   │   │           ├── constants
│   │   │   │           │   ├── AppConstants.java
│   │   │   │           │   └── ImageConstants.java
│   │   │   │           ├── core
│   │   │   │           │   ├── GameController.java
│   │   │   │           │   ├── factory
│   │   │   │           │   │   ├── ImageFactory.java
│   │   │   │           │   │   └── LevelFactory.java
│   │   │   │           │   ├── handle
│   │   │   │           │   │   └── UserInputHandle.java
│   │   │   │           │   └── level
│   │   │   │           │       ├── LevelParent.java
│   │   │   │           │       ├── levelOne
│   │   │   │           │       │   └── LevelOne.java
│   │   │   │           │       ├── levelThree
│   │   │   │           │       │   └── LevelThree.java
│   │   │   │           │       └── levelTwo
│   │   │   │           │           └── LevelTwo.java
│   │   │   │           ├── entity
│   │   │   │           │   ├── actors
│   │   │   │           │   │   ├── BossPlane.java
│   │   │   │           │   │   ├── EnemyPlane.java
│   │   │   │           │   │   ├── Plane.java
│   │   │   │           │   │   └── UserPlane.java
│   │   │   │           │   ├── common
│   │   │   │           │   │   ├── ActiveActor.java
│   │   │   │           │   │   ├── ActiveActorDestructible.java
│   │   │   │           │   │   └── Destructible.java
│   │   │   │           │   └── projectiles
│   │   │   │           │       ├── BossProjectile.java
│   │   │   │           │       ├── EnemyProjectile.java
│   │   │   │           │       ├── Projectile.java
│   │   │   │           │       └── UserProjectile.java
│   │   │   │           ├── exceptions
│   │   │   │           │   └── LevelLoadingException.java
│   │   │   │           ├── ui
│   │   │   │           │   ├── LevelView.java
│   │   │   │           │   ├── basicImage
│   │   │   │           │   │   ├── GameOverImage.java
│   │   │   │           │   │   ├── HeartImage.java
│   │   │   │           │   │   ├── ShieldImage.java
│   │   │   │           │   │   └── WinImage.java
│   │   │   │           │   └── specialElements
│   │   │   │           │       └── HeartDisplay.java
│   │   │   │           └── utils
│   │   │   │               ├── AlertUtil.java
│   │   │   │               ├── ImageUtil.java
│   │   │   │               └── LevelUtil.java
│   │   │   └── module-info.java
│   │   └── resources
```

#### Note about packages renaming

I restructured project package categories.

- Constant-related information
- Core logic information
  - level
    - Specific level implementation
  - handle 
    - Game engine-related classes
  - factory
    - Some factory classes
- Entities involved in the game
  - actors
    - Planes for user or enemy or other units.
  - projectile
    - Projectile for user or enemy or other units
  - common
    - Some base classes for entities.
- Custom exceptions
- UI-related classes
  - Display of page elements
- Utility classes

#### Note about classes renaming

- We should keep similar names for classes with the same functionality, and also use similar keywords for classes with an inheritance relationship.
- This will make project maintenance clearer.
  Based on this idea, I renamed the classes and organized them into the appropriate folders according to their functionality.
  The best example of this is the Plane-related classes. I renamed the base class to `Plane`, and its subclass Boss was renamed like `BossPlane`, `UserPlan` and so on.

### Fixed some bugs

1. Ensure that Game can be properly loaded into the next level.
2. Fixed a bug that caused the system to crash due to incorrect image path.
3. Fixed the collision detection between bullets to ensure that both bullets are destroyed upon collision.
4. Fixed a bug where the boss's shield in the final level would not display correctly.

### Some logic optimizations

1. Imported LevelFactory to unify the generation of level entities.
2. Extracted `ImageUtil` and `ImageFactory` to unify the management of image generation information.
3. Moved some methods from subclasses of LevelParent to the LevelParent superclass, in order to improve code reuse.
4. Added image processing to automatically filter out transparent pixel backgrounds, allowing for more flexible use of assets.

### Function Added

1. Submitted basic JUnit test units to ensure the project can be tested using the testFX framework.
2. Inserted a new level between the original two levels. In a space-themed background, you need to defeat 20 enemies to proceed to the next level.
3. Changed the boss's shield in the final level to a semi-transparent mode to optimize the UI.

### Improved Git usage skills

I gained a better understanding of Git throughout the project, mainly in the following two aspects:

1. When renaming files, it's best to make a separate commit to ensure that file comparisons remain clear.
2. When naming branches, using the format [task type/specific content] makes it easier to track the purpose of the branch later. For example, `refactor/projectile` represents a refactoring task related to the "projectile" entites.

## Implemented but Not Working Properly



## Features Not Implemented

1. Java Version.

   - Although I successfully completed the project development using JavaFX 19 after configuration.

     I found that version 19 is not a long-term supported version for JDK. 

     Therefore, a better solution would be to use a long-term support (LTS) version.

2. Level Load time.

   - A loading time should be added between levels.

     Because it can implement a smooth user experience.

3. Display level objectives
   - Currently, users cannot directly know the level objectives from the page. To improve user experience, we can add some additional features.
   - In levels where the objective is to shoot down a specified number of enemy planes, we can display [the target number of enemy planes to be destroyed] and [the number of enemy planes already destroyed] on the page.
   - In levels where the boss needs to be defeated, we can add a display of the boss's health on the page.
4. After the game ends, a quit button or a restart button should be provided for the user.
5. Make the game more distinctive
   - Even with the same shooting game concept, we can make the game more interesting by changing the design itself. This might make our game more appealing.
   - For example, setting the background in a magical academy, where the player is a wizard, the bullets fired are lightning-shaped magic, and the enemies to be defeated are the academy’s foes.

6. More meaningful JUnit tests.

## New Java Classes
In this part, you can click the title to go to git and check the source file directly.

I've also specified the relative file path clearly in the Location.

### [AppConstants.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/constants/AppConstants.java)

- Location
  - src/main/java/com/hcyzz1company/skybattle/constants/AppConstants.java
- Description
  - A new const class, save the game window settings and related variables here.
  - I've also extracted the boundary for moving Actors up and down and managed it in a unified way here.
- Purpose
  - Decouple the code, make it easier to change the windows settings and title information in the future.

### [ImageConstants.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/constants/ImageConstants.java)

- Location
  - src/main/java/com/hcyzz1company/skybattle/constants/ImageConstants.java
- Description
  - I have extracted the resource path of the entire project into a constant, `ImageConstants.IMAGE_ROOT_PATH`. 
- Purpose
  - This helps prevent potential issues, such as errors caused by typos when we use the root path for images.
  - Also, when we want to change the root(such as put all images in a specific folder), we can simply change ont constant file instead changing many files.

### [LevelUtil.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/utils/LevelUtil.java)

- Location
  - src/main/java/com/hcyzz1company/skybattle/utils/LevelUtil.java

- Description
  - An Util class to manage level information. Including finding the first level, finding the next level for the specific level and other functions.
- Purpose
  - Make the logic about level management in the same file.
  - It will be easier to add new levels later, and we can modify the relationships more clearly, avoiding any impact on the main logic.
  - You can even modify the order of the levels here.

### [LevelFactory.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/factory/LevelFactory.java)

- Location
  - src/main/java/com/hcyzz1company/skybattle/core/factory/LevelFactory.java
- Description
    - Used the Factory Design Pattern to manage level information.
- Purpose
    - It makes the code easier to maintain by organizing level creation in one place, keeping the code clean.
    - The factory reuses the same logic for creating levels, reducing repetition and mistakes.
    - Code that calls level-related methods via reflection is usually complex, so a separate factory pattern is used to manage them. This way, we can more easily add other types of levels in the future.

### [ImageFactory.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/factory/ImageFactory.java)

- Location
  - src/main/java/com/hcyzz1company/skybattle/core/factory/ImageFactory.java
- Description
    - Used the Factory Design Pattern to create Image instances in the project.
- Purpose
    - Extensibility: The Factory pattern allows easy extension.
    - Separation of Concerns: Keeps the creation logic separate from the rest of the codebase.
    - Maybe we will need more ways to load pictures in the future. For example, load the picture from the Internet. At that time, we just need to update this method.

### [ImageUtil.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/utils/ImageUtil.java)

- Location

  - src/main/java/com/hcyzz1company/skybattle/utils/ImageUtil.java

- Description

  - A utility class providing methods for creating and displaying images.

  - Be able to filter out transparent pixels from images in the Util!!  (**So proud of this idea!**)

    You can check the method here: https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/utils/ImageUtil.java#L97

- Purpose

  - Better Image Operations: 

    Organize image-related operations (such as resizing, positioning, and displaying) in a single class. The code becomes easier to maintain and extend.

  - Improving Code Readability: By extracting common operations, the business logic code becomes cleaner and easier to understand.
  
  - 
  
  - In this class, I added the automatic handling of filtering out transparent pixels from images! I'm glad that I separated it into its own class from the beginning, as it allowed me to easily add the background removal function later on.

### [HeartImage.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/ui/basicImage/HeartImage.java)

- Location
  - src/main/java/com/hcyzz1company/skybattle/ui/basicImage/HeartImage.java
- Description
  - A class to organize Heart Image.
- Purpose
  - Extracted the image-related operations from `HeartDisplay.java`.
    This ensures that `HeartDisplay.java` mainly handles the logical relationships, making the code structure clearer and easier to understand.

### [ImageUtilTest.java](https://github.com/hcyzz1/CW2024/blob/master/src/test/java/com/hcyzz1company/skybattle/utils/ImageUtilTest.java)

- Location
  - src/test/java/com/hcyzz1company/skybattle/utils/ImageUtilTest.java
- Description
  - A Junit Test for [ImageUtil.java].
- Purpose
  - I attempted to create a JUnit test method for the `ImageUtil` class. Since `ImageUtil` is a fundamental utility class for the entire project, I hope to ensure that it maintains stable functionality regardless of any changes made.

### [UserInputHandle.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/handle/UserInputHandle.java)

- Location
  - src/main/java/com/hcyzz1company/skybattle/core/handle/UserInputHandle.java
- Description
  - Managing game controls related to user input in a specific file.
- Purpose
  - Extract the user input-related operations from the original `LevelParent` to reduce system coupling.
  - This will help with future functionality management and related expansions.
  - In the current modification, I have ensured that the plane can move left and right. However, I found that the logic for detecting when it stops is still not smooth enough. It may be possible for further improvements in the future. 


### [MusicUtil.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/utils/MusicUtil.java)

- **Location**
  - `src/main/java/com/hcyzz1company/skybattle/utils/MusicUtil.java`
- **Description**
  - This utility class manages the playback of music and sound effects in the Sky Battle game. It provides methods to play background music, shooting sounds, and explosion sounds. It also allows toggling the enabling or disabling of these sound effects.
- **Purpose**
  - Centralizing audio management helps streamline the sound effect control process and makes it easier to manage and modify sound settings in the future. This contributes to a more immersive gameplay experience by ensuring consistent audio feedback.


### [PowerUpItem.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/entity/item/PowerUpItem.java)

- **Location**
  - `src/main/java/com/hcyzz1company/skybattle/entity/item/PowerUpItem.java`
- **Description**
  - Represents a power-up item in the game. Power-up items are destructible entities that provide special effects when activated. The behavior of the power-up is defined by its subclasses.
- **Purpose**
  - Provides a base class for all power-up items, allowing for common functionality such as movement and destruction. Subclasses will implement specific effects and behaviors associated with different types of power-ups.

---

### [AttackSpeedPowerUp.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/entity/item/AttackSpeedPowerUp.java)

- **Location**
  - `src/main/java/com/hcyzz1company/skybattle/entity/item/AttackSpeedPowerUp.java`
- **Description**
  - Represents a power-up item that increases the attack speed of the player. This item moves at a fixed speed and is destroyed when it takes damage.
- **Purpose**
  - Implements the specific effect of increasing the player's attack speed when activated. It handles its own destruction upon taking damage.

---

### [HeartPowerUp.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/entity/item/HeartPowerUp.java)

- **Location**
  - `src/main/java/com/hcyzz1company/skybattle/entity/item/HeartPowerUp.java`
- **Description**
  - Represents a power-up item that increases the player's health. This item moves at a fixed speed and is destroyed when it takes damage.
- **Purpose**
  - Implements the effect of increasing the player's health upon activation. It also manages its destruction when it takes damage.

---

### [PowerUpManager.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/entity/item/PowerUpManager.java)

- **Location**
  - `src/main/java/com/hcyzz1company/skybattle/entity/item/PowerUpManager.java`
- **Description**
  - Manages the generation and dropping of power-up items during gameplay. This class determines the type of power-up item to drop based on random probabilities.
- **Purpose**
  - Centralizes the logic for power-up generation, allowing for random selection of power-ups to enhance gameplay dynamics. This helps maintain a balanced and engaging game environment by providing players with various power-up options.

### [GameStartScreen.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/GameStartScreen.java)

- **Location**
  - `src/main/java/com/hcyzz1company/skybattle/core/GameStartScreen.java`
- **Description**
  - Represents the game's start screen with a background image and buttons for starting the game, viewing controls, and adjusting sound settings.
- **Purpose**
  - Provides a user-friendly interface for the game start, allowing players to initiate gameplay, view controls, and customize sound settings.

### [HealthBar.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/ui/specialElements/HealthBar.java)

- **Location**
  - `src/main/java/com/hcyzz1company/skybattle/ui/specialElements/HealthBar.java`
- **Description**
  - Represents a health bar in the game, visually indicating the player's health status through a progress bar.
- **Purpose**
  - Provides a visual representation of health, allowing players to easily see their current health in relation to their maximum health.

---

## Modified Java Classes

Each title represents a file. Each number represents a change point. 

I will explain the change points and the reasons for the changes, including the line numbers associated with the change points(if it's a simple change instread of the whole file change).

In fact, some of the changes described here may overlap with the "New Java Classes" section. However, to better help you understand my changes, I hope you can forgive these redundant parts.

### [module-info.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/module-info.java#L10)

1. I modified the information to be exported based on the new file directory structure after the refactor.

   - Location:
     - src/main/java/module-info.java
   
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/module-info.java#L10
   
   - Reason:
   
     - Make sure all classes can be exported for JavaDoc.
   
     - Make sure the application can run successfully.
   
     - Change the package names following my refactory.

### [Main.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/Main.java)

1. Moved some variables about settings into a new Constant file.

   - Location:
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/Main.java#L29

     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/constants/AppConstants.java#L7


   - Reason: 
     
     - Move some constants that is not about the main logic into a new file. It will make the project easier to maintenance in the future. 
     
     - Also, when we want to change the settings, we can find the settings easier in the new file.


### [Controller.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/GameController.java)

1. Split the logic for loading the first level and subsequent levels into a new file.
   - Location:
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/GameController.java#L61
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/factory/LevelFactory.java
   - Reason:
     - Ensured that the Controller is only responsible for controlling level listeners and other parts related to the main logic.
     - Moved parts(logics about levels) that might change into new methods, making it easier to modify and extend in the future.

### [LevelParent.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/level/LevelParent.java)

1. Fix method `goToNextLevel` .
   - Location: 
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/level/LevelParent.java#L136
   - Reason:
     - I added the `levelChanging` variable to ensure thread safety.
     - Call the new method following `stopCurrentLevelActivities`, make sure the game be able to load the next level correctly.
2. Add method `stopCurrentLevelActivities`.
   - Location: 
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/level/LevelParent.java#L414
   - Reason: 
     - In order to prevent any dead loops.
     - Before entering a new level, I make sure to remove the observers for the old level and delete the entities related
       to the old level.
3. I rewrited method `checkIfGameOver` and added `winLevel` method, in orde to extract the same logic, avoiding child class override the same logic again and again.
   - Location: 
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/level/LevelParent.java#L73
   - Reason: 
     - Actually, the logic for completing or failing a level is quite similar, so I extracted them.
       - When the player fails, it’s because they received enough damage.
       - When a level is won, it’s either by advancing to the next level or winning the entire game.
       - Each level is allowed to define its own victory conditions, such as defeating enough enemies or defeating a boss, by override the `winLevel` method.
     - Subclasses only need to override the `winLevel` method！It's easier to use now!
4. Optimized constant information in `LevelParent`
   - Location :
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/level/LevelParent.java#L49
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/constants/AppConstants.java
   - Reason:
     - The size of game's pages rarely change. 
     - This avoids passing too many parameters between classes, improving the readability of the project code.
5. Move method `instantiateLevel` and method  `initializeFriendlyUnits`  into `LevelParent`.  ( move from Child class to Parent class)
   - Location: 
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/level/LevelParent.java#L66
   - Reason:
     - Child class do the same rewrite again and again. So it's better to extract them into the parent class to improve code reusability.
6. Extract the user keyboard operations from `LevelParent` into a separate class `UserInputHandle`.
   - Location:
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/level/LevelParent.java#L117
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/core/handle/UserInputHandle.java
   - Reason:
     - Reduce the coupling between classes by separating individual functionalities into dedicated classes.
     - It can be safer and easier to implement the user input related operation in the future.


### [ShieldImage.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/ui/basicImage/ShieldImage.java)

1. I modified the path for finding the Shield image.
   - Location: 
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/ui/basicImage/ShieldImage.java#L11
   - Reason: 
     - The original image path had a `.png` extension, but the code was using a `.jpg` extension, which caused the exception.

### [HeartDisplay.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/ui/specialElements/HeartDisplay.java)

1. The image handling logic has been moved to a new file, `HeartImage.java`.
   - Location:
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/ui/specialElements/HeartDisplay.java#L43
   - Reason:
     - Split the logic so that each class is responsible for a single function, making the code structure clearer.
     - The pattern like `HeartDisplay` and `HeartImage` may be can use more in the project. It's more clearly now.

### [ActiveActorDestructible.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/entity/common/ActiveActorDestructible.java)

1. remove `updateActor()` method
   - Location:
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/entity/common/ActiveActorDestructible.java
   - Reason:
     - In this simple game, it seems that method `updatePosition()` is too similar to method `updateActor()`. So I make them together. 

### [Projectile.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/entity/projectiles/Projectile.java)

1. Define method `updatePosition()` here. (Parent Class for projectile objects)
   - Location:
     - https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/entity/projectiles/Projectile.java#L41
   - Reason:
     - I found that all projectile objects will only move Horizontally.
     - It is not a good idea to rewrite the same method again and again. So I move the method implementation into the Parent Class[Projectile.java]. The children class can still write it if they want. But if the projectile will only move horizontally. The children class don't need to rewrite the method any more.


### [BoosPlane.java](https://github.com/hcyzz1/CW2024/blob/master/src/main/java/com/hcyzz1company/skybattle/entity/actors/BossPlane.java)

1. Implement Shield function for bossPlane
   - Location: 
     - You can check all related changes in the PR:https://github.com/hcyzz1/CW2024/pull/9/files#diff-8db55e1c9c00b78214649359e9f60dad3b3039b0f3984719c1d9b426f1a5bc5a
   - Reason:
     - Added a ShieldImage member variable to BossPlane and created a method to retrieve the corresponding ImageView. This way, by simply adding it to the Level instance, the view will be displayed correctly.
2. Changed the shield trigger probability and maximum duration
   - Location:
     - Same PR: PR:https://github.com/hcyzz1/CW2024/pull/9/files#diff-8db55e1c9c00b78214649359e9f60dad3b3039b0f3984719c1d9b426f1a5bc5a
   - Reason:
     -  Improve user experience and make the gameplay smoother.


### Other Simple Image Management Changes

In this section, I will list some small changes I made. The changes and their reasons are straightforward, so I’ve grouped them together.

- [ShieldImage.java]()

- [WinImage.java]()

- [GameOverImage.java]()

  - To align with the refactored image handling approach, I rewrote the methods using the newly created `ImageUtil`.

# Documents

- JavaDoc
  - https://hcyzz1.github.io/CW2024/
- Readme.md
  - https://github.com/hcyzz1/CW2024/blob/master/README.md
- Class Diagram
  - Picture with description: https://github.com/hcyzz1/CW2024/blob/gh-pages/Class%20Diagram.jpg 
  - Or if you want to see uml : https://github.com/hcyzz1/CW2024/blob/gh-pages/Class%20Diagram.uml

## Unexpected Problems

### The issue of confusion during Git pre-merge comparisons.

- Date
  - 2024/11/15

- Problem Description
  - Today, while using Git, I found that if a single commit includes both renaming files and changing their content, a lot of formatting details might appear when checking the changes. This makes it harder to compare branches and merge them later using GitHub's tools.

- How I resolved it
  - To make my commit history clearer, I will commit file renaming separately from other changes. This will help make my changes easier to understand.


### Issues with Modifying the `pom.xml`

- Date
  - 2024/11/20
- Problem Description
  - Although I understand the principles of Maven, when adding new dependencies or making changes, there are times when crashes occur, possibly due to IDE or caching issues.
- How I resolved it
  - When sharing with others, try importing and using your project in a new directory to ensure it works correctly.

# OTHERS

## Work For Each Day

This is just a note I made while doing homework. If you're interested, feel free to read it.

### Day1 (2024/11/12)

You can check branch `https://github.com/hcyzz1/CW2024/tree/setup-and-initial-setup` here.

- Forked the project and set up Git.
  - I tested SSH and ensured that I could manage the project using Git.
- Configured the local environment.
  - I successfully configured the environment and started the project on my local machine.
- Reviewed the code and added comments.
  - This helped me gain an initial understanding of the project.
  - I may revise the comments later as I continue working on the project.
  - I can use these comments to generate **JavaDoc** files successfully!

### Day2 (2024/11/13)

You can check branch `https://github.com/hcyzz1/CW2024/tree/basic-bugs-fix` here.

- Try to run and play locally. I found the game can't go to Level2. In order to resolve this problem, What I do:
  - I added the [AlertUtil] class to help split some logic for better understanding of the project.
  - The first problem I found was that [the path to the shield file was incorrect]
    - I changed the extension from .jpg to .png. This allowed the image to be found correctly, and now I can load the
      background for levelTwo!
  - Then, while debugging, I noticed that even though I hadn't performed any actions in levelTwo, the [update] method
    in [LevelParent] was being called repeatedly, which eventually led to an Out of Memory (OOM) error.
    - I found that it is the [LevelParent] class use problem!
    - When using [Observable], before we observe the newLevel, we should destroy the old observable obj to avoid OOM!
  - Now we can play Level Two and Kill the Boss!

### Day3 (2024/11/14)

I have refactored the project preliminarily.

- Meaningful package naming
  - I looked up the naming conventions for packages in Java.
  - I found that they generally use the company's domain name followed by project information.
  - So, for the company domain part, I'm using my GitHub username as a placeholder.
  - For the project information, I named it "skybattle", which reflects my understanding of this game.(same as the
    default title)
- Meaningful package organisation
  - I reorganized the package structure. I extracted them into packages like action, boot, constants, core, entity,
    exceptions, logic, ui, and utils.(I still think that I will change it again and again later)
    - action: Contains actions and game events.
    - boot: Manages the game's startup processes.
    - constants: Stores constant values like settings, and other game parameters.
    - core: Includes the core game logic about level changes and others.
    - entity: Holds classes representing game entities, such as characters and objects(projectiles here).
    - exceptions: Custom exceptions for error and Exceptions.
    - logic: Some game's behavior to destroy actors.
    - ui: The user interface of the game. Like win image and other images.
    - utils: Utility classes ,helper methods used across the project.
- Add Factory Design Pattern
  - Add `LevelFactory` , which uses factory design pattern. In order to make instaces of levels. By using this design pattern, we can make the logic more clearly while creating instance about levels.
- Change `LevelParent`
  - I rewrited method `checkIfGameOver` and added `winLevel` method, in orde to extract the same logic, avoiding child class override the same logic again and again.

### Day4 (2024/11/15)

- I mainly refactored the image handling logic.
  - I found that many constants representing image positions and sizes were scattered across various files in the project, which made it difficult to understand the key logic.
  - I decided to extract part of the logic. 
  - I created `ImageFactory` and `ImageUtil` files and rewrote `WinImage`, `GameOverImage`, and other related classes.
- Thoughts on using Git
  - After using Git for a few days, I realized that my past branch names weren't very effective.
  - From now on, I will categorize my changes into types like "refactor," "function," etc., and include these in my branch names as a way to communicate the purpose of the changes. This will help me manage my project versions more effectively.


### Day5 (2024/11/17)

- I created a JUnit test class for the first time.
  - You can check the work [here](https://github.com/hcyzz1/CW2024/pull/4/files).
  - I wrote the `ImageUtilTest` class for the `ImageUtil` utility. 
  - Through this process, I learned how to write and test JUnit unit tests in a JavaFX environment. 
  - Compared to standard JUnit tests, working within the JavaFX framework requires additional steps when interacting with graphical interface components. Include:
    1. Opening the relevant package permissions in `module-info.java` when necessary.
    2. Adding related dependencies, such as `org.testfx`, to the `pom.xml`.
    3. Extending the test class from `import org.testfx.framework.junit5.ApplicationTest`, which allows us to launch the relevant GUI and perform unit tests involving graphical interface components.
  - The content I have written may not be entirely necessary for the overall maintenance of the project, but it allowed me to learn the basics of writing JUnit tests. I might create additional JUnit tests in the future as needed.

### Day6 (2024/11/18)

- Simply add new Level
  - I've simply added a space-themed background for Level 2. Now the game is like: 
    - Level 1: Defeat 10 enemies (Original Level1)
    - Level 2: Defeat 20 enemies
    - Level 3: Defeat the boss(Original Level3)

### Day7 (2024/11/19)

- Create ImageConstants.java
  - I have extracted the resource path of the entire project into a constant, `ImageConstants.IMAGE_ROOT_PATH`. This helps prevent potential issues, such as errors caused by typos, when repeatedly referencing the same path.
- Refactory Projectile.java and its children class.
  - Remove method`updateActors()`, because its function is too similar to method `updatePostion()`. So we just use one.
  - Extract and implement method `updatePostion()` in Parent Class. The projectile only moves in a simple way. So the method is better implemented in Parent Class instead of in Child Class.

- Some problems when I use git.

  - Today, while using Git, I found that if a single commit includes both renaming files and changing their content, a lot of formatting details might appear when checking the changes. This makes it harder to compare branches and merge them later using GitHub's tools.

    To make my commit history clearer, I will commit file renaming separately from other changes. This will help make my changes easier to understand.


### Day8 (2024/11/20)

- Improved the use of Git
  - Today, I made sure to separate file renaming from file content changes when making commits. As I expected, this gave me much clearer results when comparing branches.
- Optimized constant information in `LevelParent`
  - I extracted page-related information because the size of game's pages rarely change. This avoids passing too many parameters between classes, improving the readability of the project code.
- I extracted the user keyboard interaction operations from `LevelParent` into a separate class `UserInputHandle`.
  - By separating individual functionalities into dedicated classes, I reduced the coupling between classes.
  - This makes future optimizations of user interaction much safer and easier to implement.

- I moved the `instantiateLevel` and `initializeFriendlyUnits` methods into `LevelParent`.
  - These methods are currently implemented identically across all levels, so I extracted them into the parent class to improve code reusability.

### Day9 (2024/11/21)

- Added interaction that allows the user to move left and right.
  - https://github.com/hcyzz1/CW2024/pull/8
- Fixed the boss's shield display and shield functionality.
  - https://github.com/hcyzz1/CW2024/pull/9
- Reorganized the package structure and updated all the comments used for generating JavaDoc.
- Added projectile and projectile collision engine.
  - https://github.com/hcyzz1/CW2024/pull/10

### Day10 (2024/11/22)

- Added image processing features. When processing images, we automatically remove the transparent background and use a new image as the actual object size.

  This is an improvement I'm proud of. In this way, we can use images with transparent backgrounds more flexibly.

  - https://github.com/hcyzz1/CW2024/pull/11/files

- JavaDoc

  - I tried using the recommended `gh-pages` branch and setup on GitHub to create a page for browsing the JavaDoc! It's really cool!ßß
    - https://hcyzz1.github.io/CW2024/
  - I encountered some errors while generating the JavaDoc, but after running the command `mvn clean package source:jar javadoc:jar -Peclipse-release -DskipTests -Dpmd.skip=true -Dcheckstyle.skip=true`, it generated the JavaDoc correctly.


### Day11 (2024/12/04)
- I Display the number of kills added.
- I Switching between levels, adding and loading images.
- Add multiple layers of bullet attributes to the aircraft.
- Add a fourth level and a new enemy plane and boss.
- Fix some bugs during the testing process.

### Day12 (2024/12/08)

- **Integrated Audio Features for Immersive Gameplay**
  - **Background Music**: Added background music that loops indefinitely during gameplay to enhance the immersive experience.
    - **Method**: `MusicUtil.playBackGroundSound()`
  - **Shooting Sound Effects**: Implemented shooting sound effects, triggered whenever the player fires a projectile.
    - **Method**: `MusicUtil.playShootSound()`
    - **Integration**:
      - Added a call to `MusicUtil.playShootSound()` in the `UserInputHandle` class inside the `if (kc == KeyCode.SPACE)` block to ensure shooting sounds play when the space bar is pressed.
  - **Explosion Sound Effects**: Included explosion sound effects that play when any plane (enemy or player) is destroyed.
    - **Method**: `MusicUtil.playExplosionSound()`
    - **Integration**:
      - Added a call to `MusicUtil.playExplosionSound()` in the `Plane.takeDamage()` method, ensuring the sound plays when the plane's health reaches zero and it is destroyed.

- **Enhanced Class Interactions**
  - Integrated sound effect playback into:
    - **`UserInputHandle`**: Shooting sound effect on spacebar press.
    - **`Plane.takeDamage()`**: Explosion sound effect when a plane is destroyed.
  - Updated **`LevelParent`** class to handle:
    - Explosion visual effects for destroyed planes.
    - Playback of explosion sound via `MusicUtil.playExplosionSound()`.
    - Power-up item drops after a plane is destroyed in the `removeDestroyedActors()` method.

- **Improved User Feedback**
  - Players now receive auditory and visual feedback for key interactions:
    - Shooting projectiles.
    - Destroying planes, with explosion sounds and visual effects.
  - Added item drops (e.g., power-ups) dynamically with explosion effects, enriching gameplay.

- **Optimized Audio Control**
  - Centralized audio logic in the `MusicUtil` utility class:
    - Background music: `MusicUtil.playBackGroundSound()`
    - Shooting sound: `MusicUtil.playShootSound()`
    - Explosion sound: `MusicUtil.playExplosionSound()`
  - Allows global toggling of sound settings (`isBackGroundPlaying`, `isShootPlaying`, `playExplosionSound`).

- **Overall Benefits**
  - Enhanced immersion with dynamic background music and sound effects.
  - Clearer and modular code by centralizing audio logic in `MusicUtil`.
  - More engaging gameplay with interactive sound, visual effects, and item drops.


## Day 13 (2024/12/09)

- **Implemented Power-Up System for Enhanced Gameplay**
  - **Random Power-Up Drops**: Introduced a mechanism where enemy planes drop power-ups (Health or Attack Speed) upon destruction.
    - **Method**: `PowerUpManager.dropPowerUp(double x, double y)`
    - **Integration**:
      - Integrated into the `removeDestroyedActors()` method of the `LevelParent` class, allowing for power-up generation at the location of destroyed enemy planes.

  - **Health Power-Up**: Added a `HeartPowerUp` class that increases the player’s health when collected.
    - **Method**: `Plane.addHealth()`
    - **Integration**:
      - When the player’s plane intersects with a `HeartPowerUp`, the player’s health is increased, enhancing survivability.

  - **Attack Speed Power-Up**: Implemented an `AttackSpeedPowerUp` class that upgrades the player's projectile level.
    - **Method**: `User.increaseProjectileLevel()`
    - **Integration**:
      - When the player’s plane collects an `AttackSpeedPowerUp`, it triggers an increase in projectile level, allowing for more powerful attacks.

- **Enhanced Interaction with Power-Ups**
  - Integrated power-up collection into:
    - **`handleItem()`**: This method checks for collisions between the player's plane and power-ups, applying the effects based on the type of power-up collected.
  - Updated **`LevelParent`** class to manage interactions with power-ups during gameplay.

- **Improved Projectile Mechanics**
  - **Projectile Level Enhancements**: Adjusted projectile firing logic based on power-up collection, allowing for varying levels of projectile fire.
    - **Method**: `UserPlane.fireProjectile()`
    - **Integration**:
      - Depending on the `currentProjectileLevel`, the player can fire one, two, or three projectiles simultaneously, introducing strategic depth to combat.

- **Visual Feedback for Power-Ups**
  - Implemented visual indicators for power-up drops, enhancing player awareness of available power-ups.
    - Used explosion effects to indicate where power-ups drop, improving overall gameplay experience.

- **Overall Benefits**
  - The addition of a power-up system adds strategic elements to gameplay, encouraging players to actively seek out and collect items.
  - Improved player feedback through both auditory and visual cues during power-up interactions.
  - Enhanced combat dynamics with upgraded projectile mechanics, making battles more engaging and varied.
  - 
## Day 14 (2024/12/10)

- **Implemented Boss Transition and Health Display in Level Four**
  - **Boss Plane Management**: Introduced a system to manage two bosses, `BossPlane` and `BossPlane1`, with a transition mechanism.
    - **Method**: `spawnEnemyUnits()`
    - **Integration**:
      - The first boss (`BossPlane`) is spawned at the start, and upon its destruction, the second boss (`BossPlane1`) is activated.
      - Utilized a flag `bossPlane1Spawned` to ensure that `BossPlane1` is only spawned once after the first boss is defeated.

  - **Health Bar Display**: Added health bars for both bosses to provide visual feedback on their health status.
    - **Method**: `addHealthBarForFirstBoss()`, `addHealthBarForSecondBoss()`
    - **Integration**:
      - Health bars are added to the root view for both bosses, updating dynamically based on their current health.

- **Enhanced Game Flow with Boss Transitions**
  - Integrated the boss spawning logic in the `spawnEnemyUnits()` method, which checks if the first boss is destroyed before spawning the second boss.
  - Used `Platform.runLater()` to ensure that UI updates are performed on the JavaFX application thread.

- **Improved Boss Interaction and Visibility**
  - **Shield View Management**: Added visual indicators (shields) for both bosses to show their protective states.
    - **Method**: `addShieldViewForFirstBoss()`, `addShieldViewForSecondBoss()`
    - **Integration**:
      - Shields are displayed on the screen and updated based on the boss states.

## Day 15 (2024/12/11)

-I tried to use visual paradigm to draw the class diagram , and it worked.
-I tried to make the introduce video , but it not finished yet.

- **Overall Benefits**
  - Enhanced gameplay experience through strategic boss encounters, requiring players to adapt to different boss behaviors.
  - Improved player feedback via health bars, making it clear how much health each boss has left.
  - Introduced a dynamic game environment with a clear progression from one boss to another, adding depth to level design.
- 
- JavaDoc

  - I tried using the recommended `gh-pages` branch and setup on GitHub to create a page for browsing the JavaDoc! It's really cool!ßß
    - https://hcyzz1.github.io/CW2024/
  - I encountered some errors while generating the JavaDoc, but after running the command `mvn clean package source:jar javadoc:jar -Peclipse-release -DskipTests -Dpmd.skip=true -Dcheckstyle.skip=true`, it generated the JavaDoc correctly.
