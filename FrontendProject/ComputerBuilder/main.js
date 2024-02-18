const config ={
    target: document.getElementById("target"),
    displayPc: document.getElementById("displayPc"),
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
        <select id="selectCpuBrand" class="col-9">
            <option value="-">-</option>
            <option value="Intel">Intel</option>
            <option value="AMD">AMD</option>
        </select>
        <h5>Model</h5>
        <select id="selectCpuModel" class="col-9">
            <option value="-">-</option>
        </select>
        `
        container.innerHTML = htmlString;

        return container;
    }

    static displayGpuSelecter(){
        let container = document.createElement("div");
        container.classList.add("d-flex", "justify-content-start", "flex-column", "mx-3")
        let htmlString =
        `
        <h5>Brand</h5>
        <select id="selectGpuBrand" class="col-9">
            <option>-</option>
        </select>
        <h5>Model</h5>
        <select id="selectGpuModel" class="col-9">
            <option>-</option>
        </select>
        `
        container.innerHTML = htmlString;

        return container;
    }

    static displayMemorySelecter(){
        let container = document.createElement("div");
        container.classList.add("d-flex", "justify-content-start", "flex-column", "mx-3")
        let htmlString =
        `
        <h5>How many?</h5>
        <select id="selectHowMany" class="col-9">
            <option>-</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>
        <h5>Brand</h5>
        <select id="selectMemoryBrand" class="col-9">
            <option>-</option>
        </select>
        <h5>Model</h5>
        <select id="selectMemoryModel" class="col-9">
            <option>-</option>
        </select>
        `
        container.innerHTML = htmlString;

        return container;
    }

    static displayStorageSelecter(){
        let container = document.createElement("div");
        container.classList.add("d-flex", "justify-content-start", "flex-column", "mx-3")
        let htmlString =
        `
        <h5>HDD or SSD</h5>
        <select id="selectStrageType" class="col-9">
            <option>-</option>
        </select>
        <h5>Storage</h5>
        <select id="selectStrageSize" class="col-9">
            <option>-</option>
        </select>
        <h5>Brand</h5>
        <select id="selectStrageBrand" class="col-9">
            <option>-</option>
        </select>
        <h5>Model</h5>
        <select id="selectStrageModel" class="col-9">
            <option>-</option>
        </select>
        `
        container.innerHTML = htmlString;

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

Viewer.displayInitialPage();

