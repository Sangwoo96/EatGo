package kr.co.fastcampus.eatgo.domain;

public class Restaurant {

    private final Long id;
    private final String name;
    private final String address;

    public Restaurant(Long id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public Object getName() {
        return name;
    }

    public Object getInformation() {
        return name + " in " + address;
    }

    public Object getAddress() {
        return address;
    }

    public Long getId() { return id; }
}
