package trainings.adaptivelayout.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

import trainings.adaptivelayout.MyCommunicator;
import trainings.adaptivelayout.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
	List<Landscape> mData;
	private LayoutInflater inflater;
	private Context mContext;

	public RecyclerAdapter(Context context, List<Landscape> data) {
		this.mContext = context;
		inflater = LayoutInflater.from(context);
		this.mData = data;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.list_item, parent, false);
		MyViewHolder holder = new MyViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		Landscape current = mData.get(position);
		holder.setData(current, position);
		holder.setListeners();
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	class MyViewHolder extends RecyclerView.ViewHolder {
		TextView title;
		ImageView imgThumb;
		int position;
		Landscape current;

		View itemView;

		public MyViewHolder(View itemView) {
			super(itemView);

			this.itemView = itemView;
			title       = (TextView)  itemView.findViewById(R.id.tvTitle);
			imgThumb    = (ImageView) itemView.findViewById(R.id.img_row);
		}

		public void setData(Landscape current, int position) {
			this.title.setText(current.getTitle());
			this.imgThumb.setImageResource(current.getImageID());
			this.position = position;
			this.current = current;
		}

		public void setListeners() {

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					MyCommunicator myCommunicator = (MyCommunicator) mContext;
					myCommunicator.displayDetails(current.getTitle(), current.getDescription());
				}
			});
		}
	}
}
