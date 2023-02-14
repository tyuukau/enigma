package model;

public class SSTFScheduler extends AbstractSchedulerNoDirection {

    public SSTFScheduler(int[] requests, int head) {
        super("SSTF", requests, head);
    }

    @Override
    public void compute() {
        int seek_count = 0;

		// Create object of class node
		node diff[] = new node[this.requests.length];

		// initialize array
		for (int i = 0; i < this.requests.length; i++) {
			diff[i] = new node();
		}

		// seek_sequence: schedule of tracks
		int[] seek_sequence = new int[this.requests.length + 1];

		for (int i = 0; i < this.requests.length; i++) {

			seek_sequence[i] = head;
			caldiff(this.requests, head, diff);
			int index = findMin(diff);
			diff[index].accessed = true;
			seek_count += diff[index].distance;

			this.seekCount.add(seek_count);
			if (i > 0) {
				this.schedule.add(head);
			}

			// Accessed track becomes new head
			head = this.requests[index];
		}

		// For last accessed track
		this.schedule.add(head);

    }

    // Calculate difference between head and each track
    private void caldiff(int arr[], int head, node[] diff) {
        for (int i = 0; i < this.requests.length; i++) {
            diff[i].distance = Math.abs(head - arr[i]);
        }
    }

    // Find unaccessed track that has minimum distance from head
    private int findMin(node diff[]) {
        int index = -1, minimum = Integer.MAX_VALUE;

        for (int i = 0; i < this.requests.length; i++) {
            if (!diff[i].accessed && minimum > diff[i].distance) {
                minimum = diff[i].distance;
                index = i;
            }
        }
        return index;
    }

}

class node {
	// distance: difference between head position and current track
	int distance = 0;

	// True if track has been accessed
	boolean accessed = false;
}
