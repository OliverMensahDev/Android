package recipefindermyapp.gohool.com.recipefinderapp.data

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import recipefindermyapp.gohool.com.recipefinderapp.R
import recipefindermyapp.gohool.com.recipefinderapp.ShowLinkActivity
import recipefindermyapp.gohool.com.recipefinderapp.model.Recipe

/**
 * Created by paulodichone on 7/8/17.
 */
class RecipeListAdapter(private val list: ArrayList<Recipe>,
                        private val context: Context): RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        if (holder != null) {
            holder.bindView(list[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, position: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.list_row, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size


    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.recipeTitle)
        var thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)
        var ingredients = itemView.findViewById<TextView>(R.id.recipeIngredients)
        var linkButton = itemView.findViewById<Button>(R.id.linkButton)


        fun bindView(recipe: Recipe) {
            title.text = recipe.title
            ingredients.text = recipe.ingredients



            if (!TextUtils.isEmpty(recipe.thumbnail)) {
                Picasso.with(context)
                        .load(recipe.thumbnail)
                        .placeholder(android.R.drawable.ic_menu_report_image)
                        .error(android.R.drawable.ic_menu_report_image)
                        .into(thumbnail)
            }else {
                Picasso.with(context).load(R.mipmap.ic_launcher).into(thumbnail)
            }


            linkButton.setOnClickListener {

                var intent = Intent(context, ShowLinkActivity::class.java)
                intent.putExtra("link", recipe.link.toString())
                context.startActivity(intent)
            }


        }

    }
}