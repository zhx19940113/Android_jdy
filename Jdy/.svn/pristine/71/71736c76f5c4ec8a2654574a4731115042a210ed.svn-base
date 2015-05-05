package com.app.jdy.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * 
 * description :
 *
 * @version 1.0
 * @author zhonghuixiong
 * @createtime : 2015-1-24 下午11:59:54
 * 
 * 修改历史:
 * 修改人                                          修改时间                                                  修改内容
 * --------------- ------------------- -----------------------------------
 * zhonghuixiong        2015-1-24 下午11:59:54
 *
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
	private List<Fragment> list;

	public MyFragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	public MyFragmentAdapter(FragmentManager fm, List<Fragment> list) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.list = list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
	 */
	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.isEmpty() ? 0 : list.size();
	}

}
