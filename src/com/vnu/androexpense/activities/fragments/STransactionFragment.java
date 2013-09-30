package com.vnu.androexpense.activities.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;
import com.vnu.androexpense.R;
import com.vnu.androexpense.adapters.SharedTransactionAdapter;
import com.vnu.androexpense.models.SharedTransaction;

public class STransactionFragment extends SherlockFragment {

	ListView lvTrans;
	ArrayList<SharedTransaction> trans;
	SharedTransactionAdapter transAdap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialConfig();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater
				.inflate(R.layout.frag_stransactions, container, false);
		lvTrans = (ListView) v.findViewById(R.id.lvSTransactions);
		trans = new ArrayList<SharedTransaction>();
		transAdap = new SharedTransactionAdapter(getActivity(), trans);
		lvTrans.setAdapter(new SlideExpandableListAdapter(transAdap,
				R.id.llTrans, R.id.sExpandable));
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public void initialConfig() {
		// transAdap = new TransactionAdapter(getActivity(), trans);
		// lvTrans = (ListView) getActivity().findViewById(R.id.lvTransactions);
		// lvTrans.setAdapter(transAdap);

	}

	public ListView getListView() {
		return lvTrans;
	}

	public SharedTransactionAdapter getAdapter() {
		return transAdap;
	}

}
