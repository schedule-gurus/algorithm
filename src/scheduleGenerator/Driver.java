package scheduleGenerator;

import scheduleGenerator.models.course;
import scheduleGenerator.scheduleAlgorithm.scheduler;

import java.util.ArrayList;
import java.util.List;

public class Driver {


    public static void main(String [] args) throws Exception {


        // STEP 1: RETRIEVE ALL CLASSES NEEDED and ALL ITS INFO
            //API SQL STUFF


        // STEP 2: PUT CLASSES INTO OBJECT FORM.
        // Eg.
        // Paramters for course("CLASSNAME, SESSIONID, DAYSOFWEEK, CURRCAPACITY, MAXCAPACITY")

        course cs1               = new course("csci201,123456,MW,1,10");
        course cs2               = new course("csci201,121212,TR,1,10");
        course cs3               = new course("csci201,212121,F,1,10");
        cs1.setCourseTime(900, 1000);
        cs2.setCourseTime(1030, 1200);
        cs3.setCourseTime(1230, 1300);
        List<course> csci201 = new ArrayList<course>();
        csci201.add(cs1);
        csci201.add(cs2);
        csci201.add(cs3);

        course math1               = new course("math307,888888,MW,1,10");
        course math2               = new course("math307,999999,TR,1,10");
        course math3               = new course("math307,010101,F,1,10");
        math1.setCourseTime(1700, 1900);
        math2.setCourseTime(1200, 1330);
        math3.setCourseTime(1430, 1500);
        List<course> mat307 = new ArrayList<course>();
        mat307.add(math1);
        mat307.add(math2);
        mat307.add(math3);


        List<List<course>> courses = new ArrayList<>();
        courses.add(mat307);
        courses.add(csci201);


//        System.out.println("Size is " + courses.size());
//        System.out.println("Contents are \n" + courses.toString());
//
//        for (int i =0 ; courses.size() > i ; i++){
//            System.out.println(courses.get(i).toString()+ "\n");
//        }
        scheduler s = new scheduler();
        s.buildSchedules(courses, 3);

        System.out.println(s.printSchedules());




//        course math             = new course("math307,123456 ,TR , 51, 100");
//        math.setCourseTime(1030, 1200);                                 //10:30AM to 12:00PM
//        course science          = new course("chemistry101, 141141, F, 33, 50");
//        science.setCourseTime(1200, 1700);                              //12:00PM to 5:00PM
//        course literature       = new course("writ340, 14141, mwf, 11, 15");
//        literature.setCourseTime(1000, 1100);                           //10:00AM to 11:00AM
//
//        //Make a list of course objects
//        List<course> courses = new ArrayList<course>();
//        courses.add(cs);
//        courses.add(math);
//        courses.add(literature);
//        courses.add(science);

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
//        scheduler s = new scheduler();
//        s.buildSchedules(courses, 1);
//        List<BitSet> poolofSchedules = s.schedules;
//        for(int j = 0; j < poolofSchedules.size(); j++){
//            System.out.println(poolofSchedules.get(j).toString());
//        }



    }
}
