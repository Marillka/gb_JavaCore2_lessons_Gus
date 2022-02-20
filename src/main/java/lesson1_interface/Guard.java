package lesson1_interface;

public class Guard {

    boolean canPass(User user) {
//        return user.getAge() > 18;
        return user.getAgeBefore18() == 0;
    }
}
