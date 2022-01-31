# Category API README

카테고리 API입니다

> ### Note: 과제 제출용입니다.
> Ubuntu GUI 기준 설치, 빌드 방법  
> 1. 자바 설치 : sudo apt-get install openjdk-8-jdk  
> 2. H2DB 설치 : https://www.h2database.com/html/main.html 접속하여 H2DB 설치  
> 3. https://drive.google.com/file/d/12UfAeUKzuz63KKTMs0dGjDODgiviiIyM/view?usp=sharing 에서 war파일 다운로드 후 리눅스로 이동  
> 4. war파일 실행 : java -jar Category.war  
> 5. 실행하면 자동적으로 테이블 생성  
> 6. 유닛테스트 진행 시 기본 데이터로 셋팅 후 진행  

<br />

**개발환경:**
  - Java 1.8으로 개발하였습니다.
  - STS로 개발하였습니다.
  - Springboot + JPA로 개발하였습니다.
  - 데이터베이스는 H2DB을 사용하였습니다.
  - Encoding은 UTF-8 기준에서 개발하였습니다. 
  - 테스트는 Junit을 사용하였습니다.

  
<br />
  
## 목차

- [Database](#Database)
  - [DB접속정보](#DB접속정보)
  - [ERD](#ERD)
  - [DB정의서](#DB정의서)
- [등록](#등록)
  - [등록API](#등록API)
  - [Unit Test](#InsertUnitTest)
- [검색](#검색)
  - [검색API](#검색API)
  - [Unit Test](#SearchUnitTest)
- [수정](#Update)
  - [수정API](#수정API)
  - [Unit Test](#UpdateUnitTest)
- [삭제](#Delete)
  - [삭제API](#삭제API)
  - [Unit Test](#DeleteUnitTest)


<br />


## Database

#### DB접속정보
> *URL* : jdbc:h2:tcp://localhost/~/test  
> *ID* : sa  
> *Password* : (공란)  

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

#### InsertUnitTest

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

## 검색
#### 검색API
|TYPE|URL|PARAM(필수)|PARAM(선택)|
|------|---|------|------|
|GET|localhost:8080/search||ID|

<br />

#### SearchAllUnitTest

  - ****테스트명**** : search
  - ****상위 카테고리 지정 안했을 경우****
  ```markdown
  # localhost:8080/search
  ```
|PARAM|VALUE|
|-----|-----|
|ID|NULL|

#


#### SearchUnitTest

  - ****테스트명**** : searchID
  - ****상위 카테고리 지정 했을 경우****
  ```markdown
  # localhost:8080/search?id=2
  ```
|PARAM|VALUE|
|-----|-----|
|ID|2|

#

<br />

## 수정
#### 수정API
|TYPE|URL|PARAM(필수)|PARAM(선택)|
|------|---|------|------|
|GET|localhost:8080/update|ID|CATEGORY, PARENTID, ROWNUMS|

<br />

#### UpdateUnitTest

  - ****테스트명**** : updateOnlyCategory
  - ****카테고리만 수정 할 경우****
  ```markdown
  # localhost:8080/update?id=2&category=update category test
  ```
|PARAM|VALUE|
|-----|-----|
|ID|2|
|category|"update category test"|

#

  - ****테스트명**** : updateRownum
  - ****같은 부모 아래에서 행 수정 할 경우****
  ```markdown
  # localhost:8080/update?id=3&category=update rownum test&rownums=2
  ```
|PARAM|VALUE|
|-----|-----|
|ID|3|
|category|"update rownum test"|
|rownums|2|

#


  - ****테스트명**** : updateParentid
  - ****부모 수정 할 경우****
  ```markdown
  # localhost:8080/update?id=5&category=update parent test&rownums=2&parentid=3
  ```
|PARAM|VALUE|
|-----|-----|
|ID|5|
|category|"update parent test"|
|rownums|2|
|parentid|3|

#

<br />

## 삭제
#### 삭제API
|TYPE|URL|PARAM(필수)|PARAM(선택)|
|------|---|------|------|
|GET|localhost:8080/delete|ID||

<br />

  - ****테스트명**** : deleteSuccess
  - ****삭제 성공했을 경우****
  ```markdown
  # localhost:8080/delete?id=3
  ```
|PARAM|VALUE|
|-----|-----|
|ID|3|

#


  - ****테스트명**** : deleteFail
  - ****삭제 실패했을 경우****
  ```markdown
  # localhost:8080/delete?id=100
  ```
|PARAM|VALUE|
|-----|-----|
|ID|100|

#
