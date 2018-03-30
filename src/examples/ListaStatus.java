package examples;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import br.com.developer.redu.DefaultReduClient;
import br.com.developer.redu.models.Status;

public class ListaStatus {
	private static final String CONSUMER_KEY = "seuconsumer";
	private static final String CONSUMER_SECRET_KEY = "suachave";
	private static final String PIN = "seupin";
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		DefaultReduClient client = new DefaultReduClient(CONSUMER_KEY, CONSUMER_SECRET_KEY, PIN);
		// Lista todos os status de um espaço.
		// Note que você pode passar argumentos opcionais como Null
		System.out.println(client.getMe());
	}	

}
