package globant.svp.rest.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import globant.svp.core.domain.Customer;
import hello.Application;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:8090")
public class CustomerControllerTest {
	private static String BASE_URL = "http://localhost:8090/Customer";
	@Value("${server.port}")
	private int port;
	
	@Test
	public void testHome() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> entity = new TestRestTemplate().exchange(
				"http://localhost:" + this.port + "/Customer", HttpMethod.GET, new HttpEntity<Void>(
						headers), String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
	@Test
	public void testHeaders() throws Exception {
		HttpHeaders headers = getHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	//@Test
	@SuppressWarnings("unused")
	public void shouldCreateFollowAndDelete() {

		Customer customer = new Customer();
		customer.setFirstName("Mohammed Mahmudul");
		customer.setLastName("Alam");
		String requestJson = JSONUtil.convertTOJson(customer);
		RestTemplate rest = new TestRestTemplate();

		// Post new Customer
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> postData = new HttpEntity<String>(requestJson,
				headers);

		ResponseEntity<Customer> postResponse = rest.postForEntity(BASE_URL,
				postData, Customer.class);
		assertThat(postResponse.getStatusCode(), equalTo(HttpStatus.CREATED));

		// Test GET Operation
		ResponseEntity<String> getResponse = rest.getForEntity(
				BASE_URL, String.class);
		MediaType contentType = getResponse.getHeaders().getContentType();

		//assertEquals(contentType, MediaType.APPLICATION_JSON);

	}
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<String> page = new TestRestTemplate().getForEntity(
				"http://localhost:" + this.port + "/Customer", String.class);
		assertEquals(HttpStatus.OK, page.getStatusCode());
		
		assertThat(page.getBody() , notNullValue());
		return headers;
	}
}