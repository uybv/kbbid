package dev.uybv.bid;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AuthControllerTest extends AbstractTest {
	@Override
	@BeforeMethod
	public void setUp() {
		super.setUp();
	}

	@Test(description = "Login success")
	public void testLoginSuccess() throws Exception {
		String uri = "/api/auth";
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).content("{\"username\":\"uybv\",\"password\":\"123456\"}")
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		String token = mvcResult.getResponse().getContentAsString();
		Assert.assertTrue(token.length() > 0);
	}
}
