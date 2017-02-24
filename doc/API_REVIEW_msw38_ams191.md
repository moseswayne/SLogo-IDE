#Team 4 API Review (msw38)

### Anngelyque Stevenson - ams191
### Moses Wayne - msw38

##Part 1

####What about your API/design is intended to be flexible?
In the front end, interactions with the user should be flexible. There will initially be generic cases that can be built upon such as having the user interact with the screen instead of inputting commands. The UI class should be very extendable for the backend.
In the back end, accepting new commands and additional functions should keep it flexible.
####How is your API/design encapsulating your implementation decisions?
The team broke their design into ModelView and ModelAction. Never of these views understand what the others are doing as they are only concerned with what they are being inputed. For example, backend doesn't care what front end sends it such as turtle image and color, etc.
####What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
Incorrect text commands and changing variable throw errors in the back end that are caught by the front. For example, the backend reads in a property file the reads in commands are creates a hashmap of phrases. New commands add entries to hashmap. If the command is not in the hashmap, an error should be thrown.
####Why do you think your API/design is good (also define what your measure of good is)?
Good is everything is on a need to know basis yet very extendable. Command interpreter for example, should never have to be updated for example because there are no hard coded statements and it's very generic. 


##Part 2

####How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
The program uses a modelView controller which means that the front end doesn't communicate at all with the backend. Factories are used for implementing classes. Use iterators objects to loop through commands and command objects when multiple need to be read.
####How do you think the "advanced" Java features will help you implement your design?
Reflection will help maintain generic design so classes won't have to be changed. Enumeration will be used to implement commands with similar design/ control flow.
####What feature/design problem are you most excited to work on?
Moses is excited to work on making commands as generic as possible. It's a big problem to solve in order to keep the program most flexible.
####What feature/design problem are you most worried about working on?
He was to figure out a way to work around when the user creates new commands, how will he go about saving those without altering much code.
####Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
1. User tries to change pen color in the middle of the simulation
	- Line will change color as soon as it is switched (from that point forward)
2. The language is switched while variables are already defined.
	- The side panel with commands will be switched to the new translation while parsing.
3. User tries to open up an invalid slogo file.
	- Will be resolved by error message by checking stub at end of file.
4. User tries to change line thickness mid-draw.
	- This will be handled by the front end.
5. User wants to save current status of slogo screen.
	- Slogo output file will be created with correct stub ending.