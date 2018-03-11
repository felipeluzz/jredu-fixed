package br.com.developer.redu.http;

import br.com.developer.redu.models.*;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import org.scribe.builder.ServiceBuilder;

import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: igor
 * Date: 8/30/12
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScribeHttpClient extends HttpClient {
    private OAuthService service;
    private Token accesToken;




    public ScribeHttpClient(String consumerKey, String consumerSecret){
        super(consumerKey, consumerSecret);
        this.initOauth();

    }
    public ScribeHttpClient(String consumerKey, String consumerSecret, String pin) throws MalformedURLException, IOException{
        super(consumerKey, consumerSecret);
        this.initOauth();
        String serverUrl = "http://openredu.ufpe.br/oauth/token?";
        String urlParameters = "client_id=" + consumerKey + "&client_secret="
        		+ consumerSecret + "&code=" + pin + "&grant_type=authorization_code";
        serverUrl = serverUrl + urlParameters;
        HttpURLConnection con = (HttpURLConnection) new URL(serverUrl).openConnection();
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Content-Type",
//           "application/x-www-form-urlencoded");
//        con.setRequestProperty("Content-Language", "pt-BR");
//        con.setDoOutput(true);
//        con.setUseCaches (false);
//        con.setDoInput(true);
//        try (DataOutputStream wr = new DataOutputStream (
//                con.getOutputStream ())) {
//            wr.writeBytes (urlParameters);
//            wr.flush ();
//        }
        String redirect = con.getHeaderField("Location");
        System.out.println(redirect);
        if (redirect != null){
            con = (HttpURLConnection) new URL(redirect).openConnection();
        }
        
        JsonElement jElement;
        JsonParser jsonParser = new JsonParser();
        
        InputStream is = con.getInputStream();
        
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(is))) {
        	
            String line = rd.readLine();
            jElement = jsonParser.parse(line);
        } 
       
        this.accesToken = new Token(jElement.getAsJsonObject().get("access_token").getAsString(),
consumerSecret);
    }
    
    private void initOauth(){
        this.service = new ServiceBuilder().provider(ReduOauth2.class)
                .apiKey(this.consumerKey).apiSecret(this.consumerSecret).callback("").build();

    }

    private void addUrlParams(OAuthRequest request, Map.Entry<String, String>... params){
        for (Map.Entry<String, String> pair : params){
            request.addQuerystringParameter(pair.getKey(), pair.getValue());
        }
    }

    private void addBodyParams(OAuthRequest request, Map.Entry<String, String>... params){
        for(Map.Entry<String, String> pair : params){
            request.addBodyParameter(pair.getKey(), pair.getValue());
        }
    }

    @Override
    public void initClient(String pin) {
        Verifier v = new Verifier(pin);
        this.accesToken = this.service.getAccessToken(null, v);
    }

    @Override
    public String getAuthUrl() {
        return this.service.getAuthorizationUrl(null);
    }

    @Override
    public String get(String url, Map.Entry<String, String>... params) {
        OAuthRequest request = new OAuthRequest(Verb.GET, url);
        if(params != null){
            this.addUrlParams(request, params);
        }
        this.service.signRequest(this.accesToken, request);
        Response r = request.send();
        return r.getBody();
    }

    @Override
    public String post(String url, Map.Entry<String, String>... params) {
        OAuthRequest request = new OAuthRequest(Verb.POST, url);
        if(params != null){
            this.addBodyParams(request, params);
        }
        this.service.signRequest(this.accesToken, request);
        Response r = request.send();
        return r.getBody();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String post(String url, byte[] payload, Map.Entry<String, String>... params) {
        OAuthRequest request = new OAuthRequest(Verb.POST, url);
        if(params != null){
            this.addBodyParams(request, params);
        }

        request.addPayload(payload);

        request.addHeader("Content-Type", "application/json");
        this.service.signRequest(this.accesToken, request);
        Response r = request.send();
        return r.getBody();
    }

    @Override
    public void delete(String url, Map.Entry<String, String>... params) {
        OAuthRequest request = new OAuthRequest(Verb.DELETE, url);
        if(params != null){
            this.addUrlParams(request, params);
        }
        this.service.signRequest(this.accesToken, request);
        Response r = request.send();
        if (!(r.getCode() == 200)){
            throw new DeleteException("Invalid return code", r.getCode());
        }
    }

    @Override
    public void put(String url,byte[] payload, Map.Entry<String, String>... params) {
        OAuthRequest request = new OAuthRequest(Verb.PUT, url);
        if(params != null){
            this.addBodyParams(request, params);
        }
        request.addPayload(payload);
        request.addHeader("Content-Type", "application/json");
        this.service.signRequest(this.accesToken, request);
        Response r = request.send();
        if(!(r.getCode() == 200)){
            throw new PutException("Invalid return code", r.getCode());
        }

    }


}
