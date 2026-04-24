package blue;

import blue.rose.Constants;
import blue.rose.FileManager;
import blue.rose.Member;

public class Main {
    public static void main(String[] args) {
        FileManager manager = new FileManager(Constants.memberAmount);
        System.out.println(Constants.members.get(0));
        manager.string();
        Constants.members.get(0).setName("No");
        manager.updateList();
        manager.string();
        Constants.members.get(0).setRandom(2);
        Constants.members.get(2).setRandom(2);
        Constants.members.get(1).setRandom(2);
        manager.updateList();
        manager.string();
    }
}