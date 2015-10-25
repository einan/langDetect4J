package detector;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class BabelNetValidator {
	public String getJSONContent(String word, String langType) {
		Client client = Client.create();
		// String langs[] = { "TR", "ENG", "DE" };
		String key = "2cb77fb4-5cf4-4b9a-91b2-c994795c56c6";
		WebResource webResource = client
				.resource("https://babelnet.io/v2/getSynsetIds?word=" + word
						+ "&langs=" + langType + "&key=" + key);
		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);
		controlStatus(response);
		String output = response.getEntity(String.class);
		System.out.println(output);
		return output;
	}

	public void controlStatus(ClientResponse response) {
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
	}

	public void validateLang(String output) {
		if (output.equals("[]"))
			System.out.println("not valid!");
		else
			System.out.println("valid");
	}
}
