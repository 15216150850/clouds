$(function () {
    //改变iframe高度
    $("#content-main").css("height", $(window).height() - 142);
    $(window).resize(function () {
        $("#content-main").css("height", $(window).height() - 142);
    });
});