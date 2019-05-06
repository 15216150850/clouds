/*! common.js
 * ===============================================
 * 框架共用js封装
 * ===============================================
 *
 * @Author  
 * @version 1.0.0
 */
var layer_loading;//加载框
var layer_window;//弹出框
var layer_confirm;//询问框

/**
 * 显示加载框
 */

function loading() {
    layer_loading =  layer.load(1, {shade: [0.2]}, {shadeClose: true})
}

/**
 * 关闭加载框
 */
function closeloading() {
    layer.close(layer_loading)
}

/**
 * 询问框
 * @param tips
 * @param fun
 */
function confirmx(tips,fun) {
    top.layer.confirm(tips,{
        btn: ['确定', '取消']
    },function (index) {
        top.layer.close(index);
        fun()
    })

}

/**
 * 打开对话框
 * @param title 标题
 * @param url 地址
 * @param width 宽度
 * @param height 高度
 * @param isMax 是否最大化
 */
function openDialog(title, url, width, height, isMax) {
    layer_window = top.layer.open({
        type: 2,
        area: [width, height],
        title: title,
        maxmin: true, //开启最大化最小化按钮
        content: [url,'no'],
        btn: ['确定', '关闭'],
        yes: function (index, layero) {
            var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            iframeWin.contentWindow.doSubmit();
        },
        cancel: function (index) {
            top.layer.close(layer_window);
        }
    });
    if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {//如果是移动端，直接最大化
        top.layer.full(layer_window);
    } else {
        if (isMax) {
            top.layer.full(layer_window);
        }
    }
}
function openDialogVerify(title, url, width, height, isMax) {
    layer_window = top.layer.open({
        type: 2,
        area: [width, height],
        title: title,
        maxmin: true, //开启最大化最小化按钮
        content: [url,''],
        btn: ['确定', '关闭'],
        yes: function (index, layero) {
            var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
            submit.click();
        },
        cancel: function (index) {
            top.layer.close(layer_window);
        }
    });
    if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {//如果是移动端，直接最大化
        top.layer.full(layer_window);
    } else {
        if (isMax) {
            top.layer.full(layer_window);
        }
    }
}

/**
 * 打开选择框
 * @param title 标题
 * @param url 地址
 * @param width 宽度
 * @param height 高度
 * @param isMax 是否最大化
 * @param method 回调方法名
 * @param parentWindow 父窗口window
 */
function openChoiceRedio(title, url, width, height, isMax, method, parentWindow) {
    layer_window = top.layer.open({
        type: 2,
        area: [width, height],
        title: title,
        maxmin: true, //开启最大化最小化按钮
        content: [url, 'no'],
        btn: ['确定', '关闭'],
        yes: function (index, layero) {
            var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            //调用子页面方法抓取数据，并且获取页面索引后面关闭弹出层用得上
            var rs = resultDataRedio(iframeWin.contentWindow);
            parentWindow.eval(method + "('" + rs + "')");
        },
        cancel: function (index) {
            top.layer.close(layer_window);
        }
    });
    if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {//如果是移动端，直接最大化
        top.layer.full(layer_window);
    } else {
        if (isMax) {
            top.layer.full(layer_window);
        }
    }
}

/**
 * 抓取单选框数据
 * @param childWindow
 * @returns {*}
 */
function resultDataRedio(childWindow){
    var table=childWindow.table;
    var result='{';
    var checkStatus = table.checkStatus('bpTable')
        , data = checkStatus.data;
    var index = top.layer.getFrameIndex(childWindow.name);
    if (data.length==0) {
        //top.layer.msg("请选择领用人!");
        result+='"index":"'+index+'","testId":"yes"}';
        return result;
    } else {
        result+='"id":"'+data[0].id+'",';
        result+='"name":"'+data[0].name+'",';
    }
    result+='"index":"'+index+'"}';
    return result;
}



/**
 * 打开选择框
 * @param title 标题
 * @param url 地址
 * @param width 宽度
 * @param height 高度
 * @param isMax 是否最大化
 * @param method 回调方法名
 * @param parentWindow 父窗口window
 */
function openChoice(title, url, width, height, isMax, method, parentWindow) {
    layer_window = top.layer.open({
        type: 2,
        area: [width, height],
        title: title,
        maxmin: true, //开启最大化最小化按钮
        content: [url,'no'],
        btn: ['确定', '关闭'],
        yes: function (index, layero) {
            var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            //调用子页面方法抓取数据，并且获取页面索引后面关闭弹出层用得上
            var rs=resultData(iframeWin.contentWindow);
            parentWindow.eval(method+"('"+rs+"')");
        },
        cancel: function (index) {
            top.layer.close(layer_window);
        }
    });
    if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {//如果是移动端，直接最大化
        top.layer.full(layer_window);
    }else{
        if(isMax){
            top.layer.full(layer_window);
        }
    }
}

/**
 * 抓取复选框数据
 * @param childWindow 子页面window
 */
