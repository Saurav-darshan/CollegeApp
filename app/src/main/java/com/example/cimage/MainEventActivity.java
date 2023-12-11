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

public class MainEventActivity extends AppCompatActivity {

    ViewPager viewPagerIndependence, viewPagerHero, viewPagerHoli, viewPagerCsr;
    private Timer timerIndependence, timerHero, timerHoli, timerCsr;
    private int currentIndependencePage = 0, currentHeroPage = 0, currentHoliPage = 0, currentCsrPage = 0;

    int[] heroImages = {
            R.drawable.pls101,
            R.drawable.pls102,
            R.drawable.pls103,
            R.drawable.pls104,
            R.drawable.pls105
    };

    int[] independenceImages = {
            R.drawable.pls1,
            R.drawable.pls2,
            R.drawable.pls4,
            R.drawable.pls5,
            R.drawable.pls3
    };

    int[] holiImages = {
            R.drawable.mid1,
            R.drawable.mid2,
            R.drawable.mid3,
            R.drawable.mid4,
            R.drawable.mid5

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
        setContentView(R.layout.main_event_layout);

        // placement card
        viewPagerIndependence = findViewById(R.id.viewPagerplacement);
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
        viewPagerHero = findViewById(R.id.viewPagerhero);
        ViewPagerAdapterKranti viewPagerAdapterKranti = new ViewPagerAdapterKranti(this);
        viewPagerHero.setAdapter(viewPagerAdapterKranti);

        timerHero = new Timer();
        timerHero.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (currentHeroPage == heroImages.length) {
                            currentHeroPage = 0;
                        }
                        viewPagerHero.setCurrentItem(currentHeroPage++);
                    }
                });
            }
        }, 2000, 2000);

         //news CardView
        viewPagerHoli = findViewById(R.id.viewPagermedia);
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

//        // Social Events CardView
//        viewPagerCsr = findViewById(R.id.viewPagerCsr);
//        ViewPagerAdapterCsr viewPagerAdapterCsr = new ViewPagerAdapterCsr(this);
//        viewPagerCsr.setAdapter(viewPagerAdapterCsr);
//
//        timerCsr = new Timer();
//        timerCsr.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                new Handler(Looper.getMainLooper()).post(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (currentCsrPage == csrImages.length) {
//                            currentCsrPage = 0;
//                        }
//                        viewPagerCsr.setCurrentItem(currentCsrPage++);
//                    }
//                });
//            }
//        }, 2000, 2000);
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
            return heroImages.length;
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
            imageView.setImageResource(heroImages[position]);

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
