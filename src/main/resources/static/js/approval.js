const applications = [

];

const pageSize = 10;
let currentPage = 1;

function renderTable(page) {
    const table = document.getElementById("applicationTable");
    table.innerHTML = `
        <tr>
            <th>app_id</th>
            <th>stu_id</th>
            <th>major_id</th>
            <th>doc_language</th>
            <th>app_status</th>
        </tr>
    `;
    const start = (page - 1) * pageSize;
    const end = start + pageSize;
    const paginatedApplications = applications.slice(start, end);
    paginatedApplications.forEach(application => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${application.app_id}</td>
            <td>${application.stu_id}</td>
            <td>${application.major_id}</td>
            <td>${application.doc_language}</td>
            <td>${application.app_status}</td>
            <td><button onclick="approve(${application.app_id})">Approval</button></td>
        `;
        table.appendChild(row);
    });
}

function renderPagination() {
    const totalPages = Math.ceil(applications.length / pageSize);
    const pagination = document.getElementById("pagination");
    pagination.innerHTML = "";
    for (let i = 1; i <= totalPages; i++) {
        const button = document.createElement("button");
        button.innerText = i;
        button.onclick = function() {
            currentPage = i;
            renderTable(currentPage);
        };
        pagination.appendChild(button);
    }
}

function search() {
    const searchInput = document.getElementById("searchInput").value.toLowerCase();
    const filteredApplications = applications.filter(application =>
        application.major_id.toLowerCase().includes(majorInput) ||
        application.doc_language.toString().includes(languageScoreInput) ||
        application.app_status.toLowerCase().includes(statusInput)
    );
    renderFilteredTable(filteredApplications);
}

function renderFilteredTable(applications) {
    const table = document.getElementById("applicationTable");
    table.innerHTML = "";
    applications.forEach(application => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${application.app_id}</td>
            <td>${application.stu_id}</td>
            <td>${application.major_id}</td>
            <td>${application.doc_language}</td>
            <td>${application.app_status}</td>
            <td><button onclick="approve(${application.id})">Approval</button></td>
        `;
        table.appendChild(row);
    });
}

function approve(id) {
    window.location.href = `edit.html?id=${id}`;
}


document.addEventListener("DOMContentLoaded", () => {
    renderTable(currentPage);
    renderPagination();
});
