package com.ming6464.minhngph25430_assignmnet.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.ming6464.minhngph25430_assignmnet.DAO.NguoiDungDAO;
import com.ming6464.minhngph25430_assignmnet.DTO.BaiHat;
import com.ming6464.minhngph25430_assignmnet.DTO.NguoiDung;
import com.ming6464.minhngph25430_assignmnet.DangKyActi;
import com.ming6464.minhngph25430_assignmnet.DangNhapActi;
import com.ming6464.minhngph25430_assignmnet.Fragment.AmNhacFragment;
import com.ming6464.minhngph25430_assignmnet.MainActivity;
import com.ming6464.minhngph25430_assignmnet.R;
import com.ming6464.minhngph25430_assignmnet.RoomDb.AssignmentRoom;
import com.ming6464.minhngph25430_assignmnet.SharePreferences.AssignmentShare;

public class MyService extends Service {
    private NguoiDungDAO nguoiDungDAO;
    public static final String CHANNEL_ID = "CHANNEL_ID";
    private MediaPlayer player;
    Notification notification = null;
    @Override
    public void onCreate() {
        super.onCreate();
        nguoiDungDAO = AssignmentRoom.getInstance(this).getNguoiDungDAO();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.hasExtra(DangKyActi.KEYOBJ_DANGKYACTI))
            handlerActionDangKy(intent);
        else if(intent.hasExtra(DangNhapActi.KEYOBJ_DANGNHAPACTI))
            handlerActionDangNhap(intent);
        else{
            handlerActionPhatNhac(intent);
        }
        return START_NOT_STICKY;
    }

    private void handlerActionPhatNhac(Intent intent) {
        BaiHat obj = (BaiHat) intent.getSerializableExtra(AmNhacFragment.KEYOBJ_AMNHACFRAG);
        if(player == null)
            player = new MediaPlayer();
        else{
            player.stop();
            player.release();
            player = new MediaPlayer();
        }
        player = MediaPlayer.create(this,obj.getMusic());
        player.start();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_ID, "Service Channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel1.setSound(null,null);
            if(manager != null){
                manager.createNotificationChannel(channel1);
            }
        }
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),obj.getAnh());
        RemoteViews remote = new RemoteViews(getPackageName(), R.layout.item_noti_nhac);
        remote.setTextViewText(R.id.layout_noti_tv_name,obj.getTenBaiHat());
        remote.setTextViewText(R.id.layout_noti_tv_singer,obj.getCaSi());
        remote.setImageViewBitmap(R.id.layout_noti_img_anh,bitmap);
        notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_musicnote_24)
                .setCustomContentView(remote)
                .setSound(null)
                .build();
        startForeground(1,notification);
    }

    private void handlerActionDangNhap(Intent intent) {
        NguoiDung obj = (NguoiDung) intent.getSerializableExtra(DangNhapActi.KEYOBJ_DANGNHAPACTI),
                obj2 = nguoiDungDAO.getObj(obj.getTaiKhoan());
        if(obj2 != null && obj2.getMatKhau().equals(obj.getMatKhau())){
            Toast.makeText(this, "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
            AssignmentShare.getInstance(this).putAccount(obj2,intent.getBooleanExtra(DangNhapActi.KEYCHECK_DANGNHAPACTI,false));
            intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            intent = new Intent();
            intent.setAction(DangNhapActi.KEYPHANHOi_DANGNHAPACTI);
            sendBroadcast(intent);
            return;
        }
        Toast.makeText(this, "Tài khoản hoặc mật khẩu không đúng !", Toast.LENGTH_SHORT).show();
    }

    private void handlerActionDangKy(Intent intent) {
        if(nguoiDungDAO.insert((NguoiDung) intent.getSerializableExtra(DangKyActi.KEYOBJ_DANGKYACTI))){
            Toast.makeText(this, "Đăng ký người dùng thành công !", Toast.LENGTH_SHORT).show();
            intent = new Intent();
            intent.setAction(DangKyActi.KEYPHANHOI_DANGKYACTI);
            sendBroadcast(intent);
            return;
        }
        Toast.makeText(this, "Người dùng đã tồn tại !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
        player = null;
    }
}
