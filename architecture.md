# API 명세서
## Response Format
### 성공
```json
{
  "success": True,
  "response": 응답 데이터(객체),
  "error": null
}
```

### 실패
```json
{
  "success": False,
  "response": null,
  "error": {
    "message": 에러 메시지,
    "status": Http Status
  }
}
```

## Domain Driven Design (DDD)
### 상품
#### Create: 상품 등록
1. 상품 개별 등록
상품을 개별로 등록하는 API를 추가합니다.
등록할 상품 정보는 
URL에 담아 보내지 않고,
Request Body에 담아 Spring 서버로 전달합니다.

#### Read: 상품 조회
1. 상품 전체 조회
4개의 page로 paging 할 수 있도록 구현합니다.
2. 상품 카테고리별 조회
카테고리와 4개의 페이지로 구현합니다.
카테고리는 id로 전달합니다.
3. 상품 개별 조회 

#### Update: 상품 수정
1. 상품 개별 수정
개별 상품의 데이터를 수정하도록 구현합니다.
2. 상품 여러 개 수정

#### Delete: 상품 삭제
1. 상품 개별 삭제
2. 상품 여러 개 삭제
POST를 통해, RequestBody에 삭제할 상품들의 ID를 전달하여
삭제하도록 구현합니다.

### 주문

### 장바구니

### 회원
#### 회원가입
#### 로그인
#### 회원정보 수정
#### 회원 탈퇴





