const config ={
    target: document.getElementById("target"),
    displayPc: document.getElementById("displayPc"),
    url : "https://api.recursionist.io/builder/computers?type="
}

fetch(url+"gpu").then(response => response.json()).then(function(data){
    let brands = data.map(item => item.Brand);
    let uniqueBrands = [... new Set(brands)]

    console.log(uniqueBrands)

})