package ugs.mobilehci.crashprevention.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import butterknife.Unbinder;
import ugs.mobilehci.crashprevention.MvpApplication;
import ugs.mobilehci.crashprevention.R;
import ugs.mobilehci.crashprevention.di.component.ActivityComponent;
import ugs.mobilehci.crashprevention.di.component.DaggerActivityComponent;
import ugs.mobilehci.crashprevention.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private ActivityComponent mActivityComponent;

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstance){
        super.onCreate(savedInstance);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MvpApplication) getApplication()).getComponent())
                .build();
    }

    @Override
    public void onError(String message) {
        if (message != null) {
//            showSnackBar(message);
        } else {
//            showSnackBar(getString(R.string.some_error));
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }
    protected abstract void setUp();
}
