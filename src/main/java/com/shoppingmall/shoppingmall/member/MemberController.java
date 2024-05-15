package com.shoppingmall.shoppingmall.member;


import com.shoppingmall.shoppingmall.utils.ApiUtils;
import com.shoppingmall.shoppingmall.utils.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.shoppingmall.shoppingmall.utils.ApiUtils.error;
import static com.shoppingmall.shoppingmall.utils.ApiUtils.success;

// TODO: 24.05.14까지 최신화
@Slf4j
@RestController
@AllArgsConstructor
public class MemberController {
    MemberService memberService;

    @PostMapping("/join/res/en") // Before
    public ResponseEntity<String> joinByResponseEntity(@RequestBody Member member) {
        if (Validator.isAlpha(member.getName())) {
            // 유저 이름을 log로 출력
            log.info(member.toString());

            // ID 중복 체크
            // 중복이면 사용자 예외 클래스 소환
            //      1) 예외 클래스한테 니가 return 해!
            //      2) 예외만 발생 시키고.. 메세지는 내가 보낼게
            if (isDuplicateId(member)) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }

            // Repository에 저장 시도
            String user_id = memberService.join(member);

            try {
                log.info(user_id);
            } catch (NullPointerException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
//            return new ResponseEntity<>(user_id, HttpStatus.CREATED);
            return new ResponseEntity<>(user_id, HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/join/api/result") // After
    public ApiUtils.ApiResult<String> joinByApiResult(@RequestBody Member member) {
        if (Validator.isAlpha(member.getName())) {
            // 유저 이름을 log로 출력
            log.info(member.toString());

            // ID 중복 체크
            // 중복이면 사용자 예외 클래스 소환
            //      1) 예외 클래스한테 니가 return 해!
            //      2) 예외만 발생 시키고.. 메세지는 내가 보낼게
            if (isDuplicateId(member)) {
                return error("아이디 중복", HttpStatus.CONFLICT);
            }

            // Repository에 저장 시도
            String userId = memberService.join(member);

//            {
//	                “success” : True,
//	                “response” : 응답 데이터(객체),
//	                “error” : null
//            }

            try {
                log.info(userId);
            } catch (NullPointerException e) {
                return success(userId);
            }
            return success(userId);
        } else
            return error("아이디 숫자 포함", HttpStatus.BAD_REQUEST);
    }

    private boolean isDuplicateId(Member member) {
        return memberService.checkDuplicateId(member.getUserId());
    }
}



//    @GetMapping("/duplicate")
//    public ResponseEntity<Boolean> isDuplicateId(@RequestBody Map<String, String> userId) {
//        // 1. 중복 체크를 진행할 때
//        Boolean isInID = memberService.duplicate(userId.get("user_id"));
//        return new ResponseEntity<>(isInID, HttpStatus.OK);
//    }


    // @JsonNaming Test
//    @PostMapping("/join")
//    public ResponseEntity<Member> join(@RequestBody Member member) {
//        if(Validator.isAlpha(member.getName())) {
//            // 유저 이름을 log로 출력
//            log.info(member.toString());
//            // Repository에 저장 시도
////            String user_id = memberService.join(member);
//            Member user = memberService.join(member);
//            try{
////                log.info(user_id);
//                log.info(user.getUserId());
//            } catch(NullPointerException e) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
////            return new ResponseEntity<>(user_id, HttpStatus.CREATED);
//            return new ResponseEntity<Member>(user, HttpStatus.CREATED);
//        } else
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

