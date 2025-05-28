package vn.edu.lethanhtung.messengappproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class setting extends AppCompatActivity {

    ImageView setprofile;
    EditText setname, setstatus;
    Button donebut;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    String email, password, currentProfilePicUrl;
    Uri setImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        setprofile = findViewById(R.id.settingprofile);
        setname = findViewById(R.id.settingname);
        setstatus = findViewById(R.id.settingstatus);
        donebut = findViewById(R.id.donebut);

        DatabaseReference reference = database.getReference().child("user").child(auth.getUid());
        StorageReference storageReference = storage.getReference().child("upload").child(auth.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                email = snapshot.child("mail").getValue().toString();
                password = snapshot.child("password").getValue().toString();
                String name = snapshot.child("userName").getValue().toString();
                currentProfilePicUrl = snapshot.child("profilepic").getValue().toString();
                String status = snapshot.child("status").getValue().toString();
                setname.setText(name);
                setstatus.setText(status);
                Picasso.get()
                        .load(currentProfilePicUrl)
                        .placeholder(R.drawable.man)
                        .error(R.drawable.man)
                        .resize(100, 100)
                        .centerCrop()
                        .into(setprofile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(setting.this, "Lỗi khi lấy dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });

        setprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10);
            }
        });

        donebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = setname.getText().toString();
                String status = setstatus.getText().toString();

                if (setImageUri != null) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(setImageUri));
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos); // Nén ảnh
                        byte[] dataByte = baos.toByteArray();
                        storageReference.putBytes(dataByte).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()) {
                                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String finalImageUri = uri.toString();
                                            Users users = new Users(auth.getUid(), name, email, password, finalImageUri, status);
                                            reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(setting.this, "Dữ liệu đã được lưu", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(setting.this, MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        Toast.makeText(setting.this, "Có lỗi xảy ra...", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                } else {
                                    Toast.makeText(setting.this, "Tải ảnh lên thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (Exception e) {
                        Toast.makeText(setting.this, "Lỗi khi nén ảnh", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Users users = new Users(auth.getUid(), name, email, password, currentProfilePicUrl, status);
                    reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(setting.this, "Dữ liệu đã được lưu", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(setting.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(setting.this, "Có lỗi xảy ra...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK && data != null) {
            setImageUri = data.getData();
            String mimeType = getContentResolver().getType(setImageUri);
            if (mimeType != null && (mimeType.startsWith("image/jpeg") || mimeType.startsWith("image/png"))) {
                setprofile.setImageURI(setImageUri);
            } else {
                Toast.makeText(setting.this, "Please select a JPEG or PNG image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}