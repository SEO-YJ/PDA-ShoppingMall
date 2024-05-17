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

/**
 * 기능: Member Controller 클래스
 * 용도: Member 도메인에 대한 응답을 처리하는 Controller로 사용합니다.
 *
 * @author 최종 수정자: 서유준
 * @version 1.0, 작업 내용: 24.05.16 최신화
 * @see MemberController#joinByResponseEntity(Member)
 * @see MemberController#joinByApiResult(Member)
 */
@Slf4j
@RestController
@AllArgsConstructor
public class MemberController {

    /**
     * Member에 대한 요청을 처리하기 위해 사용되는 Service 계층 변수
     */
    MemberService memberService;

    /**
     * message 멤버 필드를 반환하는 메소드입니다.
     *
     * @param member 클라이언트에게 전달 받은 데이터를 저장할 member Entity 객체입니다.
     * @see MemberController
     * @return .
     */
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
    public ApiUtils.ApiResult<String> joinByApiResult(@RequestBody MemberDto memberDTO) {
        if (Validator.isAlpha(memberDTO.getName())) {
            // 유저 이름을 log로 출력
            log.info(memberDTO.toString());

            // ID 중복 체크
            // 중복이면 사용자 예외 클래스 소환
            //      1) 예외 클래스한테 니가 return 해!
            //      2) 예외만 발생 시키고.. 메세지는 내가 보낼게
            if (isDuplicateId(memberDTO)) {
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

