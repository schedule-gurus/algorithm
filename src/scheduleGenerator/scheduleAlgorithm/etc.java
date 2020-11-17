//package scheduleAlgorithm;
//
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class SchedulingProblem {
//    //=================================================================================================================================================
//
//    // Course and room capacities
//
//    public int classsize = new int;
//
//
//    // Course lecture hour arrangements
//
//    public static final int A3 = 1 ;  /* Course is to be scheduled as 3 consecutive hours on a single day */
//    public static final int A2 = 2 ;  /* Course is to be scheduled as 2 + 1 hours on two different days   */
//    public static final int A1 = 3 ;  /* Course is to be scheduled as 2 + 1 hours on two different days   */
//
//    public int coursesSize     = 0   ;
//    public int slotsSize       = 0   ;
//    public int roomsSize       = 0   ;
//    public int insSize         = 0   ;
//    public int epoch           = 1   ;
//    public int max             = 0   ;
//    public int maxIndex        = 0   ;
//
//
//    //-------------------------------------------------------------------------------------------------------------------------------------------------
//
//    public static String capacityToString ( int capacity )
//    {
//        switch ( capacity )
//        {
//            case SMALL  : return "Small"                  ;
//            case MEDIUM : return "Medium"                 ;
//            case LARGE  : return "Large"                  ;
//            default     : return "Invalid capacity value" ;
//        }
//    }
//
//    //-------------------------------------------------------------------------------------------------------------------------------------------------
//
//    public static int stringToCapacity ( String text )
//    {
//        switch ( text )
//        {
//            case "Small"  : return SMALL  ;
//            case "Medium" : return MEDIUM ;
//            case "Large"  : return LARGE  ;
//            default       : return -1     ;
//        }
//    }
//
//    //-------------------------------------------------------------------------------------------------------------------------------------------------
//
//    public static String arrangementToString ( int arrangement )
//    {
//        switch ( arrangement )
//        {
//            case A3  : return "A3"                        ;
//            case A2  : return "A2"                        ;
//            case A1  : return "A1"                        ;
//            default  : return "Invalid arrangement value" ;
//        }
//    }
//
//    //-------------------------------------------------------------------------------------------------------------------------------------------------
//
//    public static int stringToArrangement ( String text )
//    {
//        switch ( text )
//        {
//            case "A3" : return A3 ;
//            case "A2" : return A2 ;
//            case "A1" : return A1 ;
//            default   : return -1 ;
//        }
//    }
//
//    //-------------------------------------------------------------------------------------------------------------------------------------------------
//
//    public static boolean fits ( int courseCapacity , int roomCapacity )
//    {
//        return ( roomCapacity >= courseCapacity ) ;
//    }
//
//    //=================================================================================================================================================
//
//    public Slot          [] slots           ;  // Note: Entry in index 0 is null
//    public Room          [] rooms           ;  // Note: Entry in index 0 is null
//    public Instructor    [] instructors     ;  // Note: Entry in index 0 is null
//    public Course        [] courses         ;  // Note: Entry in index 0 is null
//    public int           [] fitnessValue    ;
//    public int           [] fitnessValidity ;
//    public int           [] listOfA1        ;
//    public int              numOfA1s = 0    ;
//
//    //=================================================================================================================================================
//
//    public SchedulingProblem ( String fileName , char commentStarter ) throws Exception
//    {
//        //-----------------------------------------------------------------------------------------------------------------------------------------------
//
//        List< Slot       > listSlots       = new ArrayList<>() ;
//        List< Room       > listRooms       = new ArrayList<>() ;
//        List< Instructor > listInstructors = new ArrayList<>() ;
//        List< Course     > listCourses     = new ArrayList<>() ;
//
//        //-----------------------------------------------------------------------------------------------------------------------------------------------
//
//        BufferedReader file = new BufferedReader( new FileReader( fileName ) ) ;
//        String         line                                                    ;
//
//        while ( ( line = getNextParsableLine( file , commentStarter ) ) != null )
//        {
//            String [] parts = line.split( "=" ) ;
//
//            switch ( parts[0].trim() )
//            {
//                case "Slot"       : listSlots      .add( new Slot      ( parts[1].trim() ) ) ;  break ;
//                case "Room"       : listRooms      .add( new Room      ( parts[1].trim() ) ) ;  break ;
//                case "Instructor" : listInstructors.add( new Instructor( parts[1].trim() ) ) ;  break ;
//                case "Course"     : listCourses    .add( new Course    ( parts[1].trim() ) ) ;  break ;
//                default           : System.err.println ( "Invalid line in input file!"     ) ;  break ;
//            }
//        }
//
//        file.close() ;
//
//        //-----------------------------------------------------------------------------------------------------------------------------------------------
//
//        slots           = new Slot       [ listSlots      .size() + 1 ]              ;
//        rooms           = new Room       [ listRooms      .size() + 1 ]              ;
//        instructors     = new Instructor [ listInstructors.size() + 1 ]              ;
//        courses         = new Course     [ listCourses    .size() + 1 ]              ;
//        hypothesis      = new int        [ numOfHypothesis ] [ listCourses.size() + 1 ] ;  //start from index 1 // index zero is null
//        flag            = new int        [ numOfHypothesis ] [ listSlots  .size() + 1 ] ;
//        schedule        = new String     [ numOfHypothesis ] [ listSlots  .size() + 1 ][ listRooms .size() + 1 ] ;
//        fitnessValue    = new int        [ numOfHypothesis                         ] ;
//        fitnessValidity = new int        [ numOfHypothesis                         ] ;
//
//        coursesSize = listCourses    .size() + 1 ;
//        slotsSize   = listSlots      .size() + 1 ;
//        roomsSize   = listRooms      .size() + 1 ;
//        insSize     = listInstructors.size() + 1 ;
//
//
//        for ( Slot       s : listSlots       )  { slots      [ s.index ] = s ; }
//        for ( Room       r : listRooms       )  { rooms      [ r.index ] = r ; }
//        for ( Instructor i : listInstructors )  { instructors[ i.index ] = i ; }
//        for ( Course     c : listCourses     )  { courses    [ c.index ] = c ; }
//
//        //-----------------------------------------------------------
//        // fill the array of listOfA1 with the indexes of A1 courses
//
//        for (int i =1;i<coursesSize;i++)
//        {
//            if(courses[i].arrangement == 3) // A1
//            {
//                numOfA1s++;
//            }
//        }
//        listOfA1 = new int [ numOfA1s + 1 ];
//        int j = 1;
//        for (int i =1;i<coursesSize;i++)
//        {
//            if(courses[i].arrangement == 3) // A1
//            {
//                listOfA1[j] = i;
//                j++;
//            }
//        }
//
//        //------------
//
//        mainProcess();
//
//        //------------
//    }
//
//    //=================================================================================================================================================
//
//    public void mainProcess() throws IOException
//    {
//        initHypothesis();
//
//        // until we didn't reach the fitness threshold value do
//        while(true)
//        {
//            for (int i= 1; i < numOfHypothesis ;i++) // reset the fitnessValue && fitnessValidity && [0] fields of hypothesis
//            {
//                fitnessValue    [i]    = 0;
//                fitnessValidity [i]    = 0;
//                hypothesis      [i][0] = 0;
//            }
//
//            applyHypothesis();
//            fitness();
//
//            // check the validity and threshold
//            thresholdCheck();
//
//            for (int i= 1; i < numOfHypothesis/2 ;i++) // 50% of population select for crossover and produce 25% new child
//            {
//                crossover();
//            }
//
//            //in the mutation operation try to find the A1 and mutate them
//            for (int i= 1; i < numOfHypothesis/4 ;i++) // 25% of population select for mutation and produce 25% new child
//            {
//                Random r = new Random();
//                int j = 1;
//                while(true)
//                {
//                    if (hypothesis[j][0] != 1 && hypothesis[j][1] != 0 && hypothesis[j][1] != 2) // no new child && no empty hypothesis && no mutation before
//                    {
//                        mutation(j);
//                        break;
//                    }
//                    else
//                    {
//                        if(j+1 < numOfHypothesis)
//                        {
//                            j++;
//                        }
//                        else{break;}
//                    }
//                }
//
//            }
//
//            for (int i= 1; i < numOfHypothesis ;i++) // add new child to empty hypothesis as random way
//            {
//                if (hypothesis[i][1] == 0)
//                {
//                    newRandomHypothesis(i);
//                }
//            }
//        }
//    }
//
//    //=================================================================================================================================================
//
//    public void thresholdCheck() throws IOException
//    {
//        if(epoch  == 1)
//        {
//            for (int i = 1; i < numOfHypothesis ; i++)
//            {
//                if(fitnessValue[i] >= max)
//                {
//                    maxIndex = i;
//                    max = fitnessValue[i];
//                }
//            }
//        }
//        if(epoch > 4)
//        {
//            for (int i = 1; i < numOfHypothesis ; i++)
//            {
//                if(fitnessValidity[i] == 0 && fitnessValue[i] >= max)
//                {
//                    createHTML(i);
//                    System.exit(1);
//                }
//            }
//        }
//        epoch++;
//    }
//
//    //=================================================================================================================================================
//
//    public void createHTML(int h) throws IOException {
//        String[] courseName = new String[361];
//        int index = 1;
//        int start = 1;
//        int end = 10;
//        while (true) {
//            for (int r = 1; r < roomsSize; r++) {
//                for (int i = start; i < end; i++) {
//                    if (schedule[h][i][r] != null) {
//                        String[] parts = schedule[h][i][r].split("\\+");
//                        String[] course = parts[2].split(",");
//                        String name = course[1].trim();
//                        courseName[index] = name;
//                        index++;
//                    } else {
//                        courseName[index] = "";
//                        index++;
//                    }
//                }
//            }
//            start = end;
//            end = end + 9;
//            if (end > 46) {
//                break;
//            }
//        }
//    }
//    private String getNextParsableLine ( BufferedReader file ) throws Exception
//    {
//        while ( true )
//        {
//            String line = file.readLine() ;
//
//            if ( line == null )  { return null ; }
//
//            line = line.trim() ;
//
//            if ( line.equals( "" )                   )  { continue ; }
//            if ( line.charAt( 0  ) == commentStarter )  { continue ; }
//
//            return line ;
//        }
//    }
//
//    //=================================================================================================================================================
//
//    public void print ()
//    {
//        System.out.println( "Slots"       + "\n" ) ; for ( Slot       s : slots       )  { System.out.println( s ) ; }  System.out.println( "\n" ) ;
//        System.out.println( "Rooms"       + "\n" ) ; for ( Room       r : rooms       )  { System.out.println( r ) ; }  System.out.println( "\n" ) ;
//        System.out.println( "Instructors" + "\n" ) ; for ( Instructor i : instructors )  { System.out.println( i ) ; }  System.out.println( "\n" ) ;
//        System.out.println( "Courses"     + "\n" ) ; for ( Course     c : courses     )  { System.out.println( c ) ; }  System.out.println( "\n" ) ;
//    }
//
//    //=================================================================================================================================================
//
//
//    public void newRandomHypothesis(int index)
//    {
//        Random r = new Random();
//        for(int j = 1 ; j < coursesSize ; j++)
//        {
//            hypothesis[index][j] = j;
//        }
//
//        //shuffle
//        for(int j = 1 ; j < coursesSize ; j++)
//        {
//            int randIndex                = r.nextInt (coursesSize - 1 ) + 1 ;
//            int temp                     = hypothesis[index][randIndex]     ;
//            hypothesis[index][randIndex] = hypothesis[index][j]             ;
//            hypothesis[index][j]         = temp                             ;
//        }
//    }
//
//    //=================================================================================================================================================
//
//
//    //=================================================================================================================================================
//
//    public boolean unwantedCheck(int h, int i, int r, int j)
//    {
//        String insIndex    = courses[j].instructorIndex + ""                  ;
//        String slotIndex   = i + ""                                           ;
//        int insIndex_int   = Integer.parseInt(insIndex)                       ;
//
//        String instructor  = "" + instructors[insIndex_int]                   ;
//        String [] insParts = instructor.split(",")                            ;
//        int unwantedNum    = Integer.parseInt(insParts[2].trim())             ;
//
//        String unwanted    = ""                                               ;
//
//        for (int k = 1;k <= unwantedNum ;k++)
//        {
//            unwanted = unwanted + insParts[2+k];
//        }
//        String [] unwantedParts    = unwanted   .split("\\s+");
//        int unwantedCheckFlag = 1;
//        for (int k = 0;k < unwantedParts.length ; k++)
//        {
//            if ( slotIndex.equals(unwantedParts[k].trim()))
//            {
//                unwantedCheckFlag = 0;
//                break;
//            }
//        }
//        return ( unwantedCheckFlag == 1 );
//    }
//
//    //=================================================================================================================================================
//
//    public boolean unpreferredCheck(int h, int i, int r, int j)
//    {
//        String insIndex    = courses[j].instructorIndex + ""                  ;
//        String slotIndex   = i + ""                                           ;
//        int insIndex_int   = Integer.parseInt(insIndex)                       ;
//
//        String instructor  = "" + instructors[insIndex_int]                   ;
//        String [] insParts = instructor.split(",")                            ;
//        int unwantedNum    = Integer.parseInt(insParts[2].trim())             ;
//        int unpreferredNum = Integer.parseInt(insParts[unwantedNum+3].trim()) ;
//        String unpreferred = ""                                               ;
//
//
//        for (int k = 1;k <= unpreferredNum ;k++)
//        {
//            unpreferred = unpreferred + insParts[3 + unwantedNum + k];
//        }
//        unpreferred = unpreferred .substring(0, unpreferred.length()-1) ; // remove the ] character from end of string
//
//        String [] unpreferredParts = unpreferred.split("\\s+");
//
//        int unpreferredCheckFlag = 1;
//        for (int k = 0;k < unpreferredParts.length ; k++)
//        {
//            if ( slotIndex.equals( unpreferredParts[k].trim()))
//            {
//                unpreferredCheckFlag = 0;
//                break;
//            }
//        }
//        if(unpreferredCheckFlag == 1)
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
//
//    //=================================================================================================================================================
//
//    public void fitness()
//    {
//        for (int h = 1; h < numOfHypothesis; h++)
//        {
//            for(int i = 1 ; i < slotsSize ; i++)
//            {
//                for(int r = 1 ; r < roomsSize ; r++)
//                {
//                    if(schedule[h][i][r] != null)
//                    {
//                        //-------------------------------------------------------------------------------------------------------------------------------------------------
//                        // check unpreferred hours
//
//                        String [] parts = schedule[h][i][r].split("\\+");
//                        String [] courseParts = parts[2].split(",");
//                        String jString = courseParts[4].trim();
//                        jString = jString .substring(0, jString.length()-1);
//                        int j = Integer.parseInt(jString);
//
//                        if(unpreferredCheck( h,i,r,j ) == true) // int h, int i, int r, int j
//                        {
//                            fitnessValue[h]++;
//                        }
//
//                        // end of check unpreferred hours
//                        //-------------------------------------------------------------------------------------------------------------------------------------------------
//
//
//                        //-------------------------------------------------------------------------------------------------------------------------------------------------
//                        // check A2 are not at say day as A1
//
//                        if(courseParts[3].trim().equals("A2"))
//                        {
//                            String checkCase = courseParts[1].trim();
//                            if( i < 10 )
//                            {
//                                if ( checkA2noA1(r,h,1,10,checkCase).equals("YES"))
//                                {
//                                    fitnessValue[h]++;
//                                }
//                                else
//                                {
//                                    fitnessValidity[h] = -1;
//                                }
//                            }
//                            else if(i > 9 && i < 19)
//                            {
//                                if ( checkA2noA1(r,h,10,19,checkCase).equals("YES"))
//                                {
//                                    fitnessValue[h]++;
//                                }
//                                else
//                                {
//                                    fitnessValidity[h] = -1;
//                                }
//                            }
//                            else if(i > 18 && i < 28)
//                            {
//                                if ( checkA2noA1(r,h,19,28,checkCase).equals("YES"))
//                                {
//                                    fitnessValue[h]++;
//                                }
//                                else
//                                {
//                                    fitnessValidity[h] = -1;
//                                }
//                            }
//                            else if(i > 27 && i < 37)
//                            {
//                                if ( checkA2noA1(r,h,28,37,checkCase).equals("YES"))
//                                {
//                                    fitnessValue[h]++;
//                                }
//                                else
//                                {
//                                    fitnessValidity[h] = -1;
//                                }
//                            }
//                            else //if(i > 36 && i < 46)
//                            {
//                                if ( checkA2noA1(r,h,37,46,checkCase).equals("YES"))
//                                {
//                                    fitnessValue[h]++;
//                                }
//                                else
//                                {
//                                    fitnessValidity[h] = -1;
//                                }
//                            }
//                        }
//                        // end of check A2 are not at say day as A1
//                        //-------------------------------------------------------------------------------------------------------------------------------------------------
//                    }
//                }
//            }
//        }
//
//    } // End of fitness
//
//    //=================================================================================================================================================
//
//    // check it if A2 are not at the same day as A1
//    public String checkA2noA1(int r, int h, int start, int end, String checkCase )
//    {
//
//        for (int t = start ; t < end ; t++)
//        {
//            if( schedule[h][t][r] != null)
//            {
//                String [] slotRoomCourseParts = schedule[h][t][r].split( "\\+" );
//                String [] courseParts = slotRoomCourseParts[2].split(",");
//
//                if( courseParts[3].trim().equals("A1") && courseParts[1].trim().equals(checkCase) )
//                {
//                    return "NO"; // A1 and A2 are at the same day
//                }
//            }
//        }
//        return "YES"; //A1 and A2 are not at the same day
//    }
//
//    //=================================================================================================================================================
//
//    public void crossover ()
//    {
//        Random r = new Random();
//
//        int parent1 = getMax(); // is the index of hypothesis with the max fitnessValue hypothesis[parent1][i]
//        int parent2 = getMax();
//
//        int rand1 = r.nextInt(coursesSize/2 - 1 ) + 1 ;
//        int rand2 = r.nextInt(coursesSize - coursesSize/2 ) + coursesSize/2 ;
//
//        int [] child = new int[coursesSize];
//
//        // crossover operation with 3 for loop
//        for (int i = 1; i < rand1;i++)
//        {
//            child[i] = hypothesis[parent1][i];
//        }
//        for (int i = rand1; i < rand2;i++)
//        {
//            child[i] = hypothesis[parent2][i];
//        }
//        for (int i = rand2; i < coursesSize;i++)
//        {
//            child[i] = hypothesis[parent1][i];
//        }
//
//
//        for (int i = 1;i < coursesSize;i++)
//        {
//            hypothesis[parent1][i] = child[i];  // add new child to population and
//            hypothesis[parent2][i] = 0;	                                        // remove the parents
//        }
//        hypothesis[parent1][0] = 1; // that means it is new child
//        // add this new child to the population and remove the parents
//    }
//
//    //=================================================================================================================================================
//
//
//
//    //=================================================================================================================================================
//
//    public int getMax()
//    {
//        int max = fitnessValue[0];
//        int index = 0;
//
//        for (int i = 1; i < numOfHypothesis; i++)
//        {
//            if (fitnessValue[i] >  max)
//            {
//                max = fitnessValue[i];
//                index = i;
//            }
//        }
//        fitnessValue[index] = 0;
//        return index; // return the hypothesis with the highest fitnessValue
//    }
//
//    //=================================================================================================================================================
//
//    public static void main ( String [] args ) throws Exception
//    {
//        //-----------------------------------------------------------------------------------------------------------------------------------------------
//
//        String fileName       = "Input.txt" ;
//
//        //-----------------------------------------------------------------------------------------------------------------------------------------------
//
//        if ( args.length > 0 )  { fileName       = args[0].trim()             ; }
//
//        //-----------------------------------------------------------------------------------------------------------------------------------------------
//
//        SchedulingProblem problem = new SchedulingProblem( fileName ) ;
//
//        //-----------------------------------------------------------------------------------------------------------------------------------------------
//    }
//
//}
