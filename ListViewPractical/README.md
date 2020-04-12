## Using list view as usual.

```
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            View itemView = getLayoutInflater().inflate(R.layout.list_resource, viewGroup, false);

            TextView tvname = itemView.findViewById(R.id.tv_name);
            TextView tvuid = itemView.findViewById(R.id.tv_uid);
            Course course = getItem(i);

            tvname.setText(course.getName());
            tvuid.setText(course.getUid());

            return itemView;
        }
```

## Optimised List View Usage
### This is done by not making more layouts but rather reusing the layout which has been created.
#### 1. Using view the getView provides instead of every time creating a new different view.
```
 public View getView(int i, View view, ViewGroup viewGroup) {

            if(view == null) {
                view = getLayoutInflater().inflate(R.layout.list_resource, viewGroup, false);
            }

            TextView tvname = view.findViewById(R.id.tv_name);
            TextView tvuid = view.findViewById(R.id.tv_uid);
            Course course = getItem(i);

            tvname.setText(course.getName());
            tvuid.setText(course.getUid());

            return view;
        }
```        
#### 2. Removing redundant usage of FindviewbyId by making a ViewHolder for that purpose
```
 @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolderCourse holder;
            if(view == null) {
                view = getLayoutInflater().inflate(R.layout.list_resource, viewGroup, false);
                 holder = new ViewHolderCourse(view);
                view.setTag(holder);
            }else{
                holder = (ViewHolderCourse) view.getTag();
            }

            Course course = getItem(i);

            holder.tvname.setText(course.getName());
            holder.tvuid.setText(course.getUid());

            return view;
        }

        class ViewHolderCourse{
            TextView tvname, tvuid;
            ViewHolderCourse(View view){
                tvname = view.findViewById(R.id.tv_name);
                tvuid = view.findViewById(R.id.tv_uid);
            }
        }
  ```      
