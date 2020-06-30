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
          href="${pageContext.request.contextPath}/css/productcategorymanagement.css">

    <script src="${pageContext.request.contextPath}/webjars/jquery/3.3.1-2/jquery.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-confirm.css">
    <script src="${pageContext.request.contextPath}/js/dist/jquery-confirm.min.js"></script>

</head>
<body>
<header class="bar bar-nav">
    <a class="button button-link button-nav pull-left back"
       href="#" onclick="javascript:window.history.go(-1);"> <span class="icon icon-left"></span> 返回
    </a>
    <h1 class="title">商品分类管理</h1>
</header>
<div class="content">
    <div class="content-block">
        <div class="row row-product-category">
            <div class="col-33">类别</div>
            <div class="col-33">优先级</div>
            <div class="col-33">操作</div>
        </div>
        <div class="category-wrap"></div>
    </div>
    <div class="content-block">
        <div class="row">
            <div class="col-50">
                <a href="#" class="button button-big button-fill button-success"
                   id="new">新增</a>
            </div>
            <div class="col-50">
                <a href="#" class="button button-big button-fill" id="submit">提交</a>
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
    $(function () {
        var listurl = '${pageContext.request.contextPath}/shop/getproductcategorylist';
        var addurl = '${pageContext.request.contextPath}/shop/addproductcategory';
        var deleteurl = '${pageContext.request.contextPath}/shop/removeproductcategory';
        getList();
        function getList() {
            $.getJSON(listurl,function (data) {
                if (data.success){
                    var dataList = data.data;
                    $('.category-wrap').html('');
                    var contexts = '';
                    dataList.map(function (item,index) {
                       contexts += '' + '<div class="row row-product-category now">'
                           + '<div class="col-33 product-category-name">'
                           + item.productCategoryName
                           + '</div>'
                           + '<div class="col-33">'
                           + item.priority
                           + '</div>'
                           + '<div class="col-33"><a href="#" class="button delete" data-id="'
                           + item.productCategoryId
                           + '">删除</a></div>'
                           + '</div>';
                    });
                    $('.category-wrap').append(contexts);
                }
            });
        }
        $('#new').click(
            function () {
                var contexts = '<div class="row row-product-category temp">'
                    + '<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
                    + '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
                    + '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
                    + '</div>';
                $('.category-wrap').append(contexts);
            }
        );
        $('#submit').click(function () {
            // 遍历新增的行 新增的行 为temp 旧的为now
            var temps = $(".temp");
            var productCategoryList = [];
            temps.map(function (index, item) {
                var temp = {};
                temp.productCategoryName = $(item).find(".category").val();
                temp.priority = $(item).find('.priority').val();
                if (temp.productCategoryName && temp.priority)
                    productCategoryList.push(temp);
            });
            $.ajax({
                url:addurl,
                type:'post',
                data:JSON.stringify(productCategoryList),
                contentType:'application/json',
                success:function (data) {
                    if (data.success) {
                        alert("提交成功!");
                        getList();
                    }
                    else
                        alert("提交失败!");
                }
            });
        });

        // 删除 temp
      $('.category-wrap').on('click','.row-product-category.temp .delete',function (e) {
            console.log($(this).parent().parent());
            // 删除 temp 这行数据
            $(this).parent().parent().remove();
        });

        $('.category-wrap').on('click','.row-product-category.now .delete',function (e) {
            // 获取被点击的元素
            var target = e.currentTarget;
            $.confirm('确定删除？',function () {
                $.ajax({
                    url:deleteurl,
                    type:'post',
                    data:{
                        // 获取 data-id 的值
                        productCategoryId:target.dataset.id
                    },
                    dataType:'json',
                    success:function (data) {
                        if (data.success){
                            $.alert("删除成功！");
                            getList();
                        }
                        else
                            $.alert("删除失败！");
                    }
                });
            });
        });

    });
</script>
</body>
</html>
