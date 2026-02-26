package com.example.javatest.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javatest.R;
import com.example.javatest.dao.CategoryDAO;
import com.example.javatest.dao.ProductDAO;
import com.example.javatest.model.Category;
import com.example.javatest.model.Product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {

    EditText edtName, edtPrice;
    Spinner spCategory;
    ImageView imgProduct;
    Button btnCancel, btnConfirm;

    ProductDAO dao;
    ArrayList<Category> cateList;

    int idEdit = -1;
    String imagePath = "";

    // 👉 pick image
    ActivityResultLauncher<Intent> pickImg =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if(result.getResultCode()==RESULT_OK && result.getData()!=null){

                            Uri uri = result.getData().getData();

                            try{
                                InputStream is = getContentResolver().openInputStream(uri);

                                String fileName = "img_"+System.currentTimeMillis()+".jpg";
                                File file = new File(getFilesDir(), fileName);

                                FileOutputStream fos = new FileOutputStream(file);

                                byte[] buf = new byte[1024];
                                int len;
                                while((len=is.read(buf))>0){
                                    fos.write(buf,0,len);
                                }

                                fos.close();
                                is.close();

                                imagePath = file.getAbsolutePath();
                                imgProduct.setImageURI(Uri.fromFile(file));

                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_product);

        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        spCategory = findViewById(R.id.spCategory);
        imgProduct = findViewById(R.id.imgProduct);
        btnCancel = findViewById(R.id.btnCancel);
        btnConfirm = findViewById(R.id.btnConfirm);

        dao = new ProductDAO(this);

        // ===== load category spinner =====
        CategoryDAO cateDAO = new CategoryDAO(this);
        cateList = new ArrayList<>(cateDAO.getAll());

        ArrayList<String> names = new ArrayList<>();
        for(Category c:cateList) names.add(c.getName());

        spCategory.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                names
        ));

        // ===== nhận id edit =====
        idEdit = getIntent().getIntExtra("id",-1);
        if(idEdit!=-1) loadData(idEdit);

        // ===== pick image =====
        imgProduct.setOnClickListener(v->{
            Intent i=new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            pickImg.launch(i);
        });

        btnCancel.setOnClickListener(v->finish());
        btnConfirm.setOnClickListener(v->save());
    }

    // =============================
    void loadData(int id){

        for(Product p:dao.getAll()){
            if(p.getIdFood()==id){

                edtName.setText(p.getNameFood());
                edtPrice.setText(String.valueOf(p.getPrice()));

                for(int i=0;i<cateList.size();i++){
                    if(cateList.get(i).getId()==p.getIdCate()){
                        spCategory.setSelection(i);
                        break;
                    }
                }

                if(p.getImage()!=null){
                    imagePath=p.getImage();
                    com.example.javatest.util.ImageLoader
                            .load(imgProduct,imagePath);
                }
                break;
            }
        }
    }

    // =============================
    void save(){

        String name=edtName.getText().toString();
        String priceStr=edtPrice.getText().toString();

        if(name.isEmpty()||priceStr.isEmpty()){
            Toast.makeText(this,"Nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            return;
        }

        double price=Double.parseDouble(priceStr);
        int cateId=cateList.get(spCategory.getSelectedItemPosition()).getId();

        Product p=new Product();
        p.setNameFood(name);
        p.setPrice(price);
        p.setIdCate(cateId);
        p.setImage(imagePath);

        if(idEdit!=-1){
            p.setIdFood(idEdit);
            dao.update(p);
            Toast.makeText(this,"Đã cập nhật",Toast.LENGTH_SHORT).show();
        }else{
            dao.insert(p);
            Toast.makeText(this,"Đã thêm",Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}