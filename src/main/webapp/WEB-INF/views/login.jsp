<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval var="uiMode" expression="@conf.getProperty('ui.run.mode')" />
<spring:eval var="opMode" expression="@conf.getProperty('operation.mode')" />
<c:choose>
	<c:when  test="${opMode eq 'stage'}">
		<c:redirect context="/" url="/"/>
	</c:when >
	<c:otherwise>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>uEngineSolutions Chatting</title>
		<link rel="shortcut icon" href="<c:url value='images/favicon.ico' />" />
		<script>var contextpath = '${pageContext.request.contextPath}';</script>
		<script src="<c:url value='/js/jquery/jquery-1.11.3.min.js' />"></script>
		<script src="<c:url value='/js/jquery/jquery-ui.min.js' />"></script>
		<script src="<c:url value='/js/util/ajaxUtil.js' />"></script>
		<script>
			
			$(document).ready(function(){
				$("#signUpTable").hide();
			});
		
			function signup() {
				$("#signUpTable").show();
				$("#signInTable").hide();
			}
			
			function confirm() {
				
				var jsondata = {
						"userId"		: $("#registId").val(),
						"userName"	: $("#registName").val(),
						"password"	: $("#registPassword").val()
				}
				
				ajaxPOST(contextpath + '/regist', jsondata, function(data){
					if(data.memberId > 0) {
						alert("등록되었습니다. 등록한 아이디로 로그인하세요!");
						$("#signInTable").show();
						$("#signUpTable").hide();
						$("#signUpBtn").hide();
					} else {
						alert("error");
					}
					
				});
			}
		</script>
	</head>
	<body>
		<form id="frm" name="frm"  method="post">
			<div  id="signInTable">
				<p>Sing-In</p>
				<table>
					<tbody>
						<tr>
							<td>
								Sign-In ID &nbsp;: <input type="text" name="loginId" value="" placeholder="아이디를 입력하세요." size="30" />
							</td>
						</tr>
						<tr>
							<td>
								Sign-In Password : <input type="password" name="loginPassword" value="" placeholder="패스워드를 입력하세요." size="30" />
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" value="sign-in" onclick="submit()" /> <input type="button" id="signUpBtn" value="sign-up" onclick="signup()" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div  id="signUpTable">
				<p>Sing-Up</p>
				<table>
					<tbody>
						<tr>
							<td>
								Sign-Up ID &nbsp;: <input type="text" name="registId" id="registId" value="" placeholder="아이디를 입력하세요." size="30" />
							</td>
						</tr>
						<tr>
							<td>
								Sign-Up Name : <input type="text" name="registName"  id="registName" value="" placeholder="이름을 입력하세요" size="30" />
							</td>
						</tr>
						<tr>
							<td>
								Sign-Up Password : <input type="password" name="registPassword" id="registPassword" value="" placeholder="패스워드를 입력하세요" size="30" />
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" value="confirm" onclick="confirm()" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</body>
</html>
	</c:otherwise>
</c:choose>