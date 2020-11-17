package scheduleGenerator.models;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Map;
public class course {
    public String className;                                              // CSCI201
    public int sessionID;                                                     // 102491
    public String daysofweek;
    public int currCapacity;                                                // Current capacity
    public int maxCapacity;                                                 // Max Capacity
    BitSet courseTime;                                                      // Course Times [("M", "1200-1330"), ("TH", "1600-1700")]
    public double ratemyprof;                                               // Rate my professor

    /**
     *course object has to be created in a very specific format
     *      I made the course Object to take specficially these parameters
     *          ("CLASSNAME, SESSIONID, DAYSOFWEEK, CURRCAP, MAXCAP")
     *          ("CLASSNAME, SESSIONID, DAYSOFWEEK, CURRCAP, MAXCAP, RATEMYPROFESSOR")
     */
    public course()
    //Constructor
    {
        className       = "";
        sessionID       = 0;
        daysofweek      ="";
        currCapacity    = 0;
        maxCapacity     = 0;
        courseTime      = new BitSet(150);
        ratemyprof      = -1;


    }
    public course(String text) throws Exception              // "CSCI, 201, 102411, 80, ")
    {
        //Creating an course Object
        //Output: pushing out a list of classes?
        //Input needs to be in this specific format //"DEPTCODE, classID, currCapacity, maxCapacity, coursetime, rate my professor"
        className     = "";
        sessionID         = 0;
        currCapacity    = 0;
        daysofweek      ="";
        maxCapacity     = 0;
//        courseTimes     = new ArrayList<Map.Entry<String, String>>();   //Time should be in format [("D", "TTTT-TTTT"), ("D", "TTTT-TTTT"), ("D", "TTTT-TTTT")]
        ratemyprof      = -1;

        String [] tokens = text.trim().split("\\s*,\\s*");         //parsing it by comma separated.
        if (tokens.length < 4){
            throw new Exception("Something isn't write with your class input data");
        }
        if (tokens.length == 5){                           //no rate my professor in the input string
            className       = tokens[0];
            sessionID       = Integer.parseInt(tokens[1]);
            daysofweek      = tokens[2];
            currCapacity    = Integer.parseInt(tokens[3]);
            maxCapacity     = Integer.parseInt(tokens[4]);
        }
        else if (tokens.length == 6){                           //Rate my professor is in the input string
            className       = tokens[0];
            sessionID       = Integer.parseInt(tokens[1]);
            daysofweek      = tokens[2];
            currCapacity    = Integer.parseInt(tokens[3]);
            maxCapacity     = Integer.parseInt(tokens[4]);
            ratemyprof      = Integer.parseInt(tokens[5]);

        }
        else{
            throw new Exception("Something isn't right with your class input data\n\t I'm getting: " + Arrays.toString(tokens));

        }
    }
    public String printCourse(){
        StringBuilder sb = new StringBuilder();
        sb.append("Class Name: " + className + "\n");
        sb.append("Session ID: " + sessionID+ "\n");
        sb.append("Day of Week : " + daysofweek+ "\n");
        sb.append("Current Capacity : " + currCapacity+ "\n");
        sb.append("Max Capacity : " + maxCapacity+ "\n");

        if (ratemyprof != -1){
            sb.append("Rate My Professor : " + ratemyprof+ "\n");
        }
        return sb.toString();
    }
    public void setCourseTime(int start, int end){
        binaryizer b = new binaryizer(daysofweek, start, end);
        courseTime = b.output;

    }
    public BitSet getCourseTime(){
        return courseTime;
    }



}
