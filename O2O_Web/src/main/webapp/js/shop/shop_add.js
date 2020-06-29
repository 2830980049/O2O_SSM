
// 实现前后端分离 解耦
$(function () {
//    获取店铺分类
    var initUrl = '/o2o/shopadmin/getshopinitinfo';
    var registerShopUrl = '/o2o/shopadmin/registershop';
    alert(initUrl);
    getShopInitInfo();
    function getShopInitInfo(){
        //  获取JSON $getJSON（访问URL，回调方法）
        $.getJSON(initUrl, function (data) {
            if (data.success){
                alert(data)
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
        if(count != 5){
            alert("请完善信息!");
            return;
        }
        formData.append('shopImg', shopImg);
        formData.append('shopStr',JSON.stringify(shop));
        var check_code = $('#checks').val();
        if(!check_code){
            alert("请输入验证码！");
            return;
        }
        formData.append('check_code', check_code);
        $.ajax({
            url : registerShopUrl,
            type : 'post',
            data : formData,
            contentType : false,
            processData : false,
            cache : false,
            success:function (data) {
                if(data.success)
                    alert('提交成功！');
                else
                    alert('提交失败！' + data.errMsg);
                $('#check_img').click();
            }
        });
    });
});
