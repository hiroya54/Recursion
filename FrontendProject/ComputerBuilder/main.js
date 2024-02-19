const config ={
    target: document.getElementById("target"),
    displayPc: document.getElementById("displayPc"),
    url : "https://api.recursionist.io/builder/computers?type="
}

let brands = {"cpu":[], "gpu":[], "ram":[], "hdd":[], "ssd":[] }
let candidateItems = {"cpu":{}, "gpu":{}, "ram":{}, "storage":{}}

class Viewer{

    static count = 0;

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
            candidateItems["cpu"] = {};
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
            candidateItems["gpu"] = {};
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
            candidateItems["ram"] = {};
            Controller.setBrandOption("ram", brandContainer)
    
        })
        brandContainer.addEventListener("change", function(){

            Controller.initializeOption(modelContainer);
            candidateItems["ram"] = {};
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
            candidateItems["storage"] = {};

            if(storageTypeContainer.value !== "-"){
                Controller.setStorageSize(container);
            }

        })

        storageSizeContainer.addEventListener("change", function(){

            let type = storageTypeContainer.value;
            Controller.initializeOption(brandContainer);
            Controller.initializeOption(modelContainer);
            candidateItems["storage"] = {};

            if(storageSizeContainer.value != "-"){
                Controller.setBrandOption(type, brandContainer);
            }
            
        })

        brandContainer.addEventListener("change", function(){
            let type = storageTypeContainer.value;
            Controller.initializeOption(modelContainer);
            candidateItems["storage"] = {};
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
        container.querySelectorAll("#addPc")[0].addEventListener("click", function(){

            let isFilled = true;
            for(let type in candidateItems){

                if(Object.keys(candidateItems[type]).length === 0){
                    alert("Please fill in all forms.");
                    isFilled = false;
                    break;
                }
            }

            if(isFilled){
                // 計算処理
                config.target.querySelectorAll("#displayPc")[0].append(Viewer.displayBuiltPcPage());
            }
        })
        return container;
    }

    static displayBuiltPcPage(){

        Viewer.count += 1;

        let brandContainers = config.target.querySelectorAll("#selectBrand");
        let modelContainers = config.target.querySelectorAll("#selectModel");
        let storageType = config.target.querySelectorAll("#selectStorageType")[0].value.toUpperCase();
        let storageSize = config.target.querySelectorAll("#selectStorageSize")[0].value;

        let gamingScore = Controller.calculateGamingScore();
        let workScore = Controller.calculateWorkScore();
        
        let container = document.createElement("div");
        container.classList.add("bg-primary","text-white", "m-2", "col-12");
        container.innerHTML =
        `
        <div class="m-2 pt-3 d-flex justify-content-center">
            <h1>Your PC${Viewer.count}</h1>
        </div>
        <div class="m-2 pt-3 d-flex flex-column">
            <h1>CPU</h1>
            <h5>Brand: ${brandContainers[0].value}</h5>
            <h5>Model: ${modelContainers[0].value}</h5>
        </div>
        <div class="m-2 pt-3 d-flex flex-column">
            <h1>GPU</h1>
            <h5>Brand: ${brandContainers[1].value}</h5>
            <h5>Model: ${modelContainers[1].value}</h5>
        </div>
        <div class="m-2 pt-3 d-flex flex-column">
            <h1>RAM</h1>
            <h5>Brand: ${brandContainers[2].value}</h5>
            <h5>Model: ${modelContainers[2].value}</h5>
        </div>
        <div class="m-2 pt-3 d-flex flex-column">
            <h1>Storage</h1>
            <h5>Disk: ${storageType}</h5>
            <h5>Storage: ${storageSize}</h5>
            <h5>Brand: ${brandContainers[3].value}</h5>
            <h5>Model: ${modelContainers[3].value}</h5>
        </div>
        <div class="m-2 pt-3 d-flex justify-content-around align-items-center">
            <h1>Gaming: ${gamingScore}%</h1>
            <h1>Work: ${workScore}%</h1>
        </div>
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
                type ="storage"
            }
            // 可能性のあるModelがitemsに格納されている。
            // これを有効活用したい
            // モデル名をキー、ベンチマークを値として候補をすべて格納しておく？

            for(let i=0;i<items.length;i++){
                let model = items[i].Model;
                let newOption = document.createElement("option");
                newOption.value = model;
                newOption.text = model;
                modelContainer.appendChild(newOption);

                if(candidateItems[type][model] === undefined) candidateItems[type][model] = items[i].Benchmark;
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

    static setSelectedItems(type, container){
        let model = container.querySelectorAll("#selectModel")[0].value;

        fetch(config.url + type).then(response => response.json()).then(data => {

            let selectedItem = data.filter(item => item.Model == model);

            selectedItems[type] = selectedItem;
        })
    }

    static calculateGamingScore(){

        let cpuModel = config.target.querySelectorAll("#selectModel")[0].value;
        let gpuModel = config.target.querySelectorAll("#selectModel")[1].value;
        let ramModel = config.target.querySelectorAll("#selectModel")[2].value;
        let storageModel = config.target.querySelectorAll("#selectModel")[3].value;
   
        return Math.floor(candidateItems["cpu"][cpuModel]*0.25 + candidateItems["gpu"][gpuModel]*0.6 
                + candidateItems["ram"][ramModel]*0.125 + candidateItems["storage"][storageModel]*0.025);


    }

    static calculateWorkScore(){
        let cpuModel = config.target.querySelectorAll("#selectModel")[0].value;
        let gpuModel = config.target.querySelectorAll("#selectModel")[1].value;
        let ramModel = config.target.querySelectorAll("#selectModel")[2].value;
        let storageModel = config.target.querySelectorAll("#selectModel")[3].value;

        return Math.floor(candidateItems["cpu"][cpuModel]*0.6 + candidateItems["gpu"][gpuModel]*0.25
                + candidateItems["ram"][ramModel]*0.1 + candidateItems["storage"][storageModel]*0.05);

    }

}

Viewer.displayInitialPage();
