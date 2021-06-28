

package com.example.todolistfinal.data.expenses;


import com.example.todolistfinal.data.expenses.Expense;

import java.util.ArrayList;

public class Calculate
{
    private double avgExpense;
    private double totalExpense;
    private double lowestExpense;
    private double highestExpense;


    public Calculate()
    {
        avgExpense = 0;
        totalExpense = 0;
        lowestExpense = 0;
        highestExpense = 0;
    }

    public double[] getStacks(ArrayList<Expense> elist)
    {
        double[] fail = {0,0,0,0};
        if ( elist.size() == 0 )
            return fail;
        calcTotal(elist);
        calcAverage(elist);
        calcLowest(elist);
        calcHighest(elist);
        double[] answers = new double[] {totalExpense, avgExpense, highestExpense, lowestExpense};
        return answers;
    }

    public double calcTotal(ArrayList<Expense> elist)
    {
        double t = 0;
        for (Expense s: elist)
        {
            if ( s.getPrice().equals("") )
                t += 0;
            else
            t += (Double.parseDouble(String.valueOf(s.getPrice())));
        }
        totalExpense = t;
        return totalExpense;


    }

    public void calcAverage(ArrayList<Expense> elist)
    {
        int count = 0;
        double t = 0;
        for (Expense s: elist)
        {
            if ( s.getPrice().equals("") )
                t += 0;
            else
                {
                count++;
                t += Double.parseDouble(String.valueOf(s.getPrice()));
                }
        }
        avgExpense = t / count;
    }

    public void calcHighest(ArrayList<Expense> elist)
    {
        double high = 0;
        for (Expense s: elist)
        {
            if ( !s.getPrice().equals("") )
                if ( Double.parseDouble(String.valueOf(s.getPrice())) > high )
                    high = Double.parseDouble(String.valueOf(s.getPrice()));
        }
        highestExpense = high;
    }

    public void calcLowest(ArrayList<Expense> elist)
    {
        double low = Double.parseDouble(String.valueOf(elist.get(0).getPrice()));

        for (Expense s: elist)
        {
            if ( !s.getPrice().equals("") )
                if ( Double.parseDouble(String.valueOf(s.getPrice())) < low )
                    low = Double.parseDouble(String.valueOf(s.getPrice()));
        }
        lowestExpense = low;
    }


}
