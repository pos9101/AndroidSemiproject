package app.com.movie;

import android.content.Context;
import android.graphics.Color;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by OKSU on 2017. 3. 3..
 */
public class TestAdapter extends BaseAdapter {

    private final String movieKey ="28337cecac8957a62bcd7505a43cccf4";
    private String movieDate ="";




    View v = null;

    String[] strs = new String[]{
            "movie1","movie2","movie3","movie4","movie5","movie6","movie7","movie8","movie9","movie10",
    };

    String[] details = new String[10];

    Integer[] imgs = new Integer[]{
        R.drawable.kong,R.drawable.logan,R.drawable.haebing,
            R.drawable.jaesim,R.drawable.twenty_three,R.drawable.light_between_ocean,
            R.drawable.lalaland,R.drawable.moonlight,R.drawable.girlonthetrain,
            R.drawable.girlonthetrain
            };
    Context c;
    LayoutInflater inflater;
    public TestAdapter(Context c) {
        this.c = c;
        inflater = (LayoutInflater)c.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);


        /////////////////어제날짜로 movieDate 설정하기
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);

        String yesterday = date.format(cal.getTime());
        movieDate = yesterday;
        //////////////////////////////////////////////


    }
    @Override   //필수
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override   //필수
    public View getView(final int position, View convertView, ViewGroup parent) {
//        TextView tv = new TextView(c);
//        tv.setTextSize(30.0f);
//        tv.setTextColor(Color.BLUE);
//        tv.setText(strs[position]);
//        return tv;

        ImageView iv = null;
        TextView tv = null;
        TextView tv_detail = null;

//        ///////////////////
//        new Thread(){
//            @Override
//            public void run() {
//                movieJSON();
//            }
//        }.start();
/////////////////////



        if(convertView==null){
            v = inflater.inflate(R.layout.field,null);

        }else{
            v = convertView;

        }

        iv = (ImageView)v.findViewById(R.id.gridMovieImage);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300 , 300);
        params.gravity = Gravity.CENTER;    //Layout 과 같이 설정
        iv.setLayoutParams(params);     //image 자체에 설정
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setPadding(20, 20, 20, 20);

        tv = (TextView)v.findViewById(R.id.gridMovieName);
        tv.setTextColor(Color.BLACK);

        iv.setImageResource(imgs[position]);
        tv.setText(MovieActivity.vos[position].getStr());

        tv_detail = (TextView) v.findViewById(R.id.gridDetails);
        tv_detail.setText(MovieActivity.vos[position].getDetail());

        return v;
    }

//    Handler mHandler = new Handler();
//
//    public void movieJSON(){
//        HttpURLConnection conn = null;
////        HttpURLConnection conn2 = null;
//        InputStream is = null;
////        InputStream is2 = null;
//        InputStreamReader isr = null;
////        InputStreamReader isr2 = null;
//        BufferedReader br = null;
////        BufferedReader br2 = null;
//
//        URL url = null;
//        URL url2 = null;
//        try {
//            url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?" +
//                    "key="+movieKey+"&targetDt="+movieDate);
//            conn = (HttpURLConnection) url.openConnection();
//            is = conn.getInputStream();
//            isr = new InputStreamReader(is);
//            br = new BufferedReader(isr);
//            String str = null;
//            StringBuilder sb = new StringBuilder();
//            while ((str = br.readLine()) != null) {
//                sb.append(str);
//            }
////            Log.i("MovieActivity",sb.toString());
//
//            final String txtJSON = sb.toString();
//
//            ////txtJSON 의 각 데이터 VO 에 담음.
//            try {
//                JSONObject obj = new JSONObject(txtJSON);
//                JSONObject obj_BOR = new JSONObject(obj.getString("boxOfficeResult"));
//                JSONArray arr_DBOL = new JSONArray(obj_BOR.getString("dailyBoxOfficeList"));
////                JSONObject[] obj_DBOL = new JSONObject[arr_DBOL.length()]; //혹시 JSONObject 따로저장하고 싶을 때 사용
//                //for 문 내부도 변경하여야 함.
//
//
//
//                final movieVO[] vos = new movieVO[arr_DBOL.length()];   //inner class 인 mhandler에 사용 하므로 final.
//
//                for(int i=0; i<arr_DBOL.length();i++){
//                    vos[i] = new movieVO();
//                    JSONObject obj_DBOL = arr_DBOL.getJSONObject(i);
//                    vos[i].setRank(obj_DBOL.getString("rank"));
//                    vos[i].setRankInten(obj_DBOL.getString("rankInten"));
//                    vos[i].setOpenDt(obj_DBOL.getString("openDt"));
//                    vos[i].setAudiCnt(obj_DBOL.getString("audiCnt"));
//                    vos[i].setAudiAcc(obj_DBOL.getString("audiAcc"));
//                    strs[i] = obj_DBOL.getString("movieNm");
//
//
//                    ///////////////////for문 내부 영화 검색 알고리즘
//                    url2 = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key="
//                            + movieKey+"&movieNm="+ URLEncoder.encode(strs[i], "UTF-8"));
////                    Log.i("HHhHHHHH",url2.toString());
//                    conn = (HttpURLConnection) url2.openConnection();
//                    is = conn.getInputStream();
//                    isr = new InputStreamReader(is);
//                    br = new BufferedReader(isr);
//                    String str2 = null;
//                    sb = new StringBuilder();
//                    while ((str2 = br.readLine()) != null) {
//                        sb.append(str2);
//                    }
//
//                    final String txtJSON2 = sb.toString();
////                    Log.i("txtJSON2>>>>",txtJSON2);
//
//                    JSONObject obj2 = new JSONObject(txtJSON2);
//                    JSONObject obj_MLR = new JSONObject(obj2.getString("movieListResult"));
//                    JSONArray arr_ML = new JSONArray(obj_MLR.getString("movieList"));
//                    JSONObject obj_ML = arr_ML.getJSONObject(0);
//                    vos[i].setGenreAlt(obj_ML.getString("genreAlt"));
//                    strs[i] =obj_ML.getString("movieNm")+"\n"+obj_ML.getString("movieNmEn");
//
//                    JSONArray arr_DT = new JSONArray(obj_ML.getString("directors"));
//                    JSONObject obj_PM = arr_DT.getJSONObject(0);
//                    vos[i].setPeopleNm(obj_PM.getString("peopleNm"));
//
//                    ////////////////////////////////////
//
//                }
//
//
//
//
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        DecimalFormat df = new DecimalFormat("###,###,###,###"); //관객수 포맷형태 변경 (가독성) ex) 1,525,566 /double->String
//
//                        //필요 한 정보들 출력: 순위, 순위변동, 영화제목, 개봉일, 전일 관객, 누적관객 수
//
//                        for(int i=0; i<10; i++){
//                             details[i]= "RANK:"+vos[i].getRank()+"  순위변동 "+vos[i].getRankInten()+"\n"
//                                     +vos[i].getGenreAlt()+"\n"
//                                    +"개봉일:"+vos[i].getOpenDt()+"\n 전일 관객 수:"+df.format(Double.parseDouble(vos[i].getAudiCnt()))
//                                     +"\n 누적 관객 수:"+df.format(Double.parseDouble(vos[i].getAudiAcc()))
//                                     +"\n 감독 :"+vos[i].getPeopleNm();
//                        }
//
//
//
//                    }
//                });
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            if(br!= null){
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(isr!= null){
//                try {
//                    isr.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(is != null){
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(conn != null){
//                conn.disconnect();
//            }
//        }
//    }//end movieJSON()


}
