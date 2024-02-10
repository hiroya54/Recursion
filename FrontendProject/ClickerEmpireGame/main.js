function displayNone(ele){
    ele.classList.remove("d-block");
    ele.classList.add("d-none");
}

function displayBlock(ele){
    ele.classList.remove("d-none");
    ele.classList.add("d-block");
}

const config = {
    initialPage:document.getElementById("initialPage"),
    mainPage:document.getElementById("mainPage"),
}

class User{
    inititalMoney = 50000;
    initialOneClickWage = 25;
    constructor(name, items){
        this.name=name;
        this.money = this.inititalMoney;
        this.age = 20;
        this.clickCount = 0;
        this.passedDays = 0;
        this.items = items;
    }

    countUpClickCount(){
        this.clickCount++;
    }

    updateMoneyByClick(){
        this.money += this.items[0].owned * this.items[0].unitWage + this.initialOneClickWage;
    }

    purchaseItem(item, amount){
        let payment = item.price * amount;
        this.money -= payment;
        return payment;
    }

    reset(){
        let newItems = Controller.createNewItems();

        this.name = this.name;
        this.money = this.inititalMoney;
        this.age = 20;
        this.clickCount = 0;
        this.passedDays = 0;
        this.items = newItems;
    }

    updateOwned(amount,item){
        console.log()
        item.owned += amount;
        return item.owned;
    }

    static fromJSON(jsonString) {
        let userData = JSON.parse(jsonString);
        let user = new User(userData.name);
        user.money = userData.money;
        user.age = userData.age;
        user.clickCount = userData.clickCount;
        user.passedDays = userData.passedDays;
        user.items = userData.items;
        return user;
    }
}

class Item{
    constructor(name, type, price, unitWage, maxQuantitiy,imgURL){
        this.name=name;
        this.type = type;
        this.price = price;
        this.unitWage = unitWage;
        this.maxQuantitiy = maxQuantitiy;
        this.imgURL = imgURL;
        this.owned = 0;
    }
}

class View{

    static displayInitialPage(){
        let container = document.createElement("div");
        container.classList.add("vh-100", "d-flex", "justify-content-center", "align-items-center")
        container.innerHTML = `
        <div class="bg-white text-center p-4">
            <h2 class="mb-3">Clicker Empire Game</h2>
            <form>
                <div class="form-row pb-3">
                    <div class="col">
                        <input type="text" class="form-control" placeholder="Your name">
                    </div>
                </div>
            </form>
            <div class="d-flex justify-content-between">
                <div class="col-6 pl-0">
                    <button type="submit" class="btn btn-primary col-12 back-btn" id="newGame">New</button>
                </div>
                <div class="col-6 pl-0">
                    <button type="submit" class="btn btn-primary col-12 back-btn" id="login">Login</button>
                </div>
    
            </div>
        </div>
        `
        return container;
    }

