package com.ming6464.minhngph25430_assignmnet.Fragment;

import static android.app.Activity.RESULT_OK;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ming6464.minhngph25430_assignmnet.DAO.NguoiDungDAO;
import com.ming6464.minhngph25430_assignmnet.MainActivity;
import com.ming6464.minhngph25430_assignmnet.RoomDb.AssignmentRoom;
import com.ming6464.minhngph25430_assignmnet.SharePreferences.AssignmentShare;
import com.ming6464.minhngph25430_assignmnet.databinding.FragmentManHinhChinhBinding;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManHinhChinhFragment extends Fragment {
    private FragmentManHinhChinhBinding binding;
    private String currentPhotoPath,taiKhoan;
    private NguoiDungDAO dao;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static ManHinhChinhFragment newInstance() {
        return new ManHinhChinhFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManHinhChinhBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = AssignmentRoom.getInstance(requireActivity()).getNguoiDungDAO();
        taiKhoan = AssignmentShare.getInstance(requireActivity()).getTK2();
        binding.manHinhChinhFragBtnTenNguoiDung.setText(AssignmentShare.getInstance(requireActivity()).getTenNguoiDung());
        upAvatar();
        addAction();
    }

    private void upAvatar() {
        currentPhotoPath = dao.getPathAvatar(taiKhoan);
        if(currentPhotoPath != null)
            setPic(false);
    }

    private void addAction() {
        binding.manHinhChinhFragBtnAmNhac.setOnClickListener(v -> {
            new MainActivity().chuyenFragment(requireActivity().getSupportFragmentManager(),AmNhacFragment.newInstance());
        });
        binding.manHinhChinhFragBtnTinTuc.setOnClickListener(v -> {
            new MainActivity().chuyenFragment(requireActivity().getSupportFragmentManager(),TinTucFragment.newInstance());
        });
        binding.manHinhChinhFragImgAvatar.setOnClickListener(v -> {
            openCamera();
        });

    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (Exception ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(requireActivity(),
                        "vn.edu.spx.camerav1.fileprovider",
                        photoFile);


                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Save a file: path for use with ACTION_VIEW intents
        assert image != null;
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void setPic(boolean check) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = 1;
        bmOptions.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        binding.manHinhChinhFragImgAvatar.setImageBitmap(bitmap);
        if(check)
            dao.updatePathAvatar(taiKhoan,currentPhotoPath);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            setPic(true);
        }
    }
}