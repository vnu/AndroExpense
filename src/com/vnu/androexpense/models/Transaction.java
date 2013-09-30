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

@Table(name = "Transactions")
public class Transaction extends Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1638950919924862676L;

	@Column(name = "type")
	private String type;
	@Column(name = "vendor")
	private String vendor;
	@Column(name = "account")
	private String account;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "trans_id")
	private int trans_id;
	@Column(name = "username")
	private String username;
	@Column(name = "notes")
	private String notes;
	@Column(name = "date")
	private Date date;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public int getTrans_id() {
		return trans_id;
	}

	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDate() {
		return date;
	}

	public String getStringDate() {
		DateFormat df = new SimpleDateFormat("MMM dd", Locale.US);
		String tvDate = df.format(this.date);
		return tvDate;
	}

	public static Transaction fromJson(JSONObject jsonObject) {
		Transaction tran = new Transaction();
		tran.initialize_transaction(jsonObject);
		return tran;
	}

	private void initialize_transaction(JSONObject jo) {
		try {
			this.setType(jo.getString("type"));
			this.setVendor(jo.getString("vendor"));
			this.setAccount(jo.getString("account"));
			this.setAmount(jo.getDouble("amount"));
			this.setTrans_id(jo.getInt("id"));
			this.setNotes(jo.getString("notes"));
			this.setUsername(jo.getString("user"));
			// this.setDate(new Date());
			this.setDate(getJsonDate(jo.getString("trans_date")));

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<Transaction> fromJson(JSONArray jsonArray) {
		ArrayList<Transaction> trans = new ArrayList<Transaction>(
				jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject transJson = null;
			try {
				transJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			Transaction tran = Transaction.fromJson(transJson);
			if (tran != null) {
				trans.add(tran);
			}
		}

		return trans;
	}

	public Date getJsonDate(String jsonDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
		// 2013-09-29T00:00:00.000Z
		Date date = null;
		try {
			date = sdf.parse(jsonDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
