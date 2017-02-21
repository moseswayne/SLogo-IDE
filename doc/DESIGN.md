# DESIGN.md
## SLogo Team 4:
- Kris Elbert - ke60
- Justin Yang - jy183
- Moses Wayne - msw38
- Yuxiang He - yh129

### Introduction
The problem that this program intends to solve is the creation of a development environment for an interpreted language called SLogo, a simpler variant of the language Logo. The language is meant to be run on each expression, and the code that is executed is able to accomplish numerous features, such as drawing to a screen using an interactive turtle, complete arithmetic and boolean operations, and defined user commands. The integrated development environment should also be able to take in “logo” files as a series of commands to be executed.

The primary design goals of this project are to maintain flexibility in the addition of new text commands, different methods of interaction with the IDE, and controls to alter the specifications of the IDE itself. In this way, the modularity of these components of the code in the program must be extensively focused on to ensure extensibility. The primary architecture of the design is based around this principle of extensibility. The classes for various text commands would be an “open” part of the program in that adding new commands would require the implementation of the text command interface to extend the functionality of the program. Conversely, a part of the program that would be “closed” is the interpreter of the commands as it would use a table based approach to decipher commands. Other open parts of the program are the control panel and the front end display.

At a high level, the architecture of the program is split between a Model, a View, and a Controller. The Model of the program performs all of the computational functions of the SLogo program ranging from simple arithmetic and boolean operations to turtle commands to user definitions from specific function calls and assignments. The View of the program is the visualization portion of the program. It constitutes the visualization of typed commands and their results, the movement and pen lines of the turtle, the history of declared variables and functions, and the general GUI layout of the program. To interface between these two sides of the program is the Controller, which passes information back and forth between the two sides of the program allowing the SLogo program to execute functions in the back end and display their results in the front end.

In keeping these three sections of the program separate, each portion is only required to do its portion of work and both the modularity and extensibility of the program benefit, which is in line with the overall design goals of the project.

### Design Overview
This program follows the Control-Model-View model. Our code is divided into the three packages.

On the front end view package, there is the main GUI class that contains all other front-end classes like Console, TurtleDisplay and VariableDisplay. All such classes implement the interface FrontEndModule. This GUI class serves as the connection port of the front-end classes. Main, or the control class, calls APIs in the GUI class to control the elements on the front end, and GUI then calls the specific FrontEndModule interface API updateDisplayedData(FrontEndData) for each front end module. Each modules gets its own implementation of the abstract class FrontEndData. This is such that each module can have its own object in representing the data it needs for display.

FrontEndModule classes on the front end also pass information to the  Main class as they receive input from the user. This is done through the getRawCommand() API in GUI class. Front end modules need to implement the getUserInteractionResult() API from the interface. This information is first packed into RawCommand object and passed to Main. Main then calls RawCommandInterpreter to interpret the raw command, and then passes the command in a form of a Command object to ExecutionEngine though execute() API.

Once the back end receives the command object ExecutionEngine calls the API getType() on the CommandObject. It figures out the type of the command and passes the command to the corresponding ModelCommandExecuter that is responsible for executing the type of the command. This is done through the execute() API in ModelCommandExecuter interface.

There are classes created for storing important information on the back end, such as TurtleSimulator, VariableSpace and CommandHistory.

### User Interface
The user can load a file of executable commands or type them directly into a console. The results of these commands will be shown in the display, where there will be a turtle that can draw lines, and in the console itself, where the returned values are printed. Color pickers and combo boxes that drop down from a control panel strip at the top of the program window allow the user to choose line color, command language, and background color. The user can also modify variable values in the variable value sidebar next to the console and display.
Erroneous situations can arise when the user inputs an incorrect command (misspelled or nonexistent). A dialog box pops up to alert the user that the command that was entered was an invalid command and suggests the user be redirected to a list of valid commands in the SLogo language. The same occurs for when an invalid input file is loaded, except the exception thrown details to the specific line in the input file that contains the invalid command. Another situation that involves error checking is the change of a variable’s value in the side panel of the UI. If the value is incompatible with the type of the data (typing “hello” in the value field of a double), an exception is thrown and a dialog box is returned to the user preventing the change from occurring. 

In terms of the actual appearance of the GUI, the program’s user interface is composed of the main window filled with different sections. The top of the screen houses a control panel where various settings of the application can be set, such as pen color or application language. In the middle of the screen is the application display, showing the movements of the turtle and the lines that it lays down. To the side of the display is the variable window housing the values of the variables and definitions that have been declared by the user. Beneath these windows is the console, a text based window where the user is able to input commands and see the return values.

