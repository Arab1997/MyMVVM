package my.mymvvm.employees;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import my.mymvvm.R;
import my.mymvvm.adapters.EmployeeAdapter;
import my.mymvvm.pojo.Employee;


public class EmployeeListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;
    private EmployeeViewModel viewModel;
  //  private EmployeeListPresenter presenter; // MVP
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //    presenter = new EmployeeListPresenter(this); // MVP
        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployees);
        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<Employee>());
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
         viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {//etot metod vozvrashaet LiveData  podpisatsiy izmeneniya  v bd
             @Override
             public void onChanged(List<Employee> employees) {
                 adapter.setEmployees(employees);
             }
         });
         viewModel.getErrors().observe(this, new Observer<Throwable>() { // mi podpisalis izmeneniya  v objecte LiveData s oshibkami
             @Override
             public void onChanged(Throwable throwable) {
                 if (throwable != null){
                 Toast.makeText(EmployeeListActivity.this, "Error", Toast.LENGTH_SHORT).show(); // yesli danniye izminilis
                 viewModel.clearErrors();  // ochishaem danniy
             }
             }
         });
         viewModel.loadData();  // zagrujaem data
    //    presenter.loadData();
    }




    // ------------------------  // MVP // -------------------------------------//
/*
    @Override
    protected void onDestroy() {
    //    presenter.disposeDisposable();    // MVP
        super.onDestroy();
    }

    @Override
    public void showData(List<Employee> employees) {
        adapter.setEmployees(employees);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }*/
}
