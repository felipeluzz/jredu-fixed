package examples;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import br.com.developer.redu.DefaultReduClient;
import br.com.developer.redu.models.Status;

public class ListaStatus {
	private static final String CONSUMER_KEY = "ogDqNVtbGItSZXxQzeA89c8Vn228KX163Om9F9gL";
	private static final String CONSUMER_SECRET_KEY = "bCa6QXSiiBhLVX9xLetVZbpeyLfBRvMZzH0M4Asx";
	private static final String PIN = "7Jo33q55xw8qLgei62SG";
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		DefaultReduClient client = new DefaultReduClient(CONSUMER_KEY, CONSUMER_SECRET_KEY, PIN);
		// Lista todos os status de um espaço.
		// Note que você pode passar argumentos opcionais como Null
		System.out.println(client.getMe());
	}	

}
