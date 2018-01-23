package com.tech20.mobiledelivery.models;

import com.tech20.mobiledelivery.database.DataAccess;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dao.FaqDao;
import com.tech20.mobiledelivery.database.dataentities.DbModelFaq;
import com.tech20.mobiledelivery.executors.AppExecutor;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.RestClient;
import com.tech20.mobiledelivery.retrofitclient.faqclient.FAQClient;
import com.tech20.mobiledelivery.retrofitclient.faqclient.FAQResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


public class ModelFAQ {

    public interface IFAQCallback{
        void onFAQResponseSucess(List<DbModelFaq> listFaq);
        void onFAQResponseFail(ErrorMessage errorMessage);
    }

    private IFAQCallback iFaqCallback = null;
    private DatabaseHouse dataHouse = null;
    private PreferenceUtils preferenceUtils = null;

    private Converter<ResponseBody, ErrorMessage> errorConverter = null;

    public ModelFAQ(IFAQCallback iFaqCallback,DatabaseHouse datHouse,PreferenceUtils preferenceUtils){
        this.iFaqCallback = iFaqCallback;
        this.dataHouse = datHouse;
        this.preferenceUtils = preferenceUtils;
    }
    public void getFAQ(){

        errorConverter = RestClient.getErrorConverter();
        FAQClient faqClient = RestClient.createServiceWithHeaders(FAQClient.class,preferenceUtils);

        Call<List<FAQResponse>> call =faqClient.getFAQs(preferenceUtils.getString(Constants.PreferenceConstants.DRIVER_ID));
        call.enqueue(callBack);
    }

    private Callback<List<FAQResponse>> callBack = new Callback<List<FAQResponse>>() {
        @Override
        public void onResponse(Call<List<FAQResponse>> call, Response<List<FAQResponse>> response) {

            if(response.isSuccessful()){
                saveFaqData(response.body(),dataHouse);
            }else{

                ErrorMessage errorMessage = null;
                try {
                    errorMessage = errorConverter.convert(response.errorBody());
                } catch (IOException e) {
                    e.printStackTrace();

                    errorMessage = ErrorMessage.getBlankError();
                }finally {
                    iFaqCallback.onFAQResponseFail(errorMessage);
                }
            }
        }

        @Override
        public void onFailure(Call<List<FAQResponse>> call, Throwable t) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(t.getMessage());
            iFaqCallback.onFAQResponseFail(errorMessage);
        }
    };

    private void saveFaqData(List<FAQResponse> list,DatabaseHouse datHouse){

        DataAccess.execute(()->{

            List<DbModelFaq> listDabFaq = new ArrayList<>();
            for(FAQResponse faqResponse:list){
                DbModelFaq dbModelFaq = new DbModelFaq();
                dbModelFaq.setFaqId(faqResponse.getFaqId());
                dbModelFaq.setQuestion(faqResponse.getQuestion());
                dbModelFaq.setAnswer(faqResponse.getAnswer());
                dbModelFaq.setDateCreated(faqResponse.getDateCreated());

                listDabFaq.add(dbModelFaq);
            }

            //Store FAQ to database
            FaqDao faqDao = datHouse.getFaqDao();
            faqDao.insert(listDabFaq);

            List<DbModelFaq> listFaq = datHouse.getFaqDao().getFaqs();
            AppExecutor.getINSTANCE().getMainThread().execute(()->{
                iFaqCallback.onFAQResponseSucess(listFaq);
            });
        });
    }
}
