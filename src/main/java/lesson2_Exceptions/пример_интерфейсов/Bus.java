package lesson2_Exceptions.пример_интерфейсов;

public class Bus extends Transport{
    String marshroute;

    public String getMarshroute() {
        return marshroute;
    }

    public void setMarshroute(String marshroute) {
        this.marshroute = marshroute;
    }

    @Override
    void load() {
        System.out.println("Люди ломятся в автобус");
    }
}
