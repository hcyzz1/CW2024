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
### LevelLoadingException.java
- Location
  - src/main/java/com/example/demo/exceptions/LevelLoadingException.java
- Description
  - A custom exception for unified management of loading errors.
- Purpose
  - Handling all exceptions in one place keeps the code cleaner and more organized.
  - It helps in identifying and managing errors more easily.
### AlertUtil.java

- Location
  - src/main/java/com/example/demo/utils/AlertUtil.java
- Description
  - Unified management of Alert-related handling.
- Purpose
  - It's easier to maintain and update the logic about alert in one location.

### AppConstants.java

- Location
  - 
- Description
  - Extracted the game window settings and related variables into a const class.
- Purpose
  - The goal is to decouple the code, making each file easier to maintain and making it simpler to identify the related
    modification points when issues occur.

### xxxClass

- Location
- Description
- Purpose

### xxxClass

- Location
- Description
- Purpose

## Modified Java Classes
### module-info.java
- Location
  - ./CW2024/src/main/java/module-info.java
- Changes
  - Add `exports com.example.demo;`(After I renamed packages, <=>exports com.hcyzz1company.skybattle)
- Reason for change
  - Make sure all classes can be exported for JavaDoc.
### LevelParent.java
- Location
  - src/main/java/com/example/demo/LevelParent.java

- Changes
  - fix method `goToNextLevel` . 
    - I added the `levelChanging` variable to ensure thread safety.
    - Before entering a new level, I make sure to remove the observers for the old level and delete the entities related to the old level.
  - add method `stopCurrentLevelActivities`
    - Stop the loading of the timeline
    - Remove the observers.

- Reason for change
  - In order to prevent any potential infinite loops.
  - Make the game be able to load the next level correctly.


### ShieldImage.java

- Location
  - src/main/java/com/example/demo/ShieldImage.java
- Changes
  - I modified the path for finding the Shield image.
- Reason for change
  - The original image path had a `.png` extension, but the code was using a `.jpg` extension, which caused the exception.

### Controller.java

- Location
  - src/main/java/com/example/demo/controller/Controller.java
- Changes
  - Unified the exceptions with a try-catch block.
- Reason for change
  - In actual app development, it is difficult to handle and list every possible exception, so managing them in a unified way can make the code structure clearer. Later, when we can handle each specific case more thoroughly, we can add them back individually.

### Main.java

- Location
  - src/main/java/com/example/demo/controller/Main.java
- Changes
  - Unified the exceptions with a try-catch block.
- Reason for change
  - In actual app development, it is difficult to handle and list every possible exception, so managing them in a unified way can make the code structure clearer. Later, when we can handle each specific case more thoroughly, we can add them back individually.

### xxxClass

- Location
- Changes
- Reason for change

### xxxClass

- Location
- Changes
- Reason for change

### xxxClass

- Location
- Changes
- Reason for change

### xxxClass

- Location
- Changes
- Reason for change

### xxxClass

- Location
- Changes
- Reason for change

### xxxClass

- Location
- Changes
- Reason for change

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