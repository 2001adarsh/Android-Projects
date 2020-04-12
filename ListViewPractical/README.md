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
