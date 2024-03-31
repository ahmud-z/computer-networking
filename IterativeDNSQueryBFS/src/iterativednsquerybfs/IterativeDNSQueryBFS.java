package iterativednsquerybfs;

import java.util.LinkedList;
import java.util.Queue;

public class IterativeDNSQueryBFS {

    private DNSRecord[] dnsRecords;

    public IterativeDNSQueryBFS() {
        dnsRecords = new DNSRecord[3];

        dnsRecords[0] = new DNSRecord("example.com", "10.34.23.56");
        dnsRecords[1] = new DNSRecord("example.org", "23.45.23.78");
        dnsRecords[2] = new DNSRecord("google.com", "192.168.0.7");
    }

    public String findIPAddress(String domain) {
        Queue<DNSRecord> queue = new LinkedList<>();
        boolean[] visited = new boolean[dnsRecords.length];

        for (int i = 0; i < dnsRecords.length; i++) {
            if (dnsRecords[i].getDomain().equals(domain)) {
                return dnsRecords[i].getIPAddress();
            }
            queue.offer(dnsRecords[i]);
            visited[i] = true;
        }

        while (!queue.isEmpty()) {
            DNSRecord currentRecord = queue.poll();
            // Iterate through all DNS records to find subdomains
            for (int i = 0; i < dnsRecords.length; i++) {
                if (!visited[i] && isSubdomain(dnsRecords[i].getDomain(), currentRecord.getDomain())) {
                    if (dnsRecords[i].getDomain().equals(domain)) {
                        return dnsRecords[i].getIPAddress();
                    }
                    queue.offer(dnsRecords[i]);
                    visited[i] = true;
                }
            }
        }

        return "IP Not Found";
    }

    private boolean isSubdomain(String potentialSubdomain, String domain) {
        return potentialSubdomain.endsWith("." + domain);
    }

    public static void main(String[] args) {
        IterativeDNSQueryBFS query = new IterativeDNSQueryBFS();
        System.out.println("IP of example.com is " + query.findIPAddress("example.com"));
        System.out.println("IP of example.org is " + query.findIPAddress("example.org"));
        System.out.println("IP of google.com is " + query.findIPAddress("google.com"));
        System.out.println("IP of spacex.com is " + query.findIPAddress("spacex.com"));
    }

}
