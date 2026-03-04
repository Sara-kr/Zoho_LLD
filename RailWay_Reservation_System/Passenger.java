public class Passenger {

    private int ticketId;
    private String name;
    private int age;
    private String gender;
    private String berthPreference;
    private String allotedPreference;

    public Passenger(String name, int age, String gender, String berthPreference) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
    }

    public Passenger() {}

    public int getTicketId(){
        return ticketId;
    }
    public void setTicketId(int ticketId){
        this.ticketId = ticketId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }

    public String getBerthPreference(){
        return berthPreference;
    }
    public void setBerthPreference(String bp){
        this.berthPreference = bp;
    }

    public String getAllotedPreference(){
        return allotedPreference;
    }
    public void setAllotedPreference(String ap){
        this.allotedPreference = ap;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "ticketId="          + ticketId          +
                ", name='"           + name              + '\'' +
                ", age="             + age               +
                ", gender='"         + gender            + '\'' +
                ", allotedPreference='" + allotedPreference + '\'' +
                '}';
    }
}