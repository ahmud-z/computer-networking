package recursivednsquery;

public class DNSRecord {

    private String domain;
    private String ipAddress;

    public DNSRecord(String domain, String ipAddress) {
        this.domain = domain;
        this.ipAddress = ipAddress;
    }

    public String getDomain() {
        return domain;
    }

    public String getIPAddress() {
        return ipAddress;
    }

}
