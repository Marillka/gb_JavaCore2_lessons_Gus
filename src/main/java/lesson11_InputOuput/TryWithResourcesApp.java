package lesson11_InputOuput;

import java.io.Closeable;
import java.io.IOException;

public class TryWithResourcesApp {

    public static void main(String[] args) {


        try (
                MyResource resource = new MyResource("recourse 1");
                MyResource resource2 = new MyResource("recourse 2");
        ) {
            resource.read();
            resource2.read();
        } catch (Exception e) {
            //ignore
        }

//        recourse 1 created
//        recourse 1 closed

//        recourse 1 created
//        recourse 2 created
//        recourse 2 closed
//        recourse 1 closed

//        recourse 1 created
//        recourse 2 created
//        read from resources
//        read from resources
//        recourse 2 closed
//        recourse 1 closed


    }


    private static class MyResource implements Closeable {

        private final String name;

        public MyResource(String name) {
            System.out.println(name + " created");
            this.name = name;
        }

        public void read() {
            System.out.println("read from resources");
        }

        @Override
        public void close() throws IOException {
            System.out.println(name + " closed");
        }
    }
}
