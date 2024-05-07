document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');
    form.addEventListener('submit', function(e) {
        e.preventDefault(); // 阻止表单的默认提交行为
        const formData = new FormData(this); // 创建FormData对象，用于收集表单数据
        fetch('/api/submit-application', {
            method: 'POST', // 设置请求方法为POST
            body: formData // 将表单数据作为请求体发送
        })
        .then(response => response.json())
        .then(data => console.log('Success:', data))
        .catch(error => console.error('Error:', error));
    });
});
