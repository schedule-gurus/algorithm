# Schedule Builder Algorithm

This is the algorithm that takes course and produces different
schedules randomly.

##TASKS
- [ ] Connect/Retrieve from Data Base
- [X] Write Algorithm and generate correct results
- [ ] Rigorous Testing
- [ ] Generate calender style schedule. 
        *NOTE: Algorithm outputs list of SesssionID's and BitSets
## Running the algorithm

Import to IntelliJ and run the Driver file.

## Concept of Algorithm and Key Points
Each day is divided into 30 bits each (1 bit = 30 minutes). 
Start of day 0700 & End of day 2200

15 hour days * 60 minutes = 900 minutes --> 900 minutes / 30 minute bins = 30 bins * 5 days = 150 bins/bits total

     I made the course Object to take specficially these parameters
               ("CLASSNAME, SESSIONID, DAYSOFWEEK, CURRCAP, MAXCAP") OR
               ("CLASSNAME, SESSIONID, DAYSOFWEEK, CURRCAP, MAXCAP, RATEMYPROFESSOR")
               
     *NOTE courseTimes need to be added seperately as such: courseObj.setCourseTime(START,END)
     
 

## Break down of Algorithm
**Step 1: Connecting and Retrieving from the Data Base**

Connect to Data Base and retrieve information that is needed
*This needs to be implemented*

**Step 2: Creating course objects from data**

Make course objects of the classes and categorize them into subjects.
Look at comments carefully and **course times need to be added seperately by using 
`setCourseTime` function of the courses object.**

Eg. a subject can have multiple course offerings. The final format shoud be
`List<List<courses>>` where courses is an object.

List of subjects that have each course times in it.

**Step 3: Generate Schedules**
 
The algorithm chooses one course from each subject and attempts to build the algorithm.
In scheduler file, `buildSchedules` does this by asking for the list of all subjects and classes and **how many 
schedules it should make**. It will output which binary bits the schedule fits and its sessionID's. 

*Note:* When there is a collision, it restarts the process of trying to fit schedules in and if there 
is no possible matches, it will eventually reach 100 collisions and terminate.

**Step 4: Create a GUI schedule or connect to front end**
 
 Not Done. *This needs to be implemented*
 







## License
[Kevin and Schedule Gurus](kevincorvallis@gmail.com)