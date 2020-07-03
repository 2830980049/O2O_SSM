<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/7/2
  Time: 1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品编辑</title>
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
</head>
<body>
<header class="bar bar-nav">
    <h1 class="title" id="titles">商品添加</h1>
</header>
<div class="content">
    <div class="list-block">
        <ul>
            <li>
                <div class="item-content">
                    <div class="item-media">
                        <i class="icon icon-form-name"></i>
                    </div>
                    <div class="item-inner">
                        <div class="item-title label">商品名称</div>
                        <div class="item-input">
                            <input type="text" id="product-name" placeholder="商品名称">
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="item-content">
                    <div class="item-media">
                        <i class="icon icon-form-email"></i>
                    </div>
                    <div class="item-inner">
                        <div class="item-title label">目录</div>
                        <div class="item-input">
                            <select id="category">
                            </select>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="item-content">
                    <div class="item-media">
                        <i class="icon icon-form-email"></i>
                    </div>
                    <div class="item-inner">
                        <div class="item-title label">优先级</div>
                        <div class="item-input">
                            <input type="number" id="priority" placeholder="数字越大越排前面">
                        </div>
                    </div>
                </div>
            </li>
            <!--				TODO 积分-->
            <!--				<li>-->
            <!--					<div class="item-content">-->
            <!--						<div class="item-media">-->
            <!--							<i class="icon icon-form-email"></i>-->
            <!--						</div>-->
            <!--						<div class="item-inner">-->
            <!--							<div class="item-title label">积分</div>-->
            <!--							<div class="item-input">-->
            <!--								<input type="number" id="point" placeholder="商品积分">-->
            <!--							</div>-->
            <!--						</div>-->
            <!--					</div>-->
            <!--				</li>				-->
            <li>
                <div class="item-content">
                    <div class="item-media">
                        <i class="icon icon-form-email"></i>
                    </div>
                    <div class="item-inner">
                        <div class="item-title label">原价</div>
                        <div class="item-input">
                            <input type="number" id="normal-price" placeholder="可选">
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="item-content">
                    <div class="item-media">
                        <i class="icon icon-form-email"></i>
                    </div>
                    <div class="item-inner">
                        <div class="item-title label">现价</div>
                        <div class="item-input">
                            <input type="number" id="promotion-price" placeholder="可选">
                        </div>
                    </div>
                </div>
            </li>
            <!-- 				<li>
                <div class="item-content">
                    <div class="item-media">
                        <i class="icon icon-form-email"></i>
                    </div>
                    <div class="item-inner">
                        <div class="item-title label">积分</div>
                        <div class="item-input">
                            <input type="number" id="point" placeholder="可选">
                        </div>
                    </div>
                </div>
            </li> -->
            <li>
                <div class="item-content">
                    <div class="item-media">
                        <i class="icon icon-form-email"></i>
                    </div>
                    <div class="item-inner">
                        <div class="item-title label">缩略图</div>
                        <div class="item-input">
                            <input type="file" id="small-img">
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="item-content">
                    <div class="item-media">
                        <i class="icon icon-form-email"></i>
                    </div>
                    <div class="item-inner detail-img-div">
                        <div class="item-title label">详情图片</div>
                        <div class="item-input" id="detail-img">
                            <input type="file" class="detail-img">
                            <!-- <input type="file" class="detail-img">
                            <input type="file" class="detail-img"> -->
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="item-content">
                    <div class="item-media">
                        <i class="icon icon-form-email"></i>
                    </div>
                    <div class="item-inner">
                        <div class="item-title label">商品描述</div>
                        <div class="item-input">
                            <textarea id="product-desc" placeholder="商品描述"></textarea>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="item-content">
                    <div class="item-media">
                        <i class="icon icon-form-email"></i>
                    </div>
                    <div class="item-inner">
                        <label for="j_captcha" class="item-title label">验证码</label> <input
                            id="j_captcha" name="j_captcha" type="text"
                            class="form-control in" placeholder="验证码" />
                        <div class="item-input">
                            <img id="captcha_img" alt="点击更换" title="点击更换"
                                 onclick="changeCode(this)" src="${pageContext.request.contextPath}/Kaptcha" />
                        </div>
                    </div>
                </div>
            </li>

        </ul>
    </div>
    <div class="content-block">
        <div class="row">
            <div class="col-50">
                <a href="${pageContext.request.contextPath}/shop/shopmanagement"
                   class="button button-big button-fill button-danger" id="back">返回商店管理</a>
            </div>
            <div class="col-50">
                <a href="#" class="button button-big button-fill" id="submit">提交</a>
            </div>
        </div>
    </div>
