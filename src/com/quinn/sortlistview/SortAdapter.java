package com.quinn.sortlistview;

import java.util.List;

import com.quinn.myutilapp.R;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class SortAdapter extends BaseAdapter implements SectionIndexer{

	private List<SortModel> list = null;
	private Context mContext;
	
	public SortAdapter(Context mContext, List<SortModel> list) {
		this.mContext = mContext;
		this.list = list;
	}
	
	public void updateListView(List<SortModel> list) {
		this.list = list;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		final SortModel mContent = list.get(position);
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item, null);
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			viewHolder.letter = (TextView) convertView.findViewById(R.id.catalog);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		int section = getSectionForPosition(position);
		if(position == getPositionForSection(section)){
			viewHolder.letter.setVisibility(View.VISIBLE);
			viewHolder.letter.setText(mContent.getSortLetters());
		}else{
			viewHolder.letter.setVisibility(View.GONE);
		}
		viewHolder.title.setText(this.list.get(position).getName());
		return convertView;
	}
	
	final static class ViewHolder {
		TextView letter;
		TextView title;
	}

	@Override
	public int getPositionForSection(int sectionIndex) {
		// TODO Auto-generated method stub
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == sectionIndex) {
				return i;
			}
		}
		return -1;

	}

	@Override
	public int getSectionForPosition(int position) {
		return this.list.get(position).getSortLetters().charAt(0);
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

}
