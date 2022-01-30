package com.example.architecturemvvm.viewmodel;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.architecturemvvm.BR;
import com.example.architecturemvvm.model.Model;

public class AppViewModel  extends BaseObservable {

    private Model model;

    private String successMessage = "Login successful";
    private String errorMessage = "Email or Password is not valid";

    @Bindable
    private String toastMessage = null;

    public String getToastMessage(){
        return toastMessage;
    }

    public void setToastMessage(String toastMessage){
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public String getUserEmail(){
        return model.getEmail();
    }

    public void setUserEmail(String email){
        model.setEmail(email);
        notifyPropertyChanged(BR.userEmail);
    }

    @Bindable
    public String getUserPassword(){
        return model.getPassword();
    }

    public void setUserPassword(String password){
        model.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    public AppViewModel(){
        model = new Model("","");
    }

    public void onButtonClicked(){
        if(isValid()){
            setToastMessage(successMessage);
        }else
            setToastMessage(errorMessage);
    }

    private boolean isValid() {
        return !TextUtils.isEmpty(getUserEmail()) && Patterns.EMAIL_ADDRESS.matcher(getUserEmail()).matches()
                && getUserPassword().length() > 5;
    }
}
