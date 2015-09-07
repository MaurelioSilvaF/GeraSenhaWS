$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/GeraSenhaWS/api/senhasAtuais"
    }).then(function(data) 
       $('col-md-1').append(data);
    });
});