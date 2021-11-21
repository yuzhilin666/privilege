package com.imooc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.IMoocJSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "首页", tags = { " 支付宝登录" })
public class AliLoginTeamController extends BasicController {
	
	private static final String APPID = "2019030863476657";
	private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwpZSOkBG9gLKBZ/UlV6Yqgc8YYUDC0SLhAf7M7bkwrxvrnDavAEQ5ye66jA+xgvyfli17ZDwFj1WtQTdLWXj4qNNTJQkFRvLoqrEhG85yUmWwX+mLSaRT4x8pWI4FU71zevIItLw31TWdQqbQBlG1+lAxFL+MMwJH56ZA7ngV0osanJU1HnW1n5KYSmP9V4gaohcF9q0CsKLF446nXcgqhXHKwfqSHPzSUF9KM1vbAspp9emdKxzpnDh+vvdNY9O5kTM7vWj7ITQktJBSjT/i4PVWYUn2sn7kl/aKMRyVK5GddE0jI3yZ7q/1isUvGft5rhHsCY6ovmV9QryKrYsVAgMBAAECggEBAJ90qHhUVp9AiGJGDEWsLCQcz2vLhmEV1i1U1sP4J35p44M4nSg1zmB4BxbADf6IJjCMqPvEjGXccFOYFijfWFLmWvSn7s+jruwWARTI+/+4n3Cp87C3Ri4vTFbPSExIvjB7H/Z555liziXqH5iSy7nAfhkACLSu6vUrh6j/WskeoqZnpcsGbw4YB0G9bJ3iDPB/Uyw9oeOVzIdwiSZpingptoWgi4ry3Yp+Su03NYAZjdwvS7f1yEepcQ028+Wsqi5cE0tyrnWDKDoacNjjDJlZzWKJDZ3Lc671wIMHXuHLMmNn8Kd0u/C9y/h6Fgmt3TKKEHW61z0SW+f0EOQU1bUCgYEA/ME+923GaICtMdXMw7qG4Xw9qy9O3GRNqMzWmLXHKktUlIiv8Vx0rIRMbU5Gqs8/m8+VmCLeAfyJXSE924H9vQcmqpMX0inoDqNUV7d/mfWJTgmLypfPDLcYVmCm4DnFVV6ZGD2NuDPyoAYtO/DMAtfaW3yWm2M0DFSjRMVft5sCgYEAsuouupvU7dgtZHggiP75BW2Oq5lgjm1TJqSL4gDuFvS1uRsuNvpAD+QZwejgB/RcK5r2zjzVcNNnutnrS9VvjH7DnhgUbNq6SbKtsGSmktZrOQncydX6+dVIweqT9atl8La/Y+3DEXUOzQDO/e48kBzTXdANBBr0/aou29A8aw8CgYBRTHdMburawkjzNqneayjnbmcHoPfku/6exQRhqladrA1QmYa+Q9bjhspufjcMygzTuTmM7eorCWfFpOOYr39GRf6R7y5CU2hpDPAcJtHMW8YGa9ujFqFqnZoebog+p1Zp1zu3KFVlYpAwGMyD8zgr0fJSawimgopDTRYXEMVw8QKBgDrI4ROlMd/4AGobYi1BAVb8OT0+htAlMYXn2KIoRhI60x5/xL6BmNzfyGrC9iEG8+XL4729lS+d9l4ZTcs71Ivn9qQSK1vcvgqK77CeUXC7jL1P9UBH00+U+XPlfDni1/qwQNA3hblNqdBHrLsYNMPxBK21NyGbLdXOvdixYbiXAoGAdjcNPJW2PutjwPRAUQU7xzy5W+0LK+4aAfgtPP1Pp0Ju+bsRnr9kAVBvY0PwmisqTKaUp16f/MX+XZDWNLFuGBO8z3K94DlEIcrfqOvOxXXajw/e+L8cDLfFDNySLCyvSRgeb6nvi+KAD/4A5o0H3pPBG1DlaNpJ/PZ9j3BhbZI=";
	private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx4jXBdhth4O3ju4P9j5alyu3pMOgHm9/lvl3sCtKm3vwK77tlLNXPkRR4dIWHn4mv+bK4SQHAPCvYsN3K8WlKKSPg+Ss6QRzUs1JV10zA3zMZvUsPhusSf/V8lsBoMdK/aMBhM7nFxfAaSm9NX6ydWsPA4U3y1CaLsdsaE5LO05gE2EqoFi7n9v0y1TxD014DGMBOzaEK7kpNxu8peEBptUnnfsenYix49aZ9so5Uk6tGYfSDTer3g1EKhEPfT71rkemlArl5VqccEr7oZ+LkJKREAFwcQyYqqtdQwSA1vAXQRuPgoXszRvIQ0AX9HzIKUWk8QqKWrmD8KUkjlxavwIDAQAB";
//	
//	private static String APPID = "";
//	private static String PRIVATE_KEY = "";
//	private static String ALIPAY_PUBLIC_KEY = "";
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "统一登录接口", notes = "支付宝小程序唤起登录后调用", httpMethod = "POST")
	@PostMapping("/team/login/{authCode}/{qq}")
	public IMoocJSONResult login(
			@ApiParam(name = "authCode", 
			value = "授权码", 
			required = true, 
			example = "授权码") @PathVariable String authCode,
			@ApiParam(name = "qq", 
			value = "请提供你的qq号给老师", 
			required = true) @PathVariable String qq) throws Exception {
		
		// 1. 服务端获取access_token、user_id
		AlipaySystemOauthTokenResponse response = getAccessToken(authCode);
		if (response.isSuccess()) {
			System.out.println("获取access_token - 调用成功");
			/**
			 *  获取到用户信息后保存到数据
			 *  1. 如果数据库不存在对用的 alipayUserId, 则注册
			 *  2. 如果存在，则获取数据库中的信息再返回
			 */
			String accessToken = response.getAccessToken();
			String alipayUserId = response.getUserId();
			System.out.println("accessToken:" + accessToken);
			System.out.println("alipayUserId:" + alipayUserId);
			
			// 2. 查询该用户是否存在
			Users userInfo = userService.queryUserIsExist(alipayUserId);
			if (userInfo != null) {
				// 如果用户存在，直接返回给前端，表示登录成功
				return IMoocJSONResult.ok(userInfo);
			} else {
				// 如果用户不存在，则通过支付宝api获取用户的信息后，再注册用户到自己平台数据库
				// 获取会员信息
				AlipayUserInfoShareResponse aliUserInfo = getAliUserInfo(accessToken);
				if (aliUserInfo != null) {
					 Users newUser = new Users();
					 newUser.setAlipayUserId(alipayUserId);
					 newUser.setNickname(aliUserInfo.getNickName());
					 newUser.setRegistTime(new Date());
					 newUser.setIsCertified(aliUserInfo.getIsCertified().equals("T") ? 1 : 0);
					 newUser.setFaceImage(aliUserInfo.getAvatar());
					 userService.createUser(newUser);
					 return IMoocJSONResult.ok(newUser);
				}
			}
		} else {
			System.out.println("获取access_token - 调用失败");
		}
		return IMoocJSONResult.ok();
	}
	
	// 服务端获取access_token、user_id
	private AlipaySystemOauthTokenResponse getAccessToken(String authCode) throws Exception {
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", 
				APPID,					// 1. 填入appid
				PRIVATE_KEY,			// 2. 填入私钥 
				"json", 
				"GBK", 
				ALIPAY_PUBLIC_KEY, 		// 3. 填入公钥
				"RSA2");
		AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
		request.setGrantType("authorization_code");
		request.setCode(authCode);		// 4. 填入前端传入的授权码authCode
		request.setRefreshToken("201208134b203fe6c11548bcabd8da5bb087a83b");	// 0. 不用管
		AlipaySystemOauthTokenResponse response = alipayClient.execute(request);

		return response;
	}
		
	// 获取支付宝用户信息
	private AlipayUserInfoShareResponse getAliUserInfo (String accessToken) throws Exception {
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
				APPID,					// 1. 填入appid
				PRIVATE_KEY,			// 2. 填入私钥 
				"json", 
				"GBK", 
				ALIPAY_PUBLIC_KEY, 		// 3. 填入公钥
				"RSA2");
		AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
		AlipayUserInfoShareResponse response = alipayClient.execute(request, accessToken);
		if(response.isSuccess()){
			System.out.println("获取会员信息 - 调用成功");
			return response;
		}

		return null;
	}
}
