document.addEventListener('DOMContentLoaded', function() {
    // 获取申请单信息
    var app_id = "";
    var requestDetails = "";

    // 显示申请单信息
    document.getElementById('applicantName').value = app_id;
    document.getElementById('requestDetails').value = requestDetails;

    // 监听表单提交事件
    document.getElementById('Approval Editing').addEventListener('submit', function(event) {
        event.preventDefault();

        // 获取审批结果
        var approvalResult = document.getElementById('approvalResult').value;

        var approvalId = "";

        // 修改申请单状态为已审核，反写审批单ID
        alert("Approval submitted successfully. Approval ID: " + approvalId);
    });
});

// 模拟审批单字段确认信息
var approvalFields = {

};

// 动态生成审批单字段确认表单
var approvalFieldsContainer = document.getElementById('approvalFields');
Object.keys(approvalFields).forEach(function(fieldName) {
    var field = approvalFields[fieldName];
    var label = document.createElement('label');
    label.setAttribute('for', fieldName);
    label.textContent = field.label + ":";
    var input = document.createElement('input');
    input.setAttribute('type', field.type);
    input.setAttribute('id', fieldName);
    input.setAttribute('name', fieldName);
    var restrictionInfo = document.createElement('span');
    restrictionInfo.textContent = "(" + field.restriction + ")";
    approvalFieldsContainer.appendChild(label);
    approvalFieldsContainer.appendChild(input);
    approvalFieldsContainer.appendChild(restrictionInfo);
    approvalFieldsContainer.appendChild(document.createElement('br'));
});

// 检查审批单字段确认的输入是否符合限制条件
var fieldsValid = true;
Object.keys(approvalFields).forEach(function(fieldName) {
    var field = approvalFields[fieldName];
    var inputValue = document.getElementById(fieldName).value.trim();
    if (field.type === "number" && isNaN(inputValue)) {
        alert(field.label + " must be a number.");
        fieldsValid = false;
    } else if (field.type === "number" && parseFloat(inputValue) <= 0) {
        alert(field.label + " must be greater than 0.");
        fieldsValid = false;
    } else if (field.type === "text" && inputValue.length > 50) {
        alert(field.label + " exceeds maximum length.");
        fieldsValid = false;
    }
});

if (fieldsValid) {
    // 将审批结果和字段确认结果发送到后端进行处理
    var approval_id = "";
    alert("Approval submitted successfully. Approval ID: " + approval_id);
}