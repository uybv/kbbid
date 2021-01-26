# Overview
##### programming languages, frameworks, libraries, standard
* Java Spring Boot
* Vue.js
* MySQL
* Rest API
* Unit test report (allure)

##### Comments
* Open content, no specific 
> * *Nội dung mở, không có nghiệp vụ và logic cụ thể, tùy ý xây dựng*

#####  Difficult
* Never worked with Java and Spring framework
* No experience with Vue.js
* Unit testing, auto testing and reports with Allure

>* *Không có kinh nghiệm với Vue.js*
>* *Chưa từng làm việc với Java và Spring framework*
>* *Unit test và auto test trên java, báo cáo với allure*

# Timeline
##### 15/01: 
* Overview of the Spring framework, read document & tutorial
* Install Spring boot environment, use Maven
* Spring framework, java package & POM
* Building Rest API web service
* Accessing data, JPA
>* *Tổng quát về Spring framework, đọc doc và những hướng dân ban đầu*
>* *Cài đặt đặt môi trường Spring boot, chọn sử dụng Maven*
>* *Các package cơ bản của Spring*
>* *Spring boot với Rest API*
>* *Các phương thức Spring giao tiếp với DB, sử dụng JPA*
##### 18/01: 
* Authenticating
* Spring with CORS & CSRF...
* Unit test, test Rest API application with NgTest
* Report test result with Allure
>* *Auth trên Spring*
>* *Spring với các vấn đề về CORS, CSRF...*
>* *Unit test cơ bản với Spring, chọn NgTest*
>* *Report từ NgTest với Allure*
##### 19/01: 
* Learn vue.js, create projects quickly with vue.js
* Vue.js frontend, Spring Boot backend, compile vue.js with maven
>* *Làm quen với Vue.js, khởi tạo project nhanh với vue.js, bỏ qua việc tìm hiểu sâu về kiến trúc của vue.js*
>* *Tích hợp Vue.js vào trong môi trường build của Spring (Maven)*
##### 20/01:
* Create project, make Database
* Generate Entity and Repository
* Make Rest controller, CRUD
* Unit test for Rest controller
* Export report from test result with Allure
>* *Tạo project, làm DLL cơ bản*
>* *Khởi tạo các Entity, Repository tham chiếu DB*
>* *Tạo controller lấy dữ  và trả dữ liệu cơ bản*
>* *Viết Unit test cho các Rest controller đã tạo*
>* *Xuất report các unit test với Allure*
##### 21/01
* Add DTO layer (Service and ModelMapper), independent between Rest API response data construct and Entity
* Authentication class with JWT
* Update unit test
>* *Thay đổi kiến trúc, khởi tạo lớp DTO  bao gồm Service và ModelMapper => mục đích tạo ra các tham chiếu input và output cho endpoint thay vì mặc định các Entity*
>* *Cài đặt lớp Authencation, đưa JWT vào thay thế cho auth mặc định của Spring*
>* *Sửa đổi lại Unit test cho phù hợp*
##### 22/01
* Create frontend project with Vue.js, compiled concurrently with Maven
* Make viewer & component with mock data
* Connect to the Rest API via XHR Request
* Make Dockerfile, docker-compose for deploy
>* *Tạo project Vue.js, kết hợp vào build của Maven*
>* *Tạo các viewer và component với dữ liệu giả định*
>* *Kết nối Rest API của Spring, hoàn thành chương trình cơ bản*
>* *Xây dựng docker build, docker-compose để deploy chương trình*
##### 25/01
* Push source code to Github
* Send email report
>* *Kiểm tra lại lần cuối và đưa lên Github*
>* *Gửi email*


# Files description
- src/main/resources/init.sql [DLL]
- Dockerfile & docker-composer.yml [Docker deloy]

# Develop
- Spring start
```sh
$ mvn spring-boot:run
```
http://localhost:8081/

- Vue.js
```sh
$ cd src/client
$ npm run serve
```
http://localhost:8082/

- Report unit test
```sh
$ mvn clean test
$ allure serve allure-results
```

# Deploy docker

```sh
$ docker-composer build
$ docker-composer up --no-start
$ docker-composer start mysql_db
$ docker-composer start bid_app
```
URL: http://localhost:8080/

# Note
* Java and Spring framework can be very complete in terms of features built in, but difficult to reach for beginners
* Maybe due to unfamiliarity with the Java language, programmers spend more time on syntax than solving problems, it would be disastrous without IDE support
* Vue.js is extremely simple, very beneficial for newbies, but for developing projects with a large number of modules, I think refactoring is necessary.
>* Java và Spring framework có thể rất đầy đủ về mặt tính năng được tích hợp, nhưng khó tiếp cận cho người mới bắt đầu
>* Có thể do chưa quen với ngôn ngữ Java, nhưng cảm nhận ban đầu là tốc độ viết code và triển khai chậm, lập trình phải dành nhiều thời gian cho cú pháp hơn là giải quyết vấn đề, sẽ thật là thảm họa nếu viết chúng mà không có sự hỗ trợ của IDE
>* Vue.js vô cùng đơn giản, điều này rất có lợi cho người mới, nhưng để phát triển các project lớn, số lượng các module nhiều thì cần được cấu trúc lại 
