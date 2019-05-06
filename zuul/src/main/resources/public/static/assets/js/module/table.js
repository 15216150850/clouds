var datatable;
function initDatatable() {
    datatable = $('#datatable').DataTable({
        "dom": 'rt<"dataTables_page"<"col-sm-6 col-xs-12"il><"col-sm-6 col-xs-12"p>>',
        "lengthChange": true,//选择lenth
        "autoWidth": false,//自动宽度
        "searching": false,//搜索
        "processing": false,//loding
        "serverSide": true,//服务器模式
        "ordering": false,//排序
        "pageLength": 10,//初始化lenth
        "deferRender": true,
        "language": {
            "url": "assets/libs/datatables/language/zh_cn.json"
        },
        "ajax": {
            "url": "json/user.json",
            "type": "get",
            "data": function(d) {
                d.username = $('#username').val();
                d.name = $('#name').val();
                d.roleName = $('#roleName').val();
            }
        },
        "columns": [
            {
                "data": "id",
                width: "10",
                render: function (data) {
                    return '<input type="checkbox" class="iCheck check" value="' + data + '">';
                }
            },
            {"data": "username"},
            {"data": "name"},
            {"data": "status",
                render: function(data, type, row) {
                    if(data == '0') {
                        return '<span class="badge bg-green">正常</span>';
                    } else {
                        return '<span class="badge bg-red">禁用</span>';
                    }
                }
            },
            {"data": "roleName"},
            {"data": "id",
                "render": function(data, type, row) {
                    var button = "";
                    if(row.status == '0') {
                        button = ' ';
                    } else {
                        button = ' ';
                    }
                    if(row.username != 'admin') {
                        return '<button class="btn btn-xs btn-info seeBtn" rid="' + row.id + '">' +
                            '<i class="fa fa-eye fa-btn"></i>查看' +
                            '</button>' +
                            '<button class="btn btn-xs btn-warning updateBtn" rid="' + row.id + '">' +
                            '<i class="fa fa-edit fa-btn"></i>修改' +
                            '</button>' +
                            '<button class="btn btn-xs btn-danger deleteBtn" rid="' + row.id + '">' +
                            '<i class="fa fa-trash fa-btn"></i>删除' +
                            '</button>' +
                            '' + button;
                    } else {
                        return '<button class="btn btn-xs  btn-info seeBtn" rid="' + row.id + '">' +
                            '<i class="fa fa-user-times fa-btn"></i>查看' +
                            '</button>';
                    }
                }
             }],
        "fnDrawCallback": function () {
            checkAll();
        }
    });
}

$(function() {

    //初始化datatable
    initDatatable();

    //新增
    $("#insertBtn").click(function() {
        openDialog("新增表单", "form.html", "800px", "380px");
    });
    //查看
    $(document).on("click", ".seeBtn", function() {
        var id = $(this).attr("rid");
        openDialogView("查看表单", "form.html", "800px", "380px");
    });
    //修改
    $(document).on("click", ".updateBtn", function() {
        var id = $(this).attr("rid");
        openDialog("修改表单", "form.html?id=" + id, "800px", "380px");
    });
    //删除
    $(document).on("click", ".deleteBtn", function() {
        var id = $(this).attr("rid");
        var fun = function() {
            $.ajax({
                type: "POST",
                url: "form.html?id=" + id,
                beforeSend: function() {
                    return loading();
                },
                success: function(msg) {
                    closeloading();
                    if(msg.state == 'success') {
                        top.layer.msg("操作成功");
                        refreshTable(datatable);
                    } else {
                        top.layer.msg("操作失败");
                    }
                },
                error: function() {
                    closeloading();
                }
            });
        };
        confirmx("你确定删除吗？", fun);
    });
});
