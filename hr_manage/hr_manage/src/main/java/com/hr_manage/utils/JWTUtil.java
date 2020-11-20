package com.hr_manage.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hr_manage.config.JwtConfigProperties;
import java.util.Date;


public class JWTUtil {

    /**
     * 验证token是否正确
     *
     * @param token
     * @param jwtConfigProperties
     * @return
     * @throws Exception
     */
    public static boolean verify(String token, JwtConfigProperties jwtConfigProperties) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtConfigProperties.getSecret());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(jwt);
            return true;
        } catch (TokenExpiredException tokenExpiredException) {
            throw new Exception(JwtVerifyConst.EXPIRED);
        } catch (SignatureVerificationException signatureVerificationException) {
            System.out.println("token验证失败");
            throw new Exception((JwtVerifyConst.SIGNATURE_VERIFICATION));
        } catch (JWTDecodeException jwtDecodeException) {
            System.out.println("token解析失败");
            throw new Exception(JwtVerifyConst.DECODE_ERROR);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception(JwtVerifyConst.NOT_LOGIN);
        }
    }

    /**
     * 获取token中的信息
     *
     * @param token
     * @return
     */
    public static String getMessage(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     *生成签名
     * @param username
     * @param jwtConfigProperties
     * @return
     */
    public static String sign(String username, JwtConfigProperties jwtConfigProperties) {
        Date date = new Date(System.currentTimeMillis() + jwtConfigProperties.getExpire());
        Algorithm algorithm = Algorithm.HMAC256(jwtConfigProperties.getSecret());
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

}

/**
 * 验证结果常量
 */
class JwtVerifyConst {
    public static String SUCCESS = "token验证成功";
    public static String EXPIRED = "token已过期";
    public static String SIGNATURE_VERIFICATION = "token签名失败";
    public static String DECODE_ERROR = "token解析失败，请重新登录获取token";
    public static String NOT_LOGIN = "未登录";
}
