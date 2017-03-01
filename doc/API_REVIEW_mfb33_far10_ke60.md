#API_REVIEW_mfb33_far10_ke60.md
 - Maddie Briere - mfb33
 - Faith Rodriguez - far10
 - Kris Elbert - ke60
 
### Part 1
 
 Our front end is designed to be flexible with adding in new languages using property files so users, adding different nodes to display different aspects, 
 
 The front and back ends are well encapsulated. They do not touch each other and only interact through the Command. They do not see each other or modify each other directly. The backend recieves state information and commends from the front end view through the controller and then modifies its model and tells the command which then gets the front end to update the view.
 The front end does not know how the commands are interpreted or how math is done; that is all encapsulated in the back end.
 
 Potential errors include invalid or insufficient arguments (InvalidArgumentException), unknown commands that are not in the resource file for that language (UnknownCommandException), and unbalanced expressions if there is, say, a dangling open bracket (UnbalancedExpressionException). These errors will be handled by passing them to the front end to visually alert the user and describe the issue with a dialog box.
 	
 Our API is good in that it balances methods that are useful for users while protecting specific implementation details. The back end and front end are well separate. 
 
### Part 2
 We are using Factory to create commands in the backend as an abstract class. These commands are further organized into more abstract classes such as TurtleQueries and MathCommands that extend the command class. Each command then will have its own class that extends one of these higher groups, but all remain executable. This also uses the Command design pattern.
We will use an interpretor to translate between the raw commands, the command titles in the resource files, and the command objects executable by the back end.
We will have a controller class as part of our model-view-controller design that will act as a Mediator between the Model (back end) and View (front end) so that they need not know of the other's existence.
 
 Reflection can be used to translate commands in strings from the front end to functions in the backend. Generic types 
Regular expressions can store syntax

I'm excited to work on the command builder and the mathematics that go along with modifying the turtle's position.
I am a little worried about how flexible our back end command builder will be and if it will allow users to define their own commands.

User types in "fd 50" into the console and the turtle moves 50 pixels, leaving a trail.
User inputs fd which gets sent as a RawCommand object by the GUI console and interpreted by the command class. Using the getType() method, the back end recognizes this as a forward command that will add fifty pixels to the turtle's position in whatever direction its head is facing. 
This new position will be passed from the back end to the Controller and translated into the view.

User inputs "fd fd 50" and sees the turtle move forward 
The first fd 50 is treated as above and the backend will have it return 50, as in 50 pixels as the argument for the first fd action.
Thus through recursion, an expression tree is created and the leaves are executed first, with 50 returning a constant of 50 as the argument for the second fd, which will then return 50 for the first forward to run.

User inputs "klajewrlkawn et 5270 93"
The Controller sees that there is no corresponding command in the resource file and pops up an error in the form of a dialog box.

User enters a number into a text field that corresponds to a variable in order to update its value
The variable display's listener detects an event and creates a rawCommand for the Controller. The controller then passes the new value of the variable to the back end.

User changes the pen color using the color picker
The pen color Color is now updated using the color picker's listener response. This is contained in the front end.


 