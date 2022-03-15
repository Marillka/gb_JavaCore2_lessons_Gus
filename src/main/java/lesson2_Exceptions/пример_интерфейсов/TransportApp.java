package lesson2_Exceptions.пример_интерфейсов;

public class TransportApp {

    public static void main(String[] args) {
        Transport[] transports = new Transport[]{new Cater(), new Ship(), new Bus()};// is a (Является ли?) Катер is a Transport?

        for (Transport transport : transports) {
            transport.load();

            if (transport instanceof Swimable) {
                System.out.println("Это штука плавает");
                Swimable swimable = (Swimable) transport;// type cast
                swimable.swim();
            }

            if (transport instanceof Bus) {
                Bus bus = (Bus) transport;
                System.out.println("Маршрут " + bus.getMarshroute());
            }
            System.out.println();
        }



        NewsService newsService = new OracleNewsService();

        /// comlex logic - какая то сложная логика...

        newsService.getNews();

    }
}
