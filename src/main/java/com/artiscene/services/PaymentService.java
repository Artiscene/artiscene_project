package com.artiscene.services;

import com.stripe.Stripe;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//
///**
// * Created by vanessamnoble on 2/23/17.
// */
public class PaymentService {
        //Stripe.apiKey = "sk_test_kUyFKcQWcsRYKDohJ6bPDm3F";

    public static final String AUTHORIZE_URI
            = "https://connect.stripe.com/oauth/authorize";
    public static final String TOKEN_URI
            = "https://connect.stripe.com/oauth/token";

    public static void main(final String[] args) throws IOException {
        // Read Stripe platform's client ID and secret API key
        /*ClassLoader classLoader = PaymentService.class.getClassLoader();
        File keyFile = new File(classLoader.getResource("stripe.ini")
                .getFile());
        IniPreferences prefs = new IniPreferences(new Ini(keyFile));
        final String clientId = prefs.node("stripe").get("client_id", null);
        final String apiKey = prefs.node("stripe").get("api_key", null);



            Map<String, Object> viewObjects
                    = new HashMap<String, Object>();

            try {
                CloseableHttpClient httpClient
                        = HttpClients.createDefault();
                String code = request.queryParams("code");
                URI uri = new URIBuilder(TOKEN_URI)
                        .setParameter("client_secret", apiKey)
                        .setParameter("grant_type", "authorization_code")
                        .setParameter("client_id", clientId)
                        .setParameter("code", code)
                        .build();

                // Make /oauth/token endpoint POST request
                HttpPost httpPost = new HttpPost(uri);
                CloseableHttpResponse resp = httpClient.execute(httpPost);

                // Grab stripe_user_id (use this to authenticate as the
                // connected account)
                String bodyAsString
                        = EntityUtils.toString(resp.getEntity());
                Type t = new TypeToken<Map<String, String>>() { }.getType();
                Map<String, String> map
                        = new GsonBuilder().create().fromJson(bodyAsString, t);
                String accountId = map.get("stripe_user_id");

                viewObjects.put("account_id", accountId);
                viewObjects.put("raw_body", bodyAsString);

                return new ModelAndView(viewObjects, "callback.ftl");
            } catch (Exception e) {
                response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                viewObjects.put("error", e.getMessage());
                return new ModelAndView(viewObjects, "error.ftl");
            }
*/    }
}
