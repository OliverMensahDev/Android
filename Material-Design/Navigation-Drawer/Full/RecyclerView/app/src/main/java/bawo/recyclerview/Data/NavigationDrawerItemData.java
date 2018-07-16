package bawo.recyclerview.Data;

import java.util.ArrayList;
import java.util.List;

import bawo.recyclerview.Model.NavigationDrawerItem;
import bawo.recyclerview.R;

public class NavigationDrawerItemData {

    public static List<NavigationDrawerItem> getData(){
        List<NavigationDrawerItem> dataList = new ArrayList<>();

        int[] imageIds = getImages();
        String[] titles = getTitles();

        for(int    i= 0; i< titles.length; i++){
            NavigationDrawerItem navItem = new NavigationDrawerItem();
            navItem.setTitle(titles[i]);
            navItem.setImageId(imageIds[i]);
            dataList.add(navItem);
        }
        return dataList;
    }

    private static int[] getImages(){
        return new int[]{
                R.drawable.ic_birds,
                R.drawable.ic_animal,
                R.drawable.ic_forest,
                R.drawable.ic_ocean,
                R.drawable.ic_planet,
                R.drawable.ic_landscape
        };
    }

    private  static String[] getTitles(){
        return new String[]{
                "Birds", "Animal", "Forest", "Ocean", "Planets", "Landscape"
        };
    }

}
