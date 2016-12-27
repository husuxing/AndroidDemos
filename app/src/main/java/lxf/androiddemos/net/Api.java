package lxf.androiddemos.net;


import io.reactivex.Observable;
import lxf.androiddemos.model.RequestModel;
import lxf.androiddemos.model.ResponseModel;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 网络接口
 * Created by lxf on 2016/10/19.
 */
interface Api {
    @POST("getdataserver")
    Observable<ResponseModel> get(@Body RequestModel requestModel);

    @POST("postdataserver")
    Observable<ResponseModel> post(@Body RequestModel requestModel);

}
