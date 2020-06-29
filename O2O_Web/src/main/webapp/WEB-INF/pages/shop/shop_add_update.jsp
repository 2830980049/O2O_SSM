<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/6/28
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SUI Mobile Demo</title>
    <meta name="description" content="MSUI: Build mobile apps with simple HTML, CSS, and JS components.">
    <meta name="author" content="阿里巴巴国际UED前端">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">

    <!-- Google Web Fonts -->

    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/msgs.css">
    <link rel="apple-touch-icon-precomposed" href="/assets/img/apple-touch-icon-114x114.png">

    <script src="${pageContext.request.contextPath}/webjars/jquery/3.3.1-2/jquery.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/dist/sweetalert2.all.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/dist/sweetalert2.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sweetalert2.min.css">

<%--    <script src="${pageContext.request.contextPath}/js/dist/sweetalert2.min.js"></script>--%>
<%--    <script src="${pageContext.request.contextPath}/js/shop/shop_add.js"></script>--%>
</head>
<body>
<script>
    function changeCode(img) {
        // 生成 4位随机数 floor 返回小于等于x的最大整数:
        img.src = "${pageContext.request.contextPath}/Kaptcha?" + Math.floor(Math.random() * 100);
    }
    // 获得URL 里 shopId的值 例如 /?shopId=1 获得 值1
    function getShopId(name){
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)
            return decodeURIComponent(r[2]);
        return '';
    }

    // 实现前后端分离 解耦
    $(function () {
        //    获取店铺分类
        var shopId = getShopId('shopId');
        var isEdit = shopId ? true : false;
        var initUrl = '${pageContext.request.contextPath}/shopadmin/getshopinitinfo';
        var registerShopUrl = '${pageContext.request.contextPath}/shopadmin/registershop';
        var shopInfoUrl = '${pageContext.request.contextPath}/shopadmin/getshopById?shopId='+shopId;
        var editShopUrl = '${pageContext.request.contextPath}/shopadmin/updateShop';
        if (!isEdit)
            getShopInitInfo();
        else
            getShopInfo(shopId);

        function getShopInfo(){
            //  获取JSON $getJSON（访问URL，回调方法）
            $.getJSON(shopInfoUrl, function (data) {
                if (data.success){
                    var shop = data.shop;
                    $('#shop-name').val(shop.shopName);
                    $('#shop-addr').val(shop.shopAddr);
                    $('#shop-phone').val(shop.phone);
                    $('#shop-desc').val(shop.shopDesc);
                    var shopCategory = '<option data-id="'
                        + shop.shopCategory.shopCategoryId + '"selected>'
                        + shop.shopCategory.shopCategoryName + '</option>';
                    var areas = '';
                    data.areaList.map(function (item, index){
                        areas += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                    });
                    $('#shop-category').html(shopCategory);
                    // 不能选择
                    $('#shop-category').attr('disabled','disabled');
                    $('#area').html(areas);
                    // 默认选择原来地区
                    $("#area option[data-id='"+shop.area.areaId+"']").attr("selected","selected");
                }
            });
        }


        function getShopInitInfo(){
            //  获取JSON $getJSON（访问URL，回调方法）
            $.getJSON((isEdit?shopInfoUrl : initUrl), function (data) {
                if (data.success){
                    var temp = '';
                    var tempArea = '';
                    //  获取 shopCategoryList参数
                    data.ShopCategoryList.map(function (item, index) {
                        temp += '<option data-id="' + item.shopCategoryId + '">' +
                            item.shopCategoryName + '</option>';
                    });
                    data.areaList.map(function (item, index) {
                        tempArea +=  '<option data-id="' + item.areaId + '">' +
                            item.areaName + '</option>'
                    });
                    // 填充
                    $('#shop-category').html(temp);
                    $('#area').html(tempArea)
                }
            });
        }

        // 提交按钮的事件响应，分别对店铺注册和编辑操作做不同响应
        $('#submit').click(function () {
            // 创建shop对象
            var shop = {};
            if (isEdit)
                shop.shopId = shopId;
            shop.shopName = $('#shop-name').val();
            shop.shopAddr = $('#shop-addr').val();
            shop.phone = $('#shop-phone').val();
            shop.shopDesc = $('#shop-desc').val();
            shop.shopCategory = {
                shopCategoryId:$('#shop-category').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId: $('#area').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            var shopImg = $('#shop-img')[0].files[0];
            var formData = new FormData();
            var inputs = document.getElementsByTagName("input");
            var count = 0;
            for(var i = 0; i < inputs.length; i++){
                var msg = inputs[i].nextElementSibling;
                if (msg.className == "msg-success")
                    count++;
            }
            //var checks = document.getElementById();
            if(count != 5 && !isEdit && count != 4){
                alert("请完善信息");
                return;
            }
            formData.append('shopImg', shopImg);
            formData.append('shopStr',JSON.stringify(shop));
            var check_code = $('#checks').val();
            if(!check_code){
                alert("请输入验证码");
                return;
            }
            formData.append('check_code', check_code);
            $.ajax({
                url : (isEdit ? editShopUrl : registerShopUrl),
                type : 'post',
                data : formData,
                contentType : false,
                processData : false,
                cache : false,
                success:function (data) {
                    if(data.success)
                        alert("提交成功");
                    else
                        alert("提交失败");
                    $('#check_img').click();
                }
            });
        });
    });
</script>
<div class="page-group">
    <div id="page-layout" class="page">
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left back" href="/demos/form">
                <span class="icon icon-left"></span>
                返回
            </a>
            <h1 class="title">商店信息</h1>
        </header>
        <div class="content">
            <div class="list-block">
                <ul>
                    <!-- Text inputs -->
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">商铺名称</div>
                                <div class="item-input">
                                    <input type="text" minlength="0" maxlength="60" id="shop-name" placeholder="商铺名称" style="width: 200px" required>
                                    <span class="msg-default hidden"></span>
                                </div>
                            </div>
                        </div>
                    </li>
<%--                 商铺分类 下拉列表   --%>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">商铺分类</div>
                                <div class="item-input">
                                    <select id="shop-category">
                                    </select>
                                </div>
                            </div>
                        </div>
                    </li>
<%--                 区域分类 下拉列表   --%>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">所属区域</div>
                                <div class="item-input">
                                    <select id="area">
                                    </select>
                                </div>
                            </div>
                        </div>
                    </li>
<%--                 详细地址 text   --%>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">详细地址</div>
                                <div class="item-input">
                                    <input type="text" minlength="0" maxlength="200" id="shop-addr" placeholder="详细地址" required>
                                    <span class="msg-default hidden"></span>
                                </div>
                            </div>
                        </div>
                    </li>
<%--                 联系电话 text   --%>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">联系电话</div>
                                <div class="item-input">
                                    <input type="text" id="shop-phone" pattern="(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$" placeholder="联系电话" required>
                                    <span class="msg-default hidden"></span>
                                </div>
                            </div>
                        </div>
                    </li>
<%--                 缩略图 上传控件   --%>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">缩略图</div>
                                <div class="item-input">
                                    <input type="file" id="shop-img">
                                    <span class="msg-success"></span>
                                </div>
                            </div>
                        </div>
                    </li>
<%--                 店铺简介 textarea   --%>
                    <li class="align-top">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">店铺简介</div>
                                <div class="item-input">
                                    <textarea id="shop-desc" placeholder="店铺简介"></textarea>
                                </div>
                            </div>
                        </div>
                    </li>
<%--                 验证码 kaptcha   --%>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">验证码</div>
                                <input type="text" id="checks" required>
                                <span class="msg-success"></span>
                                <div class="item-input">
                                    <img src="${pageContext.request.contextPath}/Kaptcha" id="check_img" alt="点击更换" title="点击更换" onclick="changeCode(this);">
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="content-block">
                <div class="row">
                    <div class="col-50"><a href="#" class="button button-big button-fill button-danger">返回</a></div>
                    <div class="col-50"><a href="#" class="button button-big button-fill button-success" id="submit" >提交</a></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
<script type="text/javascript">

    $('#shop-name').blur(function () {
        if (this.validity.valueMissing) {
            this.nextElementSibling.innerHTML = '商铺名称不能为空';
            this.nextElementSibling.className = 'msg-error';
            this.setCustomValidity('商铺名称不能为空');
        }
        else if (this.validity.tooLong) {
            this.nextElementSibling.innerHTML = '不能多于60位';
            this.nextElementSibling.className = 'msg-error';
            this.setCustomValidity('不能多于60位');
        }
        else {
            this.nextElementSibling.innerHTML = '商铺名称格式正确';
            this.nextElementSibling.className = 'msg-success';
            this.setCustomValidity('商铺名称格式正确');
        }
    });
    $('#shop-name').focus(function () {
        this.nextElementSibling.innerHTML = '长度60位之间';
        this.nextElementSibling.className = 'msg-default';
    });

    $('#shop-addr').blur(function () {
        if (this.validity.valueMissing) {
            this.nextElementSibling.innerHTML = '详细地址不能为空';
            this.nextElementSibling.className = 'msg-error';
            this.setCustomValidity('详细地址不能为空');
        }
        else if (this.validity.tooLong) {
            this.nextElementSibling.innerHTML = '不能多于200位';
            this.nextElementSibling.className = 'msg-error';
            this.setCustomValidity('不能多于200位');
        }
        else {
            this.nextElementSibling.innerHTML = '详细地址格式正确';
            this.nextElementSibling.className = 'msg-success';
            this.setCustomValidity('详细地址格式正确');
        }
    });
    $('#shop-addr').focus(function () {
        this.nextElementSibling.innerHTML = '长度200位之间';
        this.nextElementSibling.className = 'msg-default';
    });

    $('#shop-phone').blur(function () {
        if (this.validity.valueMissing) {
            this.nextElementSibling.innerHTML = '电话不能为空';
            this.nextElementSibling.className = 'msg-error';
            this.setCustomValidity('电话不能为空');
        } else if (this.validity.patternMismatch) {
            this.nextElementSibling.innerHTML = '格式不正确';
            this.nextElementSibling.className = 'msg-error';
            this.setCustomValidity('格式不正确');
        } else {
            this.nextElementSibling.innerHTML = '格式正确';
            this.nextElementSibling.className = 'msg-success';
            this.setCustomValidity('');
            //  var data =document.getElementById("email").value;
            var data = $("#phone").val();
            if (!data) {   //用户没有输入任何内容
                return;
            }
        }
    });
    $('#shop-phone').focus(function () {
        this.nextElementSibling.innerHTML = '电话不能为空';
        this.nextElementSibling.className = 'msg-default';
    });
</script>
</body>
</html>
