###Part 1

1. What about your API/design is intended to be flexible?

	The front end has an interface called FrontEndModule. Each module, e.g. `TurtleDisplay`, `VarDisplay` implements the interface. Thus the moduels are like 'plug-in's. We can thus add a variety of new modules without any difficulty. As long as our assumption in the interface that front end modules show data and returns results of user interactions.
	
	
2. How is your API/design encapsulating your implementation decisions?

	The front end only has the `GUI` class visible to `Control`. Control only interacts with `GUI`. `Control` tells `GUI` what to do and the implementation of such orders are entirely up to the encapsulated classes on the front end.
	
	
3. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

	If the user enters an invalid command, the command is passed back to Model and Model checks that there is an exception and throws it. This excption is passed up to Control, where the exception is caught by asking front end to show the error. How the error is shown is up to front end's implementation.
	
4. Why do you think your API/design is good (also define what your measure of good is)?

	Good means flexible, and each method/class does its own thing and the total work is divided equally among different classes/methods. It also means the methods are abstracted enough so that others can build tons of things based on the APIs.
	
	My API is good in that it is flexible, i.e. new functionalities only need to implement the APIs. It is also abstracted enough because the API is based on the basic purpose of front end stuff.
	

###Part 2
1. How do you think Design Patterns are currently represented in the design or could be used to help improve the design?

	Mediator. Our `Main` class works like a middle man and a manager. Our model and view classes talk to the `Main` class in order to accomplish something that it cannot do. This helps encapsulate the implementation irrelavant to a part's job.
	
	Factory. Since there are many data objects for each front end module, it will be easier to give the work to a factory class, so that the back end does not have to use tons of lines just to determie which data object to create.

2. How do you think the "advanced" Java features will help you implement your design?

	Reflection. This is a handy tool for use for the factory class.
	
	Observable. For some modules on the front end, they are merely to display data. Observable makes the implementation much easier.

3. What feature/design problem are you most excited to work on?
	The `ExecutionEngine` class on the back end. It works as the portal from Control to Model. This is where our program implements the encapsulated 

4. What feature/design problem are you most worried about working on?

	The control classes. Since there are a lot of 'communication' going on in the program, I am worried that the communication goes wonky. It is like the wiring in a complicated electrical component.

5. Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).

	1. User enters
	```
	Define fd50
	as fd 50
	```
	
		What is stored as the command in command history?	
	2. User enters
	
		```
			REAPEAT 5[
				FD A=A+1
			]
		```
		How should the history, variable and turtle display updated at the same time? What is stored as the command in command history? The for loop, or 5 `FD A=A+1`?
	
	3. User clicks the variable display and changes the value of `x` to 6
	
		How should this be implemented? Should `VariableDisplay` generate a object signaling assinging a value to a variable, or should this be abstracted as the console being inputted `x=6`?
		
	5.  User chooses the language from English to German
	
		The button reacts to this event. GUI calls PrefetenceSetter to change the language to German. Now, when front end modules wrap RawCommand, they put the language variable to be German, instead of English. Language is an instance variable stored in GUI.
		
	6. User enters an illegal argument “true” to command: “sum 2 false”. Throws errors.
	
		The command gets passed in the usual way back to model. PrimitiveOpExecuter checks the arguments of the “sum” and sees that one of them is actually a boolean. It throws an IllegalArgumentException.
		
		The exception is passed back to Main. Where the exception is caught. Main generates a FrontEndData object and calls GUI.showError(FrontEndData error).
		
		GUI then shows the error.