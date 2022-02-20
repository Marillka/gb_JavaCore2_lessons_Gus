package lesson1_interface;

public class App0 {
    public static void main(String[] args) {
        ComputerUser user = new ComputerUser();
        Guard guard = new Guard();
        boolean canPass = guard.canPass(user);

//        User u = new User();
    }
}
