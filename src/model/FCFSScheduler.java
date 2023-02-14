package model;

public class FCFSScheduler extends AbstractSchedulerNoDirection {

    public FCFSScheduler(int[] requests, int head) {
        super("FCFS", requests, head);
    }

    @Override
    public void compute() {

        int seek_count = 0;
        int distance, cur_track;

		for (int i = 0; i < this.requests.length; i++) {

			cur_track = this.requests[i];
			distance = Math.abs(cur_track - this.head);
			seek_count += distance;
			head = cur_track;

            this.seekCount.add(seek_count);
            this.schedule.add(this.requests[i]);
		}

    }
    
}
