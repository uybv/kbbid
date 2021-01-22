package dev.uybv.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dev.uybv.bid.dao.BidRepository;
import dev.uybv.bid.dao.Member;
import dev.uybv.bid.dao.MemberRepository;
import dev.uybv.bid.dto.MemberService;
import dev.uybv.bid.dto.core.PageResult;
import dev.uybv.bid.dto.model.BidDto;

public class BidControllerTest extends AbstractTest {
	@Override
	@BeforeMethod
	public void setUp() {
		super.setUp();
	}

	@Autowired
	private MemberRepository memberRepos;

	@Autowired
	private MemberService memberSrv;

	@Autowired
	private BidRepository bidRepos;

	@Autowired
	private JwtUtil jwtUtil;

	private Integer bidId;

	private Member authUser = null;

	public Member getAuthUser() {
		if (authUser == null) {
			authUser = this.memberRepos.findByUsername("uybv");
		}
		return authUser;
	}

	private String getToken() {
		return jwtUtil.generateToken(getAuthUser().getUsername());
	}
	
	@Test(description = "Create Bid")
	public void test1_CreateBid() throws Exception {
		String token = "Bearer " + getToken();
		BidDto bid = new BidDto("test 001x", "", 0, 100000, 100, 1611489600, 1611687600, null);
		String inputJson = super.mapToJson(bid);
		String uri = "/api/bid";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(status, 200);
		String content = mvcResult.getResponse().getContentAsString();
		BidDto newBid = super.mapFromJson(content, BidDto.class);
		Assert.assertTrue(newBid != null);
		Assert.assertEquals(newBid.getName(), bid.getName());
		this.bidId = newBid.getId();
	}
	
	@Test(description = "Get Bids")
	public void test2_GetBidsById() throws Exception {
		String token = "Bearer " + getToken();
		String uri = "/api/bid";
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri).header("Authorization", token)
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(status, 200);
		String content = mvcResult.getResponse().getContentAsString();
		PageResult<BidDto> result = super.mapFromJson(content, PageResult.class);
		Assert.assertTrue(result != null);
		Assert.assertTrue(result.getItems().size() > 0);
	}

	@Test(description = "Get Bid by ID")
	public void test3_GetBidById() throws Exception {
		String token = "Bearer " + getToken();
		String uri = "/api/bid/" + bidId;
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri).header("Authorization", token)
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(status, 200);
		String content = mvcResult.getResponse().getContentAsString();
		BidDto bid = super.mapFromJson(content, BidDto.class);
		Assert.assertTrue(bid != null);
		Assert.assertTrue(bid.getId() != null);
	}

	@Test(description = "Get Bid by ID Without token")
	public void test4_GetBidByIdWithoutToken() throws Exception {
		String uri = "/api/bid/" + bidId;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(status, 403);
	}

	@Test(description = "Update Bid")
	public void test5_UpdateBid() throws Exception {
		String token = "Bearer " + getToken();
		BidDto bid = new BidDto("test 001y", "", 0, 100000, 100, 1611489600, 1611687600,
				memberSrv.toObject(getAuthUser()));
		bid.setId(bidId);
		String inputJson = super.mapToJson(bid);
		String uri = "/api/bid/" + bidId;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(status, 200);
		String content = mvcResult.getResponse().getContentAsString();
		BidDto newBid = super.mapFromJson(content, BidDto.class);
		Assert.assertTrue(newBid != null);
		Assert.assertEquals(newBid.getName(), bid.getName());
		Assert.assertTrue(newBid.getLastUpdatedAt() != null);
	}

	@Test(description = "Delete Bid")
	public void test6_DeleteBid() throws Exception {
		String token = "Bearer " + getToken();
		String uri = "/api/bid/" + bidId;
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.delete(uri).header("Authorization", token)
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(status, 200);
	}

}
