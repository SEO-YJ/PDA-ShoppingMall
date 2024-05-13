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




