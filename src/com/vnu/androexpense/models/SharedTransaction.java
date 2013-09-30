package com.vnu.androexpense.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "SharedTransactions")
public class SharedTransaction extends Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2157251432181754262L;

	@Column(name = "st_id")
	private int st_id;
	@Column(name = "vendor")
	private String vendor;
	@Column(name = "trans_id")
	private int trans_id;
	@Column(name = "payee")
	private String payee;
	@Column(name = "payer")
	private String payer;
	@Column(name = "date")
	private Date date;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "notes")
	private String notes;
	@Column(name = "status")
	private String status;
	@Column(name = "expense")
	private Boolean expense;
	@Column(name = "action")
	private String action;

	public int getSt_id() {
		return st_id;
	}

	public void setSt_id(int st_id) {
		this.st_id = st_id;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public int getTrans_id() {
		return trans_id;
	}

	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Boolean getExpense() {
		return expense;
	}

	public void setExpense(Boolean expense) {
		this.expense = expense;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStringDate() {
		DateFormat df = new SimpleDateFormat("MMM dd", Locale.US);
		String tvDate = df.format(this.date);
		return tvDate;
	}

	public static SharedTransaction fromJson(JSONObject jsonObject) {
		SharedTransaction tran = new SharedTransaction();
		tran.initialize_transaction(jsonObject);
		return tran;
	}

	private void initialize_transaction(JSONObject jo) {
		try {
			this.setSt_id(jo.getInt("id"));
			this.setTrans_id(jo.getInt("transaction_id"));
			this.setAmount(jo.getDouble("amount"));
			this.setVendor(jo.getString("vendor"));
			this.setPayer(jo.getString("payer"));
			this.setPayee(jo.getString("payee"));
			this.setStatus(jo.getString("status"));
			this.setNotes(jo.getString("notes"));
			this.setAction(jo.getString("action"));
			this.setExpense(jo.getBoolean("expense"));
//			this.setDate(new Date());
			this.setDate(getJsonDate(jo.getString("trans_date")));

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<SharedTransaction> fromJson(JSONArray jsonArray) {
		ArrayList<SharedTransaction> trans = new ArrayList<SharedTransaction>(
				jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject transJson = null;
			try {
				transJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			SharedTransaction tran = SharedTransaction.fromJson(transJson);
			if (tran != null) {
				trans.add(tran);
			}
		}

		return trans;
	}

	public Date getJsonDate(String jsonDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
		Date date = null;
		try {
			date = sdf.parse(jsonDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}


}
