package trainings.bawo.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import trainings.bawo.R;
import trainings.bawo.animation.AnimationUtil;
import trainings.bawo.model.Course;


public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder>  {
    public OnItemClickListener itemClickListener;
    private CourseData courseData = new CourseData();
    private int previousPosition = 0;


    @Override
    public CourseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View courseRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_row, parent, false);
        return new ViewHolder(courseRow);
    }

    @Override
    public void onBindViewHolder(final CourseListAdapter.ViewHolder holder, int position) {
        final Context context = holder.courseTitle.getContext();
        Course course = courseData.courseList().get(position);
        holder.courseTitle.setText(course.getCourseName());
        Picasso.get()
                .load(course.getImageResourceId(context))
                .into(holder.courseImageView);
        Picasso.get()
                .load(course.getImageResourceIdForAuthor(context))
                .into(holder.authorImageView);
        Bitmap photo = BitmapFactory.decodeResource(context.getResources(), course.getImageResourceId(context));
        Palette.from(photo).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
               int bgColor =  palette.getMutedColor(ContextCompat.getColor(context,
                        android.R.color.black));

                holder.courseTitle.setBackgroundColor(bgColor);
                holder.authorImageView.setBorderColor(bgColor);

            }
        });
        if (position > previousPosition){
            AnimationUtil.animate(holder,true);
        }
        else {
            AnimationUtil.animate(holder, false);
        }
        previousPosition = position;
    }

    @Override
    public int getItemCount() {
        //Log.d("TAG: ","Item count: " + courseData.courseList().size());
        return courseData.courseList().size();
    }

    public void setOnClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {


        public TextView courseTitle;
        public ImageView courseImageView;
        public CircleImageView authorImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            //Very important piece of code - register our view to receive click events
            itemView.setOnClickListener(this);

            courseTitle = itemView.findViewById(R.id.courseTitleId);
            courseImageView = itemView.findViewById(R.id.courseImageId);
            authorImageView = itemView.findViewById(R.id.authorImageID);
        }


        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}
