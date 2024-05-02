function loadNavigation() {
    fetch('/navigation')
        .then(response => response.text())
        .then(html => {
            const navContainer = document.getElementById('nav-container');
            if (navContainer) {
                navContainer.innerHTML = html;
            } else {
                console.error('Element with ID "nav-container" not found.');
            }
        })
        .catch(error => console.error('Failed to load navigation:', error));
}

document.addEventListener('DOMContentLoaded', function() {
    loadNavigation();
});
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    
}

