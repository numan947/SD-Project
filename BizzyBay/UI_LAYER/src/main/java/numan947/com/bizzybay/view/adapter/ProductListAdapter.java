package numan947.com.bizzybay.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.ProductModelMultiple;
import numan947.com.bizzybay.view.component.BrowseProductViewHolder;


public class ProductListAdapter extends RecyclerView.Adapter<BrowseProductViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<ProductModelMultiple> products;

    public ProductListAdapter(Context context, LayoutInflater layoutInflater, List<ProductModelMultiple> products) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.products = products;
    }

    @Override
    public BrowseProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BrowseProductViewHolder(context,layoutInflater.inflate(R.layout.product_in_list,parent,false));
    }

    @Override
    public void onBindViewHolder(BrowseProductViewHolder holder, int position) {

        //todo need to change depending on the data in the browse product models
        holder.bindModel(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
