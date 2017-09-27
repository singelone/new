<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="portlet light">
    <div class="portlet-title">
        <div class="caption">
            <i class="icon-layers font-green"></i>
            <span class="caption-subject font-green sbold uppercase">材料列表</span>
        </div>
        <div class="actions form-inline">
            <a href="<c:url value="/admin/inventory/material/materialSpecificationEdit"/>"><button class="btn blue"><i class="fa fa-plus-circle"></i>新增规格</button></a>
            <a href="<c:url value="/admin/inventory/material/materialEdit"/>"><button class="btn blue"><i class="fa fa-plus-circle"></i>新增</button></a>
        </div>
    </div>
    <form class="navbar-form navbar-left" role="search" id="materialSearchForm">

        <div class="form-group">
            <input type="hidden" value="" name="mcCode">
            <button type="button" class="btn btn-primary js_choose_category">选择分类</button>
        </div>
        <div class="form-group">
            <input type="text" name="mcode" class="form-control" value="" placeholder="材料编码">
        </div>
        <div class="form-group">
            <input type="text" name="name" class="form-control" value="" placeholder="材料名称">
        </div>
        <div class="form-group">
            <input type="text"  name="specification" class="form-control" value="" placeholder="规格">
        </div>

        <button type="button"  class="btn btn-default js_search">搜索</button>
        <button type="button" class="btn btn-default js_reset">重置</button>
    </form>
    <div class="portlet-body">
        <div class="row">
            <div class="col-md-12">
                <table class="table table-width table-striped table-bordered table-advance table-hover table-checkable " id="datatable_ajax">
                    <thead>
                    <tr role="row" class="heading">
                        <th width="3%">序号</th>
                        <th width="4%">材料编码</th>
                        <th width="4%">材料分类</th>
                        <th width="4%">材料名称</th>
                        <th width="4%">别名</th>
                        <th width="4%">规格</th>
                        <th width="4%">单位</th>
                        <th width="5%">相关操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade js_tree_div " data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">材料分类</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-4">
                            <label>材料分类</label>
                            <ul id="categoryTree" class="ztree"></ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary js_save_btn">确定</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="<c:url value="/resources/admin/global/plugins/datatables/datatables.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/admin/global/scripts/datatable.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/admin/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" />"></script>


<script>

