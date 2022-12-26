# 📕 Culture-Stamp
## 소개
나만의 문화 생활 기록장
## 핵심 기능
1. 리뷰 작성
2. TODO
3. 태그 기능
4. 월별 통계 그래프
5. 연말 결산 그래프

## 애플리케이션 (Language & Framework)
- Language - Java 17
- Framework - Spring Framework (Spring Boot, Spring MVC, Spring Security)
- Data - JPA/Hibernate, Mysql
- Test - Junit5, Spring Test, Mockito, SpringMockK
- Build Tool - Gradle
## ERD

<img src="https://user-images.githubusercontent.com/64303211/199386771-13be3acb-a7c1-4db1-80fb-864fa1add3c5.png" width="2500" />

## Diagram
<img src="https://user-images.githubusercontent.com/64303211/194745216-5cbaef59-4aef-4636-9e22-4107c5d89a3f.jpeg" width="80%" height="60%"/>

## CONVENTION

| 태그                  | 설명                                                                      |
| --------------------- | ------------------------------------------------------------------------- |
| `Feat: `             | 새로운 기능을 추가할 경우                                                 |
| `Fix: `              | 버그를 고친 경우                                                          |
| `Design: `           | CSS 등 사용자 UI 디자인 변경                                              |
| `!BREAKING CHANGE: ` | 커다란 API 변경의 경우                                                    |
| `!HOTFIX: `          | 급하게 치명적인 버그를 고쳐야하는 경우                                    |
| `Style: `            | 코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우                     |
| `Refactor: `         | 프로덕션 코드 리팩토링                                                    |
| `Comment: `          | 필요한 주석 추가 및 변경                                                  |
| `Docs: `             | 문서를 수정한 경우                                                        |
| `Test: `             | 테스트 추가, 테스트 리팩토링(프로덕션 코드 변경 X)                        |
| `Chore: `            | 빌드 테스트 업데이트, 패키지 매니저를 설정하는 경우(프로덕션 코드 변경 X) |
| `Rename: `           | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우                        |
| `Remove: `           | 파일을 삭제하는 작업만 수행한 경우                                        |
