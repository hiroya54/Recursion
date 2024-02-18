const config ={
    target: document.getElementById("target"),
    displayPc: document.getElementById("displayPc"),
    url : "https://api.recursionist.io/builder/computers?type="
}

const brands = {
    "cpu":["Intel", "AMD"],
    "gpu":["Nvidia","AMD","Zotac","Asus","MSI","Gigabyte",
            "EVGA","Sapphire","PowerColor","XFX","ASRock","Gainward","PNY","PwrHis"],
    "ram":["G.SKILL","Crucial","Corsair","HyperX"],
    "hdd":["WD","HGST","Seagate","Toshiba","Hitachi"],
    "ssd":["Intel","Samsung","Sabrent","Corsair","Gigabyte",
            "HP","Crucial","WD","Adata","SanDisk","Mushkin","Seagate","XPG","Plextor","Nvme","Zotac"]
}

class Viewer{
    static displayInitialPage(){
        let container = document.createElement("div");
        container.classList.add("bg-white");
        config.target.append(container);

        container.append(this.displayHeader())
        container.append(this.displayStep(1))
        container.append(this.displayCpuSelecter());
        container.append(this.displayStep(2))
        container.append(this.displayGpuSelecter());
        container.append(this.displayStep(3))
        container.append(this.displayMemorySelecter());
        container.append(this.displayStep(4))
        container.append(this.displayStorageSelecter());
        container.append(this.displayBtn());

        let displayPcDiv = document.createElement("div");
        displayPcDiv.id= "displayPc";

        container.append(displayPcDiv);
    }

    static displayHeader(){
        let container = document.createElement("div")
        container.classList.add("bg-dark", "col-12", "py-1", "text-center", "text-white");
        container.innerHTML = `<h1>Build Your Own Computer</h1>`
        return container;
    }
    static displayStep(num){
        let container = document.createElement("div");
        container.classList.add("m-2", "pt-3");
        if(num==1){
            container.innerHTML = `<h3>step1: Select Your CPU</h3>`;
        }else if(num==2){
            container.innerHTML = `<h3>Step2: Select Your GPU`;
        }else if(num==3){
            container.innerHTML = `<h3>step3: Select Your Memory Card`;
        }else if(num==4){
            container.innerHTML = `<h3>step4: Select Your Storage`;
        }else{
            container.innerHTML = ``;
        }
        return container;
    }

    static displayCpuSelecter(){
        let container = document.createElement("div");
        container.classList.add("d-flex", "justify-content-start", "flex-column", "mx-3")
        let htmlString =
        `
        <h5>Brand</h5>
        <select id="selectBrand" class="col-9 pl-1">
            <option value="-">-</option>
        </select>
        <h5>Model</h5>
        <select id="selectModel" class="col-9 pl-1">
            <option value="-">-</option>
        </select>
        `

        container.innerHTML = htmlString;

        let brandContainer = container.querySelectorAll("#selectBrand")[0];
        this.setBrandOption("cpu", brandContainer);
        brandContainer.addEventListener("change", function(){

            Controller.fetchAndAddModel("cpu", container);

        })

        return container;
    }

    static displayGpuSelecter(){
        let container = document.createElement("div");
        container.classList.add("d-flex", "justify-content-start", "flex-column", "mx-3")
        let htmlString =
        `
        <h5>Brand</h5>
        <select id="selectBrand" class="col-9 pl-1">
            <option>-</option>
        </select>
        <h5>Model</h5>
        <select id="selectModel" class="col-9 pl-1">
            <option>-</option>
        </select>
        `
        container.innerHTML = htmlString;

        let brandContainer = container.querySelectorAll("#selectBrand")[0];
        this.setBrandOption("gpu", brandContainer)
        brandContainer.addEventListener("change", function(){

            Controller.fetchAndAddModel("gpu", container)

        })

        return container;
    }