    static displayMainPage(user){

        //idを持つ要素を含む側だけをcontainerのinnerHTMLに設定する

        let container = document.createElement("div");
    
        let view = document.createElement("div");
        view.classList.add("d-flex", "justify-content-center", "p-md-5", "pb-5");
        view.style.height = "100vh";
    
        let page = document.createElement("div");
        page.classList.add("bg-navy", "p-2", "d-flex", "col-md-11", "col-lg-10");
    
        container.append(view);
        view.append(page);
        
        //burgerStatusを表示
        let burgerContainer = document.createElement("div");
        page.append(burgerContainer);
        burgerContainer.classList.add("bg-dark", "col-4", "p-2");
        burgerContainer.id="burgerStatus";
        burgerContainer.append(View.displayBurgerStatus(user));

        let rightContainer = document.createElement("div");
        page.append(rightContainer);
        rightContainer.classList.add("col-8");
    
        //user情報を表示
        rightContainer.append(View.displyaUserInfo(user));
        
        //item一覧を表示
        rightContainer.append(View.displayItems(rightContainer,user));
        
        //saveとリセット用のボタンを表示
        let saveResetContainer = document.createElement("div");
        rightContainer.append(saveResetContainer);
        saveResetContainer.classList.add("d-flex", "justify-content-end", "mt-2");
        saveResetContainer.innerHTML = `
        <div class="border p-2 mr-2 hover" id="reset">
            <i class="fas fa-undo fa-2x text-white"></i>
        </div>
        <div class="border p-2 hover" id="save">
            <i class="fas fa-save fa-2x text-white"></i>
        </div>
        `;
    
        //クリックイベントを設定するimgをinnerHTMLで書き換えることになるので、leftContainerにイベントリスナーを設定する
        burgerContainer.addEventListener("click",function(event){
            if(event.target.id === "burger"){
                user.countUpClickCount();
                user.updateMoneyByClick();
                burgerContainer.innerHTML = `
                <div>
                    <div class="bg-navy text-white text-center">
                        <h5>${user.clickCount} Burgers</h5>
                        <p>one click ¥${user.items[0].owned * user.items[0].unitWage + user.initialOneClickWage}</p>
                    </div>
                    <div class="p-2 pt-5 d-flex justify-content-center">
                        <img src="https://cdn.pixabay.com/photo/2014/04/02/17/00/burger-307648_960_720.png" width="80%" class="py-2 hover img-fuid" id="burger">
                    </div>
                </div>
                `;
                //クリックした直後にuser情報も更新する
                View.updateUserInfo(user);
            }
        })
    
        //save用イベント
        let saveBtn = container.querySelectorAll("#save")[0];
        saveBtn.addEventListener("click", function(){
            alert("Saved your data. Please put the same name when you login.");
            //保存するためにuserをjson文字列化
            let saveUser = JSON.stringify(user);
            //localstrageに保存
            localStorage.setItem(user.name,saveUser);
            //ログインフォームに戻る
            Controller.stopTimer();
            Controller.changeMainToInitial();
        })
        
        //リセット用
        let resetBtn = container.querySelectorAll("#reset")[0];
        resetBtn.addEventListener("click", function(){
            if(confirm("Reset All Data?")){
                //itemsの所持数を0にする
                for(let i=0;i<user.items.length;i++){
                    user.items[i].owned = 0;
                }
                user.reset();
                config.mainPage.innerHTML = "";
                Controller.stopTimer();
                config.mainPage.append(View.displayMainPage(user));
                Controller.startTimer(user);
            }
        }) 
    
        return container;
    
    }

    static displayBurgerStatus(user){
        let container = document.createElement("div");
        container.innerHTML = `
        <div class="bg-navy text-white text-center">
            <h5>${user.clickCount} Burgers</h5>
            <p>one click ¥${user.items[0].owned * user.items[0].unitWage + user.initialOneClickWage}</p>
        </div>
        <div class="p-2 pt-5 d-flex justify-content-center">
            <img src="https://cdn.pixabay.com/photo/2014/04/02/17/00/burger-307648_960_720.png" width="80%" class="py-2 hover img-fuid" id="burger">
        </div>
        `;

        return container;
    }

    static displyaUserInfo(user){
        let userInfoContainer = document.createElement("div");
        userInfoContainer.classList.add("p-1", "bg-navy");
        userInfoContainer.id="userInfo";
        userInfoContainer.innerHTML = `
        <div class="d-flex flex-wrap p-1">
            <div class="text-white text-center col-12 col-sm-6 userInfoBorder">
                <p>${user.name}</p>
            </div>
            <div class="text-white text-center col-12 col-sm-6 userInfoBorder">
                <p>${user.age + Math.floor(user.passedDays/365)} years old</p>
            </div>
            <div class="text-white text-center col-12 col-sm-6 userInfoBorder">
                <p>${user.passedDays} days</p>
            </div>
            <div class="text-white text-center col-12 col-sm-6 userInfoBorder">
                <p>￥${user.money}</p>
            </div>
        </div>
        `
        return userInfoContainer;
    
    }

