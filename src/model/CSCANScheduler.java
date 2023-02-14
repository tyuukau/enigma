package model;

import java.util.Collections;
import java.util.Vector;

public class CSCANScheduler extends AbstractSchedulerWithDirection {

    public CSCANScheduler(int[] requests, int head, Boolean direction) {
        super("CSCAN" + (direction ? "Right" : "Left"), requests, head, direction);
    }

    @Override
    public void compute() {
        int seek_count = 0;
        int distance, cur_track;

        Vector<Integer> left = new Vector<Integer>();
        Vector<Integer> right = new Vector<Integer>();

        for (int i = 0; i < this.requests.length; i++) {
            if (this.requests[i] < head) {
                left.add(this.requests[i]);
            } else if (this.requests[i] > head) {
                right.add(this.requests[i]);
            }
        }

        // Append end values which has to be visited before reverse
        if ((direction && left.size() > 0) || (!direction && right.size() > 0)) {
            left.add(0);
            right.add(this.diskSize - 1);
        }

        // Sort left & right vectors
        Collections.sort(left);
        Collections.sort(right);

        if (direction) {
            if (right.size() > 0) {
                for (int i = 0; i < right.size(); i++) {
                    cur_track = right.get(i);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;

                    this.schedule.add(cur_track);
                    this.seekCount.add(seek_count);

                    head = cur_track;
                }
            }
            if (left.size() > 0) {
                head = 0;
                seek_count += this.diskSize - 1;

                for (int i = 0; i < left.size(); i++) {
                    cur_track = left.get(i);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;

                    this.schedule.add(cur_track);
                    this.seekCount.add(seek_count);

                    head = cur_track;
                }
            }
        } else {
            if (left.size() > 0) {
                for (int i = left.size() - 1; i > -1; i--) {
                    cur_track = left.get(i);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;

                    this.schedule.add(cur_track);
                    this.seekCount.add(seek_count);

                    head = cur_track;
                }
            }
            if (right.size() > 0) {
                head = this.diskSize - 1;
                seek_count += this.diskSize - 1;

                for (int i = right.size() - 1; i > -1; i--) {
                    cur_track = right.get(i);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;

                    this.schedule.add(cur_track);
                    this.seekCount.add(seek_count);

                    head = cur_track;
                }
            }
        }

    }

}
