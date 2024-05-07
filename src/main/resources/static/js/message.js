const pageSize = 10;
let currentPage = 1;
function fetchDefaultMessages() {
    const defaultCategory = 'AUDIT_TODO'; // 默认显示的消息类型
    fetchMessagesByCategory(defaultCategory, 1); // 调用之前定义的函数来请求并显示消息
}
// 定义一个函数，根据消息类型向后端请求对应消息
function fetchMessagesByCategory(messageType, currentPage) {
    // 构造请求参数，这里假设后端接口是 /query，并且需要传递 category 参数
    const messageDto = {
        msgType: messageType,
        pageNo: currentPage,
        pageSize: pageSize
        // ,receiver: 
    };

    // 发送 POST 请求到后端接口
    fetch('/msg/query', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(messageDto)
    })
    .then(response => response.json())
    .then(data => {
        // 获取到后端返回的消息列表数据
        const results = data.content; 

        // 将消息列表显示到页面上，假设消息列表显示区域的 id 是 'table-body'
        const tableBody = document.getElementById('table-body');
        tableBody.innerHTML = ''; // 清空原有内容

        // 遍历消息列表并添加到页面中
        results.forEach(message => {
            // const row = document.createElement('tr');
            // row.innerHTML = `
            //     <td><input type="checkbox"></td>
            //     <td style="display: none;">${message.appId}</td>
            //     <td>${message.msgText}</td>
            //     <td>${message.senderName}</td>
            //     <td>${message.senderTime}</td>
            //     <td>${message.auditTime? message.auditTime:'-'}</td>
            //     <td>${message.msgType}</td>
            // `;
            // tableBody.appendChild(row); 

            const row = document.createElement('tr');

            // 创建第三列单元格
            const msgTextCell = document.createElement('td');
            msgTextCell.textContent = message.msgText;
            // msgTextCell.style.display = 'none'; 
            msgTextCell.addEventListener('mouseover', function () {
                this.style.cursor = 'pointer';
                this.style.textDecoration = 'underline';
                this.style.color = '#00008b';
            });
            msgTextCell.addEventListener('mouseout', function () {
                this.style.cursor = '';
                this.style.textDecoration = 'none';
                this.style.color = ''; // 默认颜色，或者设置一个具体的颜色
            });
            msgTextCell.addEventListener('click', function () {
                const appIdCell = row.querySelector('td:nth-child(2)');
                if (appIdCell) {
                    const appId = appIdCell.textContent;
                    if (appId) {
                        // TODO
                        // window.location.href = '/application/detail?appId=${appId}';
                        window.location.href = '/index';
                    }
                }
            });

            // 创建其他单元格
            const checkboxCell = document.createElement('td');
            checkboxCell.innerHTML = '<input type="checkbox">';
            const appIdCell = document.createElement('td');
            appIdCell.style.display = 'none';
            appIdCell.textContent = message.appId;
            const senderNameCell = document.createElement('td');
            senderNameCell.textContent = message.senderName;
            const senderTimeCell = document.createElement('td');
            senderTimeCell.textContent = message.senderTime;
            const auditTimeCell = document.createElement('td');
            auditTimeCell.textContent = message.auditTime || '-';
            const msgTypeCell = document.createElement('td');
            msgTypeCell.textContent = message.msgType;

            // 将单元格添加到行中
            row.appendChild(checkboxCell);
            row.appendChild(appIdCell);
            row.appendChild(msgTextCell);
            row.appendChild(senderNameCell);
            row.appendChild(senderTimeCell);
            row.appendChild(auditTimeCell);
            row.appendChild(msgTypeCell);

            tableBody.appendChild(row);          
        });
        // 更新页面上的总数和页码等信息
        const itemNum = document.getElementById('itemNum');
        itemNum.textContent = data.totalElements;

        // 可根据后端返回的总页数来更新页面上的页码等信息
        const pageNum = document.getElementById('pageNum');
        const pageTotal = document.getElementById('pageTotal');
        pageNum.textContent = currentPage; 
        pageTotal.textContent = data.totalPages == 0? 1:data.totalPages; 
    })
    .catch(error => {
        console.error('Error fetching messages:', error);
    });
}

// document.addEventListener('DOMContentLoaded', function () {
//     // Get default to-do messages
//     fetchDefaultMessages(); 
//     // Add event listeners
//     const categories = document.querySelectorAll('.categroy-title'); 
//     categories.forEach(category => {
//         category.addEventListener('click', function (event) {
//             const messageType = category.id;
//             fetchMessagesByCategory(messageType, 1);
//         });
//     });
// });
document.addEventListener('DOMContentLoaded', function () {
    // Get default to-do messages
    fetchDefaultMessages(); 
    // Add event listeners
    const categoryMenu = document.querySelector('.msg-category-menu'); 
    
    categoryMenu.addEventListener('click', function (event) {
        
        if (event.target.classList.contains('categroy-title')) {
            const messageType = event.target.id;
            fetchMessagesByCategory(messageType, 1);
        }
    });

});

function prevPage() {
    const pageNum = document.getElementById("pageNum").textContent;
    currentPage = parseInt(pageNum) - 1;;
    if (currentPage < 1) {
        currentPage = 1;
    }
    document.getElementById("pageNum").textContent = currentPage;
    fetchMessagesByCategory(getMsgType(), currentPage);
}

function nextPage() {
    const pageNum = document.getElementById("pageNum").textContent;
    const pageTotal = document.getElementById("pageTotal").textContent;
    currentPage = parseInt(pageNum) + 1;
    if (currentPage > parseInt(pageTotal)) {
        currentPage = parseInt(pageTotal);
    }
    document.getElementById("pageNum").textContent = currentPage;
    fetchMessagesByCategory(getMsgType(), currentPage);
}

function getMsgType() {
    const firstRow = document.querySelector('#table-body tr:first-child'); // 获取第一行元素
    const fieldValue = firstRow.querySelector('td:last-child').textContent; // 获取第二列字段的文本内容
    return fieldValue;
}

