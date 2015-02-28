package acon.android.v4;

import acon.android.v4.R.id;
import acon.android.v4.component.exceptions.ExceptionsFragment;
import acon.android.v4.component.orders.OrdersFragment;
import acon.android.v4.component.overview.OverviewFragment;
import acon.android.v4.component.qc_cal.QC_CALFragment;
import acon.android.v4.component.reagents.ReagentsFragment;
import acon.android.v4.component.results.ResultsFragment;
import acon.android.v4.component.supplies.SuppliesFragment;
import acon.android.v4.component.system.SystemFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private FragmentTabHost mTabHost;
	@SuppressWarnings("rawtypes")
	private Class mFragmentArray[] = { OverviewFragment.class,
			OrdersFragment.class, ResultsFragment.class, QC_CALFragment.class,
			ExceptionsFragment.class, ReagentsFragment.class,
			SuppliesFragment.class, SystemFragment.class };
	private String[] tags;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		initTabHost();
		// Environment.getExternalStorageDirectory().getPath();

	}

	private void initTabHost() {
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		tags = new String[] { getResources().getString(R.string.overview_tag),
				getResources().getString(R.string.orders_tag),
				getResources().getString(R.string.results_tag),
				getResources().getString(R.string.qccal_tag),
				getResources().getString(R.string.exception_tag),
				getResources().getString(R.string.reagents_tag),
				getResources().getString(R.string.supplies_tag),
				getResources().getString(R.string.system_tag) };
		mTabHost.addTab(mTabHost.newTabSpec(tags[0]).setIndicator("Overview"),
				mFragmentArray[0], null);

		mTabHost.addTab(mTabHost.newTabSpec(tags[1]).setIndicator("Orders"),
				mFragmentArray[1], null);

		mTabHost.addTab(mTabHost.newTabSpec(tags[2]).setIndicator("Results"),
				mFragmentArray[2], null);

		mTabHost.addTab(mTabHost.newTabSpec(tags[3]).setIndicator("QC_Cal"),
				mFragmentArray[3], null);

		mTabHost.addTab(
				mTabHost.newTabSpec(tags[4]).setIndicator("Exceptions"),
				mFragmentArray[4], null);

		mTabHost.addTab(mTabHost.newTabSpec(tags[5]).setIndicator("Reagents"),
				mFragmentArray[5], null);

		mTabHost.addTab(mTabHost.newTabSpec(tags[6]).setIndicator("Supplies"),
				mFragmentArray[6], null);
		mTabHost.addTab(
				mTabHost.newTabSpec(tags[7]).setIndicator("",
						getResources().getDrawable(R.drawable.more)),
				mFragmentArray[7], null);

		mTabHost.getTabWidget().setShowDividers(
				LinearLayout.SHOW_DIVIDER_MIDDLE);

		for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
			TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i)
					.findViewById(android.R.id.title);
			tv.setGravity(Gravity.CENTER_VERTICAL);
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tv
					.getLayoutParams();
			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0); // 取消文字底边对齐
			params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE); // 设置文字居中对齐

			ImageView iv = (ImageView) mTabHost.getTabWidget().getChildAt(i)
					.findViewById(android.R.id.icon);
			iv.setPadding(5, 5, 5, 5);
		}
	}

	private View getIndicatorView(String name) {
		View v = getLayoutInflater().inflate(R.layout.tab_layout, null);
		TextView tv = (TextView) v.findViewById(R.id.tabText);
		tv.setText(name);

		/*
		 * if (isImageShow) { ImageView iv = (ImageView)
		 * v.findViewById(R.id.tabImage); iv.setImageDrawable(drawable); }
		 */
		return v;
	}

	private View getIndicatorMoreView(Drawable drawable) {
		View v = getLayoutInflater().inflate(R.layout.tab_more_layout, null);
		ImageView iv = (ImageView) v.findViewById(R.id.tabImage);
		iv.setImageDrawable(drawable);
		return v;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mTabHost = null;
	}
}