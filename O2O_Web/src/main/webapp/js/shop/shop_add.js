
// 实现前后端分离 解耦
$(function () {
//    获取店铺分类
    var initUrl = '/shopadmin/getshopinitinfo';
    var registerShopUrl = '/shopadmin/registershop';
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

    $('#submit').click(function () {
        var shop = {};
        shop.shopName = $('#shop-name').val();
        shop.shopAddr = $('#shop-addr').val();
        shop.phone = $('#shop-phone').val();
        shop.Desc = $('#shop-desc').val();
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
        var formData = new formData();
        formData.append('shopImg', shopImgh);
        formData.append('shopStr',JSON.stringify(shop));
        $.ajax({
            url : registerShopUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            proceesData:false,
            cache:false,
            success:function (data) {
                if(data.success){
                    $.toast('提交成功！');
                }
                else{
                    $.toast('提交失败！' + data.errMsg);
                }
            }
        })
    });
});