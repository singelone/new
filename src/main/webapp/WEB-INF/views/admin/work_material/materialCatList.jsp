<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
    .glyphicon-leaf::before{
        content: " " !important;
    }
</style>

<div class="portlet light">
    <div class="portlet-title">
        <div class="caption">
            <i class="icon-layers font-green"></i>
            <span class="caption-subject font-green sbold uppercase">分项工程</span>
        </div>
        <div class="actions form-inline">
            <button class="btn blue js_add_root"><i class="fa fa-plus-circle"></i>添加顶级分项工程</button>
        </div>
    </div>
    <div class="row">
            <div class="col-xs-12" role="main">
                <div class="panel panel-default">
                    <div class="panel-body form-horizontal">
                        <div class="form-group">
                            <div class="col-xs-4" data-toggle="popover" data-trigger="focus" data-content="请输入20位有效字符">
                                <div class="input-group ">
                                    <input type="text" id="serachvalue" class="form-control" placeholder="请输入关键字">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default form-control" type="button"
                                                id="serach">查询
                                        </button>
                                        <input type="hidden" id="addCatInputValue" name="addCatInputValue"/>
                                        <input type="hidden" id="addCatId" name="addCatInputValue" value="${param.id}"/>
                                    </span>
                                </div>
                            </div>
                            <div class="col-xs-8 text-right">
                                <button type="button" class="btn btn-primary" id="expendAll">全部展开
                                </button>
                                <button type="button" class="btn btn-primary" id="collapseAll">全部关闭
                                </button>
                            </div>
                        </div>

                        <nav class="pull-right">
                            <ul class="pagination">

                            </ul>
                        </nav>

                        <table class="table table-hover table-condensed " id="treetable">
                            <thead class="bg-default">
                            <tr>
                                <th>名称</th>
                                <th data-column-id="auditstatedesc"  style="width:150px">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${list}" var="data">
                                <tr id="${data.id}" data-id="${data.id}" data-pid="${data.parentId == -1 ? "" : data.parentId}">
                                    <td id="categoryNameId">${data.code} ${data.name}</td>
                                    <td class="text-center" style="position: relative;text-align: left">
                                        <a href="javascript:void(0)" class="doadd" data-id="${data.id}" data-name="${data.name}" data-code="${data.code}">新增</a>
                                        <a href="javascript:void(0)" class="doedit" data-id="${data.id}" data-name="${data.name}">编辑</a>
                                        <%--<c:if test="${data.parentId ne -1}">--%>
                                            <a class="dodel" href="javascript:void(0)"  data-id="${data.id}"   data-pid="${data.parentId}" >删除</a>
                                        <%--</c:if>--%>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
            <!-- 修改 Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">分项工程编辑</h4>
                        </div>
                        <div class="modal-body">
                            <form  role="form" class="js_base_form" id="catEditForm" method="post" action="/admin/workMaterialCategory/update">
                                <input type="hidden" name="id"/>
                                <div class="form-body">
                                    <div class="row">
                                        <div class="col-md-6 ">
                                            <div class="form-group">
                                                <label>分项工程名称：</label>
                                                <input type="text"  class="form-control" name="name" value="" />
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" onclick="$('#catEditForm').submit()" class="btn btn-primary">保存</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 新增 Modal -->
            <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabelAdd">增加分项工程</h4>
                        </div>
                        <div class="modal-body">
                            <form  role="form" class="js_base_form" id="catAddForm" method="post">
                                <input type="hidden" name="parentId"/>
                                <input type="hidden" name="code"/>
                                <div class="form-body">
                                    <div class="row">
                                        <div class="col-md-6 ">
                                            <div class="form-group">
                                                <label>父分项工程名称：</label>
                                                <input type="text" disabled class="form-control" name="pname" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 ">
                                            <div class="form-group">
                                                <label>分项工程名称：</label>
                                                <input type="text" class="form-control" name="name" />
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary js_save_btn">保存</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

</div>


</div>

<script src="<c:url value="/resources/admin/global/plugins/bootstrap-treetable.js"/>"></script>



<script>

    $(function () {
        var treeTableObj =$('#treetable');

        var treeTable = treeTableObj.BootstrapTreeTable({
            expandlevel: 1,//默认展开级次
            expandAll: false,//是否全部展开
            collapseAll: false,//是否全部关闭
            maxResult: 100//搜索最大结果集，超过将停止返回结果
        }).on("initialized.bs.treetable", function () {
            var count = treeTable.BootstrapTreeTable('getMaxLevel');
            createExpandButton(count);

        });

        function createExpandButton(count) {
            for (var i = 1; i <= count; i++) {
                var $btn = $('<li><a href="#" class="expendlevel" data-level="' + i + '">' + i + '</a></li>');
                $('.pagination').append($btn);
            }
            $('.expendlevel').click(function (e) {
                e.preventDefault();
                var level = $(this).data('level');
                treeTableObj.BootstrapTreeTable('expendLevel', level);
            });
        }


        $('#serach').click(function () {
            var value = $('#serachvalue').val();
            var result = treeTableObj.BootstrapTreeTable('searchNodeName', value);
            $(this).text('查询(' + result + ')');
        });


        $('#expendAll').click(function () {
            treeTableObj.BootstrapTreeTable('expendAll');
        });

        $('#collapseAll').click(function () {
            treeTableObj.BootstrapTreeTable('collapseAll');
        });


        $('a.doedit').click(function () {
            var _this =$(this);
            var _modal =$('#myModal');
            _modal.find('input[name=id]').val(_this.data('id'));
            _modal.find('input[name=name]').val(_this.data('name'));
            _modal.modal('show');
        });

        $('a.doadd').click(function () {
            var _this =$(this);
            var _modal =$('#addModal');
            _modal.find('input[name=parentId]').val(_this.data('id'));
            _modal.find('input[name=code]').val(_this.data('code'));
            _modal.find('input[name=pname]').val(_this.data('name'));
            _modal.modal('show');
        });


        $('a.dodel').click(function () {
            var id = $(this).data('id');
            var pid = $(this).data('pid');
            var node = $(this).parents('tr');
            if (node && node.data('leaf') === 1){
                layer.confirm('你确定要删除吗？', {
                    btn: ['确定','取消']
                }, function(_index){
                    layer.close(_index);
                    $.post("/admin/workMaterialCategory/delete",{"id":id,"parentId":pid},function (data) {
                        if (data.success){
                            treeTableObj.BootstrapTreeTable('removeByNode', node);
                        }else {
                            layer.msg(data.rmsg, {time: 1000});
                        }
                    });
                });
            }else if (node.data('leaf') === 0){
                layer.msg('请先删除子分类', {time: 1000});
            }
        });


        $("#addModal").on('click',"button.js_save_btn",function () {
            $.post("/admin/workMaterialCategory/create",$("#catAddForm").serialize(),function (data) {
                if(data.success){
                    $('#addModal').modal('hide');
                    window.location.reload();
                }else{
                    layer.msg(data.rmsg);
                }
            });
        });

        /***
         * 添加顶级分类
         */
        $('button.js_add_root').on('click',function () {
            $('#addModal').modal('show').find('div.row :eq(0)').addClass('hidden');
            $('#catAddForm').find('input[name=parentId]').val(-1);
        });

    });

</script>