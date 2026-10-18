package functional_interface;

// Naming Things Clearly (Domain Specificity)
public class GlueExample {

    @FunctionalInterface
    interface AccessValidator {
        boolean canAccess(User u);

        // The "Glue": It creates a NEW lambda combining 'this' and 'other'
        default AccessValidator and(AccessValidator other) {
            return u -> this.canAccess(u) && other.canAccess(u);
        }
    }

    public static void main(String[] args) {
        User myUser = new User(20, true); // 20 years old and a subscriber

        // Define small, simple rules
        AccessValidator isAdult = u -> u.getAge() >= 18;
        AccessValidator hasPaid = u -> u.isSubscriber();

        // Use the "Glue" to build a complex rule
        AccessValidator fullCheck = isAdult.and(hasPaid);

        // Now run the final check
        if (fullCheck.canAccess(myUser)) {
            System.out.println("Access Granted! User is an adult AND has paid.");
        } else {
            System.out.println("Access Denied.");
        }
    }
}

class User {
    private int age;
    private boolean isSubscriber;

    public User(int age, boolean isSubscriber) {
        this.age = age;
        this.isSubscriber = isSubscriber;
    }
    public int getAge() { return age; }
    public boolean isSubscriber() { return isSubscriber; }
}