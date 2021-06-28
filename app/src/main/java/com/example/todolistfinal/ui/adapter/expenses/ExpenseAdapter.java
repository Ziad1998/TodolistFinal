
package com.example.todolistfinal.ui.adapter.expenses;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todolistfinal.R;
import com.example.todolistfinal.data.expenses.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends ArrayAdapter<Expense>
{
    private Context mContext;
    private List<Expense> contactList = new ArrayList<Expense>();

    public ExpenseAdapter( Context context, ArrayList<Expense> list)
    {
        super( context, 0, list);
        mContext = context;
        contactList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        String change;
        // Associates the list with the XML Layout file "contact_view"
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.expense_view,parent,false);

        // Individually handles each Contact in the contactList
        Expense currentExpense = contactList.get(position);

        // Extracts the item name of the Expense
        TextView item = (TextView) listItem.findViewById(R.id.textView_Item);
        item.setText(currentExpense.getItem());

        // Extracts the price of the Expense
        TextView price = (TextView) listItem.findViewById(R.id.textView_price);

        if ( !currentExpense.getPrice().equals(""))
            change = currentExpense.getPrice() +" $"  ;
        else
            change = currentExpense.getPrice();
        price.setText(change);

        // Extracts Date
        if ( !currentExpense.getDate().equals(""))
            change =currentExpense.getDate();
        else
            change = currentExpense.getDate();
        TextView date = (TextView) listItem.findViewById(R.id.textView_date);
        date.setText(change);

        // Extracts Quantity
        if ( !currentExpense.getNote().equals(""))
            change =  currentExpense.getNote();
        else
            change = currentExpense.getNote();
        TextView quantity = (TextView) listItem.findViewById(R.id.textView_note);
        quantity.setText(change);

        return listItem;
    }
}
