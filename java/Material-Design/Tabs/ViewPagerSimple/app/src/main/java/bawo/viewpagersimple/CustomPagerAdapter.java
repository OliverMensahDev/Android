package bawo.viewpagersimple;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {

    private final String TAG = CustomPagerAdapter.class.getSimpleName();

    private List<DataModel> itemList;

    private Context context;

    private LayoutInflater inflater;

    public CustomPagerAdapter(Context context, List<DataModel> itemList) {
        this.context = context;
        this.itemList = itemList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override // Checks whether View is associated with Object
    // Or Object is associated with Page view i.e. view or not
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Log.i(TAG, "instantiateItem " + position);

        // Get the View of the Single ViewPager Item
        View itemView = inflater.inflate(R.layout.viewpager_item, container, false);

        // Locate the ImageView and TextView
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageItem);
        TextView textView = (TextView) itemView.findViewById(R.id.textViewItem);

        // Get the Data Model for current Position
        DataModel dataModel = itemList.get(position);

        // Set the Data for Image and Text
        imageView.setImageResource(dataModel.imageId);
        textView.setText(dataModel.title);

        // Add viewpager_item.xml to View Pager
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.i(TAG, "destroyItem " + position);
        // Remove viewpager_item.xml from ViewPager container
        container.removeView((FrameLayout) object);
    }
}