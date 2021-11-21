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
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
@Api(value = "首页", tags = { " 支付宝登录" })
public class AliLoginController extends BasicController {
	
	private static final String APPID = "";
	private static final String PRIVATE_KEY = "/lBitOG2uunyrrD1PY+VP8SFn/5hqJI+QR58ofOq0Ok9w5aRo+wB6vn6oTvR5MNWLtLZMQiH5VRW8HQRyHGGHo1fsSnW+JhIZ8sAY0pJ/y5ffW0D/NL2DfPTfPQA+zTW1z7NUYaEH8+vzhHPuMlofreBvU5lZMDeDQvujjfecpKwFOTQrH5J9lBpjCMWbdRNfv6GZBIW2AptLnzLscg85SoGU2I0nLgcNreE+QQzpFdv2ngDC79hRgcaZhrIAKjCT1pi6c8iWyz2py/GtaQITJjLXXtcxHcZXF24TUDB7LNIti/7eFwmFBMyMhO4/5JAgMBAAECggEAD/1BGLPnZpnv/ZJdZzdmae74Pca8RsUGup3ZGU91ACKCxtONUyQJGcYKW9X0Pr01qtyuqmy/v3fFZB3mQxsOZ4yXlXfTyFlduY4SJRCpUlR88CyFAkS/fdK+ULNE0UvOA6aDutZ+RAeKNcuHSXkjztpGRn/G5LsH0l/u+VsK4Qca3FTTB53+d9GGgd+zbotxGbc43E0fe/OHuJNDuX+uHvnBu8q6Y1T2Sc8bk8gv1NwG7gYCE/D1ZaoAmJSUmPX1JTowCZvFnCy0rdLfesBHRIFLInSVQpOV5KTE7ungf/BFhkl3ey2OOzU0EWXJ3hglWQygHNrDa+H1iAARRkRpAQKBgQC2HgKI1NoO/NDsoocZMLoHG/BznIfg8LyGYmYEBDdVVtCQdOQ6relxJvG6GR94V/JwFFWIoLEG51qEbdiqHbt2Y2D5muFvxQK8WsPZgPBfkGGwTKh6o3MJJIz7q0SoUecQNGz7RWbYNTezdrIamkAbvPSNYng6vhFYUC2vEaKmaQKBgQC0RbaufJL4vL7UZnAFM2fUUMuYi6yxex57EMjLyoq5fpUlKqY2PWIPogBVAtPy7Fl4OkGGn6mSnXcDzVbqsrxxQYJ1ymu4HEVM26JiSGdnhU5NbRoS5vK4ZSSpk/PGW4cxDAnEm2uk/4Q8jm2ZTP0+vKexvM018156GlHO8dJc4QKBgB8owUxCBtEbpm9mW3EO6jU/LYPKW2E8LzVa7pNvWTxLr0GYDliP2uoWPLOXis/fVEai5AONYtyIhu8zkCeDEMjvte7e9gVWkuJBMVMsgXQIUv6EDyt0fcEptMl2gze9U6htH9xaAn+pk08sVsrAa4mTadAq8eEzFeqjRT236blJAoGBAKeqd0BOSPvjwLEYHXQY3acRBmt47UOdOTpI4Bj65o+j9d+9jR3BdaUIjbqDiNHVlVlHBY7KjRL6NalpoD9lwfKsdURM4mc4lBPp61vx74rO54cHxEp0Vqe+Gp1GKGMC2fh6M9OSokmyBTG1XqZIfvmMyYN8EHjvQ53ZQOFy9K7BAoGAb4UlvWBMBk4buVt3vHJhIz9rSDWRa6A9ISV3hHb/tyfSNuIXgyYJajg3Z/MR8e5El0A/4W3LndJh9SJQJ7fWZ/N0nJCaDnKGts4bphA1+yVX5Jz2D3WNkzje48pGdumDdAHDqaIpUyIVbPd2Qd61ioOVRIzd5dVqEngxpNc85AA=";
	private static final String ALIPAY_PUBLIC_KEY = "+IQgAtcbB71PeUIFfRruiUrRq0afIBJyNUORMkGwySnVCCR3yuLqg7GlhMmRKM54lwZ4zvQOds2EgWs5Xv3pNqHgJR9MWcOCV9U2s4UdIS4/T3XamGmsnmUG9poPBkTL4Da1T7ySpdMz3VNGoAuImxPX/VUgxdrl3FXsI+2ZRgXCcy67V4ikfvLav7PdpDlmiPHQFis8aflM2QCDA1Zjj9Gtqr5wpmkBXi5Ca+e6N/O+EA85PMutZTYfUuEGTFAmD3mP4B4Mb/ujg89v612A3TcPgPHYhEv1aQIDAQAB";
	
	@Autowired
	private UserService userService;

	@ApiOperation(value = "统一登录接口", notes = "支付宝小程序唤起登录后调用", httpMethod = "POST")
	@PostMapping("/login/{authCode}")
	public IMoocJSONResult login(
			@ApiParam(name = "authCode", 
			value = "授权码", 
			required = true, 
			example = "授权码") @PathVariable String authCode) throws Exception {

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
