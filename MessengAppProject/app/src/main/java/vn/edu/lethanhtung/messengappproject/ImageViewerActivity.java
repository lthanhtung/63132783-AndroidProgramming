package vn.edu.lethanhtung.messengappproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ImageViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        ImageView fullImageView = findViewById(R.id.full_image_view);
        ImageButton closeButton = findViewById(R.id.close_button);

        // Lấy URL ảnh từ Intent
        String imageUrl = getIntent().getStringExtra("image_url");

        // Tải ảnh bằng Picasso
        if (imageUrl != null) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.man)
                    .error(R.drawable.man)
                    .into(fullImageView);
        }

        // Xử lý nút đóng
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}