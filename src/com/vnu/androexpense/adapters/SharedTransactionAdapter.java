package com.vnu.androexpense.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vnu.androexpense.R;
import com.vnu.androexpense.models.SharedTransaction;

public class SharedTransactionAdapter extends ArrayAdapter<SharedTransaction> {

	public SharedTransactionAdapter(Context context,
			List<SharedTransaction> tran) {
		super(context, 0, tran);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.s_transaction, null);
		}

		// Get the specific Transaction at that position
		SharedTransaction t = getItem(position);

		// Get all the views
		TextView tvTransDate = (TextView) view.findViewById(R.id.tvSTransDate);
		TextView tvTransVendor = (TextView) view
				.findViewById(R.id.tvSTransVendor);
		TextView tvTransType = (TextView) view.findViewById(R.id.tvSTransType);
		TextView tvTransAmt = (TextView) view.findViewById(R.id.tvSTransAmt);
		TextView tvTransStatus = (TextView) view
				.findViewById(R.id.tvSTransStatus);
		EditText etAcnt = (EditText) view.findViewById(R.id.etAcnt);
		Button tvSTransAction = (Button) view.findViewById(R.id.tvSTransAction);

		// Now set the values
		tvTransDate.setText(t.getStringDate());

		Boolean type = t.getExpense();
		int color;
		if (type) {
			color = Color.parseColor("#CD3035");
		} else {
			color = Color.parseColor("#215E21");
		}

		String stype = t.getPayer();
		if (stype.equalsIgnoreCase("You")) {
			stype = t.getPayee() + " Owes";
		} else {
			stype = "Pay " + t.getPayer();
		}
		tvTransVendor.setText(t.getVendor());
		tvTransType.setText(stype);
		tvTransType.setTextColor(color);
		tvTransAmt.setText("$ " + t.getAmount().toString());
		tvTransAmt.setTextColor(color);
		tvTransStatus.setText(t.getStatus());
		String action = t.getAction();
		tvSTransAction.setText(action);
		if (action.equalsIgnoreCase("Confirm")) {
			etAcnt.setVisibility(View.VISIBLE);
		}
		tvSTransAction.setTag(t.getSt_id());

		return view;
	}
}