    static displayMemorySelecter(){
        let container = document.createElement("div");
        container.classList.add("d-flex", "justify-content-start", "flex-column", "mx-3")
        let htmlString =
        `
        <h5>How many?</h5>
        <select id="selectHowMany" class="col-9 pl-1">
            <option>-</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>
        <h5>Brand</h5>
        <select id="selectBrand" class="col-9 pl-1">
            <option>-</option>
        </select>
        <h5>Model</h5>
        <select id="selectModel" class="col-9 pl-1">
            <option>-</option>
        </select>
        `
        container.innerHTML = htmlString;

        let ramNumContainer = container.querySelectorAll("#selectHowMany")[0];
        let brandContainer = container.querySelectorAll("#selectBrand")[0];
        ramNumContainer.addEventListener("change", function(){
            brandContainer.innerHTML = "";
            brandContainer.appendChild(Viewer.setDefaultOption())
            Viewer.setBrandOption("ram", brandContainer)
    
        })
        brandContainer.addEventListener("change", function(){
            Controller.fetchAndAddModel("ram", container);
        })
        return container;
    }

    static displayStorageSelecter(){
        let container = document.createElement("div");
        container.classList.add("d-flex", "justify-content-start", "flex-column", "mx-3")
        let htmlString =
        `
        <h5>HDD or SSD</h5>
        <select id="selectStorageType" class="col-9 pl-1">
            <option>-</option>
            <option value="hhd">HDD</option>
            <option value="ssd">SSD</option>
        </select>
        <h5>Storage</h5>
        <select id="selectStrageSize" class="col-9 pl-1">
            <option>-</option>
        </select>
        <h5>Brand</h5>
        <select id="selectBrand" class="col-9 pl-1">
            <option>-</option>
        </select>
        <h5>Model</h5>
        <select id="selectModel" class="col-9 pl-1">
            <option>-</option>
        </select>
        `
        container.innerHTML = htmlString;

        let storageTypeContainer = container.querySelectorAll("#selectStorageType")[0];
        storageTypeContainer.addEventListener("change", function(){

            Viewer.setStorageSize();
        })


        return container;
    }

    static displayBtn(){
        let container = document.createElement("div");

        container.innerHTML = 
        `
        <button class="my-3 ml-3 btn btn-primary col-2" type="submit" id="addPc">Add PC</button>
        `

        return container;
    }

    static setBrandOption(type, container){
        for(let i=0; i<brands[type].length; i++){
            let brand = brands[type][i];
            let newOption = document.createElement("option");
            newOption.value = brand;
            newOption.text = brand;
            container.append(newOption)
        }
    }

    static setDefaultOption(){
        let defaultOption = document.createElement("option")
        defaultOption.text = "-";
        return defaultOption 
    }

    static setStorageSize(){

    }
}

class Controller{
    static fetchAndAddModel(type, container){
        let brandContainer = container.querySelectorAll("#selectBrand")[0];
        let modelContainer = container.querySelectorAll("#selectModel")[0];
        let items = [];
        fetch(config.url + type).then(response => response.json()).then(function(data){
            for(let i=0; i<brands[type].length;i++){
                let brand = brands[type][i]
                if (brand != brandContainer.value) continue;
                items = data.filter(item => item.Brand == brand);
            }
            if(type == "ram"){
                let slotNum = container.querySelectorAll("#selectHowMany")[0].value;
                items = items.filter(function(item){
                    let lastSpaceIndex = item.Model.lastIndexOf(" ");
                    let lastXIndex = item.Model.lastIndexOf("x");

                    return item.Model.substring(lastSpaceIndex+1, lastXIndex) == slotNum
                })
                console.log(items)
            }

            modelContainer.innerHTML = "";
            modelContainer.appendChild(Viewer.setDefaultOption())
            for(let i=0;i<items.length;i++){
                let model = items[i].Model;
                let newOption = document.createElement("option");
                newOption.value = model;
                newOption.text = model;
                modelContainer.appendChild(newOption);
            }
        })
    }

}

Viewer.displayInitialPage();