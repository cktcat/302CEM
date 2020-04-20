import java.util.Scanner;

/**
 * Write a description of class Cal here.
 *
 * Name: Chan Ka Tsang
 * ID: 56247464
 */
public class Main
{
    public static void main(String[] args) 
    {
        Main main = new Main();
        Calculator Cal = new Calculator();
        int SelfIncome = 0;
        int SpouseIncome = 0;
        int SelfTotal = 0;
        int SpouseTotal = 0;
        int JointTotal = 0;
        String isExit = "2";
        Scanner input = new Scanner(System.in);

        do{
            main.printInfo();
            String isMarried = input.nextLine();

            if (isMarried.equals("2"))
            {
                System.out.print("Please Input the self income : ");
                String Strincome = input.nextLine(); 
                SelfIncome = Integer.parseInt(Strincome);

                System.out.print("Please Input the spouse income : ");
                Strincome = input.nextLine(); 
                SpouseIncome = Integer.parseInt(Strincome);
                Cal.CalMarried(SelfIncome,SpouseIncome);

                System.out.println();
                System.out.println("                         Under Separate Taxation    |  Under Joint Assessment");
                System.out.println("                                Self  |     Spouse  |          ");  
                System.out.print("Total Income          :      ");
                System.out.print(Cal.ChangeStr(SelfIncome) +"  |    "); 
                System.out.print(Cal.ChangeStr(SpouseIncome) +"  |       ");
                System.out.println(Cal.ChangeStr(SelfIncome + SpouseIncome));

                System.out.print("Deductions            :      ");
                System.out.print(Cal.ChangeStr(Cal.getSelfDeductions()) +"  |    "); 
                System.out.print(Cal.ChangeStr(Cal.getSpouseDeductions()) +"  |       ");
                System.out.println(Cal.ChangeStr(Cal.getJointDeductions()));
                System.out.println("Total Allowances    :         132000  |     132000  |        264000");

                System.out.print("Net Chargeable Income :      ");
                System.out.print(Cal.ChangeStr(Cal.getSelfCharge()) +"  |    "); 
                System.out.print(Cal.ChangeStr(Cal.getSpouseCharge()) +"  |       ");
                System.out.println(Cal.ChangeStr(Cal.getChargeIncomeJoint()));

                SelfTotal = Cal.getSelfTotal();
                SpouseTotal = Cal.getSpouseTotal();
                JointTotal = Cal.getJointTotal();
                
                System.out.print("Tax Payable By you    :      ");
                System.out.print(Cal.ChangeStr(SelfTotal) +"  |    "); 
                System.out.print(Cal.ChangeStr(SpouseTotal) +"  |       ");
                System.out.println(Cal.ChangeStr(JointTotal));

                System.out.print("Total Tax Payable by  :              ");
                System.out.print(Cal.ChangeStr(SelfTotal + SpouseTotal) +"        |       "); 
                System.out.println(Cal.ChangeStr(JointTotal) );
                System.out.println("you and your spouse         \n");
                if(JointTotal == 0 || (SelfTotal + SpouseTotal) == 0 ){
                    System.out.println("You don't need to pay the tax.");
                }else if(JointTotal == (SelfTotal + SpouseTotal)){
                    System.out.println("Joint or Separate Taxation is acceptable.");
                }else if(JointTotal < (SelfTotal + SpouseTotal) ){
                    System.out.println("The best choice is Joint Assessment. ");
                }else{
                    System.out.println("The best choice is Separate Taxation. "); 
                }
            } else{
                System.out.print("Please Input the self income : ");
                String Strincome = input.nextLine(); 
                SelfIncome = Integer.parseInt(Strincome);
                System.out.println();
                Cal.CalSingle(SelfIncome);

                System.out.print("Total Income          :      ");
                System.out.println(Cal.ChangeStr(SelfIncome)); 

                System.out.print("Deductions            :      ");
                System.out.println(Cal.ChangeStr(Cal.getSelfDeductions())); 

                System.out.print("Net Chargeable Income :      ");
                System.out.println(Cal.ChangeStr(Cal.getSelfCharge())); 

                System.out.print("Tax Payable By you    :      ");
                System.out.println(Cal.ChangeStr(Cal.getSelfTotal())); 
            }
            System.out.println();

            System.out.print("(1) Again / (2) Exit : ");
            isExit = input.nextLine();
            System.out.println("\n");
        } while(!isExit.equals("2"));
    }
    
    public void printInfo(){
        System.out.println("Salaries Tax Computation");
            System.out.println("Year of assessment 2020/21");
            System.out.println("the Marital status ");
            System.out.println("(1) Single / Separated / Divorced / Widowed | ");
            System.out.println("(2) Married");
            System.out.print("Please Input the Marital status (1)/(2): ");
    }
}


