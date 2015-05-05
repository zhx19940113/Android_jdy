package com.app.jdy.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.jdy.R;

public class SearchListViewAdapter extends BaseAdapter implements Filterable {

	private Context context;
	private List<String> listStr;
	private ListFilter filter;

	public SearchListViewAdapter(List<String> list, Context context) {
		this.listStr = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listStr.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listStr.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.searchlist_item, null);
		}
		ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
		TextView firstname = (TextView) convertView.findViewById(R.id.tv_search);
		icon.setBackgroundResource(fromNameToIcon(listStr.get(position)));
		firstname.setText(listStr.get(position));
		return convertView;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		if (filter == null) {
			filter = new ListFilter(listStr);
		}
		return filter;
	}

	private class ListFilter extends Filter {

		private List<String> original;

		public ListFilter(List<String> list) {
			this.original = list;
		}

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			// TODO Auto-generated method stub
			FilterResults results = new FilterResults();
			if (constraint == null || constraint.length() == 0) {
				results.values = original;
				results.count = original.size();
			} else {
				List<String> mList = new ArrayList<String>();
				for (String s : original) {
					if (s.toUpperCase().contains(constraint.toString().toUpperCase())) {
						mList.add(s);
					}
				}
				results.values = mList;
				results.count = mList.size();
			}
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			// TODO Auto-generated method stub
			listStr = (List<String>) results.values;
			notifyDataSetChanged();
		}

	}

	public int fromNameToIcon(String icon) {
		int resId = 0;
		if ("银行理财".equals(icon)) {
			resId = R.drawable.bank;
		} else if ("公募基金".equals(icon)) {
			resId = R.drawable.pubfunds;
		} else if ("私募基金".equals(icon)) {
			resId = R.drawable.prifunds;
		} else if ("保险理财".equals(icon)) {
			resId = R.drawable.insurance;
		} else if ("股权众筹".equals(icon)) {
			resId = R.drawable.equity;
		} else if ("信托理财".equals(icon)) {
			resId = R.drawable.trust;
		} else if ("资管理财".equals(icon)) {
			resId = R.drawable.captmanage;
		} else if ("债权众筹".equals(icon)) {
			resId = R.drawable.credit;
		}
		return resId;
	}
}
