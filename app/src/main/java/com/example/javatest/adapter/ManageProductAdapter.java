package com.example.javatest.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.R;
import com.example.javatest.account.AddProduct;
import com.example.javatest.dao.CategoryDAO;
import com.example.javatest.dao.ProductDAO;
import com.example.javatest.model.Category;
import com.example.javatest.model.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageProductAdapter extends RecyclerView.Adapter<ManageProductAdapter.ViewHolder>{

    Context context;
    ArrayList<Product> list;
    ArrayList<Product> originalList;
    ProductDAO productDAO;
    HashMap<Integer,String> cateMap = new HashMap<>();

    public ManageProductAdapter(Context c, ArrayList<Product> list){
        this.context=c;
        this.list=list;
        this.originalList=new ArrayList<>(list);
        productDAO=new ProductDAO(c);

        CategoryDAO dao=new CategoryDAO(c);
        List<Category> cates=dao.getAll();
        for(Category ca:cates){
            cateMap.put(ca.getId(),ca.getName());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v=LayoutInflater.from(context)
                .inflate(R.layout.layout_manage_product,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder h,int pos){

        Product p=list.get(pos);

        h.txtName.setText(p.getNameFood());
        h.txtPrice.setText(p.getPrice()+" VNĐ");

        String cateName=cateMap.get(p.getIdCate());
        h.txtCategory.setText(cateName==null?"":cateName);

        // ===== LOAD IMAGE =====
        if(p.getImage()!=null && !p.getImage().isEmpty()){
            try{
                h.imgProduct.setImageURI(Uri.fromFile(new File(p.getImage())));
            }catch(Exception e){
                h.imgProduct.setImageResource(R.drawable.ic_launcher_background);
            }
        }else{
            h.imgProduct.setImageResource(R.drawable.ic_launcher_background);
        }

        // ===== EDIT =====
        h.btnEdit.setOnClickListener(v->{
            Intent i=new Intent(context,AddProduct.class);
            i.putExtra("id",p.getIdFood());
            context.startActivity(i);
        });

        // ===== DELETE =====
        h.btnDelete.setOnClickListener(v->{

            new AlertDialog.Builder(context)
                    .setTitle("Xóa sản phẩm")
                    .setMessage("Bạn chắc chắn muốn xóa?")
                    .setPositiveButton("Xóa",(d,w)->{
                        productDAO.delete(p.getIdFood());

                        int position=h.getAdapterPosition();
                        list.remove(position);
                        originalList.remove(p);

                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("Hủy",null)
                    .show();
        });
    }

    @Override
    public int getItemCount(){return list.size();}

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgProduct;
        TextView txtName,txtPrice,txtCategory;
        ImageButton btnEdit,btnDelete;

        public ViewHolder(View v){
            super(v);

            imgProduct=v.findViewById(R.id.imgProduct);
            txtName=v.findViewById(R.id.txtName);
            txtPrice=v.findViewById(R.id.txtPrice);
            txtCategory=v.findViewById(R.id.txtCategory);
            btnEdit=v.findViewById(R.id.btnEdit);
            btnDelete=v.findViewById(R.id.btnDelete);
        }
    }

    // ===== SEARCH =====
    public void filter(String key){

        list.clear();

        if(key.isEmpty()){
            list.addAll(originalList);
        }else{
            key=key.toLowerCase();
            for(Product p:originalList){
                if(p.getNameFood().toLowerCase().contains(key)){
                    list.add(p);
                }
            }
        }

        notifyDataSetChanged();
    }
}