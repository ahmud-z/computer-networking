package dasd.tcptahoe;
import java.util.*;
public class TCPTahoe {
    private int cwnd;
    private int ssthresh;
    private int rtt;
    private boolean congestion;

    public TCPTahoe(int init_ssthresh) {
        cwnd = 1;
        ssthresh = init_ssthresh;
        congestion = false;
        rtt = 0;
    }

    public void run() {
        System.out.println("Connected to the Server... ...");
        System.out.println("Enter the length of your data: ");
        Scanner scan = new Scanner(System.in);
        int len = scan.nextInt();
        int dataSeqNum = 0;
        System.out.println("Your data is started to be sent ... ");

        while (dataSeqNum < len) {
            this.rtt++;
            System.out.println();
            System.out.println();
            System.out.println("Data sending in RTT number " + this.rtt);
            System.out.println("−−−−−−−−−−−−−−−−−−−−−−−−−−−−");
            System.out.println("previous cwnd size: " + cwnd);
            System.out.println("updated ssthresh value: " + ssthresh);
            
            if (!congestion) {
                if (cwnd < ssthresh) {
                    cwnd = cwnd * 2;
                    System.out.println("...SS phase running...");
                } else {
                    cwnd = 1;
                    congestion = true;
                    System.out.println("Congestion detected. "
                            + "Entering slow start phase..");
                }
            }
            System.out.println("updated cwnd size: " + cwnd);

            sendPacket(dataSeqNum);

            dataSeqNum = dataSeqNum + cwnd;
        }
        System.out.println("\n\nYour data sending is completed. No more data to send."
                + "\nCongestion Control mechanism concludes.\nIt took " + this.rtt
                + " transmission rounds to send the whole data.");
    }

    public void sendPacket(int dataSeqNum) {
        System.out.println("Data from " + (dataSeqNum + 1) + " − " +
                (dataSeqNum + cwnd) + " is being sent now... ...\n\n");

        if (!receiveAcknowledgment()) {
            congestion = true;
            System.out.println("... but wait ! congestion has been detected !");
            handleCongestion();
        } else {
            congestion = false;
        }
    }

    public boolean receiveAcknowledgment() {
        Random ack = new Random();
        return ack.nextBoolean();
    }

    public void handleCongestion() {
        System.out.println("\n\nCongestion detected. Entering slow start phase...");
        ssthresh = cwnd / 2;
        if (ssthresh == 0) {
            ssthresh = 1;
        }
        cwnd = 1;
        retransmitPacket();
    }

    public void retransmitPacket() {
        System.out.println("\nRetransmiting the lost packet now after congestion handling.\n");
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Please input the initial ssthresh value: ");
        int ssthresh = scn.nextInt();

        TCPTahoe tahoe = new TCPTahoe(ssthresh);
        tahoe.run();
    }
}