$(function () {

        var treeObj = null;

        var initTree = function (_arr,treeId,callback,settings) {
            var _setting = {callback:{
                onClick:function (event, treeId, treeNode) {
                    if ("function" == typeof(callback)){
                        callback(event,treeId,treeNode);
                    }
                },onCheck:function (event, treeId, treeNode) {
                    if ("function" == typeof(callback)){
                        callback(event,treeId,treeNode);
                    }
                }
            },data:{
                key:{
                    children:"list"
                }
            },view:{
                expandSpeed:''
            }};
            if ( typeof settings == "object"){
                for(var k in settings){
                    var _set = _setting[k];
                    if(_set==undefined){
                        _setting[k]=settings[k];
                    }else{
                        var tmp = settings[k];
                        if ( typeof tmp == "object") {
                            for (var i in tmp) {
                                _set[i] = tmp[i];
                            }
                        }
                    }
                }
            }
            return $.fn.zTree.init($('#'+treeId), _setting, _arr);
        }


        $('button.js_choose_category').on('click',function () {
            if(!treeObj){
                $.ajax({
                    async: false,
                    type : 'POST' ,
                    dataType:'json',
                    url : '<c:url value="/admin/inventory/materialCategory/tree"/>',
                    success: function (result) {
                        treeObj = initTree(result, 'categoryTree', function (event, treeId, treeNode) {

                        });
                        var nodes = treeObj.getNodes();
                        treeObj.expandNode(nodes[0],true,false,false);
                    }
                });
            }
            $('div.js_tree_div').modal('show');
        });

        $('button.js_save_btn').on('click',function () {
            var nodes = treeObj.getSelectedNodes();
            if(nodes.length>0){
                $('input[name=mcCode]').val(nodes[0].code);
                $('div.js_tree_div').modal('hide');
            }else{
                layer.msg('请选择分类');
            }
        });

        var TableDatatablesAjax = function() {
            var  e = function() {
                var table = new Datatable;
                table.init({
                    src: $("#datatable_ajax"),
                    loadingMessage: "Loading...",
                    dataTable: {
                        bStateSave: !0,
                        lengthMenu: [[10, 20, 50, 100], [10, 20, 50, 100]],
                        searching:true,
                        pageLength: 10,
                        ajax: {
                            url: "<c:url value="/admin/inventory/material/materialSpecificationListData"/>"
                        },
                        order: [[1, "asc"]],
                        columns:[
                            {"data":"id", "targets": 0},
                            {"data":"mcode","render" : function(data, type, full, meta ){
                                return data==undefined?'':data;
                            }},
                            {"data":"categoryName","render" : function(data, type, full, meta ){
                                return data==undefined?'':data;
                            }},
                            {"data":"name","render" : function(data, type, full, meta ){
                                return data==undefined?'':data;
                            }},
                            {"data":"alias","render" : function(data, type, full, meta ){
                                return data==undefined?'':data;
                            }},
                            {"data":"specification","render" : function(data, type, full, meta ){
                                return data==undefined?'':data;
                            }},
                            {"data":"unitStr","render" : function(data, type, full, meta ){
                                return data==undefined?'':data;
                            }},
                            {"data":"id","render" : function(data, type, full, meta ){
                                var editUrl = "<c:url value='/admin/inventory/material/materialEdit/'/>"+data+"/"+full.specificationId;
                                return  '<a href="'+editUrl+'" class="btn btn-sm btn-outline grey-salsa" ><i class="fa fa-edit"></i> 编辑</a>'+
                                    '<button class="btn btn-sm btn-outline grey-salsa"  target="_blank" data-id="'+data+'"  data-specification="'+full.specificationId+'"><i class="fa fa-close"></i> 删除</button>';
                            }}
                        ],fnDrawCallback: function(){
                            var api = this.api();
                            var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
                            api.column(0).nodes().each(function(cell, i) {
                                cell.innerHTML = startIndex + i + 1;
                            });
                        },"initComplete":function () {

                        }
                    }
                });
                return table;
            };
            return {
                init: function() {
                    return e();
                }
            }
        } ();


        var table = TableDatatablesAjax.init();

        $('#datatable_ajax').on('click','button',function () {
            var _id = $(this).data('id');
            var _specificationId = $(this).data('specification');
            layer.confirm("确定要删除该材料？",function (_index) {
                layer.close(_index);
                $.post('<c:url value="/admin/inventory/material/deleteOne"/>', {id:_id,specificationId:_specificationId}, function (result) {
                    if (result.success) {
                        layer.msg('操作成功',{time:1000},function () {
                            table.getDataTable().ajax.reload();
                        });
                    }else {
                        layer.msg('删除失败！：'+result.rmsg,{time:2000});
                    }
                }, 'json');
            });
        });

        $('button.js_search').click(function () {
            table.clearAjaxParams();
            table.setAjaxParam('mcode',$.trim($('input[name=mcode]').val()));
            table.setAjaxParam('mcCode',$.trim($('input[name=mcCode]').val()));
            table.setAjaxParam('name',$.trim($('input[name=name]').val()));
            table.setAjaxParam('specification',$.trim($('input[name=specification]').val()));
            table.setAjaxParam('start',0);
            table.getDataTable().ajax.reload();
        });

        $('button.js_reset').click(function () {
            table.clearAjaxParams();
            $('input[name=mcode]').val('');
            $('input[name=mcCode]').val('');
            $('input[name=name]').val('');
            $('input[name=specification]').val('');
            table.setAjaxParam('start',0);
            table.getDataTable().ajax.reload();
        });



        /*批量删除*/
//        $('#batchDelete').click(function () {
//            var ids = new Array();
//            $("input.js_checkBox:checked").each(function (k, v) {
//                ids.push(v.value);
//            });
//            if (ids.length==0){
//                layer.msg('请至少选择一行数据', {time: 1000});
//                return;
//            }
//            layer.confirm("确定要删除这些材料？",function (_index) {
//                $.post("/admin/inventory/material/delete",{"ids":ids.join(',')},function (result) {
//                    layer.close(_index);
//                    if ("true"==result.success) {
//                        layer.msg('操作成功',{time:1000},function () {
//                            table.getDataTable().ajax.reload();
//                        });
//                    }else if("notTrue"==result.success){
//                        layer.msg('请选择没有关联价格的材料！：'+result.rmsg,{time:3000});
//                    }else {
//                        layer.msg('删除失败！：'+result.rmsg,{time:3000});
//                    }
//                });
//            });
//        });

});
</script>