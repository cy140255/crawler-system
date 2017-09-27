$(document).ready(function(){
    a();
    countdown();
    setInterval(countdown,1000);
    function a() {
        $("#myModal").modal("show");
        $("#myModal").modal({backdrop: 'static',show:true, keyboard: true});
    }
    function countdown () {
        var nowDate = new Date();
        var endDate = new Date("2018-01-01 00:00:00");
        var lastDate = endDate.getTime()-nowDate.getTime();
        var tempSecond = parseInt(lastDate/1000);
        var day = Math.floor(tempSecond/(24*60*60));
        var hour = Math.floor((tempSecond-(day*24*60*60))/3600);
        var minute = Math.floor((tempSecond-((day*24*60*60))-(hour*3600))/60);
        var second = Math.floor(tempSecond-((day*24*60*60))-(hour*3600)-(minute*60));
        var date =day+"天"+hour+"时"+minute+"分"+second+"秒";
        $("#p1").html(date);

    }


    $("#button").click(function () {
        var value =  $("#input").val();
        var strRegex = "^((https|http|ftp|rtsp|mms)?:\/\/)[^\s]+";

        var re=new RegExp(strRegex);
        if (!re.test(value)){
            $("#p").css("visibility","");
        }else {
//                $.ajax({
//                    type:"post",
//                    url:"login/login.do",
//                    data:"url="+ value,
//                    error:function (result) {
//                        alert(1);
//                    }
//                })
            window.location.href="login/login.do?url="+value;
        }
    })
    $("#input").focus(function () {
        $("#p").css("visibility","hidden");
    })


    $("#confirm").click(function () {
        $.ajax({
            type:"post",
            url:"confirm/idConfirm.do",
            data:"id="+$("#in").val(),
            success:function (result) {
                if (result=="success"){
                    $("#modal-5").modal("hide");
                    $("#myModal").modal("hide");
                }
                if (result=="error"){
                    $("#error").css("visibility","");
                }
            },

        })
    });

    $("#in").bind('keypress',function (event) {
            if (event.keyCode=='13'){
                $.ajax({
                    type:"post",
                    url:"confirm/idConfirm.do",
                    data:"id="+$("#in").val(),
                    success:function (result) {
                        if (result=="success"){
                            $("#modal-5").modal("hide");
                            $("#myModal").modal("hide");
                        }
                        if (result=="error"){
                            $("#error").css("visibility","");
                        }
                    },

                })
            }
    })

    $("#in").focus(function () {
        $("#error").css("visibility","hidden");
    })

});
