<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hotel Booking</title>
</head>
<body style="background:#F6FFA4">
<h1 style="text-align: center;color:#FFA8A8">WELCOME!!</h1>
<p style="text-align: center;color:#FFA8A8;font-size:35px"><i>Enjoy your stay...</i></p>
<div style="text-align: center;color:#FD5D5D;background-color:#FFD93D;margin-left:1cm;width:10cm;height:4.5cm">
<form action=adduser>

<label style="font-size:25px;text-align: center" for="name">NAME </label>
<input type="text" name="name" id="name"  value=" "/>
<br>
<label style="font-size:25px;text-align: center" for="email">EMAIL </label>
<input type="email" name="email" id="email"  value=" "/>
<br>
<label style="font-size:25px;text-align: center" for="password">PASSWORD </label>
<input type="password" name="password" id="password"  value=""/>
<br>
<br>
<label style="text-align: center" for="submit"></label>
<input type="submit" name="submit" id="submit"  value="submit"/>

</form>
</div>
</body>
</html>