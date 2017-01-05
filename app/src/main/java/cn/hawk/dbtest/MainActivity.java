package cn.hawk.dbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.hawk.dbtest.db.UserDAO;
import cn.hawk.dbtest.model.User;

public class MainActivity extends AppCompatActivity {
    private TextView mDbTestLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbTestLabel = (TextView) findViewById(R.id.mDbTestLabel);
        testDB();
    }

    private void testDB() {
        User user = new User();
        user.id = 1;
        user.age = 18;
        user.name = "antush";
        mDbTestLabel.append(user.toString());
        UserDAO dao = new UserDAO(this);
        dao.addOrUpdate(user);

        User user1 = dao.queryById(user.id);
        mDbTestLabel.append("\n");
        mDbTestLabel.append(user1.toString());
    }
}
