package my.mymvvm.api;






import io.reactivex.Observable;

import my.mymvvm.pojo.EmployeeResponse;
import retrofit2.http.GET;

public interface ApiService {
    @GET("testTask.json")
    Observable<EmployeeResponse> getEmployees(); // resultat zaprosa   Observable io.reactivex
}
