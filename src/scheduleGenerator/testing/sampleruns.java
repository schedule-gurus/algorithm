package scheduleGenerator.testing;


import scheduleGenerator.models.course;
import scheduleGenerator.scheduleAlgorithm.scheduler;

import java.util.ArrayList;
import java.util.List;

public class sampleruns{

    public sampleruns() throws Exception {
        normalrun();
//        coupleCollisions();
//        noPossibleSchedule();


    }
    public void normalrun() throws Exception {
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


        scheduler s = new scheduler();
        s.buildSchedules(courses, 5);
//
        System.out.println(s.printSchedules());

    }
    public void coupleCollisions() throws Exception {


        // STEP 2: PUT CLASSES INTO OBJECT FORM.
        // Parameter: course("CLASSNAME, SESSIONID, DAYSOFWEEK, CURRCAPACITY, MAXCAPACITY")


        course cs1               = new course("csci201,123456,MW,1,10");
        course cs2               = new course("csci201,121212,TR,1,10");
        course cs3               = new course("csci201,212121,F,1,10");

        // SET COURSE TIMES FOR EACH SCHEDULE

        //MILITARY TIME
        cs1.setCourseTime(900, 1000);       // reminder: 0900 -> 900 get rid of 0 in front
        cs2.setCourseTime(900, 1000);
        cs3.setCourseTime(900, 1000);


        //Add to subject list
        List<course> csci201 = new ArrayList<course>();
        csci201.add(cs1);
        csci201.add(cs2);
        csci201.add(cs3);

        //Same thing as above
        course math1               = new course("math307,888888,MW,1,10");
        course math2               = new course("math307,999999,TR,1,10");
        course math3               = new course("math307,010101,F,1,10");
        math1.setCourseTime(900, 1000);
        math2.setCourseTime(900, 1000);
        math3.setCourseTime(900, 1000);
        List<course> mat307 = new ArrayList<course>();
        mat307.add(math1);
        mat307.add(math2);
        mat307.add(math3);
        List<List<course>> courses = new ArrayList<>();
        courses.add(mat307);
        courses.add(csci201);

        //STEP 3: FEED THE ALGORITHM

        scheduler s = new scheduler();
        s.buildSchedules(courses, 5);

        System.out.println(s.printSchedules());



    }
    public static void noPossibleSchedule() throws Exception {


        // STEP 2: PUT CLASSES INTO OBJECT FORM.
        // Parameter: course("CLASSNAME, SESSIONID, DAYSOFWEEK, CURRCAPACITY, MAXCAPACITY")


        course cs1               = new course("csci201,123456,MW,1,10");

        // SET COURSE TIMES FOR EACH SCHEDULE

        //MILITARY TIME
        cs1.setCourseTime(900, 1000);       // reminder: 0900 -> 900 get rid of 0 in front


        //Add to subject list
        List<course> csci201 = new ArrayList<course>();
        csci201.add(cs1);


        //Same thing as above
        course math1               = new course("math307,888888,MW,1,10");

        math1.setCourseTime(900, 1000);

        List<course> mat307 = new ArrayList<course>();
        mat307.add(math1);

        List<List<course>> courses = new ArrayList<>();
        courses.add(mat307);
        courses.add(csci201);

        //STEP 3: FEED THE ALGORITHM

        scheduler s = new scheduler();
        s.buildSchedules(courses, 5);

        System.out.println(s.printSchedules());



    }
}