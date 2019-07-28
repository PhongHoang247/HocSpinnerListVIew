package com.phong.hocspinnerlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.phong.model.DanhMuc;
import com.phong.model.SanPham;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerDanhMuc;
    ArrayAdapter <DanhMuc> danhMucArrayAdapter;

    ListView lvSanPham;
    ArrayAdapter <SanPham> sanPhamArrayAdapter;

    EditText edtMa, edtTen;
    Button btnNhap;

    DanhMuc selectedDanhMuc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLyNhapSanPham();
            }
        });
        //Cần lưu vết xem Danh mục nào đang đc lựa chọn trên giao diện
        spinnerDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Đối số thứ 3 là vị trí:
                selectedDanhMuc = danhMucArrayAdapter.getItem(i);
                //Lấy đc DanhMuc ra thì Show lại SanPham của DanhMuc đó
                sanPhamArrayAdapter.clear();//Xóa dữ liệu cũ trong adapterSanPham di
                sanPhamArrayAdapter.addAll(selectedDanhMuc.getSanPhams());//Tự động vẽ lại
                /*
                1 ListView đc sử dụng cho nhiều DanhMuc để hiển thị SanPham theo DanhMuc đó
                Do đó phải xóa cái cũ để lấy lại dữ liệu mới của danh mục mới
                Mỗi danh mục có nhiều sản phẩm
                 */
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void XuLyNhapSanPham() {
        SanPham sp = new SanPham(edtMa.getText().toString(), edtTen.getText().toString());
        //sanPhamArrayAdapter.add(sp);
        selectedDanhMuc.getSanPhams().add(sp);
        sanPhamArrayAdapter.clear();//Xóa dữ liệu cũ đi
        sanPhamArrayAdapter.addAll(selectedDanhMuc.getSanPhams());//Tự động vẽ lại
    }

    private void addControls() {
        spinnerDanhMuc = (Spinner) findViewById(R.id.spinnerDanhMuc);
        danhMucArrayAdapter = new ArrayAdapter<DanhMuc>(
                MainActivity.this,
                android.R.layout.simple_spinner_item);
        danhMucArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        //Gán Adapter cho Spinner:
        spinnerDanhMuc.setAdapter(danhMucArrayAdapter);

        lvSanPham = (ListView) findViewById(R.id.lvSanPham);
        sanPhamArrayAdapter = new ArrayAdapter<SanPham>(
                MainActivity.this,
                android.R.layout.simple_list_item_1);
        //Gán Adapter cho ListView:
        lvSanPham.setAdapter(sanPhamArrayAdapter);

        edtMa = (EditText) findViewById(R.id.edtMa);
        edtTen = (EditText) findViewById(R.id.edtTen);
        btnNhap = (Button) findViewById(R.id.btnNhap);

        danhMucArrayAdapter.add(new DanhMuc("Danh mục 1", "Nước"));
        danhMucArrayAdapter.add(new DanhMuc("Danh mục 2", "Thuốc"));
        danhMucArrayAdapter.add(new DanhMuc("Danh mục 3", "Sữa"));
        danhMucArrayAdapter.add(new DanhMuc("Danh mục 4", "Y tế"));
    }
}
