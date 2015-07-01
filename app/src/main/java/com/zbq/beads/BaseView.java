package com.zbq.beads;

import android.app.Activity;
import android.view.View;

public abstract class BaseView {

	protected Activity mContext;
	protected View view;

	public BaseView(Activity context, int layoutId) {
		mContext = context;
		view = mContext.getLayoutInflater().inflate(layoutId, null);
		view.setTag(this);
	}

	/**
	 * @return 返回真实的view
	 */
	public View getView() {
		return view;
	}

	/**
	 * 进入view时触发
	 */
	public abstract void OnViewShow();

	/**
	 * 退出view时触发
	 */
	public abstract void OnViewHide();
}