Below is a mockup drawing of the user interface for the SLogo application:
![Scanned in mockup of the User Interface design for our application] (SLogo_UI.jpg "SLogo Application User Interface Example Design")

### API Details
- Front end:
	-  GUI classes (View):
		- GUI: This class is the gateway to the front end modules. Main only communicates with GUI through its APIs. Main only needs to care about a job is being done, but not how it is done. GUI acts as a layer of abstraction, it hides the front end implementations from Main and other outsider classes.
		- Void show(FrontEndData data): Determines the type of the front end data and passes the data to the module responsible for visualizing the data, calls the updateDisplayedData(FrontEndData) API  on the FrontEndModule interface.
		- RawCommand getRawCommand(): Returns the raw command generated from a user’s interaction with the front end.
		- Void showError(): Shows the user an error. The visualization can be dependent on the type of error, e.g. show a pop-up window when there is an illegal argument to a command, or to print the message to the console. Since GUI has access to all front end modules, this has a lot of flexibility in terms of how to show an error 
	- Abstract class FrontEndData: The wrapper class that contains information required for each front-end module when they are displaying information
	- Interface FrontEndModule: Mimics the functionality of a front end module
		- Displays information
		- Allows the user to interact with the program
		- Void updateDisplayedData(FrontEndData): Update the module’s displayed data 
		- RawCommand getUserInteractionResult(): Returns the user’s input on the module as a RawCommand
	- _The following classes are meant for implementing the front-end modules. They are somewhat like plugins as they all plug into the same API “socket”._
	- _Some setters and getters specific for each class are also needed as there are requirements for the user changing the front end appearance of the turtle display._
	- Console implements FrontEndModule
		- (Collection<String> cmdHistory): Stores the previous commands in terms of the strings
		- (Private String getPrevCommand() ): Internal API that helps implement showing the user previous commands
		- RawCommand getUserInteractionResult(): Returns the inputted text as a RawCommand object to be passed to model for explanation and execution
		- Void updateDisplayedData(ConsoleData): Prints a ConsoleData on the console, e.g. printing the result of a command
	- ConsoleData extends FrontEndData: Data to be displayed on the console, e.g. the result of a calculation from a command
	- (private Control Panel): JavaFX Buttons, sliders etc
	- TurtleDisplay implements FrontEndModule
		- Void updateDisplayedData(TurtleDispData): Move the turtle img to a position specified by pos, and draw a trace on the screen if penUp
		- RawCommand getUserInteractionResult(): Not really a useful method for now, but should requirements change, we can allow the user to actively interact with the turtle on the front end using mouse instead of typing commands on the console.
	- TurtleDispData extends FrontEndData: Data to be displayed on the TurtleDisplay, e.g. the position of the turtle, penUp or penDown
	- VariableDisplay implements FrontEndModule
		- Void updateDisplayedData(VarDispData): Update the variables to the vars specified by VarDispData
		- RawCommand getUserInteractionResult(): API for implementing the user setting a variable’s value by clicking and setting it directly on var display.
	- VarDispData extends FrontEndData: Wrapper that contains information about variables to be displayed by VariableDisplay
	- CommandDisplay extends FrontEndModule
		- Void updateDisplayedData(CmdDispData): Update the commands to the vars specified by CmdDispData
		- RawCommand getUserInteractionResult(): API for implementing the user executing a command by clicking it directly on cmd display.
	- CmdDispData extends FrontEndData: Wrapper that contains information about commands to be displayed by CommandDisplay
	- This class acts as the setup class for attributes of the game:
	- PreferenceSetter: helps the front end to change its (mostly visual) properties
		- Void updatePenColor(String color): Sets the color of the trace left by the turtle’s pen
		- Void changeTurtleImage(String imgFile): Sets the turtle’s img to the file specified by imgFile
		- Void changeLanguage(String newLang): Changes the language of the command to newLang
		- String getLanguage(): Returns the current language used for the command
		- Image getTurtleImage(): Getter for turtle image
		- Color getPenColor(): Getter for the pen color

- Controller Classes:
	- RawCommand Interpreter
		- Command interpret(RawCommand rawCmd)
			- Interprets a raw command and returns a Command object to be processed by CommandExecutor
			- It checks if there exists a corresponding command if not it throws an error and front end receives a FrontEndData object signalling an error
	- ViewController: Class that helps Main to manipulate view class
		- updateTurtleDisplay()
		- printToConsole()
		- changeVarDisplay()
	- Main (application): Pretty self explanatory. Main calls everything else and is the “manager” of the whole program.

_This is where the program catches the exceptions thrown by the  back-end and gives the front end orders to display them._

