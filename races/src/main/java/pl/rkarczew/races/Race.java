package pl.rkarczew.races;

class Race {

    private String name;

    private String id;

    private String state;

    private String city;

    public Race(String name, String id, String state, String city) {

        super();

        this.name = name;

        this.id = id;

        this.state = state;

        this.city = city;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getId() {

        return id;

    }

    public void setId(String id) {

        this.id = id;

    }

    public String getState() {

        return state;

    }

    public void setState(String state) {

        this.state = state;

    }

    public String getCity() {

        return city;

    }

    public void setCity(String city) {

        this.city = city;

    }

}