<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/6/30
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商店管理</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet"
          href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet"
          href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopmanagement.css">
    <script src="${pageContext.request.contextPath}/webjars/jquery/3.3.1-2/jquery.min.js"></script>
</head>
<body>
<header class="bar bar-nav">
    <h1 class="title">商店管理</h1>
</header>
<div class="content">
    <div class="content-block">
        <div class="row">
            <div class="col-50 mb">
                <a id="shopInfo" href="${pageContext.request.contextPath}/shop/shop_add"
                   class="button button-big button-fill">商铺信息</a>
            </div>
            <div class="col-50 mb">
                <a id="shopmanage" href="${pageContext.request.contextPath}/shop/productmanagement"
                   class="button button-big button-fill">商品管理</a>
            </div>
            <div class="col-50 mb">
                <a href="${pageContext.request.contextPath}/shop/productcategorymanagement"
                   class="button button-big button-fill">类别管理</a>
            </div>
            <div class="col-100 mb">
                <a href="${pageContext.request.contextPath}/shop/shoplist"
                   class="button button-big button-fill button-danger">返回</a>
            </div>
        </div>
    </div>
</div>
<script type='text/javascript' src='${pageContext.request.contextPath}/js/common/common.js' charset='utf-8'></script>
<script>
    $(function() {
        var shopId = getQueryString('shopId');
        var shopInfoUrl = '${pageContext.request.contextPath}/shopadmin/getshopmanagementinfo?shopId=' + shopId;
        $.getJSON(shopInfoUrl, function(data) {
            if (data.redirect) {
                window.location.href = data.url;
            } else {
                if (data.shopId != undefined && data.shopId != null) {
                    shopId = data.shopId;
                }
                $('#shopInfo').attr('href', '${pageContext.request.contextPath}/shop/shop_add?shopId=' + shopId);
                $('#shopcategory').attr('href', '${pageContext.request.contextPath}/shop/productcategorymanagement?shopId=' + shopId);
            }
        });
    });
</script>
<script type='text/javascript'
        src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript'
        src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript'
        src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
</body>
</html>

