package blue.rose;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class FileManager {
    private String[][] memberList;
    public FileManager(int memberNum){
        this.memberList = new String[memberNum][];
        try {
            fileSetup();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fileSetup() throws FileNotFoundException{
        InputStream is = null;
        try {
            is = FileManager.class.getClassLoader().getResourceAsStream("blue/rose/Files/members.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(is);
        String[] currentLine;
        Member person;
        int i = 1;
        
        while(scan.hasNext()){
            currentLine=scan.nextLine().split(", ");
            person = new Member(Integer.valueOf(i), currentLine[1], Integer.valueOf(currentLine[2]), currentLine[3]);
            Constants.members.add(person);
            i++;
        }
        scan.close();
        updateList();
    }

    public void updateList(){
        int j = 0;
        for(Member m : Constants.members){
            this.memberList[j] = new String[]{String.valueOf(m.getId()), m.getName(), String.valueOf(m.getGrade()), m.getRole()};
            j++;
        }
    }

    public void string(){
        for(Member j : Constants.members){
            System.out.println(j.toString());
        }
    }
}