</div>
<script>
$(function () {
    //从URL里获取productId参数值
    var productId = getQueryString('productId');
    // 通过productId获取商品信息URL
    var infoUrl = '${pageContext.request.contextPath}/shop/getproductbyid?productId='+productId;
    // 获取当前店铺设定的商品类别列表URL
    var categoryUrl = '${pageContext.request.contextPath}/shop/getproductcategorylist';
    // 更新商品信息URL
    var productPostUrl = '${pageContext.request.contextPath}/shop/modifyProduct';
    // 由于商品添加和编辑使用的是同一个页面
    // 该标识符用来标明本次是添加还是编辑操作
    var isEdit = false;
    if (productId){
        // 若有productId则为编辑操作
        $('#titles').text('商品编辑');
        getInfo(productId);
        isEdit = true;
    }
    else{
        getCategory();
        productPostUrl = '${pageContext.request.contextPath}/shop/addproduct';
        alert(productPostUrl)
    }

    // 获取需要编辑商品的商品信息，并赋值给表单
    function getInfo(id) {
        $.getJSON(infoUrl,function (data) {
            if (data.success){
              alert(infoUrl);
                // 返回JSON当中获取product对象信息，并赋值给表单
                var product = data.product;
                $('#product-name').val(product.productName);
                $('#product-desc').val(product.productDesc);
                $('#priority').val(product.priority);
                // $('#point').val(product.point);
                $('#normal-price').val(product.normalPrice);
                $('#promotion-price').val(product.promotionPrice);
                // 获取原来商品类别以及该店铺的所有商品类别列表
                var optionHtml = '';
                var optionArr = data.productCategoryList;
                var optionSelected = product.productCategory.productCategoryId;
                // 生产前段HTML商品类型列表，并默认选择编辑前商品类别
                optionArr.map(function (item, index) {
                    var isSelect = (optionSelected == item.productCategoryId) ? 'selected'
                        : '';
                    optionHtml += '<option data-value="'
                        + item.productCategoryId
                        + '"'
                        + isSelect
                        + '>'
                        + item.productCategoryName
                        + '</option>';
                });
                $('#category').html(optionHtml);
            }
        });
    }

    // 为商品添加操作提供店铺该店铺下所有商品类别列表
    function getCategory() {
        $.getJSON(categoryUrl, function(data) {
            if (data.success) {
                var productCategoryList = data.data;
                var optionHtml = '';
                productCategoryList.map(function(item, index) {
                    optionHtml += '<option data-value="'
                        + item.productCategoryId + '">'
                        + item.productCategoryName + '</option>';
                });
                $('#category').html(optionHtml);
            }
        });
    }

    // 针对商品详情图控件组，若该控件组最后一个元素发生变化（即上传了图片）
    // 且控件总数未达到6个，则生成新的一个文件上传控件
    $('.detail-img-div').on('change','.detail-img:last-child',function () {
       if($('.detail-img').length < 6){
           $('#detail-img').append('<input type="file" class="detail-img">');
       }
    });

    // 提交按钮的事件响应，分别对商品添加和编辑操作做不同响应
    $('#submit').click(
        function() {
            // 创建商品json对象，并从表单里面获取对应的属性值
            var product = {};
            product.productName = $('#product-name').val();
            product.productDesc = $('#product-desc').val();
            product.priority = $('#priority').val();
            product.point = $('#point').val();

            product.normalPrice = $('#normal-price').val();
            product.promotionPrice = $('#promotion-price').val();
            // 获取选定的商品类别值
            product.productCategory = {
                productCategoryId : $('#category').find('option').not(
                    function() {
                        return !this.selected;
                    }).data('value')
            };
            product.productId = productId;

            // 获取缩略图文件流
            var thumbnail = $('#small-img')[0].files[0];
            // 生成表单对象，用于接收参数并传递给后台
            var formData = new FormData();
            formData.append('thumbnail', thumbnail);
            // 遍历商品详情图控件，获取里面的文件流
            $('.detail-img').map(
                function(index, item) {
                    // 判断该控件是否已选择了文件
                    if ($('.detail-img')[index].files.length > 0) {
                        // 将第i个文件流赋值给key为productImgi的表单键值对里
                        formData.append('productImg' + index,
                            $('.detail-img')[index].files[0]);
                    }
                });
            // 将product json对象转成字符流保存至表单对象key为productStr的的键值对里
            formData.append('productStr', JSON.stringify(product));
            // 获取表单里输入的验证码
            var check_code = $('#j_captcha').val();
            if (!check_code) {
                $.toast('请输入验证码！');
                return;
            }
            formData.append("check_code", check_code);
            // 将数据提交至后台处理相关操作
            $.ajax({
                url : productPostUrl,
                type : 'post',
                data : formData,
                contentType : false,
                processData : false,
                cache : false,
                success : function(data) {
                    if (data.success) {
                        $.toast('提交成功！');
                        $('#captcha_img').click();
                    } else {
                        $.toast('提交失败！'+data.errMsg);
                        $('#captcha_img').click();
                    }
                }
            });
        });

});
</script>

<script type='text/javascript'
        src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript'
        src='${pageContext.request.contextPath}/js/dist/sm.js' charset='utf-8'></script>
<script type='text/javascript'
        src='${pageContext.request.contextPath}/js/dist/sm-extend.js' charset='utf-8'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/js/common/common.js'
        charset='utf-8'></script>
</body>
</html>

