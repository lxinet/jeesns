package com.lxinet.jeesns.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangchuanzhao
 * @date 2020/5/8 13:18
 */
@Component("jwtUtil")
public class JwtUtil {
    private static final String SECRET = "D3F5E472-5C90-41D4-A38A-D3864DBB9426";
    private static String ISSUER = "http://www.jeesns.cn";
    @Autowired
    private MemberService memberService;

    /**
     * 生成token
     * @param member
     * @return
     */
    public String genToken(Member member){
        //使用HMAC256进行加密
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        //创建jwt
        JWTCreator.Builder builder = JWT.create().
                withIssuer(ISSUER). //发行人
                withIssuedAt(new Date()).
                withExpiresAt(new Date((Instant.now().getEpochSecond() + 24 * 60 * 60) * 1000)); //过期时间点
        //传入参数
        Map<String, String> claims = new HashMap<>(3);
        claims.put("id", String.valueOf(member.getId()));
        claims.put("name", member.getName());
        claims.forEach((key,value)-> builder.withClaim(key, value));
        //签名加密
        return builder.sign(algorithm);
    }

    /**
     * 解密jwt
     * @param token
     * @return
     */
    public Member getMember(String token) {
        try {
            if (null == token){
                return null;
            }
            Algorithm algorithm = Algorithm.HMAC256(SECRET);;
            //解密
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT jwt =  verifier.verify(token);
            Map<String, Claim> map = jwt.getClaims();
            Map<String, String> resultMap = new HashMap<>();
            map.forEach((k,v) -> resultMap.put(k, v.asString()));
            Member member  = memberService.findById(Integer.parseInt(resultMap.get("id")));
            if (null == member){
                return null;
            }
            return member;
        }catch (Exception e){
            return null;
        }
    }

    public Member getMember(HttpServletRequest request) {
        try {
            String token = request.getHeader("aut");
            return getMember(token);
        }catch (Exception e){
            return null;
        }
    }



}
