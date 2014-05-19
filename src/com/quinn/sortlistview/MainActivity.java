package com.quinn.sortlistview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.quinn.myutilapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.quinn.sortlistview.SideBar.OnTouchingLetterChangedListener;

public class MainActivity extends Activity{

	private ListView sortListView;
	private SideBar sideBar;
	
	private TextView dialog;
	private SortAdapter adapter;
	private EditText search;
	
	private CharacterParser characterParser;
	private List<SortModel> sourceDataList;
	
	private PinyinComparator pinyinComparator; 
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sortlist);
		initViews();
	}
	
	private void initViews(){
		characterParser = CharacterParser.getInstance();
		pinyinComparator = new PinyinComparator();
		
		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setmTextDialog(dialog);
		
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			
			@Override
			public void onTouchingLetterChanged(String s) {
				// TODO Auto-generated method stub
				int position = adapter.getPositionForSection(s.charAt(0));
				if(position != -1){
					sortListView.setSelection(position);
				}
			}
		});
		
		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), ((SortModel)adapter.getItem(arg2)).getName(), Toast.LENGTH_SHORT).show();
			}
		});
		
		sourceDataList = filledData(getResources().getStringArray(R.array.date));
		Log.d("quinn", sourceDataList.toString());
		Collections.sort(sourceDataList, pinyinComparator);
		adapter = new SortAdapter(getApplicationContext(), sourceDataList);
		sortListView.setAdapter(adapter);
		
		search = (EditText) findViewById(R.id.filter_edit);
		search.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				filterData(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private List<SortModel> filledData(String[] data){
		List<SortModel> mSortList = new ArrayList<SortModel>();
		for(int i = 0; i < data.length; i ++){
			SortModel sortModel = new SortModel();
			sortModel.setName(data[i]);
			String pinyin = characterParser.getSelling(data[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();
			if(sortString.matches("[A-Z]")){
				sortModel.setSortLetters(sortString.toUpperCase());
			}else{
				sortModel.setSortLetters("#");
			}
			mSortList.add(sortModel);
		}
		return mSortList;
	}
	
	private void filterData(String filterStr){
		List<SortModel> filterDataList = new ArrayList<SortModel>();
		if(null == filterStr && "".equals(filterStr)){
			filterDataList = sourceDataList;
		}else{
			filterDataList.clear();
			for(SortModel sortModel : sourceDataList){
				String name = sortModel.getName();
				if (name.toUpperCase().indexOf(filterStr.toUpperCase()) != -1
						|| characterParser.getSelling(name).toString()
								.toUpperCase()
								.startsWith(filterStr.toUpperCase())) {
					filterDataList.add(sortModel);
				}
			}
		}
		Collections.sort(filterDataList, pinyinComparator);
		adapter.updateListView(filterDataList);
	}
}