- Model Classes:
	- ExecutionEngine
		- Public APIs for the back end are all in this class. It receives and redirects Command objects to the right ModelCommandExecuter for execution. In the process Main only sees ModelCommandExecuter executing a command, but does not see the execution detail. This is an abstraction so we can have lots of freedom in implementing the execution process.
		- It also checks if a command is valid (for error checking) and throws exceptions (as seen from Main. Its subclasses are the ones implementing error checking)
		- FrontEndData execute(cmdObject)
			- Main class calls this method in ExecutionEngine to pass the command object to the back end. In this method, ExecutionEngine figures out which kind of command object is it by calling CommandObject’s getType() API.  ExecutionEngine then passes the CommandObject to the corresponding ModelCommandExecuter by calling the ModelCommandExecuter’s execute() API 
	- The following methods and classes are protected, used for Model’s implementation. Main only sees ExecutionEngine executing a command but not the following classes and APIs
	- We have a class specifically for each kind of operation. This divides the tasks up and makes code easier to maintain.
	- All executers share the same kind of job:
		- to execute a command, and
		- Checks if a command is valid (for error checking) and throws exceptions
	- There can be other works that we have not thought about yet, but we can always add them to the interface.
	- Interface ModelCommandExecuter
		- Throws InvalidArgumentException
		- Throws UnknowCommandException
		- FrontEndData execute(Command cmdObject)
			- Execute a command, and returns the result in the form of a FrontEndData object
			- Boolean isValidCommand(Command cmdObject)
				- Returns true if command is valid
		- Implemented by
			- PrimitiveOpExecuter: Executes all commands related to primitive types, e.g. int, float, boolean
			- TurtleOpExecuter: Executes all commands related to the turtle
			- … and more should new kinds of commands arise
	- TurtleSimulator: Stores all data related to the turtle
	- VariableSpace: Stores all data related to variables that the user has created
		- getVariable(String name)
		- clearAll(): Clears all current variable
		- clear(String varName): Clears the variable specified by varName
	- CommandHistory: Stores all commands execute
- Utility Class:
	- Object
	- TurtlePosition
		- X
		- Y
		- Angle
	- Variable
		- Variable data
		- Type
	- Command
		- getCmdType()
		- hasNext()
		- getNext()
	- RawCommand
		- hasNext()
		- getNext()
	- SLogo Exception
		- InvalidArgumentException
		- UnknowCommandException
	- FileParser
		- RawCommand parse(String/File source)

### API Example Code
- **The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.**
	- Console wraps the command “fd 50” into a RawCommand object.
	- Each step Main calls getRawCommand() on GUI, which calls getUserInteractionResult() on Console. Thus, the RawCommand “fd 50” is passed to Main, which calls RawCommandInterpreter to interpret the raw command. The returned interpreted Command object is then passed to ExecutionEngine.execute() API.
	- ExecutionEngine sees that the Command object is of type TurtleOp, it passes this to TurtleOpExecuter to execute by using the TurtleSimulator to calculate the new position of the turtle, and returns the FrontEndData which is the new position back to Main. Also, this command is stored in CommandHistory on the back end.
	- Main then calls GUI.show(FrontEndData data) API. GUI sees that this data is for TurtleDisplay, it then calls TurtleDisplay.updateDisplayedData(FrontEndData data) to show the result of the execution of the command, which is the turtle moving to its new position.
- **User clicks variable display on the screen, and sets the value of variable “x” from 2 to 6**
	- Event listener on variable display detects the event, and VariableDisplay wraps the command “x=6” into a RawCommand.
	- Each step Main calls getRawCommand() on GUI, which calls getUserInteractionResult() on VariableDisplay. Thus, the RawCommand “x=6” is passed to Main, which calls RawCommandInterpreter to interpret the raw command. The returned interpreted Command object is then passed to ExecutionEngine.execute() API.
	- ExecutionEngine sees that the Command object is of type PrimitiveOp, it passes this to PrimitiveOpExecuter to execute and returns the FrontEndData which is now “x : 6” back to Main. Also, this command is stored in CommandHistory on the back end.
	- Main then calls GUI.show(FrontEndData data) API. GUI sees that this data is for VarDisplay, it then calls VarDisplay.updateDisplayedData(FrontEndData data) to show the result of the execution of the command.
- **User chooses background color to blue**
	- The button reacts to this event. GUI calls PrefetenceSetter to change the background color to Blue. It then calls the API on turtle display changeBackgroundColor(Color color) with color=Color.Red. The background color is then set to blue.
- **User clicks to set pen color to red**
	- The button reacts to this event. GUI calls PrefetenceSetter to change the color to Red. It then calls the API on turtle display changePenColor(Color color) with colot=Color.Red. In future updateDisplayedData() calls on TurtleDisplay, TurtleDisplay draws red traces.
