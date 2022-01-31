# Category API README

카테고리 API입니다

> Note: 과제 제출용입니다.

<br />

**개발환경:**
  - Java 1.8으로 개발하였습니다.
  - 데이터베이스는 H2DB을 사용하였습니다.
  - STS로 개발하였습니다.
  - Encoding은 UTF-8 기준에서 개발하였습니다. 
  
<br />
  
## API 목차

- [Database](#Database)
  - [DB접속정보](#DB접속정보)
  - [ERD](#ERD)
  - [DB정의서](#DB정의서)
- [등록](#등록)
  - [등록API](#등록API)
  - [Unit Test](#UnitTest)
- [검색](#Search)
  - [Call](#SearchCall)
  - [Unit Test](#SearchTest)
- [수정](#Update)
  - [Call](#UpdateCall)
  - [Unit Test](#UpdateTest)
- [삭제](#Delete)
  - [Call](#DeleteCall)
  - [Unit Test](#DeleteTest)


<br />


## Database

#### DB접속정보
- *URL* : jdbc:h2:tcp://localhost/~/test
- *ID* : sa
- *Password* : (공란)

#### DB정의서
|Column|Type|설명|
|------|---|------|
|ID|INTEGER|PK, AI|
|CATEGORY|VARCHAR(255)||
|PARENT_ID|INTEGER|FK|
|ROWNUMS|INTEGER||

#### ERD
<img width="296" alt="ERD" src="https://user-images.githubusercontent.com/44846071/151783855-789c6c62-1eaa-496c-ae2d-5e576564c19c.PNG">

<br /><br />

## 등록
#### 등록API
|TYPE|URL|PARAM(필수)|PARAM(선택)|
|------|---|------|------|
|GET|localhost:8080/insert|CATEGORY, PARENTID|ROWNUMS|

<br />

#### UnitTest

  - ****테스트명**** : insertSuccess
  - ****INSERT 성공시****
  ```markdown
  # localhost:8080/insert?CATEGORY=insert test&PARENTID=1&ROWNUMS=1
  ```
|PARAM|VALUE|
|-----|-----|
|CATEGORY|"insert test"|
|PARENTID|1|
|ROWNUMS|1|

#

  - ****테스트명**** : categorynameInsertFail
  - ****카테고리명 PARAM 없을경우****
  ```markdown
  # localhost:8080/insert?PARENTID=1&ROWNUMS=1
  ```
|PARAM|VALUE|
|-----|-----|
|CATEGORY|NULL|
|PARENTID|1|
|ROWNUMS|1|

#

  - ****테스트명**** : parentInsertFail
  - ****부모ID PARAM 없을경우****
  ```markdown
  # localhost:8080/insert?CATEGORY=insert test&ROWNUMS=1
  ```
|PARAM|VALUE|
|-----|-----|
|CATEGORY|"insert test"|
|PARENTID|1|
|ROWNUMS|NULL|

#

<br />

<br /><br /><br /><br /><br /><br /><br /><br /><br /><br />


