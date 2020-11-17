package scheduleGenerator;

import scheduleGenerator.models.binaryizer;
import scheduleGenerator.models.course;
import scheduleGenerator.scheduleAlgorithm.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class Driver {


    public static void main(String [] args) throws Exception {


        // STEP 1: RETRIEVE ALL CLASSES NEEDED and ALL ITS INFO
            //API SQL STUFF


        // STEP 2: PUT CLASSES INTO OBJECT FORM.
        // Eg.
        // Paramters for course("CLASSNAME, SESSIONID, DAYSOFWEEK, CURRCAPACITY, MAXCAPACITY")

        course cs               = new course("csci201,123456,MW,1,10");
        cs.setCourseTime(800, 900);                                     //8AM to 9AM
        course math             = new course("math307,123456 ,TR , 51, 100");
        math.setCourseTime(1030, 1200);                                 //10:30AM to 12:00PM
        course science          = new course("chemistry101, 141141, F, 33, 50");
        science.setCourseTime(1200, 1700);                              //12:00PM to 5:00PM
        course literature       = new course("writ340, 14141, mwf, 11, 15");
        literature.setCourseTime(1000, 1100);                           //10:00AM to 11:00AM

        //Make a list of course objects
        List<course> courses = new ArrayList<course>();
        courses.add(cs);
        courses.add(math);
        courses.add(literature);
        courses.add(science);

        //STEP 3: FEED THE ALGORITHM

        /**
         * Stuck here:
         * In order to figure out the collisions, I need to figure out
         * how I am going to set up the same "csci201" course with different
         * multiple offered times.
         *
         * Create subject list with same subject but different course offerings??
         *
         * Need to build API first.
         *
         */
        scheduler s = new scheduler();
        s.buildSchedules(courses, 1);
//        List<BitSet> poolofSchedules = s.schedules;
//        for(int j = 0; j < poolofSchedules.size(); j++){
//            System.out.println(poolofSchedules.get(j).toString());
//        }
        System.out.println(s.buildSchedules(courses, 1));



    }
}
