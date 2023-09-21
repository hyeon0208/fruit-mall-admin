# 프룻 프룻 쇼핑몰 - 어드민 서버 🍎

<br>

---
## 🛠 사용 기술 스택


<p align="center">
📌 BackEnd
<br>
<br>
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/java 11-007396?style=flat-square&logo=java&logoColor=white"/>
<img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=white"/>
<img src="https://img.shields.io/badge/log4j2-BEFCFF?style=flat-square&logo=&logoColor=white"/>

<br>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>
<img src="https://img.shields.io/badge/Firebase-FFCA28?style=flat-square&logo=firebase&logoColor=white"/>
<img src="https://img.shields.io/badge/MyBatis-9EB0A2?style=flat-square&logo=&logoColor=white"/>
<img src="https://img.shields.io/badge/Kafka-231F20?style=flat-square&logo=apachekafka&logoColor=white"/>
<br>
<br>
</p>

<p align="center">
📌FrontEnd
<br>
<br>
<img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white"/>
<img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white"/>
<img src="https://img.shields.io/badge/jQuery-0769AD?style=flat-square&logo=jQuery&logoColor=white"/>
<img src="https://img.shields.io/badge/Axios-5A29E4?style=flat-square&logo=axios&logoColor=white"/>
<br>
<img src="https://img.shields.io/badge/TinyMCE-2596BE?style=flat-square&logo=TinyMCE&logoColor=white"/>
<img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square&logo=thymeleaf&logoColor=white"/>
<br>
</p>

---
## 💿 프로젝트 기능

- #### ️👨🏻‍🔧 관리자
|    기능    |              설명              |                               비고                               |
|:--------:|:----------------------------:|:--------------------------------------------------------------:|
|   상품등록   |       판매하고자 하는 상품을 등록.       |     상품명, 분류, 가격, 할인율, 수량, 대표이미지,<br/> 상품 상세 설명(텍스트 및 이미지)      |
|   상품관리   |     판매등록한 상품들을 확인하고 관리.      | 조건별(판매상태, 분류, 상품명) 검색으로 조건에 맞는 상품 확인.<br/>상품 수정 및 상품 판매 중지 가능. |
|   리뷰관리   | 구매자들의 리뷰를 관리하고 해당 리뷰에 답글 작성. |                  답변상태별, 리뷰작성기간별, 검색조건별 조회 가능.                  |
| 리뷰 답글 알림 |  구매자의 리뷰에 답글을 달 시 알림 메시지 전송  |                  답글 작성 이벤트 발생 시 Kafka로 메시지 전송                  |
<br>

---
## 📋 ERD
👉🏻 **ERD 링크 바로가기 :** https://www.erdcloud.com/d/N33PiySjCTmbMZxTq
![img.png](img.png)