<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/6/30
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商店列表</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/sm.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/sm-extend.css">

    <script src="${pageContext.request.contextPath}/webjars/jquery/3.3.1-2/jquery.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/shoplist.css">

</head>
<body>
<header class="bar bar-nav">
    <h1 class="title">商店列表</h1>
</header>
<div class="content">
    <div class="content-block">
        <p>
            你好,<span id="user-name"></span><a class="pull-right"
                                              href="${pageContext.request.contextPath}/shop/shop_add">增加店铺</a>
        </p>
        <div class="row row-shop">
            <div class="col-40">商店名称</div>
            <div class="col-40">状态</div>
            <div class="col-20">操作</div>
        </div>
        <div class="shop-wrap"></div>
    </div>
    <div class="content-block">
        <div class="row">
            <div class="col-33">
                <a href="${pageContext.request.contextPath}/o2o/local/accountbind?usertype=2"
                   class="button button-big button-fill button-success">帐号绑定</a>
            </div>
            <div class="col-33">
                <a href="#" id="log-out" usertype="2"
                   class="button button-big button-fill button-danger">退出系统</a>
            </div>
            <div class="col-33">
                <a href="${pageContext.request.contextPath}/o2o/local/changepsw?usertype=2"
                   class="button button-big button-fill button-success"
                   id="bindOrChange">修改密码</a>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'
        src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript'
        src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript'
        src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
<script>
    $(function () {
        getlist();
        function getlist() {
            $.ajax({
                url:'/shopadmin/getshoplist',
                type:'get',
                dataType:'json',
                success:function (data) {
                    if (data.success){
                        handleList(data.shopList);
                        handleUser(data.user);
                    }
                }
            });
        }

        function handleUser(data) {
            $('#user-name').text(data.name);
        }

        function handleList(data) {
            var html = '';
            data.map(function(item, index) {
                html += '<div class="row row-shop"><div class="col-40">'
                    + item.shopName + '</div><div class="col-40">'
                    + shopStatus(item.enableStatus)
                    + '</div><div class="col-20">'
                    + goShop(item.enableStatus, item.shopId) + '</div></div>';
            });
            $('.shop-wrap').html(html);
        }

        function shopStatus(status) {
            if (status == 0)
                return '审核中';
            else if (status == -1)
                return '店铺非法';
            else if (status == 1)
                return '审核通过';
        }

        function goShop(status, id) {
            if (status == 1){
                return '<a href="${pageContext.request.contextPath}/shop/shopmanagement?shopId='+id+'">进入</a>';
            }
            else return '';
        }
    });
</script>
</body>
</html>
