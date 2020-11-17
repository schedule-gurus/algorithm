
package scheduleGenerator.scheduleAlgorithm;

import scheduleGenerator.models.course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;


public class scheduler {


    public List<BitSet> schedules ;
    public List<List<Integer>> successfulSchedulesSID;                //0700->2200, M-F, 30 minute bins = 150 bins to represent the whole week


    public List<BitSet> buildSchedules(List<course> courseList, int schedulesDesired) throws Exception {
        //INPUT: LIST OF COURSE OBJECTS
        //OUTPUT: List of possible schedules in bitset format
        List<BitSet> schedules = new ArrayList<>();
        successfulSchedulesSID = new ArrayList<>();



        //BUILDING SCHEDULES
        while (schedules.size() < schedulesDesired){
            BitSet tempSchedule = new BitSet(150);              //creating a empty schedule to fill later
            List<Integer> tempSIDArray = new ArrayList<>();
            boolean collision = false;
            for (scheduleGenerator.models.course course : courseList) {
                //Looping through course list and adding to tempshcedule
                try {
                    tempSchedule = addCoursetoSchedule(tempSchedule, tempSIDArray, course);
                } catch (IOException e) {
                    //Collision so we need to restart the above loop. possibly ask JaeHyung
                    collision = true;
                }
            }
            if(!collision){
                //add courses to final list

                schedules.add(tempSchedule);
                successfulSchedulesSID.add(tempSIDArray);
            }

            // Continue...


        }
        return schedules;

    }



    public BitSet addCoursetoSchedule(BitSet schedule,  List<Integer> SIDarray, course classtoAdd) throws Exception {            //TODO: Might need to change Bitset -> Object course. Maybe return a course
        int courseBits = classtoAdd.getCourseTime().cardinality();                //Cardinality essentially adds all the positive bits and outputs integer eg. 0101 => 2
        int scheduleBits = schedule.cardinality();                          // Doing this to later compare with the result
        BitSet temp = new BitSet(150);
        temp.or(schedule);        //Copying into temp
        temp.or(classtoAdd.getCourseTime());
        int tempBits = temp.cardinality();      //added schedule, to later compare

        //Is there a collision?
        if (tempBits != courseBits+scheduleBits){
            throw new Exception("COLLISION");
            //COLLISION there is a conflict in the course time with the schedule you have.
            //Break the whole thing and swap this current SessionID with a different one sequence.
            //Go back to the specific course list you collided with and grab a different one
            //I should have a try block for whatever is running this function
        }
        //No collision and we good, everything worked out.
        else{
            SIDarray.add(classtoAdd.sessionID);
            return temp;
        }

    }

}

