package app.com.movie;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MovieActivity extends AppCompatActivity {

    static int pageNum = 1;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        ///////////////page 변경시 확인 하는 것 ////
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                Log.i("onPageSelected",""+position+1);
                pageNum = position+1;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //////////////////////////////////////////////


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(
                        getApplicationContext(),
                        MovieTimeActivity.class);
                intent.putExtra("pageNum",pageNum);
                Log.i("MainActivity",">>>"+pageNum+"<<<pageNum");
                startActivity(intent);
            }
        });




    }//end onCreate()


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        //////////////////////////////////

        TextView textView;

        private final String movieKey ="e53d0f96b48200021aeb4d7decc28155";
        private String movieDate ="";

        movieVO[] vos;
        //////////////////////////////////


        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {


            /////////////////어제날짜로 movieDate 설정하기
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE,-1);

            String yesterday = date.format(cal.getTime());
            movieDate = yesterday;
            //////////////////////////////////////////////

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
            textView = (TextView) rootView.findViewById(R.id.section_label);

            new Thread(){
                @Override
                public void run() {
                    movieJSON();
                }
            }.start();

            //////////////////////////

            DecimalFormat df = new DecimalFormat("###,###,###,###"); //관객수 포맷형태 변경 (가독성) ex) 1,525,566 /double->String

            /////////////// 각 페이지 에 띄울 내용 들
            ImageView movieImage = (ImageView) rootView.findViewById(R.id.movieImage);
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
                movieImage.setImageResource(R.drawable.kong);



            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
                movieImage.setImageResource(R.drawable.logan);


            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==3){
                movieImage.setImageResource(R.drawable.haebing);
            }

            ///////////////

//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            return rootView;
        }

        Handler mHandler = new Handler();

        public void movieJSON(){
            HttpURLConnection conn = null;
            InputStream is = null;
            InputStreamReader isr = null;
            BufferedReader br = null;

            URL url = null;
            try {
                url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?" +
                        "key="+movieKey+"&targetDt="+movieDate);
                conn = (HttpURLConnection) url.openConnection();
                is = conn.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                String str = null;
                StringBuilder sb = new StringBuilder();
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
//            Log.i("MovieActivity",sb.toString());

                final String txtJSON = sb.toString();

                ////txtJSON 의 각 데이터 VO 에 담음.
                try {
                    JSONObject obj = new JSONObject(txtJSON);
                    JSONObject obj_BOR = new JSONObject(obj.getString("boxOfficeResult"));
                    JSONArray arr_DBOL = new JSONArray(obj_BOR.getString("dailyBoxOfficeList"));
//                JSONObject[] obj_DBOL = new JSONObject[arr_DBOL.length()]; //혹시 JSONObject 따로저장하고 싶을 때 사용
                    //for 문 내부도 변경하여야 함.



                    vos = new movieVO[arr_DBOL.length()];   //inner class 인 mhandler에 사용 하므로 final.

                    for(int i=0; i<arr_DBOL.length();i++){
                        vos[i] = new movieVO();
                        JSONObject obj_DBOL = arr_DBOL.getJSONObject(i);
                        vos[i].setRank(obj_DBOL.getString("rank"));
                        vos[i].setRankInten(obj_DBOL.getString("rankInten"));
                        vos[i].setMovieNm(obj_DBOL.getString("movieNm"));
                        vos[i].setOpenDt(obj_DBOL.getString("openDt"));
                        vos[i].setAudiCnt(obj_DBOL.getString("audiCnt"));
                        vos[i].setAudiAcc(obj_DBOL.getString("audiAcc"));
                    }




                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            DecimalFormat df = new DecimalFormat("###,###,###,###"); //관객수 포맷형태 변경 (가독성) ex) 1,525,566 /double->String

                            //필요 한 정보들 출력: 순위, 순위변동, 영화제목, 개봉일, 전일 관객, 누적관객 수

                            if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
                                textView.setText("RANK:"+vos[0].getRank()+" 순위변동 "+vos[0].getRankInten()+" "+vos[0].getMovieNm()+"\n"
                                        +"개봉일:"+vos[0].getOpenDt()+"\n 전일 관객 수:"+df.format(Double.parseDouble(vos[0].getAudiCnt()))+" 누적 관객 수:"+df.format(Double.parseDouble(vos[0].getAudiAcc())));



                            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
                                textView.setText("RANK:"+vos[1].getRank()+" 순위변동 "+vos[1].getRankInten()+" "+vos[1].getMovieNm()+"\n"
                                        +"개봉일:"+vos[1].getOpenDt()+"\n 전일 관객 수:"+df.format(Double.parseDouble(vos[1].getAudiCnt()))+" 누적 관객 수:"+df.format(Double.parseDouble(vos[1].getAudiAcc())));

                            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==3){
                                textView.setText("RANK:"+vos[2].getRank()+" 순위변동 "+vos[2].getRankInten()+" "+vos[2].getMovieNm()+"\n"
                                        +"개봉일:"+vos[2].getOpenDt()+"\n 전일 관객 수:"+df.format(Double.parseDouble(vos[2].getAudiCnt()))+" 누적 관객 수:"+df.format(Double.parseDouble(vos[2].getAudiAcc())));
                            }


                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if(br!= null){
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(isr!= null){
                    try {
                        isr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(is != null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(conn != null){
                    conn.disconnect();
                }
            }
        }//end movieJSON()
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }



}
