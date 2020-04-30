package com.miu30.common;

import android.os.Bundle;

import com.miu30.common.config.Config;
import com.miu30.common.ui.entity.IdCardInfo;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

public abstract class BaseEventBusFragment extends BaseFragment {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Subscriber(tag =  Config.IDCARD)
	public void getIdCard(IdCardInfo idCardInfo){
		getIdCardInfo(idCardInfo);
	}

	public abstract void getIdCardInfo(IdCardInfo idCardInfo);
}