    static displayItems(rightContainer, user){
        let container = document.createElement("div");
        container.classList.add("bg-dark", "mt-2", "p-1", "overflow-auto", "flowHeight")
        container.id="displayItems";
    
        let itemsContainer = document.createElement("div");
        container.append(itemsContainer);
        for(let i=0;i<user.items.length;i++){
            itemsContainer.innerHTML+=`
            <div class="text-white d-sm-flex align-items-center m-1 selectItem">
                <div class="d-none d-sm-block p-1 col-sm-3">
                    <img src=${user.items[i].imgURL} class="img-fluid">
                </div>
                <div class="col-sm-9">
                    <div class="d-flex justify-content-between">
                        <h4>${user.items[i].name}</h4>
                        <h4>${user.items[i].owned}</h4>
                    </div>
                    <div class="d-flex justify-content-between">
                        <p>￥${user.items[i].price}</p>
                        <p class="text-success">￥${user.items[i].unitWage} /${user.items[i].type}</p>
                    </div>
                </div>                      
            </div>
            `;
        }
        let selectItems = itemsContainer.querySelectorAll(".selectItem");
        for(let i=0;i<selectItems.length;i++){
            selectItems[i].addEventListener("click",function(event){
                //購入画面を表示する
                container.innerHTML = "";
    
                let purchaseContainer = document.createElement("div");
                container.append(purchaseContainer);
                purchaseContainer.append(View.displayPurchase(user.items[i]));
    
                //個数を入力したらtotalを更新
                let input = purchaseContainer.querySelectorAll(".form-control")[0];
                let total = purchaseContainer.querySelectorAll("#totalPrice")[0];
                let totalValue = 0;
                input.addEventListener("input", function(){
                    totalValue = input.value >0 ?input.value * user.items[i].price : 0;
                    total.innerHTML = `total: ￥${totalValue}`;
                })
    
                //backボタンをクリックするとitem画面に戻る
                //購入はしておらずitem画面を更新する必要はないので、itemContainerをappendする
                let backBtn = purchaseContainer.querySelectorAll("#back")[0];
                backBtn.addEventListener("click", function(){
                    container.innerHTML = "";
                    container.append(itemsContainer);
                })
    
                let purchaseBtn = purchaseContainer.querySelectorAll("#purchase")[0];
                purchaseBtn.addEventListener("click", function(){
                    //所持数+valueが上限値を超えている場合
                    if(totalValue > user.money){
                        alert("You don't have enough money.")
                    }else if(input.value + user.items[i].owned > user.items[i].maxQuantitiy){
                        alert("You can't buy anymore.");
                    }else if(input.value <= 0){
                        alert("Invalid Number");
                    }
                    else{
                        user.updateOwned(parseInt(input.value),user.items[i]);
                        user.purchaseItem(user.items[i], input.value);
                    }
                    //問題ない場合は、所有数を更新、所持金をマイナス
                    //itemsContainer画面に戻る(所有数が更新されているので、新しい画面を作成？その場合、イベントリスナー消える？)
                    config.mainPage.innerHTML = "";
                    config.mainPage.append(View.displayMainPage(user));
                })
            })
        }
    
        return container;
    }

    static displayPurchase(item){
        let container = document.createElement("div");
        container.innerHTML = `
        <div class="bg-navy p-2 m-1 text-white">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <h4>${item.name}</h4>
                            <p>Max purchases: ${item.maxQuantitiy}</p>
                            <p>Price: ￥${item.price}</p>
                            <p>Get ￥${item.unitWage} /${item.type}</p>
                    </div>
                    <div class="p-2 d-sm-block col-sm-5">
                        <img src=${item.imgURL} class="img-fluid">
                    </div>
                </div>
                <p>How many would you like to buy?</p>
                <input type="number" placeholder="0" class="col-12 form-control">
                <p class="text-right" id="totalPrice">total: ￥0</p>
                <div class="d-flex justify-content-between pb-3">
                    <button class="btn btn-outline-primary col-5 bg-light" id="back">Go Back</button>
                    <button class="btn btn-primary col-5" id="purchase">Purchase</button>
                </div>
            </div>
        `;
        return container;
    }

    static updateUserInfo(user){
        document.getElementById("userInfo").innerHTML = `
        <div class="d-flex flex-wrap p-1">
            <div class="text-white text-center col-12 col-sm-6 userInfoBorder">
                <p>${user.name}</p>
            </div>
            <div class="text-white text-center col-12 col-sm-6 userInfoBorder">
                <p>${user.age + Math.floor(user.passedDays/365)} years old</p>
            </div>
            <div class="text-white text-center col-12 col-sm-6 userInfoBorder">
                <p>${user.passedDays} days</p>
            </div>
            <div class="text-white text-center col-12 col-sm-6 userInfoBorder">
                <p>￥${user.money}</p>
            </div>
        </div>
        `
    }

}

