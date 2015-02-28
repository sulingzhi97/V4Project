package acon.android.v4.component.overview;

import java.util.ArrayList;
import java.util.List;

import acon.android.v4.MainActivity;
import acon.android.v4.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class OverviewFragment extends Fragment implements OnClickListener {
	private MainActivity contextActivity;
	private PopupWindow popupWindow;
	private ArrayList<Fragment> fragmentList;
	private ViewPager mPager;
	private int currIndex;
	private TextView tvSnapshot;
	private TextView tvSimpleStates;
	private TextView tvOperationsManual;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_fragment_overview, null);
	}

	@Override
	public void onResume() {
		super.onResume();
		initData();
		initView();
		initPopwindow();
		initViewPager();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	private void initData() {
		contextActivity = (MainActivity) getActivity();
	}

	private void initView() {
		View view = getView();
		tvSnapshot = (TextView) view
				.findViewById(R.id.overview_fragment_tv_snapshot);
		tvSimpleStates = (TextView) view
				.findViewById(R.id.overview_fragment_tv_simple_status);
		tvOperationsManual = (TextView) view
				.findViewById(R.id.overview_fragment_tv_operations_manual);

		ImageView imgMore = (ImageView) view
				.findViewById(R.id.overview_fragment_img_more);
		imgMore.setOnClickListener(this);
		tvSnapshot.setOnClickListener(this);
		tvSimpleStates.setOnClickListener(this);
		tvOperationsManual.setOnClickListener(this);
	}

	private void initViewPager() {
		fragmentList = new ArrayList<Fragment>();
		mPager = (ViewPager) contextActivity
				.findViewById(R.id.overview_fragment_viewpager);
		ChildFragmentSnapshot snapshotFragment = new ChildFragmentSnapshot();
		ChildFragmentOperationManual operationManualFragment = new ChildFragmentOperationManual();
		ChildFragmentSimpleStatus simpleStatusFragment = new ChildFragmentSimpleStatus();
		fragmentList.add(snapshotFragment);
		fragmentList.add(simpleStatusFragment);
		fragmentList.add(operationManualFragment);

		FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(
				getChildFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return fragmentList.size();
			}

			@Override
			public Fragment getItem(int index) {
				// TODO Auto-generated method stub
				return fragmentList.get(index);
			}
		};
		mPager.setAdapter(pagerAdapter);
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mPager.setPageTransformer(true, new DepthPageTransformer());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.overview_fragment_tv_snapshot:
			mPager.setCurrentItem(0);
			tvSnapshot.setBackgroundDrawable(contextActivity.getResources()
					.getDrawable(R.drawable.ic_tabpager_indicator_sel));
			tvSimpleStates.setBackgroundDrawable(null);
			tvOperationsManual.setBackgroundDrawable(null);
			break;
		case R.id.overview_fragment_tv_simple_status:
			mPager.setCurrentItem(1);
			tvSnapshot.setBackgroundDrawable(null);
			tvSimpleStates.setBackgroundDrawable(contextActivity.getResources()
					.getDrawable(R.drawable.ic_tabpager_indicator_sel));
			tvOperationsManual.setBackgroundDrawable(null);
			break;
		case R.id.overview_fragment_tv_operations_manual:
			mPager.setCurrentItem(2);
			tvSnapshot.setBackgroundDrawable(null);
			tvSimpleStates.setBackgroundDrawable(null);
			tvOperationsManual.setBackgroundDrawable(contextActivity.getResources()
					.getDrawable(R.drawable.ic_tabpager_indicator_sel));
			break;
		case R.id.overview_fragment_img_more:
			getPopupWindow();
			popupWindow.showAtLocation(v, Gravity.RIGHT, 0, -250);
			break;
		default:
			break;
		}
	}

	private void initPopwindow() {
		LayoutInflater inflater = (LayoutInflater) contextActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View vPopWindow = inflater.inflate(R.layout.popwindow_layout,
				null, false);
		ListView listView = (ListView) vPopWindow
				.findViewById(R.id.popwindow_layout_list);
		List<String> items = new ArrayList<String>();
		items.add("menu1");
		items.add("menu2");
		items.add("menu3");

		PopWindowAdapter adapter = new PopWindowAdapter(contextActivity, items);
		listView.setAdapter(adapter);

		popupWindow = new PopupWindow(vPopWindow, 200,
				LayoutParams.WRAP_CONTENT, true);
		popupWindow.setAnimationStyle(R.style.AnimationFade);
		// 设置动画效果
		// 点击其他地方消失
		vPopWindow.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
				}
				return false;
			}

		});
	}

	private void getPopupWindow() {
		if (null != popupWindow) {
			popupWindow.dismiss();
			return;
		} else {
			initPopwindow();
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			currIndex = arg0;
			int i = currIndex + 1;
		}
	}
}
