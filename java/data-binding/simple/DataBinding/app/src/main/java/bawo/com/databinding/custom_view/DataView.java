package bawo.com.databinding.custom_view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import bawo.com.databinding.data.DataSource;
import bawo.com.databinding.databinding.DataViewBinding;

public class DataView extends FrameLayout {

    public DataView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        DataSource dataSource = DataSource.get("Data View");
        DataViewBinding binding = DataBindingUtil.findBinding(this);
        binding.setDataSource(dataSource);
    }
}
