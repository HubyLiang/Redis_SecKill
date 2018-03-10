<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>秒杀</title>
</head>
<body>

    <h2>MacBookd 开始抢购了</h2>
    <form id="skForm" action="${pageContext.request.contextPath}/seckill">
        <input type="hidden" id="user" name="user" value=""> 
        <input type="hidden" id="product" name="product" value="mac">
		<input type="button" id="seckill_btn" name="seckill_btn" value="秒杀">
    </form>

    <script type="text/javascript" src="script/jquery.min.js"></script>
    <script>
        $("#seckill_btn").click(function () {
            var user = "user_"+Math.floor(Math.random()*1000);
            $("#user").val(user);
            var url= $("#skForm").attr("action");
            var product = $("#product").val();
            $.ajax({
                url :  url,
                data    :   {"user":user,"product":product},
                type    :   "POST",
                success :   function (result) {
                    if(result == "false"){
                        alert("已抢光");
                        $("#seckill_btn").attr("disabled",true);
                    }
                }
            })
        })
    </script>

</body>
</html>