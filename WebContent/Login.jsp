<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Login</title>
</head>
<body>

<form action="login" method="post">
  <div class="form-group">
    <label for="usuarioInput">Usuário</label>
    <input type="text" class="form-control" value="esdras" name="usuario" aria-describedby="emailHelp" placeholder="Usuário">
    <small id="emailHelp" class="form-text text-muted">We'll never share your account date with anyone else. Unless master, teacher, guru, Edson.</small>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" name="senha" value="1234" placeholder="Password">
  </div>
  
  <button type="submit"  value="Logar" class="btn btn-primary">Submit</button>
</form>

</body>
</html>