package blue.rose;

public class Member {
    private int id;
    private String name;
    private int grade;
    private String role;

    public Member(int id, String name, int grade, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.grade = grade;
    }

    @Override
    public String toString(){
        return !Constants.members.isEmpty() ? id + " : " + name + " | Grade: " + grade + " | Role: " + role : "";
    }

    // Getters
    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setRandom(int num) {
        for(int i = 0; i < num; i++){
            this.id = this.id + (int) (Math.pow(Math.random()*num*num*num, num));
            for(int j = 0; j < 3; j++){
                this.name += this.name.substring((int) ((this.name.length()*0.5) * Math.random()));
            }
        }
    }
}