- **User chooses the language from English to German**
	- The button reacts to this event. GUI calls PrefetenceSetter to change the language to German. Now, when front end modules wrap RawCommand, they put the language variable to be German, instead of English. Language is an instance variable stored in GUI.
- **The user presses the up arrow while in the console, causing the previous command run in the console to appear in the new line**
	- Event handler registers the key press while in the console and the number of times that the up arrow key had been pressed. The console then calls a function in the controller with the current “value” of the key press (how many times the up arrow key has been pressed versus how many times the down arrow key has been pressed) as the parameter.
	- The controller sends this index to the command history class in the backend which returns the command in the history indexed to the parameter sent to it. This String is then sent to the console but not executed, allowing the user to “scroll” through previous commands.
- **The user types “sum 2 3”**
	- Console wraps the command “sum 2 3” into a RawCommand object. This object is passed into the RawCommandInterpreter to interpret the raw command. 
	- This command is then passed into the ExecutionEngine where its specific primitive operation is selected from a reflection after the execution engine pushes the command into the PrimitiveOpExecuter. The operation is done in its specific command class.
	- The command in its String form is passed into the command history stack so that it can be accessed in the future. The front end console has its updateDisplayData function called with the specific console data passed into the console for it to print.
	- When RawCommand is passed to RawCommandInterpreter, RawCommandInterpreter calls the API get Language() on RawCommand. It then uses the corresponding resource file to translate the command to the command that back end understands.
- **User enters an illegal argument “true” to command: “sum 1 true”. Throws errors.**
	- The command gets passed in the usual way back to PrimitiveOpExecuter. PrimitiveOpExecuter checks the arguments of the “sum” and sees that one of them is actually a boolean. It throws an IllegalArgumentException.
	- The exception is passed by ExecutionEngine back to Main. Where the exception is caught. Main generates a FrontEndData object and calls GUI.showError(FrontEndData error).
	- GUI then shows the error. How the error is shown is up to the implementation. Some examples: printing an error message on console,  popping up a window.
- **User enters a non-existent command: “abcdef 12 46”. Throws errors.**
	- The command gets passed in the usual way back to ExecutionEngine. ExecutionEngine sees that there is no such a command (it has no idea which executer to send this command to). It throws a NonExistentCommandException.
	- The exception is passed by ExecutionEngine back to Main. Where the exception is caught. Main generates a FrontEndData object and calls GUI.showError(FrontEndData error).
	- GUI then shows the error. How the error is shown is up to the implementation. Some examples: printing an error message on console,  popping up a window.

### Design Considerations
The first design our group considered was a simple frontend-backend model where the frontend classes pass user input to the backend classes, which then process the data and send updates to the frontend classes to display. However, we decided that this design would create too many dependencies between the frontend and backend, which would make our code un-reusable and difficult to extend to future use cases. For example, we wanted to create a command class to hold user input that would be passed between the frontend and backend. However, we realized that both the frontend and backend would have to be able to distinguish between the different types of commands, such as SLogo instructions and display updates from the backend.

To address this problem, we decided to use a model-view-controller architecture. Adding the controller eliminates the dependencies between view and model. In the example above, view would focus on updating the GUI through calls to its API and passing user input back to controller, and model would focus on updating the data it holds through API calls and sending state updates to controller. Controller would process the different types of commands and perform other tasks, such as creating a command interpreter instance to handle the SLogo instructions.

- Visualize the data on the back-end
- Allow the user to interact with the program

Thus the APIs on the front end module classes include 2 methods:

- Void updateDisplayedData(FrontEndData)
- RawCommand getUserInteractionResult()

These 2 methods are meant to modularize front-end classes, i.e. the classes are like plug-ins to the front end and they are plugged in through the front API.

### Team Responsibilities

- Moses 
	- Primary: Implementation of the model classes with emphasis on commands and command interpretation to maintain extensibility
	- Secondary: css formatting of the UI, utilities and utility classes
- Kris
	- Primary: Implementation of the model classes such as commands and the implementation of various data structures
	- Secondary: control panel organization, utilities and utility classes
- Yuxiang
	- Primary: Implementation of the view classes, such as console and exceptions
	- Secondary: data structures held within the front end of the project, controller classes
- Justin
	- Primary: Implementation of the view classes, such as display and variable space
	- Secondary: controller classes and data structure utilities

The various team roles are very collaborative and none of the specific roles are completely set in stone because of an intended emphasis on pair programming and teamwork to garner numerous perspectives to tackle various design issues. The team will work both in parallel and in unison to work on independent and integrated parts, respectively.