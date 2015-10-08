package com.nibbledebt.nibble.common;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.nibbledebt.nibble.R;

import java.util.List;

/**
 * @author a.salachyonok
 */
public class WalletSpinAdapter extends ArrayAdapter<String> {
    private final LayoutInflater layoutInflater;
    private final int rowLayoutId;
    private final Context context;

    public WalletSpinAdapter(Context context, List<String> objects) {
        super(context, R.layout.spinner_account_item, objects);
        this.rowLayoutId = R.layout.spinner_account_item;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public View getDropDownView(final int position, final View convertView, final ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final View view = getLayoutInflater().inflate(R.layout.spinner_account_view, null);
//        final AccountInfo store = getItem(position);
        final TextView txtName = (TextView) view.findViewWithTag("spinner_acct_name");

//        if (store == null) {
//            txtName.setTextColor(Color.BLACK);
//            if (getCount() == 1)
//                txtName.setText(context.getString(R.string.spinner_no_account));
//            else
//                txtName.setText(context.getString(R.string.spinner_select_account));
//
//        } else {
//            txtName.setTextColor(Color.BLACK);
//            BigDecimal fundsDols = new BigDecimal(store.getBalance());
//
//            fundsDols = fundsDols.divide(new BigDecimal(100L));
//            if("CC".equalsIgnoreCase(store.getContainerType())){
//                txtName.setText("Credit Card - "+store.getContainerSubType()+ " - .." + store.getAccountNumber());
//            }else if("SVA".equalsIgnoreCase(store.getContainerType())){
//                txtName.setText("Prepaid Account"+ " - .." + store.getAccountNumber());
//            }else{
//                txtName.setText("Account"+ " - .." + store.getAccountNumber());
//            }
//        }

        view.setTag("-1");
        return view;
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public int getPosition(final String item) {
        if (item == null)
            return 0;
        else
            return super.getPosition(item) + 1;
    }

    @Override
    public String getItem(final int position) {
        if (position == 0)
            return null;
        else
            return super.getItem(position - 1);
    }

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }

    public View getCustomView(final int position, final View convertView, final ViewGroup parent) {
        final View view;
        final String store = getItem(position);

        if (convertView == null ||
                !convertView.getTag().toString().equalsIgnoreCase(Integer.toString(getItemViewType(position)))) {
            if (position == 0) {
                view = new TextView(context);
//                ((TextView)view).setTextColor(context.getResources().getColor(R.color.text_black));
            }
            else
                view = getLayoutInflater().inflate(rowLayoutId, null);

            view.setTag(Integer.toString(getItemViewType(position)));
        } else {
            view = convertView;
        }

        return initView(position, view, store);
    }

    private View initView(final int position, final View view, final String store) {
        if (position == 0) {
            ((TextView) view).setHeight(0);
            view.setVisibility(View.GONE);
        } else {
            final TextView txtName = (TextView) view.findViewWithTag("spinner_acct_name");
            final TextView txtAddress = (TextView) view.findViewWithTag("spinner_acct_num");
//            if("CC".equalsIgnoreCase(store.getContainerType())){
//                txtName.setText("Credit Card - "+store.getContainerSubType());
//            }else if("SVA".equalsIgnoreCase(store.getContainerType())){
//                txtName.setText("Prepaid Account");
//            }else{
//                txtName.setText("Account");
//            }
//            txtAddress.setText("ending in .."+store.getAccountNumber());
//
//            if (!"SVA".equals(store.getContainerType())) {
//                txtName.setTextColor(Color.BLACK);
//                txtAddress.setTextColor(Color.BLACK);
//            } else {
//                txtName.setTextColor(Color.GRAY);
//                txtAddress.setTextColor(Color.GRAY);
//            }
        }

        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(final int position) {
        if (position == 0)
            return 1;
        else
            return rowLayoutId;
    }

    private LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    @Override
    public boolean isEnabled(int position) {
        final String store = getItem(position);
        return !(store == null || "SVA".equals(store));
    }
}
