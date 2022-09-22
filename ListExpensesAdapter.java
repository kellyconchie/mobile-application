package com.example.testingproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListExpensesAdapter extends BaseAdapter {

    Context context;
    ArrayList<ExpensesModel> ExpensesArrayList;

    public ListExpensesAdapter(Context context, ArrayList<ExpensesModel> ExpensesArrayList) {
        this.context = context;
        this.ExpensesArrayList = ExpensesArrayList;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return ExpensesArrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate layout
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expenses_custom_list,null);
            TextView date = (TextView)convertView.findViewById(R.id.date);
            TextView amount = (TextView)convertView.findViewById(R.id.amount);
            TextView category = (TextView)convertView.findViewById(R.id.category);

            ExpensesModel expensesModel = ExpensesArrayList.get(position);
            date.setText(expensesModel.getDate());
            amount.setText(String.valueOf(expensesModel.getAmount()));
            category.setText(expensesModel.getCategory());

        }

        return convertView;
    }

    @Override
    public int getCount() {
        return this.ExpensesArrayList.size();
    }
}