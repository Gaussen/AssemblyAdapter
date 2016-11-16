package me.xiaopan.assemblyadaptersample.itemfactory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.xiaopan.assemblyadapter.AssemblyRecyclerItem;
import me.xiaopan.assemblyadapter.AssemblyRecyclerItemFactory;
import me.xiaopan.assemblyadaptersample.R;
import me.xiaopan.assemblyadaptersample.bean.User;

public class UserRecyclerItemFactory extends AssemblyRecyclerItemFactory<UserRecyclerItemFactory.UserRecyclerItem> {

    private EventListener eventListener;

    public UserRecyclerItemFactory(Context context) {
        this.eventListener = new EventProcessor(context);
    }

    @Override
    public boolean isTarget(Object data) {
        return data instanceof User;
    }

    @Override
    public UserRecyclerItem createAssemblyItem(ViewGroup parent) {
        return new UserRecyclerItem(R.layout.list_item_user, parent);
    }

    public interface EventListener{
        void onClickHead(int position, User user);
        void onClickName(int position, User user);
        void onClickSex(int position, User user);
        void onClickAge(int position, User user);
        void onClickJob(int position, User user);
    }

    private static class EventProcessor implements EventListener {
        private Context context;

        public EventProcessor(Context context) {
            this.context = context;
        }

        @Override
        public void onClickHead(int position, User user) {
            Toast.makeText(context, "别摸我头，讨厌啦！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onClickName(int position, User user) {
            Toast.makeText(context, "我就叫"+user.name+"，咋地不服啊！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onClickSex(int position, User user) {
            Toast.makeText(context, "我还就是"+user.sex+"个的了，有本事你捅我啊！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onClickAge(int position, User user) {
            String message;
            if(user.sex.contains("男") || user.sex.contains("先生")){
                message = "哥今年"+user.age+"岁了，该找媳妇了！";
            }else{
                message = "姐今年"+user.age+"岁了，该找人嫁了！";
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onClickJob(int position, User user) {
            Toast.makeText(context, "我是名光荣的"+user.job, Toast.LENGTH_SHORT).show();
        }
    }

    public class UserRecyclerItem extends AssemblyRecyclerItem<User> {
        private ImageView headImageView;
        private TextView nameTextView;
        private TextView sexTextView;
        private TextView ageTextView;
        private TextView jobTextView;

        public UserRecyclerItem(int itemLayoutId, ViewGroup parent) {
            super(itemLayoutId, parent);
        }

        @Override
        protected void onFindViews() {
            headImageView = findViewById(R.id.image_userListItem_head);
            nameTextView = findViewById(R.id.text_userListItem_name);
            sexTextView = findViewById(R.id.text_userListItem_sex);
            ageTextView = findViewById(R.id.text_userListItem_age);
            jobTextView = findViewById(R.id.text_userListItem_job);
        }

        @Override
        protected void onConfigViews(Context context) {
            headImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventListener.onClickHead(getLayoutPosition(), getData());
                }
            });
            nameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventListener.onClickName(getLayoutPosition(), getData());
                }
            });
            sexTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventListener.onClickSex(getLayoutPosition(), getData());
                }
            });
            ageTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventListener.onClickAge(getLayoutPosition(), getData());
                }
            });
            jobTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventListener.onClickJob(getLayoutPosition(), getData());
                }
            });
        }

        @Override
        protected void onSetData(int position, User user) {
            headImageView.setImageResource(user.headResId);
            nameTextView.setText(user.name);
            sexTextView.setText(user.sex);
            ageTextView.setText(user.age);
            jobTextView.setText(user.job);
        }
    }
}
