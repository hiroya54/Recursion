package abc;

class Main{
    public static void main(String[] args){
        //BankAccount user1 = new BankAccount("Chase", "Claire Simmmons", 30000);
        //System.out.println(user1.withdrawMoney(2000));
        //System.out.println(user1.depositMoney(10000));
        //System.out.println(user1.pastime(93));
        
        //BankAccount user2 = new BankAccount("Bank Of America", "Remy Clay", 10000);
        //System.out.println(user2.withdrawMoney(5000));
        //System.out.println(user2.depositMoney(12000));
        //System.out.println(user2.pastime(505));
            
    }
}

class BankAccount{

    public String bankName;
    public String ownerName;
    public int savings;
    
    public BankAccount(String bankName, String ownerName, int savings){
        this.bankName=bankName;
        this.ownerName=ownerName;
        this.savings=savings;
    }

    public int depositMoney(int depositAmount){
        if(savings<=20000) return this.saving+depositAmount-100;
        else return this.saving+depositAmount;
    }
    public int withdrawMoney(int withdrawAmount){
        if(withdrawAmount<=savings/5)  this.saving-=withdrawAmount;
        else this.saving=this.saving*0.8;

        return this.saving;
    }

    public double pasttime(int days){
        this.saving+=0.25*days;

        return this.saving;
    }
}