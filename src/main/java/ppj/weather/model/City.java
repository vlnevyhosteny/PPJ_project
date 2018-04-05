package ppj.weather.model;

public class City {

    private int id;

    private String name;

    private State state;

    private int stateId;

    public City(int id, String name, State state, int stateId) {
        this.id = id;
        this.name = name;
        this.state = state;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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
                ", state=" + state +
                ", stateId=" + stateId +
                '}';
    }
}
