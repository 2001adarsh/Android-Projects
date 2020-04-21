## Weak References to avoid memory leak in AsyncTask
What happens when the Activity is destroyed? The AsyncTask is holding a reference to the Activity, and the Activity cannot be collected by the GC. This is what we called a memory leak.

#### The memory leak actually happens not only when the Activity is destroyed per-se, but as well when it is forcibly destroyed by the system due to a change in the configuration or more memory is needed, etc. If the AsyncTask is complex (i.e., keeps references to Views in the Activity, etc) it could even lead to crashes, since the view references are null.

```
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MyAsyncTask(this).execute();
    }
    private static class MyAsyncTask extends AsyncTask {
        private WeakReference<MainActivity> mainActivity;    
        
        public MyAsyncTask(MainActivity mainActivity) {   
            this.mainActivity = new WeakReference<>(mainActivity);            
        }
        @Override
        protected Object doInBackground(Object[] params) {
            return doSomeStuff();
        }
        private Object doSomeStuff() {
            //do something to get result
            return new Object();
        }
        @Override
        protected void onPostExecute(Object object) {
            super.onPostExecute(object);
            if (mainActivity.get() != null){
                //adapt contents
            }
        }
    }
}
```





