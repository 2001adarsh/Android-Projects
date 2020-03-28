### OnSupportNavigateUp
A function given in Navigational Drawer by default in android.
It's actually getSupportActionBar().setDisplayHomeAsUpEnabled(true);

if you override OnOptionsItemSelected, OnSupportNavigateUp will not be called. you can make sure it gets called by adding a default: case inside the OnOptionsItemSelected like so:

```
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
        case R.id.something:
            Intent intent = new Intent(this,someActivity.class);
            startActivity(intent);
            finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
}
```
The super.onOptionsItemSelected(item) will make sure OnSupportNavigateUp is called. This is in case you don't want the case dealt inside your OnOptionsItemSelected
