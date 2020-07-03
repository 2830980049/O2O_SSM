<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/6/30
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品分类管理</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/sm.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/sm-extend.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/productmanagement.css">

    <script src="${pageContext.request.contextPath}/webjars/jquery/3.3.1-2/jquery.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-confirm.css">
    <script src="${pageContext.request.contextPath}/js/dist/jquery-confirm.min.js"></script>

</head>
<body>
<header class="bar bar-nav">
    <a class="button button-link button-nav pull-left back"
       href="#" onclick="javascript:window.history.go(-1);"> <span class="icon icon-left"></span> 返回
    </a>
    <h1 class="title">商品管理</h1>
</header>
<div class="content">
    <div class="content-block">
        <div class="row row-product-category">
            <div class="col-33">商品名称</div>
            <div class="col-33">优先级</div>
            <div class="col-33">操作</div>
        </div>
        <div class="category-wrap"></div>
    </div>
    <div class="product-wrap">
<%--        <div class="col-33">#{商品名称}</div>--%>
<%--        <div class="col-33">#{优先级}</div>--%>
<%--        <div class="col-33">--%>
<%--            <a href="#">操作</a>--%>
<%--            <a href="#">删除</a>--%>
<%--            <a href="#">预览</a>--%>
<%--        </div>--%>
    </div>
    <div class="content-block">
        <div class="row">
            <div class="col-50">
                <a href="${pageContext.request.contextPath}/shop/shopmanagement" class="button button-big button-fill button-danger" >返回商店管理</a>
            </div>
            <div class="col-50">
                <a href="${pageContext.request.contextPath}/shop/productoperation" class="button button-big button-fill" id="submit">新增</a>
            </div>
        </div>
    </div>
</div>
<script type='text/javascript'
        src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript'
        src='${pageContext.request.contextPath}/js/dist/sm.js' charset='utf-8'></script>
<script type='text/javascript'
        src='${pageContext.request.contextPath}/js/dist/sm-extend.js' charset='utf-8'></script>
<script>
  $(function() {
    // 获取此店铺下的商品列表的URL
    var listUrl = '${pageContext.request.contextPath}/shop/getproductlistbyshop?pageIndex=1&pageSize=999';
    // 商品下架URL
    var statusUrl = '${pageContext.request.contextPath}/shop/modifyproduct';
    getList();
    /**
     * 获取此店铺下的商品列表
     *
     * @returns
     */
    function getList() {
      // 从后台获取此店铺的商品列表
      $.getJSON(listUrl, function(data) {
        if (data.success) {
          var productList = data.productList;
          var tempHtml = '';
          // 遍历每条商品信息，拼接成一行显示，列信息包括：
          // 商品名称，优先级，上架\下架(含productId)，编辑按钮(含productId)
          // 预览(含productId)
          productList.map(function(item, index) {
            var textOp = "下架";
            var contraryStatus = 0;
            if (item.enableStatus == 0) {
              // 若状态值为0，表明是已下架的商品，操作变为上架(即点击上架按钮上架相关商品)
              textOp = "上架";
              contraryStatus = 1;
            } else {
              contraryStatus = 0;
            }
            // 拼接每件商品的行信息
            tempHtml += '' + '<div class="row row-product">'
                + '<div class="col-33">'
                + item.productName
                + '</div>'
                + '<div class="col-20">'
                + item.priority
                + '</div>'
                + '<div class="col-40">'
                + '<a href="#" class="edit" data-id="'
                + item.productId
                + '" data-status="'
                + item.enableStatus
                + '">编辑</a>'
                + '<a href="#" class="status" data-id="'
                + item.productId
                + '" data-status="'
                + contraryStatus
                + '">'
                + textOp
                + '</a>'
                + '<a href="#" class="preview" data-id="'
                + item.productId
                + '" data-status="'
                + item.enableStatus
                + '">预览</a>'
                + '</div>'
                + '</div>';
          });
          // 将拼接好的信息赋值进html控件中
          $('.product-wrap').html(tempHtml);
        }
      });
    }
    // 将class为product-wrap里面的a标签绑定上点击的事件
    $('.product-wrap')
        .on(
            'click',
            'a',
            function(e) {
              var target = $(e.currentTarget);
              if (target.hasClass('edit')) {
                console.log(e.currentTarget.dataset.id);
                // 如果有class edit则点击就进入店铺信息编辑页面，并带有productId参数
                window.location.href = '${pageContext.request.contextPath}/shop/productoperation?productId='
                    + e.currentTarget.dataset.id;
              } else if (target.hasClass('status')) {
                // 如果有class status则调用后台功能上/下架相关商品，并带有productId参数
                changeItemStatus(e.currentTarget.dataset.id,
                    e.currentTarget.dataset.status);
              } else if (target.hasClass('preview')) {
                // 如果有class preview则去前台展示系统该商品详情页预览商品情况
                window.location.href = '${pageContext.request.contextPath}/frontend/productdetail?productId='
                    + e.currentTarget.dataset.id;
              }
            });
    function changeItemStatus(id, enableStatus) {
      // 定义product json对象并添加productId以及状态(上架/下架)
      var product = {};
      product.productId = id;
      product.enableStatus = enableStatus;
      $.confirm('确定么?', function() {
        // 上下架相关商品
        $.ajax({
          url : statusUrl,
          type : 'POST',
          data : {
            productStr : JSON.stringify(product),
            statusChange : true
          },
          dataType : 'json',
          success : function(data) {
            if (data.success) {
              $.toast('操作成功！');
              getList();
            } else {
              $.toast('操作失败！');
            }
          }
        });
      });
    }
  });
</script>
</body>
</html>
