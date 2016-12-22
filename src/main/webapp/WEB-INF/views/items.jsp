<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Items</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Items</h1>
				<p>All the available items in our store</p>
			</div>
		</div>
	</section>

	<section class="container">
		<div class="row">
			<c:forEach items="${items}" var="item">
				<form:form  modelAttribute="cartItem" class="form-horizontal" >
					<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
						<div class="thumbnail">
							<div class="caption">
								<h3>${item.name}</h3>
								<p>${item.description}</p>
								<p>${item.initialPrice}USD</p>
	 							<p>
									<div class="form-group">
										<div class="col-lg-offset-2 col-lg-10">
											<input type="submit" id="btnAdd" class="btn btn-primary" value ="Add To Cart"/>
										</div>
									</div>
								</p>
	
							</div>
						</div>
					</div>
				</form:form>
			</c:forEach>
		</div>
	</section>
</body>
</html>
