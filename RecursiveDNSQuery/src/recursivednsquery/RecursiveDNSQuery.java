package recursivednsquery;

public class RecursiveDNSQuery {

    private DNSRecord[] dnsRecords;

    public RecursiveDNSQuery() {
        dnsRecords = new DNSRecord[2];

        dnsRecords[0] = new DNSRecord("example.com", "10.34.23.56");
        dnsRecords[1] = new DNSRecord("example.org", "23.45.23.78");
    }

    public String findIPAddress(String domain, int index) {
        if (index >= dnsRecords.length) {
            return "IP Not Found";
        }
        if (dnsRecords[index].getDomain().equals(domain)) {
            return dnsRecords[index].getIPAddress();
        }
        return findIPAddress(domain, index + 1);
    }

    public static void main(String[] args) {

        RecursiveDNSQuery query = new RecursiveDNSQuery();
        System.out.println("Ip of example.com is " + query.findIPAddress("example.com", 0));
        System.out.println("Ip of example.org is " + query.findIPAddress("example.org", 0));
        System.out.println("Ip of google.com is " + query.findIPAddress("google.com", 0));

    }
}
