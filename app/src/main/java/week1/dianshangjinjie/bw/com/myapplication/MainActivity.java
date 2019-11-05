package week1.dianshangjinjie.bw.com.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import week1.dianshangjinjie.bw.com.myapplication.bean.Person;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tianmei.myapplication.greendao.gen.DaoMaster;
import com.example.tianmei.myapplication.greendao.gen.DaoSession;
import com.example.tianmei.myapplication.greendao.gen.PersonDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DaoMaster daoMaster;
    private DaoMaster.DevOpenHelper medhlper;
    private DaoSession daoSession;
    private PersonDao personDao;
    private SQLiteDatabase database;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();


    }

    //创建表
    private void initData() {
        medhlper = new DaoMaster.DevOpenHelper(this, "sport-db", null);
        database = medhlper.getWritableDatabase();
        daoMaster =  new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    //添加
    public void insert(View view) {
        person = new Person();
        person.setName("韩鹏飞");
        person.setAge(21);
        daoSession.insert(person);

    }
    //查询
    public void select(View view) {
       List<Person> list =  daoSession.loadAll(Person.class);
        Log.e("tag", "select: size "+list.size() );
        Log.e("tag", "select: size "+list.get(0).getName());
    }

    //修改
    public void update(View view) {
        person.setName("韩鹏飞11");
        daoSession.update(person);
    }

    //删除
    public void delete(View view) {
        daoSession.delete(person);
    }
}