function resultData(childWindow){
    var table=childWindow.table;
    var ids = "",names="",result='{"data":';
    var checkStatus = table.checkStatus('bpTable')
        , data = checkStatus.dataAll;
    data = JSON.stringify(data);

   if (data.length == 0){
       top.layer.msg('请选择需要的数据')
   }
    var index = top.layer.getFrameIndex(childWindow.name);
    result = result+data+',"index":'+index+'}';
     return result;
}

/**
 * 打开查看对话框
 * @param title
 * @param url
 * @param width
 * @param height
 */
function openDialogView(title, url, width, height, isMax) {
    layer_window = top.layer.open({
        type: 2,
        area: [width, height],
        title: title,
        maxmin: true, //开启最大化最小化按钮
        content: [url,''],
        btn: ['关闭'],
        cancel: function (layer_window) {
            top.layer.close(layer_window);
        }
    });
    if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {//如果是移动端，直接最大化
        top.layer.full(layer_window);
    } else {
        if (isMax) {
            top.layer.full(layer_window);
        }
    }
}

/**
 * 打开搜索页
 * @param title
 * @param url
 * @param width
 * @param height
 * @param isMax
 */
var isHide= false;
var dom = '';
function openDialogSearch(title, url, width, height, isMax, dataurl, table, tableId) {
    $('#layui-layer-shade1').addClass('show');
    // 点击查看详情
    layer_window = top.layer.open({
        type: 2,
        area: [width, height],
        title: title,
        id:'ceshi',
        maxmin: true, //开启最大化最小化按钮
        content: [url,'no'],
        btn: ['搜索', '重置'],
        yes: function (index, layero) {
            $('#layui-layer-shade1').addClass('hide');
            //console.log(layero[0]);
                dom = layero[0];
                isHide =true;
                $(layero[0]).animate({
                    bottom:'0px',
                    top:'1000px',
                    opacity:'0.1'
                });
            var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            var data = iframeWin.contentWindow.doSubmit();
            var jsonurl = dataurl+ '?' + data;
            table.reload(tableId, {
                url: jsonurl,
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            top.layer.close(layer_window);
        },
       btn2: function(index,layero){
           console.log((layero)[0].getElementsByTagName('input'));
           var body = layer.getChildFrame('body', index);
           var rest = layero.find('iframe').contents().find("#rest");
           rest.click();
           body.find('input').val('');
           return false
       },
       zIndex: layer.zIndex,
        cancel: function (index) {
            top.layer.close(layer_window);
        }
    });
    // console.log(index)
    if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {//如果是移动端，直接最大化
        top.layer.full(layer_window);
    } else {
        if (isMax) {
            top.layer.full(layer_window);
        }
    }
    return;
    $('#layui-layer-shade1').removeClass('hide')
    $($(dom)[0]).animate({
        top:'200px',
        opacity:'1',
    });
    isHide = false;
}

/**
 * 刷新Iframe
 */
function refreshIframe() {
    var target = top.$(".J_iframe:visible");
    if (target[0] == null || target[0] == 'undefined') {
        parent.location.href = parent.location.href;
    } else {
        var url = target[0].contentWindow.location.href;
        //显示loading提示
        var loading = layer.load();
        target.attr('src', url).load(function () {
            //关闭loading提示
            layer.close(loading);
        });
    }
}

/**
 * 刷新dataTable
 * @param datatableObj
 */
function refreshTable(datatableObj) {
    if (datatable == null || datatable == 'undefined') {//添加、修改
        var target = top.$(".J_iframe:visible");
        if (target[0] == null || target[0] == 'undefined') {
            var datatable = parent.window[0].datatable;
            datatable.ajax.reload(null, false);
        } else {
            var datatable = target[0].contentWindow.datatable;
            datatable.ajax.reload(null, false);
        }
    } else {//删除
        start = $("#datatable").dataTable().fnSettings()._iDisplayStart;
        total = $("#datatable").dataTable().fnSettings().fnRecordsDisplay();
        if ((total - start) == 1) {
            if (start > 0) {
                $("#datatable").dataTable().fnPageChange('previous', true);
            }
        }
        datatableObj.ajax.reload(null, false);
    }
}


/**
 * ajax session失效
 */
$.ajaxSetup({
    complete: function (XMLHttpRequest, textStatus) {
        if (XMLHttpRequest.getResponseHeader('Session-Status') == 'timeout') {
            top.layer.msg("登陆超时！请重新登陆！");
            setTimeout(function () {
                top.location.href = top.location.href;
            }, 1000);
        } else if (textStatus == "parsererror") {
            top.layer.msg("JSON解析错误!");
        }
    }
});

/**
 * pace监听ajax
 * 使用前一定要先引入pace.min.js
 */
$(document).ajaxStart(function () {
    Pace.restart();
});

/**
 * 获取对象权限的
 * @param username
 * @returns {Array}
 */
function checkPermission(username) {
    var pers = [];
    $.ajax({
        type : 'get',
        url : 'http://localhost:9527/sys/findPermissionsByUser',
        data : {'username':username},
        contentType : "application/json; charset=utf-8",
        async : false,
        success : function(data) {
            pers = data.permissions;
        }
    });
    return pers;
}

/**
 * 时间格式化
 * fmt 传入的
 */
function fmtDate(obj){
    var date = new Date(obj);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;

}



