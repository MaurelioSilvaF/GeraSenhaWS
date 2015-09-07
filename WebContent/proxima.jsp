<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>
            Sistema de Senhas pela Internet
        </title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    
    <body>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
		
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">
                            Toggle navigation
                        </span>
                        <span class="icon-bar">
                        </span>
                        <span class="icon-bar">
                        </span>
                        <span class="icon-bar">
                        </span>
                    </button>
                    <a class="navbar-brand">Sistemas de Senhas</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="zerar.jsp">Zerar</a>
                        </li>
						<li>
                            <a href="proxima.jsp">Chamar Pr&oacute;xima</a>
                        </li>
						<li>
                            <a href="painel.jsp">Painel</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div id="main" class="container-fluid">
            <br/>
            <h3 class="page-header">
                Senha chamada...
            </h3>
            <div class="row">
                <div class="col-md-8">
                    <p>
						<object style="max-width:445px; width:100%; height:400px;" type="text/html" data="/GeraSenhaWS/api/proximaSenha">
						</object>
                    </p>
                </div>
                
            </div>
            <hr />
            
        </div>
               
    </body>

</html>