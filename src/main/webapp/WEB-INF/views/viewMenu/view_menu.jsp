<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ko" class="h-100">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>view</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/cover/">




	<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>"/>

    <style>
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=Varela+Round&display=swap');

    .bd-placeholder-img {
     font-size: 1.125rem;
     text-anchor: middle;
     -webkit-user-select: none;
     -moz-user-select: none;
     user-select: none;
   }

   @media (min-width: 768px) {
     .bd-placeholder-img-lg {
       font-size: 3.5rem;
     }
   }

   .b-example-divider {
     height: 3rem;
     background-color: rgba(0, 0, 0, .1);
     border: solid rgba(0, 0, 0, .15);
     border-width: 1px 0;
     box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
   }

   .b-example-vr {
     flex-shrink: 0;
     width: 1.5rem;
     height: 100vh;
   }

   .bi {
     vertical-align: -.125em;
     fill: currentColor;
   }

   .nav-scroller {
     position: relative;
     z-index: 2;
     height: 2.75rem;
     overflow-y: hidden;
   }

   .nav-scroller .nav {
     display: flex;
     flex-wrap: nowrap;
     padding-bottom: 1rem;
     margin-top: -1px;
     overflow-x: auto;
     text-align: center;
     white-space: nowrap;
     -webkit-overflow-scrolling: touch;
   }
    </style>


    <!-- Custom styles for this template -->
    	<link rel="stylesheet" href="<c:url value='/resources/css/cover.css'/>"/>
  </head>
  <body class="d-flex h-100 text-center">

 <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
   <header class="mb-auto">
     <div>
       <h3 class="float-md-start mb-0">Java Pos</h3>
     </div>
   </header>

   <main class="px-3">
     <div class="wrap">
 <div class="main">
 <h1><a href="main" style="text-decoration:none;">Java Cafe</a></h1>
	<h3>메뉴 보기</h3>
	   <form:form method="post" modelAttribute="menu">
   <label>카테고리 : </label>
     <form:select path="category">
       <form:options itemLabel="CategoryCode" itemValue="CategoryCode" items="${categoryProviderList }" />
       <input type="submit" value="보기">
     </form:select>
      </form:form><br>
	 <ul style="margin-top: 50px;">
      <li><a href="kiosk" style="text-decoration:none;">키오스크</a></li>
      <li><a href="addmenu" style="text-decoration:none;">메뉴등록</a></li>
      <li><a href="deletemenu" style="text-decoration:none;">메뉴삭제</a></li>
      <li><a href="addcategory" style="text-decoration:none;">카테고리추가</a></li>
      <li><a href="deletecategory" style="text-decoration:none;">카테고리삭제</a></li>
      <li><a href="view" style="text-decoration:none;">메뉴보기</a></li>
    </ul>
   </div>
 </div>
   </main>

   <footer class="mt-auto">
     <p>JavaCafe&copy</p>
   </footer>
 </div>



   </body>
 </html>