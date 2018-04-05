package ppj.weather.model;

public class City {

    private int id;

    private String name;

    private State country;

    private int stateId;

    public City(int id, String name, State country, int stateId) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.stateId = stateId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getCountry() {
        return country;
    }

    public void setCountry(State country) {
        this.country = country;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", stateId=" + stateId +
                '}';
    }
}
