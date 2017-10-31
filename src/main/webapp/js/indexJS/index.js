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
            window.location.href="login.do?url="+value;
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
    });

    $("#register-username").popover({
            trigger:'manual'
    }).on('focus',function () {
        $(this).popover('show');
    }).on('blur',function () {
        $(this).popover('hide');
    });


    $("#register-password").popover({
        trigger:'manual'
    }).on('focus',function () {
        $(this).popover('show');
    }).on('blur',function () {
        $(this).popover('hide');
    });

    $("#register-confirmpassword").popover({
        trigger:'manual'
    }).on('focus',function () {
        $(this).popover('hide');
    }).on('blur',function () {
        var password =$("#register-password").val();
        var confirmpassword= $("#register-confirmpassword").val();
        if (password != confirmpassword) {
            $(this).popover('show');
        }
        $("#register-password").popover({
            trigger:'manual'
        }).on('focus',function () {
            $("#register-confirmpassword").popover('hide');
        })

        $("#register-username").popover({
            trigger:'manual'
        }).on('focus',function () {
            $("#register-confirmpassword").popover('hide');
        })
    });


    $("#btn-close").click(function () {
        $("#register-confirmpassword").popover('hide');
    })

    $("#btn-register").click(function () {
        var $btn = $(this).button('loading')
            var username = $("#register-username").val();
            var password = $("#register-password").val();
            var confirmpassword = $("#register-confirmpassword").val();

            <!-- 判断input 标签内是否有填入数据， 如果没有则标红显示-->
            if (username == null || username == ""){
                $("#register-username").css("border","1px solid red");

            }
            if (password == null ||password == ""){
                $("#register-password").css("border","1px solid red");

            }

            if (confirmpassword == null ||confirmpassword == ""){
                $("#register-confirmpassword").css("border","1px solid red");

            }

            if (username == null || username == ""
                ||password == null ||password == ""
                ||confirmpassword == null ||confirmpassword == ""
            ){
                $btn.button('reset');
                return;
            }
            <!-- 这里结束，并放开确定按钮，并跳出程序 -->
            if (password != confirmpassword){
                $("#register-confirmpassword").popover('show');
                $btn.button('reset');
            }else {
                $.ajax({
                    type: "POST",
                    url: "register.do",
                    cache: true,
                    data: {userName: $("#register-username").val(), password: $("#register-password").val()},
                    success: function (data) {

                        if (data != null) {
                            $("#modal-1").modal("hide");
                            var obj = eval(data);
                            $('#a-register').css("display", "none");
                            $('#a-login').css("display", "none");
                            $('#a-username').html(obj[obj.length - 1].userName);
                            $('#a-username').css("display", "block");
                            $('#a-exit').css("display", "block");

                            if (obj.length > 1) {
                                for (var i = 0; i < obj.length - 1; i++) {
                                    $("#ul-menu").append("<li><a id='user-" + i + "' data-toggle=''>" + obj[i].userName + "</a></li>");
                                }
                            } else {
                                $("#ul-menu").append("<li><a id='user-1'  data-toggle=''>暂无好友</a></li>");


                            }
                            $btn.button('reset');
                            $("#modal-3").modal("hide");
                        } else {
                            alert("服务器异常，请稍后重试");
                            $btn.button('reset');
                        }
                    }
                })


            }

     });





    $("#btn-login").click(function () {
        var $btn = $(this).button('loading');
        var username = $("#login-username").val();
        var password = $("#login-password").val();


        <!-- 判断input 标签内是否有填入数据， 如果没有则标红显示-->
        if (username == null || username == ""){
            $("#login-username").css("border","1px solid red");

        }

        if (password == null || password == ""){
            $("#login-password").css("border","1px solid red");

        }

        if (username == null || username == "" ||password == null || password == ""){
            $btn.button('reset');
            return;
        }

        <!--这里结束，并放开确定按钮，并跳出程序-->


        $.ajax({
            type:"POST",
            url:"loginIn.do",
            scriptCharset: 'utf-8',
            data:{userName:$("#login-username").val(),password:$("#login-password").val()},
            success:function (data) {
                 if(data=="error"){
                    alert("账号不存在或密码错误，请重试！");
                     $btn.button('reset');
                }else {
                     var obj = eval(data);

                     $('#a-register').css("display","none");
                     $('#a-login').css("display","none");

                     $('#a-username').html(obj[obj.length-1].userName);
                     $('#a-username').css("display","block");
                     $('#a-exit').css("display","block");

                     if (obj.length>1){
                         for(var i = 0;i< obj.length-1;i++){
                             $("#ul-menu").append("<li><a id='user-"+i+"' data-toggle='' onclick='getFriendName("+i+")' >"+obj[i].userName+"</a></li>");
                         }
                     }else {
                         $("#ul-menu").append("<li><a id='user-1'  data-toggle=''>暂无好友</a></li>");


                     }
                     $btn.button('reset');
                     $("#modal-2").modal("hide");
                        connect(obj[obj.length-1].userName);


                 }
            }
        })
    })


