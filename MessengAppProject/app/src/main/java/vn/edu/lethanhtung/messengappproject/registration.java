package vn.edu.lethanhtung.messengappproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.Normalizer;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class registration extends AppCompatActivity {

    TextView loginbut;
    EditText rg_username, rg_email, rg_password, rg_repassword;
    Button rg_signup;
    CircleImageView rg_profileImg;
    FirebaseAuth auth;
    Uri imageURI;
    String imageuri;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseDatabase database;
    FirebaseStorage storage;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Establishing The Account");
        progressDialog.setCancelable(false);

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();

        loginbut = findViewById(R.id.loginbut);
        rg_username = findViewById(R.id.rgusername);
        rg_email = findViewById(R.id.rgemail);
        rg_password = findViewById(R.id.rgpassword);
        rg_repassword = findViewById(R.id.rgrepassword);
        rg_profileImg = findViewById(R.id.profilerg0);
        rg_signup = findViewById(R.id.signupbutton);

        loginbut.setOnClickListener(v -> {
            startActivity(new Intent(registration.this, login.class));
            finish();
        });

        rg_signup.setOnClickListener(v -> {
            String namee = rg_username.getText().toString();
            String emaill = rg_email.getText().toString();
            String Password = rg_password.getText().toString();
            String cPassword = rg_repassword.getText().toString();
            String status = "Hey I'm Using This Application";

            if (TextUtils.isEmpty(namee) || TextUtils.isEmpty(emaill) || TextUtils.isEmpty(Password) || TextUtils.isEmpty(cPassword)) {
                Toast.makeText(registration.this, "Please Enter Valid Information", Toast.LENGTH_SHORT).show();
            } else if (!emaill.matches(emailPattern)) {
                rg_email.setError("Type a Valid Email Here");
            } else if (Password.length() < 6) {
                rg_password.setError("Password Must Be 6 Characters Or More");
            } else if (!Password.equals(cPassword)) {
                rg_password.setError("The Password Doesn't Match");
            } else {
                progressDialog.show();
                auth.createUserWithEmailAndPassword(emaill, Password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String id = task.getResult().getUser().getUid();
                        DatabaseReference reference = database.getReference().child("user").child(id);
                        DatabaseReference rootRef = database.getReference().child("user");

                        // Tạo customUserID theo User + tên đã chuẩn hóa + số nếu trùng
                        generateUniqueCustomUserID(namee, rootRef, new OnCustomIdGeneratedListener() {
                            @Override
                            public void onGenerated(String customUserID) {
                                if (customUserID == null) {
                                    progressDialog.dismiss();
                                    Toast.makeText(registration.this, "Failed to generate custom ID", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                StorageReference storageReference = storage.getReference().child("Upload").child(id);

                                if (imageURI != null) {
                                    try {
                                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageURI));
                                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                                        byte[] dataByte = baos.toByteArray();
                                        UploadTask uploadTask = storageReference.putBytes(dataByte);

                                        uploadTask.addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                                                    imageuri = uri.toString();
                                                    Users users = new Users(id, customUserID, namee, emaill, Password, imageuri, status);
                                                    reference.setValue(users).addOnCompleteListener(task2 -> {
                                                        progressDialog.dismiss();
                                                        if (task2.isSuccessful()) {
                                                            startActivity(new Intent(registration.this, MainActivity.class));
                                                            finish();
                                                        } else {
                                                            Toast.makeText(registration.this, "Error in creating the user", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                });
                                            } else {
                                                progressDialog.dismiss();
                                                Toast.makeText(registration.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } catch (Exception e) {
                                        progressDialog.dismiss();
                                        Toast.makeText(registration.this, "Error compressing image", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    imageuri = "https://firebasestorage.googleapis.com/v0/b/messenger-app-project-4de8f.firebasestorage.app/o/man.png?alt=media&token=bf2f5c85-c8c8-44e5-aff9-0807b25913bd";
                                    Users users = new Users(id, customUserID, namee, emaill, Password, imageuri, status);
                                    reference.setValue(users).addOnCompleteListener(task2 -> {
                                        progressDialog.dismiss();
                                        if (task2.isSuccessful()) {
                                            startActivity(new Intent(registration.this, MainActivity.class));
                                            finish();
                                        } else {
                                            Toast.makeText(registration.this, "Error in creating the user", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(registration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        rg_profileImg.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK && data != null) {
            imageURI = data.getData();
            String mimeType = getContentResolver().getType(imageURI);
            if (mimeType != null && (mimeType.startsWith("image/jpeg") || mimeType.startsWith("image/png"))) {
                rg_profileImg.setImageURI(imageURI);
            } else {
                Toast.makeText(registration.this, "Please select a JPEG or PNG image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Hàm chuẩn hóa tên user (bỏ dấu, bỏ khoảng trắng)
    private String normalizeUserName(String input) {
        String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        temp = temp.replaceAll("\\s+", "");
        temp = temp.replaceAll("[^a-zA-Z0-9]", "");
        return temp;
    }

    // Tạo customUserID không trùng
    private void generateUniqueCustomUserID(String baseUserName, DatabaseReference rootRef, OnCustomIdGeneratedListener listener) {
        String normalizedUserName = normalizeUserName(baseUserName);
        String baseCustomId = "User" + normalizedUserName;

        rootRef.orderByChild("customUserID").startAt(baseCustomId).endAt(baseCustomId + "\uf8ff")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int maxSuffix = -1;

                        for (DataSnapshot child : snapshot.getChildren()) {
                            Users user = child.getValue(Users.class);
                            if (user == null || user.getCustomUserID() == null) continue;

                            String existingId = user.getCustomUserID();
                            if (existingId.equals(baseCustomId)) {
                                maxSuffix = Math.max(maxSuffix, 0);
                            } else if (existingId.startsWith(baseCustomId)) {
                                String suffix = existingId.substring(baseCustomId.length());
                                if (suffix.matches("\\d+")) {
                                    int number = Integer.parseInt(suffix);
                                    if (number > maxSuffix) maxSuffix = number;
                                }
                            }
                        }

                        String uniqueCustomId = (maxSuffix == -1) ? baseCustomId : baseCustomId + (maxSuffix + 1);
                        listener.onGenerated(uniqueCustomId);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        listener.onGenerated(null);
                    }
                });
    }

    // Interface callback lấy customUserID
    public interface OnCustomIdGeneratedListener {
        void onGenerated(String customId);
    }
}
