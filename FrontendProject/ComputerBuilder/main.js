const config ={
    target: document.getElementById("target"),
    displayPc: document.getElementById("displayPc"),
    url : "https://api.recursionist.io/builder/computers?type="
}

let brands = {"cpu":[], "gpu":[], "ram":[], "hdd":[], "ssd":[] }

class Viewer{
    static displayInitialPage(){

        let container = document.createElement("div");
        container.classList.add("bg-white");
        config.target.append(container);

        container.append(this.displayHeader())
        container.append(this.displayStep(1))
        container.append(this.displayCpuSelection());
        container.append(this.displayStep(2))
        container.append(this.displayGpuSelection());
        container.append(this.displayStep(3))
        container.append(this.displayMemorySelection());
        container.append(this.displayStep(4))
        container.append(this.displayStorageSelection());
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

    static displayCpuSelection(){
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
        let modelContainer = container.querySelectorAll("#selectModel")[0];

        Controller.setBrandOption("cpu", brandContainer);
        brandContainer.addEventListener("change", function(){

            Controller.initializeOption(modelContainer);
            Controller.fetchAndAddModel("cpu", container);

        })

        return container;
    }

    static displayGpuSelection(){
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
        let modelContainer = container.querySelectorAll("#selectModel")[0];

        Controller.setBrandOption("gpu", brandContainer)
        brandContainer.addEventListener("change", function(){

            Controller.initializeOption(modelContainer);
            Controller.fetchAndAddModel("gpu", container)

        })

        return container;
    }

    static displayMemorySelection(){
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
        let modelContainer = container.querySelectorAll("#selectModel")[0];

        ramNumContainer.addEventListener("change", function(){
  
            Controller.initializeOption(brandContainer);
            Controller.initializeOption(modelContainer);
            Controller.setBrandOption("ram", brandContainer)
    
        })
        brandContainer.addEventListener("change", function(){

            Controller.initializeOption(modelContainer);
            Controller.fetchAndAddModel("ram", container);
        })
        return container;
    }

    static displayStorageSelection(){
        let container = document.createElement("div");
        container.classList.add("d-flex", "justify-content-start", "flex-column", "mx-3")
        let htmlString =
        `
        <h5>HDD or SSD</h5>
        <select id="selectStorageType" class="col-9 pl-1">
            <option>-</option>
            <option value="hdd">HDD</option>
            <option value="ssd">SSD</option>
        </select>
        <h5>Storage</h5>
        <select id="selectStorageSize" class="col-9 pl-1">
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
        let storageSizeContainer = container.querySelectorAll("#selectStorageSize")[0];
        let brandContainer = container.querySelectorAll("#selectBrand")[0];
        let modelContainer = container.querySelectorAll("#selectModel")[0];

        storageTypeContainer.addEventListener("change", function(){

            Controller.initializeOption(storageSizeContainer);
            Controller.initializeOption(brandContainer);
            Controller.initializeOption(modelContainer);

            if(storageTypeContainer.value !== "-"){
                Controller.setStorageSize(container);
            }

        })

        storageSizeContainer.addEventListener("change", function(){

            let type = storageTypeContainer.value;
            Controller.initializeOption(brandContainer);
            Controller.initializeOption(modelContainer);
            if(storageSizeContainer.value != "-"){
                Controller.setBrandOption(type, brandContainer);
            }
            
        })

        brandContainer.addEventListener("change", function(){
            let type = storageTypeContainer.value;
            Controller.initializeOption(modelContainer);

            if(brandContainer.value != "-"){
                Controller.fetchAndAddModel(type, container);
            }

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

}

class Controller{

    static fetchAndAddModel(type, container){
        let brandContainer = container.querySelectorAll("#selectBrand")[0];
        let brand = brandContainer.value;
        let modelContainer = container.querySelectorAll("#selectModel")[0];

        let items = [];
        fetch(config.url + type).then(response => response.json()).then(function(data){
            items = data.filter(item => item.Brand == brand);
            if(type == "ram"){
                let slotNum = container.querySelectorAll("#selectHowMany")[0].value;
                items = items.filter(item =>{

                    // モデル名から容量を抽出
                    // TBのメモリはないけど念のため
                    let match = item.Model.match(/(\d+)x(\d+(GB|TB))/)
                    return match && match[1] == slotNum;

                })

            }else if(type == "hdd" || type == "ssd"){
                let size = container.querySelectorAll("#selectStorageSize")[0].value;
                items = items.filter(item => item.Model.includes(size))
            }

            for(let i=0;i<items.length;i++){
                let model = items[i].Model;
                let newOption = document.createElement("option");
                newOption.value = model;
                newOption.text = model;
                modelContainer.appendChild(newOption);
            }
        })
    }

    static setBrandOption(type, brandContainer){
        // 初回アクセス時にbrandsにブランド名を格納する。
        // ２回目以降はbrandsに格納されたブランド名を参照する。
        if(brands[type].length == 0){

            let brand = [];
            fetch(config.url + type).then(response => response.json()).then(data =>{
                data.forEach(item =>{
                    brand.push(item.Brand)
                })
                // 重複排除
                brand = [... new Set(brand)];

                // brandsに格納
                brand.forEach(brandName =>{
                    brands[type].push(brandName);
                })

                Controller.setOption(brandContainer, brands[type]);
            })
        }else{

            Controller.setOption(brandContainer, brands[type]);

        }
        
    }

    static setStorageSize(container){

        let storageTypeContainer = container.querySelectorAll("#selectStorageType")[0];
        let storageType = storageTypeContainer.value;
        let storageSizeContainer = container.querySelectorAll("#selectStorageSize")[0];

        let capacities = [];

        fetch(config.url + storageType.toLowerCase()).then(response => response.json()).then(data =>{
            
            // 容量を配列に格納
            data.forEach(item => {
                let model = item.Model;
                let match = model.match(/\d+TB|\d+GB/);
                if(match){
                    capacities .push(match[0]);
                }
                
            });

            // セットに格納することで、重複を排除
            let uniqueCapacities = [... new Set(capacities)];

            // 単位を揃えた上で比較を行い、降順に並べ替え
            uniqueCapacities.sort((a,b) => {
                let numA = Controller.convertToGB(a);
                let numB = Controller.convertToGB(b);
                return numB - numA;
            })

            // optionに追加
            Controller.setOption(storageSizeContainer, uniqueCapacities);
        })
    
    }

    static setOption(selectContainer, items){
        for(let i=0; i< items.length;i++){
            let newOption = document.createElement("option");
            newOption.value = items[i];
            newOption.text = items[i];
            selectContainer.appendChild(newOption);
        }
    }

    static convertToGB(capacity){
        let value = parseInt(capacity);
        if(capacity.includes("TB")){
            return value*1000;
        }
        return value;
    }

    static initializeOption(container){
        container.innerHTML = "";
        container.appendChild(Controller.setDefaultOption());
    }


    static setDefaultOption(){
        let defaultOption = document.createElement("option")
        defaultOption.text = "-";
        return defaultOption 
    }

}

Viewer.displayInitialPage();