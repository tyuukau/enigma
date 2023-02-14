package model;

public class LIFOScheduler extends AbstractSchedulerNoDirection {

    public LIFOScheduler(int[] requests, int head) {
        super("LIFO", requests, head);
    }

    @Override
    public void compute() {

        int seek_count = 0;
        int distance, cur_track;

		for (int i = this.requests.length - 1; i > -1; i--) {

			cur_track = this.requests[i];
			distance = Math.abs(cur_track - this.head);
			seek_count += distance;
			head = cur_track;

            this.seekCount.add(seek_count);
            this.schedule.add(this.requests[i]);
		}

    }
    
}
