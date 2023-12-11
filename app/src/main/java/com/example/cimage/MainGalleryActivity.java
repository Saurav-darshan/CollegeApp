package com.example.cimage;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.util.Timer;
import java.util.TimerTask;

public class MainGalleryActivity extends AppCompatActivity {

    ViewPager viewPagerIndependence, viewPagerKranti, viewPagerHoli, viewPagerCsr;
    private Timer timerIndependence, timerKranti, timerHoli, timerCsr;
    private int currentIndependencePage = 0, currentKrantiPage = 0, currentHoliPage = 0, currentCsrPage = 0;

    int[] independenceImages = {
            R.drawable.ind4,
            R.drawable.ind3,
            R.drawable.idn1,
            R.drawable.ind2,
            R.drawable.ind5
    };

    int[] krantiImages = {
            R.drawable.knt1,
            R.drawable.knt2,
            R.drawable.knt3,
            R.drawable.knt4,
            R.drawable.knt5
    };

    int[] holiImages = {
            R.drawable.holi5,
            R.drawable.holi1,
            R.drawable.holi2,
            R.drawable.holi3,
            R.drawable.holi4
    };
    int[] csrImages = {
            R.drawable.csp1,
            R.drawable.csp2,
            R.drawable.csp3,
            R.drawable.sa1,
            R.drawable.sa2,
            R.drawable.sa3,
            R.drawable.sdt1,
            R.drawable.sdt2,
            R.drawable.sdt3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_gallery_layout);

        // Independence Day CardView
        viewPagerIndependence = findViewById(R.id.viewPagerIndependence);
        ViewPagerAdapterIndependence viewPagerAdapterIndependence = new ViewPagerAdapterIndependence(this);
        viewPagerIndependence.setAdapter(viewPagerAdapterIndependence);

        timerIndependence = new Timer();
        timerIndependence.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (currentIndependencePage == independenceImages.length) {
                            currentIndependencePage = 0;
                        }
                        viewPagerIndependence.setCurrentItem(currentIndependencePage++);
                    }
                });
            }
        }, 2000, 2000);

        // Krantitirth CardView
        viewPagerKranti = findViewById(R.id.viewPagerKranti);
        ViewPagerAdapterKranti viewPagerAdapterKranti = new ViewPagerAdapterKranti(this);
        viewPagerKranti.setAdapter(viewPagerAdapterKranti);

        timerKranti = new Timer();
        timerKranti.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (currentKrantiPage == krantiImages.length) {
                            currentKrantiPage = 0;
                        }
                        viewPagerKranti.setCurrentItem(currentKrantiPage++);
                    }
                });
            }
        }, 2000, 2000);

        // Holi Mela CardView
        viewPagerHoli = findViewById(R.id.viewPagerHoli);
        ViewPagerAdapterHoli viewPagerAdapterHoli = new ViewPagerAdapterHoli(this);
        viewPagerHoli.setAdapter(viewPagerAdapterHoli);

        timerHoli = new Timer();
        timerHoli.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (currentHoliPage == holiImages.length) {
                            currentHoliPage = 0;
                        }
                        viewPagerHoli.setCurrentItem(currentHoliPage++);
                    }
                });
            }
        }, 2000, 2000);

        // Social Events CardView
        viewPagerCsr = findViewById(R.id.viewPagerCsr);
        ViewPagerAdapterCsr viewPagerAdapterCsr = new ViewPagerAdapterCsr(this);
        viewPagerCsr.setAdapter(viewPagerAdapterCsr);

        timerCsr = new Timer();
        timerCsr.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (currentCsrPage == csrImages.length) {
                            currentCsrPage = 0;
                        }
                        viewPagerCsr.setCurrentItem(currentCsrPage++);
                    }
                });
            }
        }, 2000, 2000);
    }

    public class ViewPagerAdapterIndependence extends PagerAdapter {
        private Context context;

        public ViewPagerAdapterIndependence(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return independenceImages.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_slider_layout, null);

            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(independenceImages[position]);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    public class ViewPagerAdapterKranti extends PagerAdapter {
        private Context context;

        public ViewPagerAdapterKranti(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return krantiImages.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_slider_layout, null);

            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(krantiImages[position]);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    public class ViewPagerAdapterHoli extends PagerAdapter {
        private Context context;

        public ViewPagerAdapterHoli(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return holiImages.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_slider_layout, null);

            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(holiImages[position]);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    public class ViewPagerAdapterCsr extends PagerAdapter {
        private Context context;

        public ViewPagerAdapterCsr(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return csrImages.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_slider_layout, null);

            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(csrImages[position]);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
