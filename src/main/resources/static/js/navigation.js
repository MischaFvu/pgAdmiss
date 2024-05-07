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


function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("nav-container").classList.remove("sidenav-hidden");

}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("nav-container").classList.add("sidenav-hidden");
    
}
document.addEventListener('DOMContentLoaded', function() {
    loadNavigation();
    openNav();
});
