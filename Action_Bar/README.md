# Action Bar

Items present:
 - [x] Back Navigation button.
 - [ ] App Icon
 - [ ] View Control
 - [ ] Action Control buttons
 - [ ] Overflow icon and Menu when clicked
 - [ ] Search widget
 - [ ] Tabbed Navigation
 
 
 ## Steps:
 1. Create action_bar.xml in res/menu
 2. Calling action bar in main menu
   ```
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
 
        return super.onCreateOptionsMenu(menu);
    }
```    
 3. Giving functions to selected item.
 ``` 
   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.action_search:
            // search action
            return true;
            .
            .
            .
         default:                 return super.onOptionsItemSelected(item);   
            }
       } 
```       
  4. For Back Navigation, in location activity add 
 ``` 
   ActionBar ac = getActionBar();

        //Enabling up Back Navigation/ Return to Home
        ac.setDisplayHomeAsUpEnabled(true);
```
  And in Manifest you have to mention the parent activity of location activity.
```
<!-- Location Found activity -->
        <activity android:name=".LocationFound"
            android:parentActivityName=".MainActivity"
            android:label="@string/LocationfoundID">
        </activity>
```
   5. 
