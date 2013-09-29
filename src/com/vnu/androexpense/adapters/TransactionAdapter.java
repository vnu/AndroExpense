package com.vnu.androexpense.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vnu.androexpense.R;
import com.vnu.androexpense.models.Transaction;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

	public TransactionAdapter(Context context, List<Transaction> tran) {
		super(context, 0, tran);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.transaction, null);
		}

		// Get the specific Transaction at that position
		Transaction t = getItem(position);

		// Get all the views
		TextView tvTransDate = (TextView) view.findViewById(R.id.tvTransDate);
		TextView tvTransVendor = (TextView) view
				.findViewById(R.id.tvTransVendor);
		TextView tvTransType = (TextView) view.findViewById(R.id.tvTransType);
		TextView tvTransAmt = (TextView) view.findViewById(R.id.tvTransAmt);
		TextView tvTransAcnt = (TextView) view.findViewById(R.id.tvTransAcnt);

		// Now set the values
		tvTransDate.setText(t.getStringDate());

		String type = t.getType();
		int color;
		if (type.equalsIgnoreCase("Expense")) {
			color = Color.parseColor("#CD3035");
		}else if(type.equalsIgnoreCase("Income")){
			color = Color.parseColor("#215E21");
		}else{
			color = Color.parseColor("#E56717");
		}
		tvTransVendor.setText(t.getVendor());
		tvTransType.setText(t.getType());
		tvTransType.setTextColor(color);
		tvTransAmt.setText("$ "+t.getAmount().toString());
		tvTransAmt.setTextColor(color);
		tvTransAcnt.setText(t.getAccount());

		return view;
	}
}
