package Day71;

// You are tasked with simulating a university print shop that schedules jobs using
// priority-based Round Robin scheduling. Each job has a user type (Staff gets 
// higher priority than Student), an arrival time, and a burst time. Jobs of the 
// same priority follow the Round Robin algorithm with a fixed time quantum.

// Input Format:
// -------------
//  - Number of jobs
//  - For each job: JobID ArrivalTime BurstTime UserType
// - Time Quantum

// Output Format:
// --------------
//  - Per-job waiting time and turnaround time
//  - Average Waiting Time
//  - Average Turnaround Time

// Sample Input-1:
// ---------------
// 4
// P1 0 5 Staff
// P2 1 4 Student
// P3 2 3 Staff
// P4 3 2 Student
// 2

// Sample Output-1:
// -----------------
// JobID  WaitingTime  TurnaroundTime
// P1     3            8
// P2     9            13
// P3     2            5
// P4     7            9
// Average Waiting Time: 5.25
// Average Turnaround Time: 8.75

// Sample Input-2:
// --------------
// 3
// J1 0 6 Student
// J2 1 4 Staff
// J3 2 2 Student
// 2

// Sample Output-2:
// ----------------
// JobID  WaitingTime  TurnaroundTime
// J1     6            12
// J2     1            5
// J3     4            6
// Average Waiting Time: 3.67
// Average Turnaround Time: 7.67

// ! This code does not pass all test cases !
import java.util.*;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            jobs.add(new Job(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), line[3]));
        }

        int slice = sc.nextInt();

        printTimes(jobs, slice);
        sc.close();
    }

    private static void printTimes(List<Job> jobs, int slice) {
        int n = jobs.size();
        boolean[] added = new boolean[n];

        PriorityQueue<Job> pq = new PriorityQueue<>((a, b) -> a.compareTo(b));
        for (int i = 0; i < n; i++) {
            Job job = jobs.get(i);

            if (job.arrivalTime == 0) {
                pq.add(job);
                added[i] = true;
            }
        }

        int time = 0;
        while (!pq.isEmpty()) {
            Job curr = pq.poll();
            // System.out.println("Executing: " + curr.jobId + " at time: " + time + " pq: "
            // + pq);

            int t = Math.min(curr.remainingTime, slice);
            while (t-- > 0) {
                curr.remainingTime--;
                time++;

                if (curr.remainingTime == 0) {
                    curr.completionTime = time;
                    break;
                }

            }

            for (int i = 0; i < n; i++) {
                Job job = jobs.get(i);

                if (!added[i] && time >= job.arrivalTime) {
                    added[i] = true;
                    pq.add(job);
                }
            }

            if (curr.remainingTime > 0)
                pq.add(curr);
        }

        System.out.println("JobID  WaitingTime  TurnaroundTime");
        for (int i = 0; i < n; i++) {
            Job job = jobs.get(i);
            job.computeTAT();
            job.computeWaitingTime();

            System.out.printf("%s     %d            %d\n", job.jobId, job.waitingTime, job.turnAroundTime);
            // System.out.println(job);
        }

        double avgWaitingTime = jobs.stream().mapToInt(j -> j.waitingTime).average().orElse(0);
        double avgTurnaroundTime = jobs.stream().mapToInt(j -> j.turnAroundTime).average().orElse(0);

        System.out.printf("Average Waiting Time: %.2f\n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);

        // System.out.println(pq);
    }
}

class Job {
    String jobId;
    int arrivalTime;
    int burstTime;
    String userType;
    int remainingTime;

    int completionTime;
    int turnAroundTime;
    int waitingTime;

    Job(String j, int a, int b, String u) {
        this.jobId = j;
        this.arrivalTime = a;
        this.burstTime = b;
        this.userType = u;

        this.remainingTime = this.burstTime;
    }

    public int compareTo(Job j2) {
        if (this.userType.equals("Staff") && !j2.userType.equals("Staff"))
            return -1;
        else if (j2.userType.equals("Staff") && !this.userType.equals("Staff"))
            return 1;
        // return (this.remainingTime - j2.remainingTime);
        if (this.remainingTime == j2.remainingTime) {
            return j2.arrivalTime - this.arrivalTime;
        }

        return j2.remainingTime - this.remainingTime;
    }

    public void computeTAT() {
        this.turnAroundTime = this.completionTime - this.arrivalTime;
    }

    public void computeWaitingTime() {
        this.waitingTime = this.turnAroundTime - this.burstTime;
    }

    public String toString() {
        return "jobId: " + jobId + ", arrivalTime: " + arrivalTime + ", burstTime: " + burstTime + ", completionTime: "
                + completionTime + ", userType: " + userType;
    }
}