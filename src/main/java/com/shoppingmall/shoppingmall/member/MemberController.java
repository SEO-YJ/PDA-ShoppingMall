package com.shoppingmall.shoppingmall.member;

import com.shoppingmall.shoppingmall.utils.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class MemberController {
    MemberService memberService;
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody Member member) {
        if(Validator.isAlpha(member.getName())) {
            // 유저 이름을 log로 출력
            log.info(member.toString());
            // Repository에 저장 시도
            String user_id = memberService.join(member);

            try{
                log.info(user_id);
            } catch(NullPointerException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(user_id, HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