function getUserName() {

    $.ajax({
        type:"GET",
        url:"getUserName.do",
        scriptCharset: 'utf-8',
        success:function (data) {
            if (data!=null&&data!="") {
                $("#myModal").modal("hide");
                var obj = eval(data);

                $('#a-register').css("display","none");
                $('#a-login').css("display","none");

                $('#a-username').html(obj[obj.length-1].userName);
                $('#a-username').css("display","block");
                $('#a-exit').css("display","block");

                if (obj.length>1){
                    for(var i = 0;i< obj.length-1;i++){
                        $("#ul-menu").append("<li><a id='user-"+i+"' data-toggle='' onclick='getFriendName("+i+")' >"+obj[i].userName+"</a></li>");
                    }
                }else {
                    $("#ul-menu").append("<li><a id='user-1'  data-toggle='' onclick=''>暂无好友</a></li>");


                }

                $('#a-register').css("display","none");
                $('#a-login').css("display","none");



                $('#a-username').css("display","block");
                $('#a-exit').css("display","block");

                if (websocket != null && websocket != undefined &&websocket != ''){
                    websocket.close();
                }

                connect(obj[obj.length-1].userName);

            }
        }
    })

}

$('#a-exit').click(function () {
   exit();
});

function exit() {
        alert("是否确认退出？");
    $("#ul-menu li").remove();
        $.ajax({
            type:"GET",
            url:"exit.do",
            success:function (data) {
                if (data!=null&&data!="") {
                    $('#a-register').css("display","block");
                    $('#a-login').css("display","block");
                    $('#a-username').css("display","none");
                    $('#a-exit').css("display","none");
                    $("#modal-2").modal("hide");
                }
            }
        })

}



