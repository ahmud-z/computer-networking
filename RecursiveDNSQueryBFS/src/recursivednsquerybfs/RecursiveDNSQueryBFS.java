package recursivednsquerybfs;

import java.util.LinkedList;
import java.util.Queue;

public class RecursiveDNSQueryBFS {

    private DNSRecord[] dnsRecords;

    public RecursiveDNSQueryBFS() {
        dnsRecords = new DNSRecord[2];

        dnsRecords[0] = new DNSRecord("example.com", "10.34.23.56");
        dnsRecords[1] = new DNSRecord("example.org", "23.45.23.78");
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
            // Simulating recursive behavior
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

    // Function to check if subdomain is present
    private boolean isSubdomain(String potentialSubdomain, String domain) {
        return potentialSubdomain.endsWith("." + domain);
    }

    public static void main(String[] args) {
        RecursiveDNSQueryBFS query = new RecursiveDNSQueryBFS();
        System.out.println("IP of example.com is " + query.findIPAddress("example.com"));
        System.out.println("IP of example.org is " + query.findIPAddress("example.org"));
        System.out.println("IP of google.com is " + query.findIPAddress("google.com"));
    }
}
