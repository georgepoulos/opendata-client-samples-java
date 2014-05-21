package gr.gov.diavgeia.opendata.samples.decisiontypes;

import java.net.HttpURLConnection;

import gr.gov.diavgeia.opendata.http.IHttpRequestBuilder;
import gr.gov.diavgeia.opendata.http.HttpRequests;
import gr.gov.diavgeia.opendata.http.HttpResponse;
import gr.gov.diavgeia.opendata.samples.Configuration;
import gr.gov.diavgeia.opendata.json.DecisionType;
import gr.gov.diavgeia.opendata.util.StringUtil;
import gr.gov.diavgeia.opendata.util.JsonUtil;


/**
 *
 * @author Diavgeia Developers
 */
public class GetDecisionTypeJson {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        
        IHttpRequestBuilder req = HttpRequests.get(conf.getBaseUrl() + "/types/Β.1.3/");
        req.addHeader("Accept", "application/json");
        
        HttpResponse response = req.execute();
        if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
            String body = StringUtil.readInputStream(response.getBody());
            
            DecisionType dt = JsonUtil.fromString(body, DecisionType.class);
            System.out.println(String.format("%s: %s", dt.getUid(), dt.getLabel()));
        } else {
            System.out.println(String.format("Error: %s %s", response.getStatusCode(), response.getStatusMessage()));
        }
    }
}
