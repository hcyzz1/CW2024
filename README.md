# Main Work (According to the Course Requirements)
## Github Link
You can find the project repository on GitHub at the following link:
- [CW2024 GitHub Repository](https://github.com/hcyzz1/CW2024)
- If link doesn't work, use: https://github.com/hcyzz1/CW2024
## Compilation Instructions
### Prerequisites
To compile and run this project, make sure the following tools are installed
1. JDK (Java Development Kit) 19+
2. Apache Maven
3. JavaFX SDK
### Install Dependencies Using Maven
1. cd path/to/your/project
2. mvn install
### Start the Application 
1. Run **Main.main()** as Java Application in IDE

### If you want to see JavaDoc
- User Command `mvn javadoc:javadoc`
- Check `index.html` in path `\`
## Implemented and Working Properly
## Implemented but Not Working Properly
## Features Not Implemented
## New Java Classes
### [AppConstants.java]()

- Location
  - 
- Description
  - A new const class, save the game window settings and related variables here.
- Purpose
  - Decouple the code, make it easier to change the windows settings and title information in the future.

### [LevelUtil.java]()

- Location
  - 

- Description
  - An Util class to manage level information. Including finding the first level, finding the next level for the specific level and other functions.
- Purpose
  - Make the logic about level management in the same file.
  - It will be easier to add new levels later, and we can modify the relationships more clearly, avoiding any impact on the main logic.

### [LevelFactory.java]()

- Location
  - 
- Description
    - Used the Factory Design Pattern to manage level information.
    - Created a factory class to obtain the level instances.
- Purpose
    - It makes the code easier to maintain by organizing level creation in one place, keeping the code clean.
    - The factory reuses the same logic for creating levels, reducing repetition and mistakes.

### [ImageFactory.java]()

- Location
  - 
- Description
    - Used the Factory Design Pattern to create Image instances in the project.
- Purpose
    - Extensibility: The Factory pattern allows easy extension.
    
      Maybe we will need more ways to load pictures in the future. At that time, we just need to update this method.
    
    - Separation of Concerns: Keeps the creation logic separate from the rest of the codebase.

### [ImageUtil.java]()

- Location

  - 

- Description

  - A utility class providing methods for creating and displaying images.

- Purpose

  - Better Image Operations: 

    Organize image-related operations (such as resizing, positioning, and displaying) in a single class. The code becomes easier to maintain and extend.

  - Improving Code Readability: By extracting common operations, the business logic code becomes cleaner and easier to understand.

### [HeartImage.java]()

- Location
  - 
- Description
  - A class to organize Heart Image.
- Purpose
  - Extracted the image-related operations from `HeartDisplay.java`.
    This ensures that `HeartDisplay.java` mainly handles the logical relationships, making the code structure clearer and easier to understand.

### AlertUtil.java

- Location
    - src/main/java/com/example/demo/utils/AlertUtil.java
- Description
    - Unified management of Alert-related handling.
- Purpose
    - It's easier to maintain and update the logic about alert in one location.

- 

## Modified Java Classes

Each number represents a change point. 

I will explain the change points and the reasons for the changes, including the line numbers associated with the files.

### [module-info.java]()

1. I modified the information to be exported based on the new file directory structure after the refactor.

   - Location:

   - Reason:

     - Make sure all classes can be exported for JavaDoc.

     - Make sure the application can run successfully.

### [Main.java](URL)

1. Simplified exception handling.

     - Location:

     - Reason:  
       - I used a custom exception to better pinpoint errors. 
       - It will be easier to read the code after.


2. Moved some variables about settings into a new Constant file.

   - Location:

   - Reason: 
     
     - Move some constants that is not about the main logic into a new file. It will make the project easier to maintenance in the future. 
     
     - Also, when we want to change the settings, we can find the settings easier in the new file.


### [Controller.java]()

1. Split the logic for loading the first level and subsequent levels into a new file.
   - Location:
   - Reason:
     - Ensured that the Controller is only responsible for controlling level listeners and other parts related to the main logic.
     - Moved parts(logics about levels) that might change into new methods, making it easier to modify and extend in the future.

### [LevelParent.java]()

1. Fix method `goToNextLevel` .
   - Location: 
   - Reason:
     - I added the `levelChanging` variable to ensure thread safety.
     - Call the new method following `stopCurrentLevelActivities`, make sure the game be able to load the next level correctly.
2. Add method `stopCurrentLevelActivities`.
   - Location: 
   - Reason: 
     - In order to prevent any dead loops.
     - Before entering a new level, I make sure to remove the observers for the old level and delete the entities related
       to the old level.
3. I rewrited method `checkIfGameOver` and added `winLevel` method, in orde to extract the same logic, avoiding child class override the same logic again and again.
   - Location: 
   - Reason: 
     - Actually, the logic for completing or failing a level is quite similar, so I extracted them.
       - When the player fails, it’s because they received enough damage.
       - When a level is won, it’s either by advancing to the next level or winning the entire game.
       - Each level is allowed to define its own victory conditions, such as defeating enough enemies or defeating a boss, by override the `winLevel` method.
     - Subclasses only need to override the `winLevel` method！It's easier to use now!


### [ShieldImage.java]()

1. I modified the path for finding the Shield image.
   - Location: 
   - Reason: 
     - The original image path had a `.png` extension, but the code was using a `.jpg` extension, which caused the exception.

### [HeartDisplay.java]()

1. The image handling logic has been moved to a new file, `HeartImage.java`.
   - Location:
   - Reason:
     - Split the logic so that each class is responsible for a single function, making the code structure clearer.


### xxxClass

- Location
- Changes
- Reason for change

### Other Simple Changes

In this section, I will list some small changes I made. The changes and their reasons are straightforward, so I’ve grouped them together.

- [ShieldImage.java]()

- [WinImage.java]()

- [GameOverImage.java]()

  - To align with the refactored image handling approach, I rewrote the methods using the newly created `ImageUtil`.

  

## Unexpected Problems

### xxxProblem

- Date
- Problem Description
- How I resolved it

# OTHERS

## Work For Each Day

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