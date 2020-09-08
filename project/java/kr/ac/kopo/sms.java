package kr.ac.kopo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.client.AuthCache;
import org.apache.http.HttpHost;

@RestController
public class sms {
	
	@PostMapping("/sendSMS")
	   public String sendSMS(String receiver) {

	      String hostname = "api.bluehouselab.com";
	      String url = "https://"+hostname+"/smscenter/v1.0/sendsms";
	      
	      CredentialsProvider credsProvider = new BasicCredentialsProvider();
	      
	      credsProvider.setCredentials(
	         new AuthScope(hostname, 443, AuthScope.ANY_REALM), new UsernamePasswordCredentials("HANAMOIM", "ce43964eedcf11eabfc60cc47a1fcfae")
	      );
	      
	      // Create AuthCache instance
	      AuthCache authCache = new BasicAuthCache();
	      authCache.put(new HttpHost(hostname, 443, "https"), new BasicScheme());
	      
	      // Add AuthCache to the execution context
	      HttpClientContext context = HttpClientContext.create();
	      context.setCredentialsProvider(credsProvider);
	      context.setAuthCache(authCache);
	      
	      //DefaultHttpClient client = new DefaultHttpClient();
	      HttpClient client = HttpClientBuilder.create().build();
	      
	      try {
	         HttpPost httpPost = new HttpPost(url);
	         httpPost.setHeader("Content-type", "application/json; charset=utf-8");
	         
	         //String json = "{\"sender\":\"01099194276\",\"receivers\":[\"" + receiver + "\"],\"content\":\"오늘은 모임통장 회비를 보내는 날입니다\"}";
	         String json = "{\"sender\":\""+ "01099194276" +"\",\"receivers\":[\""+ "01099194276" +"\"],\"content\":\""+ "오늘은 모임통장 회비를 보내는 날입니다"  +"\"}";
	         // ex) String json = "{\"sender\":\""+ "01050967347" +"\",\"receivers\":[\""+ "01050967347" +"\"],\"content\":\""+ "거의 다 왔습니다. 화이팅합시다."  +"\"}";
	         // ex) 복붙하실 때에 특수문자는 \표시 하나씩 더붙는거 확인해서 지워주세요.
	         // ex) 문자보낼 때 \n 줄바꿈은 안되더라구요. 저는 그냥 줄바꿈없이 했습니다.
	         StringEntity se = new StringEntity(json, "UTF-8");
	         httpPost.setEntity(se);
	         
	         HttpResponse httpResponse = client.execute(httpPost, context);
	         System.out.println(httpResponse.getStatusLine().getStatusCode());
	         
	         InputStream inputStream = httpResponse.getEntity().getContent();
	         if(inputStream != null) {
	            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	            String line = "";
	            
	            while((line = bufferedReader.readLine()) != null)
	               System.out.println(line);
	            
	            inputStream.close();
	         }
	      } catch (Exception e) {
	         System.err.println("Error: "+e.getLocalizedMessage());
	      } finally {
	         client.getConnectionManager().shutdown();
	      }
	      return "ok";
	   }

	
}
