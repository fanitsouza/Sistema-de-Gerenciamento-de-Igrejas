let toggle = document.querySelector('.toggle');
    let navigation = document.querySelector('.navigation');
    let main = document.querySelector('.main');

function toggleMenu(){
    toggle.classList.toggle('active');
    navigation.classList.toggle('active');
    main.classList.toggle('active');
    
    if (container.classList.contains("close")) {
        closeAllSubmenus(); // Fecha todos os submenus se a navegação está sendo fechada
    }
}

let list = document.querySelectorAll('.nav-list li');
function activeLink(){
    list.forEach((item) => 
    item.classList.remove('hovered'));
    this.classList.add('hovered');
}
list.forEach((item) => 
item.addEventListener('mouseover',activeLink));



let btn = document.querySelector(".fa-bars");
let container = document.querySelector(".container");

function closeAllSubmenus() {
    list.forEach(item => {
        item.classList.remove('show');
    });
}

btn.addEventListener("click", ()=>{
    container.classList.toggle("close");
    closeAllSubmenus(); 
});

let arrows = document.querySelectorAll(".arrow");
for(var i = 0; i < arrows.length ; i++) {
    arrows[i].addEventListener("click", (e)=>{
        let arrowParent = e.target.parentElement.parentElement;

        arrowParent.classList.toggle("show")
    });
}
