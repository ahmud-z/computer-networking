package com.mycompany.recursivednsquery;

/**
 *
 * @author Ahmud
 */
public class RecursiveDNSQuery {

    private DNSRecord[] dnsRecords;

    public RecursiveDNSQuery() {
        dnsRecords = new DNSRecord[2];
        dnsRecords[0] = new DNSRecord("example.com", "192.168.10.2");
        dnsRecords[1] = new DNSRecord("example.org", "256:2251:3651:154:51");
    }

    public String findIpAddress(String domain, int index) {
        if (index >= dnsRecords.length) {
            return "Not Found";
        }
        if (dnsRecords[index].getDomain().equals(domain)) {
            return dnsRecords[index].getIpAddress();
        }
        return findIpAddress(domain, index + 1);
    }

    public static void main(String[] args) {
        RecursiveDNSQuery query = new RecursiveDNSQuery();
        System.out.println("example.com " + query.findIpAddress("example.com", 0));
        System.out.println("example.com " + query.findIpAddress("spcaex.com", 0));
    }
}
