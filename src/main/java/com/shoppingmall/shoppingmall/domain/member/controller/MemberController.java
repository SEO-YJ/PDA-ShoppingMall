package com.shoppingmall.shoppingmall.domain.member.controller;

import com.shoppingmall.shoppingmall.domain.member.dto.req.MemberLoginReq;
import com.shoppingmall.shoppingmall.domain.member.entity.Member;
import com.shoppingmall.shoppingmall.domain.member.dto.req.MemberDto;
import com.shoppingmall.shoppingmall.domain.member.service.MemberService;
import com.shoppingmall.shoppingmall.exception.DuplicateMemberIdException;
import com.shoppingmall.shoppingmall.utils.ApiUtils;
import com.shoppingmall.shoppingmall.utils.Validator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shoppingmall.shoppingmall.utils.ApiUtils.error;
import static com.shoppingmall.shoppingmall.utils.ApiUtils.success;

/**
 * 기능: Member 도메인의 Controller 클래스
 * 용도: Member 도메인에 대한 응답을 처리하는 Controller로 사용합니다.
 *
 * @author 최종 수정자: 서유준
 * @version 1.0, 작업 내용: 24.05.19 최신화
 * @see MemberController#join(MemberDto)
// * @see MemberController#joinByApiResult(MemberDto, BindingResult)
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
     * Member 회원가입을 처리하는 메소드입니다.
     *
     * @param memberDto 클라이언트에게 전달 받은 데이터를 저장할 member Entity 객체입니다.
     * @see MemberController
     * @return 201(Created) : 정상적으로 MemberDto가 Repository에 저장된 경우
     * @return 400(Bad Request): MemberDto의 name 멤버값이 문자열로 구성되어 있지 않은 경우
     * @return 409(Conflict) : userId가 Repository의 Map에 존재하는 경우
     * @return 500(Internal server error) : 전달한 MemberDto가 Map에 저장되지 않은 경우
     */
    @PostMapping("/members/join") // 직접 정의한 Response Format으로 반환
    public ApiUtils.ApiResult<String> join(@Valid @RequestBody MemberDto memberDto) {
        if (Validator.isAlpha(memberDto.getName())) {
            // 유저 이름을 log로 출력
            log.info(memberDto.toString());

            // ID 중복 체크
            // 중복이면 사용자 예외 클래스 소환
            //      1) 예외 클래스한테 니가 return 해!
            //      2) 예외만 발생 시키고.. 메세지는 내가 보낼게
            if (isDuplicateId(memberDto)) {
                return error("아이디 중복", HttpStatus.CONFLICT);
            }

            // Repository에 저장 시도
            String userId = memberService.join(memberDto);
            try {
                log.info(userId);
            } catch (NullPointerException e) {
                return error(userId, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return success(userId);
        } else
            return error("아이디 숫자 포함", HttpStatus.BAD_REQUEST);
    }

    // 중복 확인 버튼
    @PostMapping("/members/check")
    public ApiUtils.ApiResult<String> checkUsableId(@RequestBody MemberDto memberDto) {
        if(isDuplicateId(memberDto)) {
            throw new DuplicateMemberIdException("이미 사용 중인 아이디입니다.");
        } else {
            return success("사용 가능한 아이디입니다.");
        }
    }

    @PostMapping("/members/login")
    public ApiUtils.ApiResult<String> login(@Valid @RequestBody MemberLoginReq memberLoginReq) {
        String loginMemberName = memberService.login(memberLoginReq);

        try {
            log.info(loginMemberName);
        } catch (NullPointerException e) {
            error("로그인에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        return success(loginMemberName);
    }

    @GetMapping("/members")
    public ApiUtils.ApiResult<List<Member>> memberList() {
        List<Member> members = memberService.getMembers();
        return success(members);
    }

    private boolean isDuplicateId(MemberDto memberDto) {
        return memberService.checkDuplicateId(memberDto);
    }

//    @GetMapping("/datasource")
//    public ApiUtils.ApiResult makeConnection() {
//        Connection connect = memberService.makeConnection();
//        if(connect != null) {
//            return success(connect);
//        } else {
//            return error("연결 실패", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //    @PostMapping("/join")
//    public ApiUtils.ApiResult join(@Valid @RequestBody MemberDto memberDto) {
//        return success("success");
//    }

    // 유효성 검사하다가 에러가 터지면 호출되는 예외 처리 메소드
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiUtils.ApiResult<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException errors) {
//            Map<String, String> errorMessages = new HashMap<>();
//
//            for (FieldError error : errors.getFieldErrors()) {
//                String errorField = error.getField(); // 예외 field 명
//                String errorMessage = error.getDefaultMessage(); // 예외 message
//                errorMessages.put(errorField, errorMessage);
//            }
//            log.info("errorMessage={}");
//            return error(errorMessages, HttpStatus.BAD_REQUEST);
//    }

    //// Before
//    /**
//     * Member 회원가입을 처리하는 메소드입니다.
//     *
//     * @param memberDto 클라이언트에게 전달 받은 데이터를 저장할 member Entity 객체입니다.
//     * @see MemberController
//     * @return 201(Created) : 정상적으로 MemberDto가 Repository에 저장된 경우
//     * @return 400(Bad Request): MemberDto의 name 멤버값이 문자열로 구성되어 있지 않은 경우
//     * @return 409(Conflict) : userId가 Repository의 Map에 존재하는 경우
//     * @return 500(Internal server error) : 전달한 MemberDto가 Map에 저장되지 않은 경우
//     */
//    @PostMapping("/join/res/en") // ResponseEntity로 저장 요청
//    public ResponseEntity<String> joinByResponseEntity(@RequestBody MemberDto memberDto) {
//        if (Validator.isAlpha(memberDto.getName())) {
//            // 유저 이름을 log로 출력
//            log.info(memberDto.toString());
//
//            // ID 중복 체크
//            // 중복이면 사용자 예외 클래스 소환
//            //      1) 예외 클래스한테 니가 return 해!
//            //      2) 예외만 발생 시키고.. 메세지는 내가 보낼게
//            if (isDuplicateId(memberDto)) {
//                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
//            }
//
//            // Dto를 Service로 전달하여, Repository에 저장 시도
//            String user_id = memberService.join(memberDto);
//
//            try {
//                log.info(user_id);
//            } catch (NullPointerException e) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            return new ResponseEntity<>(user_id, HttpStatus.CREATED);
//        } else
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }


//    @PostMapping("/join/api/result") // After
//    public ApiUtils.ApiResult<String> joinByApiResult(@Valid @RequestBody MemberDto memberDto, Errors errors) {
//        try {
//            if (errors.hasErrors()) {
//                throw new NotValidException(errors.getObjectName());
//            }
//        } catch(NotValidException e) {
//            log.info(e.getMessage() + " 가 유효성 검사를 통과하지 못하였습니다!");
//            return error("유효성 검사 실패", HttpStatus.CONFLICT);
//        }
//
//
//        if (Validator.isAlpha(memberDto.getName())) {
//            // 유저 이름을 log로 출력
//            log.info(memberDto.toString());
//
//            // ID 중복 체크
//            // 중복이면 사용자 예외 클래스 소환
//            //      1) 예외 클래스한테 니가 return 해!
//            //      2) 예외만 발생 시키고.. 메세지는 내가 보낼게
//            if (isDuplicateId(memberDto)) {
//                return error("아이디 중복", HttpStatus.CONFLICT);
//            }
//
//            Member requestMember = memberDto.convertToEntity();
//
//            // Repository에 저장 시도
//            String userId = memberService.join(requestMember);
//
////            {
////	                “success” : True,
////	                “response” : 응답 데이터(객체),
////	                “error” : null
////            }
//
//            try {
//                log.info(userId);
//            } catch (NullPointerException e) {
//                return success(userId);
//            }
//            return success(userId);
//        } else
//            return error("아이디 숫자 포함", HttpStatus.BAD_REQUEST);
//    }
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

