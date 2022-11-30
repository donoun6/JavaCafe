<%@page import="com.test.JavaCafe.menuitem.Command.MenuCommand"%>
<%@page import="org.springframework.web.bind.annotation.ModelAttribute"%>
<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@	page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@ page import="com.test.JavaCafe.kiosk.service.*" %>
<%@ page import="com.test.JavaCafe.*" %>
<%@ page import="com.test.JavaCafe.menuitem.*" %>
<%@ page import="com.test.JavaCafe.menuitem.Command.*" %>
<%@page import="java.util.List" %>
<%@page import="org.springframework.ui.Model" %>
<!doctype html>
<html lang="ko" class="h-100">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
  <meta name="generator" content="Hugo 0.101.0">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1" />
  <title>Main</title>
  <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/cover/">
  <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>

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

  <link rel="stylesheet" href="<c:url value='/resources/css/cover.css'/>" />

</head>

<body class="d-flex h-100 text-center" style="background:white;">

  <div class="d-flex w-100 h-100 p-5 mx-auto flex-column">
    <header class="headerwrap">
      <div>
        <h3 class="float-md-start mb-0">Java Pos <em style="font-size:12px;">kiosk</em></h3>
      </div>
    </header>
    <main class="px-3">
      <div class="wrap">
        <div class="main">
          <form:form method="get" modelAttribute="menu" class="formWrap" >
            <c:forEach var="category" items="${category_list }">
              <input type="submit" value=${category.category } id="category" name="category" class="categoryInput">
            </c:forEach>
          </form:form>
          <div class="container">
            <div class="row">
              <c:forEach var="l" items="${categorylist }">
                <div class="col-2">
                  <div class="card">
                    <img src="<c:url value='/resources/images/${l.image }'/>" class="card-img-top">
                    <div class="card-body">
                      <h5 class="card-title">${l.name }</h5>
                      <p class="card-text">${l.price }원</p>
                      <form:form method="post" modelAttribute="cart">
                      <input type="checkbox" checked="checked" value="${orderCount+1000 }" id="count" name="count" style="display: hidden; position: absolute; top: -2000px;">
                      <input type="checkbox" checked="checked" value="${l.price }" id="price" name="price" style="display: hidden;  position: absolute; top: -2000px;">
                      <button type="submit" value=${l.name } id="name" name="name" class="btn btn-dark" >주문하기</button>
                      </form:form>
                    </div>
                  </div>
                </div>
              </c:forEach>
            </div>
          </div>
        </div>
      </div>
    </main>
    <div style=" border-top: 1px solid gray; margin-top: 30px; padding-top: 30px; ">
    	<c:forEach var="x" items="${cartlist }" varStatus="status">
    		<div style="font-weight: 700; display: flex; justify-content: center; align-items: center; margin-bottom: 10px;">
	    		<span style=" width: 8vw; ">${x.name }</span><span style=" width: 5vw; ">${countLits[status.index] }EA</span> 
	    		<form method="get" style=" width: 4%; ">
	    			<input type="hidden" name="del" value="${x.name }">
	    			<button class="btn btn-dark">삭제</button> 
	    		</form>
      		</div>
      	</c:forEach>
      <c:forEach var="a" items="${addprice }">
      	<p style="margin-bottom: 0;margin-top: 20px;font-size: 21px;font-weight: bold;">총 가격 : ${a.price }원</p>
      </c:forEach><br>
      <form>
        <input type="submit" value="구매하기" class="btn btn-dark" style="/* margin-top: 10px; */margin-bottom: 10px;">
       	<input type="hidden" value="order" name="order">
      </form>
     </div>
    <footer class="mt-auto"> 
      <p>JavaCafe&copy</p>
    </footer>
  </div>
</body>

</html>