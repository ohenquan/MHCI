package ugs.mobilehci.crashprevention.ui.base;

import android.support.annotation.StringRes;

public interface MvpView {

    void onError(@StringRes int resId);

    void onError(String message);


    void hideKeyboard();
}
