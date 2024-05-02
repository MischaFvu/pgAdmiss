function firstPage() {
    $("#pageNum").text(1);
    queryData(1);
}

function lastPage() {
    $("#pageNum").text($("#pageTotal").text());
    // $("#pageNo").val($("#pageTotal").text());
    queryData();
}

function prev() {
    var cur = Number($("#pageNum").text()) - 1;
    if (cur < 1) {
        cur = 1;
    }
    $("#pageNum").text(cur);
    // $("#pageNo").val(cur);
    queryData();
}

function next() {
    var cur = Number($("#pageNum").text()) + 1;
    if (cur > $("#pageTotal").text()) {
        cur = $("#pageTotal").text();
    }
    $("#pageNum").text(cur);
    // $("#pageNo").val(cur);
    queryData(cur);
}

function queryData() {
    ajaxPost('/message/list', serializeNotNull($('#logForm').serialize()), refreshTable);
/* 		$.ajax({
        type: 'post',
        url: '/log/list',
        data: serializeNotNull($('#logForm').serialize()),
        success: function (result){
            refreshTable(result);
        }
    }); */
}
function refreshTable(result) {
    var dataList = result.dataList;
    var total = result.total;
    $("#pageTotal").text(total);
    $("#table-body").empty();
    if (dataList.length > 0) {
        for (var i = 0; i < dataList.length; i++) {
            var data = dataList[i];
            $("#table-body").append("<tr>");
            $("#table-body").append("<td>" + data.msgText + "</td>");
            $("#table-body").append("<td>" + data.sender + "</td>");
            $("#table-body").append("<td>" + data.createDate + "</td>");
            $("#table-body").append("<td>" + data.updateDate + "</td>");
            $("#table-body").append("<td>" + data.msgType + "</td>");
            $("#table-body").append("</tr>");
        }
    }
}