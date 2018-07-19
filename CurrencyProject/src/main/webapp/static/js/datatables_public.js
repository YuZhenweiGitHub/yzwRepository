/**
 * Created by YZW on 2017/8/21.
 */
//配置DataTables默认参数
$.extend(true, $.fn.dataTable.defaults, {
    "language": {
        "url": "/static/js/assets/Chinese.txt"
    },
    "dom": "<'row'<'col-md-6'l<'#toolbar'>><'col-md-6'f>r>" +
    "t" +
    "<'row'<'col-md-5 sm-center'i><'col-md-7 text-right sm-center'p>>"
});

var mydatatables = {
    Settings : {
        'ajax':{
            dataType:'json',
            /////contentType:'application/json',
            type:'post',
            url:"",
            data:function(aoData){
                //reqData['dtJson'] = JSON.stringify(aoData);
                return aoData;
            },
            error:function (resp){
                alert(JSON.stringify(resp));
            }
        },
        'dom': '<"html5buttons"B>lTfgitp',
        'buttons': [
            {extend: 'copy'},
            {extend: 'csv'},
            {extend: 'excel', title: 'ExampleFile'},
            {extend: 'pdf', title: 'ExampleFile'},
            {
                extend: 'print',
                customize: function (win) {
                    $(win.document.body).addClass('white-bg');
                    $(win.document.body).css('font-size', '10px');
                    $(win.document.body).find('table')
                        .addClass('compact')
                        .css('font-size', 'inherit');
                }
            }
        ],
        'serverSide':true,//是否开启服务器模式
        'stateSave':true,//保存状态 - 在页面重新加载的时候恢复状态（页码等内容）
        'processing':true,//当表格处在处理过程（例如排序）中时，启用或者禁止 'processing'指示器的显示。
        'displayStart':1,//初始化显示的时候从第几条数据开始显示(一开始显示第几页)
        'lengthMenu':[10,20,30,50,100],//显示每页大小的下拉框中的选项
        "autoWidth": true,
        'sPaginationType':'full_numbers',//分页显示所有按钮
        "fnInfoCallback": function (oSettings, iStart, iEnd, iMax, iTotal, sPre) {
            mydatatables.initIframe();
        },
        "aoColumns": [],
        "aoColumnDefs": [],
        "order":[] //默认排序列
    },
    initDatatables : function (table) {
        var oTable = table.DataTable(mydatatables.Settings);
        return oTable;
    },
    initIframe : function(){
        var obj = parent.document.getElementById("rightIframe"); //取得父页面IFrame对象
        obj.height = document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
    }
}
