package com.example.covidapps.view;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.covidapps.R;
import com.example.covidapps.model.webService.pojo.response.CovidResponse;
import com.example.covidapps.model.webService.pojo.response.FeaturesItem;
import com.example.covidapps.view.adapter.CovidListAdapter;
import com.example.covidapps.view.adapter.HintAdapter;
import com.example.covidapps.viewmodel.CovidViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private CovidViewModel viewModel;
    private CovidListAdapter adapter;
    private LinearLayout progressBar;
    private TextView tvNoData;
    private RecyclerView recyclerView;
    private View parentView;
    private EditText etSearch;
    private SwipeRefreshLayout swLayout;
    private Spinner spinnerProvinsi;
    private Spinner spinner;
    private HintAdapter hintAdapter;

    private List<FeaturesItem> dataList = new ArrayList<>();
    String[] array_city ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        vmSetAndGet();
        swipeRefresh();
        searchFunction();
    }

    private void searchFunction() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });
    }

    private void init() {
        viewModel  = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(CovidViewModel.class);
        adapter = new CovidListAdapter(this);

        progressBar = findViewById(R.id.progressBar);
        swLayout = findViewById(R.id.swlayout);
        parentView = findViewById(android.R.id.content);
        tvNoData = findViewById(R.id.tv_no_data);
        recyclerView = findViewById(R.id.recycler_view);

        etSearch = findViewById(R.id.edt_search);
        CardView btnSearch = findViewById(R.id.btn_search);

         spinner = (Spinner) findViewById(R.id.spinner);
        spinnerProvinsi = (Spinner) findViewById(R.id.spinner_provinsi);

        array_city = new String[] {"Nama Provinsi", "Kasus Terbanyak","Urutkan Berdasarkan"};

        hintAdapter=new HintAdapter(this,android.R.layout.simple_list_item_1,array_city);
        spinner.setAdapter(hintAdapter);
        spinner.setSelection(hintAdapter.getCount());
        spinner.setOnItemSelectedListener(this);

        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                if(selectedName.equals("Pilih Provinsi")){
                    String TAG = "LOG";
                    Log.i(TAG, "onItemSelected: "+selectedName);
                }else{
                    List<FeaturesItem> dataSearch = new ArrayList<>();

                    for (FeaturesItem s : dataList) {
                        if(s.getAttributes().getProvinsi().contains(selectedName)){
                            dataSearch.add(s);
                        }
                    }
                    adapter.searchData(dataSearch);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnSearch.setOnClickListener(v -> filter(etSearch.getText().toString()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getItemAtPosition(position).equals("Nama Provinsi")){
            spinnerProvinsi.setSelection(hintAdapter.getCount());
            Collections.sort(dataList, (lhs, rhs) -> lhs.getAttributes().getProvinsi().compareTo(rhs.getAttributes().getProvinsi()));
            adapter.searchData(dataList);
        }else {
            spinnerProvinsi.setSelection(hintAdapter.getCount());
            Collections.sort(dataList, (lhs, rhs) -> {
                return rhs.getAttributes().getKasusPosi()- lhs.getAttributes().getKasusPosi(); // Ascending
            });
            adapter.searchData(dataList);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void vmSetAndGet() {
        adapter.clearData();
        dataList.clear();

        showProgress();
        viewModel.setCovidData();
        viewModel.getAllCovidData().observe(this, getData);
    }

    private Observer<CovidResponse> getData = new Observer<CovidResponse>() {
        @Override
        public void onChanged(CovidResponse CovidResponse) {

            if (CovidResponse != null) {
                if (CovidResponse.getUniqueIdField().isIsSystemMaintained()) {
                    if (!CovidResponse.getFeatures().isEmpty()) {
                        showRecycler(true);
                        adapter.setData(CovidResponse.getFeatures());
                        dataList = CovidResponse.getFeatures();

                        List<String> listSpinner = new ArrayList<>();
                        for (int i = 0; i < dataList.size(); i++){
                            listSpinner.add(dataList.get(i).getAttributes().getProvinsi());
                        }
                        listSpinner.add("Pilih Provinsi");
                        hintAdapter=new HintAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,listSpinner);
                        spinnerProvinsi.setAdapter(hintAdapter);
                        spinnerProvinsi.setSelection(hintAdapter.getCount());
                    } else {
                        showRecycler(false);
                        tvNoData.setText(getString(R.string.noData));
                    }


                } else {
                    showRecycler(false);
                    tvNoData.setText(getString(R.string.gagalKesalahan));
                    showSnack(getString(R.string.gagalKesalahan));
                }
            } else {
                showRecycler(false);
                tvNoData.setText(getString(R.string.noInternet));
                showSnack(getString(R.string.gagalKoneksi));
            }
        }
    };







    private void showRecycler(Boolean show) {
        progressBar.setVisibility(View.GONE);
        if (show) {
            tvNoData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            tvNoData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }
    private void filter(String text) {
        List<FeaturesItem> dataSearch = new ArrayList<>();

        for (FeaturesItem s : dataList) {
            if(s.getAttributes().getProvinsi().toLowerCase().contains(text)){
                dataSearch.add(s);
            }
        }
        adapter.searchData(dataSearch);
    }


    private void swipeRefresh() {
        swLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            swLayout.setRefreshing(false);
            vmSetAndGet();
            array_city = new String[] {"Nama Provinsi", "Kasus Terbanyak","Urutkan Berdasarkan"};
            hintAdapter=new HintAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,array_city);
            spinner.setAdapter(hintAdapter);
            spinner.setSelection(hintAdapter.getCount());
            spinnerProvinsi.setSelection(hintAdapter.getCount());
        }, 1000));
    }
    private void showSnack(String data) {
        Snackbar mSnackbar = Snackbar.make(parentView, data, Snackbar.LENGTH_LONG);
        View view = mSnackbar.getView();
        TextView sbTextView = view.findViewById(com.google.android.material.R.id.snackbar_text);
        sbTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        sbTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        mSnackbar.show();
    }
    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        tvNoData.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }
}