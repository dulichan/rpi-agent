package org.wso2.emm.rpie.org.wso2.emm.rpie.connector;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HttpService extends ConnectionService {
    public void sendPayload(JSONObject data, String address) throws IOException {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            httpclient.start();
            HttpPost request = new HttpPost(address);
            request.setEntity(new StringEntity(data.toJSONString()));
            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }
}