getUserName();



    var mouseOffsetX = 0;
    var mouseOffsetY = 0;
    var isDraging = false;
    //鼠标事件1 - 在标题栏上按下（要计算鼠标相对拖拽元素左上角的坐标，并且标记为可拖动）
    document.getElementById("modal-header-move").addEventListener('mousedown',function (event) {
        var e= event||window.event;
        mouseOffsetX = e.pageX-document.getElementById("ui-dialog").offsetLeft;
        mouseOffsetY = e.pageY-document.getElementById("ui-dialog").offsetTop;
        isDraging = true;
    });

    document.onmousemove = function (e) {
        var e= event||window.event;
        var mousex = e.pageX;
        var mousey = e.pageY;
        var movex = 0;
        var movey = 0;
        if (isDraging === true) {

            movex = mousex - mouseOffsetX;
            movey = mousey - mouseOffsetY;
            //范围限定  movex >0 并且movex<(页面最大宽度 - 浮层宽度)
            //           movey>0 并且movey<(页面最大高度 - 浮层高度)

            var pageWidthX = document.documentElement.clientWidth;
            var pageHeightY = document.documentElement.clientHeight;
            var dialogWidth =  document.getElementById("ui-dialog").offsetWidth;
            var dialogHeight =  document.getElementById("ui-dialog").offsetHeight;
            var maxX  = pageWidthX - dialogWidth;
            var maxY = pageHeightY - dialogHeight;

            movex = Math.min(maxX,Math.max(0,movex));
            movey = Math.min(maxY,Math.max(50,movey));

            document.getElementById("ui-dialog").style.left = movex + 'px';
            document.getElementById("ui-dialog").style.top = movey + 'px';
        }

    }
    document.onmouseup = function () {
                isDraging = false;
    };

    var websocket;
    window.onunload = function () {
        websocket.close();
    }

    function connect(username) {
        var url = "ws://localhost:8080/crawler-system/websocket/"+username;
        if ('WebSocket' in window) {
            websocket = new WebSocket(url)
        } else {
            alert('Not support websocket')
        }
        websocket.onopen = function (event) {
            $(".ui-dialog-chat").append("<div>在线中</div>");
        };

        websocket.onmessage = function (event) {
            var date = new Date().format('yyyy-MM-dd hh:mm:ss');
            $(".ui-dialog-chat").append("<div style='width: 280px;height: 20px;text-align: left' >" +
                "<span>"+friendName+"("+date+")"+"</span>" +
                "</div>" +
                "<div style=\"width: auto;height: 20px;text-align: left\" >\n" +
                "<span>" + event.data + "</span>" +
                "</div>");
            $("#ui-dialog-chat").scrollTop($("#ui-dialog-chat")[0].scrollHeight);
        }
    }



    $("#btn-ent").click(function () {
        var date = new Date().format('yyyy-MM-dd hh:mm:ss');
        var message = $("#input-message").val()+","+friendName;
        $(".ui-dialog-chat").append("<div style='width: 280px;height: 20px;text-align: right;' >" +
            "<span>"+$('#a-username').html()+"("+date+"):</span>" +
            "</div>" +
            "<div style=\" width: 280px;height: 20px;text-align: right;\" >\n" +
            "<span >" + $("#input-message").val()+ "</span>" +
            "</div>")
        $("#ui-dialog-chat").scrollTop($("#ui-dialog-chat")[0].scrollHeight);
        websocket.send(message);
    })





    $("#register-username").blur(function () {
        if ( $("#register-username").val()!= null &&  $("#register-username").val()!= ""){
            $("#register-username").css("border","");
            $("#img-loading").css("display",'block');
            $.ajax({
                type:"GET",
                url:"checkUserName.do",
                data:{username:$("#register-username").val()},
                success:function (data) {
                    if (data == "success"){
                        $("#img-loading").attr("src",'images/pass.png');
                        $("#span-msg").css("display","block").css("color","#1afa29").html("该账号可以使用");
                        $("#btn-register").attr("disabled", false);

                    }else {
                        $("#img-loading").attr("src",'images/unpass.png');
                       $("#span-msg").css("display","block").css("color","#d81e06").html("该账号已被使用");
                        $("#btn-register").attr("disabled", true);
                    }

                }
            })

        }
    });
    $("#register-password").blur(function () {
        if ( $("#register-password").val()!= null &&  $("#register-password").val()!= ""){
            $("#register-password").css("border","");
        }
    });
    $("#register-confirmpassword").blur(function () {
        if ( $("#register-confirmpassword").val()!= null &&  $("#register-confirmpassword").val()!= ""){
            $("#register-confirmpassword").css("border","");
        }
    });

    $("#login-username").blur(function () {
        if ( $("#login-username").val()!= null &&  $("#login-username").val()!= ""){
            $("#login-username").css("border","");
        }
    });

    $("#login-password").blur(function () {
        if ( $("#login-password").val()!= null &&  $("#login-password").val()!= ""){
            $("#login-password").css("border","");
        }
    });


});
var  friendName ;
function getFriendName(id) {
    var id = 'user-'+id;
    $("#ui-dialog").css("display",'block');
    friendName = $('#'+id+'').html();
}





Date.prototype.format =function(format)
{
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
        (this.getFullYear()+"").substr(4- RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length==1? o[k] :
                ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}