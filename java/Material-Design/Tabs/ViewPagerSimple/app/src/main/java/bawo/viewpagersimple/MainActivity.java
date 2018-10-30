package bawo.viewpagersimple;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Prepare the Data Model
        List<DataModel> itemList = getDataList();

        // Locate the ViewPager in activity_main.xml
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        // Create instance of PagerAdapter
        CustomPagerAdapter adapter = new CustomPagerAdapter(this, itemList);

        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);
    }

    public List<DataModel> getDataList(){
        List<DataModel> itemLists = new ArrayList<>();
        int[] imageIds = new int[]{
                R.drawable.image1, R.drawable.image2, R.drawable.image3,
                R.drawable.image4, R.drawable.image5,R.drawable.image6
        };

        String[] titles = new String[]{
                "Image 1", "Image 2", "Image 3",
                "Image 4", "Image 5", "Image 6"
        };
        int count = imageIds.length;
        for(int i= 0; i< count; i++){
            itemLists.add(new DataModel(imageIds[i], titles[i]));
        }
        return itemLists;
    }


}
