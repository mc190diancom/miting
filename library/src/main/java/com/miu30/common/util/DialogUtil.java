package com.miu30.common.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.miu30.common.util.dateUtil.builder.TimePickerBuilder;
import com.miu30.common.util.dateUtil.listener.OnTimeSelectListener;
import com.miu30.common.util.dateUtil.view.TimePickerView;
import com.miu360.library.R;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：wang.
 * 邮箱：forwlwork@gmail.com
 */
public class DialogUtil {

    public static void TimePicker(final Activity activity, final String title, final Calendar calendar, final dateCallBack mCallBack
            , final boolean hour
            , final boolean minute
            , final boolean seconds) {
        View view = activity.getCurrentFocus();
        if (view != null) {//隐藏软键盘
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        if (Build.VERSION.SDK_INT < 21) {
            final MyProgressDialog pd = Windows.waiting(activity);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pd.dismiss();
                }
            }, 500);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showTimeView(activity, title, calendar, mCallBack, hour, minute, seconds);
                }
            }, 100);
        } else {
            showTimeView(activity, title, calendar, mCallBack, hour, minute, seconds);
        }

    }

    private static void showTimeView(Activity activity, String title, Calendar calendar, final dateCallBack mCallBack
            , final boolean hour, final boolean minute, final boolean seconds) {
        if (activity == null) {
            return;
        }
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        Calendar startc = Calendar.getInstance();
        Calendar endc = Calendar.getInstance();
        startc.set(Calendar.YEAR, 2010);
        endc.add(Calendar.YEAR, 2);
        if (calendar.getTime().getTime() < startc.getTime().getTime()) {
            startc = calendar;
            startc.add(Calendar.YEAR, -2);
        } else if (calendar.getTime().getTime() > endc.getTime().getTime()) {
            endc = calendar;
            endc.add(Calendar.YEAR, 2);
        }

        TimePickerView pvTime = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mCallBack.returnDate(date);
            }
        })
                .setTitleText(title)
                .setDate(calendar)
                .setRangDate(startc, endc)
                .setLineSpacingMultiplier(3.5f)
                .setTitleBgColor(activity.getResources().getColor(R.color.white))
                .setCancelColor(activity.getResources().getColor(R.color.color999))
                .setSubmitColor(activity.getResources().getColor(R.color.light_blue))
                .setDividerColor(activity.getResources().getColor(R.color.lucency))
                .setType(new boolean[]{true, true, true, hour, minute, seconds})
                .build();
        pvTime.show();
    }


    /**
     * 重新创建案件的原因
     */
    public static void showRemoveDoubtCarDialog(Context context, final OnRemoveReasonListener listener) {
        final Dialog dialog = Windows.createBottomDialog(context,R.layout.dialog_recreate_reason);
        dialog.setCanceledOnTouchOutside(false);
        dialog.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final Button tvTitle = dialog.findViewById(R.id.tv_title);
        final Button btnConfirmRemove = dialog.findViewById(R.id.btn_confirm_remove);
        final EditText etContent = dialog.findViewById(R.id.et_content);
        btnConfirmRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = etContent.getText().toString().trim();
                if(InputFilterUtil.valid(reason)){
                    ToastUtils.showShort("输入内容含有特殊符号");
                }else{
                    if (!TextUtils.isEmpty(reason) && listener != null) {
                        listener.onRemoveReason(reason);
                        dialog.dismiss();
                    } else {
                        ToastUtils.showShort("请输入重新创建案件原因");
                    }
                }
            }
        });
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean isEnabled = btnConfirmRemove.isEnabled();
                if (TextUtils.isEmpty(s)) {
                    if (isEnabled) {
                        btnConfirmRemove.setEnabled(false);
                    }
                } else {
                    if (!isEnabled) {
                        btnConfirmRemove.setEnabled(true);
                    }
                }
            }
        });

        dialog.show();
    }


    public interface dateCallBack {
        void returnDate(Date date);
    }

    public interface OnRemoveReasonListener {
        void onRemoveReason(String reason);
    }
}
