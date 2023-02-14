package model;

public class AbstractSchedulerWithDirection extends AbstractScheduler {

    // Common internal members

    Boolean direction; // True if direction is right, False if direction is left.

    // Common method

    public void compute() {

    };

    // Common constructors

    public AbstractSchedulerWithDirection(String name, int[] requests, int head, Boolean direction) {
        super(name, requests, head);
        this.direction = direction;
        compute();
    }

}