class Controller{
    timer;

    static startTimer(user){

        Controller.timer = setInterval(function(){
            user.passedDays++;
            let totalWage = 0;
            for(let i=1;i<user.items.length;i++){
                totalWage += user.items[i].owned * user.items[i].unitWage;
            }
            user.money += totalWage;
            View.updateUserInfo(user);
        },1000);
    }

    static stopTimer(){
        clearInterval(Controller.timer);
    }

    static changeInitialToMain(user){
        config.mainPage.innerHTML = "";
        config.initialPage.innerHTML = "";
        displayNone(config.initialPage);
        displayBlock(config.mainPage);
        config.mainPage.append(View.displayMainPage(user));
        Controller.startTimer(user);
    }

    static changeMainToInitial(){
        config.mainPage.innerHTML = "";
        config.initialPage.innerHTML = "";
        displayNone(config.mainPage);
        displayBlock(config.initialPage);
        config.initialPage.append(Controller.gameStart());
        Controller.stopTimer();
    }

    static createNewItems(){
        let items = [
            new Item ("Flip machine", "click", 15000, 25, 500, "https://cdn.pixabay.com/photo/2019/06/30/20/09/grill-4308709_960_720.png"),
            new Item ("EFT Stock", "sec", 300000, 0.1, Infinity, "https://cdn.pixabay.com/photo/2016/03/31/20/51/chart-1296049_960_720.png"),
            new Item ("EFT Bonds", "sec", 300000, 0.07, Infinity, "https://cdn.pixabay.com/photo/2016/03/31/20/51/chart-1296049_960_720.png"),
            new Item ("Lemonade Stand", "sec", 30000, 30, 1000, "https://cdn.pixabay.com/photo/2012/04/15/20/36/juice-35236_960_720.png"),
            new Item ("Ice Cream Truck", "sec", 100000, 120, 500, "https://cdn.pixabay.com/photo/2020/01/30/12/37/ice-cream-4805333_960_720.png"),
            new Item ("House", "sec", 20000000, 32000, 100, "https://cdn.pixabay.com/photo/2016/03/31/18/42/home-1294564_960_720.png"),
            new Item ("TownHouse", "sec", 40000000, 64000, 100, "https://cdn.pixabay.com/photo/2019/06/15/22/30/modern-house-4276598_960_720.png"),
            new Item ("Mansion", "sec", 250000000, 500000, 20, "https://cdn.pixabay.com/photo/2017/10/30/20/52/condominium-2903520_960_720.png"),
            new Item ("Industrial Space", "sec", 1000000000, 2200000, 10, "https://cdn.pixabay.com/photo/2012/05/07/17/35/factory-48781_960_720.png"),
            new Item ("Hotel Skyscraper", "sec", 10000000000, 25000000, 5, "https://cdn.pixabay.com/photo/2012/05/07/18/03/skyscrapers-48853_960_720.png"),
            new Item ("Bullet-Speed Sky Railway", "sec", 10000000000000, 30000000000, 1, "https://cdn.pixabay.com/photo/2013/07/13/10/21/train-157027_960_720.png"),
        ]
        return items;
    }

    static gameStart(){

        config.initialPage.append(View.displayInitialPage());
    
        let newBtn = document.getElementById("newGame");
        newBtn.addEventListener("click", function(){
            let name = config.initialPage.querySelectorAll(".form-control")[0].value;
            if(name===""){
                alert("Please put your name");
            }else{
                let items = Controller.createNewItems();
                let user = new User(name, items);
                Controller.changeInitialToMain(user);
            }
        })
    
        let loginBtn = document.getElementById("login");
        loginBtn.addEventListener("click", function(){
            let name = config.initialPage.querySelectorAll(".form-control")[0].value;
            let userString = localStorage.getItem(name);
            if(name===""){
                alert("Please put your name");
            }else if(userString===null){
                alert("There is no data.");
            }
            else{
                let loginUser = User.fromJSON(userString);
                Controller.changeInitialToMain(loginUser);
            }
        })

    }
}
Controller.gameStart();