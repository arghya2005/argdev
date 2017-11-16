package test;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.lucene.search.SpringLuceneSearchApplication;
import com.lucene.search.resource.LuceneSearchController;


public class LuceneSearchControllerTest {
	
	
	/*	@Test
		public void singlestringtest() throws Exception {
			
			MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
			formData.add("searchString","body");
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/rest/search/value/", formData, String.class);
			String delmessage = response.getBody();
			
			//assertEquals("", delmessage);
			assertNotNull(delmessage);

		}
		
		@Test
		public void failtest() throws Exception {
			
			MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
			formData.add("searchString","xyz");
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/rest/search/value/", formData, String.class);
			String delmessage = response.getBody();
			
			//assertEquals("", delmessage);
			assertNotNull(delmessage);

		}
		@Test
		public void pharasetest() throws Exception {
			
			MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
			formData.add("searchString","body mass index");
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/rest/search/value/", formData, String.class);
			String delmessage = response.getBody();
			
			//assertEquals("", delmessage);
			assertNotNull(delmessage);

		}
		@Test
		public void pharasetestFail() throws Exception {
			
			MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
			formData.add("searchString","ody mass index");
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/rest/search/value/", formData, String.class);
			String delmessage = response.getBody();
			
			//assertEquals("", delmessage);
			assertNotNull(delmessage);

		}

		@Test
		public void pharasetestFailextra() throws Exception {
			
			MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
			formData.add("searchString","body mass index extra");
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/rest/search/value/", formData, String.class);
			String delmessage = response.getBody();
			
			assertNotNull(delmessage);

		}*/
		
		@Test
		public void pharasetestcaseinsencitive() throws Exception {
			
			/*MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
			formData.add("searchString","ODY");
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/rest/search/value/", formData, String.class);
			String delmessage = response.getBody();
			assertNotNull(delmessage);
			*/

		}
}
