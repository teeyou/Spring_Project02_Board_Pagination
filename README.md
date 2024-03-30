# 스프링 게시판 학습 필기
- Youtube - https://www.youtube.com/playlist?list=PLV9zd3otBRt7rtRCuo-NIyBmd5Gh0Mq2Z
- Github - https://github.com/codingrecipe1/board_framework

## 추가한 것
- ErrorPage 세팅 (web.xml에서 error-page 세팅)
- /save, /update 페이지에서 뒤로가기 이벤트 처리
- interceptor 세팅 (servlet-context.xml 에서 인터셉터 설정)

## 의존성 주입
- @Autowired
1. 필드 주입 (가장 간단한 방법이지만, 변경의 가능성 있음)
2. 수정자 주입 (setter를 이용해서 주입. 변경의 가능성 있음)
3. 생성자 주입 - 가장 바람직한 방법 (final 키워드 사용해서 변경불가)

- lombok의 @RequiredArgsContructor 쓰면 선언만으로 생성자 주입 가능   
필드에 final을 쓰지않으면 에러발생. 반드시 final 키워드를 써야함

## Forward vs redirect
- Forward   
최초로 진입한 주소 그대로 보여짐   
request, response 객체를 최초진입한 페이지와 forward된 페이지에서 공유   
시스템에 변화가 생기지 않는 조회요청의 경우에 사용하는 것이 바람직함   
(게시글을 쓰고 응답하는 페이지에서 리프레쉬를 하면 여러개의 게시글이 생성되는 문제가 발생할 수 있기 때문에)

ex) 로그인이 필요한 페이지를 보여줄 때 사용. 어떤 주소로 접근했을 때 해당 페이지로 연결됐는지 알 수 있음

- Redirect   
마지막으로 진입하는 주소가 보여짐   
request, response 객체가 새롭게 생성됨   
시스템에 변화가 생기는 경우에 사용하는 것이 바람직함   
(게시글을 쓰고 응답하는 페이지에서 리프레쉬를 하더라도 처음에 생성된 request정보가 없어지기 때문에 중복문제 안생김) 

ex) 사용자가 지금은 사용하지않는 주소로 접근했을 때 리뉴얼된 주소로 이동시킬 때 사용

## interceptor 
- controller로 이동하기 전에 요청을 가로채서 처리

ex) 로그인 상태를 확인할 때 사용. 
로그인이 필요한 페이지에 대해서 매번 로그인 상태를 확인하는 코드를 작성하면 코드가 중복되고 관리가 어려움


# path parameter vs query parameter
- path parameter (users/1 , users/2)   
메소드에 따라서 해당 주소의 기능이 나뉨   
(GET - 조회, POST - 생성, PUT - 수정(없을시 생성), PATCH - 부분변경, DELETE - 삭제)   
REST API를 구성할 때 사용

- query parameter (users?id=1, users?id=2)    
필터링, 정렬, 검색 등에 대한 데이터를 보여줄 때 사용

## MySQL을 이용한 Paging
- select * from table order by id desc limit i, n;   
id기준 내림차순으로 정렬해서 i번째 데이터부터 n개씩 가져와서 페이징

## mybatis를 사용할 때, 여러개의 파라미터를 보내야 하는 경우
1. Map<K,V> 사용 - sql문에서 값을 세팅할 때 Map의 Key를 사용
2. DTO를 정의해서 사용

## 댓글 작성할 때 비동기처리 ajax vs axios
- ajax - 보통 jquery와 함께 사용하고, 콜백 형태로 처리함. 콜백지옥을 해결하기위해 Promise 객체를 생성해서 처리해야함
- axios - 기본적으로 프로미스 기반으로 설계되어 있어서 코드의 흐름을 파악하기 좋음

## @AttributedModel , @RequestBody , @RequestParam
- AttributedModel - Form의 데이터를 객체로 바인딩 할 때 사용
- RequestBody - 요청의 body에 실려오는 데이터를 객체로 바인딩 할 때 사용
- RequestParam - url에 있는 쿼리스트링의 데이터를 객체로 바인딩 할 때 사용
