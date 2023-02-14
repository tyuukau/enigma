package model;

public class AbstractSchedulerNoDirection extends AbstractScheduler {

    // Common method

    public void compute() {

    };

    // Common constructors

    public AbstractSchedulerNoDirection(String name, int[] requests, int head) {
        super(name, requests, head);
        compute();
    }

}