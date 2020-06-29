function changeCode(img) {
    // 生成 4位随机数 floor 返回小于等于x的最大整数:
    img.src = "/Kaptcha?" + Math.floor(Math.random() * 100);
}