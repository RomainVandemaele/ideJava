import java.io.*;
import java.util.Scanner;
import java.util.UUID;


public class CalendarICS {

    static String prodid =     "PRODID:-//Romain//FormationDevAndroid//\r\n";
    static String calBegin =   "BEGIN:VCALENDAR\r\n";
    static String version =    "VERSION:2.0\r\n";
    static String eventBegin = "BEGIN:VEVENT\r\n";
    static String eventEnd =   "END:VEVENT\r\n";
    static String calEnd =     "END:VCALENDAR\r\n";

    public static String eventMaker(String eventLine) {
        eventLine.strip();
        int n = eventLine.length();
        String[] tokens = new String[5];
        int index = eventLine.indexOf(" ");
        int index1 = eventLine.indexOf("\"");
        int index2 = eventLine.lastIndexOf("\"");

        tokens[0] = eventLine.substring(0,index); //Date yyyymmdd
        tokens[1] = eventLine.substring(index+1,index+2); //C or T
        tokens[2] = eventLine.substring(index1+1,index2);//Title
        tokens[3] = eventLine.substring(n-4,n-2); //AM,PM or AD (all day)
        tokens[4] = eventLine.substring(n-1); //D or P at home or at the office

        String event = eventBegin;
        event += "DTSTART:"  + tokens[0]  + "T";
        if(tokens[3].equals("AM") || tokens[3].equals("AD") ) {
            event += "063000Z" + "\r\n";
        }else { //PM
            event += "113000Z" + "\r\n";
        }

        event += "DTEND:"    + tokens[0]  + "T";
        if(tokens[3].equals("PM") || tokens[3].equals("AD") )  {
            event += "140000Z" + "\r\n";
        }else { //AM
            event += "103000Z" + "\r\n";
        }
        event += "DTSTAMP:"   + "20220516" + "T" + "060000Z" + "\r\n";
        UUID uuid = UUID.randomUUID();
        event += "UID:" + uuid.toString() + "\r\n" ;


        if(tokens[1].equals("C") ) {
            event += "SUMMARY:Cefora : "+ tokens[2] + "\r\n";
            event += "DESCRIPTION:" + tokens[2] + ", Local 3.2\r\n";
        }else {
            event += "SUMMARY:"+tokens[2] + "\r\n";
            event += "DESCRIPTION: Local 2.3" + "\r\n";
        }

        if (tokens[0].strip().equals("20220902")) {
            event += "LOCATION:Tours et taxis\\, Av. du port 86C\\, 1000 Bruxelles\\, Belgium" + "\r\n";
        }else if(tokens[4].equals("P")) {
            event += "LOCATION:Digitalcity.brussels\\, R. Jules Cockx 6\\, 1160 Auderghem\\, Belgium" + "\r\n";
        } else {
            event += "LOCATION:Maison" + "\r\n";
        }
        event += "STATUS:CONFIRMED" + "\r\n";
        event += "TRANSP:OPAQUE" + "\r\n";
        event += eventEnd;
        return event;
    }

    public static void calendarMaker(String filename)  {

        String intro = calBegin + prodid + version + "CALSCALE:GREGORIAN\r\n" + "METHOD:PUBLISH\r\n" + "X-WR-CALNAME:Dev android\r\n" + "X-WR-TIMEZONE:Europe/Brussels\r\n";
        File myFile = new File(filename);
        String events ="";
        try {
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String eventLine = myReader.nextLine();
                if(eventLine.length()>0 && eventLine.charAt(0)!='\\' && eventLine.charAt(0)!=' ') {
                    events += eventMaker(eventLine);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            String filePath = "scheduleAndroid.ics";
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(intro);
            myWriter.write(events);
            myWriter.write(calEnd);
            myWriter.close();
        }catch (FileNotFoundException e ) {
            e.printStackTrace();
        }catch (IOException e ) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        calendarMaker("src/schedule.txt");
    }


}
