package com.mycompany.iterativednsquery;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmud
 */
public class IterativeDNSQuery {

    private List<DNSRecord> dnsRecords;

    public IterativeDNSQuery() {
        dnsRecords = new ArrayList<>();
        dnsRecords.add(new DNSRecord("example.com", "192.168.10.5"));
        dnsRecords.add(new DNSRecord("example.com", "6532:354:2511:352:98"));
    }

    public String findIpAddress(String domain) {
        for (DNSRecord record : dnsRecords) {
            if (record.getDomain().equals(domain)) {
                return record.getIpAddress();
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        IterativeDNSQuery query = new IterativeDNSQuery();

        System.out.println("example.com " + query.findIpAddress("example.com"));
        System.out.println("Nasa.com " + query.findIpAddress("nasa.com"));
    }
}
