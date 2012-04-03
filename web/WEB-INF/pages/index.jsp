<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <script src="../../jquery.js"></script>
    <script type="text/javascript">

        function answerJson(url)   {
            $("#id_submit").attr("disabled", true);
            $("#id_delete").attr("disabled", true);
            var email = $("#id_email").val();
            if(isValidEmail(email)) {
                $.getJSON(url, {email: $("#id_email").attr("value")},function (json) {
                    if(json.err == false) {
//                        $("#id_formSend").empty();
                        $("#id_error").empty();
                        $("#id_error").css({
                            "color": "green"
                        });
                        $("#id_error").append(json.mess);
//                        $("#id_formSend").append(json.answer.mess);
                        $("#id_submit").attr("disabled", false);
                        $("#id_delete").attr("disabled", false);
                        $("#id_email").attr("value", "");
                    }
                    else  {
                        $("#id_error").empty();
                        $("#id_error").css({
                            "color": "red"
                        });
                        $("#id_error").append(json.mess);
                        $("#id_submit").attr("disabled", false);
                        $("#id_delete").attr("disabled", false);

                    }
                });
            }
            else  {
                $("#id_error").empty();
                $("#id_error").css({
                    "color": "red"
                });
                $("#id_error").append("the e-mail is incorrectly entered!");
                $("#id_submit").attr("disabled", false);
                $("#id_delete").attr("disabled", false);
            }

        }
        function sendEmail() {
//            $("#id_submit").attr("disabled", true);
            answerJson("/adding");
//            $("#id_submit").attr("disabled", false);
        }
        function deleteEmail()  {
//            $("#id_delete").attr("disabled", true);
            answerJson("/deleting");
//            $("#id_delete").attr("disabled", false);
        }
        function isValidEmail(emailAddress) {
            var pattern = new RegExp(/^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z]{2,4})/i);
            return pattern.test(emailAddress);
        }

        function test() {
            $.ajax({
                url: '/?email=dima',             // указываем URL и
                dataType : "text",                     // тип загружаемых данных
                success: function (data) { // вешаем свой обработчик на функцию success
                    $("#id_formSend").empty();
                    $("#id_formSend").append(data);
                }
            });
//            document.location.href = "/?email=dima";
        }

    </script>

</head>
<body>
<div id = "id_formSend">
    <form action="">
        Enter your e-mail
        <input id = "id_email" type="text"> </input>
        <%--<input id = "id_submit"  type="button" value="Submit" onclick="test();"/>--%>
        <input id = "id_submit"  type="button" value="Submit" onclick="sendEmail();"/>
        <input id = "id_delete" type = "button" value = "Unsubscribe" onclick="deleteEmail();"/>
    </form>
</div>
<div id ="id_error" <%--style="color: red"--%>></div>
</body>
</html>