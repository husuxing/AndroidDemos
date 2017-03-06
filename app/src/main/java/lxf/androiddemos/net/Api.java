package lxf.androiddemos.net;


import io.reactivex.Observable;
import lxf.androiddemos.model.RequestModel;
import lxf.androiddemos.model.ResponseModel;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 网络接口
 * Created by lxf on 2016/10/19.
 */
interface Api {
    @POST("getdataserver")
    Observable<ResponseModel> get(@Body RequestModel requestModel);

    @POST("postdataserver")
    Observable<ResponseModel> post(@Body RequestModel requestModel);

    //("http://preview.quanjing.com/pm0054/pm0054-2529gh.jpg")
    @Streaming
    @GET
    Observable<ResponseBody> down(@Url String url);

}
