package app.com.movie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MovieActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static int pageNum = 1;

    static movieVO[] vos;

    private final String movieKey ="28337cecac8957a62bcd7505a43cccf4";
    protected String movieDate ="";



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

        /////////////////어제날짜로 movieDate 설정하기
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);

        String yesterday = date.format(cal.getTime());
        movieDate = yesterday;
        //////////////////////////////////////////////


        new Thread(){
            @Override
            public void run() {
                movieJSON();
            }
        }.start();

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
                Log.i("MovieActivity",">>>"+pageNum+"<<<pageNum");
                startActivity(intent);
            }
        });
/////////////////////////////////////
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /////////////////////////////////////


    }//end onCreate()

//////////////////////////////////
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        TextView naviname = (TextView) findViewById(R.id.navi_Name);

        SharedPreferences sp2 = getSharedPreferences("session",MODE_PRIVATE);
        String strId = sp2.getString("id","guest");

        naviname.setText(strId);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.allmovies) {
            // Handle the camera action
            Log.i("onNIS","All movielist(MovielistActivity)");
            Intent intent = new Intent(
                    getApplicationContext(),
                    MovielistActivity.class);
            startActivity(intent);

        } else if (id == R.id.mymovie) {
            Log.i("onNIS","mymovie(seatsearch.do)");
            Intent intent = new Intent(
                    getApplicationContext(),
                    MyReservationActivity.class);
            startActivity(intent);

        } else if (id == R.id.myprofile) {
            Log.i("onNIS","myprofile(StatusActivity)");
            Intent intent = new Intent(
                    getApplicationContext(),
                    StatusActivity.class);
            startActivity(intent);
        } else if (id == R.id.mobozo) {
            Log.i("onNIS","mobozo");
            Intent intent = new Intent(
                    getApplicationContext(),
                    SensorActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    ///////////////////////


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        //////////////////////////////////



        TextView textView;


        //////////////////////////////



        //////////////////////////////////


        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {



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


            ///////////TIMERTASK/////////
            TimerTask timerTask =new TimerTask() {
                @Override
                public void run() {
                    settingThread();
                }
            };

            Timer timer = new Timer();
            timer.schedule(timerTask,1500);


            //////////////////////////

            DecimalFormat df = new DecimalFormat("###,###,###,###"); //관객수 포맷형태 변경 (가독성) ex) 1,525,566 /double->String

            /////////////// 각 페이지 에 띄울 내용 들
            ImageView movieImage = (ImageView) rootView.findViewById(R.id.movieImage);
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
                movieImage.setImageResource(R.drawable.beautyandbeast);



            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
                movieImage.setImageResource(R.drawable.kong);


            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==3){
                movieImage.setImageResource(R.drawable.logan);
            }

            ///////////////

//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            return rootView;
        }

        Handler mHandler = new Handler();

        public void settingThread(){

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        DecimalFormat df = new DecimalFormat("###,###,###,###"); //관객수 포맷형태 변경 (가독성) ex) 1,525,566 /double->String

                        //필요 한 정보들 출력: 순위, 순위변동, 영화제목, 개봉일, 전일 관객, 누적관객 수


                        if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                            textView.setText(vos[0].getPage());
//                            Log.i("Thread",vos[0].getPage());


                        } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                            textView.setText(vos[1].getPage());
//                            Log.i("Thread",vos[1].getPage());

                        } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                            textView.setText(vos[2].getPage());
//                            Log.i("Thread",vos[2].getPage());
                        }

                    Log.i("ThreadNum",">>>>>"+pageNum+"<<<<<");
                    }
                });

        }//end settingThread()
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


    public void movieJSON(){
        HttpURLConnection conn = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        URL url = null;
        URL url2 = null;
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
                for(int i=0; i<arr_DBOL.length(); i++){
                    vos[i] = new movieVO();
                }
                String[] strs_main= new String[arr_DBOL.length()];

                for(int i=0; i<arr_DBOL.length();i++){
                    JSONObject obj_DBOL = arr_DBOL.getJSONObject(i);

                    DecimalFormat df = new DecimalFormat("###,###,###,###"); //관객수 포맷형태 변경 (가독성) ex) 1,525,566 /double->String
                    vos[i].setPage("RANK:"+obj_DBOL.getString("rank")+" 순위변동 "+obj_DBOL.getString("rankInten")+"  "+obj_DBOL.getString("movieNm")+"\n"
                            +"개봉일:"+obj_DBOL.getString("openDt")+"\n 전일 관객 수:"+df.format(Double.parseDouble(obj_DBOL.getString("audiCnt")))+" 누적 관객 수:"+df.format(Double.parseDouble(obj_DBOL.getString("audiAcc"))));
                    vos[i].setRank(obj_DBOL.getString("rank"));
                    vos[i].setRankInten(obj_DBOL.getString("rankInten"));
                    vos[i].setMovieNm(obj_DBOL.getString("movieNm"));
                    vos[i].setOpenDt(obj_DBOL.getString("openDt"));
                    vos[i].setAudiCnt(obj_DBOL.getString("audiCnt"));
                    vos[i].setAudiAcc(obj_DBOL.getString("audiAcc"));



                    //////////////////////
                    strs_main[i] = obj_DBOL.getString("movieNm");


                    ///////////////////for문 내부 영화 검색 알고리즘
                    url2 = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key="
                            + movieKey+"&movieNm="+ URLEncoder.encode(strs_main[i], "UTF-8"));
//                    Log.i("HHhHHHHH",url2.toString());
                    conn = (HttpURLConnection) url2.openConnection();
                    is = conn.getInputStream();
                    isr = new InputStreamReader(is);
                    br = new BufferedReader(isr);
                    String str2 = null;
                    sb = new StringBuilder();
                    while ((str2 = br.readLine()) != null) {
                        sb.append(str2);
                    }

                    final String txtJSON2 = sb.toString();
//                    Log.i("txtJSON2>>>>",txtJSON2);

                    JSONObject obj2 = new JSONObject(txtJSON2);
                    JSONObject obj_MLR = new JSONObject(obj2.getString("movieListResult"));
                    JSONArray arr_ML = new JSONArray(obj_MLR.getString("movieList"));
                    JSONObject obj_ML = arr_ML.getJSONObject(0);
                    vos[i].setGenreAlt(obj_ML.getString("genreAlt"));
                    vos[i].setStr(obj_ML.getString("movieNm")+"\n"+obj_ML.getString("movieNmEn"));

                    JSONArray arr_DT = new JSONArray(obj_ML.getString("directors"));
                    JSONObject obj_PM = arr_DT.getJSONObject(0);
                    vos[i].setPeopleNm(obj_PM.getString("peopleNm"));


                    DecimalFormat df2 = new DecimalFormat("###,###,###,###"); //관객수 포맷형태 변경 (가독성) ex) 1,525,566 /double->String
                    //필요 한 정보들 출력: 순위, 순위변동, 영화제목, 개봉일, 전일 관객, 누적관객 수
                    vos[i].setDetail("RANK:"+vos[i].getRank()+"  순위변동 "+vos[i].getRankInten()+"\n"
                            +vos[i].getGenreAlt()+"\n"
                            +"개봉일:"+vos[i].getOpenDt()+"\n 전일 관객 수:"+df2.format(Double.parseDouble(vos[i].getAudiCnt()))
                            +"\n 누적 관객 수:"+df2.format(Double.parseDouble(vos[i].getAudiAcc()))
                            +"\n 감독 :"+vos[i].getPeopleNm());

                    ////////////////////////////////////
                }



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
