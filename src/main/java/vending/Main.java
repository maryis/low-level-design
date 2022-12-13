package vending;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        //the calculation and managing product list has not been implemented
        //but the state machine is complete


        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.setState(new FillingState());
        vendingMachine.fill(new ArrayList<>());

        vendingMachine.setState(new FillingState());
        vendingMachine.addItem(new Product("fg",55));

        vendingMachine.collectMoney(new Product("dfg",56));
        vendingMachine.dispenseChange(new Product("dsd",45));
        vendingMachine.dispenseItem(new Product("dgdf",56));


    }
}
