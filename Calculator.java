class Calculator {
    private static final double MPF = 0.05;          //5% Less MPF
    private static final int    BASICALLOW = 132000; //basic allowance
    private static final int    BASIC = 50000;       //First...50000
    private static final double[] RATE = {0.02 ,0.06 ,0.10 ,0.14 ,0.17};
    private static final double STANDARD = 0.15;     //15% Standard Rate
    private static final int MaxMPF = 18000;
    private double standardIncome;
    private int SelfDeductions,SpouseDeductions,JointDeductions;
    private int SelfTotal,SpouseTotal,JointTotal;
    private int SelfCharge,SpouseCharge,ChargeIncomeJoint;

    public Calculator(){

    }

    public int Deductions(int income){
        int deductions = 0;
        deductions = (int)(income * MPF);   //5% MPF (2020/21)
        if (deductions > MaxMPF){
            deductions = MaxMPF;    //Max MPF(18000)
        }
        return deductions;
    }

    public int ChargeIncome(int income,int plan)
    {
        int ChargeIncome = 0;
        int basicallow = BASICALLOW;        //132000 (2020/21)
        if (plan==2){
            basicallow = BASICALLOW * 2;    //264000 (2020/21)
        }
        ChargeIncome = income - basicallow;
        if (ChargeIncome <= 0){
            ChargeIncome = 0;
        }
        return ChargeIncome;
    }

    public int Total(int income){
        int total = 0;
        int i = 0;
        if (income ==0){       //Net Chargeable Income under basic allowance
            total = 0;         //Don't need to pay the tax payable
        }else{
            do{
                if( income >= BASIC){
                    total += (int)(BASIC * RATE[i]) ;
                    income = income - BASIC;
                    i++;
                }
            } while( (i!=4) && (income > BASIC));
            // More than 200000 income or
            // Subtract per 50000, the remainder is less than 50000
            total += (int)(income * RATE[i]);
            if(standardIncome<total){
                total = (int)standardIncome;
            }
        }
        return total;
    }

    public String ChangeStr(int number){
        String Str = "";
        if(number<0){
            Str = "       " + number ;  //"_______"
        }else if(number<10){
            Str = "      " + number ;   //"______1"
        }else if(number<100){
            Str = "     " + number ;    //"_____11"
        }else if(number<1000){
            Str = "    " + number ;     //"____111"
        }else if(number<10000){
            Str = "   " + number ;      //"___1111"
        }else if(number<100000){
            Str = "  " + number ;       //"__11111"
        }else if(number<1000000){
            Str = " " + number ;        //"_111111"
        }else{
            Str = "" + number ;         //"1111111"
        }
        return Str;
    }

    public void StandardRate(int income,int Deductions){
        standardIncome = (income - Deductions) *  STANDARD;
    }

    public void CalSingle(int income){
        SelfDeductions = Deductions(income);
        SelfCharge = ChargeIncome(income - SelfDeductions , 1);
        StandardRate(income,SelfDeductions);
        SelfTotal = Total(SelfCharge);
    }
    
    public void CalMarried(int SelfIncome,int SpouseIncome){
        SelfDeductions = Deductions(SelfIncome);
        SpouseDeductions = Deductions(SpouseIncome);
        JointDeductions = SelfDeductions + SpouseDeductions;
        SelfCharge = ChargeIncome(SelfIncome - SelfDeductions , 1);
        SpouseCharge = ChargeIncome(SpouseIncome - SpouseDeductions , 1);
        ChargeIncomeJoint = ChargeIncome(SelfIncome + SpouseIncome - JointDeductions , 2);
        
        StandardRate(SelfIncome,SelfDeductions);
        SelfTotal = Total(SelfCharge);
        StandardRate(SpouseIncome,SpouseDeductions);
        SpouseTotal = Total(SpouseCharge);
        StandardRate(SelfIncome+SpouseIncome,SelfDeductions+SpouseDeductions);
        JointTotal = Total(ChargeIncomeJoint);
    }

    //return the variable data
    public int getSelfDeductions(){return SelfDeductions;}
    public int getSelfCharge(){return SelfCharge;}
    public int getSelfTotal(){return SelfTotal;}
    public int getSpouseDeductions(){return SpouseDeductions;}
    public int getJointDeductions(){return JointDeductions;}
    public int getSpouseCharge(){return SpouseCharge;}
    public int getChargeIncomeJoint(){return ChargeIncomeJoint;}
    public int getSpouseTotal(){return SpouseTotal;}
    public int getJointTotal(){return JointTotal;}
}
