package com.sky.redhome.views;

import android.annotation.SuppressLint;
import android.content.Context;  
import android.graphics.Bitmap;  
import android.graphics.Bitmap.Config;  
import android.graphics.Canvas;  
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;  
import android.graphics.PorterDuff.Mode;  
import android.graphics.PorterDuffXfermode;  
import android.graphics.Rect;  
import android.graphics.drawable.BitmapDrawable;  
import android.graphics.drawable.Drawable;  
import android.util.AttributeSet;  
import android.util.Log;
import android.view.View;
import android.widget.ImageView;  
  
/** 
 * Բ�ε�Imageview & ��ɫ��Ե
 * 
 * @author bingyang.djj & sky
 *  
 *  �ؼ�����Բ��������ɱ���ۣ�
 */  
public class CircleImageView extends ImageView {  
    private Paint paint = new Paint();  
  
    public CircleImageView(Context context) {  
        super(context);  
    }  
  
    public CircleImageView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
    }  
  
    @Override  
    protected void onDraw(Canvas canvas) {  
  
        Drawable drawable = getDrawable();  
        if (null != drawable) {  
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();  
            Bitmap b = toRoundCorner(bitmap, 14); 
            
            int viewWidth  = this.getHeight();       // ��ÿؼ�View�ĸ߶�
            
           
            paint.reset();  
            //width ��Ҫ��ԭͼ�ĳ�����Ϊ�µ��и���Ѿ���С��width
            int width = bitmap.getWidth();
            int height = b.getHeight();
                        
            int newWidth = viewWidth;
            int newHeight = viewWidth;
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            // �õ��µ�ͼƬ
            Bitmap newbm = Bitmap.createBitmap(b, 0, 0, width, height, matrix,
            true);
            //��ͼ����
            float x = viewWidth/2;
            paint.setAntiAlias(true);  
            paint.setColor(Color.WHITE);
            canvas.drawCircle(x , x , x  , paint);
            
            paint.reset();
            // ���ڻ�����
            canvas.drawBitmap(newbm, 0, 0, paint);
                                 
/*            final Rect rect = new Rect(0, 0, width, height);          
            canvas.drawBitmap(newbm, rect, rect, paint);*/
        } else {  
            super.onDraw(canvas);  
        }  
    }  
  
    private Bitmap toRoundCorner(Bitmap bitmap, int pixels) {  
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),  
                bitmap.getHeight(), Config.ARGB_8888);  
        Canvas canvas = new Canvas(output);  
          
        final int color = 0xff424242;  
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());  
        paint.setAntiAlias(true);  
        canvas.drawARGB(0, 0, 0, 0);  
        paint.setColor(color);  
        float x = bitmap.getWidth() /2;  
        canvas.drawCircle(x , x , x - 8 , paint);  
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN)); 
        
        canvas.drawBitmap(bitmap, rect, rect, paint);  
        

        return output;  
    }  
}  