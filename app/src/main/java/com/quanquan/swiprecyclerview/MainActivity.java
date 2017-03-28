package com.quanquan.swiprecyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.quanquan.swiprecyclerview.recyclerviewadapter.BaseSwipeMenuAdapter;
import com.quanquan.swiprecyclerview.recyclerviewadapter.OnItemClickListener;
import com.quanquan.swiprecyclerview.recyclerviewadapter.RecyclerViewHolder;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SwipeMenuRecyclerView mSwipeMenuRecyclerView;
    private BaseSwipeMenuAdapter mBaseSwipeMenuAdapter;
    private List<String> mStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeMenuRecyclerView= (SwipeMenuRecyclerView) findViewById(R.id.recycler_view);
        mStrings = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            mStrings.add("帅比权" + i );
        }
        mSwipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        mSwipeMenuRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mSwipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单Item点击监听。
        mSwipeMenuRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
        // 设置菜单创建器。
        mSwipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mBaseSwipeMenuAdapter = new BaseSwipeMenuAdapter<String>(this,mStrings){

            @Override
            protected int getItemLayout(int viewType) {
                return R.layout.item;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, String item) {
                holder.setText(R.id.tv_title,item);
            }
        };
        mBaseSwipeMenuAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Toast.makeText(MainActivity.this, "position-->"+position, Toast.LENGTH_SHORT).show();
            }
        });
        mSwipeMenuRecyclerView.setAdapter(mBaseSwipeMenuAdapter);
    }

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView#RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            // TODO 推荐调用Adapter.notifyItemRemoved(position)，也可以Adapter.notifyDataSetChanged();
            if (menuPosition == 0) {// 删除按钮被点击。
                mStrings.remove(adapterPosition);
                mBaseSwipeMenuAdapter.notifyItemRemoved(adapterPosition);
            }
        }
    };
    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            // 添加右侧的，如果不添加，则右侧不会出现菜单。
                SwipeMenuItem deleteItem = new SwipeMenuItem(MainActivity.this)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
        }
    };


}
