package blue;

import blue.rose.Constants;
import blue.rose.FileManager;
import blue.rose.Member;

public class Main {
    public static void main(String[] args) {
        FileManager manager = new FileManager(Constants.memberAmount);
        System.out.println(Constants.members.get(0));
        manager.string();
    }
}