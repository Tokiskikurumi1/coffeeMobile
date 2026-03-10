package com.example.javatest.account;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.adapter.RevenueAdapter;
import com.example.javatest.dao.BillDAO;
import com.example.javatest.model.Revenue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class RevenueFragment extends Fragment {

    RecyclerView recyclerView;
    RevenueAdapter adapter;
    ArrayList<Revenue> list;
    EditText edtStartDate, edtEndDate;
    Button btnFilter;
    TextView txtTotalRevenue;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_revenue, container, false);

        recyclerView = view.findViewById(R.id.rvRevenue);
        edtStartDate = view.findViewById(R.id.edtStart);
        edtEndDate = view.findViewById(R.id.edtEnd);
        btnFilter = view.findViewById(R.id.btnFilter);

        txtTotalRevenue = view.findViewById(R.id.txtTotalRevenue);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        BillDAO billDAO = new BillDAO(getContext());
        list = billDAO.getAllRevenue();

        adapter = new RevenueAdapter(list, idBill -> {

            Bundle b = new Bundle();
            b.putInt("idBill", idBill);

            BillDetailFragment f = new BillDetailFragment();
            f.setArguments(b);

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, f)
                    .addToBackStack(null)
                    .commit();
        });

        recyclerView.setAdapter(adapter);
        updateTotalRevenue();
        // DATE PICKER START
        edtStartDate.setOnClickListener(v -> showDatePicker(edtStartDate));

        // DATE PICKER END
        edtEndDate.setOnClickListener(v -> showDatePicker(edtEndDate));

        // FILTER
        btnFilter.setOnClickListener(v -> {

            Long start = (Long) edtStartDate.getTag();
            Long end = (Long) edtEndDate.getTag();

            list.clear();
            list.addAll(billDAO.getRevenueByDate(start, end));

            adapter.notifyDataSetChanged();
            updateTotalRevenue(); // thêm dòng này
        });
        return view;
    }
    private void showDatePicker(EditText edt){

        Calendar c = Calendar.getInstance();

        new DatePickerDialog(getContext(),
                (view, year, month, day) -> {

                    Calendar selected = Calendar.getInstance();
                    selected.set(year, month, day, 23, 59, 59);

                    long time = selected.getTimeInMillis();

                    edt.setText(day + "/" + (month+1) + "/" + year);

                    // lưu timestamp
                    edt.setTag(time);

                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void updateTotalRevenue(){

        double total = 0;

        for(Revenue r : list){
            total += r.getTotal();
        }

        txtTotalRevenue.setText(
                "Tổng doanh thu: " + String.format("%,.0f VND", total)
        );
    }
}