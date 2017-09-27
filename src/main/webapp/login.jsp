<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 14025
  Date: 2017/9/1
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文档获取爬虫</title>

    <link rel="shortcut icon" href='<c:url value="/images/icons/favicon.ico"/>' type="image/x-icon">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <%@include file="/common/libs.jsp"%>

    <style>
        .panel-group{max-height:770px;overflow: auto;}
        .leftMenu{margin:10px;margin-top:5px;}
        .leftMenu .panel-heading{font-size:14px;padding-left:20px;height:36px;line-height:36px;color:white;position:relative;cursor:pointer;}/*转成手形图标*/
        .leftMenu .panel-heading span{position:absolute;right:10px;top:12px;}
        .leftMenu .menu-item-left{padding: 2px; background: transparent; border:1px solid transparent;border-radius: 6px;}
        .leftMenu .menu-item-left:hover{background:#C4E3F3;border:1px solid #1E90FF;}
    </style>

    <script type="text/javascript">
        $(function(){
            $(".panel-heading").click(function(e){
                /*切换折叠指示图标*/
                $(this).find("span").toggleClass("glyphicon-chevron-down");
                $(this).find("span").toggleClass("glyphicon-chevron-up");
            });
        });
    </script>

</head>
<body>
    <div class="row" style="margin-top: 4px">
        <div class="panel panel-primary" style="float: left;margin-left: 20px;margin-bottom: -3px">
            <div class="panel-body" style="width: 1690px">
                这是一个基本的面板
            </div>
        </div>
        <div class="col-md-2">
            <div class="panel-group table-responsive" role="tablist">
                <div class="panel panel-primary leftMenu">
                    <div class="panel-heading" id="collapseListGroupHeading1" data-toggle="collapse" data-target = "#collapseListGroup1" role="tab">
                        <h4 class="panel-title">
                            分组1
                            <span class="glyphicon glyphicon-chevron-up right"></span>
                        </h4>
                    </div>
                    <div id="collapseListGroup1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="collapseListGroupHeading1">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <!-- 利用data-target指定URL -->
                                <button class="menu-item-left" data-target="index.jsp">
                                   分组项1-1
                                </button>
                            </li>
                            <li class="list-group-item">
                                <button class="menu-item-left">
                                  分组项1-2
                                </button>
                            </li>
                        </ul>
                    </div>
                    <div class="panel-heading" id="collapseListGroupHeading2" data-toggle="collapse" data-target = "#collapseListGroup2" role="tab">
                        <h4 class="panel-title">
                            分组2
                            <span class="glyphicon glyphicon-chevron-up right"></span>
                        </h4>
                    </div>
                    <div id="collapseListGroup2" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="collapseListGroupHeading1">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <!-- 利用data-target指定URL -->
                                <button class="menu-item-left" data-target="index.jsp">
                                    分组项2-1
                                </button>
                            </li>
                            <li class="list-group-item">
                                <button class="menu-item-left">
                                    分组项2-2
                                </button>
                            </li>
                        </ul>
                    </div>
                </div><!--panel end-->

            </div>
        </div>
        <div class="panel panel-primary leftMenu" style="float: left;margin-left: -23px">
            <div class="panel-body" style="width: 1400px">
                <iframe src="index.html"></iframe>
            </div>
        </div>
    </div>
</body>
</html>
