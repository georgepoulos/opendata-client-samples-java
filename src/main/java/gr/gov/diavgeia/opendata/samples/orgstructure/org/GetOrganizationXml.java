package gr.gov.diavgeia.opendata.samples.orgstructure.org;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.xml.Organization;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.XmlUtil;

/**
 *
 * @author Diavgeia Developers
 */
public class GetOrganizationXml {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/organizations/30/");
        req.addHeader("Accept", "application/xml");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            Organization org = XmlUtil.fromString(body, Organization.class);
            System.out.println(String.format("%s: %s (Latin name: %s)", org.getUid(), org.getLabel(), org.getLatinName()));
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
