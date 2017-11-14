package com.bwie.testten.mine;

/**
 * Created by Zhang on 2017/11/13.
 */

public interface LoginConstract {

    interface ILoginView {
        void showLogin();
        void showerroe(String e);
    }

    interface ILoginModel {
        void RequestData(String url,String mobile,String password,LoginConstract.OnRequestListener onRequestListener);
    }

    interface OnRequestListener{
        void OnSuccess();
        void OnError(String e);
    }

    interface ILoginPresenter {
        void onSignUp(String url,String mobile, String password);
    }

}
