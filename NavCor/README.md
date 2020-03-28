  
  
  * Adding Navigation Header using java:
  
  This app:headerLayout inflates the specified layout into the header automatically. This can alternatively be done at runtime with:
  ```
  // Lookup navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
  // Inflate the header view at runtime
        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header);
  // We can now look up items within the header if needed
        ImageView ivHeaderPhoto = headerLayout.findViewById(R.id.imageView);
```
