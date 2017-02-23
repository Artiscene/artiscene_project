package com.artiscene.controllers;

import com.artiscene.services.PaymentService;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vanessamnoble on 2/23/17.
 */
@Controller
public class StripeOauthController {
    public static final String TOKEN_URI = "https://connect.stripe.com/oauth/token";

    @GetMapping("/oauth")
    public String oauthCallback(@RequestParam(name="code") String code, Model viewModel) throws IOException {

        ClassLoader classLoader = PaymentService.class.getClassLoader();
        File keyFile = new File(classLoader.getResource("stripe.ini").getFile());
        IniPreferences prefs = new IniPreferences(new Ini(keyFile));
        final String clientId = prefs.node("stripe").get("client_id", null);
        final String apiKey = prefs.node("stripe").get("api_key", null);

        try {
            CloseableHttpClient httpClient
                    = HttpClients.createDefault();
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
            String bodyAsString = EntityUtils.toString(resp.getEntity());
            Type t = new TypeToken<Map<String, String>>() { }.getType();
            Map<String, String> map = new GsonBuilder().create().fromJson(bodyAsString, t);
            String accountId = map.get("stripe_user_id");

            viewModel.addAttribute("account_id", accountId);
            viewModel.addAttribute("raw_body", bodyAsString);

            return "stripe";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
