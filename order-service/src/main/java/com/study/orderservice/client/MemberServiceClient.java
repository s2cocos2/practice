package com.study.orderservice.client;

import com.study.orderservice.dto.response.MemberResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member-service")
public interface MemberServiceClient {

    @GetMapping("/api/members/{memberId}")
    MemberResponseDto getMemberById(@PathVariable("memberId") Long memberId);

}
