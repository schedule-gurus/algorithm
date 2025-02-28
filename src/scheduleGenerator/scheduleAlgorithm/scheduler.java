
package scheduleGenerator.scheduleAlgorithm;

import scheduleGenerator.models.course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class scheduler {


    public List<BitSet> schedules ;
    public List<List<Integer>> successfulSchedulesSID;                //0700->2200, M-F, 30 minute bins = 150 bins to represent the whole week


    public List<BitSet> buildSchedules(List<List<course>> subjectList, int schedulesDesired) throws Exception {
        //INPUT: LIST OF COURSE OBJECTS
        //OUTPUT: List of possible schedules in bitset format
        schedules = new ArrayList<>();
        successfulSchedulesSID = new ArrayList<>();



        //BUILDING SCHEDULES
        while (schedules.size() < schedulesDesired){
            //Iterating through the classes you are taking
            //randomly choose one class from each subject
            List<course> courseList = new ArrayList<>();
            for (int i = 0; subjectList.size() > i; i++){
                //randomly grabbing subject and adding it to courseList
                int boundIndex = subjectList.get(i).size();
                //adding a random subjet class to courseList
                courseList.add(subjectList.get(i).get(ThreadLocalRandom.current()
                .nextInt(subjectList.get(i).size()) % boundIndex));
            }
            BitSet tempSchedule = new BitSet(150);              //creating a empty schedule to fill later
            List<Integer> tempSIDArray = new ArrayList<>();
            boolean collision = false;
            for (scheduleGenerator.models.course course : courseList) {
                //Looping through course list and adding to tempshcedule
                try {
                    tempSchedule = addCoursetoSchedule(tempSchedule, tempSIDArray, course);
                } catch (IOException e) {
                    //Collision so we need to restart the above loop. possibly ask JaeHyung
                    System.out.println("Collision happened");
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

    public List<List<Integer>> getSID(){
        return successfulSchedulesSID;
    }
    public List<BitSet> getSchedules(){
        return schedules;
    }

    public String printSchedules(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; schedules.size() > i; i++){
            sb.append("Schedule " + i + "\n" + schedules.get(i).toString() + "\n\n");
        }
        for (int j = 0 ; successfulSchedulesSID.size() > j; j++){
            sb.append("SID's are : " + successfulSchedulesSID.get(j).toString() + "\n");
        }
        return sb.toString();
    }
}

